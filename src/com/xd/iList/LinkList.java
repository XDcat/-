package com.xd.iList;

import java.util.Scanner;

public class LinkList implements IList{
	protected Node head;
	// 构造方法
	public LinkList(){
		this.head = new Node();
	}
	public LinkList(int n, boolean order){
		/*
		 * true: 尾插入
		 * false: 头插入
		 */
		this();
		if (order)
			creat1(n);
		else 
			creat2(n);
	}
	public LinkList(String str){
		// 把字符串每一个字符， 变成节点
		this();
		Node p = head;
		for (int i = 0; i < str.length(); i++) {
			p.next = new Node(str.charAt(i) + "", null);
			p = p.next;
		}
	}
	public void display(){
		/*
		 *  打印所有节点数据
		 */
		Node p = head.next;
		while(p != null){
			System.out.print(p.data + " ");
			p = p.next;
		}
		System.out.println();
	}
	public void invertedList(){
		/*
		 * 逆序链表
		 * 第一个节点变成尾节点， 即head.next.next = null;
		 * 并要保存后一个节点
		 * 然后分成三部分：变化的部分a1, 当前节点a2, 后一个节点a3
		 * a3 = a2.next; a2.next = a1; 循环
		 * 最后， head.next = a1;
		 */
		if (isEmpty())
			return;
		Node pa = head.next;  // 变化部分
		Node pb = pa.next;  // 当前节点
		Node pc;  // 后一个节点
		// 首节点变成尾节点
		pa.next = null;
		// 循环变换
		while(pb != null){
			pc = pb.next;
			pb.next = pa;
			pa = pb;
			pb = pc;
		}
		// 头结点接上
		head.next = pa;
	}
	public boolean isEmpty(){
		/*
		 *  判断是否为空
		 */
		return head.next == null;
	}
	public int length() {
		if (isEmpty())
			return 0;
		int count=0;
		Node p = this.head;
		while(p.next != null) {
			count++;
			p = p.next;
		}
		return count;
	}
	@Override
	public void clear() {
		this.head.next = null;
		
	}
	@Override
	public Object get(int i) {
		if (i > length() - 1)
			return null;
		else{
			Node p = head.next;
			for (int j = 0; j < i; j++) {
				p = p.next;
			}
			return p.data;
		}
		
	}
	@Override
	public void insert(int i, Object x) {
		if (i>length()-1)
			return;
		else{
			Node p = head;
			for (int j = 0; j < i; j++) {
				p = p.next;
			}
			p.next = new Node(x, p.next);
		}
		
	}
	@Override
	public void remove(int i) {
		if (i > length() - 1)
			return;
		else{
			Node p = head;
			for (int j = 0; j < i - 1; j++) {
				p = p.next;
			}
			p.next = p.next.next;
		}
		
	}
	@Override
	public int indexOf(Object x) {
		Node p = head.next;
		int count = 0;
		while (p != null){
			if (p.data.equals(x)){
				return count;
			}
			count ++;
			p = p.next;
		}
		return -1;
	}
	private void creat1(int n){
		/*
		 *  尾插入
		 */
		Scanner sc = new Scanner(System.in);
		Node pNode = head;
		for (int i = 0; i < n; i++) {
			pNode.next = new Node(sc.next(), null);
			pNode = pNode.next;
		}
		sc.close();
	}
	private void creat2(int n){
		/*
		 *  头插入
		 */
		Scanner sc = new Scanner(System.in);
		Node pNode = head;
		Node qNode;
		for (int i = 0; i < n; i++) {
			qNode = new Node(sc.next(), pNode.next);
			pNode.next = qNode;
		}
		sc.close();
	}
	public static void main(String[] args) {
		LinkList l = new LinkList(5, true);
		l.display();
		l.remove(4);
		l.display();
		System.out.println(l.indexOf("63"));
		System.out.println(l.get(3));
		
	}
}
