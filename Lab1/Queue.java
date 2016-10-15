
public class Queue<E> {
	private Node<E> head;
	private Node<E> tail;
	
	public Queue(){
		// We want to initialize our Queue to be empty
		// (ie) set head and tail to be null
		head = null;
		tail = null;
	}
	
	public void enqueue(E newData){
		// Create a new node whose data is newData and whose next node is null
		// Update head and tail
		// Hint: Think about what's different for the first node added to the Queue
		if (this.isEmpty()){
			head = new Node<E>(newData, tail);
			tail = head;
		}else {
			Node<E> newElement = new Node<E>(newData, null);
			tail.setNext(newElement);
			this.tail = newElement;
		}		
	}
	
	public Node<E> dequeue(){
		// Return the head of the Queue
		// Update head
		// Hint: The order you implement the above 2 tasks matters, so use a temporary node
	 	//	     to hold important information
		// Hint: Return null on a empty Queue
		Node<E> oldHead = head;
		head = head.getNext();
		return oldHead;		
	}
	
	public boolean isEmpty(){
		// Check if the Queue is empty
		return head == null;
	}
	
	public void printQueue(){
		// Loop through your queue and print each Node's data
		Node<E> x = this.head;
		System.out.print("[");
		while (true){
			System.out.print(x.getData() + ", ");
			if (x.getNext() != null){
				break;
			}
			x = x.getNext();
		}
		System.out.println("]");
	}
}
