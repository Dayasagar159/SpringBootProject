package com.indus.app2.programs;

public class RemoveSpecialCharacters {

	public static void main(String[] args) {
		
		String str = "!W@eb#s$pa%rr*,o:w";

        String finalStr = str.replaceAll("[^0-9a-zA-Z]", "");

        System.out.println("Original string: " + str);
        System.out.println("Final string: " + finalStr);
	}

}
