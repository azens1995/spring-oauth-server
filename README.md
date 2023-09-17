# Introduction

This is a learning repository for creating OAuth2.0 Authorization Server with Spring Boot and Spring Security.

## Features

1. Grant using Client Credentials

## Description

Client Credentials grant type only requires the client credentials, not the user credentials.
Make sure that it does not have same scope access as that requires user credentials.

We have updated the scope in the `AuthServerConfig` at line `63` to `info`.

## Usage

1. Use this command to get the token

```curl
curl -v -XPOST -u client:secret "http://localhost:8080/oauth/
token?grant_type=client_credentials&scope=info"
```

2. You will receive response as:

```curl
{"access_token":"88a04c73-79ba-4a48-893b-88166f77e5f3","token_type":"bearer","expires_in":43199,"scope":"info"}
```
