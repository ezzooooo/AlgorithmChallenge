import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * ����
 * N-Queen ������ ũ�Ⱑ N �� N�� ü���� ���� �� N���� ���� ������ �� ���� ���� �����̴�.
 * N�� �־����� ��, ���� ���� ����� ���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.
 * 
 * �Է�
 * ù° �ٿ� N�� �־�����. (1 �� N < 15)
 * 
 * ���
 * ù° �ٿ� �� N���� ���� ������ �� ���� ���� ����� ���� ����Ѵ�.
 * 
 * @author ������
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
		if(queenCount == n) { // queen�� ������ n���� ����! base-case
			return 1;
		}
		
		for(int x=0; x<n; x++) {
				if(!checkInOther(queenCount,x) && queenCount!=0) continue; // queen�� ���� ������ �ǳʶ�
				queenLoc[queenCount] = x; //queen üũ 
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
