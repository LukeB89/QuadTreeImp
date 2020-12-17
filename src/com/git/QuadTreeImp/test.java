package com.git.QuadTreeImp;



public class test {
	public static void main(String args[]) {
		QuadTree<Boolean> quadTree = new QuadTree<Boolean>(8, false);
		
		quadTree.insert(new int[]{0,0}, true,2);
		quadTree.insert(new int[]{0,1}, true,1);
		quadTree.insert(new int[]{0,0}, false,1);
		quadTree.insert(new int[]{2,1}, true,1);
		quadTree.insert(new int[]{3,1}, true,1);
		quadTree.insert(new int[]{1,2}, false,1);
		quadTree.insert(new int[]{0,3}, true,1);
		quadTree.insert(new int[]{3,2}, false,1);
		quadTree.insert(new int[]{3,0}, true,1);
		quadTree.insert(new int[]{0,2}, false,1);
		
		System.out.println("Initial QuadTree\n");
		System.out.println(quadTree);
		System.out.println("\n\n");
		
		System.out.println("Searching for {0,0}");
		System.out.println(quadTree.search(new int[] {0,0}));
		System.out.println("\n\n");
		
		System.out.println("Searching for {3,2}");
		System.out.println(quadTree.search(new int[] {3,2}));
		System.out.println("\n\n");
		
		System.out.println("Searching for {4,1}");
		System.out.println(quadTree.search(new int[] {4,1}));
		System.out.println("\n\n");
		
		quadTree.insert(new int[]{2,0}, true,1);
		
		System.out.println("First Update\n");
		System.out.println(quadTree);
		System.out.println("\n\n");
		
		quadTree.insert(new int[]{0,3}, false,1);
		
		System.out.println("First Update\n");
		System.out.println(quadTree);
		System.out.println("\n\n");

		quadTree.insert(new int[]{0,0}, true,1);
		
		System.out.println("Second Update\n");
		System.out.println(quadTree);
		System.out.println("\n\n");

		quadTree.insert(new int[]{0,0}, false,2);
		
		System.out.println("Third Update\n");
		System.out.println(quadTree);
		System.out.println("\n\n");

		quadTree.insert(new int[]{2,0}, false,2);
		
		System.out.println("Fourth Update\n");
		System.out.println(quadTree);
		System.out.println("\n\n");

//		quadTree.insert(new int[]{2,0}, false,2);
//		
//		System.out.println("Second Update\n");
//		System.out.println(quadTree);
//		System.out.println("\n\n");
		
	}
}
