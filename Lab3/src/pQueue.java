public class pQueue<E extends Comparable> {
    private MinHeap<E> myHeap;

    public pQueue (int size) {
        // Build the Constructor
        myHeap = new MinHeap<E>(size);
    }

    public void insert(E data){
        myHeap.insert(data);
    }

    public Comparable<E> findMin(){
        return myHeap.findMin();
    }

    public Comparable<E> removeMin(){
        return myHeap.extract();
    }

    public boolean isEmpty(){
        // Return true when the priority queue is empty
        // Hint: Do the actual printing in your HW3.java
        return myHeap.getLength() < 1;
    }

    public void print(){
        // print out "Current Queue: " 
        // followed by each element separated by a comma. 
        // Do not add spaces between your elements.
        E[] array = (E[]) myHeap.getArray();
        System.out.print("Current Queue: ");
        for (int i = 1; i < myHeap.getLength(); i++){
            System.out.print(array[i] + ",");
        }
        System.out.print(array[myHeap.getLength()]);
        System.out.print("\n");
    }
}

