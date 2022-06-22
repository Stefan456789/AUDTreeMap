package at.htlgkr.aud.ljsjs.redblackmap;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class Main {
    public static void main(String[] args){
        MyMap<String> m = new MyMap();
        m.put("3", "three");
        m.put("2", "two");
        m.put("1", "one");
        m.put("4", "four");
        m.put("5", "five");

        Collection<String> s = m.values();
        System.out.println(s.contains("three"));
    }

}
