package inputdatasource;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import inputdatasource.AdmissionsPopulation;
import inputdatasource.CSVFileReader;
import inputdatasource.Concentration;
import inputdatasource.Decision;
import inputdatasource.Major;
import inputdatasource.StudentDataRecord;

public class CSVFileReaderTest
{

	@Test
	public void getsColumns() throws FileNotFoundException, ClassNotFoundException
	{
		CSVFileReader reader = new CSVFileReader("Applicant Detail Report.csv");
		ArrayList<String> columnNames = reader.getColumns();
		assertEquals(47, columnNames.size());
		assertEquals("HISPANIC_LATINO_ETHNICITY_IND", columnNames.get(29));
		assertEquals("ID", columnNames.get(0));
	}

	@Test
	public void getsFirstStudent() throws FileNotFoundException, ClassNotFoundException
	{
		CSVFileReader reader = new CSVFileReader("Applicant Detail Report.csv");
		StudentDataRecord s = reader.getNextStudent();
		assertEquals(600284662, s.getID());
		assertEquals("Marko", s.getFirstName());
		assertEquals("Abdo", s.getLastName());
		assertEquals("mid", s.getMiddleName());
		assertEquals("ma7248@ship.edu", s.getPreferredEmailAddress());
		assertEquals(32, s.getSATVerbal());
		assertEquals(42, s.getSATMath());
		assertEquals(AdmissionsPopulation.TRANSFER, s.getAdmissionsPopulation());
		assertEquals(Major.COMPUTER_ENGINEERING, s.getMajor());
		assertEquals(Concentration.NONE, s.getConcentration());
		assertEquals(Decision.CONFIRMED, s.getDecision());
		GregorianCalendar latestDecisionDate = s.getLatestDecisionDate();
		assertEquals(2016, latestDecisionDate.get(Calendar.YEAR));
		assertEquals(1, latestDecisionDate.get(Calendar.MONTH));
		assertEquals(3, latestDecisionDate.get(Calendar.DAY_OF_MONTH));
		assertEquals("M", s.getGender());

	}

	@Test
	public void getsSecondStudent() throws FileNotFoundException, ClassNotFoundException
	{
		CSVFileReader reader = new CSVFileReader("Applicant Detail Report.csv");
		reader.getNextStudent();
		StudentDataRecord s = reader.getNextStudent();
		assertEquals(600281374, s.getID());
		assertEquals("Terrell", s.getFirstName());
		assertEquals("Acosta", s.getLastName());
		assertEquals("Camera", s.getMiddleName());
		assertEquals("t.acosta822@gmail.com", s.getPreferredEmailAddress());
		assertEquals(590, s.getSATVerbal());
		assertEquals(580, s.getSATMath());
		assertEquals(AdmissionsPopulation.FRESHMEN, s.getAdmissionsPopulation());
		assertEquals(Major.COMPUTER_SCIENCE, s.getMajor());
		assertEquals(Concentration.GRAPHICS, s.getConcentration());
		assertEquals(Decision.ACCEPTED, s.getDecision());
		GregorianCalendar latestDecisionDate = s.getLatestDecisionDate();
		assertEquals(2015, latestDecisionDate.get(Calendar.YEAR));
		assertEquals(11, latestDecisionDate.get(Calendar.MONTH));
		assertEquals(22, latestDecisionDate.get(Calendar.DAY_OF_MONTH));
		assertEquals("F", s.getGender());

	}

	@Test
	public void nullStudentWhenFinished() throws FileNotFoundException, ClassNotFoundException
	{
		CSVFileReader reader = new CSVFileReader("Applicant Detail Report.csv");
		reader.getNextStudent();
		reader.getNextStudent();
		assertNull(reader.getNextStudent());
	}
}
