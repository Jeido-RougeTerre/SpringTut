package com.jeido.exercisespring.services;

import com.jeido.exercisespring.dao.MessageRepository;
import com.jeido.exercisespring.dao.UserRepository;
import com.jeido.exercisespring.entities.Message;
import com.jeido.exercisespring.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public Message save(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public Message findById(UUID id) {
        return messageRepository.findById(id).orElse(null);
    }

    public void delete(UUID id) {
        messageRepository.deleteById(id);
    }

    public List<Message> findBySender(User user) {

        return messageRepository.findByUser(user);
    }

    public boolean isAuthor(User _user, Message _message) {
        Message message = findById(_message.getId());

        if (message == null) return false;

        User user = userRepository.findById(_user.getId()).orElse(null);

        if (user == null) return false;

        return message.getUser().equals(user);
    }

    public Message update(Message message) {
        return messageRepository.save(message);
    }
}
