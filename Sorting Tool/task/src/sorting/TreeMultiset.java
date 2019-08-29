package sorting;

import java.util.*;

public class TreeMultiset <E> implements Multiset<E> {


        private Map<E, Integer> map = new TreeMap<>();

        public Map<E, Integer> getMap(){
            return this.map;
        }

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
