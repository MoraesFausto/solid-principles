package com.example.composition;

public class NotifyService {
    void execute(){
        UserService service = new UserService(new EmailNotifier());
        service.notifyUser(42);
    }
}
