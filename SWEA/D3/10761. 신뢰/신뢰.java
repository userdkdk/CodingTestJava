import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	static int ans, step;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc=1;tc<=T;tc++) {
        	StringTokenizer st = new StringTokenizer(br.readLine()," ");
        	int n = Integer.parseInt(st.nextToken());
        	
        	ans = 0;
        	step = 0;
        	Robot b = new Robot("B");
        	Robot o = new Robot("O");
        	String before = "";
        	for (int i=0;i<n;i++) {
        		String rb = st.nextToken();
        		if (i==0) {
        			before = rb;
        		}
        		int next = Integer.parseInt(st.nextToken());
        		if (rb.equals("B")) {
        			b.getloc(before, next);
        		} else {
        			o.getloc(before, next);
        		}
        		before = rb;
        	}
        	
        	
        	System.out.println("#"+tc+" "+ans);
        }
         
    }
    
    static class Robot {
    	int loc = 1;
    	String name;
    	public Robot() {}
    	
    	public Robot(String name) {
    		super();
    		this.name = name;
    	}
    	
    	public void getloc(String before, int next) {
    		if (this.name.equals(before)) {
    			int distance = Math.abs(next- this.loc);
    			step += distance +1;
    			ans += distance +1;
    			
    		} else {
    			step -= Math.abs(next-this.loc);
    			if (step>=0) {
    				ans +=1;
    				step =1;
    			} else {
    				step--;
    				ans -= step;
    				step = -step;
    			}
    		}
			this.loc = next;
    	}
    }
}