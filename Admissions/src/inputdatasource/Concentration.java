package inputdatasource;

/**
 * The possible concentrations a potential student can request.  This enum matches those concentrations with the text in the DB report from banner
 * @author Merlin
 *
 */
public enum Concentration
{
	/**
	 *
	 */
	NONE(""),
	/**
	 *
	 */
	GRAPHICS("Graphics"),
	/**
	 *
	 */
	EMBEDDED("Embedded Systems"),
	/**
	 *
	 */
	RELATED_DISCIPLINE("Related Discipline");

	private String xlsDescriptor;

	Concentration(String xlsDescriptor)
	{
		this.xlsDescriptor = xlsDescriptor;
	}

	/**
	 * Given a string from the db, return the matching enum value
	 * @param xlsValue the string from the db (see the values in the enum declaration to see what string we are expecting)
	 * @return the appropriate value of this enum
	 */
	public static Concentration findForXLS(String xlsValue)
	{
		for (Concentration m : Concentration.values())
		{
			if (m.getXlsDescriptor().equals(xlsValue))
			{
				return m;
			}
		}
		return Concentration.NONE;
	}

	private String getXlsDescriptor()
	{
		return xlsDescriptor;
	}
}
