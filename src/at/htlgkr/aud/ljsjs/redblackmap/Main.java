package at.htlgkr.aud.ljsjs.redblackmap;

public class Main {
    public static void main(String[] args){
        MyMap m = new MyMap();
        m.put("1", "one");
        m.put("2", "two");
        m.put("3", "three");
        m.put("4", "four");

        m.remove("3");

        for (String s : m.values()){
            System.out.println(s);
        }
    }

}
