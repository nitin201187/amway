package org.amway.notification.engine.sns;

public class SnsStringUtil
{

	public static boolean isEmpty(final String s)
	{
		if (s == null)
		{
			return true;
		}

		if (s.length() < 1)
		{
			return true;
		}

		return false;
	}

	public static boolean isBlank(final String s)
	{
		if (isEmpty(s))
		{
			return true;
		}

		if (isEmpty(s.trim()))
		{
			return true;
		}

		return false;
	}
}
