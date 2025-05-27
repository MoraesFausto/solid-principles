package com.example.composition;

public class SmsNotifier implements Notifier {
    public void send(String message) {
        System.out.println("Enviando SMS: " + message);
    }
}