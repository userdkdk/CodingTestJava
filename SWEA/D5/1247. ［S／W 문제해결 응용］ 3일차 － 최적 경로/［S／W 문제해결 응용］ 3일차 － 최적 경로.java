
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
 
 
public class Solution {
    static int n, ans, tmp_ans; static int[][] cus;
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc=1;tc<=T;tc++) {
        	n = Integer.parseInt(br.readLine());
        	cus = new int[n+2][2];
        	st = new StringTokenizer(br.readLine());
        	int r;
        	int c;
        	r= Integer.parseInt(st.nextToken());
        	c= Integer.parseInt(st.nextToken());
        	cus[0] = new int[]{r,c};
        	r= Integer.parseInt(st.nextToken());
        	c= Integer.parseInt(st.nextToken());
        	cus[n+1] = new int[]{r,c};
        	for (int i=1;i<=n;i++) {
        		r = Integer.parseInt(st.nextToken());
        		c= Integer.parseInt(st.nextToken());
        		cus[i][0] = r;
        		cus[i][1] = c;
        	}
        	ans = Integer.MAX_VALUE;
        	tmp_ans = 0;
        	perm(1);
        	System.out.println("#"+tc+" "+ans);
        }
    }
    static void perm(int depth) {
    	if (depth==n+1) {
    		ans = Math.min(ans, tmp_ans+cal(n+1));
    		return;
    	}
    	if (tmp_ans>ans) return;
    	for (int i=depth;i<=n;i++) {
    		swap(i,depth);
    		tmp_ans += cal(depth);
    		perm(depth+1);
    		tmp_ans -= cal(depth);
    		swap(i,depth);
    	}
    }
    static void swap(int i, int j) {
    	int[] tmp = cus[i];
    	cus[i] = cus[j];
    	cus[j] = tmp;
    }
    static int cal(int i) {
    	return Math.abs(cus[i][0]-cus[i-1][0]) + Math.abs(cus[i][1]-cus[i-1][1]);
    }
}