package kernel.tools;



public class Fraction {
	public long num, denum;
	
	public Fraction(long a){
		num = a; 
		denum = 1; 
	}
	
	public Fraction(long a, long b){
		num = a; 
		denum = b; 
	}

	public Fraction(Fraction f){
		num = f.num;
		denum = f.denum; 
	}
	
	
	///////////////////////////////////////////
	
	public long getDenominator() {
		return denum;
	}
	
	public long getNumerator() {
		return num;
	}

	///////////////////////////////////////////
	
	public Fraction reduce(){
		double gcd; 
		if(num>0){
			gcd = OP.gcd(num, denum);
		}
		else
			gcd = OP.gcd(-num, denum);

		//System.out.println(num+"/"+denum+"->"+gcd);
		this.num = (int) ((double)this.num / gcd);
		this.denum = (int) ((double)this.denum / gcd);
		return this; 
	}

	public double value(){
		return (double)num/(double)denum;
	}
	
	public Fraction abs(){
		return new Fraction (Math.abs(num),denum);
	}
	

	public Fraction multiply(long value){
		num*=value;
		this.reduce();
		return this;
	}

	public Fraction multiply(Fraction f){
		num*=f.num;
		denum*=f.denum;

		this.reduce();

		return this;
	}
	public Fraction divide(long value){
		denum*=value;
		this.reduce();
		return this;
	}

	public String toString(){
		return ""+num+"/"+denum;
	}

	public boolean equals(Fraction f){
		f.reduce();
		this.reduce();
		if(f.num == this.num && f.denum == this.denum){
			return true;
		}
		return false; 
	}

	public static Fraction addition (Fraction f1, Fraction f2) {
		return (new Fraction((f1.num*f2.denum) + (f2.num*f1.denum) , f1.denum * f2.denum)).reduce(); 
	}
	
	
	public  boolean isBinaire () {
		if (! (this.getNumerator() == 1)) return false;
		double exp = 0;
		while (this.getDenominator() >= Math.pow(2, exp))
			exp++;
		return this.getDenominator() == Math.pow(2, exp);
	}
	
	public  boolean isTernaire () {
			return (this.getNumerator() == 3 && 
				(new Fraction (1, this.getDenominator()).isBinaire ()));
	}
	
	public static Fraction parse(String str){
		String delims = "/";
		String[] tokens = str.split(delims);
		if (tokens.length == 1){
			try {
				long num = Long.parseLong(tokens[0]);
				return new Fraction(num,1);
		      } catch (NumberFormatException nfe) {
		         return null;
		      }
		} else if (tokens.length != 2) return null;
			else {
				try {
					long num = Long.parseLong(tokens[0].replace(" ", ""));
					long den = Long.parseLong(tokens[1].replace(" ", ""));
					return new Fraction(num,den);
				} catch (NumberFormatException nfe) {
					return null;
				}
			}
		}
	
	public static final Fraction ZERO = new Fraction(0);
	public static final Fraction ONE = new Fraction(1);
	public static final Fraction TWO = new Fraction(2);
	public static final Fraction THREE = new Fraction(3);
	public static final Fraction FOUR = new Fraction(4);
	public static final Fraction FIVE = new Fraction(5);
	public static final Fraction SIX = new Fraction(6);
	public static final Fraction SEVEN = new Fraction(7);
	public static final Fraction EIGHT = new Fraction(8);
	public static final Fraction NINE = new Fraction(9);
	
	public static final Fraction f1_2 = new Fraction(1,2);
	public static final Fraction f1_4 = new Fraction(1,4);
	public static final Fraction f1_8 = new Fraction(1,8);
	public static final Fraction f1_16 = new Fraction(1,16);
	public static final Fraction f1_32 = new Fraction(1,32);
	public static final Fraction f1_64 = new Fraction(1,64);
	public static final Fraction f1_128 = new Fraction(1,128);
	public static final Fraction f1_256 = new Fraction(1,256);
	public static final Fraction f1_512 = new Fraction(1,512);


}
