package com.git.QuadTreeImp;

import java.util.Arrays;

public class test {
	public static void main(String args[]) {
//		System.out.println("Hello World!!!");
////		int[][] array = new int[4][10];
//		
//		Node<Integer> a = new Node<Integer>();
//		Node<Integer> b = new Node<Integer>();
////		a.setNodeVal(new Pixel<Integer>(0));
//		Point l = a.getBoundTL();
//		Point r = b.getBoundBR();
//		System.out.println(l);
//		System.out.println(r);
//		System.out.println(l.compareTo(r));
//		l.setPoint(1, 1);
//		System.out.println(l.compareTo(r));
//		r.setPoint(1, 1);
//		System.out.println(l.compareTo(r));
//		r.setPoint(1, 0);
//		System.out.println(l.compareTo(r));
//		r.setPoint(0, 1);
//		System.out.println(l.compareTo(r));
//		r.setPoint(0, 2);
//		System.out.println(l.compareTo(r));
//		r.setPoint(2, 0);
//		System.out.println(l.compareTo(r));
//		System.out.println(a.isLeaf());
//		a.setChildBL(b);
//		System.out.println(a.isLeaf());
//		System.out.println(3/2);
//		int[][] array = new int[2][2];
//		int[] test = {1,2};
//		int[] test2 = {3,4};
//		array[0] = test;
//		array[1] = test2;
//		
//		System.out.println(array);
//		for (int i = 0; i < array[0].length; i++) {
//			System.out.println(Arrays.deepToString(array));
//		}
		
		QuadTree<Boolean> quadTree = new QuadTree<Boolean>(4);
		Point inP = new Point(2,1);
		Pixel<Boolean> inPix = new Pixel<Boolean>();
		quadTree.insert(inP, inPix);
		Point inP2 = new Point(3,3);
		quadTree.insert(inP2, inPix);
		Point inP3 = new Point(3,4);
		quadTree.insert(inP3, inPix);
		Point inP4 = new Point(4,3);
		quadTree.insert(inP4, inPix);
		
		
	}
}
