# README: Spring Security with JWT

This document provides an overview of implementing Spring Security with JWT (JSON Web Tokens) for secure authentication and authorization. This approach is widely used for stateless, scalable applications.

---

## What is JWT?

JWT (JSON Web Token) is an open standard (RFC 7519) for securely transmitting information between parties as a JSON object. It is compact, self-contained, and digitally signed to ensure data integrity. JWT is commonly used for:

- **Authentication**: Verifying user identity.
- **Authorization**: Controlling access to resources.

### Structure of a JWT
A JWT consists of three parts, separated by dots (`.`):

1. **Header**:
    - Specifies the type of token (JWT) and the signing algorithm (e.g., HMAC SHA256).
   ```json
   {
       "alg": "HS256",
       "typ": "JWT"
   }
   ```

2. **Payload**:
    - Contains claims (user data, metadata). Common claims include:
        - `sub`: Subject (e.g., user ID).
        - `exp`: Expiration time.
        - `iat`: Issued at.
   ```json
   {
       "sub": "1234567890",
       "name": "John Doe",
       "admin": true
   }
   ```

3. **Signature**:
    - Ensures the token's integrity by encoding the header, payload, and a secret key.
   ```
   HMACSHA256(
       base64UrlEncode(header) + "." + base64UrlEncode(payload),
       secret
   )
   ```

---

## How JWT Works with Spring Security

1. **User Login**:
    - The client sends a login request with credentials.
    - If valid, the server generates a JWT and sends it back to the client.

2. **Subsequent Requests**:
    - The client includes the JWT in the `Authorization` header (`Bearer <token>`).
    - The server validates the JWT and processes the request if valid.

3. **Token Validation**:
    - Signature: Confirms the token hasn't been tampered with.
    - Expiry: Ensures the token is not expired.

---

## Steps to Implement JWT in Spring Security

### 1. Add Dependencies
Include the necessary dependencies for Spring Security and JWT in your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.11.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.11.5</version>
</dependency>
```

### 2. Configure JWT Utility
Implement a utility class to:
- Generate tokens.
- Validate tokens.
- Extract claims.

### 3. Create a Filter
Set up a filter to intercept requests and validate the JWT from the `Authorization` header.

### 4. Integrate with Spring Security
Update the `SecurityConfig` to:
- Exclude certain endpoints (e.g., `/login`) from authentication.
- Add your custom JWT filter.

---

## Best Practices

- **Secure the Secret Key**: Use environment variables or secret management tools.
- **Set Expiry Times**: Prevent long-lived tokens.
- **Refresh Tokens**: Implement a mechanism to issue new tokens without requiring re-login.
- **HTTPS**: Always use HTTPS to secure token transmission.

---

For more details, refer to the [Spring Security documentation](https://spring.io/projects/spring-security) or [JWT.io](https://jwt.io).

