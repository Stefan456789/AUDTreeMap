package at.htlgkr.aud.ljsjs.redblackmap;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class Main {
    public static void main(String[] args){
        MyMap m = new MyMap();
        m.put("3", "three");

        Collection<String> s = m.values();
        System.out.println(s.contains("three"));
    }

}
