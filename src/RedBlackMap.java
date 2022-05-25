import java.util.Collection;
import java.util.Set;
import java.util.HashSet;

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

    public void case1(Entry e){
        if(e.parent == root){
            case5(e);
        }
        
    }
    public void case2(Entry e){
        
    }
    public void case3(Entry e){

    }
    public void case4(Entry e){

    }
    public void case5(Entry e){

    }











    public void validate(Entry e) {
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

        if (r.left == null && r.right == null){
            
            r.parent
            
        }


        return oldValue;
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
            }
            else
            {
                r = r.left;
            }
            if (!set.contains(r.key)) {
                set.add(r.key);
            }
        }
        return set;
    }



        public Collection<String> values () {
            Collection<String> values;
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
            }
            return null; // TODO : change
        }

        public Set<Entry> entrySet () {
            Set<Entry> set = new HashSet<>();
            Entry r = root;
            set.add(root);
            while(set.size() != size()){
                if(set.contains(r.left) && r.right != null){
                    r = r.right;
                }else if(set.contains(r) && (set.contains(r.left) || r.left == null) && (set.contains(r.right) || r.right == null)){
                    r = r.parent;
                }else{
                    r = r.left;
                }

                if(!set.contains(r)){
                    set.add(r);
                }
            }
            return set;
        }

        public boolean containsKey (String key){
            Entry r = root;

            while (!(r == null)) {
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
            return false;
        }

        public boolean containsValue (String value){
            return values().contains(value);
        }

        public int size () {
            return size;
        }

        public boolean isEmpty () {
            if(root == null){
                return true;
            }
            return false;
        }

        public boolean valid() {

            Entry childLeft = root.left;
            Entry childRight = root.right;
            int countBlack = 0;

            if(root.black == false) {
                return false;
            }

            while(!childRight.black == true) {
                if(!(childLeft.black && childRight.black)) {
                    return false;
                }
                childLeft = childLeft.left;
                childRight = childRight.right;
            }

            childLeft = root.left;
            childRight = root.right;

            while(childLeft.black == true) {
                if(!(childLeft.value == null)) {
                    return false;
                }
                childLeft = childLeft.left;
            }

            while(childRight.black == true) {
                if(!(childRight.value == null)) {
                    return false;
                }
                childRight = childRight.left;
            }

            childLeft = root.left;
            childRight = root.right;

            while(childRight != null) {
                if(childRight.black == true) {
                    countBlack++;
                }
                childRight = childRight.right;
            }
            System.out.println("rechts" + countBlack);

            while(childLeft != null) {
                if()
            }

            return true;
        }


            public void rotateLeft(Entry centerNode){
                Entry oldParent = centerNode.parent;


                if (centerNode.left != null) {
                    System.out.println("Error");
                    return;
                }
                centerNode.left = oldParent;
                centerNode.parent = oldParent.parent;
                if (centerNode.parent.right.equals(oldParent));
                    centerNode.parent.right = centerNode;

                if (centerNode.parent.left.equals(oldParent));
                    centerNode.parent.left = centerNode;

                oldParent.parent = centerNode;
            }


    }
