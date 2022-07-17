# todolist

README
1.1. instruction for running the app<br/>
Run command `docker-compose up` to start the application.<br/>
Run command `docker-compose stop` to start the application.

1.2. Instruction for testing the app

1.3. Instruction for building the app
Run command `docker-compose build` to build the application in docker.

1.4. interface documentation
Access to http://localhost:8080/login to login using gmail account and get the access token before calling the following service.

    API services available to call:
        1   /verifyToken
                description: To verify if the token is valid.
                method: POST
                body(JSON): [access token value from login]

                SAMPLE:
                curl -X POST http://localhost:8080/verifyToken -H "Content-Type: application/json" --data "ya29.A0AVA9y1udoO5P7tpWS914eCzeT3ocyemS0NEsXzuvrV2M-xcl-4v-tkMIJJa3eIaj54Ef5yaPElIhM54svPFSZWOWlXLw_3PsqTWXIPVMWbyw8K9OPvaGa3RcgCgjTAOZxxJDkePveNfUJ0ix6NIf9eN1R4O8YUNnWUtBVEFTQVRBU0ZRRTY1ZHI4ZWlHbEFiUldCVUcxcXNOYklWMWlSUQ0163"

        2  /list
                description: To list all the todo list as per user login.
                method: GET
                header: AUTHORIZATION=[access token value from login]

                SAMPLE:
                curl -X GET http://localhost:8080/list -H "Content-Type: application/json" -H "AUTHORIZATION:ya29.A0AVA9y1udoO5P7tpWS914eCzeT3ocyemS0NEsXzuvrV2M-xcl-4v-tkMIJJa3eIaj54Ef5yaPElIhM54svPFSZWOWlXLw_3PsqTWXIPVMWbyw8K9OPvaGa3RcgCgjTAOZxxJDkePveNfUJ0ix6NIf9eN1R4O8YUNnWUtBVEFTQVRBU0ZRRTY1ZHI4ZWlHbEFiUldCVUcxcXNOYklWMWlSUQ0163"

        3  /add
                description: To add a new todo item.
                method: POST
                body(JSON): "TO DO LIST Description"
                header: AUTHORIZATION=[access token value from login]

                SAMPLE:
                curl -X POST http://localhost:8080/add -H "Content-Type: application/json" -H "Content-Type: application/json" -H "AUTHORIZATION:ya29.A0AVA9y1udoO5P7tpWS914eCzeT3ocyemS0NEsXzuvrV2M-xcl-4v-tkMIJJa3eIaj54Ef5yaPElIhM54svPFSZWOWlXLw_3PsqTWXIPVMWbyw8K9OPvaGa3RcgCgjTAOZxxJDkePveNfUJ0ix6NIf9eN1R4O8YUNnWUtBVEFTQVRBU0ZRRTY1ZHI4ZWlHbEFiUldCVUcxcXNOYklWMWlSUQ0163" --data "Buy vegetables"

        4  /delete
                description: To delete a todo item.
                method: POST
                body(JSON): [ID of todo list to delete]
                header: AUTHORIZATION=[access token value from login]

                SAMPLE:
                curl -X POST http://localhost:8080/delete -H "Content-Type: application/json" -H "AUTHORIZATION:ya29.A0AVA9y1udoO5P7tpWS914eCzeT3ocyemS0NEsXzuvrV2M-xcl-4v-tkMIJJa3eIaj54Ef5yaPElIhM54svPFSZWOWlXLw_3PsqTWXIPVMWbyw8K9OPvaGa3RcgCgjTAOZxxJDkePveNfUJ0ix6NIf9eN1R4O8YUNnWUtBVEFTQVRBU0ZRRTY1ZHI4ZWlHbEFiUldCVUcxcXNOYklWMWlSUQ0163" --data 3

        5  /markComplete
                description: To mark a todo item to be completed, flag the status to C.
                method: POST
                body(JSON): [ID of todo list to delete]
                header: AUTHORIZATION=[access token value from login]

                SAMPLE:
                curl -X POST http://localhost:8080/markComplete -H "Content-Type: application/json" -H "AUTHORIZATION:ya29.A0AVA9y1udoO5P7tpWS914eCzeT3ocyemS0NEsXzuvrV2M-xcl-4v-tkMIJJa3eIaj54Ef5yaPElIhM54svPFSZWOWlXLw_3PsqTWXIPVMWbyw8K9OPvaGa3RcgCgjTAOZxxJDkePveNfUJ0ix6NIf9eN1R4O8YUNnWUtBVEFTQVRBU0ZRRTY1ZHI4ZWlHbEFiUldCVUcxcXNOYklWMWlSUQ0163" --data 2
