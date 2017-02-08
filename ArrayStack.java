import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayStack<T> implements StackInterface<T> {

	private T [] stack;
	private int topIndex;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10;
	private void ensureCapacity(){
		if(topIndex == stack.length -1){
			int newLength = 2*stack.length;
			checkCapacity(newLength);
			stack = Arrays.copyOf(stack, newLength);
		}
	}
	
	public ArrayStack(){
		this(DEFAULT_CAPACITY);
	}
	
	public ArrayStack(int initialCapacity) {
		checkCapacity(initialCapacity);
		
		@SuppressWarnings("unchecked")
		T[] tempStack = (T[]) new Object[initialCapacity];
		stack = tempStack;
		topIndex = -1;
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
	public void push(T newEntry) {
		checkInitialization();
		ensureCapacity();
		stack[topIndex +1]= newEntry;
		topIndex++;
		
	}
	
	

	@Override
	public T pop() {
		checkInitialization();
		if(isEmpty()){
			throw new EmptyStackException();
		}
		else{
			T top = stack[topIndex];
			stack[topIndex] = null;
			topIndex--;
			return top;
		}
	}

	@Override
	public T peek() {
		checkInitialization();
		if(isEmpty()){
			throw new EmptyStackException();
		}
		else{
			return stack[topIndex];
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return topIndex<0;
	}

	@Override
	public void clear() {
		while(!isEmpty()){
			pop();
		}
	}
       
}
