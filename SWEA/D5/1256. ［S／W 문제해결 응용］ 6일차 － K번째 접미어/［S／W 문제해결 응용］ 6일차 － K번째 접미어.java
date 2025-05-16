import java.io.*;
import java.util.*;

class Solution
{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc=1;tc<=T;tc++) {
            int count = Integer.parseInt(br.readLine());
            String str = br.readLine();
            int n = str.length();
            String[] arr = new String[n];
            for (int i=0;i<n;i++) {
                arr[i] = str.substring(i);
            }
            Arrays.sort(arr);
            System.out.println("#"+tc+" "+arr[count-1]);
        }
    }
}