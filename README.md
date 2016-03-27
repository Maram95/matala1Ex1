# matala1Ex1
classes:

WeighedDigraphEdge.java – representation of graph’s edge.
Feilds: 
  int from 
  int to 
  double weight
Methods:
  WeighedDigraphEdge(int from, int to, double weight) // copy constructor
getters/setters:
  from() 
  set(double weight)
  to() 
  weight() 
 
 

WeighedDigraph.java – graph.
Feilds: 
  HashMap<Integer
  ArrayList<WeighedDigraphEdge>> adj
Methods:
  WeighedDigraph() // constructor
  WeighedDigraph(String file) throws IOException // constructor that instantiate graph from file with data
  ArrayList<WeighedDigraphEdge> edgesOf(int vertex) //return list of edges vertex is connected to
  ArrayList<WeighedDigraphEdge> edges() //return list of all edges in the graph
  Iterable<Integer> vertices() //return iterable of all vertices in the graph
  size() //return size of adjacency list
  toString() //return string representation of digraph
  addEdge(WeighedDigraphEdge newEdge) //Add new edge to the system.

 
    

DijkstraFind.java – shortest path algorithm including test code for our experiment
Feilds:  
  HashMap<Integer, Double> weight // store weights for each vertex
  HashMap<Integer, Integer> previousNode // store previous vertex
  PriorityQueue<Integer> pq // store vertices that need to be visited
  WeighedDigraph graph // graph object
Methods:
  public DijkstraFind(WeighedDigraph graph) // constructor that instantiate algorithm providing graph
   shortestPath(int vertexA, int vertexB) //alculate shortest path from A to B,return list of vertices composing shortest path between A and B
   Comparator<Integer> PQComparator = new Comparator<Integer>() //internal class that comparator for priority queue
    
  
  
  test.java- class that tests all the classes in the project
Methods:
  test(String file,String file2 ) throws IOException // constrctor 
  
  openSource link:
  http://www.marcinkossakowski.com/finding-shortest-path-using-dijkstras-algorithm/
  github link:
  https://github.com/martez81/shortestPath
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
