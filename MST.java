import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.Collections;
import java.util.Arrays;

class MST {
  private class Connection {
    String a, b;
    int cost;

    Connection(String a, String b, int cost) {
      this.a = a;
      this.b = b;
      this.cost = cost;
    }
  }

  private class UnionFind {

    Map<String, String> roots;
    Map<String, Integer> ranks;

    UnionFind() {
      this.roots = new HashMap<>();
      this.ranks = new HashMap<>();
    }
    
     void makeSet(String s) {
      if (!roots.containsKey(s)) {
        roots.put(s, s);
        ranks.put(s, 0);
      }
    }

    //return the root of a DisjointSet
    String find(String s) {
      while (roots.get(s) != s) {
        s = roots.get(s);
      }
      return s;
    }

    //merge two cities 
    void union(String a, String b) {
      String rootA = find(a);
      String rootB = find(b);
      
      // if the two cities are not in the same disjoint sets
      if (rootA != rootB) {
        int rankRA = ranks.get(rootA);
        int rankRB = ranks.get(rootB);

        //always set the root to be the deepest root, 
        //so as to keep the tree as short as possible
        //which makes the searching as low as possible
        if (rankRA > rankRB) {
          roots.put(rootB, rootA);
        } else {
          roots.put(rootA, rootB);

          //if the same ranks, increment
          if (rankRA  == rankRB) {
            ranks.put(rootB, rankRB + 1);
          }
        }
      }
    }
  }

  public List<Connection> getLowCost(List<Connection> connections) {
    List<Connection> result = new ArrayList<>(); 
    UnionFind uf = new UnionFind(); 

    //Initializing the disjoint sets
    for (Connection c: connections) {
        uf.makeSet(c.a);
        uf.makeSet(c.b);
    }

    //sort the connections according to weigth
    Collections.sort(connections, new ConnectionComparator()); 

    for (Connection connection: connections) {
      String a = connection.a;
      String b = connection.b;
       
      //if city a and city b are not in the same sets, take this edge,
      //and union the two sets 
      if (!uf.find(a).equals(uf.find(b))) {
        result.add(connection);
        uf.union(a, b);
      } 
    }

    return result;
  }
  
  private class ConnectionComparator implements Comparator<Connection> {
    @Override
    public int compare(Connection a, Connection b) {
      return a.cost - b.cost;
    }
  }
 

  // Test
  public static void main(String[] args) {
    MST mst = new MST();
    List<Connection> connections = new ArrayList<>(Arrays.asList(
      mst.new Connection("A", "B", 5),
      mst.new Connection("A", "C", 6),
      mst.new Connection("A", "D", 4),
      mst.new Connection("B", "D", 2),
      mst.new Connection("B", "C", 1),
      mst.new Connection("C", "D", 2),
      mst.new Connection("C", "F", 3),
      mst.new Connection("C", "E", 5),
      mst.new Connection("D", "F", 4),
      mst.new Connection("E", "F", 4)
    ));
    
    for (Connection c : mst.getLowCost(connections)){
        System.out.printf("%s <-> %s (%d)\n", c.a, c.b, c.cost);
    }
  }
  
}


