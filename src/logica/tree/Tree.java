package logica.tree;

abstract public class Tree<E> {

	protected TreeNode<E> root;
	
	public Tree() {
		root = null;
	}

	public Tree(TreeNode<E> root) {
		this.root = root;
	}

	public TreeNode<E> getRoot() {
		return root;
	}

	public void setRoot(TreeNode<E> root) {
		this.root = root;
	}

	public boolean isEmpty() {
		return root == null;
	}
}
