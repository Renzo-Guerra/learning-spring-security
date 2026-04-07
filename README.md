# Authentication & role authorization

It was about time to learn spring security...

This app creates a database with users, each of them 
has a role assigned, depending on which one you have you will be able to access different 
endpoints.

Inside of _/bootstrap/UserSeeder.java_  there are preloaded users that you can modify. 
For instance only 1 SUPER_ADMIN user was created

## How to lunch it
Clone the repo, while having docker open run "docker-compose up" command 
through the console (while standing on root directory). Then you can use 
postman to test it

## Roles
### SUPER_ADMIN
Can access ANY endpoint
### ADMIN
SUPER_ADMIN users can also access their endpoints
### USER
ADMIN and SUPER_ADMIN users can also access their endpoints

## REST - Enpoints

### Public Enpoints

Register a new user, Role USER will be assigned automatically

POST: /api/auth/register
```JSON
{
  "name": "John",
  "surname": "Doe",
  "email": "johndoe@gmail.com",
  "password": "yellowsubmarine"
}
```
---
Login. If credentials are valid then it will return a JWT
that will allow you to access different endpoints (depending on your role assigned)

POST: /api/auth/login
```JSON
{
  "email": "johndoe@gmail.com",
  "password": "yellowsubmarine"
}
```

### USER Enpoints

Find current user personal information

GET: /api/users/me

PAYLOAD
```JSON
{
  "name": "String",
  "surname": "String",
  "email": "String",
  "createdAt": "LocalDate"
}
```
---

### ADMIN Enpoints

Find all users personal information

GET: /api/users

PAYLOAD
```JSON
[
  {
    "name": "String",
    "surname": "String",
    "email": "String",
    "createdAt": "LocalDate"
  }
]

```
---

### SUPER_ADMIN Enpoints

Register a new user with an ADMIN role

POST: /api/auth/register/admin
```JSON
{
  "name": "Mattew",
  "surname": "Hills",
  "email": "mathills@gmail.com",
  "password": "anightattheopera"
}
```
---