<<<<<<< HEAD
# Make Unauthenticated HTTP Request

Execute following command on your bash shell
``` bash
$ curl http://localhost:8080
```

## Expected Response:
```
{"message":"you are not logged in!!!"}
```

# Authenticate for HTTP Request

Execute following command on your bash shell
``` bash
$ curl -u user:password http://localhost:8080
```

where *user* is the username and *password* is the password.

## Expected Response:
 ```
 {"message":"you are logged in. current time is Tue Sep 19 20:03:49 EDT 2017"}
 ```
=======
# csye6225-fall2017
Repository for csye6225

Team member information such as Name and Email address.
Jin Li  li.jin3@husky.neu.edu
Chenyang Zhao zhao.chenya@husky.neu.edu

Prerequisites for building and deploying your application locally.
Build and Deploy instructions for web application.
Instructions to run unit, integration and/or load tests.
Link to TravisCI build for the project.
>>>>>>> 3746bd0b978ad7a78c4f9802e4640186e09b9fa3
