package com.git.QuadTreeImp;
/**
 * Implementation of a Pixel. This holds the value of a node with a generic data type
 *
 * @author Luke Byrne, Milo Bashford
 */
public class Pixel<T> {
	private T pixVal;
	
	// Default Init
	public Pixel() {
		this(null);
	}
	// Init
	public Pixel(T value) {
		pixVal = value;
	}
	// Accessor Method
	public T getValue() {
		return pixVal;
	}
	// Modifier Method
	public void setValue(T newValue) {
		pixVal = newValue;
	}
	// Create and Return String of Pixel Value
	public String toString() {
		String pixString= ""+pixVal+"";
		
		return pixString;
	}
	
}
