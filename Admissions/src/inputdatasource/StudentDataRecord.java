package inputdatasource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

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

	public AdmissionsPopulation getAdmissionsPopulation()
	{
		return admissionsPopulation;
	}

	public Concentration getConcentration()
	{
		return concentration;
	}

	public Decision getDecision()
	{
		return latestDecision;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getGender()
	{
		return gender;
	}

	public int getID()
	{
		return ID;
	}

	public String getLastName()
	{
		return lastName;
	}

	public GregorianCalendar getLatestDecisionDate()
	{
		return latestDecisionDate;
	}

	public Major getMajor()
	{
		return major;
	}

	public String getMiddleName()
	{
		return middleName;
	}

	public String getPreferredEmailAddress()
	{
		return preferredEmailAddress;
	}

	public int getSATMath()
	{
		return SATMath;
	}

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
