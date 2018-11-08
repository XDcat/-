package com.xd.iStack;

public class parseExpersion {
	/*
	 * 计算表达式
	 * 1.将表达式转换为后缀表达式
	 * 2.计算后缀表达式的结果
	 */
	public parseExpersion(String s) throws Exception {
		// 判断表达式是否合法
		isLegalBracket(s);  // 括号
		isLegalExpersion(s);  // 字符
		// 返回结果
		String exp = convertExpersion(s);
		System.out.println("中缀表达式：" + exp);
		System.out.println("结果：" + s + " = " + numCaculate(exp));
	}
	private String convertExpersion(String exp) {
		/*
		 * 从左往右遍历字符串
		 * 数字直接连接
		 * 运算符根据优先级别，进行栈的操作
		 */
		LinkStack st = new LinkStack();  // 运算符的栈
		String res = "";  // 结果：中缀表达式
		for (int i = 0; i < exp.length(); i++) {
			char t = exp.charAt(i);
			if (t != ' ') {
				// 不为空才进行判断
				if (isLeftBracket(t)){
					// 如果是左括号直接入栈
					st.push(t);
				} else if(isRightBracket(t)){
					// 如果是右括号，栈顶元素出栈，直到有左括号
					char top = (char) st.pop();
					while(!isLeftBracket(top)) {
						res += top;
						top = (char) st.pop();
					}
				} else if(isOperator(t)) {
					// 如果是运算符，入栈
					// 比top优先级别高，则入栈
					// 比top优先级别低，则出栈
					if (! st.isEmpty()) {
						//  不为空的时候进行比较操作
						char top = (char) st.getHead();
						while (! higherThan(t, top) && !isLeftBracket(top)) {
							res += top;
							st.pop();  // 既然没有跳出，就需要把头节点删除
							if (st.isEmpty())
								// 为空的时候就跳出去
								break;
							top = (char) st.getHead();
						}
					}
					st.push(t);  // 新的运算符一定是要进栈的
				} else {
					// 其余剩下的肯定都是数字了，直接加上
					res += t; 
				}
			}
			
		}
		while (! st.isEmpty()) {
			// 最后如果栈没有为空全部输出
			res += st.pop();
		}
		return res;
	}
	private double numCaculate(String exp) {
		// 计算后缀表达式
		double a, b;
		LinkStack st = new LinkStack();  // 记录数字的栈
		for (int i = 0; i < exp.length(); i++) {
			char c = exp.charAt(i);
			if (isOperator(c)) {
				a = Double.valueOf(st.pop().toString());
				b = Double.valueOf(st.pop().toString());
				switch (c) {
				case '+':
					st.push(b + a);
					break;
				case '-':
					st.push(b - a);
					break;
				case '*':
					st.push(b * a);
					break;
				case '/':
					st.push(b / a);
					break;
				case '^':
					st.push(Math.pow(b, a));
					break;
				}
			} else {
				st.push(c);
			}
		}
		return (double) st.pop();
	}
	private boolean isOperator(char c) {
		// TODO: 判断是否是运算符
		switch(c) {
		case '+':
		case '-':
		case '*':
		case '/':
		case '^':
			return true;
		default:
			return false;
		}
	}
	private boolean isLeftBracket(char c) {
		// TODO: 判断是否是左括号
		return c == '(';
	}
	private boolean isRightBracket(char c) {
		// TODO: 判断是否是右括号
		return c == ')';
	}
	private boolean higherThan(char a, char b) {
		// TODO: 判断是否a的优先级别低于b
		return level(a) > level(b);
	}
	private int level(char c) {
		// TODO: 返回级别大小
		switch(c) {
		case '+':
		case '-':
			return 0;
		case '*':
		case '/':
			return 1;
		case '^':
			return 2;
		default:
			return 3;
		}
	}
	private void isLegalExpersion(String s) throws Exception{
		// TODO: 判断表达式中的字符是否合法
		// 必须是数字、运算符、括号
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch(c) {
			case '+':
			case '-':
			case '*':
			case '/':
			case '^':
			case '(':
			case ')':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case '0':
			case ' ':
				break;
			default:
				throw new Exception("存在不合法字符");
			}

		}
	}
	private void isLegalBracket(String s) throws Exception{
		// TODO: 判断括号是否合法
		LinkStack st = new LinkStack();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (isLeftBracket(c))
				// 如果是左括号，则入栈
				st.push(c);
			else if (isRightBracket(c)) {
				// 如果是右括号，则出栈
				if (st.isEmpty())
					throw new Exception("括号错误：缺少左括号！");
				st.pop();
			}	
		}
		if (! st.isEmpty())
			throw new Exception("括号错误：缺少右括号！");
	}
	public static void main(String[] args) throws Exception {
		String a1 = "1 + 2 + 3";  // 简单
		String a2 = "2 * ( 3 + 4)";  // 带括号
		String a3 = "1 + 2 * ( 3 + 4)";  // 带括号+
		String a4 = "(1 + 2) * (3 - 4) / 5 ^ 6 + 7 / 8";  // 带括号+
		
		new parseExpersion(a1);
		new parseExpersion(a2);
		new parseExpersion(a3);
		new parseExpersion(a4);
	}	
}