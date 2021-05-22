package logica.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class BinaryGTreeNode<E> extends BinaryTreeNode<E> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BinaryGTreeNode() {
		super();
	}
	
	public BinaryGTreeNode(E info) {
		super(info);
	}	
	
	public int degree() {
		int degree = 0;
		BinaryTreeNode<E> cursor = left;
		while (cursor != null) {
			cursor = cursor.getRight();
			degree += 1;
		}
		return degree;
	}
	
    public boolean isLeaf() {
    	return (this.left == null);
    }  
    
	public List<TreeNode<E>> getSons() {
		List<TreeNode<E>> sons = new ArrayList<TreeNode<E>>();
		BinaryTreeNode<E> cursor = left; 
		while (cursor != null) {
			sons.add(cursor);
			cursor = cursor.getRight();
		}
		return sons;
	}
    
}
