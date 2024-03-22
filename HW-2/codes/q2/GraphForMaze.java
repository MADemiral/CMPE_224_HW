package q2;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphForMaze {
	//-----------------------------------------------------
	// Title: Question 2 Graph class
	// Author: Mehmet Alp Demiral
	// Description: This class is the includes graph methods of the question 2
	//-----------------------------------------------------

	public int total_vertice;
	public static int paths_found = 0;			//attributes
	public int col_count,row_count;

	public ArrayList<Vertice>[] adjList;			//adj list


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

	public GraphForMaze(int count)			//graph constructor
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
		int index = find_index(start.vertice_name);//find indes of the start vertice on adj list
		this.adjList[index].add(dest);					//add adj to start vertice
	}

	public int find_index(String wanted){
		//--------------------------------------------------------
        // Summary: find the index of the Vertice which has wanted name 
        // Precondition: String wanted that is the name of the vertice we wanted
        // Postcondition: index of the wanted vertice will be returned
        //--------------------------------------------------------

		for(int i = 0;i<adjList.length;i++){
            if(adjList[i].get(0).vertice_name.equalsIgnoreCase(wanted)){						//compare oll vertices and returns vertice
				return i;
			}
		}
		return -1;

	}
	
	public String format_all_the_path_array(ArrayList<String> path) {
		//--------------------------------------------------------
        // Summary: add all the elements on side by side and returns string 
        // Precondition: The list of strings we want to add side by side
        // Postcondition: all paths will be returned on one string
        //--------------------------------------------------------
		StringBuilder formattedPath = new StringBuilder();

		for (String iterate_vertex_names : path) {
			formattedPath.append(iterate_vertex_names.split(",")[2]);		//appending side by side
		}
		return formattedPath.toString();
	}


	public ArrayList<String> bfsToFindPath(Vertice staVertice, char[][] maze_char, int path_founded) {
		//--------------------------------------------------------
        // Summary: bfs method to find treasures and returns the list of paths
        // Precondition: Vertice start vertice, char [][]maze array of chars includes maze, int path_founded total_paths
        // Postcondition: the arraylist of paths will be returned
        //--------------------------------------------------------
		
		ArrayList<String> paths_founded_array = new ArrayList<>();						//total paths
		boolean[][] vertices_visited = new boolean[this.col_count][this.row_count];		//for checking vertices visited
		ArrayList<String> initialPath = new ArrayList<>();								//local path 
		initialPath.add(staVertice.vertice_name);										//start vertice name added to local fath ititally
		Queue<ArrayList<String>> bfsQ = new LinkedList<>();								//queue for bfs algorithm
		bfsQ.add(initialPath);															//added initial path to queue
	
		while (!bfsQ.isEmpty()) {
			ArrayList<String> latest_path = bfsQ.poll();							//latest path
			int queue_size = latest_path.size();									//size of the queu
			Vertice from_last_to_current = adjList[find_index(latest_path.get(queue_size - 1))].get(0);				//take the latest element
	
			String[] splitted = from_last_to_current.vertice_name.split(",");			//splitted vertice name for col and rows and char
			int col = Integer.parseInt(splitted[0]);
			int row = Integer.parseInt(splitted[1]);
	
			if (vertices_visited[col][row] != true) {				//if condition for finding treasure
				if (maze_char[col][row] == 'E') {
					this.paths_found++;							//increasing total paths
					paths_founded_array.add(format_all_the_path_array(latest_path));			//and added all path to arraylist by format function	
				}
				vertices_visited[col][row] = true;				//make visited vertice indexes true
	
				for (Vertice adj : adjList[find_index(from_last_to_current.vertice_name)]) {			//going adjs
					String[] local = adj.vertice_name.split(",");
					int adj_col = Integer.parseInt(local[0]);						//spliting adj vertice name	
					int adj_row = Integer.parseInt(local[1]);
	
					if (!vertices_visited[adj_col][adj_row]) {
						ArrayList<String> local_path_from_latest = new ArrayList<>(latest_path);				//adding local path to latest path for new list
						//and add new versce name
						local_path_from_latest.add(String.valueOf(adj.vertice_name));	
						//add local path to queue						
						bfsQ.add(local_path_from_latest);
					}
				}
			}
		}	
		return paths_founded_array;			//returns the all paths ArrayList
	}
	
}

