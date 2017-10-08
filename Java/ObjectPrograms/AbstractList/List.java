/* List.java
 * Sean Gordon
 * skgordon #1405355
 * CMPS 12M lab6
 * 5/15/15
 * Implementation of List ADT
 */

class List<T> implements ListInterface<T>{

    private class Node{
        int index;
        T item;
        Node next;

        Node(int x, T y){
            index = x;
            item = y;
            next = null;
        }
    }

    private Node head;
    private int numItems;

    public List(){
        head = null;
        numItems = 0;
    }

    // private helper functions

    private Node find(int index){
        Node N = head;
        for(int i=1; i<index; i++) N = N.next;
        return N;
    }

    // public ADT operations

    public boolean isEmpty(){
        return(numItems == 0);
    }

    public int size(){
        return numItems;
    }

    public T get(int index) throws ListIndexOutOfBoundsException{
        if(index<1 || index>numItems){
            throw new ListIndexOutOfBoundsException(
                "List Error: get() called on invalid index: "+index);
        }
        Node N = find(index);
        return N.item;
    }

    public void add(int index, T newItem) throws ListIndexOutOfBoundsException{
	if(index<1 || index>numItems+1){
            throw new ListIndexOutOfBoundsException(
                "List Error: add() called on invalid index: "+index);
        }
	if(index==1){
	    Node N = new Node(index, newItem);
	    N.next = head;
	    head = N;
            Node M = head;
            for(int i=1; i<=numItems+1; i++){
                M.index = i;
                M = M.next;
            }
	}else{
	    Node P = find(index-1);
	    Node C = P.next;
            P.next = new Node(index, newItem);
	    P = P.next;
	    P.next = C;
            Node M = head;
            for(int i=1; i<=numItems+1; i++){
                M.index = i;
                M = M.next;
            }
	}
	numItems++;
    }
	
    public void remove(int index) throws ListIndexOutOfBoundsException{
        if(index<1 || index>numItems){
            throw new ListIndexOutOfBoundsException(
                "List Error: remove() called on invalid index: "+index);
        }
	if(index==1){
	    Node N = head;
	    head = head.next;
            N.next = null;
            Node M = head;
            for(int i=1; i<numItems; i++){
                M.index = i;
                M = M.next;
            }
	}else{
	    Node P = find(index-1);
	    Node N = P.next;
	    P.next = N.next;
	    N.next = null;
            Node M = head;
            for(int i=1; i<numItems; i++){
                M.index = i;
                M = M.next;
            }
	}
        numItems--;
    }
	
    public void removeAll(){
        head = null;
        numItems = 0;
    }
	
    public String toString(){
	StringBuffer sb = new StringBuffer();
	Node N = head;
		
	for( ; N!=null; N=N.next) sb.append(N.item).append(" ");
	return new String(sb);
    }
	
    @SuppressWarnings("unchecked")
    public boolean equals(Object rhs){
	boolean eq = false;
	List<T> L = null;
	Node N = null;
	Node M = null;
		
	if(this.getClass() == rhs.getClass()){
	    L = (List<T>) rhs;
	    eq = (this.numItems == L.numItems);
			
	    N = this.head;
	    M = L.head;
	    while(eq && N!=null){
	        eq = (N.index == M.index && N.item.equals(M.item));
		N = N.next;
		M = M.next;
	    }
	}
	return eq;
    }
}
