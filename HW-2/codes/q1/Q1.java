package q1;
import java.io.File;
import java.util.LinkedList;
import java.io.IOException;
import java.util.Scanner;


public class Q1 {
    //-----------------------------------------------------
    // Title: Question 1 Main class
    // Author: Mehmet Alp Demiral
    // Description: This class is the includes main methods of the question 1
    //-----------------------------------------------------

    public  String txt_name;

    public int read_txt(String txt_name) throws IOException{
        //--------------------------------------------------------
        // Summary: Read the txt file and returns the total vertices count
        // Precondition: String txt_name
        // Postcondition: The count of total vertices will be returned
        //--------------------------------------------------------


        this.txt_name = txt_name;


        try{
        Scanner file = new Scanner(new File(txt_name));
        int total_count_of_vertices = 0;
        LinkedList<String> list_of_vertices = new LinkedList<>();

        while(file.hasNextLine()){                  //scans line by line
            String line = file.nextLine();                  
            String [] line_splitted = line.split(",");          //splits the lines by coma


            if(!list_of_vertices.contains(line_splitted[0])){       //if not added ads to the list and increase count
                list_of_vertices.add(line_splitted[0]);
                total_count_of_vertices++;
            }
            if(!list_of_vertices.contains(line_splitted[1])){
                list_of_vertices.add(line_splitted[1]);
                total_count_of_vertices++;
            }
            
        }
        file.close();
        return total_count_of_vertices;
        
        }

        catch(IOException e){
            System.out.println("File not found!");
        }
        return 0;

    }

    public Graph create_graph(int count){
        //--------------------------------------------------------
        // Summary: Creates the graph and by txt file creates a graph
        // Precondition: int count is total count of vertices
        // Postcondition: Created graph will be returned
        //--------------------------------------------------------
        Graph directed_graph = new Graph(count);
        
        try{
        Scanner file_2 = new Scanner(new File(this.txt_name));
        int total_count_of_vertices = 0;
        LinkedList<String> list_of_vertices = new LinkedList<>();

        while(file_2.hasNextLine()){
            String line = file_2.nextLine();
            String [] line_splitted = line.split(",");
            String start_name = line_splitted[0];               //start and dest names 
            String dest_name = line_splitted[1];

            Graph.Vertice start_Vertice = null;
            Graph.Vertice dest_Vertice = null;

            if(!list_of_vertices.contains(line_splitted[0])){
                list_of_vertices.add(line_splitted[0]);
                int index = total_count_of_vertices;
                total_count_of_vertices++;                                              //if start_vertice not added to adj ads and takes the vertice
                start_Vertice = directed_graph.new Vertice(index,start_name);
                directed_graph.adjList[index].add(start_Vertice);
            }
            else{
                int index = directed_graph.find_index(start_name);
                if (index != -1) {                                                              //it its added it takes Vertice from adjList
                    start_Vertice = directed_graph.adjList[index].get(0);
                } else {
                    System.out.println(start_name + " not found");
                }
            }
            if(!list_of_vertices.contains(line_splitted[1])){
                list_of_vertices.add(line_splitted[1]);                                     //if start_vertice not added to adj ads and takes the vertice
                int index = total_count_of_vertices;
                total_count_of_vertices++;
                dest_Vertice = directed_graph.new Vertice(index,dest_name);
                directed_graph.adjList[index].add(dest_Vertice);

            }
            else{
                int index = directed_graph.find_index(dest_name);
                if (index != -1) {
                    dest_Vertice = directed_graph.adjList[index].get(0);
                } else {
                    System.out.println(dest_name + " not found");
                }
                
            }

            directed_graph.addEdge(start_Vertice, dest_Vertice);                //creates edges between start and dest vertices
        

           
        }
        file_2.close();
        
        }

        catch(IOException e){
            
        }
        
        return directed_graph;                          //returns the graph
    }

    public void run() throws IOException{
        //--------------------------------------------------------
        // Summary: run function of the main class it makes all the nececary stuffs and gives the correct output
        // Precondition: there i no pre condiciton
        // Postcondition: it will give the correct output
        //--------------------------------------------------------

        Scanner scan = new Scanner(System.in);
        String txt_name = scan.next();                                  //takes inputs
        int num_of_hoops = scan.nextInt();
        String start = scan.next();

        int count = this.read_txt(txt_name);
        Graph directed_graph = create_graph(count); //creates graph

         

        Graph.Vertice startV = directed_graph.adjList[directed_graph.find_index(start)].get(0);     //start vertice of the DFS


        directed_graph.printAllPathsToDest(startV, num_of_hoops);           //dfs method in graph that takes hoops count 

        

        
    }


    public static void main(String[] args) throws IOException {
        Q1 objQ1 = new Q1();                            //main method
        objQ1.run();

        
    }
    
}
