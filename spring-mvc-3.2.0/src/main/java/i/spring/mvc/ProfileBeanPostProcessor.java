package i.spring.mvc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.StopWatch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ProfileBeanPostProcessor implements BeanPostProcessor
{
    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException
    {
        System.out.println("BeforeInitialization " + dateFormat.format(System.currentTimeMillis()));
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException
    {
        System.out.println("Bean '" + beanName + "' created class " + bean.toString());
        return bean;
    }
}