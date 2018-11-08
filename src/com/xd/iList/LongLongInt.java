package com.xd.iList;

import java.util.Collections;

public class LongLongInt extends LinkList {
	/**
	 * 长整数计算 1. 用链表存储 2. 实现加、减、乘 3. 转换为int， 字符串 4. 允许输入的格式：数字， 带逗号的数字， 带空格的数字
	 */

	// 构造方法
	private int sign; // 1代表负， 2代表正

	public LongLongInt() {
	}

	public LongLongInt(String num) {
		super(num);
		if (!isEmpty()) {
			if (this.head.next.data.equals("-")) {
				sign = 1;
				this.head.next = this.head.next.next;
			} else
				sign = 2;
		}
		this.intThis();
		this.invertedList(); // 对于加减乘， 倒叙运算
	}

	public LongLongInt add(LongLongInt t) {
		/*
		 * 加法 最终版， 带符号运算
		 */
		LongLongInt res; // 返回结果
		if ((this.sign + t.sign) % 2 == 0) {
			// 同号
			res = this.onlyAdd(t);
			res.sign = this.sign;
		} else {
			// 异号： 符号为绝对值大的那个
			if (this.biggerByAbsolateThen(t)) {
				res = this.onlyMinus(t);
				res.sign = this.sign;
			} else {
				res = t.onlyMinus(this);
				res.sign = t.sign;
			}
		}
		return res;
	}

	public LongLongInt minus(LongLongInt t) {
		/*
		 * 减法 最终版， 带符号运算
		 */
		LongLongInt res; // 返回结果
		if ((this.sign + t.sign) % 2 == 0) {
			// 同号: 用减
			if (this.biggerByAbsolateThen(t)) {
				res = this.onlyMinus(t);
				res.sign = this.sign;
			} else {
				res = t.onlyMinus(this);
				res.sign = (t.sign == 1) ? 2 : 1;
			}
		} else {
			// 异号： 用加
			res = this.onlyAdd(t);
			res.sign = this.sign;
		}
		return res;
	}

	public LongLongInt mult(LongLongInt t) {
		/*
		 * 乘法 最终版， 带符号运算
		 */
		LongLongInt res; // 返回结果
		res = this.onlyMult(t);
		res.sign = ((this.sign + t.sign) % 2 == 0) ? 2 : 1;
		return res;
	}

	private LongLongInt onlyAdd(LongLongInt t) {
		/*
		 * 加法 问题： 1. 最后的长度 = 原来最长的长度 2. 最后的长度 = 原来最长的长度 + 1 3. 循环后可能还要有一位 + 1 4.
		 * 类似9999+1， 不断进位的情况
		 */
		// 创建结果结点
		LongLongInt t1 = new LongLongInt();
		// 得到头结点（链表最重要的就是头结点）
		Node pa = this.head.next;
		Node pb = t.head.next;
		Node pc = t1.head;
		// 开始循环
		int da, db; // 存放pa， pb的数据
		int res0, res1 = 0; // 分别存储结果的个位 十位
		while (!(pa == null && pb == null)) {
			// 获取pa， pb数据
			// 且为了成为普遍情况， null时变为0
			da = (pa != null) ? (int) pa.data : 0;
			db = (pb != null) ? (int) pb.data : 0;
			// 取整， 取余
			res0 = (da + db + res1) % 10;
			res1 = (da + db + res1) / 10;
			// 创建新节点
			pc.next = new Node(res0, null);
			// 遍历
			pa = (pa != null) ? pa.next : pa;
			pb = (pb != null) ? pb.next : pb;
			pc = pc.next;
		}
		// 可能最后的进位
		if (res1 != 0)
			pc.next = new Node(1, null);
		return t1;
	}

	private LongLongInt onlyMult(LongLongInt t) {
		/*
		 * 乘法 t1: LongLongInt， 最后的返回值 t2: LinkList, 存储每一个数相乘的结果， 最后求和 e.g 56 * 34 = 56 *
		 * 4 + 56 * 30 = 224 + 1680 = 1904
		 */
		// 创建结果结点
		LongLongInt t1 = new LongLongInt();
		// 创建每一位相乘后得到的数
		LinkList t2 = new LinkList();
		// 得到头结点（链表最重要的就是头结点）
		Node pb = t.head.next;
//		Node pc = t1.head;
		Node pd = t2.head;
		// 填充t2()
		int db, count = 0; // 得到数据, 计数
		while (pb != null) {
			db = (int) pb.data;
			pd.next = new Node(this.tenMult(db, count++)); // 获取pb中每个数乘以pa
			pb = pb.next;
			pd = pd.next;
		}
		// t2 中每一个数相加
		pd = t2.head.next;
		while (pd != null) {
			t1 = t1.onlyAdd((LongLongInt) pd.data);
			pd = pd.next;
		}
		t1.del0Node(); // 处理多余数据
		return t1;
	}

	private LongLongInt onlyMinus(LongLongInt t) {
		/*
		 * 减法 大的减小的， 可以涉及变位 采用补码的方式来消去进位问题
		 */
		LongLongInt res;
		int pal = this.length(); // 自身的长度
		String negDig = String.join("", Collections.nCopies(pal, "9")); // 取反用的数
		res = this.onlyAdd((new LongLongInt(negDig)).singleMinus(t) // 取反
				.onlyAdd(new LongLongInt("1"))); // 加1
		res = res.singleMinus(new LongLongInt("1" + String.join("", Collections.nCopies(pal, "0")))); // 减去10^pal

		res.del0Node();
		return res;

	}

	private int compareByAbsolateWith(LongLongInt t) {
		/*
		 * 比较绝对值;0-相等， 1-小， 2-大； 1. 长度越长越大 2. 长度相等， 依次比较
		 */
		if (this.length() == t.length()) {
			if (this.length() == 0) {
				// 单独考虑， 防止pa。data时报错
				return 0;
			} else {
				// 将数值反转才好从头开始比较
				this.invertedList();
				t.invertedList();
				Node pa = this.head.next;
				Node pb = t.head.next;
				while (pa != null && pa.data.equals(pb.data)) {
					// 循环直到两者不等或空结束
					pa = pa.next;
					pb = pb.next;
				}
				// 再反转回来
				this.invertedList();
				t.invertedList();
				return ((int) pa.data > (int) pb.data) ? 2 : 1;
			}
		} else {
			return (this.length() > t.length()) ? 2 : 1;
		}
	}

	private boolean biggerByAbsolateThen(LongLongInt t) {
		/*
		 * 判断哪个更大 使用比较多， 单独拿出来
		 */
		int res = compareByAbsolateWith(t);
		return (res == 2) ? true : false;
	}

	private LongLongInt singleMinus(LongLongInt t) {
		/*
		 * 不涉及到变位的简单减法 大的减小的
		 */
		LongLongInt t1 = new LongLongInt();
		Node pa = this.head.next;
		Node pb = t.head.next;
		Node pc = t1.head;
		while (pb != null) {
			pc.next = new Node((int) pa.data - (int) pb.data);
			pa = pa.next;
			pb = pb.next;
			pc = pc.next;
		}
		pc.next = pa; // 连接剩下的
		return t1;
	}

	private LongLongInt tenMult(int t, int n) {
		/*
		 * 乘(t * 10 ^ n)
		 */
		// 创建结果结点
		LongLongInt t1 = new LongLongInt();
		// 得到头结点（链表最重要的就是头结点）
		Node pa = this.head.next;
		Node pc = t1.head;
		// 暂时的数据
		int da, db; // 相乘的两个数
		int res0, res1 = 0; // 个位， 十位
		for (int i = 0; i < n; i++) {
			pc.next = new Node(0);
			pc = pc.next;
		}
		while (pa != null) {
			da = (int) pa.data;
			db = t;
			// 取整， 取余
			res0 = (da * db + res1) % 10;
			res1 = (da * db + res1) / 10;
			// 创建新节点
			pc.next = new Node(res0);
			pa = pa.next;
			pc = pc.next;
		}
		// 可能最后的进位
		if (res1 != 0)
			pc.next = new Node(res1, null);
		return t1;
	}

	private void intThis() {
		/*
		 * 将结点内容转换为数值
		 */
		Node p = head.next;
		while (p != null) {
			p.data = Integer.parseInt((String) p.data);
			p = p.next;
		}
	}

	private void del0Node() {
		/*
		 * 删除多余的0
		 */
		Node pa = head;
		Node pb; // 标兵
		while (pa != null) {
			/*
			 * 如果pa的下一个是0， 则让pb为pa的下一个 pb继续循环， 直到下一个不是0， 如果下一个是null， 截断，否则， pa继续循环
			 */
			while (pa.next != null && !pa.next.data.equals(0)) {
				pa = pa.next;
			}
			if (pa.next != null) {
				pb = pa.next;
				while (pb != null && pb.data.equals(0)) {
					pb = pb.next;
				}
				if (pb == null) {
					pa.next = null;
				}
				pa = pa.next;
			} else
				break;
		}
	}

	@Override
	public void display() {
		/*
		 * 呈现数据 每三个数据一个逗号
		 */
		String res = ""; // 存储结果的字符串
		int count = 0; // 计数
		Node p = head.next;
		while (p != null) {
			res = p.data + res;
			count++;
			if (count == 3 && p.next != null) {
				res = "," + res;
				count = 0;
			}
			p = p.next;
		}
		if (this.sign == 1)
			res = "-" + res;
		System.out.println(res);
	}

	public static void main(String[] args) {
		// 普通
		String num1 = "1111";
		String num2 = "10001";
		String num3 = "-1234";
		LongLongInt t1 = new LongLongInt(num1);
		LongLongInt t2 = new LongLongInt(num2);
		LongLongInt t3 = new LongLongInt(num3);
		System.out.print("num1 = ");
		t1.display();
		System.out.print("num2 = ");
		t2.display();
		System.out.print("num3 = ");
		t3.display();
		System.out.print("num1 + num2 = ");
		t1.add(t2).display();
		System.out.print("num1 + num3 = ");
		t1.add(t3).display();
		System.out.print("num1 * num2 = ");
		t1.mult(t2).display();
		System.out.print("num1 - num2 = ");
		t1.minus(t2).display();
	}
}
