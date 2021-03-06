package it.fe.cassano.yeap.visitors;


public enum ExpType {
	InvalidExp(Void.class),
	NumExp(Integer.class),
	RealExp(Double.class),
	;

	private final Class clazz;

	ExpType(final Class clazz)
	{
		this.clazz = clazz;
	}
	public static ExpType evalReturnType(ExpType lType, ExpType rType) 
	{
		final ExpType retVal;
		if (lType != rType)
		{
			retVal = RealExp;
		}
		else if (lType == ExpType.NumExp)
		{
			retVal = NumExp;
		}
		else
		{
			retVal = RealExp;
		}
		return retVal;
	}

	public static ExpType valueof(String image) {
		switch(image.toLowerCase())
		{
		case "numexp":
			return NumExp;
		case "realexp":	
			return RealExp;
		default:
			return InvalidExp;
		}

	}

	public Class classType() {
		return this.clazz;
	}
	

}
