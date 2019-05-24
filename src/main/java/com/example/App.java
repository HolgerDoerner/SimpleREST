package com.example;

import static spark.Spark.*;

import java.sql.SQLException;

import com.example.bean.EmployeeBean;
import com.google.gson.Gson;

/**
 * Main application class
 */
public final class App {
    private static Gson gson = new Gson();
    private static RestService service;

    static {
        try {
            DatabaseUtil.initDatabase();

            // Replace this with yout own implementation of RestService.
            service = new MyRestService();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Application entry point
     * 
     * @param args The arguments of the program.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        
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
        post("/users",
            "application/json",
            (request, response) -> service.updateEmployee(gson.fromJson(request.body(), EmployeeBean.class)),
            gson::toJson);

        // set default content-type to json for all endpoints
        after((request, response) -> response.header("Content-type", "application/json"));
    }
}
