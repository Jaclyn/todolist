# todolist

README
1.1. instruction for running the app<br/>
Run command `docker-compose up` to start the application.<br/>
Run command `docker-compose stop` to start the application.

1.2. Instruction for testing the app
Requires at least Maven version 3.2.5 to run.<br/>
Successfully run on Maven version 3.8.4 in Eclipse.<br/>
Modified the file TodolistApplicationTests.java to make the value as below has the latest valid access token.<br/>
To get the latest access token, please login using http://localhost:8080/login.

        private String token = [latest access token]

Run command `mvn test` to execute the test.

1.3. Instruction for building the app
Run command `docker-compose build` to build the application in docker.

1.4. interface documentation
Access to http://localhost:8080/login to login using gmail account and get the access token before calling the following service.<br/>
To get the latest access token, please login using http://localhost:8080/login.<br/>
In case no access token successfully retrieve, add the keywords "NoAuth" at the end of URL to access the non authentication version.<br/>

For example: /list to /listNoAuth for non authentication acess



    API services available to call:
        1   /verifyToken
                description: To verify if the token is valid.
                method: POST
                body(JSON): [access token value from login]

                SAMPLE:
                curl -X POST http://localhost:8080/todolist/verifyToken -H "Content-Type: application/json" -d "ya29.A0AVA9y1udoO5P7tpWS914eCzeT3ocyemS0NEsXzuvrV2M-xcl-4v-tkMIJJa3eIaj54Ef5yaPElIhM54svPFSZWOWlXLw_3PsqTWXIPVMWbyw8K9OPvaGa3RcgCgjTAOZxxJDkePveNfUJ0ix6NIf9eN1R4O8YUNnWUtBVEFTQVRBU0ZRRTY1ZHI4ZWlHbEFiUldCVUcxcXNOYklWMWlSUQ0163"

        2  /list
                description: To list all the todo list as per user login.
                method: GET
                header: AUTHORIZATION=[access token value from login]

                SAMPLE:
                curl -X GET http://localhost:8080/todolist/list -H "Content-Type: application/json" -H "AUTHORIZATION:ya29.A0AVA9y1udoO5P7tpWS914eCzeT3ocyemS0NEsXzuvrV2M-xcl-4v-tkMIJJa3eIaj54Ef5yaPElIhM54svPFSZWOWlXLw_3PsqTWXIPVMWbyw8K9OPvaGa3RcgCgjTAOZxxJDkePveNfUJ0ix6NIf9eN1R4O8YUNnWUtBVEFTQVRBU0ZRRTY1ZHI4ZWlHbEFiUldCVUcxcXNOYklWMWlSUQ0163"

        3  /add
                description: To add a new todo item.
                method: POST
                body(JSON): "TO DO LIST Description"
                header: AUTHORIZATION=[access token value from login]

                SAMPLE:
                curl -X POST http://localhost:8080/todolist/add -H "Content-Type: application/json" -H "Content-Type: application/json" -H "AUTHORIZATION:ya29.A0AVA9y1udoO5P7tpWS914eCzeT3ocyemS0NEsXzuvrV2M-xcl-4v-tkMIJJa3eIaj54Ef5yaPElIhM54svPFSZWOWlXLw_3PsqTWXIPVMWbyw8K9OPvaGa3RcgCgjTAOZxxJDkePveNfUJ0ix6NIf9eN1R4O8YUNnWUtBVEFTQVRBU0ZRRTY1ZHI4ZWlHbEFiUldCVUcxcXNOYklWMWlSUQ0163" -d "Buy vegetables"

        4  /delete
                description: To delete a todo item.
                method: POST
                body(JSON): [ID of todo list to delete]
                header: AUTHORIZATION=[access token value from login]

                SAMPLE:
                curl -X POST http://localhost:8080/todolist/delete -H "Content-Type: application/json" -H "AUTHORIZATION:ya29.A0AVA9y1udoO5P7tpWS914eCzeT3ocyemS0NEsXzuvrV2M-xcl-4v-tkMIJJa3eIaj54Ef5yaPElIhM54svPFSZWOWlXLw_3PsqTWXIPVMWbyw8K9OPvaGa3RcgCgjTAOZxxJDkePveNfUJ0ix6NIf9eN1R4O8YUNnWUtBVEFTQVRBU0ZRRTY1ZHI4ZWlHbEFiUldCVUcxcXNOYklWMWlSUQ0163" -d 3

        5  /markComplete
                description: To mark a todo item to be completed, flag the status to C.
                method: POST
                body(JSON): [ID of todo list to delete]
                header: AUTHORIZATION=[access token value from login]

                SAMPLE:
                curl -X POST http://localhost:8080/todolist/markComplete -H "Content-Type: application/json" -H "AUTHORIZATION:ya29.A0AVA9y1udoO5P7tpWS914eCzeT3ocyemS0NEsXzuvrV2M-xcl-4v-tkMIJJa3eIaj54Ef5yaPElIhM54svPFSZWOWlXLw_3PsqTWXIPVMWbyw8K9OPvaGa3RcgCgjTAOZxxJDkePveNfUJ0ix6NIf9eN1R4O8YUNnWUtBVEFTQVRBU0ZRRTY1ZHI4ZWlHbEFiUldCVUcxcXNOYklWMWlSUQ0163" -d 2
