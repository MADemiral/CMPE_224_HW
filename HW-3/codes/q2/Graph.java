package q2;
import java.util.ArrayList;


public class Graph {
	//-----------------------------------------------------
	// Title: Question 2 Weighted Graph class
	// Author: Mehmet Alp Demiral
	// Description: This class is the includes all of the weighted graph methods for question 2
	//-----------------------------------------------------


	public int total_vertice;					
	public static int paths_found = 0;		//attributes of graph

	public ArrayList<Vertice> adjList;			//all vertices list 1 dimensional only stores vertice objects
	public ArrayList<Edge>[] edgeList;			//stores all edges two dimensional first dimesntion is the start vertice of the edge			

    public class Vertice{						
        public int vertice_id;			//vertice class for Vertice objects
        public String vertice_name;

        public Vertice(int vertice_id, String vertice_name){			//Vertice Constructor		
            this.vertice_id = vertice_id;
            this.vertice_name = vertice_name;	
        }
        public Vertice(){									//vertice default constructor

        }
    }

	public class Edge{					//edge class for edge objects
		public Vertice start;	
		public Vertice dest;
		public double weight;			//attributes of edge class
		
		public Edge(Vertice start, Vertice dest, int weight){			//edge constructor
			this.start = start;
			this.dest = dest;
			this.weight = weight;
		}

	}

	public Graph(int count)					//graph constructor
	{
		this.total_vertice = count;

		edgeList = new ArrayList[total_vertice];
		adjList = new ArrayList<>();

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


	public void add_edge(Vertice start, Vertice dest, int weight){
		//--------------------------------------------------------
		// Summary: Addind edge to EdgeList
		// Precondition: Vertice start, Vertice dest, int weight
		// Postcondition: the edge which is weighted is = int weight will be added to start and dest vertices edges 
		//--------------------------------------------------------
		Edge addingEdge1 = new Edge(start, dest, weight);
		Edge addingEdge2 = new Edge(dest, start, weight);
		this.edgeList[find_index(start.vertice_name)].add(addingEdge1);
		this.edgeList[find_index(dest.vertice_name)].add(addingEdge2);
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


	public ArrayList<ArrayList<Vertice>> paths_finder(Vertice start, Vertice dest,int steps,ArrayList<String> wanted) {
		//--------------------------------------------------------
        // Summary: For finding the paths between start and dest and the cities we wanted is inclueded 
        // Precondition: Vertice start, Vertice dest,int steps,ArrayList<String> wanted
        // Postcondition: The paths will be returned on two dimensional arraylist format
        //--------------------------------------------------------

        boolean[] vis = new boolean[this.adjList.size()];	// visited array
        ArrayList<ArrayList<Vertice>> allPaths = new ArrayList<>();		//allpaths
		ArrayList<ArrayList<Vertice>> returning_paths_List = new ArrayList<>();			//for retunring paths we wanted
        ArrayList<Vertice> localPaths_for_recursive = new ArrayList<>();				//localpaths lsit for recursive funciton
		
        recursive_paths_finder_and_adder(start, dest, vis, allPaths, localPaths_for_recursive);

		for(int a=0;a<allPaths.size();a++){
			int found = 0;
			for(int b=0;b<allPaths.get(a).size();b++){
				if(wanted.contains(allPaths.get(a).get(b).vertice_name.toLowerCase())){				//checking the condition the cities we wanted is included on paths or not
					found++;
				}
			}
			if(found == steps){					//if all oh them is in the path adds them to returning list
					returning_paths_List.add(new ArrayList<>(allPaths.get(a)));
			}
		}

        return returning_paths_List;
    }

    public void recursive_paths_finder_and_adder(Vertice current, Vertice dest, boolean[] vis, ArrayList<ArrayList<Vertice>> allPaths, ArrayList<Vertice> localPaths_for_recursive){
		//--------------------------------------------------------
        // Summary: For finding the paths between start and dest recursive part
        // Precondition: Vertice current, Vertice dest, boolean[] vis, ArrayList<ArrayList<Vertice>> allPaths, ArrayList<Vertice> localPaths_for_recursive
        // Postcondition: all the paths between start and dest will be added the allPaths arraylist
        //--------------------------------------------------------

		localPaths_for_recursive.add(current);		//adds current

        vis[find_index(current.vertice_name)] = true;		//visited array
        
        if(current.vertice_name.equals(dest.vertice_name)){
            allPaths.add(new ArrayList<>(localPaths_for_recursive));			//add all connected edges
        } 
		else{
            for(Edge edge : edgeList[find_index(current.vertice_name)]){
                if(!vis[find_index(edge.dest.vertice_name)]){
					Vertice dest_Vertice = edge.dest;
                    recursive_paths_finder_and_adder(dest_Vertice, dest, vis, allPaths, localPaths_for_recursive);		//cals dunbction with dest of the edge
                }
            }
        }
		//for moving bacward
        vis[find_index(current.vertice_name)] = false;
        localPaths_for_recursive.remove(localPaths_for_recursive.size() - 1);
    }

	public Edge find_edge(Vertice start, Vertice dest){
		//--------------------------------------------------------
        // Summary: For finding edge between start and dest vertice
        // Precondition: Vertice start, Vertice dest
        // Postcondition:Founded edge object will be returned
        //--------------------------------------------------------

		int index = find_index(start.vertice_name);				//start vertices index
		for(int inside = 0;inside<edgeList[index].size();inside++){
			if(dest.vertice_name.equalsIgnoreCase(edgeList[index].get(inside).dest.vertice_name)){	//search all edges connected to start vertice and tries to find edge
				return edgeList[index].get(inside);
			}
		}
		return null;
	}

	public void print_List_Weight(ArrayList<ArrayList<Vertice>> possible_paths){
		//--------------------------------------------------------
        // Summary: Takes all paths provides our conditions and print min weighted path
        // Precondition: ArrayList<ArrayList<Vertice>> possible_paths
        // Postcondition: the path will be printed
        //--------------------------------------------------------
		
		int min_index = -1;
		int min_weight = Integer.MAX_VALUE;

		for(int curr_index = 0;curr_index<possible_paths.size();curr_index++){
			int currennt_weight = 0;
			ArrayList<Vertice> current_path = possible_paths.get(curr_index);
			for(int i = 0;i<current_path.size()-1;i++){													// tries to find path has min path lenght
				Edge current_found_edge = find_edge(current_path.get(i), current_path.get(i+1));
				currennt_weight+= current_found_edge.weight;
			}
			if(currennt_weight<min_weight){
				min_index = curr_index;
				min_weight = currennt_weight;
			} 	
		}
		System.out.println("Routes are");
		ArrayList<Vertice> min_path = possible_paths.get(min_index);
		String path = min_path.get(0).vertice_name;
		for(int j = 1;j<min_path.size();j++){						//prints the path part
			path+="-"+min_path.get(j).vertice_name;
		}
		System.out.println(path);
		System.out.println("Length of route is: "+min_weight);
	}

}

