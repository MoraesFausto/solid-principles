package com.example.dip;

public class SmtpEmailService implements IEmailService {
    public void sendEmail(String to, String subject, String body) {
        System.out.println("Email enviado para " + to);
    }
}
