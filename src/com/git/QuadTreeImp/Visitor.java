package com.git.QuadTreeImp;
/**
 * Declaration of Visitor
 *
 * @author Luke Byrne, Milo Bashford
 */
public interface Visitor<T> {
	public void visit(Node<T> curNode , Object data);
}
