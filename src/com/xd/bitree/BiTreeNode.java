package com.xd.bitree;

public class BiTreeNode {
	/*
	 * 二叉树节点
	 */
	public Object data;
	public BiTreeNode left, right;
	// 构造方法
	public BiTreeNode() {
		this(null);
	}
	public BiTreeNode(Object date){
		this(date, null, null);
	}
	public BiTreeNode(Object date, BiTreeNode left, BiTreeNode right){
		this.data = date;
		this.left = left;
		this.right = right;
	}
}
