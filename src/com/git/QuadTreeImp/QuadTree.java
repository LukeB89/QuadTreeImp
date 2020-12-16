package com.git.QuadTreeImp;

import java.util.Arrays;

public class QuadTree<T> {
	private Node<T> root;
	private int size;
	
	public  QuadTree() {
		this.setRoot(new Node<T>());
		this.setSize(0);
	}
	public QuadTree(int pixlSqr) {
		Point boundTL = new Point();
		Point boundBR = new Point(pixlSqr-1, pixlSqr-1);
		this.setRoot(new Node<T>(boundTL, boundBR, null, null, null, null, null, null));
		this.setSize(0);		
	}
	public QuadTree(int pixlRow, int pixlCol) {
		Point boundTL = new Point();
		Point boundBR = new Point(pixlRow-1, pixlCol-1);
		this.setRoot(new Node<T>(boundTL, boundBR, null, null, null, null, null, null));
		this.setSize(0);		
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Node<T> getRoot() {
		return root;
	}
	public void setRoot(Node<T> root) {
		this.root = root;
	}
	public void insert(Point boundBoth, Pixel<T> inVal) throws BoundryError {
		Node<T> inNode = new Node<T>(boundBoth, boundBoth, inVal, null, null, null, null, null);
		if(!(this.root.withinBounds(boundBoth))) throw new BoundryError("Inputted Boundry Out Of Bounds");
		if(this.root.isLeaf()) {
			Point rootBTL = root.getBoundTL();
			Point rootBBR = root.getBoundBR();
			System.out.println("Bound TL: "+Arrays.toString(rootBTL.getPoint()));
			System.out.println("Bound BR: "+Arrays.toString(rootBBR.getPoint()));
			System.out.println("In Point: "+Arrays.toString(boundBoth.getPoint()));
			System.out.println(this.root.withinBounds(boundBoth));
//			int rowMid = 
//			Node<T> childTL = new Node<T>(new Point(), brBound, val, tl, tr, bl, br, p);
//			Node<T> childTR;
//			Node<T> childBL;
//			Node<T> childBR;
		}
	}
	
	public void insert(Point boundTL, Point boundBR, Pixel<T> inVal) {
		Node<T> inNode = new Node<T>(boundTL, boundBR, inVal, null, null, null, null, null);
		
		if(this.root.isLeaf()) {
			
		}
	}
	
	public void insert(Node<T> parentNode, Node<T> inNode) {
		if(parentNode.isLeaf()) {
			
		}
	}
}
