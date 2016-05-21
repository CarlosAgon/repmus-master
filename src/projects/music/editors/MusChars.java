package projects.music.editors;

public class MusChars {


	public static String fig1 = Character.toString((char) 81);
	public static String fig1_2 = Character.toString((char) 79);
	public static String fig1_4 = Character.toString((char) 78);
	public static String fig1_8 = Character.toString((char) 77);
	public static String fig1_16 = Character.toString((char) 76);
	public static String omicronpoint = Character.toString((char) 46);
	
	public static String get_stringfrom_num (int num) {
		String rep = "";
		switch (num) {
		case 1: rep = fig1_2 + omicronpoint;
		        break;
		case 2: rep = fig1_2;
				break;
		case 3: rep = fig1_4 + omicronpoint;
				break;
		case 4: rep = fig1_4;
				break;
		case 8: rep = fig1_8;
				break;
		case 16: rep = fig1_16;
				break;
		}
		return rep;
}

//=====================SCALES================================

	public static String diese = Character.toString((char)  35);
	public static String bemol = Character.toString((char)  98);
	public static String beca = Character.toString((char)  110);
	public static String db_diese = Character.toString((char)  45);
	public static String db_bemol = Character.toString((char) 98) + Character.toString((char) 98);
	public static String inv_bemol = Character.toString((char)  96);

	public static String t_1_16 = Character.toString((char)  107);
	public static String t_1_8 = Character.toString((char)  108);
	public static String t_3_16 = Character.toString((char)  76);  
	public static String t_1_4 = Character.toString((char)  43);
	public static String t_5_16 = Character.toString((char)  42);
	public static String t_3_8 = Character.toString((char)  41);
	public static String t_7_16 = Character.toString((char)  40);
	public static String t_1_2 = Character.toString((char)  35);
	public static String t_9_16 = Character.toString((char)  33);
	public static String t_5_8 = Character.toString((char)  34);
	public static String t_11_16 = Character.toString((char)  36);
	public static String t_3_4 = Character.toString((char)  48);  
	public static String t_13_16 = Character.toString((char)  49);  
	public static String t_7_8 = Character.toString((char)  50);  
	public static String t_15_16 = Character.toString((char)  51);  

	public static String beca_3 = Character.toString((char)  100);
	public static String t_1_12 = Character.toString((char)  107);
	public static String t_2_12 = Character.toString((char)  108);
	public static String t_3_12 = Character.toString((char)  76);   
	public static String t_4_12 = Character.toString((char)  113);
	public static String t_5_12 = Character.toString((char)  119);
	public static String t_6_12 = Character.toString((char)  35);
	public static String t_7_12 = Character.toString((char)  77);  
	public static String t_8_12 = Character.toString((char)  114);
	public static String t_9_12 = Character.toString((char)  120);
	public static String t_10_12 = Character.toString((char)  54);
	public static String t_11_12 = Character.toString((char)  78);
	
	public static String d_1_3 = Character.toString((char)  60);
	public static String d_2_3 = Character.toString((char)  61);
	
	public static String beca_5 = Character.toString((char)  100);
	public static String t_1_10 = Character.toString((char)  108);  
	public static String t_2_10 = Character.toString((char)  113);
	public static String t_3_10 = Character.toString((char)  53);
	public static String t_4_10 = Character.toString((char)  114);
	public static String t_5_10 = Character.toString((char)  35);
	public static String t_6_10 = Character.toString((char)  115);
	public static String t_7_10 = Character.toString((char)  55);
	public static String t_8_10 = Character.toString((char)  116);
	public static String t_9_10 = Character.toString((char)  56);

	public static String d_1_5 = Character.toString((char)  60);
	public static String d_2_5 = Character.toString((char)  61);
	public static String d_3_5 = Character.toString((char)  62);
	public static String d_4_5 = Character.toString((char)  63);

	public static String beca_7 = Character.toString((char)  100);
	public static String t_1_14 = Character.toString((char)  108);
	public static String t_2_14 = Character.toString((char)  113);
	public static String t_3_14 = Character.toString((char)  53);
	public static String t_4_14 = Character.toString((char)  114);
	public static String t_5_14 = Character.toString((char)  54);
	public static String t_6_14 = Character.toString((char)  115);
	public static String t_7_14 = Character.toString((char)  35);
	public static String t_8_14 = Character.toString((char)  116);
	public static String t_9_14 = Character.toString((char)  56);
	public static String t_10_14 = Character.toString((char)  117);
	public static String t_11_14 = Character.toString((char)  57);
	public static String t_12_14 = Character.toString((char)  118);
	public static String t_13_14 = Character.toString((char)  58);

	public static String d_1_7 = Character.toString((char)  60);
	public static String d_2_7 = Character.toString((char)  61);
	public static String d_3_7 = Character.toString((char)  62);
	public static String d_4_7 = Character.toString((char)  63);
	public static String d_5_7 = Character.toString((char)  64);
	public static String d_6_7 = Character.toString((char)  37); 

//=============dynamics
	public static String dyn_ppp = Character.toString((char) 82);
	public static String dyn_pp = Character.toString((char) 81);
	public static String dyn_p = Character.toString((char) 112);
	public static String dyn_mp = Character.toString((char) 80);
	public static String dyn_mf = Character.toString((char) 70);
	public static String dyn_f = Character.toString((char) 102);
	public static String dyn_ff = Character.toString((char) 103);
	public static String dyn_fff = Character.toString((char) 104);

	public static String [] dynamicsArray = {MusChars.dyn_ppp,  MusChars.dyn_pp, MusChars.dyn_p, MusChars.dyn_mp, 
		MusChars.dyn_mf, MusChars.dyn_f, MusChars.dyn_ff, MusChars.dyn_fff};
	
	public static int [] dynamicsMidiArray = {-1, 20, 40, 55, 60, 85, 100, 115, 127};
	
//==============Key
	public static String key_g = Character.toString((char) 38);
	public static String key_f = Character.toString((char) 63);
	public static String key_c = Character.toString((char) 66);

//======================head notes

	public static String head_8 = Character.toString((char) 83);
	public static String head_4 = Character.toString((char) 73);
	public static String head_2 = Character.toString((char) 82);
	public static String head_1 = Character.toString((char) 81);
	public static String head_1_2 = Character.toString((char) 80);
	public static String head_1_4 = Character.toString((char) 110);

	public static String head_carre = Character.toString((char) 101);
	public static String head_losange = Character.toString((char) 100);
	public static String head_rect = Character.toString((char) 98);
	public static String head_triangle = Character.toString((char) 102);
	public static String head_cercle = Character.toString((char) 104);


//=======================rests
	public static String rest_4 = Character.toString((char) 93);
	public static String rest_2 = Character.toString((char) 92);
	public static String rest_1 = Character.toString((char) 91);
	public static String rest_1_2 = Character.toString((char) 90);
	public static String rest_1_4 = Character.toString((char) 89);
	public static String rest_1_8 = Character.toString((char) 88); 
	public static String rest_1_16 = Character.toString((char) 87);
	public static String rest_1_32 = Character.toString((char) 86);
	public static String rest_1_64 = Character.toString((char) 85);
	public static String rest_1_128 = Character.toString((char) 84);

//=======================beams
	public static String beam_dwn = Character.toString((char) 74);
	public static String beam_up = Character.toString((char) 75);
			
//================Digits as strings
/*
	public static Strin num2sstr (int num){
		String str = Character.toString
	}
			   (let ((str Character.toString(num;
			         (rep "";
			     (loop for i from 0 to (_ (length str) 1) do
			           (setf rep (string+ rep (digi2sstr (elt str i;;
			     rep;

			(defun digi2sstr (char)
			   (case char
			     (#\1 Character.toString((char) 85; 
			     (#\2 Character.toString((char) 86;
			     (#\3 Character.toString((char) 87;
			     (#\4 Character.toString((char) 88; 
			     (#\5 Character.toString((char) 89; 
			     (#\6 Character.toString((char) 90; 
			     (#\7 Character.toString((char) 91; 
			     (#\8 Character.toString((char) 92; 
			     (#\9 Character.toString((char) 93;
			     (#\0 Character.toString((char) 84;;

*/
}


