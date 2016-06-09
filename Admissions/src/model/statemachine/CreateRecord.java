package model.statemachine;

import inputdatasource.StudentDataRecord;

/**
 * An action that creates a new record in the database
 * @author Merlin
 *
 */
public class CreateRecord implements Action
{

	/**
	 * Create a record for a newly admitted student
	 * @see model.statemachine.Action#execute(StudentDataRecord)
	 */
	@Override
	public void execute(StudentDataRecord newStudentData)
	{
		// TODO Auto-generated method stub

	}

}
