package omer.solutions.testlvlconsulting.utils;

public class ApiRoutes {
    /**
     * User routes
     */

    public static final String ENDPOINT_AUTH = "/api/auth";
    public static final String ENDPOINT_LOGIN = "/login";
    public static final String ADD_USER = "/signup";
    public static final String UPDATE_PASSWORD = "/changePassword";
    public static final String UPDATE_USER = "/update";
    public static final String DELETE_USER = "/delete";
    public static final String LOGOUT_USER = "/logout";

    /**
     * Image routes
     */

    public static final String UPLOAD_IMAGE = "/uploadImage";
    public static final String GET_IMAGE = "/downloadImage";

    /**
     * Project routes
     */
    public static final String ENDPOINT_PROJECT = "/api/project";
    public static final String CREATE_PROJECT = "/create";
    public static final String DELETE_PROJECT = "/delete";
    public static final String UPDATE_PROJECT = "/update";
    public static final String GET_PROJECT = "/get";
    public static final String GET_ALL_PROJECTS = "/getall";
    public static final String GET_PROJECTS_BY_USER = "/getbyuser";

    /**
     * Task routes
     */
    public static final String ENDPOINT_TASK = "/api/task";
    public static final String CREATE_TASK = "/create";
    public static final String DELETE_TASK = "/delete";
    public static final String UPDATE_TASK = "/update";
    public static final String GET_TASK = "/get";
    public static final String GET_TASKS_BY_USER = "/getbyuser";
    public static final String GET_ALL_TASKS = "/getall";
    public static final String SEARCH_TASKS = "/search";

    /**
     * Test routes
     */
    public static final String ENDPOINT_TEST = "/api/test";
    public static final String ALL_ACCESS = "/all";
    public static final String USER_ACCESS = "/user";
    public static final String ADMIN_ACCESS = "/admin";
}
