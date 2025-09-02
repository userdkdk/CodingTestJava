import java.util.*;
import java.io.*;

public class Main {
    static int L; static char[] T; static int[] pi;
    static ArrayList<Integer> ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        L = Integer.parseInt(br.readLine());
        T = br.readLine().toCharArray();
        pi = new int[L];
        getPi();
        System.out.println(L - pi[L-1]);
    }
    static void getPi() {
        int j = 0;
        for (int i=1;i<L;i++) {
            while (j>0 && T[i]!=T[j]) j = pi[j-1];
            if (T[i]==T[j]) j++;
            pi[i] = j;
        }
    }
}
