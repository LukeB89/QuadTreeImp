package com.git.QuadTreeImp;

public interface Visitor<T> {
	public void visit(Node<T> curNode , Object data);
}
