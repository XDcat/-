package com.xd.bitree;

public class BiTree {
	/*
	 * 二叉树
	 * --遍历：策略模式
	 */
	private BiTreeNode root;  // 根节点
	private Traverse tr;  // 遍历方法
	static int i = 0;
	// 构造方法
	public BiTree(String preOrder, String inOrder) {
		// 进入：利用先序与中序构建
		this(preOrder, inOrder, 0, 0, preOrder.length());
		// 默认先序递归遍历
		this.tr = new preOrder();
	}
	private static int index = 0;
	public BiTree(String preOrder){
		// 利用扩展二叉树构建
		char c = preOrder.charAt(index++);
		if (c != '#'){
			root = new BiTreeNode(c);
			root.left = new BiTree(preOrder).root;
			root.right = new BiTree(preOrder).root;
		} else {
			root = null;
		}
	}

	private BiTree(String preOrder, String inOrder, int preIndex, int inIndex, int count){
		// 利用先序与中序构建
		if(count > 0){
			// 在先序中找到根
			char r = preOrder.charAt(inIndex);
			root = new BiTreeNode(r);
			// 再在中序中找到根
			int i = 0;
			for(; i < count; i++){
				if (r == preOrder.charAt(i))
					break;
			}
			// 根据找到的找到的i，确定左右子树的位置，并继续迭代
			root.left = new BiTree(preOrder, inOrder, preIndex + 1, inIndex, i).root;
			root.right = new BiTree(preOrder, inOrder, preIndex + i + 1, inIndex + i + 1, count - i - 1).root;
		}
	}
	
	// 遍历
	public void traverse(){
		// 默认方法遍历
		tr.traverse(root);
	}
	public void traverse(Traverse tr){
		// 制定方法遍历
		tr.traverse(root);
	}
	public void setTraverse(Traverse tr){
		// 设置默认遍历方法
		this.tr = tr;
	}
	// 二叉树的应用
	// 1. 查找
	public BiTreeNode searchNode(Object x) {
		return searchNode(root, x);
	}
	private BiTreeNode searchNode(BiTreeNode T, Object x) {
		// 如果到空返回null， 否则进行比较后者遍历左右子节点
		if (T != null){
			if (T.data.equals(x))
				return T;
			else{
				// 必须单独return，不能只在闭包中返回
				BiTreeNode rResutlBiTreeNode = searchNode(T.left, x);
				return (rResutlBiTreeNode == null)? searchNode(T.right, x): rResutlBiTreeNode;
			}
		}
		return null;
	}
	// 2. 统计节点个数
	public int countNode() {
		return countNode(root);
	}
	private int countNode(BiTreeNode T) {
		// f(x) = 0， T = null
		//		  1 + left + right, T != null
		if (T != null)
			return 1 + countNode(T.left) + countNode(T.right);
		else
			return 0;
	}

	// 3. 求二叉树的深度
	public int getDepth() {
		return getDepth(root);
	}
	private int getDepth(BiTreeNode T) {
		// f(x) = 0, T = null
		//		  1, right = left = null
		//		  1 + left, left > right
		//		  1 + right, right > left
		if (T == null)
			return 0;
		else{
			if (T.left == null && T.right == null)
				return 1;
			else{
				int rDepth = getDepth(T.right);
				int lDepth = getDepth(T.left);
				return (rDepth > lDepth)? rDepth: lDepth;
			}
				
		}
	}
	
	
	// 4. 判断两颗二叉树是否相等（如果是相似性判断，缺少一步对于数值的判断就可以了）
	public boolean equal(BiTree biTree2) {
		return equal(root, biTree2.root);
	}
	private boolean equal(BiTreeNode T1, BiTreeNode T2) {
		//f(x) = true, T1 = T2 = null
		//		 (T1.left ==T2.left) && (T1.right == T2.right), (T1, T2 != null)
		//		 false
		if (T1 == null && T2 == null)
			return true;
		else{
			if (T1 != null && T2 != null){
				return (T1.data.equals(T2.data)) && equal(T1.left, T2.left) && equal(T1.right, T2.right);
			}
			else
				return false;
		}
	}
}
