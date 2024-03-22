package q1;
//-----------------------------------------------------
// Title: Main Class
// Author: Mehmet Alp Demiral
// Description: This class contains all methods necesary things for first questions output
//-----------------------------------------------------

import java.util.Scanner;

public class Main {

public void runFunction()
//--------------------------------------------------------
// Summary: runFuncion ofr main 
// Precondition: there is no pre condition
// Postcondition: it makes all the necesary conditions for outout
//--------------------------------------------------------
    {

        //takes needed values
        Scanner scan = new Scanner(System.in);
        String first_line = scan.nextLine();
        String[]values = first_line.split(" ");
        int station_number = Integer.parseInt(values[0]);
        int undirected_railwaty_tracks = Integer.parseInt(values[1]);
        int home_station = Integer.parseInt(values[2]);
        int tedu = Integer.parseInt(values[3]);

        //creates graph
        Graph undirected_graph = new Graph(station_number);

        //add edges between inputs
        for(int i = 0;i<undirected_railwaty_tracks;i++){
            int edge1 = scan.nextInt();
            int edge2 = scan.nextInt();

            undirected_graph.addEdge(edge1, edge2);
        }
        
        //added edges list
        String[]added = new String[station_number*station_number];
        int count = 0;
        int min_distance = undirected_graph.get_min_path_length(home_station, tedu);

        for(int first = 1;first<=station_number;first++){
            //creates temp list for calculate new min distance
            Graph temp_graph = undirected_graph;
            min_distance = undirected_graph.get_min_path_length(home_station, tedu);
                for(int second = 1;second<=station_number;second++){
                    
                    //for avoiding duplicate edges
                    if(first == second || undirected_graph.has_Adj(first, second)){
                        continue;
                    }
                    else{

                        temp_graph.addEdge(first, second);
                        
                        //calculate and compares min path lenght and if it is same or not changed it equalizes 
                        if(temp_graph.get_min_path_length(home_station, tedu)>=min_distance){
                            undirected_graph = temp_graph;
                            added[count++] = first +" "+ second;
                        }
                        else{
                            continue;
                        }

                    }
                }
            }

            if(count == 0){
                System.out.println(-1);
            }
            else{

                //prints all added paths
                System.out.println(count);
                for(int counter = 0;counter<count;counter++){
                System.out.println(added[counter]);
            }
            }


            scan.close();
    }


    public static void main(String[] args) {

        Main run_object = new Main();
        run_object.runFunction();
        


    }
    
}
