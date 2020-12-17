package com.git.QuadTreeImp;

import java.lang.Math;
import java.lang.reflect.Method;


public class runtimeTest {

    public static void main(String args[]){

        for (int i = 1; i <= 20; i++) {
            System.out.println(i);
            System.out.println(timeFunc(i));
        }


    }

    public static int timeFunc(int n){

        // instantiate a tree with 2^^n nodes
        QuadTree<Boolean> tree = new QuadTree<Boolean>((int)Math.pow(2, n), false);

        // perform & time a worst-case insert into the tree
        long start = System.nanoTime();
        tree.insert(new int[]{n - 1 ,n - 1}, true,1);
        long end = System.nanoTime();

        System.out.println(tree);

        return (int)(end - start);
    }

}
