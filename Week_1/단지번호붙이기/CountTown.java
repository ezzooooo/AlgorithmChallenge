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
