package model.statemachine;

import inputdatasource.StudentDataRecord;

/**
 * Defines an action that can occur on a state transition
 * @author Merlin
 *
 */
public interface Action
{

	/**
	 * This method encapsulates the action
	 * @param newStudentData  the latest information about this student
	 */
	public void execute(StudentDataRecord newStudentData);
}
