package com.example.dip;

public class App {
    public static void main(String[] args) {
        IEmailService emailService = new SmtpEmailService();
        OrderProcessor processor = new OrderProcessor(emailService);
        processor.process(1001);
    }
}