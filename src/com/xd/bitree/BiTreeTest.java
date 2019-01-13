package com.xd.bitree;

public class BiTreeTest {
	public static void main(String[] args) {
		BiTree biTree = new BiTree("AB##CD###");
		biTree.setTraverse(new preOrder());
		biTree.traverse();
		biTree.setTraverse(new inOrder());
		biTree.traverse();
	}
}
