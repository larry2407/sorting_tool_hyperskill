import java.util.HashMap;
import java.util.Map;
import java.util.Set;

interface Multiset<E> {

    /**
     * Add an element to the multiset.
     * It increases the multiplicity of the element by 1.
     */
    void add(E elem);

    /**
     * Remove an element from the multiset.
     * It decreases the multiplicity of the element by 1.
     */
    void remove(E elem);

    /**
     * Union this multiset with another one. The result is the modified multiset (this).
     * It will contain all elements that are present in at least one of the initial multisets.
     * The multiplicity of each element is equal to the maximum multiplicity of
     * the corresponding elements in both multisets.
     */
    void union(Multiset<E> other);

    /**
     * Intersect this multiset with another one. The result is the modified multiset (this).
     * It will contain all elements that are present in the both multisets.
     * The multiplicity of each element is equal to the minimum multiplicity of
     * the corresponding elements in the intersecting multisets.
     */
    void intersect(Multiset<E> other);

    /**
     * Returns multiplicity of the given element.
     * If the set doesn't contain it, the multiplicity is 0
     */
    int getMultiplicity(E elem);

    /**
     * Check the multiset contains an element,
     * i.e. the multiplicity > 0
     */
    boolean contains(E elem);

    /**
     * The number of unique elements
     */
    int numberOfUniqueElements();

    /**
     * The size of the multiset, including repeated elements
     */
    int size();

    /**
     * The set of unique elements (without repeating)
     */
    Set<E> toSet();
}

class HashMultiset<E> implements Multiset<E> {

    private Map<E, Integer> map = new HashMap<>();

    @Override
    public void add(E elem) {
        int newVal = map.getOrDefault(elem, 0);
        //if(newVal>1){
        map.put(elem, newVal+1);
        //}
    }

    @Override
    public void remove(E elem) {
        if(this.contains(elem)){
            int currentVal=map.get(elem);
            map.put(elem, currentVal-1);
        }
    }

    @Override
    public void union(Multiset<E> other) {
        for(E o: other.toSet()){
            int oVal = other.getMultiplicity(o);
            if(this.contains(o)){
                int thisVal = this.getMultiplicity(o);
                map.put(o, Math.max(thisVal, oVal));
            }else{
                map.put(o, oVal);
            }
        }
    }

    @Override
    public void intersect(Multiset<E> other) {
        for(E o: other.toSet()){
            if(this.contains(o)){
                int thisVal = this.getMultiplicity(o);
                int oVal = other.getMultiplicity(o);
                map.put(o, Math.min(thisVal, oVal));
            }
        }
        for(E e: this.toSet()){
            if(!other.contains(e)){
                map.remove(e);
            }
        }
    }

    @Override
    public int getMultiplicity(E elem) {
        // implement the method
        return map.getOrDefault(elem, 0);
    }

    @Override
    public boolean contains(E elem) {
        return map.containsKey(elem) &&  map.getOrDefault(elem, 0) > 0;
    }

    @Override
    public int numberOfUniqueElements() {
        int count =0;
        for(E o:this.toSet()){
            int currentVal = map.getOrDefault(o, 0);
            if(currentVal == 1){
                count++;
            }
        }
        return count;
    }

    @Override
    public int size() {
        int countSize =0;
        for(E o:this.toSet()){
            int currentVal = map.getOrDefault(o, 0);
            if(currentVal>0) {
                countSize += currentVal;
            }
        }
        return countSize;
    }

    @Override
    public Set<E> toSet() {
        return map.keySet();
    }
}
/* Do not modify code below */
public class Main {
    public static void main(String[] args) {
       Multiset<Character> testMS = new HashMultiset<>();
       testMS.add('a');
       testMS.add('a');
       testMS.add('b');
       testMS.add('b');
       testMS.add('b');
       testMS.add('c');
       testMS.remove('c');
        testMS.add('c');
        System.out.println("the size is:"+testMS.size());
        System.out.println("the number of unique elements is:"+testMS.numberOfUniqueElements());
        for(Character c: testMS.toSet()){
            //System.out.println(testMS.getMultiplicity(c));
            System.out.println(testMS.contains(c));
            //System.out.println(testMS.contains('d'));
        }

    }
}

/*

//class HashMultiset<E> implements Multiset<E> {

    private Map<E, Integer> map = new HashMap<>();

    @Override
    public void add(E elem) {
        int newVal = map.getOrDefault(elem, 0);
        //if(newVal>1){
        map.put(elem, newVal+1);
        //}
    }

    @Override
    public void remove(E elem) {
        if(this.contains(elem)){
            int currentVal=map.get(elem);
            map.put(elem, currentVal-1);
        }
        this.cleanThis();
    }

    @Override
    public void union(Multiset<E> other) {
        for(E o: other.toSet()){
            int oVal = other.getMultiplicity(o);
            if(this.contains(o)){
                int thisVal = this.getMultiplicity(o);
                map.put(o, Math.max(thisVal, oVal));
            }else{
                map.put(o, oVal);
            }
        }
        this.cleanThis();
    }

    @Override
    public void intersect(Multiset<E> other) {
        for(E e: this.toSet()){
            if(!other.contains(e)){
                map.remove(e);
            }else{
                int thisVal = this.getMultiplicity(e);
                int oVal = other.getMultiplicity(e);
                if(thisVal > 0){
                    map.put(e, Math.min(thisVal, oVal));
                }else{
                    map.remove(e);
                }
            }
        }
        this.cleanThis();
    }

    @Override
    public int getMultiplicity(E elem) {
        return this.contains(elem) ? map.get(elem) : 0;
    }

    @Override
    public boolean contains(E elem) {
        return map.containsKey(elem) &&  map.getOrDefault(elem, 0) > 0;
    }

    @Override
    public int numberOfUniqueElements() {
        this.cleanThis();
        int count =this.toSet().size();
        if(count <= 0){
            return -1;
        }
        for(E o : this.toSet()){
            int currentVal = map.getOrDefault(o, 0);
            if(currentVal > 1 || currentVal <= 0){
                count--;
            }
        }
        return count;
    }

    @Override
    public int size() {
        int countSize =0;
        int count =this.toSet().size();
        if(count <= 0){
            return count;
        }
        for(E o:this.toSet()){
            int currentVal = map.getOrDefault(o, 0);
            if(currentVal>0) {
                countSize += currentVal;
            }
        }
        return countSize;
    }

    @Override
    public Set<E> toSet() {
        return map.keySet();
    }

    public void cleanThis() {
        for(E o : this.toSet()){
            if(map.containsKey(o) && map.getOrDefault(o, 0) == 0){
                map.remove(o);
            }
        }
    }
}

 */