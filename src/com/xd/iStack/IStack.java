package com.xd.iStack;

public interface IStack {
	/*
	 * 栈的接口
	 * FILO
	 */
	public void clear();
	public boolean isEmpty();
	public Object getHead();  // 取顶
	public void push(Object x) throws Exception;  // 入栈
	public Object pop();  // 出栈
}
