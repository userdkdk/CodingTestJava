import java.io.*;
import java.util.*;

public class Main {
    static int N; static Fish[] fishes; static int ans; static Fish shark;
    static int[] dr = {-1,-1,0,1,1,1,0,-1}; static int[] dc = {0,-1,-1,-1,0,1,1,1};
    static class Fish {
        int r, c, d;
        boolean isDead;
        public Fish(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.isDead = false;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = 4;
        int[][] map = new int[N][N];
        fishes = new Fish[N*N+1];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken())-1;
                fishes[num] = new Fish(i,j,dir);
                map[i][j] = num;
            }
        }
        ans = 0;
        int num = map[0][0];
        shark = new Fish(0,0,fishes[num].d);
        fishes[num].isDead = true;
        moving(0,num,map);
        System.out.println(ans);
    }
    static void moving(int t, int sums, int[][] map) {
        ans = Math.max(ans,sums);
        if (t==16) {
            return;
        }
        int original_num = map[shark.r][shark.c];
        int[][] modified_map = new int[N][N];
        for (int i=0;i<N;i++) {
            modified_map[i] = Arrays.copyOfRange(map[i],0,N);
        }
        // move fish
        int moveCount = movingFish(modified_map);

        // use for, shark move and point
        int r = shark.r; int c = shark.c; int d = shark.d;
        for (int i=1;i<N;i++) {
            int nr = r + dr[d]*i;
            int nc = c + dc[d]*i;
            if (!canGo(nr,nc)) break;
            int next_num = modified_map[nr][nc];
            if (fishes[next_num].isDead) continue;
            fishes[next_num].isDead = true;
            shark.r = nr;
            shark.c = nc;
            shark.d = fishes[next_num].d;
            moving(t+1,sums+next_num,modified_map);
            fishes[next_num].isDead = false;
            shark.r = r;
            shark.c = c;
            shark.d = d;
        }
        // rollback fish
        rollback(moveCount);
    }
    static Deque<Runnable> dq = new ArrayDeque<>();
    static void moveFish(Fish f, int nr, int nc, int nd) {
        int pr = f.r; int pc = f.c; int pd = f.d;
        f.r = nr; f.c = nc; f.d = nd;
        dq.push(()->{f.r = pr; f.c=pc; f.d=pd;});
    }
    static void rollback(int size) {
        for (int i=0;i<size;i++) {
            dq.pop().run();
        }
    }
    static int movingFish(int[][] map) {
        int count = 0;
        for (int i=1;i<=16;i++) {
            if (fishes[i].isDead) continue;
            Fish cur = fishes[i];
            int r = cur.r;
            int c = cur.c;
            int dir = cur.d;
            for (int d=0;d<8;d++) {
                int nd = (dir+d)%8;
                int nr = r + dr[nd];
                int nc = c + dc[nd];
                if (!canGo(nr,nc)) continue;
                int next_num = map[nr][nc];
                if (next_num>0 && !fishes[next_num].isDead) {
                    Fish nextFish = fishes[next_num];
                    moveFish(nextFish,r,c,nextFish.d);
                    count++;
                }
                map[r][c] = next_num;
                moveFish(cur,nr,nc,nd);
                map[nr][nc] = i;
                count++;
                break;
            }
        }
        return count;
    }
    static boolean canGo(int a, int b) {
        if (a<0 || a>=N || b<0 || b>=N) return false;
        if (a==shark.r && b==shark.c) return false;
        return true;
    }
}
