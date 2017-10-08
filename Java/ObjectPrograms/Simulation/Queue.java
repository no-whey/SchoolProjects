/* Queue.java
 * Sean Gordon
 * skgordon #1405355
 * CMPS 12B pa4
 * 5/10/15
 * Implementation of Queue ADT
 */

public class Queue implements QueueInterface{

    private class Node{
        Object item;
        Node next;

        Node(Object k){
            item = k;
            next = null;
        }
    }

    private Node head;
    private Node tail;
    private int numItems;

    public Queue(){
        head = null;
        tail = null;
        numItems = 0;
    }

    // ADT Operations

    public boolean isEmpty(){
        return(numItems == 0);
    }

    public int length(){
        return numItems;
    }

    public void enqueue(Object newItem){
        if(head == null){
            Node N = new Node(newItem);
            head = N;
            tail = N;
        }else{
            tail.next = new Node(newItem);
            tail = tail.next;
        }
        numItems++;
    }

    public Object dequeue()
        throws QueueEmptyException{
        if(isEmpty()){
            throw new QueueEmptyException(
                "Queue Error: cannot dequeue() an empty Queue");
        }
        Node N = head;
        head = head.next;
        numItems--;
        return N.item;
    }

    public Object peek()
        throws QueueEmptyException{
        if(isEmpty()){
            throw new QueueEmptyException(
                "Queue Error: cannot peek() an empty Queue");
        }
        return head.item;
    }

    public void dequeueAll()
        throws QueueEmptyException{
        if(isEmpty()){
            throw new QueueEmptyException(
                "Queue Error: cannot dequeueAll() on an empty Queue");
        }
        head = null;
        tail = null;
        numItems = 0;
    }

    public String toString(){
        Node N = head;
        String s = "";
        for( ; N != null; N = N.next){
            s += (N.item + " ");
        }
        return s;
    }
}
