package com.jad.treenode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class NaryTreeNodeTest {
    @Test
    public void getValue() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("value");
        assertEquals("value", treeNode.getValue());
    }

    @Test
    public void setValue() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>();
        assertNull(treeNode.getValue());
        treeNode.setValue("value");
        assertEquals("value", treeNode.getValue());
    }

    @Test
    public void getChild() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>();
        final NaryTreeNode<String> child = new NaryTreeNode<>();
        treeNode.addChild(child);
        assertEquals(child, treeNode.getChild(0));
    }

    @Test
    public void getChildren() {
        final int nbChildren = 10;
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>();
        final List<NaryTreeNode<String>> children = new LinkedList<>();
        for (int i = 0; i < nbChildren; i++) {
            children.add(new NaryTreeNode<>());
        }
        for (NaryTreeNode<String> child : children) {
            treeNode.addChild(child);
        }
        final List<NaryTreeNode<String>> childrenReturned = treeNode.getChildren();
        for (int i = 0; i < nbChildren; i++) {
            assertEquals(children.get(i), childrenReturned.get(i));
        }
    }

    @Test
    public void getChildrenIsNotModifiable() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>();
        final List<NaryTreeNode<String>> children = treeNode.getChildren();
        try {
            children.add(new NaryTreeNode<>());
            fail("Should have thrown an UnsupportedOperationException exception");
        } catch (UnsupportedOperationException e) {
            // OK
        }
    }

    @Test
    public void addChild() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>();
        final NaryTreeNode<String> child = new NaryTreeNode<>();
        treeNode.addChild(child);
        assertEquals(child, treeNode.getChild(0));
    }

    @Test
    public void removeChild() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>();
        final NaryTreeNode<String> child = new NaryTreeNode<>();
        treeNode.addChild(child);
        treeNode.removeChild(0);
        assertEquals(0, treeNode.getChildrenCount());
    }

    @Test
    public void removeChild2() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>();
        final NaryTreeNode<String> child = new NaryTreeNode<>();
        treeNode.addChild(child);
        treeNode.removeChild(child);
        assertEquals(0, treeNode.getChildrenCount());
    }

    @Test
    public void getChildrenCount() {
        final int nbChildren = 10;
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>();
        for (int i = 0; i < nbChildren; i++) {
            treeNode.addChild(new NaryTreeNode<>());
        }
        assertEquals(nbChildren, treeNode.getChildrenCount());
    }

    @Test
    public void toStringTest() {
        final int nbChildren = 10;
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final  NaryTreeNode<String> child1 = new NaryTreeNode<>("child 1");
        treeNode.addChild(child1);
        for (int i = 0; i < nbChildren; i++) {
            child1.addChild(new NaryTreeNode<>(i + ""));
        }
        final  NaryTreeNode<String> child2 = new NaryTreeNode<>("child 1");
        child1.addChild(child2);
        for (int i = 0; i < nbChildren; i++) {
            child2.addChild(new NaryTreeNode<>(i + ""));
        }
        assertEquals("[root] ([child 1] ([0], [1], [2], [3], [4], [5], [6], [7], [8], [9], [child 1] ([0], [1], [2], [3], [4], [5], [6], [7], [8], [9])))", treeNode.toString());
    }

    @Test
    public void isLeaf() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>();
        assertTrue(treeNode.isLeaf());
        treeNode.addChild(new NaryTreeNode<>());
        assertFalse(treeNode.isLeaf());
    }

    @Test
    public void contains() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final NaryTreeNode<String> child = new NaryTreeNode<>("child");
        treeNode.addChild(child);
        assertTrue(treeNode.contains("root"));
        assertTrue(treeNode.contains("child"));
        assertFalse(treeNode.contains("not found"));
    }

    @Test
    public void getHeight() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final NaryTreeNode<String> child = new NaryTreeNode<>("child");
        treeNode.addChild(child);
        assertEquals(2, treeNode.getHeight());
    }

    @Test
    public void getSize() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final NaryTreeNode<String> child = new NaryTreeNode<>("child");
        treeNode.addChild(child);
        assertEquals(2, treeNode.getSize());
    }

    @Test
    public void getNumberOfLeaves() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final NaryTreeNode<String> child = new NaryTreeNode<>("child");
        treeNode.addChild(child);
        assertEquals(1, treeNode.getNumberOfLeaves());
    }

    @Test
    public void getNumberOfNodes() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final NaryTreeNode<String> child = new NaryTreeNode<>("child");
        treeNode.addChild(child);
        assertEquals(2, treeNode.getNumberOfNodes());
    }

    @Test
    public void toJson() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final NaryTreeNode<String> child = new NaryTreeNode<>("child");
        final NaryTreeNode<String> subchild = new NaryTreeNode<>("subchild");
        assertEquals("{\"value\":\"root\"}", treeNode.toJson());
        treeNode.addChild(child);
        assertEquals("{\"value\":\"root\",\"children\":[{\"value\":\"child\"}]}", treeNode.toJson());
        child.addChild(subchild);
        assertEquals("{\"value\":\"root\",\"children\":[{\"value\":\"child\",\"children\":[{\"value\":\"subchild\"}]}]}", treeNode.toJson());
    }
}