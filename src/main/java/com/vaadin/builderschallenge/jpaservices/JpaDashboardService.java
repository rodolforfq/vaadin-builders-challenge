package com.vaadin.builderschallenge.jpaservices;

import com.vaadin.builderschallenge.services.DashboardService;
import org.springframework.stereotype.Service;

@Service
public class JpaDashboardService implements DashboardService {
    @Override
    public String sayHello() {
        return "Hello";
    }
}
