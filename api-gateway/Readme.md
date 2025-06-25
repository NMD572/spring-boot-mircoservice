## API Gateway

## Knowledge

### What is API Gateway?

- Acts as an entry point for clients to access backend services.
- Forward the request to the downstream service.
- Commonly used in the distributed systems and microservices architectures.

### Benefits of using API Gateway

- Handles cross-cutting concerns such as authentication, logging, and rate limiting, ssl termination.

### Drawbacks of using API Gateway

- Increases complexity as we have to maintain yet another comoponent in our system landscape.
- Needs a lot of effort to maintain it as it will be a single point of failure - need to run multiple instances of it.
- Increased latency as it adds an additional hop in the request flow.
