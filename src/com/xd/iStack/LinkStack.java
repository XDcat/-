package com.xd.iStack;
import com.xd.iList.Node;

public class LinkStack implements IStack{
	/*
	 * 链栈
	 * 对于栈只有对栈顶元素的操作，所以应该把栈顶作为头结点；
	 * 不带头指针，直接指向头结点；
	 */
	private Node top;  // 栈顶指针
	public LinkStack() {
		// 构造方法
		top = null;
	}
	@Override
	public void clear() {
		// 滞空
		top = null;
	}

	@Override
	public boolean isEmpty() {
		// 判空
		return top == null;
	}

	@Override
	public Object getHead() {
		// 取栈顶
		if (! isEmpty())
			return top.data;
		else
			return null;
		
	}

	@Override
	public void push(Object x){
		// 入栈
		top = new Node(x, top);
	}

	@Override
	public Object pop() {
		// 出栈
		Object t;
		if (isEmpty())
			return null;
		else {
			t = top.data;
			top = top.next;
			return t;
		}
	}
	public Node getTop(){
		return top;
	}
}
