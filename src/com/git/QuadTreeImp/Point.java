package com.git.QuadTreeImp;
/**
 * Implementation of a Point data type for use in a QuadTree and Node implementation
 *
 * @author Luke Byrne, Milo Bashford
 */
public class Point {
	private int x;
	private int y;
	
	// Constructor
	public Point(int xS, int yS) {
		setPoint(xS, yS);
	}
	
	// Default Constructor
	public Point() {
		this(0, 0);
	}
	
	

	// Accessor Method
	// Returns Integer array of point
	public int[] getPoint() {
		int[] arr = {x,y};
		return arr;
	}
	
	// Setter Method
	public void setPoint(int xS, int yS) {
		this.x = xS;
		this.y = yS;
	}
	
	// Compares current Point to Supplied Point
	// 0 if current == supplied
	// -1 if current >= supplied
	// else 1
	public int compareTo(Point otherPoint) {
		int[] other = otherPoint.getPoint();
		if (this.x == other[0] && this.y == other[1]) {
			return 0;
		} else if (this.x >= other[0] && this.y >= other[1]) {
			return -1;
		} else {
			return 1;
		}
	}
	
	// Converts Point to a String to return.
	public String toString() {
		String pointString = "{"+x+","+y+"}";
		return pointString;
	}
}
