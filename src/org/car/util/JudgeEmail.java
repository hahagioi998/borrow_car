package org.car.util;

public class JudgeEmail {
    public static boolean Judge(String email) {
    	String regex="\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
    	if(email.matches(regex)) {
    		return true;
    	}else {
    		return false;
    	}
    }
}
