package model.statemachine;

public class StateMachine
{

	private Transition[] machine =
	{ new Transition(StartState.class, AnyVerifier.class, AcceptedState.class, CreateRecord.class) };

	private class Transition
	{

		public Transition(Class<?> currentState, Class<? extends ConditionVerifier> condition, Class<?> nextState,
				Class<? extends Action> action)
		{
			super();
			this.currentState = currentState;
			this.nextState = nextState;
			this.condition = condition;
			this.action = action;
		}

		Class<?> currentState;
		Class<?> nextState;
		Class<? extends ConditionVerifier> condition;
		Class<? extends Action> action;
	}
}
