public class TwoPartCircularLinkedQueue<T> implements QueueInterface<T> {

	private Node<T> queueNode;
	private Node<T> freeNode;
	private int size; //size <= 10
	private boolean isChainFull(){
		return queueNode == freeNode.getNextNode();
	}
	
	public TwoPartCircularLinkedQueue(){
		freeNode = new Node<T>(null,null);
		freeNode.setNextNode(freeNode);
		queueNode = freeNode;
		size = 0;
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
		if(isChainFull()&& size <11){
			Node<T> newNode = new Node<T>(null,freeNode.getNextNode());
			freeNode.setNextNode(newNode);
			size++;
		}
		freeNode = freeNode.getNextNode();
	}

	@Override
	public T dequeue() {
		T front = getFront();
		assert !isEmpty();
		queueNode.setData(null);
		queueNode = queueNode.getNextNode();
		size--;
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
