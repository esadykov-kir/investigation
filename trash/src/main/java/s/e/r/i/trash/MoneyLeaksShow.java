package s.e.r.i.trash;

import java.math.BigDecimal;

/**
 * @author esadykov
 * @since 10.08.2017
 */
public class MoneyLeaksShow {

    private static final int ITERATIONS = 1000000;

    public static void main(String[] args) {
        float myMoneyFloat = 0;
        float incrementFloat = 0.1f;
        System.out.printf("increment %1$.15f\n", incrementFloat);
        for (int i = 0; i < ITERATIONS; i++) {
            myMoneyFloat = myMoneyFloat + incrementFloat;
        }
        System.out.printf("myMoneyFloat should by %1$.15f but i got %2$.15f\n", ITERATIONS*incrementFloat, myMoneyFloat);

        double myMoneyDouble = 0;
        double incrementDouble = 0.1;
        System.out.printf("increment %1$.30f\n", incrementDouble);
        for (int i = 0; i < ITERATIONS; i++) {
            myMoneyDouble = myMoneyDouble + incrementDouble;
        }
        System.out.printf("myMoneyDouble should by %1$.15f but i got %2$.15f\n", ITERATIONS*incrementDouble, myMoneyDouble);

        BigDecimal myMoneyBD = BigDecimal.ZERO;
        BigDecimal incrementBD = BigDecimal.valueOf(0.1);
        System.out.printf("increment %1$.15f\n", incrementBD);
        for (int i = 0; i < ITERATIONS; i++) {
            myMoneyBD = myMoneyBD.add(incrementBD);
        }
        System.out.printf("myMoneyBD should by %1$.15f but i got %2$.15f\n", incrementBD.multiply(BigDecimal.valueOf(ITERATIONS)), myMoneyBD);

    }
}
