package com.xd.iStack;

public class SqStack implements IStack{
	/*
	 * 顺序栈
	 * 栈顶指针采用不偏移的方式， 即空栈时指到-1
	 */
	private Object[] stackElem;
	private int top;  // 栈顶指针
	public SqStack(int maxSize){
		/*
		 * 构造函数：数组的形式实现，必须有长度
		 */
		stackElem = new Object[maxSize];
		top = -1;
	}
	@Override
	public void clear() {
		/*
		 * 滞空
		 */
		top = -1;
	}
	@Override
	public boolean isEmpty() {
		/*
		 * 判断是否为空
		 */
		return top == -1;
	}
	@Override
	public Object getHead() {
		/*
		 * 取栈顶数据
		 */
		if (isEmpty())
			return null;
		else
			return stackElem[top];
	}
	@Override
	public void push(Object x) throws Exception{
		/*
		 * 入栈
		 */
		if (top == stackElem.length)
			throw new Exception("栈满！");
		else
			stackElem[++top] = x; 
	}
	@Override
	public Object pop() {
		/*
		 * 出栈
		 */
		if (! isEmpty())
			return stackElem[top--];
		else
			return null;
	}
	public int getMaxSize(){
		return stackElem.length;
	}
}
