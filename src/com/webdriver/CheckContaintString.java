package com.webdriver;

import java.util.Random;

public class CheckContaintString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String a="Automation Testing"+"\n"+"automation@gmail.com"+"\n"+"Change Password";
//		System.out.println("Chuỗi hiện tại như sau: "+a);
//		System.out.println("Chuỗi có chứa Automation Testing có hay không: "+a.contains("Automation Testing"));
//		String generatedString = RandomStringUtils.randomAlphabetic(10);
		int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	 
	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	 
	    System.out.println(generatedString);
	}

}
