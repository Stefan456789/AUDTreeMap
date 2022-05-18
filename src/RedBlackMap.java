import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

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

        while (!(r == null)) {
            if (r.key.compareTo(key) < 0) {
                r = r.left;
            } else {
                if (r.key.compareTo(key) > 0) {
                    r = r.right;
                } else {
                    String value = r.value;

                    return value;
                }
            }
        }
        return null;
    }

    public Set<String> keySet() {
        Set<String> set = new TreeSet<>();
        Entry r = root;
        String s;
        while (set.size() != size()) {
            if (set.contains(r.left) && r.right != null) {
                r = r.right;
                s = r.key;
            } else if (set.contains(r) && r.right == null && r.left == null) {
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

}

        public Collection<String> values () {

            // TODO : start programming here

            return null; // TODO : change
        }

        public Set<Entry> entrySet () {
            Set<Entry> set = new TreeSet<>();
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
            int countBlack;

            if(root.black == false) {
                return false;
            }

            while(!childLeft.black) {
                childLeft = childLeft.left;
                childRight = childRight.right;
                if(!(childLeft.black && childRight.black)) {
                    return false;
                }
            }

            childLeft = root.left;
            childRight = root.right;

            while(childLeft.black == true) {
                childLeft = childLeft.left;
                if(!(childLeft.value == null)) {
                    return false;
                }
            }

            while(childRight.black == true) {
                childRight = childRight.left;
                if(!(childRight.value == null)) {
                    return false;
                }
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

                if (centerNode.parent.left.equals(oldParent);
                    centerNode.parent.left = centerNode;

                oldParent.parent = centerNode;
            }


    }
