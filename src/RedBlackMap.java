import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class RedBlackMap {

    private Entry root = null;
    private int size = 0;

    public void put(String key, String value) {

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
                        con = false;
                    } else {
                        r = r.left;

                    }


                } else {
                    if (r.key.compareTo(key) > 0) {
                        if (r.right == null) {

                            r.right = new Entry(key, value, r);
                            this.validate(r.right);
                            con = false;
                        } else {
                            r = r.right;
                            this.validate(r);
                        }


                    } else {
                        r.value = value;

                    }

                }
            }
        }
        size++;
    }

    private void handlePutCases(Entry e) {
        if (e.parent.black == false) {
            case1(e);
        }
        Entry onkl = null;
        if (e.parent.parent.left.equals(e.parent)) {
            onkl = e.parent.parent.right;

        } else {
            onkl = e.parent.parent.left;
        }
        if(e.parent.black == false){
            case2(e, onkl);
            handlePutCases(e);
            return;
        }

    }

    private void case1(Entry e) {
        if (e.parent == root) {
            case5(e);
        }

    }

    private void case2(Entry e, Entry onkl) {

                if(onkl.black == false){
                    case3(e);
                    return;
                }


                e.parent.parent.black = false;
                e.parent.black = false;
                onkl.black = false;


    }

    private void case3(Entry e) {
        Entry temp = null;
        if(e.parent.right.equals(e)){
            temp = e.parent.left;
            e.parent.left = e.parent;
            e.parent = e;
            e = temp;
            case4(e);
        }else{
            temp = e.parent.right;
            e.parent.right = e.parent;
            e.parent = e;
            e = temp;
            case4(e);
        }
    }

    private void case4(Entry e) {

    }

    private void case5(Entry e) {

    }


    private void validate(Entry e) {
        if (!e.parent.black) {

            e.black = true;
            return;
        }
        if ((!e.right.black || e.right != null) && (!e.left.black || e.left != null)) {
            e.black = true;
        }
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
                break;
            }
        }

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
                    }



        return oldValue;

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

//von Wiki abschreiben


    }



    public Set<String> keySet() {
        Set<String> set = new HashSet<>();
        Entry r = root;
        String s;
        set.add(r.key);
        while (set.size() != size()) {
            if (set.contains(r.left) && r.right != null) {
                r = r.right;
            } else if (set.contains(r) && (set.contains(r.left) || r.left == null) && (set.contains(r.right) || r.right == null)) {

                r = r.parent;
            } else {
                r = r.left;
            }
            if (!set.contains(r.key)) {
                set.add(r.key);
            }
        }
        return set;
    }


    public Collection<String> values() {
        Collection<String> values = new ArrayList<String>();
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

        /*Collection<String> values = new ArrayList<String>();
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
        return values; // TODO : change
    }

    public Set<Entry> entrySet() {
        Set<Entry> set = new HashSet<>();
        Entry r = root;
        set.add(root);
        while (set.size() != size()) {
            if (set.contains(r.left) && r.right != null) {
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
        Entry r = root;
        Set<String> set = new HashSet();
        while (set.size() != size()) {
            if (set.contains(r.left) && r.right != null) {
                r = r.right;
            } else if (set.contains(r) && (set.contains(r.left) || r.left == null) && (set.contains(r.right) || r.right == null)) {
                r = r.parent;
            } else {
                r = r.left;
            }
            if (!set.contains(r.key)) {
                set.add(r.key);
            } else {
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
            if (!(childLeft.black && childRight.black)) {
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


}
