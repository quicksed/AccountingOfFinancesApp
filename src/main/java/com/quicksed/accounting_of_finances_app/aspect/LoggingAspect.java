package com.quicksed.accounting_of_finances_app.aspect;

import com.quicksed.accounting_of_finances_app.dto.user.UserDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(public * *(..)) && within(@org.springframework.web.bind.annotation.RestController *) " +
            "&& within(@com.quicksed.accounting_of_finances_app.annotation.Loggable *)")
    public void loggable(JoinPoint joinPoint){
        System.out.println("Executing method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(public * *(..)) " +
            "&& within(@org.springframework.web.bind.annotation.RestController *)",
            returning="user")
    public void afterReturningUserLogAdvice(Object user) {
        if (user instanceof UserDto) {
            System.out.println("Returned User with id: " + ((UserDto) user).getId() +
                    " and with Email: " + ((UserDto) user).getEmail());
        }
    }

    @AfterThrowing("execution(public * *(..)) " +
            "&& within(@org.springframework.web.bind.annotation.RestController *)")
    public void afterThrowingLogAdvice(JoinPoint joinPoint) {
        System.out.println("Method: " + joinPoint.getSignature().getName() + " threw exception");
    }
}
