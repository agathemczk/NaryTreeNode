package com.jad.treenode;

import com.google.gson.Gson;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Nary tree node.
 *
 * @param <E> the type parameter
 */
public class NaryTreeNode<E> {
    private static final String CHILDREN_SEPARATOR = ", ";
    private static final String CHILDREN_PREFIX = "(";
    private static final String CHILDREN_SUFFIX = ")";
    private static final String VALUE_SEPARATOR = " ";
    private static final String VALUE_PREFIX = "[";
    private static final String VALUE_SUFFIX = "]";
    private static final String VALUE_NULL = "null";

    private E value;
    private final LinkedList<NaryTreeNode<E>> children;

    /**
     * Instantiates a new Nary tree node.
     *
     * @param value the value
     */
    public NaryTreeNode(final E value) {
        this.value = value;
        this.children = new LinkedList<>();
    }

    /**
     * Instantiates a new Nary tree node.
     */
    public NaryTreeNode() {
        this(null);
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public E getValue() {
        return this.value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(final E value) {
        this.value = value;
    }

    /**
     * Gets child.
     *
     * @param index the index
     * @return the child
     */
    public NaryTreeNode<E> getChild(final int index) {
        return this.children.get(index);
    }

    /**
     * Gets children.
     *
     * @return the children
     */
    public List<NaryTreeNode<E>> getChildren() {
        return Collections.unmodifiableList(this.children);
    }

    /**
     * Add child.
     *
     * @param child the child
     */
    public void addChild(final NaryTreeNode<E> child) {
        this.children.add(child);
    }

    /**
     * Remove child.
     *
     * @param child the child
     */
    public void removeChild(final NaryTreeNode<E> child) {
        this.children.remove(child);
    }

    /**
     * Remove child.
     *
     * @param index the index
     */
    public void removeChild(final int index) {
        this.children.remove(index);
    }

    /**
     * Gets children count.
     *
     * @return the children count
     */
    public int getChildrenCount() {
        return this.children.size();
    }

    /**
     * Is the node a leaf.
     *
     * @return the boolean
     */
    public boolean isLeaf() {
        return this.children.isEmpty();
    }

    @Override
    public String toString() {
        if (this.isLeaf()) {
            return NaryTreeNode.VALUE_PREFIX + (this.value == null ? NaryTreeNode.VALUE_NULL : this.value.toString()) +
                    NaryTreeNode.VALUE_SUFFIX;
        }
        return NaryTreeNode.VALUE_PREFIX +  (this.value == null ? NaryTreeNode.VALUE_NULL : this.value.toString()) +
                NaryTreeNode.VALUE_SUFFIX + NaryTreeNode.VALUE_SEPARATOR
                + this.children.stream().map(NaryTreeNode::toString).collect(Collectors.joining(
                NaryTreeNode.CHILDREN_SEPARATOR,
                NaryTreeNode.CHILDREN_PREFIX, NaryTreeNode.CHILDREN_SUFFIX));
    }

    /**
     * Contains boolean.
     *
     * @param value the value
     * @return the boolean
     */
    public boolean contains(final E value) {
        if (this.value.equals(value)) {
            return true;
        }
        for (final NaryTreeNode<E> child : this.children) {
            if (child.contains(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the height of the tree.
     *
     * @return the height
     */
    public int getHeight() {
        if (this.isLeaf()) {
            return 1;
        }
        return 1 + this.children.stream().mapToInt(NaryTreeNode::getHeight).max()
                .getAsInt();
//                .orElse();
    }

    /**
     * Gets the size of the tree.
     *
     * @return the size
     */
    public int getSize() {
        if (this.isLeaf()) {
            return 1;
        }
        return 1 + this.children.stream().mapToInt(NaryTreeNode::getSize).sum();
    }

    /**
     * Gets the number of leaves in the tree.
     *
     * @return the number of leaves
     */
    public int getNumberOfLeaves() {
        if (this.isLeaf()) {
            return 1;
        }
        return this.children.stream().mapToInt(NaryTreeNode::getNumberOfLeaves).sum();
    }

    /**
     * Gets the number of nodes in the tree.
     *
     * @return the number of nodes
     */
    public int getNumberOfNodes() {
        if (this.isLeaf()) {
            return 1;
        }
        return 1 + this.children.stream().mapToInt(NaryTreeNode::getNumberOfNodes).sum();
    }

    public String toJson() {
        if (this.isLeaf()) {
            return "{\"value\":" + new Gson().toJson(this.value) + "}";
        }
        return "{\"value\":" + new Gson().toJson(this.value) + ",\"children\":[" +
                this.children.stream().map(NaryTreeNode::toJson).collect(Collectors.joining(",")) + "]}";
    }

    public String toPrettyString() {
        return new Gson().toJson(this);
    }
}
