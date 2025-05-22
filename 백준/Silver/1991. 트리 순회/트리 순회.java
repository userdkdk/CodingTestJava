import java.io.*;
import java.util.*;

class Main
{
    static int n; static int[][] arr; static Queue<Integer> q, preorder, inorder, postorder;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1][2];
        for (int i=1;i<=n;i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j=2;j<=4;j++) {
                int tmp = ch[j]-'A';
                if (tmp<0) continue;
                arr[ch[0]-'A'+1][j/2-1] = tmp+1;
            }
        }

        q= new LinkedList<>();
        preorder= new LinkedList<>();
        inorder= new LinkedList<>();
        postorder= new LinkedList<>();
        q.add(1);
        middle();
        print();
    }
    static void middle() {
        int x = q.poll();
        preorder.add(x);
        if (arr[x][0]!=0) {
            q.add(arr[x][0]);
            middle();
        }
        inorder.add(x);
        if (arr[x][1]!=0) {
            q.add(arr[x][1]);
            middle();
        }
        postorder.add(x);
    }
    static void print() {
        for (int i=0;i<n;i++) {
            System.out.print((char) (preorder.poll()+'A'-1));
        }
        System.out.println();
        for (int i=0;i<n;i++) {
            System.out.print((char) (inorder.poll()+'A'-1));
        }
        System.out.println();
        for (int i=0;i<n;i++) {
            System.out.print((char) (postorder.poll()+'A'-1));
        }
    }
}