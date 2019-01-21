package com.sloopy.project.ddd.lets.data.source;

public class TasksRepository implements TasksDataSource {

    private static TasksRepository instance = null;

    private static final String BASE_URL = "http://13.125.93.83:8080/";

    public static TasksRepository getInstance() {
        if (instance == null) {
            instance = new TasksRepository();
        }
        return instance;
    }
}
