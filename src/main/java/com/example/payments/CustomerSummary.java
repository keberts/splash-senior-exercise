package com.example.payments;

public record CustomerSummary(
    long succeededAmountCents,
    int pendingPaymentCount
) {}
