/* DictionaryTest.java
 * Sean Gordon
 * skgordon #1405355
 * CMPS 12B pa3
 * 4/26/15
 * Test file for Dictionary ADT
 */

class DictionaryTest{
    public static void main(String[] args){
        Dictionary D = new Dictionary();
        String v;

        System.out.println("==========Section 1==========");
        D.insert("1", "a");
        D.insert("2", "b");
        v = D.lookup("1");
        System.out.println("key = 1 "+(v==null?"not found":("value = "+v)));
        v = D.lookup("2");
        System.out.println("key = 2 "+(v==null?"not found":("value = "+v)));

        System.out.println("==========Section 2==========");
        System.out.println(D.isEmpty());
        System.out.println(D.size());
        D.insert("Beer", "blue moon");
        D.insert("Weed", "grape ape");
        D.insert("Dank", "bud");
        System.out.println(D.size());

        System.out.println("==========Section 3==========");
        D.delete("2");
        System.out.println(D.size());
        System.out.println();
        System.out.println(D);
        System.out.println("Lookup for key 'Weed': "+D.lookup("Weed"));
        System.out.println("Lookup for key '1': "+D.lookup("1"));

        System.out.println("==========Section 4==========");
        System.out.println(D.size());
        D.delete("1");
        System.out.println(D.size());

        System.out.println("==========Section 5==========");
        D.makeEmpty();
        System.out.println(D.isEmpty());
        System.out.println(D.size());
        D.insert("Country", "Capital");
        D.insert("USA", "DC");
        D.insert("France", "Paris");
        System.out.println(D.size());
        System.out.println();
        System.out.println(D);

        // These will produce errors
        System.out.println("==========Section 6==========");
        // D.insert("Country", "Spain");
        // D.delete("Good");
    }
} 
