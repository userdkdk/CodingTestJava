
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
		String[] coord = br.readLine().split(" ");
		int w = Integer.parseInt(coord[0]);
		int h = Integer.parseInt(coord[1]);
		String[] xy = br.readLine().split(" ");
		int x = Integer.parseInt(xy[0]);
		int y = Integer.parseInt(xy[1]);
		int t = Integer.parseInt(br.readLine());
		int ans_x = (x+t)%(2*w);
		ans_x = ans_x<=w ? ans_x : 2*w-ans_x;
		int ans_y = (y+t)%(2*h);
		ans_y = ans_y<=h ? ans_y : 2*h-ans_y;
		
		
		System.out.println(ans_x+" "+ans_y);
	}
}
