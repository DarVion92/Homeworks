package logica.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BinaryTree<E> extends Tree<E> implements Serializable, IBinaryTree<E>,IGeneralTree<E> {

	private static final long serialVersionUID = 1L;
	private BinaryTreeNode<E> NULL;

	public BinaryTree() {
		super();
	}

	public BinaryTree(TreeNode<E> root) {
		super(root);
	}

	protected int level(BinaryTreeNode<E> cursor) {
		if (cursor != null) {
			int levelLST = level(cursor.getLeft());
			int levelRST = level(cursor.getRight());
			return ((levelLST >= levelRST) ? levelLST : levelRST) + 1;
		}
		return -1;
	}
	
	public int treeLevel() {
		return level((BinaryTreeNode<E>) root);
	}

	public int nodeLevel(BinaryTreeNode<E> node) {
		if (node != null) {
			return node.equals(root) ? 0 : nodeLevel(getFather(node)) + 1;
		}
		return -1;
	}

	protected int generalLevel(BinaryTreeNode<E> cursor) {
		if (cursor != null) {
			int levelLST = level(cursor.getLeft())+11;
			int levelRST = level(cursor.getRight());
			return ((levelLST >= levelRST) ? levelLST : levelRST);
		}
		return -1;
	}
	
	public int generalTreeLevel() {
		return generalLevel((BinaryTreeNode<E>) root);
	}

	public BinaryTree(BinaryTreeNode<E> root) {
		this.root = root;
	}

	public E deleteNode(BinaryTreeNode<E> node) {
		if (node != null) {
			if (root != null && root.equals(node)) {
				deleteRoot((BinaryTreeNode<E>) root, root.degree());
			} else {
				BinaryTreeNode<E> father = getFather(node);
				deleteNotRoot(node, node.degree(), father);
			}
			return node.getInfo();
		}
		return null;
	}

	private void deleteNotRoot(BinaryTreeNode<E> node, int degree,
			BinaryTreeNode<E> father) {
		if (node != null && father != null) {
			switch (degree) {
			case 0:
				if (node.equals(father.getLeft())) {
					father.setLeft(null);
				} else {
					father.setRight(null);
				}
				break;
			case 1:
				if (node.getLeft() != null) {
					if (node.equals(father.getLeft())) {
						father.setLeft(node.getLeft());
					} else {
						father.setRight(node.getLeft());
					}
				} else {
					if (node.equals(father.getLeft())) {
						father.setLeft(node.getRight());
					} else {
						father.setRight(node.getRight());
					}
				}
				break;
			case 2:
				if (father.degree() != 2) {
					father.setLeft(node.getLeft());
					father.setRight(node.getRight());
				} else {
					if (node.equals(father.getLeft())) {
						father.setLeft(node.getLeft());
					} else {
						father.setRight(node.getLeft());
					}
					BinaryTreeNode<E> incompletedNode = null;
					if (isAllNodesDegreeTwo()) {
						if (incompletedNode != null) {
							incompletedNode = firstIncompletedNode((BinaryTreeNode<E>) root);
							if (incompletedNode.getLeft() != null) {
								incompletedNode.setRight(node.getRight());
							} else {
								incompletedNode.setLeft(node.getRight());
							}
						}
					} else {
						incompletedNode = firstIncompletedNode(node.getLeft());
						if (incompletedNode != null) {
							if (incompletedNode.getLeft() != null) {
								incompletedNode.setRight(node.getRight());
							} else {
								incompletedNode.setLeft(node.getRight());
							}
						}
					}
				}
				break;
			}
		}
	}

	private boolean isAllNodesDegreeTwo() {
		Daemon nodesDegreeTwoDaemon = new Daemon() {
        @Override
        public boolean actionFirstThat(TreeNode cursor, Object reference) {
	    // TODO Auto-generated method stub
	    if (cursor != null) {
		  return cursor.degree() == 1;
	    }
	     return false;
        }
		};
		return preOrderFirstThat(root, nodesDegreeTwoDaemon, null) == null;
	}
	
		private void deleteRoot(BinaryTreeNode<E> root, int degree) {
		switch (degree) {
		case 0:
			this.root = null;
			break;
		case 1:
			if (root.getLeft() != null) {
				setRoot(root.getLeft());
			} else {
				setRoot(root.getRight());
			}
			break;
		case 2:
			BinaryTreeNode<E> incompletedNode = firstIncompletedNode((BinaryTreeNode<E>) this.root);
			if (incompletedNode != null) {
				if (incompletedNode.getLeft() != null) {
					incompletedNode.setRight(root.getRight());
				} else {
					incompletedNode.setLeft(root.getRight());
				}
			}
			setRoot(root.getLeft());
			break;
		}
	}

	public boolean divideTree(BinaryTreeNode<E> node, BinaryTree<E> treeA,
			BinaryTree<E> treeB) {
		boolean divided = true;
		if (node != null) {
			if (node.equals(root)) {
				getNodeSubTree((BinaryTreeNode<E>) root, null, treeA);
				treeB.setRoot(null);
			} else {
				getNodeSubTree((BinaryTreeNode<E>) root, node, treeA);
				getNodeSubTree(node, null, treeB);
			}
		} else {
			treeA.setRoot(null);
			treeB.setRoot(null);
			divided = false;
		}
		return divided;
	}

	public BinaryTreeNode<E> getFather(BinaryTreeNode<E> node) {
		if (node != null) {
			if (node.equals(root)) {
				return null;
			} else {
				Daemon findFatherDaemon = new Daemon() {
                  @Override
                public boolean actionFirstThat(TreeNode cursor, Object reference) {
          			if (cursor != null && reference != null) {
						if ((reference.equals(((BinaryTreeNode<E>)cursor).getLeft()))	|| (reference.equals(((BinaryTreeNode<E>)cursor).getRight()))) 
							return true;		 
						else 
							return false;	
					}
					return false;
                }
				};
				return (BinaryTreeNode<E>)preOrderFirstThat(root, findFatherDaemon, node);
			}
		}
		return null;
	}

	private BinaryTreeNode<E> firstIncompletedNode(BinaryTreeNode<E> cursor) {
		Daemon incompleteNodeDaemon = new Daemon() {
        @Override
        public boolean actionFirstThat(TreeNode cursor, Object reference) {
			if (cursor != null) {
				return cursor.degree() < 2;
			}
			return false;
         }   
       };
       return (BinaryTreeNode<E>)preOrderFirstThat(cursor,incompleteNodeDaemon, null);
	}

	public List<BinaryTreeNode<E>> getLeaves() {
		List<BinaryTreeNode<E>> leavesList = new ArrayList<BinaryTreeNode<E>>();
		Daemon leavesDaemon = new Daemon() {

			@SuppressWarnings("unused")
			public void actionForEach(BinaryTreeNode cursor, Object reference) {
				if (cursor != null) {
					if (cursor.getLeft() == null && cursor.getRight() == null) {
						((List<BinaryTreeNode<E>>) reference).add(cursor);
					}
				}
			}
		};
		preOrderForEach(root, leavesDaemon, leavesList);
		return leavesList;
	}

	private void getNodeSubTree(BinaryTreeNode<E> root, BinaryTreeNode<E> node,
			BinaryTree<E> tree) {
		if (root != null && !root.equals(node)) {
			BinaryTreeNode<E> cursor = new BinaryTreeNode<E>(root.getInfo());
			if (root.getLeft() != null && !root.getLeft().equals(node)) {
				getNodeSubTree(root.getLeft(), node, tree);
				cursor.setLeft((BinaryTreeNode<E>) tree.getRoot());
			} else {
				cursor.setLeft(null);
			}
			if (root.getRight() != null && root.getRight().equals(node)) {
				getNodeSubTree(root.getRight(), node, tree);
				cursor.setRight((BinaryTreeNode<E>) tree.getRoot());
			} else {
				cursor.setRight(null);
			}
			tree.setRoot(cursor);
		}
	}

	@SuppressWarnings("unchecked")
	public List<BinaryTreeNode<E>> getSubTree(BinaryTreeNode<E> node, char type) {
		if (node != null) {
			SubTree<E> subTree = new SubTree<E>();
			subTree.setLocation(node);
			Daemon subTreeDaemon = new Daemon() {
            @SuppressWarnings("unchecked")
			@Override
            public void actionForEach(TreeNode cursor, Object reference) {
				SubTree<E> subTree = (SubTree<E>) reference;
				subTree.getAncestors().add((BinaryTreeNode<E>)cursor);
            }
			};
			if (type == 'L') {
				if (node.getLeft() != null) {
					postOrderForEach(node.getLeft(), subTreeDaemon, subTree);
				}
			} else if (node.getRight() != null) {
				postOrderForEach(node.getRight(), subTreeDaemon, subTree);
			}
			return subTree.getAncestors();
		}
		return null;
	}

	public boolean insertNode(BinaryTreeNode<E> node, char type,BinaryTreeNode<E> father) {
		boolean inserted = false;
		if (node != null) {
			if (type == 'R' && father == null) {
				if (isEmpty())
					setRoot(node);
				else {
					node.setLeft((BinaryTreeNode<E>)root);
					setRoot(node);
				}
				inserted = true;
			} 
			else {	
				Daemon isFatherDaemon = new Daemon() {
                  @Override
                public boolean actionFirstThat(TreeNode cursor, Object reference) {
							return cursor == reference;
	                }
				};
				TreeNode<E> existFather = preOrderFirstThat(root,
						isFatherDaemon, father);
				if (existFather != null) {
					if (type == 'L') {
						node.setLeft(father.getLeft());
						father.setLeft(node);
					} else {
						node.setRight(father.getRight());
						father.setRight(node);
					}
					inserted = true;
				}
			}
		}
		return inserted;
	}


	public int totalNodes() {
		NumberUtil count = new NumberUtil(0);
		Daemon countNodesDaemon = new Daemon() {
        @Override
        public void actionForEach(TreeNode cursor, Object reference) {
			NumberUtil count = (NumberUtil) reference;
			count.setNumber(count.getNumber() + 1);
        } 
		};
		preOrderForEach(root, countNodesDaemon, count);
		return count.getNumber();
	}

	@SuppressWarnings("unchecked")
	public E deleteGeneralNode(TreeNode<E> cursor) {
		BinaryGTreeNode <E> node = (BinaryGTreeNode <E>)cursor;		
		if (node != null) {
			Daemon prevDaemon = new Daemon() {
            @Override
            public boolean actionFirstThat(TreeNode cursor, Object reference) {
				if (cursor != null && reference != null) {
					return reference.equals(((BinaryTreeNode) cursor).getLeft())
							|| reference.equals(((BinaryTreeNode<E>) cursor).getRight());
				}
				return false;
            }
			};
			BinaryGTreeNode<E> prev = (BinaryGTreeNode<E>)preOrderFirstThat(root, prevDaemon, node);
			if (prev != null) {
				if (node.equals(prev.getLeft())) {
					prev.setLeft(node.getRight());
				} else {
					prev.setRight(node.getRight());
				}
			} else {
				setRoot(node.getRight());
			}
			return node.getInfo();
		}
		return null;
	}


	public BinaryGTreeNode<E> getGeneralFather(TreeNode<E> node) {
		if (node != null) {
			if (isEmpty() || root.equals(node)) {
				return null;
			}
			Daemon prevDaemon = new Daemon() {
            @SuppressWarnings("unchecked")
			@Override
            public boolean actionFirstThat(TreeNode cursor, Object reference) {
				if (cursor != null && reference != null) {
					return reference.equals(((BinaryTreeNode<E>) cursor).getLeft())
							|| reference.equals(((BinaryTreeNode<E>) cursor).getRight());
				}
				return false;
            }
			};
			BinaryGTreeNode<E> prev = (BinaryGTreeNode<E>)preOrderFirstThat(root, prevDaemon, node);
			if (prev != null) {
				if (node.equals(prev.getRight())) {
					return getGeneralFather(prev);
				}
			}
			return prev;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<TreeNode<E>> getGeneralLeaves() {
		if (!isEmpty()) {
			List<TreeNode<E>> leavesList = new ArrayList<TreeNode<E>>();
			Daemon leavesDaemon = new Daemon() {
            @Override
            public void actionForEach(TreeNode cursor, Object reference) {
				if (cursor != null) {
					if (((BinaryTreeNode<E>) cursor).getLeft() == null) {
						((List<BinaryTreeNode<E>>) reference).add((BinaryTreeNode<E>) cursor);
					}
				}
            }
			};
			preOrderForEach(root, leavesDaemon, leavesList);
			return leavesList;
		}
		return null;
	}

	
	public List<BinaryTreeNode<E>> getGeneralSons(BinaryTreeNode<E> node) {
		List<BinaryTreeNode<E>> sonsList = new ArrayList<BinaryTreeNode<E>>();
		if (node != null) {
			if (node.getLeft() != null) {
				sonsList.add(node.getLeft());
				if (node.getLeft().getRight() != null) {
					node = node.getLeft();
					while (node.getRight() != null) {
						sonsList.add(node.getRight());
						node = node.getRight();
					}
				}
				return sonsList;
			}
			return null;
		}
		return null;
	}

	public List<E> getGeneralSonsInfo(BinaryTreeNode<E> node) {
		List<E> sonsInfoList = new ArrayList<E>();
		if (node != null) {
			if (node.getLeft() != null) {
				sonsInfoList.add(node.getLeft().getInfo());
				if (node.getLeft().getRight() != null) {
					node = node.getLeft();
					while (node.getRight() != null) {
						sonsInfoList.add((E) node.getRight().getInfo());
						node = node.getRight();
					}
				}
				return sonsInfoList;
			}
			return null;
		}
		return null;
	}

	
	public boolean insertNode(TreeNode<E> nod,TreeNode<E> fath) {
		BinaryGTreeNode <E> node = (BinaryGTreeNode <E>)nod;
		BinaryGTreeNode <E> father = (BinaryGTreeNode <E>)fath;		
		if (isEmpty()) {
			if (father == null) {
				root = node;
				return true;
			} else {
				return false;
			}
		} else {
			if (father != null) {
				Daemon isThereDaemon = new Daemon() {
                @Override
                public boolean actionFirstThat(TreeNode cursor, Object reference) {
					if (cursor != null) {
						return cursor.equals(reference);
					}
					return false;
                } 
				};
				TreeNode<E> isFather = preOrderFirstThat(root,isThereDaemon,father);
				if (isFather != null) {
					BinaryTreeNode<E> cursor = father.getLeft();
					if (cursor == null) {
						father.setLeft(node);
					} else {
						while (cursor.getRight() != null) {
							cursor = cursor.getRight();
						}
						cursor.setRight(node);
					}
				}
				return isFather != null;
			} else {
				if (((BinaryTreeNode<E>) root).getRight() != null) {
					BinaryTreeNode<E> cursor = ((BinaryTreeNode<E>) root).getRight();
					while (cursor.getRight() != null) {
						cursor = cursor.getRight();

					}
					cursor.setRight(node);
				} else {
					((BinaryTreeNode<E>) root).setRight(node);
				}
				return true;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void postOrderForEach(TreeNode<E> cursor, Daemon daemon, Object reference) {
		postOrderForEach(cursor, daemon, reference);
		if (cursor != null) {
			postOrderForEach(((BinaryTreeNode)cursor).getLeft(), daemon, reference);
			postOrderForEach(((BinaryTreeNode)cursor).getRight(), daemon, reference);
			daemon.actionForEach((BinaryTreeNode)cursor, reference);
		}
	}

	@SuppressWarnings("unchecked")
	public void postOrderInvertedForEach(TreeNode<E> cursor, Daemon daemon, Object reference) {
		if (cursor != null) {
			daemon.actionForEach(cursor, reference);
			postOrderInvertedForEach(((BinaryTreeNode<E>) cursor).getRight(), daemon, reference);
			postOrderInvertedForEach(((BinaryTreeNode<E>) cursor).getLeft(), daemon, reference);
		}	
	}

	

	@SuppressWarnings("unchecked")
	public TreeNode<E> preOrderFirstThat(TreeNode<E> cursor, Daemon daemon, Object reference) {
		if (cursor != null) {
			if (daemon.actionFirstThat(cursor, reference)) {
				return ((cursor != null) ? cursor : null);
			} else {
				TreeNode<E> node = preOrderFirstThat(((BinaryTreeNode<E>)cursor).getLeft(),
						daemon, reference);
				if (node == null) {
					node = preOrderFirstThat(((BinaryTreeNode)cursor).getRight(), daemon,reference);
				}
				return node;
			}
		} else {
			return null;
		}

	}
	@SuppressWarnings("unchecked")
	public void preOrderForEach(TreeNode<E> cursor, Daemon daemon, Object reference) {
		if (cursor != null) {
			daemon.actionForEach(cursor, reference);
			preOrderForEach(((BinaryTreeNode<E>) cursor).getLeft(), daemon, reference);
			preOrderForEach(((BinaryTreeNode<E>) cursor).getRight(), daemon, reference);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void symmetricForEach(TreeNode<E> cursor, Daemon daemon, Object reference) {
		if (cursor != null) {
			symmetricForEach(((BinaryTreeNode<E>) cursor).getLeft(), daemon, reference);
			daemon.actionForEach(cursor, reference);
			symmetricForEach(((BinaryTreeNode<E>) cursor).getRight(), daemon, reference);
		}
	}

	public TreeNode<E> getRoot() {return root;}
	
	public PreorderIterator<E> preOrderIterator(){
		return new PreorderIterator<E>(this);
	}

	public SymmetricIterator<E> symmetricIterator(){
		return new SymmetricIterator<E>(this);
	}

	public PosOrderIterator<E> posOrderIterator(){
		return new PosOrderIterator<E>(this);
	}	

	public boolean insertFirstSonNode(BinaryTreeNode<E> node, BinaryTreeNode<E> father) {
		// TODO Auto-generated method stub
		return false;
	};
}

class SubTree<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BinaryTreeNode<T> location;

	private List<BinaryTreeNode<T>> ancestors;

	public SubTree() {
		this.location = null;
		this.ancestors = new ArrayList<BinaryTreeNode<T>>();
	}

	public SubTree(BinaryTreeNode<T> location, List<BinaryTreeNode<T>> ancestors) {
		this.location = location;
		this.ancestors = ancestors;
	}

	public BinaryTreeNode<T> getLocation() {
		return location;
	}

	public void setLocation(BinaryTreeNode<T> location) {
		this.location = location;
	}

	public List<BinaryTreeNode<T>> getAncestors() {
		return ancestors;
	}

	public void setAncestors(List<BinaryTreeNode<T>> ancestors) {
		this.ancestors = ancestors;
	}
}

class NumberUtil {
	private int number;

	public NumberUtil() {
	}

	public NumberUtil(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
