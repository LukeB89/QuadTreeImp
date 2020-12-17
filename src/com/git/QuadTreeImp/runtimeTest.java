package com.git.QuadTreeImp;

import java.lang.Math;

public class runtimeTest {

    public static void main(String args[]){

        for (int i = 1; i <= 20; i++) {
            System.out.println(i);
            System.out.println(repeatedTimeFunc(i, 1000));
        }

    }

    public static int timeFunc(int n){

        // instantiate a tree with 2^^n nodes
        QuadTree<Boolean> tree = new QuadTree<>((int)Math.pow(2, n), false);

        // perform & time a worst-case insert into the tree
        long start = System.nanoTime();
        tree.insert(new int[]{n - 1 ,n - 1}, true,1);
        long end = System.nanoTime();

        //System.out.println(tree);

        return (int)(end - start);
    }

    public static int repeatedTimeFunc(int n, int k){

        int total = 0;
        for (int i = 0; i < k; i++) {
            total += timeFunc(n);
        }

        return total / k;
    }

}
