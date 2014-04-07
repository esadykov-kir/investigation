package i.scripting;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main
{

    public static void main(String[] args)
    {
        ScriptEngineManager m = new ScriptEngineManager();
        ScriptEngine engine = m.getEngineByName("JavaScript");

        // Теперь у нас есть экземпляр движка и мы можем выполнить JavaScript
        try {
            Main value = new Main();
            engine.put("name", value);
            System.out.println(engine.eval("print('Hello ' + name.s.m.get(\'mapkey1\') + '!');" +
                    "print('Hello ' + name.s.l.get(0) + '!');" +
                    "//name.s.s = 'teststring2'; \n" +
                    "print(name.s.s);"));
           // Object r = engine.eval("name.s.s");

            //System.out.println(value.s.s);
            //System.out.println(r);
        } catch (ScriptException ex) {
            ex.printStackTrace();
        }

    }

    public class SubMain {
        public Map m = new HashMap();
        {
            m.put("mapkey1","mapkey1");
            m.put("mapkey2","mapkey2");
        }
        public List l = new ArrayList();
        {
            l.add("listindex0");
            l.add("listindex1");
        }

        public String s;

        public void setS(String s)
        {
            this.s = s;
        }
    }

    public SubMain s = new SubMain();
}
