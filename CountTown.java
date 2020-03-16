package dfsbfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * <�׸� 1>�� ���� ���簢�� ����� ������ �ִ�.
 * 1�� ���� �ִ� ����, 0�� ���� ���� ���� ��Ÿ����. 
 * ö���� �� ������ ������ ����� ������ ������ ������ �����ϰ�, ������ ��ȣ�� ���̷� �Ѵ�. 
 * ���⼭ ����Ǿ��ٴ� ���� � ���� �¿�, Ȥ�� �Ʒ����� �ٸ� ���� �ִ� ��츦 ���Ѵ�. �밢���� ���� �ִ� ���� ����� ���� �ƴϴ�. 
 * <�׸� 2>�� <�׸� 1>�� �������� ��ȣ�� ���� ���̴�. ������ �Է��Ͽ� �������� ����ϰ�, �� ������ ���ϴ� ���� ���� ������������ �����Ͽ� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.
 * https://www.acmicpc.net/problem/2667 (�׸�) 
 * 
 * �Է�
 * ù ��° �ٿ��� ������ ũ�� N(���簢���̹Ƿ� ���ο� ������ ũ��� ������ 5��N��25)�� �Էµǰ�, �� ���� N�ٿ��� ���� N���� �ڷ�(0Ȥ�� 1)�� �Էµȴ�.
 * 
 * ���
 * ù ��° �ٿ��� �� �������� ����Ͻÿ�. �׸��� �� ������ ���� ���� ������������ �����Ͽ� �� �ٿ� �ϳ��� ����Ͻÿ�.

���� �Է� 1 
7
0110100
0110101
1110101
0000111
0100000
0111110
0111000

���� ��� 1 
3
7
8
9

 * 
 * @author ������
 *
 */
public class CountTown {
	/**
	 * townHash : HashMap<Integer,Integer> => <���� ��ȣ, ������ ���� �� ����> 
	 * isVisited : boolean[][] => �湮�� ������ Ȯ���ϱ� ���� boolean �迭 
	 * map : int[][] => ���� �迭
	 * n : int => ����, ���� ���� 
	 * townCount : int => ���� ��ȣ�� �����ϱ� ���� ���� ����
	 */
	static HashMap<Integer,Integer> townHash = new HashMap<>();
	static int[][] direction ={{-1,0},{0,-1},{1,0},{0,1}};
	static boolean[][] isVisited;
	static int[][] map; 
	static int n;
	static int townCount = 1;
	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * resultList : List<Integer> => ���� �� �� ������ ���� List 
		 */
		List<Integer> resultList = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); 
		map = new int[n][n]; 
		isVisited = new boolean[n][n];
		
		for(int i=0; i<n; i++) { //input data
			String line = br.readLine();
			for(int j=0; j<n; j++) {
				map[i][j] = line.charAt(j)-'0';
			}
		}
		
		// �ϳ��� ���� �������� �̾��� �ִ� ������ �˾Ƴ��� ���ؼ��� DFS�� �̿��ؼ� �� �̾����
		// ��� ���� �������� �̾��� �ִ� ������ �˾Ƴ��� �ϱ� ������, ��� ���� ���Ͽ� DFS�� �����ؾ���.
		// ���� �湮�Ǿ��ų�, ���� �ִ� ���� �ƴ϶�� PASS
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(!isVisited[i][j] && map[i][j] == 1) {
					townHash.put(townCount, 0);
					isVisited[i][j] = true;
					searchTown(i,j);
					townCount++;
				}
			}
		}
		
		// hashmap�� �߰��� ���� ��ȣ : ���� ������ ������ List�� �߰�
		for(Integer key : townHash.keySet()) {
			resultList.add(townHash.get(key));
		}
		// �������� ����
		Collections.sort(resultList);
		
		// ���
		System.out.println(townCount-1);
		for(int result : resultList)
			System.out.println(result);
		
		

	}
	
	private static void searchTown(int tempLeftIdx, int tempRightIdx) {
		//���� ���� ���� �ϳ��� �߰��Ѵ�.
		townHash.put(townCount,townHash.get(townCount)+1);
		
		//������ ������ �湮���� �ʾҴ� ������ �湮�Ѵ�.(�밢���� �����ϴ� ���� �̾��� �ִ� ��尡 �ƴϹǷ� ����)
		for(int i=0; i<direction.length; i++) {
			int afterLeftIdx = tempLeftIdx + direction[i][0];
			int afterRightIdx = tempRightIdx + direction[i][1]; 
			// �ε��� ������ ����� �ǳʶڴ�.
			if(afterLeftIdx <0 || afterRightIdx <0 || afterLeftIdx >= n || afterRightIdx >=n)
				continue;
			if(map[afterLeftIdx][afterRightIdx] == 1 && !isVisited[afterLeftIdx][afterRightIdx]) {
				isVisited[afterLeftIdx][afterRightIdx] = true;
				searchTown(afterLeftIdx,afterRightIdx);
			}
		}
		
	}

}
