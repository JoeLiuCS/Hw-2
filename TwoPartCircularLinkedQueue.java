public class TwoPartCircularLinkedQueue<T> implements QueueInterface<T> {

	private Node<T> queueNode;
	private Node<T> freeNode;
	private boolean isChainFull(){
		return queueNode == freeNode.getNextNode();
	}
	
	public TwoPartCircularLinkedQueue(){
		freeNode = new Node<T>(null,null);
		freeNode.setNextNode(freeNode);
		queueNode = freeNode;
	}
	
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
	
	@Override
	public void enqueue(T newEntry) {
		freeNode.setData(newEntry);
		if(isChainFull()){
			Node<T> newNode = new Node<T>(null,freeNode.getNextNode());
			freeNode.setNextNode(newNode);
		}
		freeNode = freeNode.getNextNode();
	}

	@Override
	public T dequeue() {
		T front = getFront();
		assert !isEmpty();
		queueNode.setData(null);
		queueNode = queueNode.getNextNode();
		return front;
	}

	@Override
	public T getFront() {
		if(isEmpty()){
			throw new EmptyQueueException();
		}
		else{
			return queueNode.getData();
		}
	}

	@Override
	public boolean isEmpty() {
		return queueNode == freeNode;
	}

	@Override
	public void clear() {
		while(!isEmpty()){
			dequeue();
		}
		
	}

}
