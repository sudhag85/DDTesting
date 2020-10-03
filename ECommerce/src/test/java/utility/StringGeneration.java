package utility;

import org.apache.commons.lang3.RandomStringUtils;

public class StringGeneration {

	public static String generateString()
	{
		RandomStringUtils genString = new RandomStringUtils();
		String alpha = genString.randomAlphanumeric(5);
		return alpha;
	}

}
