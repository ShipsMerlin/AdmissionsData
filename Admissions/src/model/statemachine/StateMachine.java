package model.statemachine;

/**
 * This encapsulates the milestones and actions that are required by the acceptance process
 * @author Merlin
 *
 */
public class StateMachine
{

	private Transition[] machine =
	{ new Transition(StartState.class, AnyVerifier.class, AcceptedState.class, CreateRecord.class) };

	private class Transition
	{

		public Transition(Class<? extends AdmissionState> currentState, Class<? extends ConditionVerifier> condition, Class<? extends AdmissionState> nextState,
				Class<? extends Action> action)
		{
			super();
			this.currentState = currentState;
			this.nextState = nextState;
			this.condition = condition;
			this.action = action;
		}

		private Class<? extends AdmissionState> currentState;
		private Class<? extends AdmissionState> nextState;
		private Class<? extends ConditionVerifier> condition;
		private Class<? extends Action> action;
	}
}
