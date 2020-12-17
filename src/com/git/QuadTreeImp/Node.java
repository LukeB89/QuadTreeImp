package com.git.QuadTreeImp;
/**
 * Implementation of a Node. For use in a Quadtree.
 *
 * @author Luke Byrne, Milo Bashford
 */

// Node class creation
public class Node<T> {
	/* Internal variable implementation
	 * Points for bounds of current node.
	 * Pixel for value of current Node
	 * Nodes for each child and parent
	 */
	
	private Point boundTL;
	private Point boundBR;
	private Pixel<T> nodeVal;
	private Node<T> childTL;
	private Node<T> childTR;
	private Node<T> childBL;
	private Node<T> childBR;
	private Node<T> parent;
	
	// Default constructor
	public Node() {
		this(null,null,null,null,null,null,null,null);
	}
	
	// Node Constructor. Takes Boundry Points, value Pixel and Child and Parent Nodes
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
	

	// Start of Getters and Setters block
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
		// ensures bound always has a value to prevent errors
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
		// ensures bound always has a value to prevent errors
		if(boundBR != null) {
			this.boundBR = boundBR;
		}else {
			this.boundBR = new Point();
		}
	}
	// End of Getters and Setters block
	
	
	// Checks if Node is a Leaf Node
	public boolean isLeaf() {
		if (this.childTL == null && this.childTR == null && this.childBL == null && this.childBR == null) {
			return true;
		}else {
			return false;
		}
	}
	
	
	// checks to see if supplied Point is within the bounds of the current node.
	public boolean withinBounds(Point testBound) {
		if (this.boundTL.compareTo(testBound) >= 0 && this.boundBR.compareTo(testBound) <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	// Checks to see if the current Node has the same bounds as the TestNode
	public boolean equalBounds(Node<T> testNode) {
		if (this.boundTL.compareTo(testNode.getBoundTL()) == 0 && this.boundBR.compareTo(testNode.getBoundBR()) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	// Checks to see if the current Node has the same Value as the TestNode
	public boolean compareTo(Node<T> testNode) {
		// if both are not nulls do comparison else return false
		if (this.nodeVal != null && testNode.getNodeVal() != null) {
			if (this.nodeVal.getValue() == testNode.getNodeVal().getValue()) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}
	
	//Creates and Returns a string of the Node, Both Boundaries and the Pixel Value
	public String toString() {
		String nodeString;
		if (this.nodeVal == null) {
			nodeString = "["+this.boundTL.toString()+","+this.boundBR.toString()+"," +this.nodeVal+"]";
		}else {
			nodeString = "["+this.boundTL.toString()+","+this.boundBR.toString()+"," +this.nodeVal.toString()+"]";
		}

		
		
		return nodeString;
	}
	
}
