package ciic4020.lab1.set;


import java.util.Iterator;

public class DynamicSet<E> implements Set<E> {

	// static set
	private StaticSet<E> theSet;

	// current max capacity
	private int maxCapacity;

	private static final int DEFAULT_SET_SIZE = 10;

	public DynamicSet(int initialCapacity) {
		this.theSet = new StaticSet<E>(initialCapacity);
		this.maxCapacity = initialCapacity;
	}
	@Override
	public Iterator<E> iterator() {
		return theSet.iterator();
	}

	@Override
	public boolean add(E obj) {
		if (this.isMember(obj)) // Avoid extending the set unnecessarily
			return false;
		if (this.maxCapacity == this.theSet.size())
		{
			this.maxCapacity *= 2;
			StaticSet<E> newSet = new StaticSet<E>(this.maxCapacity);
			copySet(theSet, newSet);
			this.theSet = newSet;
		}
		return this.theSet.add(obj);
	}

	private void copySet(Set<E> src, Set<E> dst) {
		for (Object obj : src)
			dst.add((E) obj);
	}

	@Override
	public boolean isMember(E obj) {
		return this.theSet.isMember(obj);
	}

	@Override
	public boolean remove(E obj) {
		return this.theSet.remove(obj);
	}

	@Override
	public boolean isEmpty() {
		return this.theSet.isEmpty();
	}

	@Override
	public int size() {
		return this.theSet.size();
	}

	@Override
	public void clear() {
		this.theSet.clear();
	}

	@Override
	public Set<E> union(Set<E> S2) {
		Set<E> temp = this.theSet.union(S2);
		DynamicSet<E> result = new DynamicSet<E>(DEFAULT_SET_SIZE);
		copySet(temp, result);
		return result;
	}

	@Override
	public Set<E> difference(Set<E> S2) {
		Set<E> temp = this.theSet.difference(S2);
		DynamicSet<E> result = new DynamicSet<E>(DEFAULT_SET_SIZE);
		copySet(temp, result);
		return result;
	}

	@Override
	public Set<E> intersection(Set<E> S2) {
		Set<E> temp = this.theSet.intersection(S2);
		DynamicSet<E> result = new DynamicSet<E>(DEFAULT_SET_SIZE);
		copySet(temp, result);
		return result;
	}

	@Override
	public boolean isSubSet(Set<E> S2) {
		return this.theSet.isSubSet(S2);
	}
	@Override
	public boolean equals(Set<E> S2) {
		// TODO Auto-generated method stub
		Set<E> temp = new DynamicSet<E>(this.size());
		for(E o:S2) {
			if(this.isMember(o)) {
				temp.add(o);
			}
			/* this= {A,B,C}
			 * S2 = {A,B,C}
			 * temp = {A,B,C}
			 * S3 = {A}
			 * temp = {A} 
			 */
		}
		if(this.size() == temp.size()) {
			return true;
		}
		//if(this.isSubset(temp))
		return false;
	}
	// TODO Auto-generated method stub
	public static boolean checkDisjoint(Object[] sets) {
		// TODO Auto-generated method stub
		Set<Integer> result = new DynamicSet<Integer>(sets.length);
		Integer temp = 0;
		for (int i = 0; i < sets.length; i++) {
			for (int k = 1; k < sets.length-1; k++) {
				if (((Set<Integer>) sets[i]).intersection((Set<Integer>) sets[k]).isEmpty()){
					temp++;
				}
			}
		}
		if(temp > 0) {
			return true;
		}		
		return false;
	}

	@Override
	public Set<Set<E>> singletonSets() {
		// TODO Auto-generated method stub
		Set<Set<E>> result = new DynamicSet<Set<E>>(this.size());
		for (E e: this) {
			Set<E> temp = new DynamicSet<E>(1);
			temp.add(e);
			result.add(temp);
		}
		return result;
	}

}