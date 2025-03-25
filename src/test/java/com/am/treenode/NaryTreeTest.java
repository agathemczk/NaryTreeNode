package com.am.treenode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NaryTreeTest {

    @Test
    void getRoot() {
        INaryTree<String> tree = new NaryTree<>("root");
        assertEquals("root", tree.getRoot());
        tree = new NaryTree<>();
        assertNull(tree.getRoot());
    }

    @Test
    void add() {
        INaryTree<String> tree = new NaryTree<>("root");
        tree.add("child1");
        assertTrue(tree.contains("child1"));
    }

    @Test
    void remove() {
        INaryTree<String> tree = new NaryTree<>("root");
        tree.add("child1");
        tree.remove("child1");
        assertFalse(tree.contains("child1"));
        tree.remove("root");
        assertFalse(tree.contains("root"));
    }
}