package com.gmail.radzkovevgeni.app.service.impl;

import com.gmail.radzkovevgeni.app.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskServiceImpl implements TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    private static TaskService instance;

    private TaskServiceImpl(){
    }

    public TaskService getInstance(){
        if(instance == null){
            instance = new TaskServiceImpl();
        }
        return instance;
    }

    @Override
    public void runTask() {
        
    }
}
