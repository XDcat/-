package com.xd.huffman;


public class HuffmanTree{
	public int[][] huffmanCoidng(int[] W) {
		/*
		 * 获取huffman编码
		 * 传入字符的权值
		 */
		int n = W.length;
		int m = n*2 -1;  // 树节点的个数
		HuffmanNode[] HN = new HuffmanNode[m];
		int i;
		for (i = 0; i < n; i++)
			// 初始化为节点
			HN[i] = new HuffmanNode(W[i]);
		for (i = n; i < m; i++){
			// 构造新的节点
			// 选择最小的两个节点
			HuffmanNode min1 = selectMin(HN, i - 1);
			min1.flag = 1;
			HuffmanNode min2 = selectMin(HN, i - 1);
			min2.flag = 1;
			// 构造父节点
			HN[i] = new HuffmanNode();
			min1.parent = HN[i];
			min2.parent = HN[i];
			HN[i].lchild = min1;
			HN[i].rchild = min2;
			HN[i].weight = min1.weight + min2.weight;
		}
		int[][] HuffCode = new int[n][n];
		for (int j = 0; j < n; j++){
			int start = n -1;
			for (HuffmanNode c = HN[j], p = c.parent; p != null; c = p, p = p.parent){
				// 从叶子到根求编码
				if (p.lchild.equals(c))
					HuffCode[j][start--] = 0;
				else
					HuffCode[j][start--] = 1;
			}
			HuffCode[j][start] = -1;
		}
		return HuffCode;
	}
	private HuffmanNode selectMin(HuffmanNode[] HN, int end) {
		// 找到树中最小的节点
		HuffmanNode min = HN[end];
		for (int i = 0; i <= end; i++){
			HuffmanNode h = HN[i];
			if (h.flag == 0 && h.weight < min.weight)
				// 不在树中最小的节点
				min = h;
		}
		return min;
	}
	public static void main(String[] args) {
		// 权值列表
		int[] W = {23, 11, 5, 3, 29, 14, 7, 8};
		HuffmanTree T = new HuffmanTree();  // 创建对象
		int[][]HN = T.huffmanCoidng(W);  // 获取编码
		System.out.println("HuffMan编码：");
		for(int i = 0; i < HN.length; i++){
			System.out.print(W[i] + ": ");
			for (int j = 0; j < HN[i].length; j++) {
				if (HN[i][j] == -1 ){  // 开始标志开始读
					for(int k = j + 1; k < HN[i].length; k++)
						System.out.print(HN[i][k]);  // 输出
				break;
				}

			}
			System.out.println();  // 换行
		}
	}
}