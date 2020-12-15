package com.git.QuadTreeImp;


public class Node<T extends Comparable<T>> {
	private Point boundTL;
	private Point boundBR;
	private Pixel<T> nodeVal;
	private Node<T> childTL;
	private Node<T> childTR;
	private Node<T> childBL;
	private Node<T> childBR;
	private Node<T> parent;
	
	public Node() {
		this(null,null,null,null,null,null,null,null);
	}
	
	public Node(Point tlBound,Point brBound, Pixel<T> val, Node<T> tl, Node<T> tr, Node<T> bl, Node<T> br, Node<T> p) {
		setBoundTL(tlBound);
		setBoundBR(brBound);
		setNodeVal(val);
		setChildTL(tl);
		setChildTR(tr);
		setChildBL(bl);
		setChildBR(br);
		setParent(p);
	}
	

	
	public Point[] getBounds() {
		Point[] bounds = {boundTL,boundBR};
		return bounds;
	}
	
//	@SuppressWarnings("unchecked")
//	public Node<T>[] getChildren(){
//		Node<T>[] childNodes = (Node<T>[]) new Object[4];
//		childNodes[0] = childTL;
//		childNodes[1] = childTR;
//		childNodes[2] = childBL;
//		childNodes[3] = childBR;
//		return childNodes;
//	}

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public Node<T> getChildTL() {
		return childTL;
	}

	public void setChildTL(Node<T> childTL) {
		this.childTL = childTL;
	}

	public Node<T> getChildTR() {
		return childTR;
	}

	public void setChildTR(Node<T> childTR) {
		this.childTR = childTR;
	}

	public Node<T> getChildBL() {
		return childBL;
	}

	public void setChildBL(Node<T> childBL) {
		this.childBL = childBL;
	}

	public Node<T> getChildBR() {
		return childBR;
	}

	public void setChildBR(Node<T> childBR) {
		this.childBR = childBR;
	}

	public Pixel<T> getNodeVal() {
		return nodeVal;
	}

	public void setNodeVal(Pixel<T> nodeVal) {
		this.nodeVal = nodeVal;
	}

	public Point getBoundTL() {
		return boundTL;
	}

	public void setBoundTL(Point boundTL) {
		this.boundTL = boundTL;
	}

	public Point getBoundBR() {
		return boundBR;
	}

	public void setBoundBR(Point boundBR) {
		this.boundBR = boundBR;
	}
	
}
