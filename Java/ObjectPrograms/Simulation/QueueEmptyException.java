/* QueueEmptyException.java
 * Sean Gordon
 * skgordon #1405355
 * CMPS 12B pa4
 * 5/10/15
 * Holds the QueueEmptyException for the Queue ADT
 */

public class QueueEmptyException extends RuntimeException{
    public QueueEmptyException(String s){
        super(s);
    }
}
