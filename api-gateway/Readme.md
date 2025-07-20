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

## Using Keycloak with API Gateway

### Setting up Keycloak

1. Create a new realm in Keycloak.
2. Create a new client in the realm.
   - 2.1: Create a new client for test with Postman (Client Authentication):
     - **Step 1**: Set the client ID and enable always display in UI (optional).
     - **Step 2**: To enable client authentication, you have to set Client authentication to "ON". In Authentication flow, set the "Service accounts roles" to "On". Turn off all other options.
     - **Step 3**: If you have Root Url or Home URL, set it in the next step (optional). Note: When login with Postman, you will not be redirected to this URL.
   - 2.2: Create a new client for integration with FE (Angular, React, etc.):
     - **Step 1**: Set the client ID and enable always display in UI (optional).
     - **Step 2**: Set the Valid redirect URIs = <your-homepage-url> (e.g., `http://localhost:4200/*` for Angular). Web origin: \* (allow all orgin connect) or your homepage host (example: `http://localhost:4200` - allow only one origin: `http://localhost:4200` connect).
     - **Step 3**: Turn off Client Authentication and only enable "Authentication flow" in Authentication Flow. At this project, you don't need to use Authority from Keycloak. So, you can turn off all other options.
3. Set up keycloak to application.properties file:

   - **Step 1**: Click realm settings in the left sidebar.
   - **Step 2**: Setting for integration with FE: In login settings, enable:
     - "Login with email" (optional, if you want to use email for login).
     - "Registration" (optional, if you want to allow users to register).
     - "Remember me" (optional, if you want to use remember me functionality).
   - **Step 3**: In general settings, click "OpenID Endpoint Configuration" and copy the issuer URI.
   - **Step 4**: Fill it into your `application.properties` file.

   ```properties
   spring.security.oauth2.resourceserver.jwt.issuer-uri=<issuer-uri>
   ```

   - **Step 5**: Create security configuration class to enable security in your application.

   ```java
   @Configuration
   public class SecurityConfig {

      @Bean
      public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
         return httpSecurity.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
               .oauth2ResourceServer(oauthe2 -> oauthe2.jwt(Customizer.withDefaults())).build();
      }
   }
   ```

### Using Steps

1. Create/Open a new request in Postman.
2. Go to the "Authorization" tab.
3. Select "OAuth 2.0" from the "Type" dropdown.
4. Set information in Configure New Token session:
   - **Token Name**: Keycloak Token
   - **Grant Type**: Client Credentials
   - **Access Token URL**: `http://localhost:8080/realms/{realm}/protocol/openid-connect/token`
   - **Client ID**: `{client_id}`
   - **Client Secret**: `{client_secret}`
   - **Scope**: `{scope}` (optional, depending on your Keycloak setup, default is usually `openid`)
5. Click on "Get New Access Token".
6. Postman will send a request to Keycloak to obtain the access token.
7. If successful, the access token will be displayed in the "Access Token" field.
8. Click on "Use Token" to set it as the authorization header for your requests.
9. Now you can use this token in your requests to access protected resources.

```

```
