package model;

public enum Major
{
	CS_AND_E_GENERAL ("Comp Sci & Engineering General"),
	COMPUTER_SCIENCE ("Computer Science"),
	COMPUTER_ENGINEERING("Computer Engineering"),
	ELECTRICAL_ENGINEERING("Electrical Engineering"),
	SOFTWARE_ENGINEERING("Software Engineering");

	private String xlsDescriptor;

	Major(String xlsDescriptor)
	{
		this.xlsDescriptor = xlsDescriptor;
	}
	public static Major findForXLS(String xlsValue)
	{
		for(Major m:Major.values())
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
