package com.quicksed.accounting_of_finances_app.aspect;

import com.quicksed.accounting_of_finances_app.annotation.Loggable;
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

    @Before("@annotation(loggable)")
    public void loggable(JoinPoint joinPoint, Loggable loggable) {
        System.out.println("Выполняется: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(public * *(..)) " +
            "&& within(@org.springframework.web.bind.annotation.RestController *)",
            returning="user")
    public void afterReturningUserLogAdvice(Object user) {

        if (user instanceof UserDto) {
            System.out.println("Был возвращён пользователь с id " + ((UserDto) user).getId());
        }
    }

    @AfterThrowing("execution(public * *(..)) " +
            "&& within(@org.springframework.web.bind.annotation.RestController *)")
    public void afterThrowingLogAdvice(JoinPoint joinPoint) {
        System.out.println("Метод " + joinPoint.getSignature().getName() + "выбросил исключение");
    }
}
