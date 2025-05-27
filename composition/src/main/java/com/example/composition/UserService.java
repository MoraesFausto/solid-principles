package com.example.composition;

public class UserService {
    private final Notifier notifier;
    public UserService(Notifier notifier) {
        this.notifier = notifier;
    }
    public void notifyUser(int userId) {
        notifier.send("Usuário " + userId + " notificado.");
    }
}