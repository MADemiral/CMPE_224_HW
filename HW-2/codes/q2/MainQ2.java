package q2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainQ2 {
    //-----------------------------------------------------
	// Title: Question 2 Main class
	// Author: Mehmet Alp Demiral
	// Description: This class includes main methods of the question 2
	//-----------------------------------------------------

    private static char[][] createMazeArray(String fileName) throws IOException {
        //--------------------------------------------------------
        // Summary: reads the txt and creates two dimensional array includes chars
        // Precondition: String file_name
        // Postcondition: txt will be readed and char array will eb returned
        //--------------------------------------------------------

        BufferedReader scanner = new BufferedReader(new FileReader(fileName));  //scanner object
        ArrayList<String> all_lines = new ArrayList<>();        //all lines of the txt file
        String current_line;                                    //current line

        while ((current_line = scanner.readLine()) != null) {       //read until end
            all_lines.add(current_line);
        }

        scanner.close();

        int rows = all_lines.size();
        int cols = all_lines.get(0).length();           //row and colus count
        char[][] maze = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            maze[i] = all_lines.get(i).toCharArray();       //get the chars
        }

        return maze;        //returns array
    }

    public GraphForMaze create_graph(char[][] maze_lines){
        //--------------------------------------------------------
        // Summary: create a graph from maze 
        // Precondition: char[][] maze_lines
        // Postcondition: maze will be created and returned
        //--------------------------------------------------------

        boolean [][] visitable= new boolean[maze_lines.length][maze_lines[0].length];   //visitable indexes      
        ArrayList<String> added = new ArrayList<>();                                //added array
        

        int count = 0;
        for(int i = 0;i<maze_lines.length;i++){
            for(int j = 0;j<maze_lines[0].length;j++){
                
                if((maze_lines[i][j]>='a' && maze_lines[i][j]<='z') || maze_lines[i][j] == 'E' ){
                    visitable[i][j] = true;             //making true visitable indexes
                    count++;            //increasing total count of the vertices
                }   
                
            }
        }

        GraphForMaze directed_maze_Graph = new GraphForMaze(count);     //graph created
        int id = 0;

        for(int col = 0;col<maze_lines.length;col++){
            for(int row = 0;row<maze_lines[0].length;row++){
                if(visitable[col][row]){
                    String vertice_name = col+","+row+","+maze_lines[col][row];         
                    GraphForMaze.Vertice addedVertice = directed_maze_Graph.new Vertice(id, vertice_name);      //vertices added
                    directed_maze_Graph.adjList[id].add(addedVertice);
                    id++;

                }
            }
        }
        //----------------------------------------------------
        //
        //
        // This part includes adding edge part that includes some conditions
        // for example right up corner, left up corner, left line and 
        //
        //----------------------------------------------------


        for(int column = 0;column<maze_lines.length;column++){
            for(int row = 0;row<maze_lines[0].length;row++){

                if(visitable[column][row]){

                String current_name = column+","+row+","+maze_lines[column][row];
                int current_index = directed_maze_Graph.find_index(current_name);
                GraphForMaze.Vertice currVertice = directed_maze_Graph.adjList[current_index].get(0);
                
                
                if(row== 0 && column == 0){      //left up corner c

                    if(visitable[column+1][row]){

                        String under_name = (column+1)+","+row+","+maze_lines[column+1][row];
                        int under_index = directed_maze_Graph.find_index(under_name);
                        GraphForMaze.Vertice under =directed_maze_Graph.adjList[under_index].get(0);

                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(current_name)].contains(under)){
                            directed_maze_Graph.addEdge(currVertice, under);
                        }
                        
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(under.vertice_name)].contains(currVertice)){
                            directed_maze_Graph.addEdge(under, currVertice);
                        }

                        }

                    if(visitable[column][row+1]){

                        String right_name = column+","+(row+1)+","+maze_lines[column][row+1];
                        int right_index = directed_maze_Graph.find_index(right_name);
                        GraphForMaze.Vertice right = directed_maze_Graph.adjList[right_index].get(0);
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(current_name)].contains(right)){
                            directed_maze_Graph.addEdge(currVertice, right);
                        }
                        
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(right.vertice_name)].contains(currVertice)){
                            directed_maze_Graph.addEdge(right, currVertice);
                        }
                      

                        }
                
               }

                else if(row == maze_lines[0].length-1 && column == 0){   //right up corner possibilities

                    if(visitable[column+1][row]){

                        String under_name = (column+1)+","+row+","+maze_lines[column+1][row];
                        int under_index = directed_maze_Graph.find_index(under_name);
                        GraphForMaze.Vertice under =directed_maze_Graph.adjList[under_index].get(0);
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(current_name)].contains(under)){
                            directed_maze_Graph.addEdge(currVertice, under);
                        }
                        
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(under.vertice_name)].contains(currVertice)){
                            directed_maze_Graph.addEdge(under, currVertice);
                        }

                        }

                    if(visitable[column][row-1]){

                        String left_name = column+","+(row-1)+","+maze_lines[column][row-1];
                        int left_index = directed_maze_Graph.find_index(left_name);
                        GraphForMaze.Vertice left = directed_maze_Graph.adjList[left_index].get(0);
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(current_name)].contains(left)){
                            directed_maze_Graph.addEdge(currVertice, left);
                        }
                        
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(left.vertice_name)].contains(currVertice)){
                            directed_maze_Graph.addEdge(left, currVertice);
                        }
  

                        }

               }

                else if(row == 0 && column == maze_lines.length-1){  //left down corner possibilities

                    if(visitable[column-1][row]){

                        String above_name = (column-1)+","+row+","+maze_lines[column-1][row];
                        int above_index = directed_maze_Graph.find_index(above_name);
                        GraphForMaze.Vertice above =directed_maze_Graph.adjList[above_index].get(0);
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(current_name)].contains(above)){
                            directed_maze_Graph.addEdge(currVertice, above);
                        }
                        
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(above.vertice_name)].contains(currVertice)){
                            directed_maze_Graph.addEdge(above, currVertice);
                        }


                        }

                    if(visitable[column][row+1]){

                        String right_name = column+","+(row+1)+","+maze_lines[column][row+1];
                        int right_index = directed_maze_Graph.find_index(right_name);
                        GraphForMaze.Vertice right = directed_maze_Graph.adjList[right_index].get(0);
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(current_name)].contains(right)){
                            directed_maze_Graph.addEdge(currVertice, right);
                        }
                        
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(right.vertice_name)].contains(currVertice)){
                            directed_maze_Graph.addEdge(right, currVertice);
                        }


                        }
    
               }

                else if(row == maze_lines[0].length-1 && column == maze_lines.length-1){     //right down corner possibilities

                    if(visitable[column-1][row]){

                        String above_name = (column-1)+","+row+","+maze_lines[column-1][row];
                        int above_index = directed_maze_Graph.find_index(above_name);
                        GraphForMaze.Vertice above =directed_maze_Graph.adjList[above_index].get(0);
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(current_name)].contains(above)){
                            directed_maze_Graph.addEdge(currVertice, above);
                        }
                        
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(above.vertice_name)].contains(currVertice)){
                            directed_maze_Graph.addEdge(above, currVertice);
                        }


                        }

                    if(visitable[column][row-1]){

                        String left_name = column+","+(row-1)+","+maze_lines[column][row-1];
                        int left_index = directed_maze_Graph.find_index(left_name);
                        GraphForMaze.Vertice left = directed_maze_Graph.adjList[left_index].get(0);
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(current_name)].contains(left)){
                            directed_maze_Graph.addEdge(currVertice, left);
                        }
                        
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(left.vertice_name)].contains(currVertice)){
                            directed_maze_Graph.addEdge(left, currVertice);
                        }

                        }

               }

                else if((column > 0 && column < maze_lines.length-1) && row == 0){      //left line possibilities

                    if(visitable[column-1][row]){

                        String above_name = (column-1)+","+row+","+maze_lines[column-1][row];
                        int above_index = directed_maze_Graph.find_index(above_name);
                        GraphForMaze.Vertice above =directed_maze_Graph.adjList[above_index].get(0);
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(current_name)].contains(above)){
                            directed_maze_Graph.addEdge(currVertice, above);
                        }
                        
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(above.vertice_name)].contains(currVertice)){
                            directed_maze_Graph.addEdge(above, currVertice);
                        }


                        }

                    if(visitable[column+1][row]){

                        String under_name = (column+1)+","+row+","+maze_lines[column+1][row];
                        int under_index = directed_maze_Graph.find_index(under_name);
                        GraphForMaze.Vertice under =directed_maze_Graph.adjList[under_index].get(0);
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(current_name)].contains(under)){
                            directed_maze_Graph.addEdge(currVertice, under);
                        }
                        
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(under.vertice_name)].contains(currVertice)){
                            directed_maze_Graph.addEdge(under, currVertice);
                        }

        
                        }

                    if(visitable[column][row+1]){

                        String right_name = column+","+(row+1)+","+maze_lines[column][row+1];
                        int right_index = directed_maze_Graph.find_index(right_name);
                        GraphForMaze.Vertice right = directed_maze_Graph.adjList[right_index].get(0);
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(current_name)].contains(right)){
                            directed_maze_Graph.addEdge(currVertice, right);
                        }
                        
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(right.vertice_name)].contains(currVertice)){
                            directed_maze_Graph.addEdge(right, currVertice);
                        }


                    }
                
            }
               
                else if(column == 0 && (row > 0 && row < maze_lines[0].length-1)){      //up line possibilities

                    if(visitable[column+1][row]){

                        String under_name = (column+1)+","+row+","+maze_lines[column+1][row];
                        int under_index = directed_maze_Graph.find_index(under_name);
                        GraphForMaze.Vertice under =directed_maze_Graph.adjList[under_index].get(0);
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(current_name)].contains(under)){
                            directed_maze_Graph.addEdge(currVertice, under);
                        }
                        
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(under.vertice_name)].contains(currVertice)){
                            directed_maze_Graph.addEdge(under, currVertice);
                        }

        
                        }
                
                    if(visitable[column][row-1]){

                        String left_name = column+","+(row-1)+","+maze_lines[column][row-1];
                        int left_index = directed_maze_Graph.find_index(left_name);
                        GraphForMaze.Vertice left = directed_maze_Graph.adjList[left_index].get(0);
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(current_name)].contains(left)){
                            directed_maze_Graph.addEdge(currVertice, left);
                        }
                        
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(left.vertice_name)].contains(currVertice)){
                            directed_maze_Graph.addEdge(left, currVertice);
                        }

        
                        }
        
                    if(visitable[column][row+1]){
        
                        String right_name = column+","+(row+1)+","+maze_lines[column][row+1];
                        int right_index = directed_maze_Graph.find_index(right_name);
                        GraphForMaze.Vertice right = directed_maze_Graph.adjList[right_index].get(0);
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(current_name)].contains(right)){
                            directed_maze_Graph.addEdge(currVertice, right);
                        }
                        
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(right.vertice_name)].contains(currVertice)){
                            directed_maze_Graph.addEdge(right, currVertice);
                        }
        
                        }
                
                




               }

                else if (row == maze_lines[0].length-1 && (column > 0 && column <maze_lines.length-1)){         //right line possibilities

                    if(visitable[column-1][row]){

                        String above_name = (column-1)+","+row+","+maze_lines[column-1][row];
                        int above_index = directed_maze_Graph.find_index(above_name);
                        GraphForMaze.Vertice above =directed_maze_Graph.adjList[above_index].get(0);
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(current_name)].contains(above)){
                            directed_maze_Graph.addEdge(currVertice, above);
                        }
                        
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(above.vertice_name)].contains(currVertice)){
                            directed_maze_Graph.addEdge(above, currVertice);
                        }

                        }
    
                    if(visitable[column+1][row]){
    
                        String under_name = (column+1)+","+row+","+maze_lines[column+1][row];
                        int under_index = directed_maze_Graph.find_index(under_name);
                        GraphForMaze.Vertice under =directed_maze_Graph.adjList[under_index].get(0);
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(current_name)].contains(under)){
                            directed_maze_Graph.addEdge(currVertice, under);
                        }
                        
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(under.vertice_name)].contains(currVertice)){
                            directed_maze_Graph.addEdge(under, currVertice);
                        }

        
                        }
    
                    if(visitable[column][row-1]){
    
                        String left_name = column+","+(row-1)+","+maze_lines[column][row-1];
                        int left_index = directed_maze_Graph.find_index(left_name);
                        GraphForMaze.Vertice left = directed_maze_Graph.adjList[left_index].get(0);
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(current_name)].contains(left)){
                            directed_maze_Graph.addEdge(currVertice, left);
                        }
                        
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(left.vertice_name)].contains(currVertice)){
                            directed_maze_Graph.addEdge(left, currVertice);
                        }

        
                        }
               }
            
                else if(column == maze_lines.length -1 && (row > 0 && row < maze_lines[0].length-1)){           //down line possibilities

                    if(visitable[column-1][row]){

                        String above_name = (column-1)+","+row+","+maze_lines[column-1][row];
                        int above_index = directed_maze_Graph.find_index(above_name);
                        GraphForMaze.Vertice above =directed_maze_Graph.adjList[above_index].get(0);
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(current_name)].contains(above)){
                            directed_maze_Graph.addEdge(currVertice, above);
                        }
                        
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(above.vertice_name)].contains(currVertice)){
                            directed_maze_Graph.addEdge(above, currVertice);
                        }

        
                        }
                
                    if(visitable[column][row-1]){

                        String left_name = column+","+(row-1)+","+maze_lines[column][row-1];
                        int left_index = directed_maze_Graph.find_index(left_name);
                        GraphForMaze.Vertice left = directed_maze_Graph.adjList[left_index].get(0);
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(current_name)].contains(left)){
                            directed_maze_Graph.addEdge(currVertice, left);
                        }
                        
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(left.vertice_name)].contains(currVertice)){
                            directed_maze_Graph.addEdge(left, currVertice);
                        }

        
                        }
        
                    if(visitable[column][row+1]){
        
                        String right_name = column+","+(row+1)+","+maze_lines[column][row+1];
                        int right_index = directed_maze_Graph.find_index(right_name);
                        GraphForMaze.Vertice right = directed_maze_Graph.adjList[right_index].get(0);
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(current_name)].contains(right)){
                            directed_maze_Graph.addEdge(currVertice, right);
                        }
                        
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(right.vertice_name)].contains(currVertice)){
                            directed_maze_Graph.addEdge(right, currVertice);
                        }

        
                        }




               }
               
                else if((row<maze_lines[0].length-1 && row >0) && (column>0 && column< maze_lines.length)){         //inside lines possibilities

                    if(visitable[column-1][row]){

                        String above_name = (column-1)+","+row+","+maze_lines[column-1][row];
                        int above_index = directed_maze_Graph.find_index(above_name);
                        GraphForMaze.Vertice above =directed_maze_Graph.adjList[above_index].get(0);
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(current_name)].contains(above)){
                            directed_maze_Graph.addEdge(currVertice, above);
                        }
                        
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(above.vertice_name)].contains(currVertice)){
                            directed_maze_Graph.addEdge(above, currVertice);
                        }

        
                        }
    
    
                    if(visitable[column+1][row]){
    
                        String under_name = (column+1)+","+row+","+maze_lines[column+1][row];
                        int under_index = directed_maze_Graph.find_index(under_name);
                        GraphForMaze.Vertice under =directed_maze_Graph.adjList[under_index].get(0);
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(current_name)].contains(under)){
                            directed_maze_Graph.addEdge(currVertice, under);
                        }
                        
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(under.vertice_name)].contains(currVertice)){
                            directed_maze_Graph.addEdge(under, currVertice);
                        }


                        }
    
                    if(visitable[column][row-1]){
    
                        String left_name = column+","+(row-1)+","+maze_lines[column][row-1];
                        int left_index = directed_maze_Graph.find_index(left_name);
                        GraphForMaze.Vertice left = directed_maze_Graph.adjList[left_index].get(0);
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(current_name)].contains(left)){
                            directed_maze_Graph.addEdge(currVertice, left);
                        }
                        
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(left.vertice_name)].contains(currVertice)){
                            directed_maze_Graph.addEdge(left, currVertice);
                        }

        
                        }
    
                    if(visitable[column][row+1]){
    
                        String right_name = column+","+(row+1)+","+maze_lines[column][row+1];
                        int right_index = directed_maze_Graph.find_index(right_name);
                        GraphForMaze.Vertice right = directed_maze_Graph.adjList[right_index].get(0);
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(current_name)].contains(right)){
                            directed_maze_Graph.addEdge(currVertice, right);
                        }
                        
                        if(!directed_maze_Graph.adjList[directed_maze_Graph.find_index(right.vertice_name)].contains(currVertice)){
                            directed_maze_Graph.addEdge(right, currVertice);
                        }


        
                        }


               }

            }
                
            }
            
        }


        directed_maze_Graph.col_count = maze_lines.length;
        directed_maze_Graph.row_count = maze_lines[0].length;       // col count and row count attribute of the graph

        return directed_maze_Graph;



    }

    public void printPaths(ArrayList<String> paths_founded_array){
        //--------------------------------------------------------
        // Summary: print all paths to treasure 
        // Precondition: ArrayList<String> paths_founded_array the arraylist that paths will be printed
        // Postcondition: paths printed for output
        //--------------------------------------------------------
        System.out.println(paths_founded_array.size() + " treasures are found.");
		if (paths_founded_array.size() > 0) {
			System.out.println("Paths are:");
			for (int index = 0; index < paths_founded_array.size(); index++) {
				System.out.println((index + 1) + ".) " + paths_founded_array.get(index));       //printing paths elements
			}
            System.out.println();
		}
    }

    public String find_entrance(char [][] maze_lines){
        //--------------------------------------------------------
        // Summary: finding entrance index of the maze 
        // Precondition: char [][] maze_lines maze array includes chars
        // Postcondition: entrance vertice name will be returned
        //--------------------------------------------------------
        
        for(int i = 0;i<maze_lines.length;i++){
            if(maze_lines[i][0]>= 'a' && maze_lines[i][0]<= 'z'){           //left line
                return i+",0"+","+maze_lines[i][0];
            }
        }

        for(int i = 0;i<maze_lines[0].length;i++){                  //above line
            if(maze_lines[0][i]>= 'a' && maze_lines[0][i]<= 'z'){
                return"0,"+i+","+maze_lines[0][i];
            }
        }

        for(int i = 0;i<maze_lines.length;i++){
            if(maze_lines[i][maze_lines[0].length-1]>= 'a' && maze_lines[i][maze_lines[0].length-1]<= 'z'){     //right line
                return i+","+(maze_lines[0].length-1)+","+maze_lines[i][maze_lines[0].length-1];
            }
        }
        for(int i = 0;i<maze_lines[0].length;i++){
            if(maze_lines[maze_lines.length-1][i]>= 'a' && maze_lines[maze_lines.length-1][i]<= 'z'){       //below line
                return (maze_lines.length-1)+","+i+","+maze_lines[maze_lines.length-1][i];
            }
        }
        return"";

    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        String txt_name = scan.next();
        char[][] maze_lines = createMazeArray(txt_name);
        MainQ2 obj = new MainQ2();                                                              //main method of the question
        GraphForMaze directed_maze_graph = obj.create_graph(maze_lines);
        ArrayList<String> paths_founded = new ArrayList<>(); 
        String entrance = obj.find_entrance(maze_lines);
        int index = directed_maze_graph.find_index(entrance);
        String []splitted = entrance.split(",");
        paths_founded = directed_maze_graph.bfsToFindPath(directed_maze_graph.adjList[index].get(0),maze_lines,0 );
        obj.printPaths(paths_founded);

    }
    
}
