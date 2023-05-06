package com.example.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandRegistry {

    private RegisterUserCommand registerUserCommand;

    List<Command> commands = new ArrayList<>();

    @Autowired
    public CommandRegistry(RegisterUserCommand registerUserCommand) {
        this.registerUserCommand = registerUserCommand;
        commands.add(registerUserCommand);
    }


    public boolean registerCommand(Command command) {
        if(commands.contains(command))
             return false;
        commands.add(command);
        return true;
    }

    public boolean unregisterCommand(Command command) {
        commands.remove(command);
        return true;
    }

    public void execute(String input) {
        for(Command command: commands) {
            if(command.canExecute(input)) {
                command.execute(input);
                return;
            }
        }
    }
}
