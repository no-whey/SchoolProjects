/* ListTest.java
 * Sean Gordon
 * skgordon #1405355
 * CMPS 12M lab6
 * 5/15/15
 * Test file for List ADT
 */

class ListTest{
    public static void main(String[] args){
        List<String> S = new List<String>();
        List<Double> D = new List<Double>();
        List<Integer> I = new List<Integer>();

        System.out.println("\n==========Section 1==========");
        S.add(1, "aa");
        S.add(2, "bb");
        S.add(3, "cc");
        S.add(4, "dd");
        S.add(5, "ee");

        D.add(1, 6.21);
        D.add(2, 7.32);
        D.add(3, 8.43);
        D.add(4, 9.54);

        I.add(1, 420);
        I.add(2, 531);
        I.add(3, 642);

        System.out.println("get() for S(4): "+S.get(4));
        System.out.println("get() for D(3): "+D.get(3));
        System.out.println("get() for I(2): "+I.get(2));
        System.out.println("size() S: "+S.size());
        System.out.println("size() D: "+D.size());
        System.out.println("size() I: "+I.size());
        System.out.println("is S empty?: "+S.isEmpty());
        System.out.println("is D empty?: "+D.isEmpty());
        System.out.println("is I empty?: "+I.isEmpty());
        System.out.println("toString() of S: ");
        System.out.println(S);
        System.out.println("toString() of D: ");
        System.out.println(D);
        System.out.println("toString() of I: ");
        System.out.println(I);

        System.out.println("\n==========Section 2==========");
        S.remove(2);
        S.remove(3);
        D.remove(2);
        D.remove(2);
        I.remove(2);
        try{
            System.out.println("get() for S(10): "+S.get(10));
        }catch(ListIndexOutOfBoundsException e){
            System.out.println("Caught Exception: ");
            System.out.println(e);
            System.out.println("Continuing without interuption");
        }
        System.out.println("size() S: "+S.size());
        System.out.println("size() D: "+D.size());
        System.out.println("size() I: "+I.size());
        System.out.println("toString() of S: ");
        System.out.println(S);
        System.out.println("toString() of D: ");
        System.out.println(D);
        System.out.println("toString() of I: ");
        System.out.println(I);

        System.out.println("\n==========Section 3==========");
        List<Integer> I2 = new List<Integer>();
        List<Integer> I3 = new List<Integer>();
        I2.add(1, 420);
        I2.add(2, 642);
        I3.add(1, 420);
        I3.add(2, 531);
        I3.add(3, 642);
        I3.remove(2);
        System.out.println("S.equals(S) : "+S.equals(S));
        System.out.println("S.equals(D) : "+S.equals(D));
        System.out.println("S.equals(I) : "+S.equals(I));
        System.out.println("D.equals(S) : "+D.equals(S));
        System.out.println("D.equals(D) : "+D.equals(D));
        System.out.println("D.equals(I) : "+D.equals(I));
        System.out.println();
        System.out.println("I.equals(I2) : "+I.equals(I2));
        System.out.println("I.equals(I3) : "+I.equals(I3));
        System.out.println("I2.equals(I3) : "+I2.equals(I3));

        System.out.println("\n==========Section 4==========");
        System.out.println("is S empty?: "+S.isEmpty());
        System.out.println("is D empty?: "+D.isEmpty());
        System.out.println("is I empty?: "+I.isEmpty());
        System.out.println("Making all Lists empty");
        S.removeAll();
        D.removeAll();
        I.removeAll();
        System.out.println("is S empty?: "+S.isEmpty());
        System.out.println("is D empty?: "+D.isEmpty());
        System.out.println("is I empty?: "+I.isEmpty());
        System.out.println("size() S: "+S.size());
        System.out.println("size() D: "+D.size());
        System.out.println("size() I: "+I.size());
        System.out.println("S: "+S);
        System.out.println("D: "+D);
        System.out.println("I: "+I);

        System.out.println("\n==========Section 5==========");
        S.add(1, "zz");
        S.add(1, "yy");
        S.add(1, "uu");
        S.add(2, "xx");
        S.add(2, "vv");
        S.add(3, "ww");
        D.add(1, 1.11);
        D.add(1, 2.22);
        I.add(1, 60);
        I.add(1, 59);
        System.out.println("S: "+S);
        System.out.println("D: "+D);
        System.out.println("I: "+I);
        System.out.println("S.get(1...6): "+S.get(1)+" "+S.get(2)+" "+S.get(3)+" "
                           +S.get(4)+" "+S.get(5)+" "+S.get(6));
        System.out.println("D.get(1...2): "+D.get(1)+" "+D.get(2));
        System.out.println("I.get(1...2): "+I.get(1)+" "+I.get(2));
        System.out.println("S.size(): "+S.size());
        System.out.println("D.size(): "+D.size());
        System.out.println("I.size(): "+I.size());

        // This section will cause errors if uncommented
        System.out.println("\n==========Section 6==========");
        // I2.add(20, 100);
        // I3.add(12, 200);
        // I2.add(20, 204);
    }
}
