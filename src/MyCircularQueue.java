class MyCircularQueue {

    public int rearIndex = -1;
    public int frontIndex = -1;
    public int[] elements;
    public int numElem = 0;
    public int size = 0;
    
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        elements = new int[k];
        size = k;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        //if empty queue
        if (numElem == 0) {
            frontIndex = 0;
            rearIndex = frontIndex;
            elements[0] = value;
            numElem++;
            return true;
        }
        //if queue is full
        else if (isFull()) {
            return false;
        }
        //queue is not full, add element as rear
        else {
            rearIndex = (frontIndex + numElem) % size;
            elements[rearIndex] = value;
            numElem++;
            return true;
        }

        
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        //remove front element
        if (isEmpty()) return false;
        else {     
            elements[frontIndex] = 0;
            frontIndex = (frontIndex + 1) % size;
            numElem--;
            return true;
        }
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        return isEmpty() ? -1 : elements[frontIndex];
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        return isEmpty() ? -1 : elements[rearIndex];
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return numElem == 0;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return numElem == size;
    }
    
    public static void main(String[] args) {
    	MyCircularQueue obj = new MyCircularQueue(3);
    	obj.enQueue(1);
    	System.out.println("Rear is " + obj.elements[obj.rearIndex] + " Front is " + obj.elements[obj.frontIndex]);

    	obj.enQueue(2);
    	System.out.println("Rear is " + obj.elements[obj.rearIndex] + " Front is " + obj.elements[obj.frontIndex]);

    	obj.deQueue();
    	System.out.println("Rear is " + obj.elements[obj.rearIndex] + " Front is " + obj.elements[obj.frontIndex]);

    	obj.enQueue(3);
    	System.out.println("Rear is " + obj.elements[obj.rearIndex] + " Front is " + obj.elements[obj.frontIndex]);

    	obj.enQueue(4);
    	System.out.println("Rear is " + obj.elements[obj.rearIndex] + " Front is " + obj.elements[obj.frontIndex]);

    	obj.deQueue();
    	System.out.println("Rear is " + obj.elements[obj.rearIndex] + " Front is " + obj.elements[obj.frontIndex]);

    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */