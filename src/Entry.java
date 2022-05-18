public class Entry {
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