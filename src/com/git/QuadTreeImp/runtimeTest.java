package com.git.QuadTreeImp;

import java.lang.Math;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class runtimeTest {

    public static void main(String args[]){

        // store number of leaf nodes & runtime for each insert
        int[][] results = new int[30][2];

        for (int i = 1; i <= 30; i++) {
            results[i - 1][0] = (int)Math.pow(2, i);
            results[i - 1][1] = (int)repeatedTimeInsert(i, 10000);
        }

        // output the results to a csv file
        toCsv(results, "insertTest.csv");

        /*
        for (int[] arr: results) {
            System.out.println(arr[0] + ": " + arr[1]);
        }
         */

    }

    public static int timeInsert(int n){

        // instantiate a tree with 2^^n nodes
        QuadTree<Boolean> tree = new QuadTree<>((int)Math.pow(2, n), false);

        // perform & time a worst-case insert into the tree
        long start = System.nanoTime();
        tree.insert(new int[]{0 ,0}, true,1);
        long end = System.nanoTime();

        //System.out.println(tree);

        return (int)(end - start);
    }

    public static int repeatedTimeInsert(int n, int k){

        int total = 0;
        for (int i = 0; i < k; i++) {
            total += timeInsert(n);
        }

        return total / k;
    }

    public static void toCsv(int[][] arr, String pathName){
        try (PrintWriter writer = new PrintWriter(new File(pathName))) {
            for (int[] row: arr) {

                // build a string to represent each row in the csv
                String rowString = "";
                for (int value: row) {
                    rowString = rowString + value + ",";
                }
                // remove the last comma from each row
                rowString = rowString.substring(0, rowString.length() - 1);
                rowString += "\n";
                writer.write(rowString);
            }
        } catch (FileNotFoundException err) {
            System.out.println(err.getMessage());
        }
    }

}
