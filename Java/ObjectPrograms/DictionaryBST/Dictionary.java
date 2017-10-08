/* Dictionary.java
 * Sean Gordon
 * skgordon #1405355
 * CMPS 12M lab7
 * 5/25/15
 * BST implementation of Dictionary ADT in Java
 */

public class Dictionary implements DictionaryInterface{

   private class Node{
      String key;
      String value;
      Node left;
      Node right;

      Node(String a, String b){
         key = a;
         value = b;
         left = null;
         right = null;
      }
   }

   private Node root;
   private int numPairs;

   public Dictionary(){
      root = null;
      numPairs = 0;
   }

   // private helper functions

   private Node findKey(Node N, String key){
      if(N==null || key.compareTo(N.key)==0) return N;
      if(key.compareTo(N.key)<0) return findKey(N.left, key);
      else return findKey(N.right, key);
   }

   private Node findParent(Node N, Node R){
      Node P = null;
      if(N!=R){
         P = R;
         while(P.left!=N && P.right!=N){
            if((N.key).compareTo(P.key)<0) P = P.left;
            else P = P.right;
         }
      }
      return P;
   }

   private Node findLeftmost(Node R){
      if(R!=null) for( ; R.left!=null; R=R.left);
      return R;
   }

   private String printInOrder(Node R){
      StringBuilder bld = new StringBuilder();
      if(R==null) return "";
      bld.append(printInOrder(R.left));
      bld.append(R.key).append(" ").append(R.value).append("\n");
      return new String(bld.append(printInOrder(R.right)));
   }

   // public operations

   public boolean isEmpty(){
      return(numPairs == 0);
   }

   public int size(){
      return numPairs;
   }

   public String lookup(String key){
      Node N = findKey(root, key);
      if(N == null) return null;
      else return N.value;
   }

   public void insert(String k, String v)
      throws KeyCollisionException{

      if(findKey(root, k) != null){
         throw new KeyCollisionException(
            "Dictionary Error: cannot insert duplicate keys");
      }
      Node N = new Node(k, v);
      Node B = null;
      Node A = root;
      while(A!=null){
         B = A;
         if(k.compareTo(A.key)<0) A = A.left;
         else A = A.right;
      }
      if(B==null) root = N;
      else if(k.compareTo(B.key)<0) B.left = N;
      else B.right = N;
      numPairs++;
   }

   public void delete(String k)
      throws KeyNotFoundException{

      if(findKey(root, k)==null){
         throw new KeyNotFoundException(
            "Dictionary Error: cannot delete non-existent key");
      }
      Node N = findKey(root, k);
      Node P = null;
      Node S = null;
      if(N.left==null && N.right==null){
         if(N==root){
            root = null;
         }else{
            P = findParent(N, root);
            if(P.right==N) P.right = null;
            else P.left = null;
         }
      }else if(N.right==null){
         if(N==root){
            root = N.left;
         }else{
            P = findParent(N, root);
            if(P.right==N) P.right = N.left;
            else P.left = N.left;
         }
      }else if(N.left==null){
         if(N==root){
            root = N.right;
         }else{
            P = findParent(N, root);
            if(P.right==N) P.right=N.right;
            else P.left = N.right;
         }
      }else{
         S = findLeftmost(N.right);
         N.key = S.key;
         N.value = S.value;
         P = findParent(S, N);
         if(P.right==S) P.right = S.right;
         else P.left = S.right;
      }
      numPairs--;
   }

   public void makeEmpty(){
      root = null;
      numPairs = 0;
   }

   public String toString(){
      String s = printInOrder(root);
      return s;
   }
}
