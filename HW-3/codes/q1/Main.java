package q1;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    //-----------------------------------------------------
	// Title: Question 1 Main class
	// Author: Mehmet Alp Demiral
	// Description: This class is the includes all of the methods for question 1 Main
	//-----------------------------------------------------
    
    public ArrayList<Graph.Vertice> read_txt(String file_name){
        //--------------------------------------------------------
        // Summary: Reads the txt file and creates Vertices and add them to arraylist and returns
        // Precondition: String file_name
        // Postcondition: The vertice ArrayList will be returned
        //--------------------------------------------------------

        Graph graphInstance = new Graph(0);     //temporal graph object for using graph class methods
        ArrayList<Graph.Vertice> all_vertice = new ArrayList<>();   // the arraylist we will return
        int count = 0;                                              //count for vertice id's

        try{
        Scanner file = new Scanner(new File(file_name));
        

        while(file.hasNextLine()){                  
            String line = file.nextLine();                  
            String [] line_splitted = line.split(",");
            Graph.Vertice addVertice = graphInstance.new Vertice(count++, line_splitted[0], Integer.parseInt(line_splitted[1]) ,  Integer.parseInt(line_splitted[2]));
            all_vertice.add(addVertice);             // creates vertice objects and add them to arraylist
        }
        file.close();
        return all_vertice;                     //returns the arraylist
       
        
        }

        catch(IOException e){
            System.out.println("File not found!");
        }
        return null;
        
    }
    public Graph createGraph(ArrayList<Graph.Vertice> verticeList){
        //--------------------------------------------------------
        // Summary: Creates the graph via Arraylist
        // Precondition: ArrayList<Graph.Vertice> verticeList
        // Postcondition: The graph will be created
        //--------------------------------------------------------

        Graph returnGraph = new Graph(verticeList.size());
        returnGraph.adjList = verticeList;
        returnGraph.createEdges();
        return returnGraph;
    }

    public void run(){
        //--------------------------------------------------------
        // Summary: Run function for making all things wanted from us
        // Precondition: There is no precondition
        // Postcondition: All the wanted things will be done
        //--------------------------------------------------------
        Scanner scan = new Scanner(System.in);
        String file_name = scan.next();
        ArrayList<Graph.Vertice> list = read_txt(file_name);            //reads
        Graph graph = createGraph(list);                                //create graphs
        ArrayList<Graph.Edge> print = graph.create_mst_with_prim();     //slecting edges we need to use to create mst

        System.out.println("Paths are:");               //and prints all the edges

        for(int index = 0;index<print.size();index++){
            String lenght = String.format("%.1f", print.get(index).lenght);
            System.out.println(print.get(index).start.vertice_name+"-"+print.get(index).dest.vertice_name+": "+lenght);
        }

    }

    public static void main(String[] args) {        //main method of the question 1
        Main obj = new Main();
        obj.run();
        
    }
    
}
