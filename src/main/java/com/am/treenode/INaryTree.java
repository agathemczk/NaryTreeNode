package com.am.treenode;

import java.util.List;

/**
 * Represents a tree date structure.
 * A tree is a hierarchical data structure that consists of nodes connected by edges.
 * Each node has a value and a list of children.
 *
 * @param <E> - the type of elements in this tree
 */
public interface INaryTree<E> extends Iterable<E> {
    /**
     * Returns the root of the tree.
     *
     * @return the root of the tree
     */
    E getRoot();

    /**
     * Ensures that this collection contains the specified element.
     *
     * @param element - element whose presence in this collection is to be ensured
     *
     * @return true if this collection changed as a result of the call
     */
    boolean add(E element);


    /**
     * Remove the element from the collection.
     *
     * @param element - the element to be removed
     *
     * @return true if this collection changed as a result of the call
     */
    boolean remove(Object element);

    /**
     * Returns a string representation of the object.
     * The string representation consists of a list of the node's value and its children.
     * The string representation of the children is enclosed in {@value NaryTreeNodeUtils#CHILDREN_PREFIX} and {@value NaryTreeNodeUtils#CHILDREN_SUFFIX}.
     * Adjacent elements are separated by the characters {@value NaryTreeNodeUtils#CHILDREN_SEPARATOR}.
     * The string representation of the node's value is enclosed in the characters {@value NaryTreeNodeUtils#VALUE_PREFIX} and {@value NaryTreeNodeUtils#VALUE_SUFFIX}.
     * The string representation of the node's value is obtained by calling the toString method.
     * If the node's value is null, then the string representation is {@value NaryTreeNodeUtils#VALUE_NULL}.
     *
     * @return a string representation of the object.
     */
    String generateText();

    /**
     * Returns True if the tree contains the specified value.
     *
     * @param element - element whose presence in this tree is to be tested
     *
     * @return true if the tree contains the specified value
     */
    boolean contains(Object element);

    /**
     * Returns the height of the tree.
     * A tree with a single node (root) has a height of 1.
     *
     * @return the height of the tree
     */
    int getHeight();

    /**
     * Returns the number of nodes in the tree.
     *
     * @return the number of nodes in the tree
     */
    int size();

    /**
     * Returns the number of leaves in the tree.
     *
     * @return the number of leaves in the tree
     */
    int getNumberOfLeaves();

    /**
     * Returns a json representation of the tree.
     * The json representation consists of a list of the node's value and its children.
     * The json's key for the value is {@value NaryTreeNodeUtils#JSON_VALUE_KEY}.
     * The json's key for the children is {@value NaryTreeNodeUtils#JSON_CHILDREN_KEY}.
     *
     * @return a json representation of the tree
     */
    String toJson();

    /**
     * Returns a pretty text representation of the tree.
     * The pretty text representation consists of a list of the node's value and its children.
     * All the nodes are indented according to their depth in the tree.
     * The string representation of the node's value is obtained by calling the toString method.
     * <p>
     * Example:
     * <pre>
     *      root
     *      ├─child1
     *      │ ├─subChild11
     *      │ ├─subChild12
     *      ├─child2
     *      │ ├─subChild21
     *      │ │ ├─subSubChild211
     *      │ ├─subChild22
     *      ├─child3
     *  </pre>
     *
     * @return a pretty text representation of the tree
     */
    String toPrettyText();

    /**
     * Returns a postfix list of all values.
     * The postfix list is obtained by traversing the tree in post-order.
     * All children of a node are visited before the node itself.
     *
     * @return a postfix list of all values
     */
    List<E> toPostfixList();

    /**
     * Returns a prefix list of all values.
     * The prefix list is obtained by traversing the tree in pre-order.
     * The node is visited before its children.
     *
     * @return a prefix list of all values
     */
    List<E> toPrefixList();

    /**
     * Returns a by width list of all values.
     * The by width list is obtained by traversing the tree in width.
     * Each level of the tree is visited before the next level.
     *
     * @return a by width list of all values
     */
    List<E> toByWidthList();

    /**
     * Returns the node that contains the specified element.
     * If the element is found in the tree, the node that contains the element is returned.
     * Otherwise, null is returned.
     * If the element is present multiple times in the tree, the first node that contains the element is returned.
     *
     * @param element the element
     *
     * @return the node that contains the specified element
     */
    INaryTree<E> getNodeFromElement(E element);

    /**
     * Returns True if the tree is empty.
     *
     * @return True if the tree is empty
     */
    boolean isEmpty();
}