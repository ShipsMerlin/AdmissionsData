package model;

public enum Concentration
{
	NONE(""),
	GRAPHICS("Graphics"),
	EMBEDDED("Embedded Systems"),
	RELATED_DISCIPLINE("Related Discipline");


	private String xlsDescriptor;

	Concentration(String xlsDescriptor)
	{
		this.xlsDescriptor = xlsDescriptor;
	}
	public static Concentration findForXLS(String xlsValue)
	{
		for(Concentration m:Concentration.values())
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
