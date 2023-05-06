package com.example.commands;

public interface Command {

    boolean canExecute(String input);

    void execute(String input);
}
