package com.git.QuadTreeImp;

public class Pixel<T> {
	private T pixVal;
	
	// Empty Init
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
	
}
