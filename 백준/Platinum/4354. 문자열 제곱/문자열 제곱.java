import java.util.*;
import java.io.*;

public class Main {
    static int N; static char[] S; static int[] pi;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        S = br.readLine().toCharArray();
        while (S[0]!='.') {
            N = S.length;
            pi = new int[N];
            getPi();
            if (pi[N-1]==0) System.out.println(1);
            else {
                int len = N - pi[N-1];
                if (N%len>0) System.out.println(1);
                else System.out.println(N/len);
            }

            S = br.readLine().toCharArray();
        }
    }
    static void getPi() {
        int j = 0;
        for (int i=1;i<N;i++) {
            while (j>0 && S[i]!=S[j]) j = pi[j-1];
            if (S[i]==S[j]) j++;
            pi[i] = j;
        }
    }

}
