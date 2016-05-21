package kernel.tools;
import java.util.ArrayList;



public class OP {
	
	public static boolean IsPowerOfTwo(int x)
	{
	    return (x & (x - 1)) == 0;
	}

	public static long gcd(long a, long b)
	{
	    while (b > 0)
	    {
	        long temp = b;
	        b = a % b; // % is remainder
	        a = temp;
	    }
	    return a;
	}
	
	public static long lcm(long a, long b)
	{
	    return a * (b / gcd(a, b));
	}
	
/*	public static ArrayList<I_SimpleContainer> nthcdr(ArrayList<I_SimpleContainer> list, int n){
		
		ArrayList<I_SimpleContainer> retour = (ArrayList<I_SimpleContainer>) list.clone();
		
		for(int i = 0; i < n ; i++){
			retour.remove(0);
		}
		
		return retour;
	}
	*/

	public static long gcd(ArrayList<Integer> offsets) {
		long result = offsets.get(0);
	    for(int i = 1; i < offsets.size(); i++) 
	    	result = gcd(result, offsets.get(i));
	    return result;
	}

}
