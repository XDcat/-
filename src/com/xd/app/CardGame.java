package com.xd.app;

import com.xd.iList.Node;
import com.xd.iQueue.LinkQueue0;
import com.xd.iStack.LinkStack;

public class CardGame {
	/*2 1 3
	 * 6155 429
	 * 213462
	 * 155 9213462
	 * 题目：小猫钓鱼纸牌游戏
	 *	[问题描述]
	 *	A和B两人玩简单的纸牌游戏，每人手里都有n张牌，两人轮流出牌并依次排列在桌面上，每次出掉手里的第1张牌，出牌后如果发现桌面上有跟刚才打出的牌的数字相同的牌，则把桌面上从数字相同的那张牌开始的全部牌按次序放在自己手里的牌的末尾。当一个人手中的牌先出完时，游戏结束，对方获胜。
	 *	如n为5，A手里的牌依次为2 3 5 6 1，B手里的牌依次为1 5 4 2 9；
	 */
	LinkQueue0 personA, personB;  // 两个人，只用队列
	LinkStack table;  // 桌面使用栈:因为要遍历所有的元素， 则使用链表的top方便
	public CardGame() {
		// 默认初始值
		this(new int[]{2, 3, 5, 6, 1}, new int[]{1, 5, 4, 2, 9});
	}
	public CardGame(int[] a, int[] b){
		table = new LinkStack();
		personA = new LinkQueue0();
		personB = new LinkQueue0();
		for (int i : a) {
			personA.enqueue(i);
		}
		for (int i : b) {
			personB.enqueue(i);
		}
	}
	public void startGame(){
		// 如果有一方队列为空， 则结束
		int count = 0;  // 记录第几次出牌
		while(!(personA.isEmpty() || personB.isEmpty())){
			showState(++count);  // 展示桌面情况
			int a = (int) personA.dequeue();
			int b = (int) personB.dequeue();
			// 出牌判断
			playCard(personA, a, "A");
			playCard(personB, b, "B");
			System.out.println();  // 分割输出
		}
		char res = (personA.isEmpty())? 'A': 'B';
		System.out.println(res + "胜出！");
	}
	private boolean isIn(int x){
		// 判断x是否在栈中
		Node top = table.getTop();
		while (top != null){
			if ((int)top.data == x)
				return true;
			top = top.next;
		}
		return false;
	}
	private void playCard(LinkQueue0 person, int a, String name){
		LinkStack tmp = new LinkStack();  // 暂存数据的栈；当table的纸牌要出栈时，纸牌入队的顺序是反的， 先出来的后进入
		// 出牌时候的判定
		// 如果存在于table中， 则入队；否则， 入栈
		System.out.print(name + "出牌：" + a);
		if (isIn(a)){
			// table元素转移到tmp
			tmp.push(a);  // 把a先进栈
			while((int)table.getHead() != a){
				tmp.push(table.pop());
			}
			tmp.push(table.pop());  // 最后一个元素
			// 入队
			while(! tmp.isEmpty()){
				person.enqueue(tmp.pop());
			}
		} else {
			table.push(a);
		}
		System.out.print(" " + name + "："); showPerson(person);
	}
	private void showState(int count){
		System.out.println("第" + count + "次出牌：");
		// 展示桌面上的牌
		System.out.print("桌子：");
		Node top = table.getTop();
		String t = "";
		while (top != null){
			t = top.data + " " + t;
			top = top.next;
		}
		System.out.println(t);
	}
	private void showPerson(LinkQueue0 p){
		Node t = p.getFront();
		while(t != null){
			System.out.print(t.data + " ");
			t = t.next;
		}
		System.out.println();
	}
	public static void main(String[] args) {
		CardGame game = new CardGame();
		game.startGame();
	}
}
