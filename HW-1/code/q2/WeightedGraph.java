package q2;
import java.util.LinkedList;
//-----------------------------------------------------
// Title: Graph Class
// Author: Mehmet Alp Demiral
// Description: This class contains all methods and attributes for Undirected Weighted Graph
//-----------------------------------------------------


public class WeightedGraph {

//edge class for storing edge weights
public class Edge {

    //start point
    public int start;
    //end point
    public int end;
    //weight of the edge
    public int weight;


    //constructor for creating edge
        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
    
//num of vertices    
private final int V;
//for storing all edges of vertices
public LinkedList<Edge>[] adj;


public WeightedGraph(int V)
//--------------------------------------------------------
// Summary: Weighted graph constructor
// Precondition: int V that is total vertices 
// Postcondition: creates undirected weighted graph object
//--------------------------------------------------------
{

    this.V = V;
    this.adj = new LinkedList[this.V];

    for (int index = 0; index <this.V ; index++) {
        adj[index] = new LinkedList<Edge>();
    }
}



public boolean has_Adj(int v1,int v2)
//--------------------------------------------------------
// Summary: ADJ checker
// Precondition: int v1 and v2 
// Postcondition: are the v1 and v2 adj's value true or false 
//--------------------------------------------------------
{
    boolean found = false;
    v1--;
    v2--;

    
    LinkedList<Edge> edge_list = adj[v1];
    for(int j = 0;j<edge_list.size();j++){
        if(edge_list.get(j).end == v2 || edge_list.get(j).start == v2){
            found = true;
            }

        }
    return found;
    }
    
public void addEdge(int start, int end, int weight)
//--------------------------------------------------------
// Summary: Edge Adder
// Precondition: int start and end and weight 
// Postcondition: creates edge between start and end vertices and makes the edges weight is weight
//--------------------------------------------------------
{
    start--;
    end--;
    Edge edge1 = new Edge(start, end, weight);
    Edge edge2 = new Edge(end, start, weight);
    this.adj[start].add(edge1);
    this.adj[end].add(edge2);
    
}

//for paths class storing paths
public class Path {
    //paths route
    public String path = "";
    //paths sumWeight
    public int sumWeight;
    //paths visited vertices for checking do we iterate all the vertices
    public LinkedList<Integer> visited_vertices = new LinkedList<>();

}



public void iterateFunctionWithMinTime()
//--------------------------------------------------------
// Summary: iterateFunctionWithMinTime
// Precondition: there is no precondition
// Postcondition: it print the mininum size path 
//--------------------------------------------------------
{
    LinkedList<Path> end_Sums = new LinkedList<>();

    for(int a = 0;a<this.V;a++){
        end_Sums.add(go_to_adj(a));
    }

    for(int a = 0;a<end_Sums.size();a++){
        System.out.println(end_Sums.get(a).path+" "+end_Sums.get(a).sumWeight);
    }

}

public Path go_to_adj(int start)
//--------------------------------------------------------
// Summary: go_to_adj function
// Precondition: int start that is start vertices
// Postcondition: it goes the adj's and add them to path and sum the sum weight while calling this function recursively
//--------------------------------------------------------
{
    //initial path
    Path start_point = new Path();
    //add visited vertices
    start_point.visited_vertices.add(start);
    //adding path
    start_point.path = start_point.path+","+(start+1);
    
    int sum =0;
    for(int count = 0; count<this.V;count++){
        
        if(start_point.visited_vertices.contains(count)){
            sum++;
        }
    }
    if(sum == this.V){
        //exit condition
       return start_point;
    }
    
    for (Edge neighbor : this.adj[start]){
        //calculates sum 
        start_point.sumWeight+=neighbor.weight;
        //calling it recursively
        go_to_adj(neighbor.end);
    }
    //retuns current path 
    return start_point;

}




}














