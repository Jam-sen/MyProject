package com.ys.rabbitmq.service;


public interface MyService {
    void sendMessage(String message);

    void sendFanoutMessage(String message);

    void sendTopicMessage(String message);
}
