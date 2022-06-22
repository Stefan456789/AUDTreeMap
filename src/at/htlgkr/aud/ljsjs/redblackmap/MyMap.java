package at.htlgkr.aud.ljsjs.redblackmap;

import java.util.*;

public class MyMap {

    private Entry root = null;
    private int size = 0;

    public String put(String key, String value) {


        String returnVal = null;



        if (root == null) {
            root = new Entry(key, value, null);
            root.black = true;
        } else {
            Entry r = root;
            boolean con = true;
            while (!(r == null) && con) {
                if (r.key.compareTo(key) < 0) {
                    if (r.left == null) {
                        r.left = new Entry(key, value, r);
                        this.validate(r.left);
                        handlePutCases(r.left);
                        con = false;
                    } else {
                        r = r.left;

                    }


                } else {
                    if (r.key.compareTo(key) > 0) {
                        if (r.right == null) {

                            r.right = new Entry(key, value, r);
                            this.validate(r.right);
                            handlePutCases(r.right);
                            con = false;
                        } else {
                            r = r.right;
                            this.validate(r);
                            handlePutCases(r);
                        }


                    } else {
                        returnVal = r.value;
                        r.value = value;
                        con = false;

                    }

                }
            }
        }
        size++;
        return returnVal;
    }

    private void handlePutCases(Entry e) {
       /* if (e.parent != null && e.parent.black == true) {
            case1(e);
        }
        Entry onkl = null;
        if (e.parent != null && e.parent.parent != null && e.parent.parent.left != null && e.parent.parent.left == e.parent) {
            onkl = e.parent.parent.right;

        } else if(e.parent != null && e.parent.parent != null) {
            onkl = e.parent.parent.left;
        }
        if(onkl != null && e.parent != null && e.parent.parent != null && e.parent.black == false && e.parent.parent.black == true && onkl.black == false){
            case2(e, onkl);
            handlePutCases(e);
            return;
        }

        if(e.parent != null && e.parent.parent != null && e.parent.black == false && e.parent.parent.black == true && (onkl == null || onkl.black == true)) {
            if (e.parent == e.parent.parent.right) {
                if(e == e.parent.right){
                    case4(e, onkl);
                }else{
                    case3(e, onkl);
                }

            }else if(e.parent != null && e.parent.parent != null && e == (e.parent.left)){
                    case4(e, onkl);
            }else if(e.parent != null && e.parent.parent != null){
                    case3(e,onkl);
                }

        }

        if(e.parent != null && e.parent.black == false && e.parent == root){
            case5(e);
        }

*/


    }

    private void case1(Entry e) {
        if (e.parent == root) {
            case5(e);


    }
    }

    private void case2(Entry e, Entry onkl) {

        e.parent.parent.black = false;
        e.parent.black = true;
        onkl.black = true;
        handlePutCases(e);

    }

    private void case3(Entry e, Entry onkl) {
        Entry temp = null;
        if(e.parent.parent.right == e.parent){
            temp = e.parent.left;
            e.parent.left = e.parent;
            e.parent = e;
            e = temp;
            case4(e, onkl);
        }else{
            temp = e.parent.right;
            e.parent.right = e.parent;
            e.parent = e;
            e = temp;
            case4(e, onkl);
        }
    }

    private void case4(Entry e, Entry onkl) {
        Entry temp = e.parent.parent;
        e.parent.parent = e.parent;

        if(e.parent.parent.right == e.parent){
            e.parent.parent.left = temp;
        }else{
            e.parent.parent.right = temp;
        }
        e.parent.black = true;

        temp.black = false;




    }

    private void case5(Entry e) {
        e.parent.black = true;
    }


    private void validate(Entry e) {
        /*
        if (!e.parent.black) {

            e.black = true;
            return;
        }
        if ((!e.right.black || e.right != null) && (!e.left.black || e.left != null)) {
            e.black = true;
        }*/

    }

    public String get(String key) {
        Entry r = root;
        boolean left = false;

        while (!(r == null)) {
            if (r.key.compareTo(key) < 0) {
                r = r.left;
            } else {
                if (r.key.compareTo(key) > 0) {
                    r = r.right;

                } else {
                    return r.value;
                }
            }
        }
        return null;
    }


    public String remove(String key) {
        //not finished
        Entry r = root;
        String oldValue = "";





        while (!(r == null)) {
            if (r.key.compareTo(key) < 0) {
                r = r.left;
            } else if (r.key.compareTo(key) > 0) {
                r = r.right;
            } else {
                if (r.parent == null)
                    root = null;
                else if (r.parent.left != null && r.parent.left.equals(r))
                    r.parent.left = null;
                else
                    r.parent.right = null;
                r.parent = null;
                break;
            }
        }

        if (r == null){
            return null;
        }

        Entry current = r;
        Entry parent = current.parent;


        oldValue = current.value;


        while (true){
            if (current.left != null && !current.equals(parent)){
                current = current.left;
            } else if (!current.equals(parent)){
                if (current.right != null){
                    current = current.right;
                }else{
                    current = current.parent;
                    if (current == null){
                        size--;
                        return oldValue;
                    }else{
                        if (current.left == null){
                            this.put(current.right.key, current.right.value);
                            current.right = null;
                        } else {
                            this.put(current.left.key, current.left.value);
                            current.left = null;
                        }
                    }

                }
            }else {
                /*if (current.parent == null){
                    root = null;
                }else if (current.parent.right.equals(r))
                    current.parent.right = null;
                else if (current.parent.left.equals(r))
                    current.parent.left = null;
                else continue;*/

                return oldValue;
            }
        }

        /*
        //Easy Case
        if (r.parent == null && r.left == null && r.right == null) {
            root = null;
        } else
            // Linker Knoten ist null, rechter nicht
            if (r.left == null && r.right != null) {

                //Laut Wiki ist das rechte zwangsläufig rot
                //Und wird somit auf schwarz gesetzt
                r.right.black = true;
                r.right.parent = r.parent;
                if (r.parent.left.equals(r))
                    r.parent.left = r.right;
                else if (r.parent.right.equals(r))
                    r.parent.right = r.right;
            } else
                // Rechter Knoten ist null, linker nicht
                if (r.right == null && r.left != null) {

                    //Laut Wiki ist das rechte zwangsläufig rot
                    //Und wird somit auf schwarz gesetzt
                    r.left.black = true;
                    r.left.parent = r.parent;
                    if (r.parent.left.equals(r))
                        r.parent.left = r.left;
                    else if (r.parent.right.equals(r))
                        r.parent.right = r.left;
                } else
                // Knoten ist nicht die Wurzel, hat kein Kind, ist schwarz (also ein schwarzes Blatt)
                    if (r.right == null && r.left == null && r.black && r.parent != null){
                        RBdelete2(r);
                    } else {

                    }


*/

    }

    private void RBdelete2(Entry n){

        Entry p = n.parent;
        Entry s;
        Entry c;
        Entry d;


        if (p.left.equals(n))
            s = p.right;
        else if (p.right.equals(n))
            s = p.left;

        if (p.parent.left.equals(p)){
            c = p.right.left;
            d = p.right.right;
        } else if (p.parent.right.equals(p)){
            c = p.left.right;
            d = p.right.right;
        }

        if(n.black && n.right == null && n.left == null){
            if (p.left.equals(n))
                p.left = null;
            else if (p.right.equals(n))
                p.right = null;
        }

        fixMyPain(p);


    }

    private void fixMyPain(Entry p){
        //von Wiki abschreiben
    }


    public Set<String> keySet() {
        Set<String> set = new HashSet<>();

        for(Entry e : entrySet()){
            set.add(e.key);
        }

        return set;
        /*
        set.add(r.key);
        while (true) {
            if ((set.contains(r.left) || r.left == null)&& r.right != null) {
                r = r.right;
            } else if (set.contains(r) && (set.contains(r.left) || r.left == null) && (set.contains(r.right) || r.right == null)) {

                r = r.parent;
            } else {
                r = r.left;
            }
            if (r.key != null && !set.contains(r.key)) {
                set.add(r.key);
            }
        }
        return set;

         */
    }


    public Collection<String> values() {
        Collection<String> values = new ArrayList<String>();



        for(Entry e : entrySet()){
            values.add(e.value);
        }

        return values;
/*
        Set<String> set = new HashSet<>();
        Entry r = root;
        values.add(r.value);
        while (values.size() != size()) {
            if ((values.contains(r.left) || r.left == null)&& r.right != null) {
                r = r.right;
            } else if (values.contains(r) && (values.contains(r.left) || r.left == null) && (values.contains(r.right) || r.right == null)) {

                r = r.parent;
            } else {
                r = r.left;
            }
            if (r.value != null && !values.contains(r.value)) {
                values.add(r.value);
            }
        }

 */


        /*Collection<String> values = new ArrayList<String>();
        Entry r = root;
        values.add(root.value);
        while (values.size() != size()) {
            if (values.contains(r.value) && r.left == null && r.right != null) {
                r = r.right;
            } else if (values.contains(r.value) && (values.contains(r.left.value) || r.left == null) && (values.contains(r.right) || r.right == null)) {
                r = r.parent;
            } else {
                r = r.left;
            }

            if (!values.contains(r.value)) {
                values.add(r.value);
            }
        }

        Collection<String> values = new ArrayList<String>();
            Entry r = root;
            values.add(root);
            while(values.size() != size()){
                if(values.contains(r.left) && r.right != null){
                    r = r.right;
                }else if(values.contains(r) && (values.contains(r.left) || r.left == null) && (values.contains(r.right) || r.right == null)){
                    r = r.parent;
                }else{
                    r = r.left;
                }

                if(!values.contains(r.value)){
                    values.add(r.value);
                }
            }*/
    }

    public Set<Entry> entrySet() {


        Set<Entry> set = new HashSet<>();
        if(root == null)
        {
            return set;
        }

        Entry r = root;
        set.add(root);
        while (set.size() != size()) {
            if ((set.contains(r.left) || r.left == null) && r.right != null) {
                r = r.right;
            } else if (set.contains(r) && (set.contains(r.left) || r.left == null) && (set.contains(r.right) || r.right == null)) {
                r = r.parent;
            } else {
                r = r.left;
            }

            if (!set.contains(r)) {
                set.add(r);
            }
        }
        return set;
    }

    public boolean containsKey(String key) {


        return keySet().contains(key);//muass echt nd sei, des woitn wir grod schreiben
        /*
        Entry r = root;
        Set<String> set = new HashSet();
        set.add(root.key);
        while (set.size() != size()) {
            if ((set.contains(r.left) || r.left == null) && r.right != null) {
                r = r.right;
            } else if (set.contains(r) && (set.contains(r.left) || r.left == null) && (set.contains(r.right) || r.right == null)) {
                r = r.parent;
            } else {
                r = r.left;
            }
            if (!r.key.equals(key))
            {
                set.add(r.key);
            }
            else {
                return true;
            }
        }
        return false;

            /*while (!(r == null)) {
                if (r.key.compareTo(key) < 0) {
                    r = r.left;
                } else {
                    if (r.key.compareTo(key) > 0) {
                        r = r.right;

                    } else {
                        return true;
                    }
                }
            }
            return false;*/
    }

    public boolean containsValue(String value) {
        return values().contains(value);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (root == null) {
            return true;
        }
        return false;
    }

    public boolean valid() {

        Entry childLeft = root.left;
        Entry childRight = root.right;
        int countBlack = 0;

        if (root.black == false) {
            return false;
        }

        while (!childRight.black == true) {
            if (!(childLeft.black && childRight.black)){
                return false;
            }
            childLeft = childLeft.left;
            childRight = childRight.right;
        }

        childLeft = root.left;
        childRight = root.right;

        while (childLeft.black == true) {
            if (!(childLeft.value == null)) {
                return false;
            }
            childLeft = childLeft.left;
        }

        while (childRight.black == true) {
            if (!(childRight.value == null)) {
                return false;
            }
            childRight = childRight.left;
        }

        Entry child = root.right;

        while (child != null && child.black == true) {
            countBlack++;
            System.out.println(countBlack);
            child = child.right;
        }

        child = child.parent.left;

        while (child != null && child.black == true) {
            countBlack++;
            System.out.println(countBlack);
            child = child.right;
        }



        return true;
    }


    public void rotateLeft(Entry centerNode) {
        Entry oldParent = centerNode.parent;


        if (centerNode.left != null) {
            System.out.println("Error");
            return;
        }
        centerNode.left = oldParent;
        centerNode.parent = oldParent.parent;
        if (centerNode.parent.right.equals(oldParent)) ;
        centerNode.parent.right = centerNode;

        if (centerNode.parent.left.equals(oldParent)) ;
        centerNode.parent.left = centerNode;

        oldParent.parent = centerNode;
    }

    static class Entry{

    public String key ;
    public String value ;

    public Entry left ;
    public Entry right ;
    public Entry parent ;

    public boolean black = false;


    Entry( String key , String value , Entry parent )
    {
        this.key = key ;
        this.value = value ;
        this.parent = parent ;
    }

    public boolean valid(){
        if (left.key.compareTo(this.key) > 0) return false;
        if (right.key.compareTo(this.key) < 0) return false;
        return true;
    }

    }
}
