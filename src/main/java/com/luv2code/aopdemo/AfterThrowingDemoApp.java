package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterThrowingDemoApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        // call method to find the accounts
        List<Account> accounts = null;

        try {
            // add a boolean flag to simulate exception
            boolean tripWire = true;
            accounts = accountDAO.findAccounts(tripWire);
        } catch (Exception ex) {
            System.out.println("\n\nMain Program ... caught exception: " + ex);
        }

        System.out.println("\n\nMain Program: AfterThrowingDemoApp");
        System.out.println("-----");

        System.out.println(accounts);

        System.out.println("\n");

        context.close();
    }
}
