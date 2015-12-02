/**
 * CMPS130 - Homework assignment 9
 *
 * Author: Tyler Tagawa
 */

import java.util.LinkedList;
import java.util.List;
import static java.util.Arrays.asList;

public class TM {

	// Turing machine definition (States, Symbols, Transitions, INITIAL, ACCEPTING)

	private enum State { Q0, Q1, Q2, Q3, Q4, Q5 }

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

		new Transition(State.Q1, Symbol.EMPTY, State.Q1, Symbol.EMPTY, Direction.Right),
		new Transition(State.Q1, Symbol.A, State.Q2, Symbol.EMPTY, Direction.Right),
		new Transition(State.Q1, Symbol.EOS, State.Q5, Symbol.EOS, Direction.Right),

		new Transition(State.Q2, Symbol.A, State.Q2, Symbol.A, Direction.Right),
		new Transition(State.Q2, Symbol.EMPTY, State.Q2, Symbol.EMPTY, Direction.Right),
		new Transition(State.Q2, Symbol.B, State.Q3, Symbol.EMPTY, Direction.Right),

		new Transition(State.Q3, Symbol.B, State.Q3, Symbol.B, Direction.Right),
		new Transition(State.Q3, Symbol.EMPTY, State.Q3, Symbol.EMPTY, Direction.Right),
		new Transition(State.Q3, Symbol.C, State.Q4, Symbol.EMPTY, Direction.Left),

		new Transition(State.Q4, Symbol.A, State.Q4, Symbol.A, Direction.Left),
		new Transition(State.Q4, Symbol.B, State.Q4, Symbol.B, Direction.Left),
		new Transition(State.Q4, Symbol.C, State.Q4, Symbol.C, Direction.Left),
		new Transition(State.Q4, Symbol.EMPTY, State.Q4, Symbol.EMPTY, Direction.Left),
		new Transition(State.Q4, Symbol.EOS, State.Q1, Symbol.EOS, Direction.Right)
	};

	private static final State INITIAL = State.Q0;

	private static final State[] ACCEPTING = { State.Q5 };

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

	// Turing Machine simulator

	public static boolean simulate(List<Symbol> input) {
		/**
		 * Copy input to tape and simulate Turing Machine.
		 * If at some point no matching transitions can be found,
		 * stop the machine and return whether accepting or not.
		 */
		return true;
	}
}
