
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;


public class Solution {
	static int[][] arr;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc=1;tc<=T;tc++) {
        	int n = Integer.parseInt(br.readLine());
        	arr = new int[4][8];
        	for (int i=0;i<4;i++) {
        		st = new StringTokenizer(br.readLine()," ");
        		for (int j=0;j<8;j++) {
        			arr[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
        	for (int i=0;i<n;i++) {
        		st = new StringTokenizer(br.readLine()," ");
        		int loc = Integer.parseInt(st.nextToken())-1;
        		int dir = Integer.parseInt(st.nextToken());
        		toRight(loc,dir);
        		if (dir!=1) {
            		rotClock(loc);
            	} else {
            		rotReverse(loc);
            	}
        		toLeft(loc,dir);
        	}
        	int ans = 0;
        	for (int i=0;i<4;i++) {
        		if (arr[i][0]==1) {
        			ans += (int) Math.pow(2,i);
        		}
        	}
        	System.out.println("#"+tc+" "+ans);
        }
    }
    static void toRight(int loc, int dir) {
    	if (loc<3 && arr[loc][2]!=arr[loc+1][6]) {
    		toRight(loc+1,-dir);
    	}
    	
    	if (dir==1) {
    		rotClock(loc);
    	} else {
    		rotReverse(loc);
    	}
    }
    static void toLeft(int loc, int dir) {
    	if (loc>0 && arr[loc][6]!=arr[loc-1][2]) {
    		toLeft(loc-1,-dir);
    	}

    	if (dir==1) {
    		rotClock(loc);
    	} else {
    		rotReverse(loc);
    	}
    }
    static void rotClock(int loc) {
    	int temp = arr[loc][7];
    	for (int i=7;i>0;i--) {
    		arr[loc][i] = arr[loc][i-1];
    	}
    	arr[loc][0]=temp;
    }
    static void rotReverse(int loc) {
    	int temp = arr[loc][0];
    	for (int i=0;i<7;i++) {
    		arr[loc][i] = arr[loc][i+1];
    	}
    	arr[loc][7]=temp;
    }
}
