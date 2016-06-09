package inputdatasource;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads a CVS file (currently generated from banner) to create a sequence of StudentRecords representing the values in that file.  Create
 * an object for the file.  Then the getNextStudent method can be called iteratively to see all of the students in that file.
 * @author Merlin
 *
 */
public class CSVFileReader
{

	private ArrayList<ColumnToSetter> mappings;

	private class ColumnToSetter
	{
		String column;
		Method setter;

		public ColumnToSetter(String column, String setterMethodName, Method[] methods)
		{
			super();
			this.column = column;
			this.setter = getMethod(setterMethodName, methods);
		}

		private Method getMethod(String methodName, Method[] methods)
		{
			for (Method m : methods)
			{
				if (m.getName().equals(methodName))
				{
					return m;
				}
			}
			return null;
		}
	}

	private ArrayList<String> columns;
	private Scanner fileScanner;

	/**
	 * Create a reader for a given file
	 * @param fileTitle the file system's name for the file (may or may not include the path)
	 * @throws FileNotFoundException if there is no file with that name
	 * @throws ClassNotFoundException this exception reflects in internal coding problem in the mappings in this file
	 */
	CSVFileReader(String fileTitle) throws FileNotFoundException, ClassNotFoundException
	{
		fileScanner = new Scanner(new File(fileTitle));
		buildMappings();

		readInColumnNames();
	}

	private void buildMappings() throws ClassNotFoundException
	{
		Class<?> studentClass = Class.forName("inputdatasource.StudentDataRecord");
		Method[] methods = studentClass.getMethods();
		mappings = new ArrayList<ColumnToSetter>();
		mappings.add(new ColumnToSetter("ID", "setStudentID", methods));
		mappings.add(new ColumnToSetter("FIRST_NAME", "setFirstName", methods));
		mappings.add(new ColumnToSetter("LAST_NAME", "setLastName", methods));
		mappings.add(new ColumnToSetter("MIDDLE_NAME", "setMiddleName", methods));
		mappings.add(new ColumnToSetter("EMAIL_PREFERRED_ADDRESS", "setPreferredEmailAddress", methods));
		mappings.add(new ColumnToSetter("SAT Verbal", "setSATVerbal", methods));
		mappings.add(new ColumnToSetter("SAT Math", "setSATMath", methods));
		mappings.add(new ColumnToSetter("ADMISSIONS_POPULATION_DESC", "setAdmissionsPopulation", methods));
		mappings.add(new ColumnToSetter("MAJOR_DESC", "setMajor", methods));
		mappings.add(new ColumnToSetter("FIRST_CONCENTRATION_DESC", "setConcentration", methods));
		mappings.add(new ColumnToSetter("LATEST_DECISION_DESC", "setLatestDecision", methods));
		mappings.add(new ColumnToSetter("LATEST_DECISION_DATE", "setLatestDecisionDate", methods));
		mappings.add(new ColumnToSetter("GENDER", "setGender", methods));
	}

	private void readInColumnNames()
	{
		fileScanner.reset();
		String firstList = fileScanner.nextLine();
		// split on a comma only if it has an even number of quotes before it
		String[] splitRow = firstList.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
		columns = new ArrayList<String>();
		for (String s : splitRow)
		{
			columns.add(s);
		}
	}

	/**
	 * @return a list of the names of the columns (only visible for testing purposes)
	 */
	ArrayList<String> getColumns()
	{
		return columns;
	}

	/**
	 * @return a record holding the information for the next student in the file.
	 * @throws ClassNotFoundException this exception reflects in internal coding problem in the mappings in this file
	 */
	public StudentDataRecord getNextStudent() throws ClassNotFoundException
	{
		if (fileScanner.hasNext())
		{
			StudentDataRecord s = new StudentDataRecord();
			String line = fileScanner.nextLine();
			String[] splitRow = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			for (int i = 0; i < splitRow.length; i++)
			{
				splitRow[i] = splitRow[i].replace("\"", "");
			}
			for (ColumnToSetter mapping : mappings)
			{
				int position = columns.indexOf(mapping.column);

				try
				{
					mapping.setter.invoke(s, splitRow[position]);

				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
				{
					System.out.println("Couldn't invoke a setter in Student: " + mapping.setter + " passing "
							+ splitRow[position]);
				}
			}
			return s;
		} else
		{
			return null;
		}
	}

}
