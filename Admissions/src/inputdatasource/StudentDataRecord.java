package inputdatasource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * One record that holds all of the information in the banner report for one student
 * @author Merlin
 *
 */
public class StudentDataRecord
{
	private int ID;
	private String firstName;
	private String lastName;
	private String middleName;
	private String preferredEmailAddress;
	private int SATVerbal;
	private int SATMath;
	private Major major;
	private Concentration concentration;
	private GregorianCalendar latestDecisionDate;
	private Decision latestDecision;
	private AdmissionsPopulation admissionsPopulation;
	private String gender;

	/**
	 * @return the type of applicant
	 */
	public AdmissionsPopulation getAdmissionsPopulation()
	{
		return admissionsPopulation;
	}

	/**
	 * @return the concentration this student requested
	 */
	public Concentration getConcentration()
	{
		return concentration;
	}

	/**
	 * @return the most recent milestone in the acceptance process
	 */
	public Decision getDecision()
	{
		return latestDecision;
	}

	/**
	 * @return the student's first name
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @return M or F
	 */
	public String getGender()
	{
		return gender;
	}

	/**
	 * @return SUID
	 */
	public int getID()
	{
		return ID;
	}

	/**
	 * @return the student's last name
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @return the date on which the most recent milestone in the acceptance process occurred
	 */
	public GregorianCalendar getLatestDecisionDate()
	{
		return latestDecisionDate;
	}

	/**
	 * @return the major they are in
	 */
	public Major getMajor()
	{
		return major;
	}

	/**
	 * @return the student's middle name
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @return the email address we should use to contact them
	 */
	public String getPreferredEmailAddress()
	{
		return preferredEmailAddress;
	}

	/**
	 * @return their SAT math score
	 */
	public int getSATMath()
	{
		return SATMath;
	}

	/**
	 * @return their SAT Verbal score
	 */
	public int getSATVerbal()
	{
		return SATVerbal;
	}

	/**
	 * @param population the type of application
	 * @See inputdatasource.AdmissionsPopulation
	 */
	public void setAdmissionsPopulation(String population)
	{
		this.admissionsPopulation = AdmissionsPopulation.valueOf(population.toUpperCase());
	}

	/**
	 * @param concentration the concentration they are hoping for
	 * @see inputdatasource.AdmissionsPopulation
	 */
	public void setConcentration(String concentration)
	{
		this.concentration = Concentration.findForXLS(concentration);
	}

	/**
	 * @param decision the latest milestone the student has reached
	 * @see inputdatasource.Decision
	 */
	public void setLatestDecision(String decision)
	{
		this.latestDecision = Decision.findForXLS(decision);
	}

	/**
	 * @param firstName the student's first name
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @param gender "M" or "F"
	 */
	public void setGender(String gender)
	{
		this.gender = gender;
	}

	/**
	 * @param lastName the student's last name
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @param latestDecisionDate the date of the most recent milestone they have achieved.  Format is "MMM d, yyyy HH:mm:ss a"
	 */
	public void setLatestDecisionDate(String latestDecisionDate)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy HH:mm:ss a");// EEE
																					// MMM
																					// d
																					// HH:mm:ss
																					// zzz
																					// yyyy");
		try
		{
			Date x = formatter.parse(latestDecisionDate);
			this.latestDecisionDate = new GregorianCalendar();
			this.latestDecisionDate.setTime(x);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @param major the major that they are admitted into
	 * @see inputdatasource.Major
	 */
	public void setMajor(String major)
	{
		this.major = Major.findForXLS(major);
	}

	/**
	 * @param middleName the studen't middle name
	 */
	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	/**
	 * @param preferredEmailAddress the email address we should use to contact them
	 */
	public void setPreferredEmailAddress(String preferredEmailAddress)
	{
		this.preferredEmailAddress = preferredEmailAddress;
	}

	/**
	 * @param sATMath The student's SAT Math score
	 */
	public void setSATMath(String sATMath)
	{
		SATMath = Integer.parseInt(sATMath);
	}

	/**
	 * @param sATVerbal The student's SAT Verbal score
	 */
	public void setSATVerbal(String sATVerbal)
	{
		SATVerbal = Integer.parseInt(sATVerbal);
	}

	/**
	 * @param id the student's SUID
	 */
	public void setStudentID(String id)
	{
		ID = Integer.parseInt(id);
	}

}
