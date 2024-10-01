package com.example.todoapi.hw;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@RequiredArgsConstructor
@Component
public class MyController {
    private final MyService myService;

    public void controllerMethod(){
        System.out.println("controller");
        myService.serviceMethod();
    }
}
