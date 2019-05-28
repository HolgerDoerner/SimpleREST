package com.example;

import static spark.Spark.*;

import java.sql.SQLException;

import com.example.bean.EmployeeBean;
import com.google.gson.Gson;

/**
 * Main application class
 */
public final class App {
    private static final RestService service;

    private static final Gson gson = new Gson();

    static {
        try {
            DatabaseProvider.initDatabase();
        } catch (SQLException e) {
            System.err.println("Error setting up database: " + e.getMessage());
            System.err.println("Exiting...");
            System.exit(1);
        }

        // Replace this with yout own implementation of RestService.
        service = new MyRestService();
    }

    /**
     * Application entry point
     * 
     * @param args The arguments of the program.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        staticFiles.location("/html");

        // returns a list of all employees
        get("/users", (request, response) -> service.getEmployees(), gson::toJson);

        // returns a single employee by its ID or an empty list if ID is not found.
        get("/users/:id",
            (request, response) -> service.getEmployeeById(Integer.parseInt(request.params(":id"))),
            gson::toJson);

        // delete a employee from database
        delete("/users/:id",
            (request, response) -> service.deleteEmployee(Integer.parseInt(request.params(":id"))),
            gson::toJson);
        
        // update an employee
        post("/users",
            "application/json",
            (request, response) -> service.updateEmployee(gson.fromJson(request.body(), EmployeeBean.class)),
            gson::toJson);

        // set default content-type to json for response from all endpoints.
        after((request, response) -> response.header("Content-type", "application/json"));
    }
}
