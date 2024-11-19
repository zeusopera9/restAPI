### Task Management RESTful API

Port: 8080
Entry point to application: http://localhost:8080/api/v1

Setup:
1. Clone the repository and setup the project using IntelliJ Idea.
2. Choose "Load Maven Scripts" when the suggestion appears on you screen.
3. To start the server, run the "RestApiApplication.java" file.
4. To execute the tests written for this API, run the "TaskServiceImplementationTests.java" file.
5. Import the "Task REST API.postman_collection" JSON file in Postman or equivalent testing application.
6. Test the endpoints by executing the requests imported as part of this collection.
  
API Documentation:
1. GET all Tasks: http://localhost:8080/api/v1/tasks
2. GET Task by id: http://localhost:8080/api/v1/tasks/{id}
3. POST new Task: http://localhost:8080/api/v1/tasks  
  (Sample JSON Object: {
      "title": "Task 1",
      "description": "Description for the task",
      "due_date": "2024-11-25"
  })
4. PUT Update existing Task: http://localhost:8080/api/v1/tasks/{id}
   (Sample JSON Object: {
    "title": "Task 1.0",
    "description": "Description for the task 2.0",
    "due_date": "2024-09-09"
   })
5. DELETE existing Task: http://localhost:8080/api/v1/tasks/{id}
6. PATCH Mark Task as completed: http://localhost:8080/api/v1/tasks/{id}
