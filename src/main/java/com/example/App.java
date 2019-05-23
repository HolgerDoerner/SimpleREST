package com.example;

import static spark.Spark.after;
import static spark.Spark.get;

import com.example.bean.UserBean;
import com.google.gson.Gson;

/**
 * Main application class
 */
public final class App {
    private static Gson gson = new Gson();
    private static UserService service = new MockUserService();

    /**
     * Application entry point
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        
        // returns a list of all users
        get("/users", (request, response) -> service.getUsers(), gson::toJson);

        // returns a single user by its ID or an empty new one if ID is not found
        get("/users/:id",
            (request, response) -> service.getUserById(request.params("id")).orElse(new UserBean()),
            gson::toJson);

        // set default content-type to json for all endpoints
        after((request, response) -> response.header("Content-type", "application/json"));
    }
}

class Test {
    public int id = 1;
    public String name = "Hans";
    public String last = "Wurst";
}
