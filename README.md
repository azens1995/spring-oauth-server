# Introduction

This is a learning repository for creating OAuth2.0 Authorization Server with Spring Boot and Spring Security.

## Features

1. Grant using Auth Code

## Usage

1. After running the server, use this code to access the OAuth server in browser

```
http://localhost:8080/oauth/authorize?response_type=code&client_id=client&scope=read
```
This will redirect to the login page.

2. Use user credentials to login.
3. After logging in, server asks to grant/reject the required scopes explicitly.
4. Once the scopes are granted, the user is redirected to the redirectUri mentioned in `AuthServerConfig` at line `64` as `http://localhost:9090/home`.
5. Auth server redirects to the redirect uri with the code.
```
http://localhost:9090/home?code=qeSLSt
```
6. Code received in the redirectUri can be used to get the access token as
```
curl -v -XPOST -u client:secret "http://localhost:8080/oauth/token?grant_type=authorization_code&scope=read&code=qeSLSt"
```
7. It will provide the following response.

```
{
    "access_token":"0fa3b7d3-e2d7-4c53-8121-bd531a870635",
    "token_type":"bearer",
    "refresh_token":"77d5e36a-d114-4896-871f-a6502c1c3f1a",
    "expires_in":43052,
    "scope":"read"
}
```

Note: Authorization code can only be used once.
