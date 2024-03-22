import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Trie {
    //-----------------------------------------------------
    // Title: Trie Class
    // Author: Mehmet Alp Demiral
    // Description: This class contains all the trie methods
    //-----------------------------------------------------
    
    //attributes of Trie Class

    //words for calling recursively
    public ArrayList<String> trie_words;
    //size of alphabet we are using for storing chars
    public static final int size_of_alphabet_using = 26;
   
     
    public class Nodes
    {
        //attributes of nodes for storing chars on nodes
        boolean is_end_node = false;        //firstly en node is false
        int nodes_count_after_this_node;    //for storing how many nodes added after this node
        
        String word_of_end_node;        //if the node is en node for word attribute
        char node_char;                 //nodes's char

        Nodes[] child_nodes = new Nodes[size_of_alphabet_using];        //every node has child node and this is the attribute
        
         
        public Nodes() {}           //default constructor

        public Nodes(char node_char) {      //for creating Nodes object with char
            this.node_char = node_char;
        }
    }
      
    public Nodes root;              //starting node

    public Trie() {                     //trie class default constructor 
        this.root = new Nodes();            //creates first node
        trie_words = new ArrayList<>();     //and create list
    }
    
    public void insert(String word) {           //inserting word to trie method
        this.trie_words.add(word);              //first adds word to array
        Nodes node = root;                      //starts from starting point
        
        for (int index = 0; index < word.length(); index++) {           //and goes char by char on word with for loop
            
            if (node.child_nodes[word.charAt(index) - 'a'] == null) {                           //if the node is null adds the node 
                node.child_nodes[word.charAt(index) - 'a'] = new Nodes(word.charAt(index));
                node.nodes_count_after_this_node++;
            }
            node = node.child_nodes[word.charAt(index) - 'a'];      //and goes the next node
        }
        node.is_end_node = true;            //when the word is finished make the last node end node
        node.word_of_end_node = word;       //make the end nodes word 
    }

    public void countPrefix() {                     //count prefix method

        Map<String, Integer> prefix_map_for_print = new TreeMap<>();                //for printing firstly created map
    
        for (String word : trie_words) {
            Nodes currentNode = root;               //starts from beginning and iterate char by char
    

            for (char word_char : word.toCharArray()) {                         //char by char iteration

                if (currentNode.child_nodes[word_char - 'a'] == null) {
                    break;                                                      //if the node is null breaks the loop
                }
    
                currentNode = currentNode.child_nodes[word_char - 'a'];             //geos child node
                
                if (currentNode.word_of_end_node != null) {
                    prefix_map_for_print.putIfAbsent(currentNode.word_of_end_node, -1);                        //if the word is done add if word didnt add on map else increase count 1
                    prefix_map_for_print.put(currentNode.word_of_end_node, prefix_map_for_print.get(currentNode.word_of_end_node) + 1);     //increase count 1
                }
            }
        }

        for (Map.Entry<String, Integer> print_every_word_on_order : prefix_map_for_print.entrySet()) {          //printing the word and count prefix value
            System.out.println(print_every_word_on_order.getKey() + ": " + print_every_word_on_order.getValue());
        }
    }

    public void search(String arg) {            //searching word method
        Nodes node = this.root;
        boolean is_found = false;                   //for returning and printing
        for (int i = 0; i < arg.length(); i++) {       //for itearation char by char
            
            //if the words are done make found false
            if (node.child_nodes[arg.charAt(i) - 'a'] == null) {            
                is_found = false;
                break;
            }
            //goes next node
            node = node.child_nodes[arg.charAt(i) - 'a'];       
        }
        //if found the word make boolean true
        if(node.is_end_node && node != null && node.word_of_end_node.length()==arg.length()){       
            is_found = true;
        }
        if(is_found){
            System.out.println("True");
        }                                           //printing part
        else{
            System.out.println("False");
        }
    }

    public void LongestCommonPrefix(){              //longest common prefix function
        String current = "";
        Nodes currNode = this.root;                 //startin conditions
        current += findLongest(currNode, current);      //calling recursive method
        if(current.length()==0){                
            System.out.println("not exists");           
        }                       // printing part
        else{
            System.out.println(current);
        }
        
        
    }
    public String findLongest(Nodes node, String prefix) {          //recursive function for longest common prefix
        int counter = 0;
        int index = 0;
    
        for (int i = 0; i < size_of_alphabet_using; i++) {
            if (node.child_nodes[i] != null) {
                counter++;                                      //for iterate all child nodes
                index = i;
            }
        }
    
        if (counter > 1) {
            return prefix;                  //if there is more than 1 child nodes it returns the current prefix
        } else if (counter == 1) {
            prefix += node.child_nodes[index].node_char;        //if there is 1 child node it resumes
            node = node.child_nodes[index];                     //goes next node if the condition provided
    
            if (node.is_end_node) {
                return node.word_of_end_node;              //if it comes the end on some word retruns this word
            }
    
            return findLongest(node, prefix);       //cals it with next node
        } else {
            return prefix;
        }
    }
    
    
    
    public void recursive_for_reverse_find(Nodes node, String currentPrefix, String suffix,  ArrayList<String> words_ArrayList) {       //reverse find recursive function
        
        if (node.is_end_node && node.word_of_end_node.endsWith(suffix)) {
            //exit condition if the word ends with suffix
            words_ArrayList.add(node.word_of_end_node);         
        }

        for (int i = 0; i < size_of_alphabet_using; i++) {
            if (node.child_nodes[i] != null) {              //goes the next child nodes they are not null
                
                //cals it recursively
                recursive_for_reverse_find(node.child_nodes[i], currentPrefix + (char) (i + 'a'), suffix,  words_ArrayList);        
            }
        }
    }

    public void reverseFind(String suffix) {
        //for storing words array
        ArrayList<String> words_ArrayList = new ArrayList<>();
        //cals recursive function with current prefix
        recursive_for_reverse_find(root, "", suffix,  words_ArrayList);

        //print the words end with suffix
        for(String word:words_ArrayList){
            System.out.println(word);
        }
    }

    
    public String findShortestUniquePrefix(Nodes current_node, String currentPrefix, String word_searching_prefix) {
        //shortes unşque prefix recursive function

    Nodes root = current_node;
    int counter = 0;        //counter for how many nodes moved
    while (counter < word_searching_prefix.length()) {
        char word_char = word_searching_prefix.charAt(counter);
        current_node = current_node.child_nodes[word_char - 'a'];   //iterate char by char and andd these nodes chars to current prefix
        currentPrefix += word_char;
        counter++;
    }
    //if the word is done goes another if

    if (current_node != null && current_node.nodes_count_after_this_node == 0) {//checks how many nodes after this node if it is 0 goes if and start iteratin again 
        char zero_char = word_searching_prefix.charAt(0);                   //else it returns not exists
        String returning_prefix = "";
        returning_prefix += root.child_nodes[zero_char - 'a'].node_char;        //adds zero index of the word for start to returning prefix
        root = root.child_nodes[word_searching_prefix.charAt(0) - 'a'];     //start from root and searching for words inside this word and if there is makes the current this word

        //start iterating word
        for (int index_of_nodes = 1; index_of_nodes < word_searching_prefix.length()-1; index_of_nodes++) {
            //goes next node firstly
            root = root.child_nodes[word_searching_prefix.charAt(index_of_nodes) - 'a']; 

            //if there is any word inside make it returning prefix and 
            if (root.is_end_node) {
                returning_prefix = root.word_of_end_node;

                if(returning_prefix.length() < word_searching_prefix.length()-1){   //do this untill comes this condition

                    //goes next node
                    root = root.child_nodes[word_searching_prefix.charAt(returning_prefix.length()) - 'a'];
                    //for this move for loop's index moves 1 
                    index_of_nodes++;
                    //add current char to prefix
                    returning_prefix += root.node_char;
                    

                }
                if (this.trie_words.contains(returning_prefix)) {
                    //and if there is end node move 1 more move
                    Nodes nextNode = root.child_nodes[word_searching_prefix.charAt(returning_prefix.length()) - 'a'];
                    returning_prefix += nextNode.node_char;
                }
                
            }
        }   
        //returns prefix
        return returning_prefix;
    }
    //default return condition if any prefix found
    return "not exists";
}
    

    public void ShortestUniquePrefix() {
        //shortest unique prefix function
        for (String wanted_word : this.trie_words) {
            //starts from root for every word and calls the recursive function word by word
            Nodes currentNode = root;
            String shortestUniquePrefix = findShortestUniquePrefix(currentNode, "", wanted_word);
            //prints the word and this words shortest unique prefix
            System.out.println(wanted_word + ": " + shortestUniquePrefix);
        }
    }
    
    

}