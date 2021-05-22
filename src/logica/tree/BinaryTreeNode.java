package logica.tree;

import java.io.Serializable;


public class BinaryTreeNode<E> extends TreeNode<E> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected BinaryTreeNode<E> left;
	protected BinaryTreeNode<E> right;
	
	public BinaryTreeNode() {
		this.info = null;
		this.left = null;
		this.right = null;
	}
	
	public BinaryTreeNode(E info) {
		this.info = info;
		this.left = null;
		this.right = null;
	}
	
	public int degree() {
		int degree = 0;
		if (left != null)
			degree++;
		if (right != null)
			degree++;
		return degree;
	}
	
    public boolean isLeaf() {
    	return (degree() == 0);
    }  
    
    public BinaryTreeNode<E> getLeft() {
		return left;
	}
    
    public void setLeft(BinaryTreeNode<E> left) {
		this.left = left;
	}
    
    public BinaryTreeNode<E> getRight() {
		return right;
	}
    
    public void setRight(BinaryTreeNode<E> right) {
		this.right = right;
	}
    
}
