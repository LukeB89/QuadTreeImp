package com.git.QuadTreeImp;


/**
 * Implementation of a QuadTree
 *
 * @author Luke Byrne, Milo Bashford
 */

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
		 * is a power of two, that the bounds are correct and Passes to the internal insert function.
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
		// Calls on Private Insert function to implement insert
		insert(this.root, inNode);

	}
	
	
	private void insert(Node<T> parentNode, Node<T> inNode) {
		/* This function inserts a node in place. when inserting it will check bounds 
		 * against bounds of the node being inserted. if they are the same it will replace 
		 * the value at that node with the inserted node if not it will split the node 
		 * and recursively go to the next node with the required boundary points.
		 * 
		 */
		
		// parent node is current node
		// check for no children
		if(parentNode.isLeaf()) {
			if (parentNode.equalBounds(inNode)) {
				// if parent node has equal bounds to new node update value
				parentNode.setNodeVal(inNode.getNodeVal());
				compressTree(parentNode);
			}else {
				if (!parentNode.compareTo(inNode)) {
					// if Node Values are different Split current  and make childern
					splitNodeChildren(parentNode);
					// pass correct Node and New node to insert again
					insert(getChildNode(parentNode, inNode.getBoundTL(), inNode.getBoundBR()), inNode);
				}
				
			}
		}else {
			if (parentNode.equalBounds(inNode)) {
				// if parent node has equal bounds to new node update value
				parentNode.setNodeVal(inNode.getNodeVal());
				compressTree(parentNode);
			}else {
				// pass correct Node and New node to insert again
				insert(getChildNode(parentNode, inNode.getBoundTL(), inNode.getBoundBR()), inNode);
			}
			
		}
	}
	
	public Node<T> search(int[] searchXY) {
		/* This function sets up the point being searched and makes sure it is in the root.
		 * 
		 * Then passes point and root to recursive search.
		 */
		Point searchPoint = new Point(searchXY[0], searchXY[1]);
		if(!(this.root.withinBounds(searchPoint))) throw new BoundryError("Inputted Point Out Of Bounds");
		return search(this.root,searchPoint);
	}
	
	private Node<T> search(Node<T> curNode, Point searchPoint) {
		/* This searches for the Node closest to the inputed point
		 * 
		 */
		
		// If node is leaf node return it to function call
		if(curNode.isLeaf()) {
			return curNode;
		}else {
			// If not leaf node get Child that contains point and search again, return Node to function call
			return search(getChildNode(curNode, searchPoint, searchPoint), searchPoint);
		}
	}
	
	//Checks if number is power of 2 using bitwise and
	private boolean checkPower2(int num) 
    { 
        return num != 0 && ((num & (num - 1)) == 0); 
    } 
	
	
	private Node<T> getChildNode(Node<T> parentNode, Point boundTL, Point boundBR) throws BoundryError{
		/* from parent node this checks all children nodes against the supplied bounds
		 * if bounds are within child node bounds return child node,
		 * if bounds are outside all child node bounds throw Boundry Error
		 */
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
		/* This function will take a parentNode and Split into 4 child Nodes.
		 * 
		 * Do not need to worry about splitting on leaf as boundary check will 
		 * always be true if node is single pixel (smallest leaf node will boundary error or be worked on).
		 * 
		 * takes current boundary for parent and splits them into quadrants (one for each child)
		 * 
		 */
		//get boundaries
		int[] pointsL = parentNode.getBoundTL().getPoint();
		int[] pointsR = parentNode.getBoundBR().getPoint();
		// Get Mid points
		int rowMid = (pointsL[0]+pointsR[0])/2;
		int colMid = (pointsL[1]+pointsR[1])/2;
		// Setup child nodes with new boundaries
		Node<T> childTL = new Node<T>(new Point(pointsL[0],pointsL[1]), new Point(rowMid,colMid), parentNode.getNodeVal(), null, null, null, null, parentNode);
		Node<T> childTR = new Node<T>(new Point(pointsL[0],(colMid+1)), new Point(rowMid,pointsR[1]), parentNode.getNodeVal(), null, null, null, null, parentNode);
		Node<T> childBL = new Node<T>(new Point((rowMid+1),pointsL[1]), new Point(pointsR[0],colMid), parentNode.getNodeVal(), null, null, null, null, parentNode);
		Node<T> childBR = new Node<T>(new Point((rowMid+1),(colMid+1)), new Point(pointsR[0],pointsR[1]), parentNode.getNodeVal(), null, null, null, null, parentNode);
		// Set parent nodes children
		parentNode.setChildTL(childTL);
		parentNode.setChildTR(childTR);
		parentNode.setChildBL(childBL);
		parentNode.setChildBR(childBR);
		// Set Parent Nodes value
		parentNode.setNodeVal(null);
	}
	
	
	public String toString() {
		/* This function is used to print the tree.
		 * 
		 * The Structure is as follows:
		 * 
		 * Root
		 * 		Level 1 ChildLT
		 * 				Level 2 ChildLT
		 * 						Level 3 ChildLT
		 * 								...
		 * 										Level N ChildLT 
		 * 		Level 1 ChildTR
		 * 		Level 1 ChildBL
		 * 		Level 1 ChildBR
		 */
		final StringBuffer buf = new StringBuffer();
		// Annomous class adapted from class code
		new Visitor<T>() {
			public void visit(Node<T> curNode, Object data) {
				// add any tabs for this level
				buf.append(data.toString());
				// add Nodes String
				buf.append(curNode.toString());
				// add New Line
				buf.append("\n");
				// if not a leaf recursively visit next node
				if (!curNode.isLeaf()) {
					// recursively visit next node add tab to data for next level
					visit(curNode.getChildTL(), data+"\t");
					visit(curNode.getChildTR(), data+"\t");
					visit(curNode.getChildBL(), data+"\t");
					visit(curNode.getChildBR(), data+"\t");
				}

			}
			// initiate visit with route
		}.visit(this.root, "");
		return buf.toString();
	}
	
	private void compressTree(Node<T> curNode) {
		/* This function will compress the tree.
		 * 
		 * It is run whenever there is an insert run. compares all the children of the parent. 
		 * If they are the same then update parents value to be children's value and remove.
		 * 
		 * If Parent Node value is already set then remove children.
		 * 
		 * Recursively call up through the tree compressing until there are no more parents (in root) or children are different.
		 * 
		 */
		if (curNode.isLeaf()) {
			Node<T> parentNode = curNode.getParent();
			if (parentNode != null) {
				compressTree(parentNode);
			}
			
		}else {
			Node<T> childTL = curNode.getChildTL();
			Node<T> childTR = curNode.getChildTR();
			Node<T> childBL = curNode.getChildBL();
			Node<T> childBR = curNode.getChildBR();
			
			if (childTL.compareTo(childTR) && childTL.compareTo(childBL) && childTL.compareTo(childBR) && curNode.getNodeVal() == null) {
				curNode.setNodeVal(childTL.getNodeVal());
				curNode.setChildTL(null);
				curNode.setChildTR(null);
				curNode.setChildBL(null);
				curNode.setChildBR(null);
				Node<T> parentNode = curNode.getParent();
				if (parentNode != null) {
					compressTree(parentNode);
				}
				
			}else if (curNode.getNodeVal() != null) {
				curNode.setChildTL(null);
				curNode.setChildTR(null);
				curNode.setChildBL(null);
				curNode.setChildBR(null);
				Node<T> parentNode = curNode.getParent();
				if (parentNode != null) {
					compressTree(parentNode);
				}
			} 
				

		}
		
		
	}
	
}
