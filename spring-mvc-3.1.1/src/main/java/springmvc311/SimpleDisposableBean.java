package springmvc311;

/**
 * Created by esadykov on 13.11.2014.
 */
public class SimpleDisposableBean {

    public SimpleDisposableBean() {
        System.out.println("init");
    }

    public void destroy() {
        System.out.println("destroy");
    }
}
