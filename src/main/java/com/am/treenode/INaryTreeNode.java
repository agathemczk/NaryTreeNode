package com.am.treenode;

import java.util.List;

interface INaryTreeNode<E> extends INaryTree<E> {
    /**
     * Returns the child at the specified position in the children list of this node.
     *
     * @param index - the index of the child
     *
     * @return the child at the specified position in the children list of this node.
     */
    INaryTreeNode<E> getChild(int index);

    /**
     * Ensures that this collection contains the specified node.
     *
     * @param node - node whose presence in this collection is to be ensured
     *
     * @return true if this collection changed as a result of the call
     */
    boolean add(INaryTreeNode<E> node);

    /**
     * Returns all children of this node.
     *
     * @return all children of this node.
     */
    List<INaryTreeNode<E>> getChildren();

    /**
     * Returns True if this node is a leaf.
     *
     * @return True if this node is a leaf.
     */
    boolean isLeaf();

    /**
     * Returns the number of children of this node.
     *
     * @return the number of children of this node.
     */
    int getChildrenCount();


    /**
     * Returns the value of the node.
     *
     * @return the value of the node
     */
    E getValue();


    /**
     * Sets the value of the node.
     *
     * @param value - the value of the node
     */
    void setValue(E value);
}