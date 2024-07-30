import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		int[] arr = new int[T];
		StringTokenizer arrs =new StringTokenizer(br.readLine());
		for (int i=0;i<T;i++) {
			arr[i] = Integer.parseInt(arrs.nextToken());
		}
		int count = Integer.parseInt(br.readLine());
		int per;
		int num;
		for (int i=0;i<count;i++) {
			StringTokenizer st =new StringTokenizer(br.readLine());
			per = Integer.parseInt(st.nextToken());
			num = Integer.parseInt(st.nextToken());
			if (per == 1) {
				for (int j=num-1;j<T;j+=num) {
					arr[j] = Math.abs(arr[j]-1);
				}
			} else {
				arr[num-1] = Math.abs(arr[num-1]-1);
				int comp = 1;
				if (num-comp-1<0 || num+comp-1>=T) continue;
				while (arr[num-comp-1]==arr[num+comp-1]) {
					arr[num-comp-1] = Math.abs(arr[num-comp-1]-1);
					arr[num+comp-1] = Math.abs(arr[num+comp-1]-1);
					comp++;
					if (num-comp-1<0 || num+comp-1>=T) break;
				}
			}
		}
		for (int i=0;i<arr.length;i++) {
			System.out.printf(arr[i]+" ");
			if ((i+1)%20==0) System.out.println();
		}
	}
}