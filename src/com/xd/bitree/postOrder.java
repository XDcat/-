package com.xd.bitree;

public class postOrder implements Traverse {
	// 后序遍历
	public void traverse(BiTreeNode T) {
		if (T != null){
			traverse(T.left);
			traverse(T.right);
			System.out.print(T.data);
		}
	}

}
