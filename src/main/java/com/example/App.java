package com.example;

import static spark.Spark.*;

import com.google.gson.Gson;

/**
 * Main application class
 */
public final class App {
    private static Gson gson = new Gson();
    private static UserService service = new MockEmployeeService();

    /**
     * Application entry point
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        
        // returns a list of all employees
        get("/users", (request, response) -> service.getEmployees(), gson::toJson);

        // returns a single employee by its ID or an empty new one if ID is not found
        get("/users/:id",
            (request, response) -> service.getEmployeeById(Integer.parseInt(request.params(":id"))),
            gson::toJson);

        // delete a employee from database
        delete("/users/:id",
            (request, response) -> service.deleteEmployee(Integer.parseInt(request.params(":id"))),
            gson::toJson);
        
        // update an employee
        // post("/users/:employee", (request, response) -> service.updateEmployee(request.params(":employee")));

        // set default content-type to json for all endpoints
        after((request, response) -> response.header("Content-type", "application/json"));
    }
}
