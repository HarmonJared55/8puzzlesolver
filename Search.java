//Author: Jared Harmon
//Date Created: 2/20/20
//Date Last Modified: 2/23/20

import java.util.Arrays;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Comparator;


class Search{

    //members
    private ArrayList<State> visited_states;
    public ArrayList<State> inital_states;
    public ArrayList<State> final_states;


    /**
    Constructor
    */
    public Search(){
        visited_states = new ArrayList<State>();
        inital_states = new ArrayList<State>();
        inital_states.add(new State());
        System.out.println("First State");
        inital_states.get(0).printState();
        inital_states.add(new State());
        System.out.println("Second State");
        inital_states.get(1).printState();
        inital_states.add(new State());
        System.out.println("Third State");
        inital_states.get(2).printState();
    }

    /**
    Breadth First Search Solve
    Params  - Init State
    Returns - void
    */
    public State BFS(State state){


        
        State solved_state = null;

        //create queue
        Queue<State> queue = new LinkedList<State>();

        //add start state as visited 
        visited_states.add(state);
         
        //generate starting states and add to queue
        state.generateStates(state.getState());
        for(int i = 0; i < state.getNextStates().size(); i++){
            queue.add(state.getNextStates().get(i));
        }

        int counter = 0;
        while(solved_state == null){
            State next = (State)queue.poll();
            if(next.isSolved(next.getState())){
                solved_state = next;
            }
            else{
                next.generateStates(next.getState());
                for(int i = 0; i < next.getNextStates().size(); i++){
                    if(!visited_states.contains(next.getNextStates().get(i))){
                        queue.add(next.getNextStates().get(i));
                        visited_states.add(next.getNextStates().get(i));
                    }        
                }
            }
            counter++;
            System.out.println(counter);
        }

        return solved_state;

    }

    public State aStar(State state){

        State solved_state = null;
        StateComparitor s = new StateComparitor();

        //create queue
        PriorityQueue<State> queue = new PriorityQueue<State>(10,s);

        //add start state as visited 
        visited_states.add(state);
         
        //generate starting states and add to queue
        state.generateStates(state.getState());
        for(int i = 0; i < state.getNextStates().size(); i++){
            queue.offer(state.getNextStates().get(i));
        }

        int counter = 0;
        while(solved_state == null){
            State next = (State)queue.poll();
            if(next.isSolved(next.getState())){
                solved_state = next;
            }
            else{
                next.generateStates(next.getState());
                for(int i = 0; i < next.getNextStates().size(); i++){
                    if(!visited_states.contains(next.getNextStates().get(i))){
                        queue.offer(next.getNextStates().get(i));
                        visited_states.add(next.getNextStates().get(i));
                    }        
                }
            }
            counter++;
            System.out.println(counter);
        }

        return solved_state;

        


    }

    /**
    Get intial states
    Params  - void
    Returns - ArrayList<State> 
    */


    public static void main(String[] args){
        Search search = new Search();
        
        //BFS on all three states
        for(int i = 0; i < search.inital_states.size(); i++){
            search.BFS(search.inital_states.get(i));
        }
        //aStar on all three states
        for(int i = 0; i < search.inital_states.size(); i++){
            search.aStar(search.inital_states.get(i));
        }

       

    }

}