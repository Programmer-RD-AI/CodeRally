import java.io.*;
import java.util.*;
import java.text.*;



public class Solution {



   public static void main(String args[]) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int testCases = Integer.parseInt(br.readLine().trim());
       br.readLine(); // Read the blank line after the number of test cases.

       while (testCases-- > 0) {
           int n = Integer.parseInt(br.readLine().trim()); // Number of freckles
           double[][] points = new double[n][2]; // Stores (x, y) coordinates of freckles



           // Read the freckles coordinates
           for (int i = 0; i < n; i++) {
               String[] coords = br.readLine().split("\\s+");
               points[i][0] = Double.parseDouble(coords[0]);
               points[i][1] = Double.parseDouble(coords[1]);
           }



           // Blank line between consecutive test cases
           if (testCases > 0) br.readLine();



           // Prim's algorithm to find MST
           double result = prim(points, n);



           // Print result with two decimal places
           System.out.printf("%.2f\n", result);



           if (testCases > 0) {
               System.out.println();
           }
       }
   }



   public static double prim(double[][] points, int n) {
       boolean[] inMST = new boolean[n]; // Tracks which points are in the MST
       double[] minDist = new double[n]; // minDist[i] gives the minimum distance to include point i in the MST
       Arrays.fill(minDist, Double.MAX_VALUE);
       minDist[0] = 0; // Start from the first point



       PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble(edge -> edge.distance));
       pq.add(new Edge(0, 0));



       double totalLength = 0;



       while (!pq.isEmpty()) {
           Edge current = pq.poll();
           int u = current.to;



           // Skip if the point is already in the MST
           if (inMST[u]) continue;



           // Include point u in the MST
           inMST[u] = true;
           totalLength += current.distance;



           // Update distances of the remaining points
           for (int v = 0; v < n; v++) {
               if (!inMST[v]) {
                   double dist = euclideanDistance(points[u], points[v]);
                   if (dist < minDist[v]) {
                       minDist[v] = dist;
                       pq.add(new Edge(v, dist));
                   }
               }
           }
       }



       return totalLength;
   }



   // Function to compute Euclidean distance between two points
   public static double euclideanDistance(double[] point1, double[] point2) {
       return Math.sqrt(Math.pow(point1[0] - point2[0], 2) + Math.pow(point1[1] - point2[1], 2));
   }



   // Class to represent an edge with a destination node and a distance
   static class Edge {
       int to;
       double distance;



       public Edge(int to, double distance) {
           this.to = to;
           this.distance = distance;
       }
   }
}
 