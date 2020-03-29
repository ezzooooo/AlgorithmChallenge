package tree;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GetPreOrder {
	static int[] inOrder; 
	static int[] postOrder; 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		inOrder = new int[N]; 
		postOrder = new int[N];
		String[] line = br.readLine().split(" ");
		for(int j=0; j<line.length; j++) 
			inOrder[j] = Integer.parseInt(line[j]);
		
		line = br.readLine().split(" "); 
		for(int j=0; j<line.length; j++) 
			postOrder[j] = Integer.parseInt(line[j]);
		
		printPreOrder(0	, postOrder.length-1,0,postOrder.length-1);
		br.close();
	}
	
	private static void printPreOrder(int inStIdx, int inEndIdx, int poStIdx, int poEnIdx) {
		if(poStIdx > poEnIdx || inStIdx > inEndIdx) {
			return;
		}
		
		int root = postOrder[poEnIdx];
		
		//1. root의 inOrder에서 위치를 찾는다, 그 왼쪽 값들은 다 왼쪽에 위치한 노드 들이며 오른쪽 값들은 다 오른쪽에 위치한 노드들이다. 
		int rootIdx = 0;
		for(int i=inStIdx; i<=inEndIdx; i++) {
			if(root == inOrder[i]) {
				rootIdx = i;
				break;
			}
		}
		
		int left = rootIdx - inStIdx - 1;
		int right = inEndIdx - rootIdx - 1;
		
		System.out.print(root + " "); //1. 부모를 출력한다
		printPreOrder(inStIdx, inStIdx + left, poStIdx, poStIdx + left); //2. 왼쪽 자식을 출력한다.
		printPreOrder(inEndIdx - right, inEndIdx, poEnIdx - right - 1, poEnIdx - 1 ); //3. 오른쪽 자식을 출력한다. 
	}
	

}