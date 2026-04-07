# Authentication & role authorization

It was about time to learn spring security...

This app creates a database with users, each of them 
has a role assigned, depending on which one you have you will be able to access different 
endpoints.

Inside of _/bootstrap/UserSeeder.java_  there are preloaded users that you can modify. 
For instance only 1 SUPER_ADMIN user was created.

IMPORTANT: There is already a .json file with a postman collection, 
in case you don't want to create the endpoints manually.

It's in root/spring-security.postman_collection.json

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

Find all users detailed information (everything). 
I just wanted to make sure that role was being assigned correctly.

GET: /api/users/full
```JSON
[
  {
      "id": 5,
      "name": "Matías",
      "surname": "López",
      "email": "matias.lopez@gmail.com",
      "password": "$2a$10$osD2/fU2BLOquPm7RcgxeeV.vUfs5wRm7TJPtcBieM4bP3HDRmk6u",
      "createdAt": "2026-04-07",
      "updatedAt": "2026-04-07",
      "role": {
        "id": 1,
        "name": "USER",
        "description": "Default user role",
        "createdAt": "2026-04-07",
        "updatedAt": "2026-04-07"
      },
      "accountNonExpired": true,
      "accountNonLocked": true,
      "authorities": [
        {
          "authority": "USER"
        }
      ],
      "credentialsNonExpired": true,
      "enabled": true,
      "username": "matias.lopez@gmail.com"
    },
  {
    "id": 6,
    "name": "Valentina",
    "surname": "Rodríguez",
    "email": "valentina.rodriguez@gmail.com",
    "password": "$2a$10$/Gv1JjH56gjO4rB5iBzje.7k5rrvlFDPqnV8HuTp69X/fFbUNA/4W",
    "createdAt": "2026-04-07",
    "updatedAt": "2026-04-07",
    "role": {
      "id": 1,
      "name": "USER",
      "description": "Default user role",
      "createdAt": "2026-04-07",
      "updatedAt": "2026-04-07"
    },
    "accountNonExpired": true,
    "accountNonLocked": true,
    "authorities": [
      {
        "authority": "USER"
      }
    ],
    "credentialsNonExpired": true,
    "enabled": true,
    "username": "valentina.rodriguez@gmail.com"
  }
]
```
---