import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fibonacci {
    public static BigInteger tailRecursionFibonacci(int n, BigInteger curr, BigInteger next){
        if(n <= 1)
            return next;

        return tailRecursionFibonacci(n-1, next, curr.add(next));
    }

    public static BigInteger dpFibonacci(int n) {
        ArrayList<BigInteger> memo = new ArrayList<>(n + 1);
        memo.add(BigInteger.ZERO);
        memo.add(BigInteger.ONE);

        for (int i = 2; i <= n; i++) {
            memo.add(memo.get(i - 1).add(memo.get(i - 2)));
        }
        return memo.get(n);
    }

    public static BigInteger[][] multiply2x2Matrices(BigInteger[][] a, BigInteger[][] b){
        BigInteger c00 = a[0][0].multiply(b[0][0]).add(a[0][1].multiply(b[1][0]));
        BigInteger c01 = a[0][0].multiply(b[0][1]).add(a[0][1].multiply(b[1][1]));
        BigInteger c10 = a[1][0].multiply(b[0][0]).add(a[1][1].multiply(b[1][0]));
        BigInteger c11 = a[1][0].multiply(b[0][1]).add(a[1][1].multiply(b[1][1]));
        return new BigInteger[][] {{c00, c01},{c10, c11}};
    }

    public static BigInteger[][] matPower(BigInteger[][] mat, BigInteger exponent) {
        //If the exponent is zero return the identity matrix
        if (exponent.equals(BigInteger.ZERO))
            return new BigInteger[][] {{BigInteger.ONE, BigInteger.ZERO}, {BigInteger.ZERO, BigInteger.ONE}};

        //Recurse and get the matrix with power of exponent / 2
        BigInteger[][] tmp = matPower(mat, exponent.divide(BigInteger.TWO));

        //Multiply tmp by itself
        BigInteger[][] result = multiply2x2Matrices(tmp, tmp);

        //If the exponent is odd multiply by the matrix to increase the power with one
        if (exponent.divideAndRemainder(BigInteger.TWO)[1].equals(BigInteger.ONE))
            result = multiply2x2Matrices(result, mat);

        return result;
    }

    public static BigInteger getNthFibonacciNumber(BigInteger n) {
        BigInteger[][] b = new BigInteger[][] {{BigInteger.ZERO, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ONE}};
        return matPower(b, n)[0][1]; // Replace the question marks by correct values.
    }

    public static void main(String[] args) {
        BigInteger tailRecursionSol = tailRecursionFibonacci(10000, BigInteger.ZERO, BigInteger.ONE);
        BigInteger dpSol = dpFibonacci(10000);
        BigInteger matPowerSol = getNthFibonacciNumber(BigInteger.valueOf(10000));


        System.out.println(
                "The solutions are equivalent? " +
                (tailRecursionSol.equals(dpSol) && dpSol.equals(matPowerSol) ? "YES" : "NO")
        );

        System.out.println("Solution is: ");
        System.out.println(tailRecursionSol);
    }
}