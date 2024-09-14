import java.io.*;
import java.util.*;

public class Solution {
    static int[] parent; // Union-Find parent array
    static int[] rank; // Rank array to keep track of tree height

    // Find function with path compression
    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Union function with union by rank
    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    // Union-Find with modifications for enemies
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] input;

        // Read number of people
        int n = Integer.parseInt(br.readLine());

        // Union-Find arrays
        parent = new int[2 * n];
        rank = new int[2 * n];

        // Initialize Union-Find sets
        for (int i = 0; i < 2 * n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        while (true) {
            input = br.readLine().split(" ");
            int c = Integer.parseInt(input[0]);
            int x = Integer.parseInt(input[1]);
            int y = Integer.parseInt(input[2]);

            if (c == 0 && x == 0 && y == 0)
                break;

            if (x >= n || y >= n) {
                sb.append("-1\n");
                continue;
            }

            int xFriend = x;
            int xEnemy = x + n;
            int yFriend = y;
            int yEnemy = y + n;

            if (c == 1) { // setFriends(x, y)
                if (find(xFriend) == find(yEnemy)) {
                    sb.append("-1\n");
                } else {
                    union(xFriend, yFriend);
                    union(xEnemy, yEnemy);
                }
            } else if (c == 2) { // setEnemies(x, y)
                if (find(xFriend) == find(yFriend)) {
                    sb.append("-1\n");
                } else {
                    union(xFriend, yEnemy);
                    union(xEnemy, yFriend);
                }
            } else if (c == 3) { // areFriends(x, y)
                if (find(xFriend) == find(yFriend)) {
                    sb.append("1\n");
                } else {
                    sb.append("0\n");
                }
            } else if (c == 4) { // areEnemies(x, y)
                if (find(xFriend) == find(yEnemy)) {
                    sb.append("1\n");
                } else {
                    sb.append("0\n");
                }
            }
        }
        System.out.print(sb.toString());
    }
}
