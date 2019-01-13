package com.xd.huffman;

public class HuffmanNode{
	public int weight;
	public int flag;
	public HuffmanNode parent, lchild, rchild;
	public HuffmanNode() {
		this(0);
	}
	public HuffmanNode(int weight) {
		this.weight = weight;
		flag = 0;
		parent = lchild = rchild = null;
	}
}