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
        while(!inputRelation.startsWith("{(") || !inputRelation.endsWith(")}") || !inputRelation.contains(",") || !inputRelation.matches(".*\\([^,]+,[^,]+\\).*")){
            System.out.print(
                    "Invalid input. Please provide a valid relation in the specified format.\n" +
                    "Enter relation R in the form of R = {(a1,b1), (a2,b2), ..., (an,bn)}: "
            );
            inputRelation = scanner.next() + scanner.nextLine();
        }

        // Step 3: Generate the matrix
        String[] domainArray = getDomainArray(inputRelation);
        String[] rangeArray =  getRangeArray(inputRelation);
        String[][] matrix = generateMatrix(domainArray, rangeArray, inputRelation);

        // Step 4: Display the matrix
        if (matrix != null) {
            System.out.println("\nMatrix representation of the relation R:");
            printMatrix(domainArray, rangeArray, matrix);
        } else {
            System.out.println("Invalid input. Please provide a valid relation in the specified format.");
        }
    }

    public static String[] getDomainArray(String inputRelation){
        String[] pairs = convertToArray(inputRelation);
        Set<String> domainSet = new HashSet<>();

        for (String pair : pairs) {
            String[] elements = pair.split(",");
            domainSet.add(elements[0]);
        }
        String[] domainArray = domainSet.toArray(new String[0]);
        return domainArray;
    }
    public static String[] getRangeArray(String inputRelation){
        String[] pairs = convertToArray(inputRelation);
        Set<String> rangeSet = new HashSet<>();

        for (String pair : pairs) {
            String[] elements = pair.split(",");
            rangeSet.add(elements[1]);
        }
        String[] rangeArray = rangeSet.toArray(new String[0]);
        return rangeArray;
    }
    private static String[][] generateMatrix(String[] domainArray, String[] rangeArray, String inputRelation) {
        String[] pairs = convertToArray(inputRelation);

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
    private static void printMatrix(String[] domainArray, String[] rangeArray, String[][] matrix) {
        //First (Column) Line
        System.out.printf("%-3s|", "");
        for (int i = 0; i < rangeArray.length; i++) {
            System.out.printf("%-3s|", rangeArray[i]);
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            String[] row = matrix[i];
            System.out.printf("%-3s|", domainArray[i]);
            for (int j = 0; j < row.length; j++) {
                String value = row[j];
                System.out.printf("%-3s|", value != null ? value : "0");
            }
            System.out.println();
        }
    }
}