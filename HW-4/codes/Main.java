import java.util.Scanner;

public class Main {
    //-----------------------------------------------------
    // Title: Main Class
    // Author: Mehmet Alp Demiral
    // Description: This class contains all the Main methods
    //-----------------------------------------------------

    public void run(){      //run function for main and does the all wanted things
        Trie trie = new Trie();
        Scanner scan = new Scanner(System.in);
        int num_of_inputs = scan.nextInt();         //takes inputs

        for(int add_count = 0;add_count<num_of_inputs;add_count++){
            String word = scan.next();
            word = word.toLowerCase();      //adds words to trie
            trie.insert(word);
        }
        int operation = 0;
        while(operation!=6){            //take operaiton from user
            System.out.println("Enter Operation Code:");
            operation = scan.nextInt();

            if(operation == 1){
                String search_word = scan.next();
                trie.search(search_word.toLowerCase());         //searcf case for trie
                
                
            }

            if(operation == 2){
                trie.countPrefix();             //count prefix case for trie

            }
            
            if(operation == 3){
                String suffix = scan.next();                    //reverse find case for trie
                trie.reverseFind(suffix.toLowerCase());
            }

            if(operation == 4){
                trie.ShortestUniquePrefix();        //shortest unique prefix for trie
            }

            if(operation == 5){
                trie.LongestCommonPrefix();     //longest common prefix for trie
            }
        }
        scan.close();           //exits run function
    }

    public static void main(String[] args) {
        Main mainObject = new Main();       //main method
        mainObject.run();
        
    }
    
}
