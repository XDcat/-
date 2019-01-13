package com.xd.bitree;

public class preOrder implements Traverse {
	// 先序遍历
	public void traverse(BiTreeNode T) {
		if (T != null){
			// 不空时进入
			System.out.print(T.data);
			traverse(T.left);
			traverse(T.right);
		}
	}

}
