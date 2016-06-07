package model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Contains all of the information about a single student
 * @author Merlin
 *
 */
public class Student
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
	 * @return which admissions population this student belongs to
	 * @see model.AdmissionsPopulation
	 */
	public AdmissionsPopulation getAdmissionsPopulation()
	{
		return admissionsPopulation;
	}

	/**
	 * @return which concentration this student is enrolled in.
	 * @see model.Concentration
	 */
	public Concentration getConcentration()
	{
		return concentration;
	}

	/**
	 * @return the latest admission decision this student has made
	 * @see model.Decision
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
	 * @return the student's gender ("M" or "F");
	 */
	public String getGender()
	{
		return gender;
	}

	/**
	 * @return the student's SU ID
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
	 * @return the date of the student's most recent admissions decision
	 */
	public GregorianCalendar getLatestDecisionDate()
	{
		return latestDecisionDate;
	}

	/**
	 * @return the student's major
	 * @see model.Major
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
	 * @return the email address we should use to communicate with this student
	 */
	public String getPreferredEmailAddress()
	{
		return preferredEmailAddress;
	}

	/**
	 * @return the student's Mathematics SAT score
	 */
	public int getSATMath()
	{
		return SATMath;
	}

	/**
	 * @return the studen't Verbal SAT score
	 */
	public int getSATVerbal()
	{
		return SATVerbal;
	}

	public void setAdmissionsPopulation(String population)
	{
		this.admissionsPopulation = AdmissionsPopulation.valueOf(population.toUpperCase());
	}

	public void setConcentration(String concentration)
	{
		this.concentration = Concentration.findForXLS(concentration);
	}

	public void setLatestDecision(String decision)
	{
		this.latestDecision = Decision.findForXLS(decision);
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public void setLatestDecisionDate(String latestDecisionDate)
	{
		SimpleDateFormat formatter=new SimpleDateFormat("MMM d, yyyy HH:mm:ss a");//EEE MMM d HH:mm:ss zzz yyyy");
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


	public void setMajor(String major)
	{
		this.major = Major.findForXLS(major);
	}

	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	public void setPreferredEmailAddress(String preferredEmailAddress)
	{
		this.preferredEmailAddress = preferredEmailAddress;
	}

	public void setSATMath(String sATMath)
	{
		SATMath = Integer.parseInt(sATMath);
	}

	public void setSATVerbal(String sATVerbal)
	{
		SATVerbal = Integer.parseInt(sATVerbal);
	}

	public void setStudentID(String id)
	{
		ID = Integer.parseInt(id);
	}

}
