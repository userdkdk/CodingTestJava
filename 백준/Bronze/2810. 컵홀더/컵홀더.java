import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		char[] T = sc.next().toCharArray();
		int num = 1;
		for (int i=0;i<=T.length-1;i++) {
			if (T[i]=='S') num++;
			else {
				i++;
				num++;
			}
		}
		System.out.println(Math.min(n,num));
	}

}
