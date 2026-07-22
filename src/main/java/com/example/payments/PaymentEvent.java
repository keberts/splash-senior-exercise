package com.example.payments;

import java.time.Instant;

public record PaymentEvent(
    String paymentId,
    String customerId,
    long amountCents,
    PaymentStatus status,
    Instant occurredAt
) {}
