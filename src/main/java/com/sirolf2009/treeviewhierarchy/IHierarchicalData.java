package com.sirolf2009.treeviewhierarchy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sirolf2009.treeviewhierarchy.change.Addition;
import com.sirolf2009.treeviewhierarchy.change.IChange;
import com.sirolf2009.treeviewhierarchy.change.Subtraction;

import javafx.collections.ObservableList;
import javafx.util.Pair;

public interface IHierarchicalData<T extends IHierarchicalData<?>> {

	public ObservableList<T> getChildren();

	default List<? extends IChange> getDifferencesTo(IHierarchicalData<T> other) {
		return getDifferencesToStream(other).collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	default Stream<? extends IChange> getDifferencesToStream(IHierarchicalData<T> other) {
		final Stream<? extends IChange> newChildren = getChildren().stream().filter(child -> !other.getChildren().stream().anyMatch(otherChild -> child.equals(otherChild))).map(child -> (IChange) new Addition(child));
		final Stream<? extends IChange> deletedChildren = other.getChildren().stream().filter(otherChild -> !getChildren().stream().anyMatch(child -> child.equals(otherChild))).map(child -> (IChange) new Subtraction(child));
		final Stream<? extends IChange> sharedChildren = getChildren().stream().map(child -> new Pair<IHierarchicalData, IHierarchicalData>(child, other.getChildren().stream().filter(otherChild -> child.equals(otherChild)).findFirst().orElse(null))).filter(pair -> pair.getValue() != null).flatMap(pair -> pair.getKey().getDifferencesToStream(pair.getValue()));
		return Stream.concat(Stream.concat(newChildren, deletedChildren), sharedChildren);
	}

}
