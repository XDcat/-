package com.xd.iQueue;

public class CircleSqSqueue implements IQueue {
	/*
	 * 循环队列（实质上由数组构成，在逻辑上看成是首尾相接的）
	 * 为了避免判空和判满的条件相同，空出一个元素位置
	 * 判空：rear == front；
	 * 判满：(rear + 1) % queueElem.length == front;
	 */
	private Object[] queueElem;
	private int front;  // 队首：出元素
	private int rear;  // 队尾：添加元素
	public CircleSqSqueue(int maxSize) {
		// 构造方法
		queueElem = new Object[maxSize + 1];
		front = rear = 0;
	}
	
	@Override
	public void clear() {
		// 滞空
		front = rear = 0;
	}

	@Override
	public boolean isEmpty() {
		// 判空
		return front == rear;
	}

	@Override
	public Object getHead() {
		// 取队头元素
		if (! isEmpty())
			return queueElem[front];
		else 
			return null;
	}

	@Override
	public void enqueue(Object x) throws Exception{
		// 入队
		if ((rear + 1) % queueElem.length == front)
			throw new Exception("队满！");
		else {
			queueElem[rear] = x;
			rear = (rear +1) % queueElem.length;
		}

	}

	@Override
	public Object dequeue() {
		// 出队
		if (isEmpty())
			return null;
		else {
			Object t = queueElem[front];
			front = (front + 1) % queueElem.length;
			return t;
		}
		
	}

}
