package com.git.QuadTreeImp;

import java.lang.Math;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class runtimeTest {

    public static void main(String[] args){

        // store number of leaf nodes & runtime for each insert
        int[][] results = new int[30][3];

        for (int i = 1; i <= 30; i++) {
            results[i - 1][0] = (int)Math.pow(2, i);
            results[i - 1][1] = repeatedTimeInsert(i, 1000000);
            results[i - 1][2] = repeatedTimeSearch(i, 1000000);
        }

        // output the results to a csv file
        toCsv(results, new String[]{"nodes", "insert", "search"},"runTimes.csv");

        /*
        for (int[] arr: results) {
            System.out.println(arr[0] + ": " + arr[1]);
        }
         */

    }

    public static int timeInsert(int n){
        //performs a worst-case insert for a single node

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
        //returns the worst-case average runtime for the insert method over k iterations

        int total = 0;
        for (int i = 0; i < k; i++) {
            total += timeInsert(n);
        }

        return total / k;
    }

    public static void toCsv(int[][] arr, String pathName){
        toCsv(arr, new String[]{""} , pathName);
    }

    public static void toCsv(int[][] arr, String[] header, String pathName){
        try (PrintWriter writer = new PrintWriter(new File(pathName))) {
            //write the csv header
            writer.write(String.join(",", header) + "\n");

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

    public static int timeSearch(int n){
        //performs a worst-case search for a single node

        // instantiate a tree with 2^^n nodes
        QuadTree<Boolean> tree = new QuadTree<>((int)Math.pow(2, n), false);
        tree.insert(new int[]{0 ,0}, true,1);

        // perform & time a worst-case search into the tree
        long start = System.nanoTime();
        tree.search(new int[]{0, 0});
        long end = System.nanoTime();

        //System.out.println(tree);

        return (int)(end - start);
    }

    public static int repeatedTimeSearch(int n, int k){
        //returns the worst-case average runtime for the search method over k iterations

        int total = 0;
        for (int i = 0; i < k; i++) {
            total += timeSearch(n);
        }

        return total / k;
    }

}
