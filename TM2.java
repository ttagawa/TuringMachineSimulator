/**
 * CMPS130 - Homework assignment 9
 *
 * Author: Tyler Tagawa
 */

import java.util.LinkedList;
import java.util.List;
import static java.util.Arrays.asList;

public class TM2 {

	// Turing machine definition (States, Symbols, Transitions, INITIAL, ACCEPTING)

	private enum State { Q0, Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9 }

	private enum Symbol { A, B, C, EMPTY, EOS }

	private enum Direction { Left, Right }

	private static class Transition {
		public State fromState;
		public Symbol withSymbol;
		public State toState;
		public Symbol writeSymbol;
		public Direction direction;

		public Transition(State fromState, Symbol withSymbol, State toState, Symbol writeSymbol, Direction direction) {
			this.fromState = fromState;
			this.withSymbol = withSymbol;
			this.toState = toState;
			this.writeSymbol = writeSymbol;
			this.direction = direction;
		}
	}

	private static Transition[] TRANSITIONS = {
		new Transition(State.Q0, Symbol.EOS, State.Q1, Symbol.EOS, Direction.Right),

		new Transition(State.Q1, Symbol.A, State.Q2, Symbol.A, Direction.Right),
		new Transition(State.Q1, Symbol.EOS, State.Q9, Symbol.EOS, Direction.Right),

		new Transition(State.Q2, Symbol.A, State.Q2, Symbol.A, Direction.Right),
		new Transition(State.Q2, Symbol.B, State.Q3, Symbol.B, Direction.Right),
		new Transition(State.Q2, Symbol.C, State.Q4, Symbol.C, Direction.Right),

		new Transition(State.Q3, Symbol.B, State.Q3, Symbol.B, Direction.Right),
		new Transition(State.Q3, Symbol.EOS, State.Q5, Symbol.EOS, Direction.Left),
		new Transition(State.Q3, Symbol.C, State.Q4, Symbol.C, Direction.Right),

		new Transition(State.Q4, Symbol.C, State.Q4, Symbol.C, Direction.Right),
		new Transition(State.Q4, Symbol.EOS, State.Q5, Symbol.EOS, Direction.Left),

		new Transition(State.Q5, Symbol.C, State.Q5, Symbol.C, Direction.Left),
		new Transition(State.Q5, Symbol.B, State.Q5, Symbol.B, Direction.Left),
		new Transition(State.Q5, Symbol.A, State.Q5, Symbol.A, Direction.Left),
		new Transition(State.Q5, Symbol.EOS, State.Q6, Symbol.EOS, Direction.Right),

		new Transition(State.Q6, Symbol.EMPTY, State.Q6, Symbol.EMPTY, Direction.Right),
		new Transition(State.Q6, Symbol.A, State.Q7, Symbol.EMPTY, Direction.Right),
		new Transition(State.Q6, Symbol.EOS, State.Q9, Symbol.EOS, Direction.Right),

		new Transition(State.Q7, Symbol.EMPTY, State.Q7, Symbol.EMPTY, Direction.Right),
		new Transition(State.Q7, Symbol.A, State.Q7, Symbol.A, Direction.Right),
		new Transition(State.Q7, Symbol.C, State.Q8, Symbol.EMPTY, Direction.Left),
		new Transition(State.Q7, Symbol.B, State.Q8, Symbol.EMPTY, Direction.Left),

		new Transition(State.Q8, Symbol.EMPTY, State.Q8, Symbol.EMPTY, Direction.Left),
		new Transition(State.Q8, Symbol.A, State.Q8, Symbol.A, Direction.Left),
		new Transition(State.Q8, Symbol.EOS, State.Q6, Symbol.EOS, Direction.Right)
	};

	private static final State INITIAL = State.Q0;

	private static final State[] ACCEPTING = { State.Q9 };

	// Command-line interface

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java TM [INPUT]");
			return;
		}
		List<Symbol> input = new LinkedList<>();
		input.add(Symbol.EOS);
		for (char c : args[0].toCharArray()) {
			if (c == 'a') input.add(Symbol.A);
			if (c == 'b') input.add(Symbol.B);
			if (c == 'c') input.add(Symbol.C);
		}
		input.add(Symbol.EOS);
		if (simulate(input)) {
			System.out.printf("Accepted input: %s\n", args[0]);
		} else {
			System.out.printf("Rejected input: %s\n", args[0]);
		}
	}

public static boolean acceptState(State curr,State[] Accept){
	for(int i = 0;i<Accept.length;i++){
		if(Accept[i]==curr)
			return true;
	}
	return false;
}

	// Turing Machine simulator

	public static boolean simulate(List<Symbol> input) {
		/**
		 * Copy input to tape and simulate Turing Machine.
		 * If at some point no matching transitions can be found,
		 * stop the machine and return whether accepting or not.
		 */
		 int index = 0;
		 State curr = INITIAL;
		 Symbol c = input.get(index);
		 for(;;){
			 boolean found = false;
		 	for(int i = 0;i<TRANSITIONS.length;i++){
				c=input.get(index);
			 	if(TRANSITIONS[i].fromState==curr && TRANSITIONS[i].withSymbol==c){
					found = true;
					curr = TRANSITIONS[i].toState;
				 	input.set(index,TRANSITIONS[i].writeSymbol);
				 	if(acceptState(curr,ACCEPTING)){
					 	return true;
				 	}
				 	if(TRANSITIONS[i].direction==Direction.Right){
					 	index++;
				 	}else{
					 	index--;
				 	}
			 	}
		 	}
			if(found==false){
				return false;
			}
	 	}
	}
}
