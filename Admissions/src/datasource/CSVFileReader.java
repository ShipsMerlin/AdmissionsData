package datasource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import model.Student;

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

	ArrayList<String> getColumns()
	{
		return columns;
	}

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
