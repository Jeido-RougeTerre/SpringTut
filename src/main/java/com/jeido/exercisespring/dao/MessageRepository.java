package com.jeido.exercisespring.dao;

import com.jeido.exercisespring.entities.Message;
import com.jeido.exercisespring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findByUser(User user);
}
