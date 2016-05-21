package projects.mathtools.classes;

import java.util.ArrayList;
import java.util.List;

public class MT {
	
	
	///////MOD
	public static int mod ( int num, int mod ){
	        int rep = num % mod;
	        if (rep < 0)
	        	rep = rep +=mod;
	          return rep;
	     }
	    
	public static int mod_plus ( int num1,int num2, int mod ){
		return mod (num1+num2, mod);
	}

	public static int mod_rest ( int num1,int num2, int mod ){
		return mod (num1-num2, mod);
	}

	public static int mod_times ( int num1,int num2, int mod ){
			       return mod (num1*num2, mod);
			      }
			
	//////convertion from mc to Z12
	public static <T> T mc2Z12 (T midic) {
		//error
		return null;
	}
	
	public static int mc2Z12 (int midic) {
		return mod (midic/100, 12);
	}
	
	public static <T> List<T> mc2Z12(List <T> midic) {
		List<T> rep = new ArrayList<T>();
		for (T elem : midic) 
			rep.add(mc2Z12(elem));
		return rep;
	}
	
	public static String num2name (int n) {
		String rep = "";
		switch (n){
			case 0 : rep = "C"; break;
			case 1 : rep = "C#"; break;
			case 2 : rep = "D"; break;
			case 3 : rep = "Eb"; break;
			case 4 : rep = "E"; break;
			case 5 : rep = "F"; break;
			case 6 : rep = "F#"; break;
			case 7 : rep = "G"; break;
			case 8 : rep = "Ab"; break;
			case 9 : rep = "A"; break;
			case 10 : rep = "Bb"; break;
			case 11 : rep = "B"; break;
		}
		return rep;
	}
	
	
	
	////////////////////////////////////////////////
	public static <T> List<List<T>> generatePerm(List <T> original) {
	    if (original.size() == 0) {
	      List<List<T>> result = new ArrayList<List<T>>();
	      result.add(new ArrayList<T>());
	      return result;
	    }
	    T firstElement = original.remove(0);
	    List<List<T>> returnValue = new ArrayList<List<T>>();
	    List<List<T>> permutations = generatePerm(original);
	    for (List<T> smallerPermutated : permutations) {
	      for (int index=0; index <= smallerPermutated.size(); index++) {
	        List<T> temp = new ArrayList<T>(smallerPermutated);
	        temp.add(index, firstElement);
	        returnValue.add(temp);
	      }
	    }
	    return returnValue;
	  }

}
