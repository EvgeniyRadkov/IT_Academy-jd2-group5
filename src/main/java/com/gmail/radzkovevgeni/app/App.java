package com.gmail.radzkovevgeni.app;

import com.gmail.radzkovevgeni.app.services.RunTaskService;
import com.gmail.radzkovevgeni.app.services.impl.RunTaskOneServiceImpl;
import com.gmail.radzkovevgeni.app.services.impl.RunTaskThreeServiceImpl;
import com.gmail.radzkovevgeni.app.services.impl.RunTaskTwoServiceImpl;

public class App {
    public static void main(String[] args) {
        RunTaskService[] taskServices = {RunTaskOneServiceImpl.getInstance(),
                RunTaskTwoServiceImpl.getInstance(),
                RunTaskThreeServiceImpl.getInstance()
        };

        for (RunTaskService taskService : taskServices) {
            taskService.runTask();
        }
    }
}
