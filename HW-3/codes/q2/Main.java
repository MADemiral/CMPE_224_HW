package q2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    //-----------------------------------------------------
	// Title: Question 2 Main class
	// Author: Mehmet Alp Demiral
	// Description: This class is the includes all of the methods for question 2 Main
	//-----------------------------------------------------
    public ArrayList<String> read_txt(String file_name){
        //--------------------------------------------------------
        // Summary: Reads the txt file and adds all cities names
        // Precondition: String file_name
        // Postcondition: The vertice ArrayList will be returned
        //--------------------------------------------------------

        ArrayList<String> city_list = new ArrayList<>();        //city_list for city names

        try{
        Scanner file = new Scanner(new File(file_name));
        

        while(file.hasNextLine()){                  
            String line = file.nextLine();                  
            String [] line_splitted = line.split(",");

            if(!city_list.contains(line_splitted[0])){
                city_list.add(line_splitted[0]);
            }
            if(!city_list.contains(line_splitted[1])){
                city_list.add(line_splitted[1]);
            }             
        }
        file.close();
        return city_list; 
        }

        catch(IOException e){
            System.out.println("File not found!");
        }
        return null;
        
    }

    public Graph create_weighted_graph(ArrayList<String> city_list, String file_name){
        //--------------------------------------------------------
        // Summary: Creates the graph via Arraylist
        // Precondition: ArrayList<String> city_list
        // Postcondition: The graph will be created
        //--------------------------------------------------------

        Graph weightedGraph = new Graph(city_list.size());
        int count = 0;
        for(String city_name:city_list){
            Graph.Vertice adding_city = weightedGraph.new Vertice(count++, city_name);      //creates all vertices city by city and add
            weightedGraph.adjList.add(adding_city);
        }
       
        try{
        Scanner file = new Scanner(new File(file_name));
        
        while(file.hasNextLine()){                  
            String line = file.nextLine();                  
            String [] line_splitted = line.split(",");
            String city1 = line_splitted[0];
            String city2 = line_splitted[1];
            int weight = Integer.parseInt(line_splitted[2]);                //then creates edges between the vertices we created

            Graph.Vertice city1_Vertice = weightedGraph.find_index_Vertice(city1);
            Graph.Vertice city2_Vertice = weightedGraph.find_index_Vertice(city2);
            weightedGraph.add_edge(city1_Vertice, city2_Vertice, weight);
       
        }
        file.close();

        }

        catch(IOException e){
            System.out.println("File not found!");
        }


        return weightedGraph;   //returns the graph

    }
    

    public void run(){
        //--------------------------------------------------------
        // Summary: Run function for main
        // Precondition: There is no precondition
        // Postcondition: the all the thing will be done
        //--------------------------------------------------------
        
        Scanner scan = new Scanner(System.in);
        String file_name = scan.next();
        String start = scan.next();
        String dest = scan.next();
        int steps = scan.nextInt();
        ArrayList<String> wanted = new ArrayList<>();               //doing all the nececary things reads txt creates graph and takes cities we wanted to visit
        for(int i = 0;i<steps;i++){
            String wanted_city_name = scan.next();
            wanted.add(wanted_city_name.toLowerCase());
        }
        
        ArrayList<String> city_list = read_txt(file_name);
        Graph weightedGraph = create_weighted_graph(city_list, file_name);
        Graph.Vertice start_Vertice = weightedGraph.find_index_Vertice(start);
        Graph.Vertice dest_Vertice = weightedGraph.find_index_Vertice(dest);                
        ArrayList<ArrayList<Graph.Vertice>> allPaths = weightedGraph.paths_finder(start_Vertice, dest_Vertice, steps, wanted);      //foundingt paths
        weightedGraph.print_List_Weight(allPaths);      //and printing the one which has min weight
    }

        
    public static void main(String[] args) {                    //main method of the q2
        Main obj = new Main();
        obj.run();
        
    }
    
}
