public class ArrayQueue<T> implements QueueInterface<T> {
	
	private T[] queue;
	private int frontIndex;
	private int backIndex;
    private boolean initialized = false;
    private static final int DEAFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;
    private void ensureCapacity(){
		if(frontIndex == ((backIndex +2)%queue.length)){
			T[] oldQueue = queue;
			int oldSize = oldQueue.length;
			int newSize = 2*oldSize;
			checkCapacity(newSize);
			
			@SuppressWarnings("unchecked")
			T[] tempQueue =  (T[]) new Object[newSize];
			queue = tempQueue;
			for(int i=0;i<oldSize-1;i++){
				queue[i]= oldQueue[frontIndex];
				frontIndex = (frontIndex +1) % oldSize;
			}
			frontIndex = 0;
			backIndex = oldSize -2;
		}
	}
    
    public ArrayQueue(){
    	this(DEAFAULT_CAPACITY);
    }

	public ArrayQueue(int initialCapacity) {
		checkCapacity(initialCapacity);
		
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Object[initialCapacity + 1];
		queue = tempQueue;
		frontIndex = 0;
		backIndex = initialCapacity;
		initialized = true;
	}

	public void checkCapacity(int capacity){
		if(capacity>MAX_CAPACITY)
			throw new SecurityException("The input is more than Max capacity");
	}
	
	
	public void checkInitialization() {
		if(!initialized)
			throw new SecurityException("ArragQueue object is not initialized" + "properly");
	}
	
	@Override
	public void enqueue(T newEntry) {
		checkInitialization();
		ensureCapacity();
		backIndex = (backIndex+1)%queue.length;
		queue[backIndex] = newEntry;
	}

	@Override
	public T dequeue() {
		checkInitialization();
		if(isEmpty()){
			throw new EmptyQueueException();
		}
		else{
			T front = queue[frontIndex];
			queue[frontIndex] = null;
			frontIndex = (frontIndex + 1)%queue.length;
			return front;
		}
	}

	@Override
	public T getFront() {
		checkInitialization();
		if(isEmpty()){
			throw new EmptyQueueException();
		}
		else{
			return queue[frontIndex];	
		}
	}

	@Override
	public boolean isEmpty() {
		return frontIndex == ((backIndex+1) % queue.length);
	}

	@Override
	public void clear() {
		while(!isEmpty()){
			dequeue();
		}
	}

}
