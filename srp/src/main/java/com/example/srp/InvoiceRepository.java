package com.example.srp;

import java.util.*;

public class InvoiceRepository {
    private final List<Invoice> storage = new ArrayList<>();
    public void save(Invoice invoice) { storage.add(invoice); }
    public List<Invoice> findAll() { return Collections.unmodifiableList(storage); }
}