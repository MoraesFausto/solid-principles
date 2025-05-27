package com.example.srp;

public class InvoiceService {
    
    void execute(){
        Invoice invoice = new Invoice(250.0);
        new InvoicePrinter().print(invoice);
        new InvoiceRepository().save(invoice);
        System.out.println("Total invoices: " + new InvoiceRepository().findAll().size());
    }
}
