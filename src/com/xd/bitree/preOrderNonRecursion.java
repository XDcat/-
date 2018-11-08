package com.xd.bitree;

import com.xd.iStack.LinkStack;

public class preOrderNonRecursion implements Traverse{
	// 先序遍历（非递归）
	@Override
	public void traverse(BiTreeNode T) {	
		LinkStack st = new LinkStack();
		st.push(null);
		while (T != null){
			while (T != null){
				System.out.println(T.data);
				if (T.right != null)
					st.push(T.right);
				T = T.left;
			}
			T = (BiTreeNode) st.pop();
		}
	}
	
}
