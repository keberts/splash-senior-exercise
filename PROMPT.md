# Senior Engineer Technical Exercise: Payment Summary

You are working in a small Java 17 project with tests already configured.

This exercise has two parts. You have the first part now; the second part will be
given to you partway through the session. During **Part 1, please do not use
generative AI** (no chat assistant, no Copilot-style multi-line generation).
Plain IDE completion and the Java documentation are fine. AI will be allowed in
Part 2.

We are evaluating your reasoning and engineering decisions, not your ability to
recall exact Java method names. Please explain your thinking as you work. You can
ask requirement questions freely; when several implementation approaches are
reasonable, we will usually ask you to pick one and explain why.

---

## Part 1: Implement a payment summary

The system receives status events for payment attempts:

```java
public enum PaymentStatus { PENDING, SUCCEEDED, FAILED }

public record PaymentEvent(
    String paymentId,
    String customerId,
    long amountCents,
    PaymentStatus status,
    Instant occurredAt
) {}
```

Implement:

```java
public record CustomerSummary(
    long succeededAmountCents,
    int pendingPaymentCount
) {}

public final class PaymentSummarizer {
    public Map<String, CustomerSummary> summarize(List<PaymentEvent> events) {
        // Implement this
    }
}
```

Rules:
- Events may arrive in any order.
- A payment may have multiple events.
- The event with the latest `occurredAt` represents the current state of that payment.
- A SUCCEEDED payment contributes its amount to the customer's `succeededAmountCents`.
- A PENDING payment contributes one to the customer's `pendingPaymentCount`.
- A FAILED payment contributes neither.
- Each payment is expected to keep the same `customerId` and `amountCents` across its events.
- Amounts are non-negative. Required fields are non-null.
- Empty input produces an empty result.
- Result ordering does not matter.

The contract does not define what happens when two events for the same payment
have identical timestamps. Identify that ambiguity and explain what you would do
about it.

Add whatever tests you consider important.

### Example
Given these events in any order:
```
payment-1, customer-A, 1000, PENDING,   10:00
payment-2, customer-A, 2500, FAILED,    10:01
payment-1, customer-A, 1000, SUCCEEDED, 10:02
payment-3, customer-B, 5000, SUCCEEDED, 10:03
```
The result should be:
```
customer-A: succeededAmountCents = 1000, pendingPaymentCount = 0
customer-B: succeededAmountCents = 5000, pendingPaymentCount = 0
```
