package com.xd.iQueue;

import com.xd.iList.Node;

public class LinkQueue0 implements IQueue{
	/*
	 * 链队列（不带头结点）
	 * -------------
	 * 带头结点  vs 不带头结点
	 * 带头结点的链队列在getHead和enqueue都少了一个判读
	 * 所以带头结点更加好一些
	 */
	private Node front;  // 队头
	private Node rear;  // 队尾
	public LinkQueue0() {
		// 构造方法
		front = rear = null;
	}
	@Override
	public void clear() {
		// 滞空
		front = rear = null;
	}

	@Override
	public boolean isEmpty() {
		// 判空
		return front == null;
	}

	@Override
	public Object getHead() {
		// 获取头指针
		if (!isEmpty())
			return front.data;
		else
			return null;
	}

	@Override
	public void enqueue(Object x){
		// 入队
		// 如果空， 创建一个元素后， front和rear都指向它
		// 如果不空， front不变， rear后加一个结点， 并且指向最后
		if (! isEmpty()){
			rear.next = new Node(x);
			rear = rear.next;
		} else {
			front = rear = new Node(x);
		}
	}

	@Override
	public Object dequeue() {
		// 出队
		// 如果是空，返回null
		// 如果只剩下一个元素，rear， front=null
		// 其他，返回front.data， front往后
		if (! isEmpty()){
			Object t;
			t = front.data;
			if (front == rear){
				rear = front = null;
			} else {
				front = front.next;
			}
			return t;
		} else{
			// 为空的时候
			return null;
		}
	}
	public Node getFront(){
		return front;
	}
	
}
