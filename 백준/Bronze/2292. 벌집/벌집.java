import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[20000];
		arr[1] = 1;
		for (int i=2;i<20000;i++) {
			arr[i] = arr[i-1]+6*(i-1); 
		}
		System.out.println(bynaryS(arr,N));
		
	}
	static int bynaryS(int[] arr, int tar) {
		int sta = 0;
		int las = arr.length-1;
		int mid = 0;
		while (sta<=las) {
			mid = (las+sta)/2;
			if (arr[mid]==tar) {
				return mid;
			} else if (arr[mid]<tar) {
				sta = mid+1;
			} else {
				las = mid -1;
			}
		}
		while (arr[mid]>tar) {
			mid--;
		}
		return mid+1;
	}
}
