/*
 WordLadder Solver Class
 Solves EE422C programming assignment #4
 @author Hari (), Kevin Yee (kjy252)
 @version 1.00 2016-02-025
 */
 

package assignment4;

import java.util.List;
import java.util.*;

// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface
{
    // delcare class members here.

	
	public static  ArrayList<String> Dictionary = new ArrayList<String>();
	public static ArrayList<String> SolutionsList = new ArrayList<String>();
	public static char[] eachLetter = new char[5];
	
    // add a constructor for this object. HINT: it would be a good idea to set up the dictionary there
	

	
	public void setLetter(String start){
		this.eachLetter[0] = start.charAt(0);
		this.eachLetter[1] = start.charAt(1);
		this.eachLetter[2] = start.charAt(2);
		this.eachLetter[3] = start.charAt(3);
		this.eachLetter[4] = start.charAt(4);
	}
		
    
    @Override
    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
    {
    HashMap<String,Integer> ladder = new HashMap<>(); //ladder from one word to another
    HashMap<String,ArrayList<String>> graph = new HashMap<>(); //graph of all possibilities
    Queue<String> Q = new LinkedList<String>();
    	
    ladder.put(startWord, 1);
    Q.add(startWord);
    int minSteps = Integer.MAX_VALUE;
    
    //BFS
    while(!Q.isEmpty()){
    	String word = Q.remove();
    	setLetter(word); //allows for easier conversions of char
    	
    	if(word == endWord){
    		minSteps = ladder.get(word); //we've found our word and can exit
    		break;
    	}
    	
    	int count = ladder.get(word) + 1; //one step from each previous word. keeps track of duplicates
    	
    	
    	for(int i =0; i <5; i++){
    		char[] temp = new char[5];
    		temp = Arrays.copyOf(eachLetter, 5);
    		for(char c = 'a'; c <= 'z'; c++)
    		{
    			/*Iterate through ever possible differOne combos*/
    			
    			temp[i] = c;
    			String tempString = new String(temp);
    			if(!getDictionary().contains(tempString)){continue;} //if not in dictionary, ignore
    			
    			if(!ladder.containsKey(tempString)){ //if in dictionary, but not in our ladder, put it in.
    				ladder.put(tempString, count);
    				Q.add(tempString);
    				ArrayList<String> wordGraph = new ArrayList<String>(); 
    				wordGraph.add(word);
    				graph.put(tempString, wordGraph); //put in the first word as tempString, wordGraph holds ArrayList of all differ ones
    				continue;
    			}
    			
    			if(ladder.get(tempString) < count)continue; //check if word is existing
    			else if(ladder.get(tempString) == count) //word doesn't exist in arraylist yet, put it in
    				graph.get(tempString).add(word);
    		}
    	}
    }
    
    
  ArrayList<String> result = new ArrayList<>();
  
  if(!graph.containsKey(endWord)){System.out.println("No path found");
  return SolutionsList;}
    
    buildResult(endWord, startWord,result,graph);
    
    SolutionsList = result;
    return SolutionsList;
    }
    
    public boolean found = false;
   public ArrayList<String> buildResult(String end, String startWord,ArrayList<String> result,HashMap<String,ArrayList<String>>graph){
        if (end.equals(startWord)){
            result.add(end);
            found = true;
            return result;
        }
        if(found){
        	return result;
        }
        result.add(end);
        if (end.equals(startWord)){
            result.add(end);
            return result;
        }
        for (String s: graph.get(end)){
        	if (end.equals(startWord)){
                break;
             }
        	try{
        	 buildResult(s, startWord,result,graph);
        	}
        	catch(NullPointerException e){
        		SolutionsList = result;
        		return result;
        	}
        	 
        }
        return result;
    }
        // implement this method
    
    

    @Override	
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    // add additional methods here
    
    public boolean diffbyOne(String str1, String str2){
    	
    	if (str1.length() != str2.length())
            return false;
        int same = 0;
        for (int i = 0; i < str1.length(); ++i) {
            if (str1.charAt(i) == str2.charAt(i))
                same++;
        }
        return same == str1.length() - 1;
    }


	public static ArrayList<String> getDictionary() {
		return Dictionary;
	}


	public  void setDictionary(ArrayList<String> dictionary) {
		Dictionary = dictionary;
	}
    	

    
    
}
