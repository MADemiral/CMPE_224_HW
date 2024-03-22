package q1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Graph {
	//-----------------------------------------------------
	// Title: Question 1 Weighted Graph class
	// Author: Mehmet Alp Demiral
	// Description: This class is the includes all of the weighted graph methods for question 1
	//-----------------------------------------------------

	public int total_vertice;					
	public static int paths_found = 0;			//attributes of graph

	public ArrayList<Vertice> adjList;			//all vertices list 1 dimensional only stores vertice objects
	public ArrayList<Edge>[] edgeList;			//stores all edges two dimensional first dimesntion is the start vertice of the edge

    public class Vertice{							
        public int vertice_id;
		public int x_coordinate,y_coordinate;		//vertice class for Vertice objects
        public String vertice_name;

        public Vertice(int vertice_id, String vertice_name, int x_coordinate, int y_coordinate){	//Vertice Constructor		
            this.vertice_id = vertice_id;
            this.vertice_name = vertice_name;
			this.x_coordinate = x_coordinate;
			this.y_coordinate = y_coordinate;		
        }
        public Vertice(){	//vertice default constructor

        }
    }

	public class Edge{								//edge class for edge objects
		public Vertice start;
		public Vertice dest;
		public double lenght;	//attributes of edge class
		
		public Edge(Vertice start, Vertice dest){		//edge constructor
			this.start = start;
			this.dest = dest;
			this.lenght = Math.sqrt((Math.pow(dest.x_coordinate-start.x_coordinate, 2))+(Math.pow(dest.y_coordinate-start.y_coordinate, 2)));
			// euclidian formula//
		}

	}


	public Graph(int count)		//graph constructor				
	{
		this.total_vertice = count;

		edgeList = new ArrayList[total_vertice];

		for (int i = 0; i < total_vertice; i++) {
			edgeList[i] = new ArrayList<>();
		}
	}

	

	public void addADJ(Vertice adj)
	//--------------------------------------------------------
	// Summary: Addind vertice to adjList
	// Precondition: Vertice adj
	// Postcondition: adj will be added to adjList
	//--------------------------------------------------------
	{
		this.adjList.add(adj);					
	}

	private class EdgeLengthComparator implements Comparator<Edge> {	//for sorting edges by their weight 
        @Override
        public int compare(Edge edge1, Edge edge2) {
            return Double.compare(edge1.lenght, edge2.lenght);
        }
	}

	public void createEdges(){
		//--------------------------------------------------------
        // Summary: Creating edges between all vertices
        // Precondition: there is no precondition
        // Postcondition: all the edges will be created and added to edgeList
        //--------------------------------------------------------
		for(int index = 0;index<adjList.size();index++){
			for(Vertice adj:adjList){
				Edge addEdge = new Edge(adjList.get(index), adj);			//creates edges between all vertices
				edgeList[index].add(addEdge);
		}
		}
		for(int i = 0;i<edgeList.length;i++){
			Collections.sort(edgeList[i], new EdgeLengthComparator());		//sorting the edge lists by the weight
		}
		

	}

	public Vertice find_index_Vertice(String wanted){			
		//--------------------------------------------------------
        // Summary: Return the vertice if it is on the list
        // Precondition: String wanted it is desired vertices name
        // Postcondition: Vertice object will be returned
        //--------------------------------------------------------
		int index = find_index(wanted);
		Vertice returnVertice = adjList.get(index);
		return returnVertice; 
	}

	public int find_index(String wanted){
		//--------------------------------------------------------
        // Summary: Return the index if it is on the list
        // Precondition: String wanted it is desired vertices name
        // Postcondition: index will be returned
        //--------------------------------------------------------
		for(int i = 0;i<adjList.size();i++){
            if(adjList.get(i).vertice_name.equalsIgnoreCase(wanted)){			
				return i;
			}
		}
		return -1;

	}

	public ArrayList<Edge> create_mst_with_prim() {
		//--------------------------------------------------------
        // Summary: creates mst with prims algorithm
        // Precondition: there is no pre condition only we need to graph object
        // Postcondition: and resturn the edges we used to create mst
        //--------------------------------------------------------
		
        boolean[] vis = new boolean[this.total_vertice];		//visited vertices
		vis[0] = true;										//makes initial true

        PriorityQueue<Edge> edge_queue = new PriorityQueue<>(Comparator.comparingDouble(edge -> edge.lenght));  //priority queue and our priority is edge lenght
		edge_queue.addAll(edgeList[0]);

        ArrayList<Edge> mstEdges = new ArrayList<>();		//used edges

         
        while (!edge_queue.isEmpty()) {										//prims algorithm
            Edge polled_from_priority_queue = edge_queue.poll();			//fistly polled from the queue by priority
            Vertice currentVertex = polled_from_priority_queue.dest;		

            if (!vis[currentVertex.vertice_id]) {

                vis[currentVertex.vertice_id] = true;		//makes visited vertice true 
                mstEdges.add(polled_from_priority_queue);	//and edd it to used edges

                edge_queue.addAll(edgeList[currentVertex.vertice_id]);		//and add all the edges connected to current to priority queue
            }
        }
		Collections.sort(mstEdges, new EdgeLengthComparator());		//sorts the edge by weight and returns
        return mstEdges;
    }


	

	
}

