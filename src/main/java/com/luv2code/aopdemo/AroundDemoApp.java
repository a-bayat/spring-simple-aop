package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AroundDemoApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        // get the bean from the spring container
        TrafficFortuneService fortuneService =
                context.getBean("trafficFortuneService", TrafficFortuneService.class);

        System.out.println("\nMain Program: AroundDemoApp");

        System.out.println("Calling getFortune");

        String data = fortuneService.getFortune();

        System.out.println("\nMy fortune is: " + data);

        System.out.println("Finished");

        context.close();
    }
}
