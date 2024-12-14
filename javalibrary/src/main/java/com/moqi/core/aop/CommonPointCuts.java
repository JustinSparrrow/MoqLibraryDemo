package com.moqi.core.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CommonPointCuts {

    @Pointcut("execution(public com.moqi.core.model.ReturnObject com.moqi.library..controller..*.*(..))")
    public void controllers() {

    }

    @Pointcut("execution(public * com.moqi.library..dao..*.*(..))")
    public void daos() {

    }

    @Pointcut("@annotation(com.moqi.core.aop.Audit)")
    public void auditAnnotation() {

    }
}
