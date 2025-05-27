package com.example.composition;

public class EmailNotifier implements Notifier {
    public void send(String message) {
        System.out.println("Enviando email: " + message);
    }
}