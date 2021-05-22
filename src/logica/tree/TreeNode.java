package logica.tree;

/**
 * @author Juan L
 *
 * @param <E>
 */
abstract public class TreeNode<E> {
	
	protected E info;
	
	abstract public int degree();
	abstract public boolean isLeaf();

	public E getInfo() {
		return info;
	}

	public void setInfo(E info) {
		this.info = info;
	}
	
	
	
}
