import java.io.*;
import java.util.*;

public class Main {
    static int N = 4; static int M, S; static int[][][] fishes, moved_fishes;
    static int[] shark; static int[][] smells;
    static int[] fdr = {0,-1,-1,-1,0,1,1,1}; static int[] fdc = {-1,-1,0,1,1,1,0,-1};
    static int[] sdr = {-1,0,1,0}; static int[] sdc = {0,-1,0,1};
    static int max; static int[] sharkRoot, sharkMoved;
    static class Position {
        int r, c, d;
        Position(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        fishes = new int[N][N][8];
        moved_fishes = new int[N][N][8];
        shark = new int[2];
        smells = new int[4][4];

        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int d = Integer.parseInt(st.nextToken())-1;
            fishes[r][c][d]++;
        }
        st = new StringTokenizer(br.readLine());
        shark[0] = Integer.parseInt(st.nextToken())-1;
        shark[1] = Integer.parseInt(st.nextToken())-1;

        for (int s=0;s<S;s++) {
            movingFish();
            movingShark();
            deleteSmell();
            copingFish();
        }
        int ans = 0;
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) {
                ans += countFishes(i,j,fishes);
            }
        }
        System.out.println(ans);
    }
    static void printFishes(int[][][] arr) {
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) {
                System.out.print(countFishes(i,j,arr)+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    static void copingFish() {
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) {
                for (int d=0;d<8;d++) {
                    fishes[i][j][d] += moved_fishes[i][j][d];
                }
            }
        }
    }
    static void movingShark() {
        max = -1;
        sharkRoot = new int[3];
        sharkMoved = new int[3];
        int[][] fish_count = new int[N][N];
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) {
                fish_count[i][j] = countFishes(i,j,moved_fishes);
            }
        }
        // find shark moving
        dfs(shark[0],shark[1],0,0, fish_count);

        // delete fish and move shark
        deleteFish();
    }
    static void deleteFish() {
        for (int t=0;t<3;t++) {
            shark[0] += sdr[sharkMoved[t]];
            shark[1] += sdc[sharkMoved[t]];
            int count = countFishes(shark[0],shark[1], moved_fishes);
            if (count>0) {
                for (int d=0;d<8;d++) {
                    moved_fishes[shark[0]][shark[1]][d] = 0;
                }
                smells[shark[0]][shark[1]] = 3;
            }
        }
    }
    static void dfs(int r, int c, int moved, int count, int[][] fish_count) {
        if (moved==3) {
            if (max<count) {
                max = count;
                sharkMoved = Arrays.copyOfRange(sharkRoot,0,3);
            }
            return;
        }
        for (int d=0;d<4;d++) {
            int nr = r + sdr[d];
            int nc = c + sdc[d];
            if (nr<0 || nr>=N || nc<0 || nc>=N) continue;
            sharkRoot[moved] = d;
            int cur = fish_count[nr][nc];
            fish_count[nr][nc] = 0;
            dfs(nr,nc,moved+1,count+cur,fish_count);
            fish_count[nr][nc] = cur;
        }
    }
    static int countFishes(int r, int c, int[][][] arr) {
        int ans = 0;
        for (int d=0;d<8;d++) {
            ans += arr[r][c][d];
        }
        return ans;
    }
    static void deleteSmell() {
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) {
                if (smells[i][j]>0) smells[i][j]--;
            }
        }
    }
    static void movingFish() {
        moved_fishes = new int[N][N][8];
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) {
                for (int d=0;d<8;d++) {
                    if (fishes[i][j][d]==0) continue;
                    Position pos = getPosition(i,j,d);
                    moved_fishes[pos.r][pos.c][pos.d] += fishes[i][j][d];
                }
            }
        }
    }
    static Position getPosition(int r, int c, int d) {
        for (int count=0;count<8;count++) {
            int nd = (d+8-count)%8;
            int nr = r + fdr[nd];
            int nc = c + fdc[nd];
            if (nr<0 || nr>=N || nc<0 || nc>=N) continue;
            if (smells[nr][nc]>0) continue;
            if (nr == shark[0] && nc == shark[1]) continue;
            return new Position(nr,nc,nd);
        }
        return new Position(r,c,d);
    }
}
