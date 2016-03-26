import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
// For file reading:
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WeighedDigraph {
    private HashMap<Integer, ArrayList<WeighedDigraphEdge>> adj = new HashMap(); // adjacency-list

       public WeighedDigraph() {}


    /**
     * Instantiate graph from file with data;
   
     * @throws IOException
     */
    public WeighedDigraph(String file) throws IOException {
      String filename=file+".txt";
      int counter=0;
         // FileReader fr = new FileReader(filename);
        BufferedReader reader = new BufferedReader(new FileReader(filename));
       String line = null;
       String str;
       str=reader.readLine();
       int V=Integer.parseInt(str);
        str=reader.readLine();
         int E=Integer.parseInt(str);
        while ((line = reader.readLine()) != null) {
               line=line.trim();
                 String[] parts = line.split("\\s+");
            if (parts.length == 3) {
                int from = Integer.parseInt(parts[0]);
                int to = Integer.parseInt(parts[1]);
                double weight = Double.parseDouble(parts[2]);
                counter++;

                addEdge(new WeighedDigraphEdge(from, to, weight));
                
            }
        }
        System.out.println("E countert="+counter+" , E="+E +", V="+V);
    }

    /**
     * @param vertex
     * @return list of edges vertex is connected to.
     */
    public ArrayList<WeighedDigraphEdge> edgesOf(int vertex) {
        return adj.get(vertex);
    }

    /**
     * @return list of all edges in the graph.
     */
    public ArrayList<WeighedDigraphEdge> edges() {
        ArrayList list = new ArrayList<WeighedDigraphEdge>();

        for (int from : adj.keySet()) {
            ArrayList<WeighedDigraphEdge> currentEdges = adj.get(from);
            for (WeighedDigraphEdge e : currentEdges) {
                list.add(e);
            }
        }
        return list;
    }

    /**
     * @return iterable of all vertices in the graph.
     * Nice and easy, isn't it? All the dirty work of creating the Iterator<String>, checking if it hasNext(), 
     * and calling str = getNext() is handled behind the scenes by the compiler.
     */
    public Iterable<Integer> vertices() {
        HashSet set = new HashSet();
        for (WeighedDigraphEdge edge : edges()) {
            set.add(edge.from());
            set.add(edge.to());
        }

        return set;
    }

    /**
     * @return size of adjacency list
     */
    public int size() {
        return adj.size();
    }

    /**
     * @return string representation of digraph
     */
    public String toString() {
        String out = "";
        for (int from : adj.keySet()) {
            ArrayList<WeighedDigraphEdge> currentEdges = adj.get(from);
            out += from + " -> ";

            if (currentEdges.size() == 0)
                out += "-,";

            for (WeighedDigraphEdge edge : currentEdges)
                out += edge.to() + " @ " + edge.weight() + ", ";

            out += "\n";
        }

        return out;
    }

    /**
     * Add new edge to the system.
     * @param newEdge
     */
    public void addEdge(WeighedDigraphEdge newEdge) {
        // create empty connection set
        if (!adj.containsKey(newEdge.from()))
            adj.put(newEdge.from(), new ArrayList<WeighedDigraphEdge>());

        ArrayList<WeighedDigraphEdge> currentEdges = adj.get(newEdge.from());

        /* Check if edge already exists,
         * if it is, replace it with new one assuming it needs to be updated */
        boolean edgeExists = false;
        for (int i = 0; i < currentEdges.size(); i++) {
            if (currentEdges.get(i).to() == newEdge.to()) {
                currentEdges.set(i, newEdge);
                edgeExists = true;
                break;
            }
        }

        if (!edgeExists)
            currentEdges.add(newEdge);

        adj.put(newEdge.from(), currentEdges);
    }

    /**
     * Graph Tests
     * @param args
     */
    public static void main(String args[]) throws IOException  {
        
            WeighedDigraph graph = new WeighedDigraph("tinyEWD");
            
            /* WeighedDigraphEdge edge1 = new WeighedDigraphEdge(1, 2, 2.0);
            WeighedDigraphEdge edge2 = new WeighedDigraphEdge(2, 1, 2.0);
            
            graph.addEdge(edge1);
            graph.addEdge(edge2);
            */
                    System.out.print(graph);
                   
        
    }
}