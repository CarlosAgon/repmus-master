package projects.music.editors;

import kernel.tools.ST;


public class Scale {
	
	
	String [] alterations;;
	int [] lines;
	float approx;
	
	
	public Scale (String [] alts, int [] lin, float approxFact){
		alterations = alts;
		lines = lin;
		approx = approxFact;
	}
	
	public float getApprox () {
		return approx;
	}
	
	public int[] getLines () {
		return lines;
	}
	
	public String getAlteration (int midic){
		int cents = ST.mod(midic , 1200);
		int index = ST.mod(Math.round(cents / approx), lines.length);
		return alterations[index];
	}
	
	public static Scale getScale (String name) {
		Scale rep;
		switch (name) { 
		case "1": 	rep = one_tone_chromatic_scale; break; 
		case "1#": rep = diese_one_tone_chromatic_scale; break; 
		case "1/2": rep = current_1_2_scale; break; 
		case "1/3": rep = three_tone_chromatic_scale; break; 
		case "1/3#": rep = diese_three_tone_chromatic_scale; break;
		case "1/4": rep = four_tone_chromatic_scale; break; 
		case "1/5": rep = five_tone_chromatic_scale; break;
		case "1/5#": rep = diese_five_tone_chromatic_scale; break; 
		case "1/6": rep = six_tone_chromatic_scale; break; 
		case "1/7": rep = seven_tone_chromatic_scale; break; 
		case "1/7#": rep = diese_seven_tone_chromatic_scale; break;
		case "1/8": rep = eight_tone_chromatic_scale; break; 
		case "1/10": rep = ten_tone_chromatic_scale; break;
		case "1/12": rep = twelve_tone_chromatic_scale; break;
		case "1/14": rep = fourteen_tone_chromatic_scale; break; 
		case "1/16": rep = sixteen_tone_chromatic_scale; break; 
		default: rep = current_1_2_scale;
		}
		return rep;
	}
	

	//-----------1
	public static Scale  one_tone_chromatic_scale = 
			new Scale (new String [] {"", "", "", MusChars.diese,  MusChars.diese,  MusChars.diese }, new int [] {0, 1, 2, 3, 4, 5 }, 200);
	
	public static Scale  diese_one_tone_chromatic_scale = 
			new Scale (new String [] {"", "", "", MusChars.diese,  MusChars.diese,  MusChars.diese }, new int [] {0, 1, 3, 4, 5, 6}, 200);
	
	//----------2
	public static Scale  two_tone_chromatic_scale = 
			new Scale (new String [] {"", MusChars.diese, "", MusChars.diese, "", "", MusChars.diese, "", MusChars.diese, "", MusChars.diese, "" }, 
			new int [] {0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5, 6}, 100);
	
	public static Scale c_major_scale =
	   new Scale (new String[] {"", MusChars.diese,  "", MusChars.bemol, "",   "",  MusChars.diese, "", MusChars.bemol, "", MusChars.bemol,  ""},
	     new int[] { 0, 0, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6}, 100);


	//_________3
	public static Scale three_tone_chromatic_scale =
	    new Scale  (new String[]  {"", MusChars.t_4_12, MusChars.t_8_12,
	                              "", MusChars.t_4_12, MusChars.t_8_12,
	                              "", MusChars.t_4_12, MusChars.t_8_12,
	                              MusChars.diese, MusChars.d_1_3, MusChars.d_2_3,
	                              MusChars.diese, MusChars.d_1_3, MusChars.d_2_3,
	                              MusChars.diese, MusChars.d_1_3, MusChars.d_2_3},
	      new int[]  {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5}
	      , 200/3);

	public static Scale diese_three_tone_chromatic_scale =
	    new Scale (
	      new String[]  {MusChars.diese, MusChars.d_1_3, MusChars.d_2_3,
	                              MusChars.diese, MusChars.d_1_3, MusChars.d_2_3,
	                              "", MusChars.t_4_12, MusChars.t_8_12,
	                              "", MusChars.t_4_12, MusChars.t_8_12,
	                              "", MusChars.t_4_12, MusChars.t_8_12,
	                              "", MusChars.t_4_12, MusChars.t_8_12},
	      new int[]  {0, 0, 0, 1, 1, 1, 3, 3, 3,  4, 4, 4, 5, 5, 5, 6, 6, 6 }
	      , 200/3);

	//_________4
	public static Scale four_tone_chromatic_scale =
	   new Scale (
	     new String[]  {"", MusChars.t_1_4, MusChars.diese, MusChars.t_3_4, "", MusChars.t_1_4, MusChars.diese,
	    		 		MusChars.t_3_4, "", MusChars.t_1_4, "", MusChars.t_1_4, MusChars.diese,
	    		 		MusChars.t_3_4, "", MusChars.t_1_4, MusChars.diese, MusChars.t_3_4, "", MusChars.t_1_4,
	    		 		MusChars.diese, MusChars.t_3_4, "", MusChars.t_1_4},
	     new int[]  {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6}
	     , 50);

	//_________5
	public static Scale five_tone_chromatic_scale =
	   new Scale (new String[] {"", MusChars.t_2_10, MusChars.t_4_10, MusChars.t_6_10, MusChars.t_8_10,
	            "", MusChars.t_2_10, MusChars.t_4_10, MusChars.t_6_10, MusChars.t_8_10,
	            "", MusChars.t_2_10, MusChars.t_4_10, MusChars.t_6_10, MusChars.t_8_10,
	            MusChars.diese, MusChars.d_1_5, MusChars.d_2_5, MusChars.d_3_5, MusChars.d_4_5,
	            MusChars.diese, MusChars.d_1_5, MusChars.d_2_5, MusChars.d_3_5, MusChars.d_4_5,
	            MusChars.diese, MusChars.d_1_5, MusChars.d_2_5, MusChars.d_3_5, MusChars.d_4_5},
	     new int[]  {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5}
	     , 40);

	public static Scale diese_five_tone_chromatic_scale =
	    new Scale (new String[]  {MusChars.diese, MusChars.d_1_5, MusChars.d_2_5, MusChars.d_3_5, MusChars.d_4_5,
	                              MusChars.diese, MusChars.d_1_5, MusChars.d_2_5, MusChars.d_3_5, MusChars.d_4_5,
	                              "", MusChars.t_2_10, MusChars.t_4_10, MusChars.t_6_10, MusChars.t_8_10,
	                              "", MusChars.t_2_10, MusChars.t_4_10, MusChars.t_6_10, MusChars.t_8_10,
	                              "", MusChars.t_2_10, MusChars.t_4_10, MusChars.t_6_10, MusChars.t_8_10,
	                               "", MusChars.t_2_10, MusChars.t_4_10, MusChars.t_6_10, MusChars.t_8_10},
	      new int[]  {0, 0, 0, 0, 0, 1, 1, 1, 1, 1,
	                  3, 3, 3, 3, 3, 4, 4, 4, 4, 4,
	                  5, 5, 5, 5, 5, 6, 6, 6, 6, 6}
	      , 40);

	//_________6
	public static Scale six_tone_chromatic_scale =
	    new Scale (new String[]  {"", MusChars.t_2_12, MusChars.t_4_12, MusChars.t_1_2, MusChars.t_8_12, MusChars.t_10_12,
	                              "", MusChars.t_2_12, MusChars.t_4_12, MusChars.t_1_2, MusChars.t_8_12, MusChars.t_10_12,
	                              "", MusChars.t_2_12, MusChars.t_4_12, "", MusChars.t_2_12, MusChars.t_4_12,
	                              MusChars.diese, MusChars.t_8_12, MusChars.t_10_12, "", MusChars.t_2_12, MusChars.t_4_12,
	                              MusChars.diese, MusChars.t_8_12, MusChars.t_10_12, "", MusChars.t_2_12, MusChars.t_4_12,
	                              MusChars.diese, MusChars.t_8_12, MusChars.t_10_12, "", MusChars.t_2_12, MusChars.t_4_12},
	      new int[]  {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1,
	                  2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4,
	                  4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6}
	      , 200/6);



	//_________7
	public static Scale seven_tone_chromatic_scale =
	   new Scale ( new String[] {"", MusChars.t_2_14, MusChars.t_4_14, MusChars.t_6_14, MusChars.t_8_14, MusChars.t_10_14, MusChars.t_12_14,
	            "", MusChars.t_2_14, MusChars.t_4_14, MusChars.t_6_14, MusChars.t_8_14, MusChars.t_10_14, MusChars.t_12_14,
	            "", MusChars.t_2_14, MusChars.t_4_14, MusChars.t_6_14, MusChars.t_8_14, MusChars.t_10_14, MusChars.t_12_14,
	            MusChars.diese, MusChars.d_1_7, MusChars.d_2_7, MusChars.d_3_7, MusChars.d_4_7, MusChars.d_5_7, MusChars.d_6_7,
	            MusChars.diese, MusChars.d_1_7, MusChars.d_2_7, MusChars.d_3_7, MusChars.d_4_7, MusChars.d_5_7, MusChars.d_6_7,
	            MusChars.diese, MusChars.d_1_7, MusChars.d_2_7, MusChars.d_3_7, MusChars.d_4_7, MusChars.d_5_7, MusChars.d_6_7},
	     new int[] {0, 0, 0, 0, 0, 0, 0,
	            	1, 1, 1, 1, 1, 1, 1,
	            	2, 2, 2, 2, 2, 2, 2,
	            	3, 3, 3, 3, 3, 3, 3,
	            	4, 4, 4, 4, 4, 4, 4,
	            	5, 5, 5, 5, 5, 5, 5}
	     , 200/7);

	public static Scale diese_seven_tone_chromatic_scale =
	    new Scale (new String[]  {MusChars.diese, MusChars.d_1_7, MusChars.d_2_7, MusChars.d_3_7, MusChars.d_4_7, MusChars.d_5_7, MusChars.d_6_7,
	                              MusChars.diese, MusChars.d_1_7, MusChars.d_2_7, MusChars.d_3_7, MusChars.d_4_7, MusChars.d_5_7, MusChars.d_6_7,
	                              "", MusChars.t_2_14, MusChars.t_4_14, MusChars.t_6_14, MusChars.t_8_14, MusChars.t_10_14, MusChars.t_12_14,
	                              "", MusChars.t_2_14, MusChars.t_4_14, MusChars.t_6_14, MusChars.t_8_14, MusChars.t_10_14, MusChars.t_12_14,
	                              "", MusChars.t_2_14, MusChars.t_4_14, MusChars.t_6_14, MusChars.t_8_14, MusChars.t_10_14, MusChars.t_12_14},
	      new int[]  {0, 0, 0, 0, 0, 0, 0,
	                  1, 1, 1, 1, 1, 1, 1,
	                  3, 3, 3, 3, 3, 3, 3,
	                  4, 4, 4, 4, 4, 4, 4,
	                  5, 5, 5, 5, 5, 5, 5,
	                  6, 6, 6, 6, 6, 6, 6}
	      , 200/7);

	//_________8
	public static Scale eight_tone_chromatic_scale =
	    new Scale (
	      new String[] {"", MusChars.t_1_8, MusChars.t_1_4, MusChars.t_3_8, MusChars.t_1_2, MusChars.t_5_8, MusChars.t_3_4, MusChars.t_7_8,
	             "", MusChars.t_1_8, MusChars.t_1_4, MusChars.t_3_8, MusChars.t_1_2, MusChars.t_5_8, MusChars.t_3_4, MusChars.t_7_8,
	             "", MusChars.t_1_8, MusChars.t_1_4, MusChars.t_3_8,
	             "", MusChars.t_1_8, MusChars.t_1_4, MusChars.t_3_8, MusChars.t_1_2, MusChars.t_5_8, MusChars.t_3_4, MusChars.t_7_8,
	             "", MusChars.t_1_8, MusChars.t_1_4, MusChars.t_3_8, MusChars.t_1_2, MusChars.t_5_8, MusChars.t_3_4, MusChars.t_7_8,
	             "", MusChars.t_1_8, MusChars.t_1_4, MusChars.t_3_8, MusChars.t_1_2, MusChars.t_5_8, MusChars.t_3_4, MusChars.t_7_8,
	             "", MusChars.t_1_8, MusChars.t_1_4, MusChars.t_3_8},
	      new int[] {0, 0, 0, 0, 0, 0, 0, 0,
	    		  	1, 1, 1, 1, 1, 1, 1, 1,
	    		  	2, 2, 2, 2, 3, 3, 3, 3,
	    		  	3, 3, 3, 3, 4, 4, 4, 4,
	    		  	4, 4, 4, 4, 5, 5, 5, 5,
	    		  	5, 5, 5, 5, 6, 6, 6, 6}
	      , 25);

	//_________10
	public static Scale ten_tone_chromatic_scale =
	    new Scale (new String[] {"", MusChars.t_1_10, MusChars.t_2_10, MusChars.t_3_10, MusChars.t_4_10, MusChars.t_5_10, MusChars.t_6_10, MusChars.t_7_10, MusChars.t_8_10, MusChars.t_9_10,
	             "", MusChars.t_1_10, MusChars.t_2_10, MusChars.t_3_10, MusChars.t_4_10, MusChars.t_5_10, MusChars.t_6_10, MusChars.t_7_10, MusChars.t_8_10, MusChars.t_9_10,
	             "", MusChars.t_1_10, MusChars.t_2_10, MusChars.t_3_10, MusChars.t_4_10, "", MusChars.t_1_10, MusChars.t_2_10, MusChars.t_3_10, MusChars.t_4_10,
	             MusChars.diese, MusChars.t_6_10, MusChars.t_7_10, MusChars.t_8_10, MusChars.t_9_10,  "", MusChars.t_1_10, MusChars.t_2_10, MusChars.t_3_10, MusChars.t_4_10,
	             MusChars.diese, MusChars.t_6_10, MusChars.t_7_10, MusChars.t_8_10, MusChars.t_9_10, "", MusChars.t_1_10, MusChars.t_2_10, MusChars.t_3_10, MusChars.t_4_10,
	             MusChars.diese, MusChars.t_6_10, MusChars.t_7_10, MusChars.t_8_10, MusChars.t_9_10, "", MusChars.t_1_10, MusChars.t_2_10, MusChars.t_3_10, MusChars.t_4_10},
	      new int[]  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	                  1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
	                  2, 2, 2, 2, 2, 3, 3, 3, 3, 3,
	                  3, 3, 3, 3, 3, 4, 4, 4, 4, 4,
	                  4, 4, 4, 4, 4, 5, 5, 5, 5, 5,
	                  5, 5, 5, 5, 5, 6, 6, 6, 6, 6}
	      , 20);

	//_________12
	public static Scale twelve_tone_chromatic_scale =
	    new Scale (
	      new String[] {"", MusChars.t_1_12, MusChars.t_2_12, MusChars.t_3_12, MusChars.t_4_12, MusChars.t_5_12, MusChars.t_6_12, MusChars.t_7_12, MusChars.t_8_12, MusChars.t_9_12, MusChars.t_10_12, MusChars.t_11_12,
	             "", MusChars.t_1_12, MusChars.t_2_12, MusChars.t_3_12, MusChars.t_4_12, MusChars.t_5_12, MusChars.t_6_12, MusChars.t_7_12, MusChars.t_8_12, MusChars.t_9_12, MusChars.t_10_12, MusChars.t_11_12,
	             "", MusChars.t_1_12, MusChars.t_2_12, MusChars.t_3_12, MusChars.t_4_12, MusChars.t_5_12, "", MusChars.t_1_12,  MusChars.t_2_12, MusChars.t_3_12, MusChars.t_4_12, MusChars.t_5_12,
	             MusChars.diese, MusChars.t_7_12, MusChars.t_8_12, MusChars.t_9_12, MusChars.t_10_12, MusChars.t_11_12, "", MusChars.t_1_12,  MusChars.t_2_12, MusChars.t_3_12, MusChars.t_4_12, MusChars.t_5_12,
	             MusChars.diese, MusChars.t_7_12, MusChars.t_8_12, MusChars.t_9_12, MusChars.t_10_12, MusChars.t_11_12, "", MusChars.t_1_12,  MusChars.t_2_12, MusChars.t_3_12, MusChars.t_4_12, MusChars.t_5_12,
	             MusChars.diese, MusChars.t_7_12, MusChars.t_8_12, MusChars.t_9_12, MusChars.t_10_12, MusChars.t_11_12, "", MusChars.t_1_12,  MusChars.t_2_12, MusChars.t_3_12, MusChars.t_4_12, MusChars.t_5_12},
	      new int[]  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	                  1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
	                  2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3,
	                  3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4,
	                  4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5,
	                  5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6}
	      , (float) 200/12);

	//_________14
	public static Scale fourteen_tone_chromatic_scale =
	    new Scale ( new String[] {"", MusChars.t_1_14, MusChars.t_2_14, MusChars.t_3_14, MusChars.t_4_14, MusChars.t_5_14, MusChars.t_6_14, MusChars.t_7_14, MusChars.t_8_14, MusChars.t_9_14, MusChars.t_10_14, MusChars.t_11_14, MusChars.t_12_14, MusChars.t_13_14,
	             "", MusChars.t_1_14, MusChars.t_2_14, MusChars.t_3_14, MusChars.t_4_14, MusChars.t_5_14, MusChars.t_6_14, MusChars.t_7_14, MusChars.t_8_14, MusChars.t_9_14, MusChars.t_10_14, MusChars.t_11_14, MusChars.t_12_14, MusChars.t_13_14,
	             "", MusChars.t_1_14, MusChars.t_2_14, MusChars.t_3_14, MusChars.t_4_14, MusChars.t_5_14, MusChars.t_6_14, "", MusChars.t_1_14, MusChars.t_2_14, MusChars.t_3_14, MusChars.t_4_14, MusChars.t_5_14, MusChars.t_6_14,
	             MusChars.diese, MusChars.t_8_14, MusChars.t_9_14, MusChars.t_10_14, MusChars.t_11_14, MusChars.t_12_14, MusChars.t_13_14, "", MusChars.t_1_14, MusChars.t_2_14, MusChars.t_3_14, MusChars.t_4_14, MusChars.t_5_14, MusChars.t_6_14,
	             MusChars.diese, MusChars.t_8_14, MusChars.t_9_14, MusChars.t_10_14, MusChars.t_11_14, MusChars.t_12_14, MusChars.t_13_14, "", MusChars.t_1_14, MusChars.t_2_14, MusChars.t_3_14, MusChars.t_4_14, MusChars.t_5_14, MusChars.t_6_14,
	             MusChars.diese, MusChars.t_8_14, MusChars.t_9_14, MusChars.t_10_14, MusChars.t_11_14, MusChars.t_12_14, MusChars.t_13_14, "", MusChars.t_1_14, MusChars.t_2_14, MusChars.t_3_14, MusChars.t_4_14, MusChars.t_5_14, MusChars.t_6_14},
	      new int[]  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	    				1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
	    				2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3,
	    				3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4,
	    				4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5,
	    				5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6}
	      , 200/14);

	//_________16
	public static Scale sixteen_tone_chromatic_scale =
	    new Scale (new String[] {
	    		 "", MusChars.t_1_16, MusChars.t_1_8, MusChars.t_3_16, MusChars.t_1_4, MusChars.t_5_16, MusChars.t_3_8, MusChars.t_7_16, MusChars.t_1_2, MusChars.t_9_16, MusChars.t_5_8, MusChars.t_11_16, MusChars.t_3_4, MusChars.t_13_16, MusChars.t_7_8, MusChars.t_15_16,
	             "", MusChars.t_1_16, MusChars.t_1_8, MusChars.t_3_16, MusChars.t_1_4, MusChars.t_5_16, MusChars.t_3_8, MusChars.t_7_16, MusChars.t_1_2, MusChars.t_9_16, MusChars.t_5_8, MusChars.t_11_16, MusChars.t_3_4, MusChars.t_13_16, MusChars.t_7_8, MusChars.t_15_16,
	             "", MusChars.t_1_16, MusChars.t_1_8, MusChars.t_3_16, MusChars.t_1_4, MusChars.t_5_16, MusChars.t_3_8, MusChars.t_7_16,
	             "", MusChars.t_1_16, MusChars.t_1_8, MusChars.t_3_16, MusChars.t_1_4, MusChars.t_5_16, MusChars.t_3_8, MusChars.t_7_16, MusChars.t_1_2, MusChars.t_9_16, MusChars.t_5_8, MusChars.t_11_16, MusChars.t_3_4, MusChars.t_13_16, MusChars.t_7_8, MusChars.t_15_16,
	             "", MusChars.t_1_16, MusChars.t_1_8, MusChars.t_3_16, MusChars.t_1_4, MusChars.t_5_16, MusChars.t_3_8, MusChars.t_7_16, MusChars.t_1_2, MusChars.t_9_16, MusChars.t_5_8, MusChars.t_11_16, MusChars.t_3_4, MusChars.t_13_16, MusChars.t_7_8, MusChars.t_15_16,
	             "", MusChars.t_1_16, MusChars.t_1_8, MusChars.t_3_16, MusChars.t_1_4, MusChars.t_5_16, MusChars.t_3_8, MusChars.t_7_16, MusChars.t_1_2, MusChars.t_9_16, MusChars.t_5_8, MusChars.t_11_16, MusChars.t_3_4, MusChars.t_13_16, MusChars.t_7_8, MusChars.t_15_16,
	             "", MusChars.t_1_16, MusChars.t_1_8, MusChars.t_3_16, MusChars.t_1_4, MusChars.t_5_16, MusChars.t_3_8, MusChars.t_7_16},
	      new int[] 
	    		 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	             1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
	             2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3,
	             3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4,
	             4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5,
	             5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6}
	      , 200/16);



	public static Scale current_1_2_scale = two_tone_chromatic_scale;
	public static Scale current_1_4_scale = four_tone_chromatic_scale;
	public static Scale current_1_8_scale = eight_tone_chromatic_scale;
	
	

	public class AtonalScale extends Scale {
		int base;

		public AtonalScale(String[] alts, int[] lin, float approxFact) {
			super(alts, lin, approxFact);
		}
		
		public AtonalScale(String[] alts, int[] lin, float approxFact, int theBase) {
			super(alts, lin, approxFact);
			base = theBase;
		}
	}
	
/*	public static AtonalScale thirdty_one_equal_scale =
			new AtonalScale (new String[] {MusChars.inv_bemol, "", MusChars.t_1_4, MusChars.t_1_2, 
		             MusChars.bemol, MusChars.inv_bemol, "", MusChars.t_1_4, MusChars.t_1_2,
		             MusChars.bemol, MusChars.inv_bemol, "", MusChars.t_1_4, 
		             MusChars.inv_bemol, "", MusChars.t_1_4, MusChars.t_1_2,
		             MusChars.bemol, MusChars.inv_bemol, "", MusChars.t_1_4, MusChars.t_1_2,
		             MusChars.bemol, MusChars.inv_bemol, "", MusChars.t_1_4, MusChars.t_1_2,
		             MusChars.bemol, MusChars.inv_bemol, "", MusChars.t_1_4},
		             new int[] 
		           	      {0, 0, 0, 0, 1, 1, 1, 1, 1,
		           	       2, 2, 2, 2, 3, 3, 3, 3, 
		           	       4, 4, 4, 4, 4, 5, 5, 5, 5, 5,
		           	       6, 6, 6, 6},
		           	      (float) 1200/31,
		           	      5971);
			
	public static AtonalScale nieteen_one_equal_scale =
			new AtonalScale (new String[] {MusChars.bemol, "", MusChars.diese,
		                        MusChars.bemol, "", MusChars.diese,
		                        MusChars.bemol, "",
		                        MusChars.bemol, "", MusChars.diese,
		                        MusChars.bemol, "", MusChars.diese,
		                        MusChars.bemol, "", MusChars.diese,
		                        MusChars.bemol, ""},
				             new int[] 
				           	      {0, 0, 0, 
				             		1, 1, 1, 
				             		2, 2,  
				             		3, 3, 3,  
				             		4, 4, 4, 
				             		5, 5, 5, 
				             		6, 6},
				           	      (float) 1200/19,
				           	   5952);*/
			
		}


	
