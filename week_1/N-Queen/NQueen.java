import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 문제
 * N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.
 * N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 N이 주어진다. (1 ≤ N < 15)
 * 
 * 출력
 * 첫째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.
 * 
 * @author 이윤복
 *
 */
public class NQueen {
	static int count = 0;
	static int n;
	static int[] queenLoc = new int[15];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		System.out.println(recursionFunc(0,0));
	}
	
	private static int recursionFunc(int queenCount, int rightIdx) {
		int ret = 0;
		if(queenCount == n) { // queen의 갯수가 n개면 정답! base-case
			return 1;
		}
		
		for(int x=0; x<n; x++) {
				if(!checkInOther(queenCount,x) && queenCount!=0) continue; // queen의 공격 범위면 건너뜀
				queenLoc[queenCount] = x; //queen 체크 
				ret += recursionFunc(queenCount+1,x+1); //recursion-case
		}
			
		return ret; 
	}
	
	private static boolean checkInOther(int queenCount, int x) {
		for(int i=0; i<queenCount; i++) {
			if(queenLoc[i] == x) 
				return false;
			if(Math.abs(i-queenCount) == Math.abs(queenLoc[i]-x))
				return false;
		}
		return true;
	}

	
	
	

}
