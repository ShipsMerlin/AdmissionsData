package inputdatasource;

/**
 * The milestones in the acceptance process that we are tracking in this system and maps them to the text in the report from banner
 * @author Merlin
 *
 */
public enum Decision
{
	/**
	 *
	 */
	ACCEPTED("Accepted"),
	/**
	 *
	 */
	CONFIRMED("Deposit Paid/Confirmed");

	private String xlsDescriptor;

	Decision(String xlsDescriptor)
	{
		this.xlsDescriptor = xlsDescriptor;
	}

	/**
	 * gives the enum value for a given banner string
	 * @param xlsValue the string in the banner report
	 * @return the matching enum value
	 */
	public static Decision findForXLS(String xlsValue)
	{
		for (Decision m : Decision.values())
		{
			if (m.getXlsDescriptor().equals(xlsValue))
			{
				return m;
			}
		}
		return null;
	}

	private String getXlsDescriptor()
	{
		return xlsDescriptor;
	}
}
