/* QueueTest.java
 * Sean Gordon
 * skgordon #1405355
 * CMPS 12B pa4
 * 5/10/15
 * Test file for Queue ADT
 */

class QueueTest{
    public static void main(String[] args){
        //Start test operations here
        Queue Q = new Queue();

        System.out.println("\n==========Section 1==========");
        System.out.println("Q.isEmpty(): "+Q.isEmpty());
        System.out.println("Q.length(): "+Q.length());
        Q.enqueue(6);
        Q.enqueue(2);
        Q.enqueue(1);
        System.out.println("Q.isEmpty(): "+Q.isEmpty());
        System.out.println("Q.length(): "+Q.length());
        System.out.println("Q: "+Q);
        System.out.println("Q.dequeue(): "+Q.dequeue());
        System.out.println("Q.peek(): "+Q.peek());
        System.out.println("Q.length(): "+Q.length());
        System.out.println("Q: "+Q);

        System.out.println("\n==========Section 2==========");
        Q.dequeueAll();
        System.out.println("Q.isEmpty(): "+Q.isEmpty());
        System.out.println("Q.length(): "+Q.length());
        Q.enqueue("aa");
        Q.enqueue("bb");
        Q.enqueue("cc");
        Q.enqueue("dd");
        Q.enqueue("ee");
        System.out.println("Q.isEmpty(): "+Q.isEmpty());
        System.out.println("Q.length(): "+Q.length());
        System.out.println("Q: "+Q);
        System.out.println("Q.dequeue(): "+Q.dequeue());
        System.out.println("Q.dequeue(): "+Q.dequeue());
        System.out.println("Q.peek(): "+Q.peek());
        System.out.println("Q.length(): "+Q.length());
        System.out.println("Q: "+Q);
        Q.dequeueAll();

        System.out.println("\n==========Section 3==========");
        // This section causes errors but is checked with try catch blocks
        try{
            Q.dequeue();
        }catch(QueueEmptyException e){
            System.out.println(e);
            System.out.println("Continuing...");
        }
        try{
            Q.dequeueAll();
        }catch(QueueEmptyException e){
            System.out.println(e);
            System.out.println("Continuing...");
        }try{
            Q.peek();
        }catch(QueueEmptyException e){
            System.out.println(e);
            System.out.println("Continuing...");
        }
        System.out.println("\n==========Finished without error==========");
    }
}
