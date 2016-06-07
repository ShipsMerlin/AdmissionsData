package datasource;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

import model.Student;

/**
 * Reads a CVS file that is a snapshot of admissions including demographic
 * information and latest decision information
 *
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
	 * @param fileTitle
	 *            the title of the CSV file
	 * @throws FileNotFoundException
	 *             if we can't open the file for reading
	 * @throws ClassNotFoundException
	 *             This will only happen if this class's internal mappings for
	 *             how to handle the various columns of the file are mis-coded.
	 */
	CSVFileReader(String fileTitle) throws FileNotFoundException, ClassNotFoundException
	{
		fileScanner = new Scanner(new File(fileTitle));
		buildMappings();

		readInColumnNames();
	}

	private void buildMappings() throws ClassNotFoundException
	{
		Class<?> studentClass = Class.forName("Student");
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
	 * The only reason this isn't private is for testing purposes
	 * @return a list of all of the column names in the file
	 */
	ArrayList<String> getColumns()
	{
		return columns;
	}

	/**
	 * @return the next student in the file
	 * @throws ClassNotFoundException Only if the mappings for how to handle each column are mis-coded
	 */
	public Student getNextStudent() throws ClassNotFoundException
	{
		if (fileScanner.hasNext())
		{
			Student s = new Student();
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
