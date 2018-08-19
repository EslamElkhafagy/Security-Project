/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import java.util.Arrays;

/**
 *
 * @author AhmedEldeeb
 */
public class Transposition {

    int rowLength, colLength;
    static char[][] matrix;

    public Transposition(String PT, String Key) {
        //colLength
        colLength = Key.length();//7
        PT = removeSpace(PT);
        rowLength = (int) Math.ceil((double) PT.length() / colLength); //3
        matrix = new char[rowLength + 1][colLength];
        int restWord = ((rowLength + 1) * colLength) - (PT.length() + Key.length());

        //System.out.println(restWord);
        for (int i = 0; i < restWord; i++) {
            PT += (char) (97 + i);
        }
        matrix = initializeArr(PT, Key);
        matrix=arrangeArr(matrix, Key);
          
        System.out.println(printEncryptedText());
       
        

    }

    /*-------- Decrpypted function ---------*/
    public static String printDecryptedText(String pt, String key) {
        String dec = "";

        int rowLength=pt.length() / key.length() +1;
        char[] keys = key.toCharArray();
        Arrays.sort(keys);
        int currentIndex=0;
        
        char matrix[][] = new char[rowLength][key.length()];

        for (int i = 0; i < key.length(); i++) {
            matrix[0][i] = keys[i];
        }
        /*------ Build initial Array*/
        for(int i=0;i<key.length();i++)
            for(int row=1;row<rowLength;row++)
                matrix[row][i]=pt.charAt(currentIndex++);
        
        
        currentIndex=0;
        /*------ Arrange the array --------*/
        char newMatrix[][] = new char[rowLength][key.length()];
        
        for (int i = 0; i < keys.length; i++) {
            char ch = key.charAt(i);

            for (int col = 0; col < newMatrix[0].length; col++) {
                if (matrix[0][col] == ch) {
                    System.out.println(currentIndex);
                    for (int row = 0; row < newMatrix.length; row++) {
                        newMatrix[row][currentIndex] = matrix[row][col];
                    }
                    currentIndex++;
                    break;
                }

            }
        }
            
        
        
        
        System.out.println(Arrays.deepToString(newMatrix));
        
        for(int row=1;row<rowLength;row++)
            for (int col = 0; col < newMatrix[0].length; col++) {
                dec+=newMatrix[row][col];
            }
         
        return dec;
    }
    
    /*------------ Encryption --------------*/

    /**
     *
     * @param matrix
     * @return Encrypted PT
     */
    public static String printEncryptedText() {
        
        String encText = "";

        for (int col = 0; col < matrix[0].length; col++) {
            for (int row = 1; row < matrix.length; row++) {
                encText += matrix[row][col];
            }
        }

        return encText;
    }

    /**
     *
     * @param matrix
     * @param Key
     * @return 2-D char array with sorted Key&PT
     */
    public static char[][] arrangeArr(char matrix[][], String Key) {
        char[] Keys = Key.toCharArray();
        char[][] newMatrix = new char[matrix.length][matrix[0].length];
        Arrays.sort(Keys);
        int currentIndex = 0;

        for (int i = 0; i < Keys.length; i++) {
            char ch = Keys[i];
            for (int col = 0; col < newMatrix[0].length; col++) {
                if (matrix[0][col] == ch) {
                    // System.out.println(currentIndex);
                    for (int row = 0; row < newMatrix.length; row++) {
                        newMatrix[row][currentIndex] = matrix[row][col];
                    }
                    currentIndex++;
                    break;
                }

            }
        }
        System.out.println(Arrays.deepToString(newMatrix));
        return newMatrix;
    }

    /**
     *
     * @param matrix
     * @param PT
     * @param Key
     * @return 2-D char array with Key&PT
     */
    public static char[][] initializeArr(String PT, String Key) {
        int index = 0;
        matrix[0] = Key.toCharArray();

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = PT.charAt(index++);
            }
        }

        return matrix;
    }

    /**
     *
     * @param PT => plain Text
     * @return PT without spaces
     */
    public static String removeSpace(String PT) {
        String newPT = "";

        String[] k = PT.split(" +");
        for (String s : k) {
            newPT += s;
        }
        return newPT;

    }

    public static void main(String[] args) {
        String PT = "we need more snow now";
        String key = "section";

        Transposition trans = new Transposition(PT, key);

        System.out.println(Transposition.printDecryptedText("NEWEROENBMWDDOCWONESA", "section"));
        //System.out.println(printEncryptedText("NEWEROENBMWDDOCWONESA", "section"));
//        System.out.println(Arrays.deepToString(trans.matrix));
//        System.out.println(trans.colLength + " " + trans.rowLength);
//        System.out.println((int) 'a');
    }

}
