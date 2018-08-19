/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author AhmedEldeeb
 */
public class RSA {

    static int p, n, q, z, e, d;

    public RSA(int p, int q) {

        this.p = p;
        this.q = q;

        System.out.println(p + " " + q);

        claculate();

    }

    /* calcualte the final values before encryption */
    public static void claculate() {

        if (!(checkPrime(p) && checkPrime(q))) {
            System.out.println("P and Q must be Prime");
            System.exit(0);
        }
        n = p * q;
        z = (p - 1) * (q - 1);
        e = 0;

        for (int i = 2; i < z; i++) {
            if (GCD(i, z) == 1) {
                e = i;
                break;
            }
        }

        BigInteger b1 = new BigInteger(String.valueOf(e));
        BigInteger b2 = new BigInteger(String.valueOf(z));

        d = Integer.valueOf(b1.modInverse(b2).toString());

    }

    public static String encrypt(String text) {

//        p = 5;
//        q = 11;
//        n = 55;
//        z = 40;
        e = 9;
        //      d = 9;

        System.out.println(n + " " + z + " " + e + " " + d);
        text = text.toLowerCase();
        int ch;
        String enc = "";
        for (int i = 0; i < text.length(); i++) {
            ch = text.charAt(i) - 97;
            enc += (char) ((Math.pow(ch, e) % n) + 97);
            // System.out.println((Math.pow(ch, e) % n) );

        }
        return enc;
    }

    public static int GCD(int a, int b) {
        if (a == 0 || b == 0) {
            return a + b;
        }
        return GCD(b, a % b);
    }

//    public static int generateRand() {
//        Random rand = new Random();
//        return rand.nextInt(100) + 1;
//    }
    public static Boolean checkPrime(int num) {
        if (num == 0 || num == 1) {
            return false;
        } else {
            for (int i = 2; i <= Math.sqrt((double) num); i++) {
                if (num % i == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

        RSA rsa = new RSA(2, 11);

        System.out.println(RSA.encrypt("izÄè"));
      
    }

}
