package com.git.QuadTreeImp;

public class Point {
	private int x;
	private int y;
	
	public Point(int xS, int yS) {
		setPoint(xS, yS);
	}
	
	public Point() {
		this(0, 0);
	}
	
	

	// Accessor Method

	public int[] getPoint() {
		int[] arr = {x,y};
		return arr;
	}
	
	// Setter Method
	public void setPoint(int xS, int yS) {
		this.x = xS;
		this.y = yS;
	}
	
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
}
