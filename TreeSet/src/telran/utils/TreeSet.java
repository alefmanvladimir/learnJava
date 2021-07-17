package telran.utils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> implements SortedSet<T> {
private static class Node<T> {
	T obj;
	Node<T> left;
	Node<T> right;
	Node<T> parent;
	Node (T obj) {
		this.obj = obj;
	}
}
private class TreeSetIterator implements Iterator<T> {
Node<T> currentNode = getLeastFrom(root);
Node<T> prevNode;
	@Override
	public boolean hasNext() {
		
		return currentNode != null;
	}

	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		T res = currentNode.obj;
		prevNode = currentNode;
		updateCurrentNode();
		return res;
	}
	private void updateCurrentNode() {
		currentNode = currentNode.right != null ?
				getLeastFrom(currentNode.right) : getGreaterParent(currentNode);
		
	}

	private Node<T> getGreaterParent(Node<T> currentNode) {
		while(currentNode.parent != null &&
				currentNode.parent.left != currentNode) {
			currentNode = currentNode.parent;
		}
		return currentNode.parent;
	}

	@Override
	public void remove() {
		removeNode(prevNode);
	}
}
	private Node<T> root;
	private int size;
	private Comparator<T> comp;
	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}
	
	@SuppressWarnings("unchecked")
	public TreeSet() {
		this((Comparator<T>)Comparator.naturalOrder());
	}
	@Override
	public Iterator<T> iterator() {
		
		return new TreeSetIterator();
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
			Node<T> parent = getParent(obj);//returning null means the object exists
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
		Node<T> current = root;
		Node<T> parent = null;
		while(current != null) {
			parent = current;
			int compRes = comp.compare(obj, current.obj);
			if (compRes == 0) {
				return null;
			}
			current = compRes > 0 ? current.right : current.left;
		}
		return parent;
	}
	@Override
	public boolean remove(T pattern) {
		Node<T> node = getNode(pattern);
		boolean res = false;
		if (node != null) {
			removeNode(node);
			res = true;
		}
		return res;
	}

	private void removeNode(Node<T> node) {
		size--;
		if (isJunction(node)) {
			removeJunction(node);
		} else {
			removeNonJunction(node);
		}
		
	}
	private void removeNonJunction(Node<T> node) {
		Node<T> child = node.left != null ? node.left : node.right;
		Node<T> parent = node.parent;
		if (child != null) {
			child.parent = parent;
		}
		if (parent == null) {
			root = child;
			
		} else if (parent.left == node) {
			parent.left = child;
		} else parent.right = child;
		
		
	}
	private void removeJunction(Node<T> node) {
		Node<T> substitute = getLeastFrom(node.right);
		node.obj = substitute.obj;
		removeNonJunction(substitute);
		
	}
	private Node<T> getLeastFrom(Node<T> node) {
		while(node.left != null) {
			node = node.left;
		}
		return node;
	}
	private boolean isJunction(Node<T> node) {
		
		return node.left != null && node.right != null;
	}
	private Node<T> getNode(T pattern) {
		int res = 0;
		Node<T> current = root;
		while(current != null &&
				(res = comp.compare(pattern, current.obj)) != 0) {
			current = res < 0 ? current.left : current.right;
		}
		return current;
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
	
	private T getElement(T element, boolean isMax) {
		Node<T> current = root;
		T min = null;
		T max = null;
		int res = -1;
		while (current != null && res != 0) {
			res = comp.compare(current.obj, element);
			if (res <=  0) {
				min = current.obj;
			} else {
				max = current.obj;
			}
			current = res > 0 ? current.left : current.right;
		}
		if (res == 0) {
			max = min;
		}
		return isMax ? max : min;
	}
	
	public T ceiling(T element) {
		return getElement(element, true);
	}

//	@SuppressWarnings("unused")
//	@Override
//	public T ceiling(T element) {
//		Node<T> currentNode = root;
//		Node<T> prevNode = null;
//		T prevObj = null;
//		int compPrev = 0;
//		while(currentNode!=null) {
//			
//			if(prevObj!=null) {
//				compPrev = comp.compare(element, prevObj);
//			}
//			
//			int compRes = comp.compare(element, currentNode.obj);
//			
//			if(compRes==0) {
//				System.out.println("compRes==0");
//				return currentNode.obj;
//			}
//			
//			if(compPrev<0 && compRes<0) {
//				return prevObj;
//			}
//			
//			if (compPrev < 0 && compRes > 0 &&currentNode.right==null) {
//				System.out.println("current!=null");
//				return prevObj;
//			}
//			System.out.print("! ");
//			prevObj = currentNode.obj;
//			prevNode = currentNode;
//			currentNode = compRes < 0 ? currentNode.left : currentNode.right;
//			
//		}
//		
//		if(currentNode==null && prevNode!=null) {
//			System.out.println("current==null");
//			prevObj = comp.compare(element, prevNode.obj)>0?null:prevNode.obj;
//		}
//		return prevObj;
//	}

	@Override
	public T floor(T element) {
		Node<T> currentNode = root;
		T res = null;
		while(currentNode!=null || comp.compare(element, currentNode.obj)<0) {
			int compRes = comp.compare(element, currentNode.obj);
			res = currentNode.obj;
			currentNode = compRes < 0 ? currentNode.left : currentNode.right;
		}
		return res;
	}
	

	@Override
	public SortedSet<T> subSet(T fromElement, boolean fromInclusive, T toElement, boolean toInclusive) {
		// TODO Auto-generated method stub
		return null;
	}

}
