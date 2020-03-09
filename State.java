//Author: Jared Harmon
//Date Created: 2/20/20
//Date Last Modified: 2/23/20

import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;

class State{

    //members
    private int[] state;
    private ArrayList<State> next_states;
    final private int[] SOLVED_STATE = {1,2,3,4,5,6,7,8,0};
    

    //public methods

        /**
        Null Constructor - Generates a valid board configuration
        */
        public State(){
            int[] temp_arr = new int[9];
            for(int i = 0; i < temp_arr.length; i++){
                temp_arr[i] = i;
            }
            do{
                temp_arr = shuffle(temp_arr);  
            }while(!isValid(temp_arr));  
            this.state = temp_arr;
        }

        /**
        Constructor - Generates a new state based on a given state array
        */
        public State(int[] arr){
            this.state = arr;
        }

        /**
        Get the state array
        Params  - void
        */
        public int[] getState(){
            return this.state;
        }

        /**
        Get the next_state array
        Params  - void
        */
        public ArrayList<State> getNextStates(){
            return next_states;
        }

        /**
        Prints State - Used for debugging
        */
        public void printState(){
            for(int i = 0; i < state.length; i++){
                if(i == 3 || i == 6){
                    System.out.println();
                }
                System.out.print(state[i]);
            }
            System.out.println();
        }

        public void setState(int[] state){
            this.state = state;
        }

        public int numOutOfOrder(int[] arr){
            int num_out_of_order = 0;
            for(int i = 0; i < arr.length; i++){
                for(int j = i + 1; j < arr.length; j++){
                    if(arr[i] > arr[j])
                        num_out_of_order++;
                }
            }
            return num_out_of_order;
        }

        public boolean isSolved(int[] arr){
            if(Arrays.equals(arr,SOLVED_STATE)){
                return true;
            }
            return false;
        }



    //private methods

        /**
        Shuffle the given array using the fisher-yates shuffle algorithm
        Params  - int[]
        Returns - int[]
        */
        private int[] shuffle(int[] arr){
            Random rand = new Random();
            int[] temp_arr = arr;
            for(int i = temp_arr.length - 1; i > 0; i--){
                int random_index = rand.nextInt(i+1);
                int temp = temp_arr[i];
                temp_arr[i] = temp_arr[random_index];
                temp_arr[random_index] = temp;
            }
            return temp_arr;
        }

        /**
        Determines if the board is valid and can be solved
        Params  - int[]
        Returns - boolean
        */
        private boolean isValid(int[] arr){
            int num_out_of_order = 0;
            for(int i = 0; i < arr.length; i++){
                for(int j = i + 1; j < arr.length; j++){
                    if(arr[i] > arr[j])
                        num_out_of_order++;
                }
            }
            if((num_out_of_order % 2) == 0){
                return true;
            }
            return false;
        }

        /**
        Generates new states based on current location on the board
        Params  - int[]
        Returns - State[]
        */
        public void generateStates(int[] arr){

            next_states = new ArrayList<State>();

            //find zero index
            int zero_index = 0;
            for(int i = 0; i < arr.length; i++){
                if(arr[i] == 0){
                    zero_index = i;
                }
            }

            int[] up_arr;
            
            //generate new moves based on posisiton in the array
            //up
            if(zero_index != 0 && zero_index != 1 && zero_index != 2){
                int[] temp_arr = new int[arr.length];
                for(int i = 0; i < arr.length; i++){
                    temp_arr[i] = arr[i];
                }
                //swap
                int temp = temp_arr[zero_index];
                temp_arr[zero_index] = temp_arr[zero_index-3];
                temp_arr[zero_index-3] = temp;
                next_states.add(new State(temp_arr));
            }
            
            //down
            if(zero_index != 6 && zero_index != 7 && zero_index != 8){
                int[] temp_arr = new int[arr.length];
                for(int i = 0; i < arr.length; i++){
                    temp_arr[i] = arr[i];
                }
                //swap
                int temp = temp_arr[zero_index];
                temp_arr[zero_index] = temp_arr[zero_index+3];
                temp_arr[zero_index+3] = temp;
                next_states.add(new State(temp_arr));
            }
            
            //right
            if(zero_index != 2 && zero_index != 5 && zero_index != 8){
                int[] temp_arr = new int[arr.length];
                for(int i = 0; i < arr.length; i++){
                    temp_arr[i] = arr[i];
                }
                //swap
                int temp = temp_arr[zero_index];
                temp_arr[zero_index] = temp_arr[zero_index+1];
                temp_arr[zero_index+1] = temp;
                next_states.add(new State(temp_arr));
            }
            
            //left
            if(zero_index != 0 && zero_index != 3 && zero_index != 6){
                int[] temp_arr = new int[arr.length];
                for(int i = 0; i < arr.length; i++){
                    temp_arr[i] = arr[i];
                }
                //swap
                int temp = temp_arr[zero_index];
                temp_arr[zero_index] = temp_arr[zero_index-1];
                temp_arr[zero_index-1] = temp;
                next_states.add(new State(temp_arr));
            }
            next_states.trimToSize();
        }


    public static void main(String[] args){
        int[] test = {1,2,3,4,5,6,7,0,8};
        State state = new State(test);
        state.generateStates(state.getState());

    }

}