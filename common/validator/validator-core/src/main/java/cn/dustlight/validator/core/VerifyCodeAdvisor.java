package cn.dustlight.validator.core;

import cn.dustlight.validator.annotations.VerifyCode;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.BeanFactory;

public class VerifyCodeAdvisor extends AbstractPointcutAdvisor {

    private VerifyCodeInterceptor interceptor;

    public VerifyCodeAdvisor(BeanFactory factory) {
        this.interceptor = new VerifyCodeInterceptor(factory);

    }

    public Pointcut getPointcut() {
        return new AnnotationMatchingPointcut((Class) null, VerifyCode.class, true);
    }

    public Advice getAdvice() {
        return interceptor;
    }

    @Override
    public int getOrder() {
        return interceptor.getOrder();
    }

    @Override
    public void setOrder(int order) {
        interceptor.setOrder(order);
    }
}