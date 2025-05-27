package com.example.dip;

public interface IEmailService {
    void sendEmail(String to, String subject, String body);
}