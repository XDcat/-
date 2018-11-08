package com.xd.iList;

public class SqList implements IList{
	/*
	 * 顺序表
	 */
	private Object[] listElem;
	private int curLen;  // 长度
	public SqList(int maxSize) {
		listElem = new Object[maxSize];
		curLen = 0;
	}
	@Override
	public void clear() {
		curLen = 0;
		
	}
	@Override
	public boolean isEmpty() {
		return curLen == 0;
	}
	@Override
	public int length() {
		return curLen;
	}
	@Override
	public Object get(int i) {
		if (i > 0 && i < curLen -1)
			return listElem[i];
		else
			return null;
	}
	@Override
	public void insert(int i, Object x) throws Exception{
		if (curLen == listElem.length)
			throw new Exception("顺序表已满！");
		if (i > 0 && i < curLen -1){
			for(int j = curLen; j > i; j--){
				listElem[j] = listElem[j - 1];
			}
			listElem[i] = x; 
			curLen++;
		} else {
			throw new Exception("插入位置不合法！");
		}
			
	}
	@Override
	public void remove(int i) throws Exception {
		if (i > 0 && i < curLen -1){
			for(int j = i; j < curLen - 1; j++){
				listElem[j] = listElem[j + 1];
			}
			curLen--; 
		} else {
			throw new Exception("删除位置不合法！");
		}
			
		
	}
	@Override
	public int indexOf(Object x) {
		for (int i = 0; i < curLen -1; i++) {
			if (listElem[i] == x)
				return i;
		}
		return -1;
	}
	@Override
	public void display() {
		for (Object i : listElem) {
			System.out.println(i);
		}
		
	};
	

}
