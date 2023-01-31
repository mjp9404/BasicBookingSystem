package utilities;

import exceptions.TreeException;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

import exceptions.TreeException;

/**
 * Binary search tree class implementing BSTreeADT and all of it's methods
 * @author Travis Milne
 * @version August 2, 2022
 */
public class BSTree<E> implements BSTreeADT,Serializable {
	
	/**
	 * The class version UID to ensure the backward compatibility 
	 * with the repository should the class specification change 
	 * with future enhancements.
	 */
    static final long serialVersionUID = 1L; //assign a long value for UID
    
	public BSTreeNode root;

	/**
	 * No args Binary Search Tree Constructor
	 */
	public BSTree() {
		this.root = null;
	}

	/**
	 * Method to return the Node at the root of the Binary Search Tree
	 * @return node stored at the root of tree is returned
	 * @throws TreeException if the root is null
	 */
	@Override
	public BSTreeNode getRoot() throws TreeException {
		if (root == null) {
			throw new TreeException();
		}
		return this.root;
	}
	
	/**
	 * Determines the height of the tree and returns that value as an integer
	 * @return integer value representing height of Binary Search Tree
	 */
	@Override
	public int getHeight() {
		if (root == null)
			return -1;

		Queue<BSTreeNode> q = new LinkedList();
		BSTreeNode curr = root;
		q.add(curr);
		int height = 0;

		while (!q.isEmpty()) {
			height++;
			int size = q.size();

			while (size > 0) {
				size--;
				curr = q.remove();
				if (curr.left != null) {
					q.add(curr.getLeft());
				}
				if (curr.right != null) {
					q.add(curr.getRight());
				}
			}
		}
		return height;
	}

	/**
	 * Determines the total number of Nodes currently stored in the Binary Search Tree
	 * @return integer value representing total number of nodes in Binary Search Tree
	 */
	@Override
	public int size() {
		if (root == null) {
			return 0;
		}

		Iterator it = preorderIterator();
		int size = 0;

		while (it.hasNext()) {
			it.next();
			size++;
		}
		return size;
	}

	/**
	 * Check to see if a Binary Search Tree is empty
	 * @return boolean return whether or not the Binary Search Tree is empty
	 */
	@Override
	public boolean isEmpty() {
		if (root == null) {
			return true;
		} else
			return false;
	}

	/**
	 * Clears all Nodes currently stored in Binary Search Tree
	 */
	@Override
	public void clear() {
		root = null;
	}

	/**
	 * Checks the Binary Search Tree to see if Data passed into the method is present
	 * @param entry Data to be searched for in the Binary Search Tree
	 * @return boolean return wether or not data is present in Binary Search Tree
	 */
	@Override
	public boolean contains(Comparable entry) throws TreeException {
		if (root == null) {
			throw new TreeException();
		}
		
		Iterator it = preorderIterator();
		while (it.hasNext()) {
			if (it.next() == entry) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Retrieves node from tree matching data passed into method 
	 * @param entry Data to be searched for in the Binary Search Tree
	 * @return returns the Node containing the data that was searched for
	 */
	@Override
	public BSTreeNode search(Comparable entry) throws TreeException {
		if (root == null) {
			throw new TreeException();
		}

		BSTreeNode curr = root;

		 while(curr != null) {
	            if(entry.compareTo(curr.getData()) < 0) {
	                curr = curr.getLeft();
	            }else if(entry.compareTo(curr.getData()) > 0) {
	                curr = curr.getRight();
	            }else {
	                return curr;
	            }
	        }
		return null;
	}

	/**
	 * Adds a new Node to the tree placing it in its proper sequential order
	 * @param newEntry the Node to be added to the Binary Search Tree
	 * @return Boolean returns true is Node was successfully added, false if it wasn't added
	 * @throws NullPointerException is thrown if the Node to be added is null
	 */
	@Override
	public boolean add(Comparable newEntry) throws NullPointerException {
		if (newEntry == null) {
			throw new NullPointerException();
		}

		BSTreeNode newNode = new BSTreeNode(newEntry);
		if (root == null) {
			root = newNode;
			return true;
		} else
			root.addNode(newNode);
		return true;
	}

	/**
	 * Constructs an In Order iterator to be used on current Binary Search Tree
	 * Uses InIterator internal class
	 * @return an iterator with the elements in the natural order
	 */
	@Override
	public Iterator inorderIterator() {
		InIterator it = new InIterator(root);
		return it;
	}

	/**
	 * Internal Class used to construct and manage In Order Iterator
	 */
	public class InIterator<E> implements Iterator<E> {
		Stack<BSTreeNode> stack = new Stack<BSTreeNode>();
		BSTreeNode curr;

		/**
		 * Constructs In Order Iterator and moves the pointer to the correct starting position
		 * @param root Root element of Binary Search Tree to be iterated
		 */
		public InIterator(BSTreeNode root) {
			curr = root;
			stack.push(curr);

			if (curr.hasLeftChild()) {
				while (curr.hasLeftChild()) {
					curr = curr.getLeft();
					stack.push(curr);
				}
			}
		}

		/**
		 * Checks the stack if there are any nodes remaining, 
		 * if not you have reached the end of the Binary Search Tree
		 */
		@Override
		public boolean hasNext() {
			return (!stack.isEmpty());
		}

		/**
		 * Moves the iterator to the next Node
		 * @return data of the Node it arrives at
		 * @throws NoSuchElementException if iterator is moved past the last node in the Binary Search Tree
		 */
		@Override
		public E next() throws NoSuchElementException {
			if (!hasNext())
				throw new NoSuchElementException();

			BSTreeNode temp = stack.pop();

			if (temp.hasRightChild()) {
				curr = temp.getRight();
				stack.push(curr);

				while (curr.hasLeftChild()) {
					curr = curr.getLeft();
					stack.push(curr);
				}
			}
			return (E) temp.getData();
		}
	}

	/**
	 * Constructs an Pre Order iterator to be used on current Binary Search Tree
	 * Uses PreIterator internal class
	 * @return an iterator with the elements, ordering them so root is last
	 */
	@Override
	public Iterator preorderIterator() {
		PreIterator it = new PreIterator(root);
		return it;
	}

	/**
	 * Internal Class used to construct and manage Pre Order Iterator
	 */
	public class PreIterator<E> implements Iterator {
		Stack<BSTreeNode> stack = new Stack<BSTreeNode>();
		BSTreeNode curr = root;

		/**
		 * Constructs Pre Order Iterator and places root element in the stack
		 * @param root Root element of Binary Search Tree to be iterated
		 */
		public PreIterator(BSTreeNode root) {
			stack.push(curr);
		}

		/**
		 * Checks the stack if there are any nodes remaining, 
		 * if not you have reached the end of the Binary Search Tree
		 */
		@Override
		public boolean hasNext() {
			return (!stack.isEmpty());
		}

		/**
		 * Moves the iterator to the next Node
		 * @return data of the Node it arrives at
		 * @throws NoSuchElementException if iterator is moved past the last node in the Binary Search Tree
		 */
		@Override
		public E next() throws NoSuchElementException {
			if (!hasNext())
				throw new NoSuchElementException();

			BSTreeNode curr = stack.pop();

			if (curr.hasRightChild()) {
				stack.push(curr.getRight());
			}
			if (curr.hasLeftChild()) {
				stack.push(curr.getLeft());
			}
			return (E) curr.getData();
		}
	}
	
	/**
	 * Constructs a Post Order iterator to be used on current Binary Search Tree
	 * Uses PostIterator internal class
	 * @return an iterator object with the root being the last object
	 */
	public Iterator postorderIterator() {
		PostIterator it = new PostIterator(root);
		return it;
	}

	/**
	 * Internal Class Used to Construct and manage Post Order Iterator
	 *
	 */
	public class PostIterator<E> implements Iterator { // left right root
        Stack<BSTreeNode> stack = new Stack<>();
        Queue<BSTreeNode> q = new LinkedList();
        BSTreeNode curr;
        BSTreeNode nextStackElement = null;

        /**
         * Post order iterator Constructor. 
         * Sets the initial pointer to the left most node on the lowest level of the Binary Search Tree
         * @param root Root of Binary Search tree to be iterated
         * @throws NullPointerException if root is null
         */
        public PostIterator(BSTreeNode root) {
            if (root == null) {
                throw new NullPointerException("Root element is null");
            } 
            curr = root;
            while (hasNext()) {
                if (curr != null) {
                    stack.push(curr);
                    curr = curr.getLeft();
                } else {
                    BSTreeNode node = stack.peek().getRight();
                    if (node == null) {
                        node = stack.pop();
                        q.add(node);
                        while (!stack.isEmpty() && node == stack.peek().getRight()) {
                            node = stack.pop();
                            q.add(node);
                        }
                    } else {
                        curr = node;
                    }
                }
            }
        }

        /**
		 * Checks the stack if there are any nodes remaining, 
		 * if not you have reached the end of the Binary Search Tree
		 */
        public boolean hasNext() {
            return (!stack.isEmpty() || curr != null);
        }

        /**
		 * Moves the iterator to the next Node
		 * @return data of the Node it arrives at
		 * @throws NoSuchElementException if iterator is moved past the last node in the Binary Search Tree
		 */
        @Override
        public E next() throws NoSuchElementException {
            if(q.isEmpty()) {
                throw new NoSuchElementException();
            }
            return (E) q.remove().getData();

        }
    }
}
