package q1;
//-----------------------------------------------------
// Title: Graph Class
// Author: Mehmet Alp Demiral
// Description: This class contains all methods and attributes for Undirected Graph
//-----------------------------------------------------

import java.util.LinkedList;

public class Graph {
    
private final int V;
private Bag<Integer>[] adj;

public Graph(int V)
//--------------------------------------------------------
// Summary: graph constructor
// Precondition: int V that is total vertices 
// Postcondition: creates undirected graph object
//--------------------------------------------------------
{
this.V = V;
adj = (Bag<Integer>[]) new Bag[V];
for (int v = 0; v < V; v++)
adj[v] = new Bag<Integer>();
}

public boolean has_Adj(int v1,int v2)
//--------------------------------------------------------
// Summary: does the two vercite has edge
// Precondition: int v1 and v2
// Postcondition: returns true or false does the vertices has edge between them
//--------------------------------------------------------
{
    boolean found = false; 
    for(Integer adj:this.adj(v1--)){
        if(adj == v2-1){
            found = true;
        }
    }
    return found;
}

public void addEdge(int v, int w)
//--------------------------------------------------------
// Summary: Edge adder between two vertices 
// Precondition: int v and w
// Postcondition: creates edge between this two vertices
//--------------------------------------------------------
{
    v--;
    w--;
    adj[v].add(w);
    adj[w].add(v);
}

public Iterable<Integer> adj(int v)
//--------------------------------------------------------
// Summary: adj list returner
// Precondition: int v that is vertice number
// Postcondition: returnes list of all adjecents of v
//--------------------------------------------------------
{
    v--; 
    return adj[v]; 
}



public int get_min_path_length(int start_Destination, int end_Destination)
//--------------------------------------------------------
// Summary: min path lenght between two vertices
// Precondition: int start_Destination and end_Destination
// Postcondition: it finds the lenght of distance between two vertices and returns the lenght
//--------------------------------------------------------
{

    start_Destination--;
    end_Destination--;

    //distace from start_Destination array
    int []distance_from_start = new int[this.V];
    //for counting visited vertices
    boolean [] visited_vertices = new boolean[this.V];

    //linkedlist that we add visited vertices
    LinkedList<Integer> linkedList_of_vertices = new LinkedList<Integer>();
    
    linkedList_of_vertices.add(start_Destination);
    distance_from_start[start_Destination] = 0;
    visited_vertices[start_Destination] = true;

    while(!linkedList_of_vertices.isEmpty()){
        //current vertice
        int vertice = linkedList_of_vertices.poll();
            if(vertice==end_Destination){
                //return the dstance of list
                return distance_from_start[end_Destination];
            }

        for(int adjs:this.adj(vertice+1)){
            if(visited_vertices[adjs]){
                //if we visited visit other vertice
                continue;
            }
            else{
                //and for updating parameters
                distance_from_start[adjs] = distance_from_start[vertice] + 1;
                visited_vertices[adjs]=true;
                linkedList_of_vertices.add(adjs);
            }
        }
    }
    return -1;

    
}


}



