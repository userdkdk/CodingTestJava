
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringBuilder sb = new StringBuilder();

		for (int i=0;i<str.length();i++) {
			if (str.charAt(i)=='<') {
				while (str.charAt(i)!='>') {
					sb.append(str.charAt(i++));
				}
				sb.append('>');
			} else if (str.charAt(i)==' ') {
				sb.append(" ");
			} else {
				StringBuilder tmp = new StringBuilder();
				while (i<str.length() && str.charAt(i)!=' ' && str.charAt(i)!='<') {
					tmp.insert(0, str.charAt(i++));
				}
				sb.append(tmp);
				if (i<str.length()) i--;
				
			}
		}
		System.out.print(sb);
		
	}
}
