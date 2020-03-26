# spring-jpa-jwt-auth
Sample JWT Web Api for authentication and role based authorization. Built on Spring Boot with JPA

## Endpoints

### Login 

#### Request
`curl -H "Content-type: application/json" -X POST -d '{"username":"admin","password":"admin"}' http://localhost:8080/login -i`

#### Response 
```
HTTP/1.1 200
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsIkNMQUlNX1RPS0VOIjoiUk9MRV9TeXN0ZW0gYWRtaW5pc3RyYXRvciIsImlhdCI6MTU4NTE5NzE4NiwiaXNzIjoiSVNTVUVSIiwiZXhwIjoxNTg1MjI1OTg2fQ.DE82sgeD3lg8FLCCXMBYBQ3zOOYa4mZ5z0T0NZGZzdg
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Length: 0
Date: Thu, 01 Jan 2020 00:00:00 GMT
```

### Create admin user
`curl -H "Content-type: application/json" -H "Authorization: Bearer ${token}}" -X POST -d '{"username":"user","password":"password"}' http://localhost:8080/admin`

### Create customer user
`curl -H "Content-type: application/json" -X POST -d '{"username":"user","password":"password"}' http://localhost:8080/customers`