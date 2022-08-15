package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AccountDAO {

    private String name;
    private String serviceCode;


    // add new method: findAccounts()
    public List<Account> findAccounts() {
        return new ArrayList<>(Arrays.asList(
                new Account("John", "Silver"),
                new Account("Madhu", "Platinum"),
                new Account("Luca", "Gold")
        ));
    }


    public void addAccount(Account theAccount, boolean vipFlag) {
        System.out.println(getClass() + " Doing My DB Work: Adding an account");
    }

    public boolean doWork() {
        System.out.println(getClass() + ": doWork()");
        return false;
    }

    public String getName() {
        System.out.println(getClass() + ": in getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + ": in setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + ": in getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + ": in setServiceCode()");
        this.serviceCode = serviceCode;
    }
}
