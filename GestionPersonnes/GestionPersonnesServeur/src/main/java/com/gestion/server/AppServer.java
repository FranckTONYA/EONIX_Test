package com.gestion.server;

public interface AppServer {

    void create();
    void configure();
    void start();
    void restart();
    void stop();

    default void launch() {
        this.configure();
        this.create();
        this.start();
    }
}
