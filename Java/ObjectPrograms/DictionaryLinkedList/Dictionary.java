/* Dictionary.java
 * Sean Gordon
 * skgordon #1405355
 * CMPS 12B pa3
 * 4/26/15
 * Dictionary ADT operations are done here
 */

public class Dictionary implements DictionaryInterface{

    private class Node{
        String key;
        String value;
        Node next;

        Node(String a, String b){
            key = a;
            value = b;
            next = null;
        }
    }

    private Node head;
    private int numItems;

    public Dictionary(){
        head = null;
        numItems = 0;
    }

    // private helper functions

    private Node findKey(String key){
        Node N = head;
        for(int i=1; i<=numItems && key.compareTo(N.key)!=0; i++) N = N.next;
        return N;
    }

    // ADT Operations
    
    public boolean isEmpty(){
        return(numItems == 0);
    }

    public int size(){
        return numItems;
    }

    public String lookup(String key){
        Node N = findKey(key);
        if(N == null) return null;
        else return N.value;
    }

    public void insert(String key, String value)
        throws KeyCollisionException{

        if(lookup(key) != null){
            throw new KeyCollisionException(
                "Dictionary Error: cannot insert duplicate keys");
        }
        if(head == null){
            Node N = new Node(key, value);
            head = N;
        }else{
            Node P = head;
            while(P.next != null) P = P.next;
            P.next = new Node(key, value);
        }
        numItems++;
    }

    public void delete(String key)
        throws KeyNotFoundException{

        if(lookup(key) == null){
            throw new KeyNotFoundException(
                "Dictionary Error: cannot delete non-existent key");
        }
        if(head == findKey(key)){
            Node N = head;
            head = head.next;
            N.next = null;
        }else{
            Node P = head;
            Node N = findKey(key);
            while(P.next != N) P = P.next;
            P.next = N.next;
            N.next = null;
        }
        numItems--;
    }

    public void makeEmpty(){
        head = null;
        numItems = 0;
    }

    public String toString(){
        Node N = head;
        StringBuffer str = new StringBuffer();
        for( ; N!=null; N=N.next){
            str.append(N.key).append(" ").append(N.value).append("\n");
        }
        return new String(str);
    }
}
