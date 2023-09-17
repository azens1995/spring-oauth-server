# Introduction

This is a learning repository for creating OAuth2.0 Authorization Server with Spring Boot and Spring Security.

## Features

1. Grant using Password

## Description

We have added the new credentials for the resource server using the `and()` while creating the clientcredentials.
Check line `67` and `68` in `AuthServerConfig` where we have added the credentials for the resource server.
This credentials will be used to access the auth server's `/oaut/check_token` endpoint.

## Usage

1. Receive the oauth token by using the curl with following command
```curl
curl -v -XPOST -u client:secret "http://localhost:8080/oauth/token?grant_type=password&username=eklak&password=12345&scope=read" 
```
This will return the following response:
```curl
{
    "access_token":"0783e67b-99e4-43b7-b052-8bec532d9bbe",
    "token_type":"bearer",
    "expires_in":43199,
    "scope":"read"
}
```

2. Call the `/oauth/check_token` endpoint to find the details about the access token
```curl
curl -XPOST -u resourceserver:resourceserversecret "http://localhost:8080/oauth/check_token?token=0783e67b-99e4-43b7-b052-8bec532d9bbe" 
```
It will produce following response:
```curl
{
    "active":true,
    "exp":1694977540,
    "user_name":"eklak",
    "authorities":["read"],
    "client_id":"client",
    "scope":["read"]
}
```
