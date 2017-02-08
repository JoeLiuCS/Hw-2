import java.util.EmptyStackException;
import java.util.Vector;

public class VectorStack<T> implements StackInterface<T> {

	private Vector<T> stack;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
	
	public VectorStack(){
		this(DEFAULT_CAPACITY);
	}
	
	
	public VectorStack(int initialCapacity) {
		checkCapacity(initialCapacity);
		stack = new Vector<>(initialCapacity);
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
		stack.add(newEntry);
	}

	@Override
	public T pop() {
		checkInitialization();
		if(isEmpty()){
			throw new EmptyStackException();
		}
		else{
			return stack.remove(stack.size()-1);
		}
	}

	@Override
	public T peek() {
		checkInitialization();
		if(isEmpty())
			throw new EmptyStackException();
		else
			return stack.lastElement();
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public void clear() {
		stack.clear();
	}

}
