public class MinHeap<E extends Comparable> {
    private E[] myArray;
    private int maxSize;
    private int length;

    
    public MinHeap(int size){
        // Build the constructor
        myArray  = (E[]) new Comparable[size+1];
        maxSize = size;
        length = 0;
        myArray[0] = null;

    }

    public E[] getArray(){
        return myArray;
    }

    public void setArray(E[] newArray){
        myArray = newArray;
    }

    public int getMaxSize(){
        return maxSize;
    }

    public void setMaxSize(int ms){
        maxSize = ms;
    }

    public int getLength(){
        return length;
    }

    public void setLength(int l){
        length = l;
    }

    // Other Methods
    public void insert(E data){
    	// Insert an element into your heap.
    	// When adding a node to your heap, remember that for every node n, 
    	// the value in n is less than or equal to the values of its children, 
    	// but your heap must also maintain the correct shape.
		// (ie there is at most one node with one child, and that child is the left child.)
		// (Additionally, that child is farthest left possible.)
        if (length + 1 <= maxSize){
            int current = length + 1;
            myArray[current] = data;
            length += 1;
            int parent = (int) Math.floor(current / 2);
            while (parent > 0){
                if (myArray[parent].compareTo(data) == 1){
                    swap(parent, current, myArray);
                    current = parent;
                    parent = (int) Math.floor(parent / 2);
                } else {
                    break;
                }
            }
        }
    }

    private void swap(int i, int j, E[] array){
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;   
    }

    public Comparable<E> findMin(){
        // return the minimum value in the heap
        return myArray[1];
    }

    public Comparable<E> extract(){
        // remove and return the minimum value in the heap
        Comparable<E> min = myArray[1];
        swap(1, length, myArray);
        myArray[length] = null;
        length -= 1;
        int minChild;
        int parent = 1; 
        while (myArray[parent * 2] != null || myArray[(parent * 2) + 1] != null) {
        	if (myArray[(parent * 2) + 1] == null){
        		minChild = parent * 2;
        	}
        	else if (myArray[parent * 2].compareTo(myArray[(parent * 2) + 1]) == 1){
                minChild = (parent * 2) + 1;
            } 
        	else {
                minChild = parent * 2;
            }

            if (myArray[parent].compareTo(minChild) == 1){
                swap(parent, minChild, myArray);
                parent = minChild;
            } else {
                break;
            }    
        }
		return min;
    }
}


