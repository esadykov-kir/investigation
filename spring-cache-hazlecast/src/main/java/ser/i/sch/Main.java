package ser.i.sch;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author esadykov
 * @since 15.04.2016 11:34
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(CacheConfiguration.class);
        context.refresh();

        context.scan("ser.i.sch");

        TestService test = context.getBean(TestService.class);

        test.get(1);
        test.get(1);
        test.get(2);
        test.clear(1);
        test.get(1);
        test.get(2);
        test.get(2);
        test.clear(2);
        test.get(2);

        context.destroy();
    }
}
