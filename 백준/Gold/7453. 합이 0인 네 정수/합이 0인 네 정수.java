import java.util.*;
import java.io.*;

public class Main {
    static int N; static int[][] arr; static int[] arr1, arr2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][4];
        for (int i=0;i<N;i++) {
            st =new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
            arr[i][3] = Integer.parseInt(st.nextToken());
        }

        arr1 = new int[N*N];
        arr2 = new int[N*N];
        int loc = 0;
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) {
                arr1[loc] = arr[i][0]+arr[j][1];
                arr2[loc] = arr[i][2]+arr[j][3];
                loc++;
            }
        }
        loc = 0;
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        long ans = 0;
        while (loc<N*N) {
            int now = arr1[loc];
            int next = loc;
            while (next<N*N && arr1[next]==now) {
                next++;
            }
//            System.out.println(loc+", "+next+", "+now);
            int a = getUpperBound(0,N*N-1,-now);
            int b = getLowerBound(0,N*N-1,-now);
            long tmp = 0;
            if (arr2[a]==-now) {
                tmp+=b-a;
            }
            if (arr2[b]==-now) tmp++;
            ans += ((long) next-loc)*tmp;
//            System.out.println(a+", "+b+", "+arr2[a]+", "+arr2[b]);
            loc = next;
        }

        System.out.println(ans);

    }
    static int getUpperBound(int sta, int las, int value) {
        if (sta>=las) {
            return las;
        }
        int mid = (sta +las)/2;
//        System.out.println(mid);
        if (arr2[mid]<value) {
            return getUpperBound(mid+1,las,value);
        } else {
            return getUpperBound(sta,mid,value);
        }
    }
    static int getLowerBound(int sta, int las, int value) {
        if (sta>=las) {
            return las;
        }
        int mid = (sta +las)/2;
        if (arr2[mid]<=value) {
            return getLowerBound(mid+1,las,value);
        } else {
            return getLowerBound(sta,mid,value);
        }
    }
}
