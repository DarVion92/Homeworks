package logica.tree;

import java.util.List;

public interface IGeneralTree<E> {
	
	public boolean isEmpty();

	public E deleteGeneralNode(TreeNode<E> node);
	
	public int generalTreeLevel();	

	public TreeNode<E> getGeneralFather(TreeNode<E> node);

	public List<TreeNode<E>> getGeneralLeaves();
	
	public TreeNode<E> getRoot();
	
	public boolean insertNode(TreeNode<E> node, TreeNode<E> father);	
	
	/// recorridos
	public void postOrderForEach(TreeNode<E> cursor, Daemon daemon,Object reference); 
	
	public void postOrderInvertedForEach(TreeNode<E> cursor, Daemon daemon, Object reference); 
	
	public TreeNode<E> preOrderFirstThat(TreeNode<E> cursor, Daemon daemon, Object reference); 

	public void preOrderForEach(TreeNode<E> cursor, Daemon daemon, Object reference); 
	
	public void symmetricForEach(TreeNode<E> cursor, Daemon daemon, Object reference);
	
	public int totalNodes();	

}
