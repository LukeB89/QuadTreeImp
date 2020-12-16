package com.git.QuadTreeImp;


public class Node<T> {
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
		if(boundTL != null) {
			this.boundTL = boundTL;
		}else {
			this.boundTL = new Point();
		}
		
	}

	public Point getBoundBR() {
		return boundBR;
	}

	public void setBoundBR(Point boundBR) {
		if(boundBR != null) {
			this.boundBR = boundBR;
		}else {
			this.boundBR = new Point();
		}
	}
	
	public boolean isLeaf() {
		if (this.childTL == null && this.childTR == null && this.childBL == null && this.childBR == null) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean withinBounds(Point testBound) {
		if (this.boundTL.compareTo(testBound) >= 0 && this.boundBR.compareTo(testBound) <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean equalBounds(Node<T> testNode) {
		if (this.boundTL.compareTo(testNode.getBoundTL()) == 0 && this.boundBR.compareTo(testNode.getBoundBR()) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		String nodeString = "["+this.boundTL.toString()+","+this.boundBR.toString()+"," +this.nodeVal.getValue()+"]";
		return nodeString;
	}
	
}
