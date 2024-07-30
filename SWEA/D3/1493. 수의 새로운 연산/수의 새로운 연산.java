
import java.util.Scanner;
import java.io.FileInputStream;

/*
   (1,x)에 해당하는 숫자 배열을 먼저 선언한 후에, 배열을 통해 입력값의 좌표 탐색 후
   해당 좌표를 통해 결과값 도출 및 반환
 */
class Solution
{
	public static void main(String args[]) throws Exception
	{
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
        // (1,?) 좌표에 해당하는 값 배열 생성
		int[] arr = new int[300];
		arr[0]=1;
		for (int i = 1; i < 300; i++) {
			arr[i] = arr[i-1]+i-1;
		}

		for (int tc = 1; tc <= T; tc++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int[] get_x = star1(arr, x);
			int[] get_y = star1(arr, y);
			int[] get_res = {get_x[0]+get_y[0],get_x[1]+get_y[1]};
            // 값 출력
			System.out.println("#"+tc+" "+(arr[get_res[0]+get_res[1]-1]+get_res[0]-1));
		}
	}
	// tar의 좌표 반환
	static int[] star1(int[] arr, int tar) {
		int sta = 0;
		int las = arr.length-1;
		while (sta<las) {
			int mid = (sta+las)/2;
			if (arr[mid]==tar) {
				int[] res = {1,mid};
				return res;
			}
			else if (arr[mid]<tar) sta = mid+1;
			else las = mid-1;
		}
		while (arr[sta]>tar) {
			sta--;
		}
		int[] res = {1+tar-arr[sta],sta-(tar-arr[sta])};
		return res;
	}
}
