import java.util.*;
import java.io.*;

public class Main {
    static int N, idx, len; static int[] arr = new int[1000012];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        Arrays.fill(arr,Integer.MAX_VALUE);
        arr[0] = 0;

        st = new StringTokenizer(br.readLine());
        arr[1] = Integer.parseInt(st.nextToken());
        idx = 1;
        len = 1;
        for (int i=1;i<N;i++) {
            int value = Integer.parseInt(st.nextToken());
            int loc = binary(0,len+1,value);
            arr[loc] = value;
            idx = loc;
            len = Math.max(len,idx);
        }
        System.out.println(len);
    }
    static int binary(int sta, int las, int value) {
        int mid = (sta + las)/2;
        if (sta>=las) return las;
        if (value == arr[mid]) return mid;
        if (value > arr[mid]) {
            return binary(mid+1,las,value);
        } else {
            return binary(sta,mid,value);
        }
    }
}
