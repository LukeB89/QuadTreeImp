package com.git.QuadTreeImp;


public class QuadTree<T> {
	private Node<T> root;
	private int size;
	
	public QuadTree(int pixlSqr, T defaultPixelVal){
		this(pixlSqr, pixlSqr, defaultPixelVal);	
	}
	
	public QuadTree(int pixlRow, int pixlCol, T defaultPixelVal) throws SizeError {
		if(!checkPower2(pixlRow)) throw new SizeError("Pixel Rows must be Power of 2");
		if(!checkPower2(pixlCol)) throw new SizeError("Pixel Columns must be Power of 2");
		
		Point boundTL = new Point();
		Point boundBR = new Point(pixlRow-1, pixlCol-1);
		Pixel<T> defPixel = new Pixel<T>(defaultPixelVal);
		this.setRoot(new Node<T>(boundTL, boundBR, defPixel, null, null, null, null, null));
		this.setSize(0);		
	}
	public int getSize() {
		return size;
	}
	private void setSize(int size) {
		this.size = size;
	}
	public Node<T> getRoot() {
		return root;
	}
	private void setRoot(Node<T> root) {
		this.root = root;
	}
	public void insert(int[] startPoint, T inVal, int pixlSize) throws BoundryError {
		/* This function sets up the input pixel for the node, checks to ensure that the input pixel size
		 * is a power of two, that the bounds are correct and determines which branch to go down.
		 */
		
		// Check for power of 2 throw error if not
		if(!checkPower2(pixlSize)) throw new SizeError("Pixel Size must be Power of 2");
		// Setup Bounds
		Point boundTL = new Point(startPoint[0], startPoint[1]);
		Point boundBR = new Point(startPoint[0]+pixlSize-1, startPoint[1]+pixlSize-1);
		// Setup Pixel
		Pixel<T> pix = new Pixel<T>(inVal);
		// Check Bounds, Throw Error if not within root
		if(!(this.root.withinBounds(boundTL))) throw new BoundryError("Inputted TL Boundry Out Of Bounds");
		if(!(this.root.withinBounds(boundBR))) throw new BoundryError("Inputted BR Boundry Out Of Bounds");
		// setup new node for insertion
		Node<T> inNode = new Node<T>(boundTL, boundBR, pix, null, null, null, null, null);
		//check for no children
		if(this.root.isLeaf()) {
			
			if (this.root.equalBounds(inNode)) {
				// if parent node has equal bounds to new node update value
				this.root.setNodeVal(inNode.getNodeVal());
			}else {
				// if not split children and get appropriate branch for recursive call
				splitNodeChildren(this.root);
				insert(getChildNode(this.root, inNode.getBoundTL(), inNode.getBoundBR()), inNode);
			}
		}else {
			if (this.root.equalBounds(inNode)) {
				// if parent node has equal bounds to new node update value
				this.root.setNodeVal(inNode.getNodeVal());
			}else {
				// if get appropriate branch for recursive call
				insert(getChildNode(this.root, inNode.getBoundTL(), inNode.getBoundBR()), inNode);
			}
		}

	}
	
	
	private void insert(Node<T> parentNode, Node<T> inNode) {
		// parent node is current node
		// check for no children
		if(parentNode.isLeaf()) {
			if (parentNode.equalBounds(inNode)) {
				// if parent node has equal bounds to new node update value
				parentNode.setNodeVal(inNode.getNodeVal());
			}else {
				splitNodeChildren(parentNode);
				insert(getChildNode(parentNode, inNode.getBoundTL(), inNode.getBoundBR()), inNode);
			}
		}else {
			if (parentNode.equalBounds(inNode)) {
				// if parent node has equal bounds to new node update value
				parentNode.setNodeVal(inNode.getNodeVal());
			}else {
				insert(getChildNode(parentNode, inNode.getBoundTL(), inNode.getBoundBR()), inNode);
			}
			
		}
	}
	
	//Checks if number is power of 2 using bitwise and
	private boolean checkPower2(int num) 
    { 
        return num != 0 && ((num & (num - 1)) == 0); 
    } 
	
	private Node<T> getChildNode(Node<T> parentNode, Point boundTL, Point boundBR) throws BoundryError{
		Node<T> childTL = parentNode.getChildTL();
		Node<T> childTR = parentNode.getChildTR();
		Node<T> childBL = parentNode.getChildBL();
		Node<T> childBR = parentNode.getChildBR();
		
		if (childTL.withinBounds(boundTL) && childTL.withinBounds(boundBR)) {
			return childTL;
		} else if (childTR.withinBounds(boundTL) && childTR.withinBounds(boundBR)) {
			return childTR;
		}else if (childBL.withinBounds(boundTL) && childBL.withinBounds(boundBR)) {
			return childBL;
		}else if (childBR.withinBounds(boundTL) && childBR.withinBounds(boundBR)) {
			return childBR;
		} else {
			throw new BoundryError("Inputted Pixel Out Of Bounds");
		}
	}
	
	private void splitNodeChildren(Node<T> parentNode) {
		int[] pointsL = parentNode.getBoundTL().getPoint();
		int[] pointsR = parentNode.getBoundBR().getPoint();
		int rowMid = (pointsL[0]+pointsR[0])/2;
		int colMid = (pointsL[1]+pointsR[1])/2;
		Node<T> childTL = new Node<T>(new Point(pointsL[0],pointsL[1]), new Point(rowMid,colMid), parentNode.getNodeVal(), null, null, null, null, parentNode);
		Node<T> childTR = new Node<T>(new Point(pointsL[0],(colMid+1)), new Point(rowMid,pointsR[1]), parentNode.getNodeVal(), null, null, null, null, parentNode);
		Node<T> childBL = new Node<T>(new Point((rowMid+1),pointsL[1]), new Point(pointsR[0],colMid), parentNode.getNodeVal(), null, null, null, null, parentNode);
		Node<T> childBR = new Node<T>(new Point((rowMid+1),(colMid+1)), new Point(pointsR[0],pointsR[1]), parentNode.getNodeVal(), null, null, null, null, this.root);
		parentNode.setChildTL(childTL);
		parentNode.setChildTR(childTR);
		parentNode.setChildBL(childBL);
		parentNode.setChildBR(childBR);
	}
	
	
	public String toString() {
		final StringBuffer buf = new StringBuffer();
		// Annomous class adapted from class code
		new Visitor<T>() {
			public void visit(Node<T> curNode, Object data) {
				buf.append(data.toString());
				buf.append(curNode.toString());
				buf.append("\n");
				if (!curNode.isLeaf()) {
					visit(curNode.getChildTL(), data+"\t");
					visit(curNode.getChildTR(), data+"\t");
					visit(curNode.getChildBL(), data+"\t");
					visit(curNode.getChildBR(), data+"\t");
				}

			}
		}.visit(this.root, "");
		return buf.toString();
	}
	
}
