package com.sirolf2009.treeviewhierarchy;

import java.util.stream.IntStream;

public class PrintTree {

	@SuppressWarnings("rawtypes")
	public static String toFileTree(IHierarchicalData root) {
		return toFileTree(root, 0);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String toFileTree(IHierarchicalData root, int depth) {
		final StringBuffer buffer = new StringBuffer();
		IntStream.range(0, depth).forEach(index -> buffer.append(" │ "));
		buffer.append(root.toString() + "\n");
		root.getChildren().forEach(child -> buffer.append(toFileTree((IHierarchicalData) child, depth + 1)));
		return buffer.toString();
	}

}
