package dgroomes.simple_arithmetic;

import static java.lang.System.out;

/**
 * Please see the README for more information.
 */
public class SimpleArithmetic {
    public static void main(String[] args) {
        {
            int sum = 5 + 3;
            out.printf("""
                    Addition
                        int sum = 5 + 3
                        'sum' is equal to %d
                    %n""", sum);
        }

        {
            int difference = 5 - 3;
            out.printf("""
                    Subtraction
                        int difference = 5 - 3
                        'difference' is equal to %d
                    %n""", difference);
        }

        {
            int product = 5 * 3;
            out.printf("""
                    Multiplication
                        int product = 5 * 3
                        'product' is equal to %d
                    %n""", product);
        }

        {
            int quotient = 5 / 3;
            out.printf("""
                    Division
                        int quotient = 5 / 3
                        'quotient' is equal to %d
                    %n""", quotient);
        }

        {
            int remainder = 5 % 3;
            out.printf("""
                    Modulus
                        int remainder = 5 %% 3
                        'remainder' is equal to %d
                    %n""", remainder);
        }

        {
            int overflowSum = 2_147_483_647 + 1; // This is an overflow
            out.printf("""
                    Overflow
                        int overflowSum = 2_147_483_647 + 1
                        'overflowSum' is equal to %d
                    %n""", overflowSum);
        }
    }
}
