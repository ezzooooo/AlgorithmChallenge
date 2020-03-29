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
		
		//1. root�� inOrder���� ��ġ�� ã�´�, �� ���� ������ �� ���ʿ� ��ġ�� ��� ���̸� ������ ������ �� �����ʿ� ��ġ�� �����̴�. 
		int rootIdx = 0;
		for(int i=inStIdx; i<=inEndIdx; i++) {
			if(root == inOrder[i]) {
				rootIdx = i;
				break;
			}
		}
		
		int left = rootIdx - inStIdx - 1;
		int right = inEndIdx - rootIdx - 1;
		
		System.out.print(root + " "); //1. �θ� ����Ѵ�
		printPreOrder(inStIdx, inStIdx + left, poStIdx, poStIdx + left); //2. ���� �ڽ��� ����Ѵ�.
		printPreOrder(inEndIdx - right, inEndIdx, poEnIdx - right - 1, poEnIdx - 1 ); //3. ������ �ڽ��� ����Ѵ�. 
	}
	

}