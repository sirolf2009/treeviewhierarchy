package com.sirolf2009.treeviewhierarchy.change;

import com.sirolf2009.treeviewhierarchy.IHierarchicalData;

public class Subtraction<T extends IHierarchicalData<?>> implements IChange {

	public final T item;

	public Subtraction(T item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "Addition [item=" + item + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		final Subtraction other = (Subtraction) obj;
		if(item == null) {
			if(other.item != null) {
				return false;
			}
		} else if(!item.equals(other.item)) {
			return false;
		}
		return true;
	}

	public T getItem() {
		return item;
	}

}
