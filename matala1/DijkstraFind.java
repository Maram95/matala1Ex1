
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DijkstraFind {

    private int size;
    private HashMap<Integer, Double> weight; // store weights for each vertex
    private HashMap<Integer, Integer> previousNode; // store previous vertex
    private PriorityQueue<Integer> pq; // store vertices that need to be visited
    private WeighedDigraph graph; // graph object

    /**
     * Instantiate algorithm providing graph
     *
     * @param graph WeighedDigraph graph
     */
    public DijkstraFind(WeighedDigraph graph) {
        this.graph = graph;
        size = graph.size();
    }

    /**
     * Calculate shortest path from A to B
     *
     * @param vertexA source vertex
     * @param vertexB destination vertex
     * @return list of vertices composing shortest path between A and B
     */
    public double shortestPath(int vertexA, int vertexB) {
        previousNode = new HashMap<Integer, Integer>();
        weight = new HashMap<Integer, Double>();
        pq = new PriorityQueue<Integer>(size, PQComparator);

        /* Set all distances to Infinity */
        for (int vertex : graph.vertices()) {
            weight.put(vertex, Double.POSITIVE_INFINITY);
        }

        previousNode.put(vertexA, -1); // negative means no previous vertex
        weight.put(vertexA, 0.0); // weight to has to be 0
        pq.add(vertexA); // enqueue first vertex

        while (pq.size() > 0) {
            int currentNode = pq.poll();
            ArrayList<WeighedDigraphEdge> neighbors = graph.edgesOf(currentNode);

            if (neighbors == null) {
                continue;
            }

            for (WeighedDigraphEdge neighbor : neighbors) {
                int nextVertex = neighbor.to();

                double newDistance = weight.get(currentNode) + neighbor.weight();
                if (weight.get(nextVertex) == Double.POSITIVE_INFINITY) {
                    previousNode.put(nextVertex, currentNode);
                    weight.put(nextVertex, newDistance);
                    pq.add(nextVertex);
                    //System.out.println(newDistance);
                } else {
                    if (weight.get(nextVertex) > newDistance) {
                        previousNode.put(nextVertex, currentNode);
                        weight.put(nextVertex, newDistance);
                      //   System.out.println(newDistance);

                    }
                }
              

            }
 }

        /* Path from A to B will be stored here */
        ArrayList<Integer> nodePath = new ArrayList<Integer>();

        /*
         We are reverse walking points to get to the beginning of the path.
         Using temporary stack to reverse the order of node keys stored in nodePath.
         */
        Stack<Integer> nodePathTemp = new Stack<Integer>();
        nodePathTemp.push(vertexB);

        int v = vertexB;
        while (previousNode.containsKey(v) && previousNode.get(v) >= 0 && v > 0) {
            v = previousNode.get(v);
            nodePathTemp.push(v);
        }
                    System.out.println(nodePathTemp);

        // Put node in ArrayList in reversed order
                    double path=0;
        while (nodePathTemp.size() > 0) {
            int current=nodePathTemp.pop();
        // System.out.println(current);
        ArrayList<WeighedDigraphEdge> neighbors = graph.edgesOf(current);
         for (WeighedDigraphEdge neighbor : neighbors){
             int next=neighbor.to();
           // System.out.println("next"+next);
          //  System.out.println("peek"+nodePathTemp.peek());
   if(nodePathTemp.size()>0 && next==nodePathTemp.peek()){
      
                 path=neighbor.weight()+path;
             
         }}

            nodePath.add(current);
        }
      // System.out.println(path);
        

        //return nodePath;
        return path;
    }

    /**
     * Comparator for priority queue
     */
    public Comparator<Integer> PQComparator = new Comparator<Integer>() {

        public int compare(Integer a, Integer b) {
            if (weight.get(a) > weight.get(b)) {
                return 1;
            } else if (weight.get(a) < weight.get(b)) {
                return -1;
            }
            return 0;
        }
    };
   /* public void test(String file,String file2 ) throws IOException{
          String filename=file+".txt";
           BufferedReader reader = new BufferedReader(new FileReader(filename));
             String line = null;
             String str;
             str=reader.readLine();
             int lines=Integer.parseInt(str);
               while ((line = reader.readLine()) != null) {
               line=line.trim();
                 String[] parts = line.split("\\s+");
                                    System.out.println(Integer.parseInt(parts[0]));

               int VertexA=Integer.parseInt(parts[0]);
                   System.out.println(Integer.parseInt(parts[1]));
               int VertexB=Integer.parseInt(parts[1]);
               int loop=Integer.parseInt(parts[2]);
               int i=3;
                  // System.out.println(Integer.parseInt(parts[3]));
               //ArrayList <Integer>  blacklist = new ArrayList();
                   int [] arr=new int[loop];
               for(int j=0;j<loop;j++){
              // blacklist.add(Integer.parseInt(parts[i]));
                arr[j]=Integer.parseInt(parts[i]);
                   i++;   
               }
                  // System.out.println(blacklist);
              // if (blacklist.size()>0){
             //  for( int k : blacklist){
                //  System.out.println(blacklist.get(1));
            // int current =blacklist.get(i);

               for(int k=0;k<arr.length;k++){
                   ArrayList<WeighedDigraphEdge> neighbors = graph.edgesOf(arr[k]);
               
         for (WeighedDigraphEdge neighbor : neighbors){
           
               neighbor.set(Double.POSITIVE_INFINITY);
        }
               }
                WeighedDigraph graph = new WeighedDigraph(file2);
               DijkstraFind finder = new DijkstraFind(graph);
               System.out.print("Test 2/Blue: 2 -> 202: " + finder.shortestPath(VertexA, VertexB) + "\n");
    
               }}
*/
    public static void main(String args[]) {
        WeighedDigraph graph;
//test("mediumEWD" ,"test3");
        try {
           graph = new WeighedDigraph("mediumEWD");
            // Print graph
            System.out.print("Representation of WeighedDigraph\n");
            System.out.print(graph);
            System.out.print("\n");

            DijkstraFind finder = new DijkstraFind(graph);

            System.out.print("TESTS\n");
          //System.out.print(finder.shortestPath(4, 5) + "\n");
            System.out.print("Test 2/Blue: 2 -> 202: " + finder.shortestPath(2,202) + "\n");
            //System.out.print("Test 3/Green: 3 -> 28: " + finder.shortestPath(3, 28) + "\n");

        } catch (IOException e) {
        }
    }
}
