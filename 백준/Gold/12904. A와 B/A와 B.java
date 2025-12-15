import java.io.*;
import java.util.*;

public class Main {
    static int N; static String S; static ArrayList<Character> T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        S = br.readLine();
        String line = br.readLine();
        T = new ArrayList<>();
        toArrayList(T, line);
        N = T.size() - S.length();
        for (int i=0;i<N;i++) {
            diffWord();
        }
        int ans = isSame();
        System.out.println(ans);
    }
    static int isSame() {
        for (int i=0;i<S.length();i++) {
            if (S.charAt(i)!=T.get(i)) {
                return 0;
            }
        }
        return 1;
    }
    static void toArrayList(ArrayList<Character> s, String line) {
        for (int i=0;i<line.length();i++) {
            s.add(line.charAt(i));
        }
    }
    static void diffWord() {
        char cur = T.get(T.size()-1);
        T.remove(T.size()-1);
        if (cur=='B') {
            Collections.reverse(T);
        }
    }
}
