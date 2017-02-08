public class LinkedQueue<T> implements QueueInterface<T> {

	private Node<T> firstNode;
	private Node<T> lastNode;

    
	/*
	 * the Inner class ,member of the LinkedBag
	 */
	@SuppressWarnings("hiding")
	private class Node<T>{
		private T data;
		private Node<T> next;
		
		private Node (T dataPortion){
			this(dataPortion,null);
		}
		private Node(T dataPortion, Node<T> nextNode){
			data = dataPortion;
			next = nextNode;
		}
		
		public T getData(){
			return data;
		}
		
		public void setData(T newData){
			data = newData;
		}
		
		public Node<T> getNextNode(){
			return next;
		}
		
		public void setNextNode(Node<T> nextNode){
			next = nextNode;
		}
	}
	
	public LinkedQueue(){
		firstNode = null;
		lastNode = null;
	}
	
	@Override
	public void enqueue(T newEntry) {
		Node<T> newNode = new Node<T>(newEntry,null);
		if(isEmpty())
			firstNode = newNode;
		else
			lastNode.setNextNode(newNode);
		lastNode = newNode;
	}

	@Override
	public T dequeue() {
		T front = getFront();
		assert firstNode != null;
		firstNode.setData(null);
		firstNode = firstNode.getNextNode();
		if(firstNode == null){
			lastNode = null;
		}
		return front;
	}
	
	@Override
	public T getFront() {
		if(isEmpty())
			throw new EmptyQueueException();
		else
			return firstNode.getData();
	}

	
	@Override
	public boolean isEmpty() {
		return (firstNode == null)&&(lastNode == null);
	}

	@Override
	public void clear() {
		firstNode = null;
		lastNode = null;
	}

}
