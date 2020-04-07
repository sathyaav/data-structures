/*DISJOINT-SET*/
import java.util.*;


public class UnionFind<E> {

    public ArrayList<HashSet<E>> sets = new ArrayList<HashSet<E>>();

    /*Returns the SET of elements identified by its set number*/
    public Set get(int set){
        return sets.get(set);
    }

    /*Takes two sets identified by their set number and moves the elements from the SMALLER set to the LARGER set. Returns
    the set number of the LARGER set. The SMALLER set becomes empty.*/
    public int union(int gr1, int gr2){
        if ( gr1==gr2 ) return gr1;

        if ( sets.get(gr1).size()>sets.get(gr2).size() ) return join(gr2,gr1);
        else return join(gr1,gr2);
    }

    private int join(int from, int to){
        sets.get(to).addAll( sets.get(from) );
        sets.get(from).clear();

        return to;
    }

    /*Creates a new SET and places element “e” into it. Returns the set number
    assigned to this set.*/
    public int add(E e){
        HashSet<E> set = new HashSet<E>();
        int set_num = sets.size();
        set.add(e);
        sets.add( set );

        return set_num;
    }

    /*Returns true if element e1 and e2 are in the same set. Otherwise returns
    false.*/
    public boolean find(E e1, E e2){
        Integer in_set = find( e1 );

        return in_set==null ? false : sets.get(in_set).contains(e2);
    }

    /* Returns the set number of where e1 is located in or returns NULL if e1 not
    found in any set.*/
    public Integer find(E e1){
        for( int i=0; i<sets.size(); i++ )
            if ( sets.get(i).contains(e1) ) return i;

        return null;
    }

    /*Returns the number of elements in the SET identified by its set number.*/
    public int count(int set){
        return sets.get(set).size();
    }
}
