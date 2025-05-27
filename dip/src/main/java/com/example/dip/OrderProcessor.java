package com.example.dip;

public class OrderProcessor {
    private final IEmailService emailService;
    public OrderProcessor(IEmailService emailService) {
        this.emailService = emailService;
    }
    public void process(int orderId) {
        // lógica de processamento...
        emailService.sendEmail(
            "cliente@exemplo.com", 
            "Pedido processado", 
            "Pedido " + orderId + " concluído.");
    }
}
