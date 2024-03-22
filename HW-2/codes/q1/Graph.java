package q1;
import java.util.ArrayList;
import java.util.Collections;



public class Graph {
	//-----------------------------------------------------
	// Title: Question 1 Graph class
	// Author: Mehmet Alp Demiral
	// Description: This class is the includes graph methods of the question 1
	//-----------------------------------------------------

	public int total_vertice;					//attributes
	public static int paths_found = 0;	

	public ArrayList<Vertice>[] adjList;			//adj list on arraylist of vertices format

    public class Vertice{						//vertice class for the adj list
        public int vertice_id;
        public String vertice_name;						//attributes


        public Vertice(int vertice_id,String vertice_name){			
            this.vertice_id = vertice_id;
            this.vertice_name = vertice_name;		//Vertie constructor
        }
        public Vertice(){

        }
    }


	

	public Graph(int count)				//graph constructor
	{

		this.total_vertice = count;

		adjList = new ArrayList[total_vertice];

		for (int i = 0; i < total_vertice; i++) {
			adjList[i] = new ArrayList<>();
		}
	}

	


	public void addEdge(Vertice start, Vertice dest)
	//--------------------------------------------------------
        // Summary: Addind edge between directed to start vertice to dest vertice
        // Precondition: Vertice start,dest
        // Postcondition: Edge will be created directed to start to dest vertice
        //--------------------------------------------------------
	{
		int index = find_index(start.vertice_name);			//find indes of the start vertice on adj list
		this.adjList[index].add(dest);					//add adj to start vertice
	}

	public int find_index(String wanted){
		//--------------------------------------------------------
        // Summary: find the index of the Vertice which has wanted name 
        // Precondition: String wanted that is the name of the vertice we wanted
        // Postcondition: index of the wanted vertice will be returned
        //--------------------------------------------------------
		for(int i = 0;i<adjList.length;i++){
            if(adjList[i].get(0).vertice_name.equalsIgnoreCase(wanted)){			//compare oll vertices and returns vertice
				return i;
			}
		}
		return -1;

	}


	public void printAllPathsToDest(Vertice start, int num_of_hoops) {
		//--------------------------------------------------------
        // Summary: print all the paths to which have num of hoops 
        // Precondition: Vertice start that is start vertice and int num_of_hoops is total hoop number
        // Postcondition: all paths will be printed
        //--------------------------------------------------------

		ArrayList<String> all_paths = new ArrayList<>(); 	//all_paths 
		boolean[] visited = new boolean[total_vertice];		//for checking visited vertices
		ArrayList<String> pathList = new ArrayList<>();		//and local path list for DFS
	
		pathList.add(start.vertice_name);					//start vertice will be added to path

		

		//calling recursive function
		printAllPathsUtil(start, num_of_hoops, 0, visited, pathList, all_paths, 0);

		//sort the list by lexigeographical order
		Collections.sort(all_paths);
	
		///printing paths
		System.out.println("Number of total routes: "+all_paths.size());
		System.out.println("Routes are:");

		for (int i = 0; i < all_paths.size(); i++) {
			System.out.println(all_paths.get(i));
		}
	}
	
	private void printAllPathsUtil(Vertice start, int total_hoops, int current_hoop,
									boolean[] visited, ArrayList<String> pathList, ArrayList<String> all_paths, int paths_found) {
		//--------------------------------------------------------
        // Summary: recursive dfs funciton for search paths and add them to arraylist
        // Precondition: Vertice start, int total_hoops, int current_hoop, boolean[] visited, ArrayList<String> pathList, ArrayList<String> all_paths, int paths_found
        // Postcondition: all paths will be printed
        //--------------------------------------------------------	
										


		if (total_hoops == current_hoop) {					//if the current hoop is the equal to total hoop count it will add local_path to all paths arraylist
															//and increase the paths found
			String current_path = pathList.get(0);
			for(int i = 1;i<current_hoop+1;i++){
				current_path += "-"+pathList.get(i);
			}
			all_paths.add(current_path);
			paths_found++;
			return;
		}
	
		++current_hoop;												//increase current hoop count

		visited[find_index(start.vertice_name)] = true;				//make the visited vertice index true
														
	

		int pathListSize = pathList.size();							//for removing the latest elemt

		for (Vertice i : adjList[find_index(start.vertice_name)]) {
			if (!visited[find_index(i.vertice_name)]) {								//if not visited condition

				pathList.add(i.vertice_name);					//add to local path 
				printAllPathsUtil(i, total_hoops, current_hoop, visited, pathList, all_paths, paths_found);					//calling functon with new current hoop
				pathList.remove(pathListSize);					//remove the last elemet
			}
		}
	
		visited[find_index(start.vertice_name)] = false; 			//make the false current vertice when the exited from for
	}

	
}

