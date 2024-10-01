package com.example.todoapi;

import com.example.todoapi.hw.MyController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyControllerTest {
    @Autowired
    private MyController myController;

    @Test
    public void controllerTest() {
        myController.controllerMethod();
    }
}
