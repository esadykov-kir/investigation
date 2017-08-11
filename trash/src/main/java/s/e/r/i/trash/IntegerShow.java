package s.e.r.i.trash;

/**
 * @author esadykov
 * @since 17.07.2017
 */
public class IntegerShow {
    public static void main(String[] args) {
        System.out.println(63); //dec
        System.out.println(77); //dec
        System.out.println(077); //oct
        System.out.println(0x77); //hex
        System.out.println(0x3F); //hex
        System.out.println(0b111111); //bin

        System.out.println(System.identityHashCode(new Integer(63)));
        System.out.println(System.identityHashCode(new Integer(63)));
        System.out.println(System.identityHashCode(Integer.valueOf(63)));
        System.out.println(System.identityHashCode(Integer.valueOf(63)));
        System.out.println(System.identityHashCode(Integer.valueOf("63")));
        System.out.println(System.identityHashCode(Integer.valueOf("63")));
        System.out.println(System.identityHashCode(63));
        System.out.println(System.identityHashCode(63));
        System.out.println(System.identityHashCode(Integer.parseInt("63")));
        System.out.println(System.identityHashCode(Integer.parseInt("63")));
        System.out.println(System.identityHashCode(new Integer(63000)));
        System.out.println(System.identityHashCode(new Integer(63000)));
        System.out.println(System.identityHashCode(Integer.valueOf(63000)));
        System.out.println(System.identityHashCode(Integer.valueOf(63000)));
        System.out.println(System.identityHashCode(63000));
        System.out.println(System.identityHashCode(63000));
        System.out.println(System.identityHashCode(Integer.parseInt("63000")));
        System.out.println(System.identityHashCode(Integer.parseInt("63000")));

    }
}

