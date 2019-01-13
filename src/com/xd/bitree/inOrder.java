package com.xd.bitree;

public class inOrder implements Traverse {
	// 中序遍历
	public void traverse(BiTreeNode T) {
		if (T != null){
			traverse(T.left);
			System.out.print(T.data);
			traverse(T.right);
		}
	}

}
