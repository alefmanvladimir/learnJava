package telran.utils;

import java.util.Comparator;
import java.util.Iterator;

public class TreeSet<T> implements Set<T> {

	static private class Node<T> {
		T obj;
		Node<T> left;
		Node<T> right;
		Node<T> parent;

		Node(T obj) {
			this.obj = obj;
		}
	}

	private int size;
	private Node<T> root;
	private Comparator<T> comp;

	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}

	@SuppressWarnings("unchecked")
	public TreeSet() {
		this((Comparator<T>) Comparator.naturalOrder());
	}

	private class TreeIterator implements Iterator<T> {
		Node<T> currentNode = getLess(root);
		Node<T> prevNode;
		int count = 0;

		@Override
		public boolean hasNext() {
			return count<size;
		}

		@Override
		public T next() {
			prevNode = currentNode;

			if (currentNode.right != null) {
				currentNode = getLess(currentNode.right);
				count++;
				
			} else {
				Node<T> parent = currentNode.parent;
				int compRes = comp.compare(parent.obj, currentNode.obj);
				if (compRes > 0) {
					// from left
					currentNode = currentNode.parent;
					count++;
				} else {
					// from right
					currentNode = currentNode.parent.parent;
					count++;
				}
			}
			
			return prevNode.obj;
		}

		public void remove() {
			TreeSet.this.remove(prevNode.obj);
			count--;
		}

	}

	@Override
	public Iterator<T> iterator() {
		return new TreeIterator();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean add(T obj) {
		if (root == null) {
			root = new Node<>(obj);
		} else {
			Node<T> parent = getParent(obj);
			if (parent == null) {
				return false;
			}

			Node<T> node = new Node<>(obj);

			if (comp.compare(obj, parent.obj) > 0) {
				parent.right = node;
			} else {
				parent.left = node;
			}

			node.parent = parent;

		}

		size++;

		return true;
	}

	private Node<T> getParent(T obj) {
		Node<T> currentNode = root;
		Node<T> parent = null;
		while (currentNode != null) {
			parent = currentNode;
			int compRes = comp.compare(obj, currentNode.obj);
			if (compRes == 0) {
				return null;
			}

			currentNode = compRes > 0 ? currentNode.right : currentNode.left;

		}
		return parent;
	}

	@Override
	public boolean remove(T pattern) {
		Node<T> node = getNode(pattern);
		
		if(node==null) {
			return false;
		}
		
		if (node.right == null && node.left == null) {
			removeEnd(node);
			return true;
		}

		if (node.right == null ^ node.left == null) {
			removeOneLeg(node);
			return true;
		}

		if (node.right != null && node.left != null) {
			Node<T> nodeLess = getLess(node.right);
			Node<T> parent = nodeLess.parent;
			node.obj = nodeLess.obj;

			nodeLess = nodeLess.right;
			if (nodeLess != null) {
				nodeLess.parent = parent;
			}

			size--;
			return true;
		}
		return false;
	}

	private void removeEnd(Node<T> node) {
		int compRes = comp.compare(node.obj, node.parent.obj);
		if (compRes > 0) {
			node.parent.right = null;
		} else {
			node.parent.left = null;
		}
		node = null;
		size--;
	}

	private void removeOneLeg(Node<T> node) {		
		if (node != root) {
			Node<T> parent = node.parent;
			node = node.right != null ? node.right : node.left;
			node.parent = parent;

			if (parent != null) {
				int compRes = comp.compare(node.obj, parent.obj);
				if (compRes > 0) {
					parent.right = node;
				} else {
					parent.left = node;
				}
			}

		} else {
			root = node.right != null ? node.right : node.left;
		}

		size--;
	}

	private Node<T> getLess(Node<T> node) {
		Node<T> currentNode = node;
		while (currentNode.left != null) {
			currentNode = currentNode.left;
		}
		return currentNode;
	}

	private Node<T> getNode(T pattern) {
		Node<T> currentNode = root;
		while (currentNode != null) {
			int compRes = comp.compare(pattern, currentNode.obj);
			if (compRes == 0) {
				return currentNode;
			}
			currentNode = compRes > 0 ? currentNode.right : currentNode.left;
		}

		return currentNode;
	}

	@Override
	public boolean contains(T pattern) {

		return root != null && getParent(pattern) == null;
	}

	@Override
	public void clean() {
		root = null;
		size = 0;
	}

}
