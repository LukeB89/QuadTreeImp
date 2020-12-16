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
		
		System.out.println(quadTree);
		
		
		
	}
}
