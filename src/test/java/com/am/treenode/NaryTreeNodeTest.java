package com.am.treenode;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NaryTreeNodeTest {
    @Test
    public void getValue() {
        final INaryTreeNode<String> treeNode = new NaryTreeNode<>("value");
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
        final INaryTreeNode<String> child = new NaryTreeNode<>();
        treeNode.add(child);
        assertEquals(child, treeNode.getChild(0));
    }

    @Test
    public void getChildren() {
        final int nbChildren = 10;
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>();
        final List<INaryTreeNode<String>> children = new LinkedList<>();
        for (int i = 0; i < nbChildren; i++) {
            children.add(new NaryTreeNode<>());
        }
        for (final INaryTreeNode<String> child : children) {
            treeNode.add(child);
        }
        final List<INaryTreeNode<String>> childrenReturned = treeNode.getChildren();
        for (int i = 0; i < nbChildren; i++) {
            assertEquals(children.get(i), childrenReturned.get(i));
        }
    }

    @Test
    public void getChildrenIsNotModifiable() {
        final INaryTreeNode<String> treeNode = new NaryTreeNode<>();
        final List<INaryTreeNode<String>> children = treeNode.getChildren();
        try {
            children.add(new NaryTreeNode<>());
            fail("Should have thrown an UnsupportedOperationException exception");
        } catch (final UnsupportedOperationException e) {
            // OK
        }
    }

    @Test
    public void add() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>();
        final INaryTreeNode<String> child = new NaryTreeNode<>();
        treeNode.add(child);
        assertEquals(child, treeNode.getChild(0));
        treeNode.add("child");
        assertEquals("child", treeNode.getChild(1).getValue());
    }

    @Test
    public void remove() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final INaryTreeNode<String> child = new NaryTreeNode<>("child");
        assertTrue(treeNode.add(child));
        assertEquals(1, treeNode.getChildrenCount());
        assertTrue(treeNode.remove("child"));
        assertEquals(0, treeNode.getChildrenCount());
    }

    @Test
    public void getChildrenCount() {
        final int nbChildren = 10;
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>();
        for (int i = 0; i < nbChildren; i++) {
            treeNode.add(new NaryTreeNode<>());
        }
        assertEquals(nbChildren, treeNode.getChildrenCount());
    }

    @Test
    public void generateText() {
        final int nbChildren = 10;
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final NaryTreeNode<String> child1 = new NaryTreeNode<>("child 1");
        treeNode.add(child1);
        for (int i = 0; i < nbChildren; i++) {
            child1.add(new NaryTreeNode<>(String.valueOf(i)));
        }
        final NaryTreeNode<String> child2 = new NaryTreeNode<>("child 1");
        child1.add(child2);
        for (int i = 0; i < nbChildren; i++) {
            child2.add(new NaryTreeNode<>(String.valueOf(i)));
        }
        assertEquals(
                "[root] ([child 1] ([0], [1], [2], [3], [4], [5], [6], [7], [8], [9], [child 1] ([0], [1], [2], [3], [4], [5], [6], [7], [8], [9])))",
                treeNode.generateText());
    }

    @Test
    public void isLeaf() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>();
        assertTrue(treeNode.isLeaf());
        treeNode.add(new NaryTreeNode<>());
        assertFalse(treeNode.isLeaf());
    }

    @Test
    public void contains() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final INaryTreeNode<String> child = new NaryTreeNode<>("child");
        treeNode.add(child);
        assertTrue(treeNode.contains("root"));
        assertTrue(treeNode.contains("child"));
        assertFalse(treeNode.contains("not found"));
    }

    @Test
    public void getHeight() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final INaryTreeNode<String> child = new NaryTreeNode<>("child");
        treeNode.add(child);
        assertEquals(2, treeNode.getHeight());
    }

    @Test
    public void size() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final INaryTreeNode<String> child = new NaryTreeNode<>("child");
        treeNode.add(child);
        assertEquals(2, treeNode.size());
    }

    @Test
    public void getNumberOfLeaves() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final INaryTreeNode<String> child = new NaryTreeNode<>("child");
        treeNode.add(child);
        assertEquals(1, treeNode.getNumberOfLeaves());
    }

    @Test
    public void toJson() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final NaryTreeNode<String> child = new NaryTreeNode<>("child");
        final INaryTreeNode<String> subChild = new NaryTreeNode<>("subChild");
        assertEquals("{\"value\":\"root\"}", treeNode.toJson());
        treeNode.add(child);
        assertEquals("{\"value\":\"root\",\"children\":[{\"value\":\"child\"}]}", treeNode.toJson());
        child.add(subChild);
        assertEquals(
                "{\"value\":\"root\",\"children\":[{\"value\":\"child\",\"children\":[{\"value\":\"subChild\"}]}]}",
                treeNode.toJson());
    }

    @Test
    public void testToString() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final NaryTreeNode<String> child = new NaryTreeNode<>("child");
        final INaryTreeNode<String> subChild = new NaryTreeNode<>("subChild");
        assertEquals("NaryTreeNode{value=root, children=[]}", treeNode.toString());
        treeNode.add(child);
        assertEquals("NaryTreeNode{value=root, children=[NaryTreeNode{value=child, children=[]}]}",
                treeNode.toString());
        child.add(subChild);
        assertEquals(
                "NaryTreeNode{value=root, children=[NaryTreeNode{value=child, children=[NaryTreeNode{value=subChild, children=[]}]}]}",
                treeNode.toString());
    }

    @Test
    public void toPrettyText() {
        final NaryTreeNode<String> treeNode = new NaryTreeNode<>("root");
        final NaryTreeNode<String> child1 = new NaryTreeNode<>("child1");
        final NaryTreeNode<String> child2 = new NaryTreeNode<>("child2");
        final INaryTreeNode<String> child3 = new NaryTreeNode<>("child3");
        final INaryTreeNode<String> subChild11 = new NaryTreeNode<>("subChild11");
        final INaryTreeNode<String> subChild12 = new NaryTreeNode<>("subChild12");
        final NaryTreeNode<String> subChild21 = new NaryTreeNode<>("subChild21");
        final INaryTreeNode<String> subChild22 = new NaryTreeNode<>("subChild22");
        final INaryTreeNode<String> subSubChild211 = new NaryTreeNode<>("subSubChild211");
        assertEquals("""
                     root
                     """, treeNode.toPrettyText());
        treeNode.add(child1);
        assertEquals("""
                     root
                     ├─child1
                     """, treeNode.toPrettyText());
        treeNode.add(child2);
        assertEquals("""
                     root
                     ├─child1
                     ├─child2
                     """, treeNode.toPrettyText());
        child1.add(subChild11);
        child1.add(subChild12);
        assertEquals("""
                     root
                     ├─child1
                     │ ├─subChild11
                     │ ├─subChild12
                     ├─child2
                     """, treeNode.toPrettyText());
        child2.add(subChild21);
        child2.add(subChild22);
        assertEquals("""
                     root
                     ├─child1
                     │ ├─subChild11
                     │ ├─subChild12
                     ├─child2
                     │ ├─subChild21
                     │ ├─subChild22
                     """, treeNode.toPrettyText());
        subChild21.add(subSubChild211);
        treeNode.add(child3);
        assertEquals("""
                     root
                     ├─child1
                     │ ├─subChild11
                     │ ├─subChild12
                     ├─child2
                     │ ├─subChild21
                     │ │ ├─subSubChild211
                     │ ├─subChild22
                     ├─child3
                     """, treeNode.toPrettyText());
    }

    @Test
    public void toPostfixList() {
        final INaryTree<String> a = NaryTreeNodeTest.createTestTreeNode();
        List<String> postFixListExpected = List.of("K", "L", "M", "D", "E", "F", "G", "B", "H", "I", "J", "C", "A");
        assertEquals(postFixListExpected, a.toPostfixList());
    }

    private static NaryTreeNode<String> createTestTreeNode() {
        final NaryTreeNode<String> a = new NaryTreeNode<>("A");
        final NaryTreeNode<String> b = new NaryTreeNode<>("B");
        final NaryTreeNode<String> c = new NaryTreeNode<>("C");
        final NaryTreeNode<String> d = new NaryTreeNode<>("D");
        final INaryTreeNode<String> e = new NaryTreeNode<>("E");
        final INaryTreeNode<String> f = new NaryTreeNode<>("F");
        final INaryTreeNode<String> g = new NaryTreeNode<>("G");
        final INaryTreeNode<String> h = new NaryTreeNode<>("H");
        final INaryTreeNode<String> i = new NaryTreeNode<>("I");
        final INaryTreeNode<String> j = new NaryTreeNode<>("J");
        final INaryTreeNode<String> k = new NaryTreeNode<>("K");
        final INaryTreeNode<String> l = new NaryTreeNode<>("L");
        final INaryTreeNode<String> m = new NaryTreeNode<>("M");

        a.add(b);
        a.add(c);
        b.add(d);
        b.add(e);
        b.add(f);
        b.add(g);
        c.add(h);
        c.add(i);
        c.add(j);
        d.add(k);
        d.add(l);
        d.add(m);
        return a;
    }

    @Test
    public void toPrefixList() {
        final INaryTree<String> a = NaryTreeNodeTest.createTestTreeNode();
        List<String> prefixListExpected = List.of("A", "B", "D", "K", "L", "M", "E", "F", "G", "C", "H", "I", "J");
        assertEquals(prefixListExpected, a.toPrefixList());
    }

    @Test
    public void toByWidthList() {
        final INaryTree<String> a = NaryTreeNodeTest.createTestTreeNode();
        List<String> byWidthListExpected = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M");
        assertEquals(byWidthListExpected, a.toByWidthList());
    }

    @Test
    void getNodeFromElement() {
        final NaryTreeNode<String> a = NaryTreeNodeTest.createTestTreeNode();
        assertEquals(a, a.getNodeFromElement("A"));
        assertEquals(a.getChild(0), a.getNodeFromElement("B"));
        assertEquals(a.getChild(1), a.getNodeFromElement("C"));
        assertEquals(a.getChild(0).getChild(0), a.getNodeFromElement("D"));
        assertEquals(a.getChild(0).getChild(1), a.getNodeFromElement("E"));
        assertEquals(a.getChild(0).getChild(2), a.getNodeFromElement("F"));
        assertEquals(a.getChild(0).getChild(3), a.getNodeFromElement("G"));
        assertEquals(a.getChild(1).getChild(0), a.getNodeFromElement("H"));
        assertEquals(a.getChild(1).getChild(1), a.getNodeFromElement("I"));
        assertEquals(a.getChild(1).getChild(2), a.getNodeFromElement("J"));
        assertEquals(a.getChild(0).getChild(0).getChild(0), a.getNodeFromElement("K"));
        assertEquals(a.getChild(0).getChild(0).getChild(1), a.getNodeFromElement("L"));
        assertEquals(a.getChild(0).getChild(0).getChild(2), a.getNodeFromElement("M"));
    }

    @Test
    void forEach() {
        final NaryTreeNode<String> a = NaryTreeNodeTest.createTestTreeNode();
        final List<String> list = new LinkedList<>();
        a.forEach(list::add);
        List<String> byWidthListExpected = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M");
        assertEquals(byWidthListExpected, list);
    }
}