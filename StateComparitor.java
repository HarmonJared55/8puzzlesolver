import java.util.Comparator;

class StateComparitor implements Comparator<State>{

        @Override
        public int compare(State a, State b){
            if(a.numOutOfOrder(a.getState()) > b.numOutOfOrder(b.getState())){
                return 1;
            }else if(a.numOutOfOrder(a.getState()) < b.numOutOfOrder(b.getState())){
                return -1;
            }
            return 0;
        }

}