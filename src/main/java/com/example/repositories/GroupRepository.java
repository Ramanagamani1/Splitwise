package com.example.repositories;

import com.example.models.Group;
import com.example.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    Optional<Group> findById(Long id);

    List<Group> findAllByParticipantsContaining(List<User> users);
}
