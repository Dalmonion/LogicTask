package com.task;

public class Application {

    public static void main(String[] args) {
        Processor processor = new Processor();
        processor.start(IOService.welcomeMessage());

    }
}