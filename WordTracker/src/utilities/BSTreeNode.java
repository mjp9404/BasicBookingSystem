package utilities;

import java.io.Serializable;

/**
 * BSTreeNode Class used to construct and manage Nodes to be used in BSTree Class
 * @author Travis Milne
 * @version August 2, 2022
 */
public class BSTreeNode<E extends Comparable<? super E>> implements Comparable<BSTreeNode<E>> ,Serializable {
	/**
	 * The class version UID to ensure the backward compatibility with 
	 * the repository should the class specification change with future 
	 * enhancements.
	 */
    static final long serialVersionUID = 1L; //assign a long value for UID

    /**
     * The data that is being hold in the Node
     */
	public E data;
	/**
	 * The left child of the BSTree
	 */
	public BSTreeNode left;
	/**
	 * The right child of the BSTree
	 */
	public BSTreeNode right;
	
	/**
	 * No arg Node Constructor
	 */
	public BSTreeNode() {
	}
	
	/**
	 * Node Constructor which builds the Node with assigned data
	 * @param data Data passed into method which Node will contain
	 */
	public BSTreeNode(E data) {
		super();
		this.data = data;
		this.left = null;
		this.right = null;
	}

	/**
	 * Getter method to retrieve data from specific Node
	 * @return value of the data within a Node
	 */
	public E getData() {
		return (E) data;
	}

	/**
	 * Setter method to assign Data to a Node
	 * @param data value of data to be placed within a Node
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * Getter Method to retrieve child Node on left side of specified node 
	 * @return returns child Node on left side 
	 */
	public BSTreeNode getLeft() {
		return left;
	}

	/**
	 * Setter Method to set child Node on left side of specified node 
	 * @param left child Node on left side 
	 */
	public void setLeft(BSTreeNode left) {
		this.left = left;
	}

	/**
	 * Getter Method to retrieve child Node on right side of specified node 
	 * @return returns child Node on right side 
	 */
	public BSTreeNode getRight() {
		return right;
	}

	/**
	 * Setter Method to set child Node on right side of specified node 
	 * @param right child Node on right side 
	 */
	public void setRight(BSTreeNode right) {
		this.right = right;
	}
	
	/**
	 *  Utility method used in add method. 
	 *  Determines if Node should be placed on left or right side of current Node
	 * @param newNode Node to be placed in tree
	 */
	public void addNode(BSTreeNode newNode) {
		int comp = newNode.compareTo(this);
		if(comp < 0) {
			if(this.left == null) {
				left= newNode;
			} else {
				left.addNode(newNode);
			}
		} else if (comp > 0 ) {
			if(right == null) {
				right = newNode;
			} else {
				right.addNode(newNode);
			}
		}
	}
	
	/**
	 * Utility method used to check if current node has a left child
	 * @return boolean return. Is true if Node has a child. Is false if no child
	 */
	public boolean hasLeftChild() {
		return left != null;
	}
	
	/**
	 * Utility method used to check if current node has a right child
	 * @return boolean return. Is true if Node has a child. Is false if no child
	 */
	public boolean hasRightChild() {
		return right != null;
	}

	/**
	 * Checks whether the Node is a leaf Node or not
	 * 
	 * @return Boolean if the tree isEmpty or not
	 */
	public boolean isLeaf() {
		if(this.left == null && this.right ==null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * compareTo method from comparable interface
	 * Compares the data of two nodes
	 */
	@Override
	public int compareTo(BSTreeNode<E> that) {
		return this.data.compareTo(that.getData());
	}
}
	
