package com.ys.rabbitmq.service;


public interface MyService {
    void receive();

    void directReceive(String message);
}
