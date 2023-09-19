# Introduction

This is a learning repository for creating OAuth2.0 Authorization Server with Spring Boot and Spring Security.

## Features

1. Grant Using Auth Code
2. Token Validation with JWT Token

## Description

1. Add the `jwt.key` with key in the `application.properties`. This key will be used as JWT signing key.
2. Add Bean for JWTAccessTokenConverter with the jwt key for the signing.
3. Create bean of TokenStore to generate the token using the JWTTokenStore with access token converter as the field for it's converter.
4. Add token store and token converter in the endpoints manager.

### Configuring Public Key Access

1. Add the client credentials in inmemory details.
2. Add the configuration to allow the exchange of the public key with valid credentials.

## Usage

1. Receive the oauth token by using the curl with following command
```curl
curl -v -XPOST -u client:secret "http://localhost:8080/oauth/token?grant_type=password&username=eklak&password=12345&scope=read" 
```
This will return the following response:
```curl
{
    "access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTUxNDYwNTYsInVzZXJfbmFtZSI6ImVrbGFrIiwiYXV0aG9yaXRpZXMiOlsicmVhZCJdLCJqdGkiOiIxYzQ4MDJkNC04NTZhLTRiYmEtODE2NC0xNzU1NTk0ZDVhODIiLCJjbGllbnRfaWQiOiJjbGllbnQiLCJzY29wZSI6WyJyZWFkIl19.SroAvG-Mu8AURrlcE69dTnPmNPryFxQMpa0sC1C60ao",
    "token_type":"bearer",
    "refresh_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJla2xhayIsInNjb3BlIjpbInJlYWQiXSwiYXRpIjoiMWM0ODAyZDQtODU2YS00YmJhLTgxNjQtMTc1NTU5NGQ1YTgyIiwiZXhwIjoxNjk3Njk0ODU2LCJhdXRob3JpdGllcyI6WyJyZWFkIl0sImp0aSI6IjE5ODU2YTdhLWZhMjYtNDUwNi04Y2E5LTFmN2RlNjY4ODk2ZSIsImNsaWVudF9pZCI6ImNsaWVudCJ9.JpoRsRTDO3pP5Ab8GTWZOWWM7-cVXAffZxR5P87h3gY",
    "expires_in":43199,
    "scope":"read",
    "jti":"1c4802d4-856a-4bba-8164-1755594d5a82"
}
```

2. Public Key exchange

```curl
curl -u rclient:rsecret http://localhost:8080/oauth/token_key
```

Response
```json
{
  "alg":"SHA256withRSA",
  "value":"-----BEGIN PUBLIC KEY-----\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0NsCeAzozd6eH4ug5Sr+dXzVxN05yk/bexl0KI0qckfNzkofZrtb0oLJH0durho2eP0gzWSQUOt+kHpGOIcwy70GXhqcFH7rfaLF4Z+u01U1IPdVfhoFxX+bDuy8Lw/U2AHvktq0T5CLNaH37q3tey+Kv9xGz9hpSTKCr8HGw9OM1OojE/vKj5MQdbh7o3E0iRdX267FGFgABHXtxCBfI717LVRL3sXfY5uI1ChnklUhDoMr48Uu7i1Jxr9YEWPkUUYMfdzK67XT4yvVRZT2vOXcFtEtNlogWw+lzY7hLyLXGuom+z4KUHcylFnZv5cXGBKOlvowemr4tn/QamnpewIDAQAB\n-----END PUBLIC KEY-----"
}
```
