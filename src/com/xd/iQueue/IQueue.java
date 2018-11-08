package com.xd.iQueue;

public interface IQueue {
	/*
	 * 队列：FIFO
	 * 有队头front， 队尾rear
	 */
	public void clear();
	public boolean isEmpty();
	public Object getHead();
	public void enqueue(Object x) throws Exception;
	public Object dequeue();
}
