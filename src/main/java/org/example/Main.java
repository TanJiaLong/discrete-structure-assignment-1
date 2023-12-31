package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 2: Ask the user to input the relation in the specified format
        System.out.print(
                "Enter relation R in the form of R = {(a1,b1), (a2,b2), ..., (an,bn)}: "
        );
        String inputRelation = scanner.next() + scanner.nextLine();

        // Step 3: Generate the matrix
        String[][] matrix = generateMatrix(inputRelation);

        // Step 4: Display the matrix
        if (matrix != null) {
            System.out.println("Matrix representation of the relation R:");
            printMatrix(matrix);
        } else {
            System.out.println("Invalid input. Please provide a valid relation in the specified format.");
        }
    }

    //Accept alphabet and number
    // Function to generate the matrix from the input relation
    private static String[][] generateMatrix(String inputRelation) {
        String[] pairs = convertToArray(inputRelation);
        Set<String> domainSet = new HashSet<>();
        Set<String> rangeSet = new HashSet<>();

        for (String pair : pairs) {
            String[] elements = pair.split(",");
            domainSet.add(elements[0]);
            rangeSet.add(elements[1]);
        }
        String[] domainArray = domainSet.toArray(new String[0]);
        String[] rangeArray = rangeSet.toArray(new String[0]);

        // Create the matrix
        String[][] matrix = new String[domainArray.length][rangeArray.length];

        // Fill the matrix based on the input relation
        for (String pair : pairs) {
            String[] elements = pair.split(",");
            String a = elements[0];
            String b = elements[1];
            int rowIndex = Arrays.asList(domainArray).indexOf(a);
            int colIndex = Arrays.asList(rangeArray).indexOf(b);
            matrix[rowIndex][colIndex] = "1";  // Mark the position in the matrix
        }

        if (domainArray.length == 0 || rangeArray.length == 0) {
            return null;
        } else {
            return matrix;
        }
    }


    private static String[] convertToArray(String inputRelation) {
        // Extract pairs from the input relation
        String[] pairs = inputRelation.replaceAll("[{} ]", "").split("\\)");

        for (int i = 0; i < pairs.length; i++) {
            String pair = pairs[i];
            if (pair.startsWith(",")) {
                pairs[i] = pair.substring(2);
            }else if(pair.startsWith("(")){
                pairs[i] = pair.substring(1);
            }
        }

        return pairs;
    }


    // Function to print the matrix
    private static void printMatrix(String[][] matrix) {
        for (String[] row : matrix) {
            for (String value : row) {
                System.out.print(value != null ? value : "0");
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}

// Accept number only
//    // Function to generate the matrix from the input relation
//    private static int[][] generateMatrix(String inputRelation) {
//
//        String[] pairs = convertToArray(inputRelation);
//
//        int maxDomain = 0;
//        int maxRange = 0;
//
//        for (String s : pairs) {
//            String[] elements = s.split(",");
//            int curDomain = Integer.parseInt(elements[0]);
//            int curRange = Integer.parseInt(elements[1]);
//
//            maxDomain = Math.max(maxDomain, curDomain);
//            maxRange = Math.max(maxRange, curRange);
//        }
//
//        // Create the matrix
//        int[][] matrix = new int[maxDomain][maxRange];
//
//        // Fill the matrix based on the input relation
//        for (String pair : pairs) {
//            String[] elements = pair.split(",");
//            int a = Integer.parseInt(elements[0]) - 1;  // Adjust index to start from 0
//            int b = Integer.parseInt(elements[1]) - 1;  // Adjust index to start from 0
//            matrix[a][b] = 1;  // Mark the position in the matrix
//        }
//
//        if(maxDomain == 0){
//            return null;
//        }else{
//            return matrix;
//        }
//    }
