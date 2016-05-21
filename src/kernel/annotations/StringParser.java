package kernel.annotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import kernel.tools.Fraction;

public class StringParser {
	
	public static Object [] getInputsDef (String str, Constructor met) {
		Object [] rep;
		String [] inputs = str.split(",");
		int len = inputs.length;

		Class<?>[] types  = met.getParameterTypes();
		Type[] gpType = met.getGenericParameterTypes();
		
		System.out.println ("les def " + str);
		rep = new Object[types.length];
		for (int i = 0; i< types.length; i++) {
			Class<?> theclass = types[i];
			if (theclass == long.class){
				if (i < len)
					rep[i] = getlong(inputs[i]);
				else
					rep[i] = 0;
			}
			else if (theclass == Fraction.class){
				if (i < len)
					rep[i] = getFraction(inputs[i]);
				else
					rep[i] = new Fraction(1);
			}
			else if (theclass == int.class){
				if (i < len)
					rep[i] = getint(inputs[i]);
				else
					rep[i] = 0;
			}
			else if (theclass == double.class){
				if (i < len)
					rep[i] = getdouble(inputs[i]);
				else
					rep[i] = 0;
			}
			else if (theclass == List.class) {
				if (i < len) {
					ParameterizedType pType = (ParameterizedType)  gpType[i];
					List<Type> typelist = new ArrayList<Type>();
					getTypeList(pType,typelist);
					rep[i] = getList(inputs[i], typelist.get(typelist.size()-1), typelist.subList(1, typelist.size()-1));
				}
				else
					rep[i] = "null";
			}
			else 
				rep[i] = "null";
		}
		return rep;
	}
	
	public static Class<?> getFinalClass (ParameterizedType clazz) {
		Type [] types = clazz.getActualTypeArguments();
		if (types[0] instanceof ParameterizedType) 
			return getFinalClass ((ParameterizedType) types[0]);
		else
			return (Class<?>) types[0];
		}
	
	public static int getProfondeur (ParameterizedType clazz, int n) {
		Type [] types = clazz.getActualTypeArguments();
		if (types[0] instanceof ParameterizedType) 
			return getProfondeur ((ParameterizedType) types[0], n+1);
		else
			return n;
		}
	
	public static void getTypeList (ParameterizedType clazz, List<Type> rep) {
		rep.add (clazz);
		Type [] types = clazz.getActualTypeArguments();
		if (types[0] instanceof ParameterizedType) 
			getTypeList ((ParameterizedType) types[0], rep);
		else 
			rep.add (types[0]);
		}

	public static void addToList (List<Long> list, Long elem) {
		list.add(elem);
	}
	public static void addToList (List<Fraction> list, Fraction elem) {
		list.add(elem);
	}
	public static void addToList (List<Integer> list, int elem) {
		list.add(elem);
	}
	
	public static void addToList (List list, Object elem) {
		list.add(elem);
	}
		
	
	public static <T> List<T> getList (String str, T clazz, List<Type> rest) {
		System.out.println("list " + str + " " + clazz + " " + rest);
		List<T> rep = new ArrayList<T>();
		String str1 = str.replace(" ", "");
		int len = str1.length();
		if (str1.charAt(0) != '(' || str1.charAt(len-1) != ')')
			return rep;
		str1 = str1.substring(1, len-1);
		String [] strlist = str1.split(",");
		len = strlist.length;
		if (rest.size() == 0) {
			try {
				if (clazz == Long.class)
					for (int i=0; i < len; i++)
						addToList((List<Long>) rep,getlong(strlist[i]));
				else if (clazz == Fraction.class)
					for (int i=0; i < len; i++)
						addToList((List<Fraction>) rep,getFraction(strlist[i]));
				else if (clazz == Integer.class)
					for (int i=0; i < len; i++)
						addToList((List<Integer>) rep,getint(strlist[i]));
			} catch (NumberFormatException e) {
				return rep;
			}
		}
		else {
				for (int i=0; i < len; i++)
					addToList(rep,getList(strlist[i], clazz,  rest.subList(1, rest.size())));
			}
			return rep;
	}
		
	
		
	public static int getint (String str) {
		int rep = 0;
		try {
		      rep = Integer.parseInt(str.replace(" ", ""));
		} catch (NumberFormatException e) {
		      return 0;
		}
		return rep;
	}
	
	public static double getdouble (String str) {
		double rep = 0;
		try {
		      rep = Double.parseDouble(str.replace(" ", ""));
		} catch (NumberFormatException e) {
		      return 0;
		}
		return rep;
	}
	
	public static long getlong (String str) {
		long rep = 0;
		try {
		      rep = Long.parseLong(str.replace(" ", ""));
		} catch (NumberFormatException e) {
		      return 0;
		}
		return rep;
	}
	
	public static Fraction getFraction(String str){
		String delims = "/";
		String[] tokens = str.split(delims);
		if (tokens.length == 1){
			try {
				long num = Long.parseLong(tokens[0]);
				return new Fraction(num,1);
		      } catch (NumberFormatException nfe) {
		         return new Fraction(1);
		      }
		} else if (tokens.length != 2) return null;
			else {
				try {
					long num = Long.parseLong(tokens[0].replace(" ", ""));
					long den = Long.parseLong(tokens[1].replace(" ", ""));
					return new Fraction(num,den);
				} catch (NumberFormatException nfe) {
					return new Fraction(1);
				}
			}
		}
	
	public static Object str2Object (String str, Class<?> clazz, Type gtype) {

		System.out.println("entro " + str + " " + clazz + " " + clazz.getClass() );
		Object rep = null;
		if (clazz == long.class)
			rep = getlong(str);
		else if (clazz == Fraction.class)
			rep = getFraction(str);
		else if (clazz == int.class)
			rep = getint(str);
		else if (clazz == List.class) {
				ParameterizedType pType = (ParameterizedType) gtype;
				List<Type> typelist = new ArrayList<Type>();
				getTypeList(pType,typelist);
				rep = getList(str, typelist.get(typelist.size()-1), typelist.subList(1, typelist.size()-1));
				}
		return rep;
	}

}
