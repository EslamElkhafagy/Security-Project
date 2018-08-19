/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import java.math.BigInteger;


/**
 *
 * @author AhmedEldeeb
 */
public class Affine {

    static int key;
    static String PT;
    static int DF;//m

    public Affine(int key, String PT,int DF) {
        this.key = key; //S
        this.PT = PT.toLowerCase();
        this.DF = DF;//PT.length(); //M

        //((M*P)+S)mod N
//        if(GCD(PT.length(), 26) ==1)
//            System.out.println(encrypt());
    }

    public static int GCD(int a, int b) {
        if (a == 0 || b == 0) {
            return a + b;
        }
        return GCD(b, a % b);
    }
// C=((M*P)+S)mod N

    public static String encrypt() {
        if (GCD(DF, 26) == 1) {
            String encText = "";

            for (int i = 0; i < PT.length(); i++) {
                int ch = PT.charAt(i) - 97;
                encText += (char) ((((DF * ch) + key) % 26) + 97);
            }
            return encText;
        }
        return "Can't Perform Encryption GCD(M, 26) have to be equal 1";
    }

    ///// Decryption ////////
    public static int modInverse() {
        BigInteger b1 = new BigInteger(DF + "");
        BigInteger b2 = new BigInteger(26 + "");

        return Integer.valueOf(b1.modInverse(b2).toString());
    }

    public static String decrypt() {

        if (GCD(DF, 26) == 1) {
            int m = modInverse();
            String dec = "";

            for (int i = 0; i < PT.length(); i++) {
                int ch = PT.charAt(i) - 97;
                int ch_Key = m * (ch - key);
                while (ch_Key < 0) {
                    ch_Key += 26;
                }

                dec += (char) ((((ch_Key) % 26)) + 97);
            }
            return dec;
        }
        return "Can't Perform Decryption";
    }

    public static void main(String[] args) {

        Affine affine = new Affine(1, "bwlnkUniknneUyzyrUlbinf",3);

        System.out.println(Affine.decrypt());
//ahmed eldeeb zizo malek
    }

}
