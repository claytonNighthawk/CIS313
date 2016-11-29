
public class TwoStackQueue<E> {
	private Stack<E> stack1;
	private Stack<E> stack2;
	
	public TwoStackQueue(){
		stack1 = new Stack<E>();
		stack2 = new Stack<E>(); //the "in order" stack
	}
	
	public void enqueue(E newData){
		stack1.push(newData);
	}
	
	public Node<E> dequeue(){
		if (stack2.isEmpty()){
			while (!stack1.isEmpty()){
				stack2.push(stack1.pop().getData());
			}
		}
		return stack2.pop();
	}
	
	public boolean isEmpty(){
		return stack1.isEmpty() && stack2.isEmpty();
	}
	
	public void printQueue(){
		//Only works if stack2 is empty currently
		if (stack2.isEmpty()){
			while (!stack1.isEmpty()){
				stack2.push(stack1.pop().getData());
			}
			stack2.printStack();
		} 
	}
}
