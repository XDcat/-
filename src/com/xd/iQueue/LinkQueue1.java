package com.xd.iQueue;

import com.xd.iList.Node;

public class LinkQueue1 implements IQueue{
	/*
	 * 链队列（带头结点）
	 * -------------
	 * 带头结点  vs 不带头结点
	 * 带头结点的链队列在getHead和enqueue都少了一个判读
	 * 所以带头结点更加好一些
	 */
	private Node front;  // 队头
	private Node rear;  // 队尾
	private Node head;  // 头指针
	public LinkQueue1() {
		// 构造方法
		head = front = rear = new Node();
	}
	public void clear() {
		// 滞空
		front = rear = head;
	}

	@Override
	public boolean isEmpty() {
		// 判空
		return front == head;
	}

	@Override
	public Object getHead() {
		// 获取头指针
		// 如果空， front = Node(null, null)
		// 如果不空， front = Node(data, next)
		// 所以， 直接返回front.data
		return front.data;
	}

	@Override
	public void enqueue(Object x) throws Exception {
		// 入队
		// 如果空，rear和head是一个引用， rear加个next， front在head后面， rear在rear后面
		// 如果不空， front在head后面， rear后加一个结点， 并且指向最后
		rear.next = new Node(x);
		rear = rear.next;
		front = head.next;
	}

	@Override
	public Object dequeue() {
		// 出队
		// 如果是空，返回null
		// 如果只剩下一个元素，rear， front=head
		// 其他，返回front.data， front往后
		if (! isEmpty()){
			Object t;
			t = front.data;
			if (front == rear){
				rear = front = head;
			} else {
				head.next = front.next;
				front = head.next;
			}
			return t;
		} else{
			// 为空的时候
			return null;
		}
	}

	
}
