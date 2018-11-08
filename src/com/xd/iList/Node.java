package com.xd.iList;

public class Node {
	/**
	 * 节点类
	 */
	public Object data;
	public Node next;
	public Node(){
		this(null, null);
	}
	public Node(Object data){
		this(data, null);
	}
	public Node(Object data, Node next){
		this.data = data;
		this.next = next;
	}
}
