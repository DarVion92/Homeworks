package logica.tree;

import java.util.List;

public interface IBinaryTree<E> {
	
	public TreeNode<E> getRoot();	
	
	public boolean isEmpty();

	public E deleteNode(BinaryTreeNode<E> node);

	public BinaryTreeNode<E> getFather(BinaryTreeNode<E> node);

	public List<BinaryTreeNode<E>> getLeaves();

	public boolean insertNode(BinaryTreeNode<E> node, char type, BinaryTreeNode<E> father);

	// recorridos	
	public void postOrderForEach(TreeNode<E> cursor, Daemon daemon,Object reference); 
	
	public void postOrderInvertedForEach(TreeNode<E> cursor, Daemon daemon, Object reference); 
	
	public TreeNode<E> preOrderFirstThat(TreeNode<E> cursor, Daemon daemon, Object reference); 

	public void preOrderForEach(TreeNode<E> cursor, Daemon daemon, Object reference); 
	
	public void symmetricForEach(TreeNode<E> cursor, Daemon daemon, Object reference);

	public int totalNodes();
	
	public int treeLevel();		

}
