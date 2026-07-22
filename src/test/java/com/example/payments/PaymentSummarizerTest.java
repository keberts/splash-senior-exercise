package com.example.payments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class PaymentSummarizerTest {

    private final PaymentSummarizer summarizer = new PaymentSummarizer();

    private static PaymentEvent event(
        String paymentId, String customerId, long amountCents,
        PaymentStatus status, String isoInstant) {
        return new PaymentEvent(paymentId, customerId, amountCents, status, Instant.parse(isoInstant));
    }

    @Test
    void emptyInputProducesEmptyResult() {
        assertTrue(summarizer.summarize(List.of()).isEmpty());
    }

    @Test
    void workedExample() {
        // Supplied out of chronological order on purpose.
        List<PaymentEvent> events = List.of(
            event("payment-3", "customer-B", 5000, PaymentStatus.SUCCEEDED, "2026-01-01T10:03:00Z"),
            event("payment-1", "customer-A", 1000, PaymentStatus.PENDING,   "2026-01-01T10:00:00Z"),
            event("payment-2", "customer-A", 2500, PaymentStatus.FAILED,    "2026-01-01T10:01:00Z"),
            event("payment-1", "customer-A", 1000, PaymentStatus.SUCCEEDED, "2026-01-01T10:02:00Z")
        );

        Map<String, CustomerSummary> result = summarizer.summarize(events);

        assertEquals(2, result.size());
        assertEquals(new CustomerSummary(1000, 0), result.get("customer-A"));
        assertEquals(new CustomerSummary(5000, 0), result.get("customer-B"));
    }
}
