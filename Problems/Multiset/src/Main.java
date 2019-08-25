import java.util.*;

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

        int newVal = null != map.get(elem) ? map.getOrDefault(elem, 0) : 0;
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
        if(this.getMultiplicity(elem) == 0){
            this.map.remove(elem);
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
        int l = this.toSet().size();
        List<E> listSet = new ArrayList<>(this.toSet());
        for(int j=l-1; j>=0; j--){
            E e = listSet.get(j);
            if (!other.contains(e)){
                map.remove(e);
            }else{
                int thisVal = this.getMultiplicity(e);
                int oVal = other.getMultiplicity(e);
                map.put(e, Math.min(thisVal, oVal));
            }
        }
    }

    @Override
    public int getMultiplicity(E elem) {
        return null != map.get(elem) ? map.getOrDefault(elem, 0) : 0;
    }

    @Override
    public boolean contains(E elem) {
        return this.getMultiplicity(elem) > 0;
    }

    @Override
    public int numberOfUniqueElements() {
        int count =0;
        for(E o:this.toSet()){
            if( this.getMultiplicity(o) == 1){
                count++;
            }
        }
        return count<=this.size() ? count : 0;
    }

    @Override
    public int size() {
        int countSize =0;
        for(E o:this.toSet()){
            int currentVal = this.getMultiplicity(o);
            if(currentVal>0) {
                countSize += currentVal;
            }
        }
        return countSize;
    }

    @Override
    public Set<E> toSet() {
        List<E> listSet = new ArrayList<>(this.map.keySet());
        int l = listSet.size();
        for(int j=l-1; j>=0; j--){
            E e = listSet.get(j);
            if(this.getMultiplicity(e)==0){
                map.remove(e);
            }
        }

        return this.map.keySet();
    }

    @Override
    public String toString(){

        return this.map.entrySet().toString();
    }
}
public class Main {

    private static Random rand = new Random();

    public static void main(String[] args) {
       Multiset<Character> testMS = new HashMultiset<>();
       testMS.add('a');
       testMS.add('a');
       testMS.add('b');
       testMS.add('b');
       testMS.add('b');
       testMS.add('c');

        System.out.println("testMS: "+testMS);
        System.out.println("unique elements in testMS: "+testMS.numberOfUniqueElements());
        Multiset<Character> testMS2 = createRandomMultiset(6);
        System.out.println("testMS2: "+testMS2);
        System.out.println("unique elements in testMS2: "+testMS2.numberOfUniqueElements());
        Multiset<Character> testMS3 = createRandomMultiset(7);
        System.out.println("testMS3: "+testMS3);
        System.out.println("unique elements in testMS3: "+testMS3.numberOfUniqueElements());

        testMS2.intersect(testMS3);
        System.out.println("Intersection between testMS2 and testMS3: "+testMS2);
        System.out.println("testMS3 should not have changed: "+testMS3);
        System.out.println("unique elements in Intersection between testMS2 and testMS3: "+testMS2.numberOfUniqueElements());
/*
        Multiset<Character> testMS4 = testMS2;
        testMS4.union(testMS3);
        System.out.println("testMS4: "+testMS4);
        Multiset<Character> testMS5 = testMS2;
        testMS5.intersect(testMS3);
        System.out.println("testMS5: "+testMS5);
        System.out.println("unique elements in testMS4: "+testMS4.numberOfUniqueElements());
        System.out.println("unique elements in testMS5: "+testMS5.numberOfUniqueElements());
*/

    }

    private static Multiset<Character> createRandomMultiset(int n){
        Multiset<Character> randMS = new HashMultiset<>();
        for(int i=0; i<n; i++){
            randMS.add((char)(97+rand.nextInt(26)));
        }
        return randMS;
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