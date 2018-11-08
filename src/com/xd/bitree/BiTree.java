package com.xd.bitree;

public class BiTree {
	/*
	 * 二叉树
	 * --遍历：策略模式
	 */
	private BiTreeNode rootBiTreeNode;  // 根节点
	private Traverse tr;  // 遍历方法
	// TODO: 创造具体的操作类
	public BiTree(Traverse tr) {
		this.tr = tr;
	}
	public void traverse(){
		// 策略模式
		tr.traverse(rootBiTreeNode);
	}

}
