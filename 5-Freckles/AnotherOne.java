import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;



public class Solution {



  static HashMap<Integer, HashMap<Integer, Double>> adjacencyList = new HashMap<Integer, HashMap<Integer, Double>>();



  public static void main(String[] args) /* throws FileNotFoundException */{
    // System.setIn(new FileInputStream(new File("test.txt")));



    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    double[][] coordinates;



    for (int i = 0; i < n; i++) {
      int freckles = sc.nextInt();



      coordinates = new double[freckles][2];



      for (int j = 0; j < freckles; j++) {
        coordinates[j][0] = sc.nextDouble();
        coordinates[j][1] = sc.nextDouble();
      }



      for (int k = 0; k < coordinates.length; k++) {
        for (int l = 0; l < coordinates.length; l++) {
          if (l == k)
            continue;
          double distance = Math.sqrt((Math.pow(coordinates[k][0] - coordinates[l][0], 2) + Math.pow(
              coordinates[k][1] - coordinates[l][1], 2)));
          addOrUpdateEdge(k, l, distance);
          addOrUpdateEdge(l, k, distance);
        }
      }



      // blank line is only needed between test cases
      if (i == n - 1)
        System.out.printf("%.2f\n", prim(0));
      else
        System.out.printf("%.2f\n\n", prim(0));



      adjacencyList = new HashMap<Integer, HashMap<Integer, Double>>();
    }
  }



  static void addOrUpdateEdge(int from, int to, double weight) {
    if (adjacencyList.containsKey(from)) {
      adjacencyList.get(from).put(to, weight);
    } else {
      HashMap<Integer, Double> edges = new HashMap<Integer, Double>();
      edges.put(to, weight);
      adjacencyList.put(from, edges);
    }
  }



  static Set<Entry<Integer, Double>> neighborsAndWeight(int node) {
    Set<Entry<Integer, Double>> neighborsAndWeight = new HashSet<Entry<Integer, Double>>();
    if (adjacencyList.containsKey(node)) {
      neighborsAndWeight = adjacencyList.get(node).entrySet();
    }
    return neighborsAndWeight;
  }



  static double prim(int start) {
    // HashSet<Integer> mst = new HashSet<Integer>();
    HashSet<Integer> visited = new HashSet<Integer>();
    Integer lastInserted = start;
    double sum = 0;
    visited.add(start);



    PriorityQueue<Triple> possibleEdges = new PriorityQueue<Main.Triple>();



    while (adjacencyList.size() != visited.size()) {
      for (Entry<Integer, Double> nw : neighborsAndWeight(lastInserted)) {
        possibleEdges.add(new Triple(lastInserted, nw.getKey(), nw.getValue()));
      }



      // dealing with an empty adjacencyList
      if (possibleEdges.size() <= 0)
        break;



      Triple t = possibleEdges.poll();



      while (visited.contains(t.to)) {
        t = possibleEdges.poll();
      }



      // mst.add(t.to);
      visited.add(t.to);
      lastInserted = t.to;
      sum += t.weight;
    }



    return sum;
  }



  static class Triple implements Comparable<Triple> {
    public int from;
    public int to;
    public double weight;



    public Triple(int from, int to, double weight) {
      super();
      this.from = from;
      this.to = to;
      this.weight = weight;
    }



    @Override
    public int compareTo(Triple anotherElement) {
      int ret;



      if (this.weight < anotherElement.weight) {
        ret = -1;
      } else if (this.weight == anotherElement.weight) {
        ret = 0;
      } else {
        ret = 1;
      }



      return ret;
    }



  }
}
