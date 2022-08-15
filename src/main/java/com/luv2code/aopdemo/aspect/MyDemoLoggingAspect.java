package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;


@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // print out method we are advising on
        String method = proceedingJoinPoint.getSignature().toShortString();
        logger.info("\n===========>>> Executing @Around on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // now, let's execute the method
        Object result = proceedingJoinPoint.proceed();

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        logger.info("\n===============> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }

    @After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        logger.info("\n===========>>> Executing @After (finally) on method: " + method);
    }

    @AfterThrowing(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc")
    public void afterThrowingFindAccountsAdvice(
            JoinPoint joinPoint,
            Throwable theExc) {
        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        logger.info("\n===========>>> Executing @AfterThrowing on method: " + method);

        // log the exception
        logger.info("\n===========>>> The exception is: " + theExc);

    }

    @AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(
            JoinPoint joinPoint, List<Account> result
    ) {
        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        logger.info("\n===========>>> Executing @AfterReturning on method: " + method);

        // print out the result of the method call
        logger.info("\n===========>>> result is: " + result);

        // let's post-construct the data ... let's modify if :-)

        // convert the account names to uppercase
        convertAccountNamesToUpperCase(result);

        logger.info("\n===========>>> result is: " + result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        for (Account account : result) {
            String theUpperName = account.getName().toUpperCase();
            account.setName(theUpperName);
        }
    }

    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        logger.info("\n========>>> Executing @Before advice on addAccount()");

        // Display the method signature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        logger.info("Method: " + signature);

        // Display method signature

        // get args
        Object[] args = joinPoint.getArgs();

        // loop through args
        for (Object arg : args) {
            logger.info(arg.toString());
        }
    }

}
