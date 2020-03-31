package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/*
 * https://www.acmicpc.net/problem/1722
 */
public class Permutation {
	static int[] permutation;
	static boolean[] isChecked; 
	static long order; 
	static List<Integer> tempPermutation = new ArrayList<>(); 
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		isChecked = new boolean[N];
		String[] readLine = br.readLine().split(" ");
		switch(readLine[0]) {
			case "1":
				order = Long.parseLong(readLine[1]);
				getPermutation(0, order); 
				break;
			case "2":
				permutation = new int[N];
				for(int i=1; i<=N; i++) permutation[i-1] = Integer.parseInt(readLine[i]);
				getOrder(0,1);
				break;
			default :
				return;
		}
			
	}
	
	private static void getPermutation(int locate, long order) {
		if(locate == N) {
			for(int i=0; i<tempPermutation.size(); i++) 
				System.out.print(tempPermutation.get(i) + " ");
			return;
		}
		
		int cntIdx = 0;
		long total = 0;
		if(order == 0) cntIdx = N - locate - 1;
		else {
			total = Factorial(N - locate - 1);
			cntIdx = (int) (order / total);
			if(order % total == 0) cntIdx -= 1;
		}
			
		//3. 해당 숫자값을 구한다.
		int tempCntIdx = -1;
		for(int i=0; i<N; i++) {	
			if(isChecked[i] == false) {
				tempCntIdx++;
				if(tempCntIdx == cntIdx) {
					tempPermutation.add(i+1);
					isChecked[i] = true;
					break;
				}
			}
		}
		
		long nextOrder = 0;
		if(order != 0) nextOrder = order % total;
		getPermutation(locate+1,nextOrder);
	}
	
	private static void getOrder(int locate, long result) {
		if(locate == N-1) {
			System.out.println(result);
			return;
		}
		
		// 1. 다음번째 숫자부터 끝까지의 모든 경우의 수를 구한다. 
		long total = Factorial(N-locate-1);
		//  2. 현재 위치의 숫자를 구한다.
		int temp = permutation[locate]; 
		// 3. 몇번째인지 확인한다.
		long cnt = 0;
		for(int i=0; i<temp-1; i++) 
			if(isChecked[i] == false) cnt++;	
		
		
		if(cnt > 0) result += cnt * total;
		isChecked[temp-1] = true;
		getOrder(locate+1, result);
	}
	
	private static long Factorial(int n) {
		long result = 1;
		for(long i=n; i>0; i--) result *= i;
		return result;
	}
}
