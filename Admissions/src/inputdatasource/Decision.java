package inputdatasource;

public enum Decision
{
	ACCEPTED("Accepted"), CONFIRMED("Deposit Paid/Confirmed");

	private String xlsDescriptor;

	Decision(String xlsDescriptor)
	{
		this.xlsDescriptor = xlsDescriptor;
	}

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
