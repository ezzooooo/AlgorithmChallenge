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
 * <그림 1>과 같이 정사각형 모양의 지도가 있다.
 * 1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다. 
 * 철수는 이 지도를 가지고 연결된 집들의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다. 
 * 여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다. 대각선상에 집이 있는 경우는 연결된 것이 아니다. 
 * <그림 2>는 <그림 1>을 단지별로 번호를 붙인 것이다. 지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.
 * https://www.acmicpc.net/problem/2667 (그림) 
 * 
 * 입력
 * 첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고, 그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.
 * 
 * 출력
 * 첫 번째 줄에는 총 단지수를 출력하시오. 그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.

예제 입력 1 
7
0110100
0110101
1110101
0000111
0100000
0111110
0111000

예제 출력 1 
3
7
8
9

 * 
 * @author 이윤복
 *
 */
public class CountTown {
	/**
	 * townHash : HashMap<Integer,Integer> => <단지 번호, 단지에 속한 집 갯수> 
	 * isVisited : boolean[][] => 방문한 집들을 확인하기 위한 boolean 배열 
	 * map : int[][] => 지도 배열
	 * n : int => 가로, 세로 길이 
	 * townCount : int => 단지 번호를 구분하기 위한 단지 갯수
	 */
	static HashMap<Integer,Integer> townHash = new HashMap<>();
	static int[][] direction ={{-1,0},{0,-1},{1,0},{0,1}};
	static boolean[][] isVisited;
	static int[][] map; 
	static int n;
	static int townCount = 1;
	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * resultList : List<Integer> => 단지 당 집 갯수를 담을 List 
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
		
		// 하나의 집을 기준으로 이어져 있는 집들을 알아내기 위해서는 DFS를 이용해서 쭉 이어가야함
		// 모든 집을 기준으로 이어져 있는 집들을 알아내야 하기 때문에, 모든 집에 대하여 DFS를 진행해야함.
		// 만약 방문되었거나, 집이 있는 곳이 아니라면 PASS
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
		
		// hashmap에 추가한 단지 번호 : 단지 갯수중 갯수만 List에 추가
		for(Integer key : townHash.keySet()) {
			resultList.add(townHash.get(key));
		}
		// 오름차순 정렬
		Collections.sort(resultList);
		
		// 출력
		System.out.println(townCount-1);
		for(int result : resultList)
			System.out.println(result);
		
		

	}
	
	private static void searchTown(int tempLeftIdx, int tempRightIdx) {
		//단지 내에 마을 하나를 추가한다.
		townHash.put(townCount,townHash.get(townCount)+1);
		
		//인접한 집들중 방문하지 않았던 집들을 방문한다.(대각선에 존재하는 집은 이어져 있는 노드가 아니므로 제외)
		for(int i=0; i<direction.length; i++) {
			int afterLeftIdx = tempLeftIdx + direction[i][0];
			int afterRightIdx = tempRightIdx + direction[i][1]; 
			// 인덱스 범위를 벗어나면 건너뛴다.
			if(afterLeftIdx <0 || afterRightIdx <0 || afterLeftIdx >= n || afterRightIdx >=n)
				continue;
			if(map[afterLeftIdx][afterRightIdx] == 1 && !isVisited[afterLeftIdx][afterRightIdx]) {
				isVisited[afterLeftIdx][afterRightIdx] = true;
				searchTown(afterLeftIdx,afterRightIdx);
			}
		}
		
	}

}
