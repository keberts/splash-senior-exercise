# Payment Summary Exercise

A small Java 17 project with tests already configured. See `PROMPT.md` for the
task. The exercise has a second part that your interviewer will give you partway
through the session.

## Requirements
- JDK 17 or newer on your PATH (`java -version` to check).
- Internet access on first run (the Gradle wrapper downloads Gradle and JUnit once, then caches them).

## Running the tests
```
./gradlew test          # macOS / Linux
gradlew.bat test        # Windows
```

## Starting state
On a fresh checkout `./gradlew test` runs two tests:
- `emptyInputProducesEmptyResult` — PASSES against the stub.
- `workedExample` — FAILS until you implement `summarize`. This is expected; it
  shows you the happy-path target and confirms the harness works.

## Layout
```
src/main/java/com/example/payments/
  PaymentStatus.java      enum
  PaymentEvent.java       input record
  CustomerSummary.java    output record
  PaymentSummarizer.java  <-- implement summarize() here
src/test/java/com/example/payments/
  PaymentSummarizerTest.java   add your own tests here
```
