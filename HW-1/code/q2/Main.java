package q2;
//-----------------------------------------------------
// Title: Main Class
// Author: Mehmet Alp Demiral
// Description: This class contains all methods necesary things for second questions output
//-----------------------------------------------------


import java.util.Scanner;



public class Main {


    public void runFunction()
//--------------------------------------------------------
// Summary: main function
// Precondition: there is no pre condiiton
// Postcondition: it takes input and output on wanted way
//--------------------------------------------------------
    {

        Scanner scan = new Scanner(System.in);
        String first_line = scan.nextLine();
        String[]values = first_line.split(" ");
        int museum_count = Integer.parseInt(values[0]);
        int undirected_roads = Integer.parseInt(values[1]);

        WeightedGraph weightedGraph = new WeightedGraph(museum_count);

        for(int a = 0; a<undirected_roads;a++){
            int edge1 = scan.nextInt();
            int edge2 = scan.nextInt();
            int weight = scan.nextInt();
            weightedGraph.addEdge(edge1, edge2, weight);
        }

       weightedGraph.iterateFunctionWithMinTime();
        




    }

    public static void main(String[] args) {
        
        Main runObject = new Main();
        runObject.runFunction();

    }
    
}
