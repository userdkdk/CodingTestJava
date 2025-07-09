import java.util.*;
import java.io.*;

public class Main {
    static int n; static Leaf root, now; static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in)) ;
        StringTokenizer st;
        String line;
        root = new Leaf(Integer.parseInt(br.readLine()));

        while (true) {
            line = br.readLine();
            if (line==null || line.isEmpty()) break;
            root.insert(Integer.parseInt(line));
        }

        print(root);
    }
    static void print(Leaf now) {

        if (now.left != null) {
            print(now.left);
        }
        if (now.right != null) {
            print(now.right);
        }
        System.out.println(now.e);

    }

    static class Leaf {
        int e, lev;
        Leaf left, right, p;
        Leaf(int e) {
            this.e = e;
        }

        void insert(int num) {
            if (num < this.e) {
                if (this.left == null) {
                    this.left = new Leaf(num);
                } else {
                    this.left.insert(num);
                }
            } else {
                if (this.right == null) {
                    this.right = new Leaf(num);
                } else {
                    this.right.insert(num);
                }
            }
        }

        @Override
        public String toString() {
            return e + ", ";// + left.e +", " + right.e;
        }
    }
}
