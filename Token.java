
import java.io.BufferedReader;
import java.util.*;

public class Token {

	public enum start_emum_symbols { // might replace symbols with token
										// REF:ksenum_t
		ST_START, ST_ID, ST_STRING, ST_DIGITS, ST_NUMP, ST_FLOAT
	}

	public enum terminal_enum_symbols { // might replace symbols with token
										// REF:ktenum_t
		// Paired Delimeters
		BRACE1, BRACE2, PARENS1, PARENS2, ANGLE1, ANGLE2, BRACKET1, BRACKET2, // not
																				// in
																				// ref
																				// compiler

		// Unpaired Delimeters
		COMMA, SEMI,

		// Single-char operators
		EQUAL, ASTER, SLASH, CARET, PLUS, MINUS,

		// From ref compiler, unsure if used
		FLOAT, ID, INT, STRING, KWDELSE, KWDELSEIF, KWDFCN,  KWDIF, KWDMAIN,
		KWDPROG, KWDRETURN, KWDVARS, KWDWHILE, KEOF,
		// Below, these are NOT parser terminal symbols.
		// They are handy Lexer internal codes.
		ERROR
	}

	static int TERM_ENUM_SYMB_SIZE = 31; // Needs to change depending on size
	static public int TERMINALS_SIZE = TERM_ENUM_SYMB_SIZE - 1; // WTF is
																// this???
	public enum g_akenums 
	{
	     
	     BRACE1(0), BRACE2(1), PARENS1(2), PARENS2(3), ANGLE1(4), ANGLE2(5), BRACKET1(6), BRACKET2(7),
	     COMMA(8), SEMI(9),

			// Single-char operators
			EQUAL(10), ASTER(11), SLASH(12), CARET(13), PLUS(14), MINUS(15),

			// From ref compiler, unsure if used
			FLOAT(16), ID(17), INT(18), STRING(19), KWDELSE(20), KWDELSEIF(21), KWDFCN(22), KWDIF(23), KWDMAIN(24),
			KWDPROG(25), KWDRETURN(26), KWDVARS(27), KWDWHILE(28), KEOF(29);

	     private static final Map<Integer,g_akenums> lookup 
	          = new HashMap<Integer,g_akenums>();

	     static {
	          for(g_akenums w : EnumSet.allOf(g_akenums.class))
	               lookup.put(w.getCode(), w);
	     }

	     private int code;

	     private g_akenums(int code) {
	          this.code = code;
	     }

	     public int getCode() { return code; }

	     public static g_akenums get(int code) { 
	          return lookup.get(code); 
	     }
	     
	     public static String getAsString(int code){
	    	 return lookup.get(code).toString(); 
	     }
	}
	


	public String[] g_aknames = {

			// Paired Delimeters
			"brace1", "brace2", "parens1", "parens2", "angle1", "angle2",
			"bracket1", "bracket2",

			// Unpaired Delimeters
			"comma", "semi",

			// Single-char operators
			"equal", "aster", "slash", "caret", "plus", "minus",

			// From ref compiler, unsure if used
			"float", "id", "int", "string", "else", "elseif", "fcn", "if", "main",
			"prog", "return", "vars", "while", "keof", };

	String error = "ERROR";
	public terminal_enum_symbols mEnum;
	public String mString;
	public int mLineNumber;
	public BufferedReader br;

	public Token(BufferedReader file) {
		mEnum = terminal_enum_symbols.ERROR;
		
		mLineNumber = -1;
		mString = "";
		br = file;
	}

	public terminal_enum_symbols findEnumByName(String inputName) {
		//
		terminal_enum_symbols returnValue = terminal_enum_symbols.ERROR;
		for (int i = 0; i < TERMINALS_SIZE; i++) {
			if (inputName == g_aknames[i]) {
				returnValue = terminal_enum_symbols.values()[i];
				break;
			}
		}
		return returnValue;
	};

	public void read(){
				System.out.println("A1_token::read"); 
				  String sline ="";
				  //getline( g_tk_fin, sline );
				  //sline = br.readLine();
				  //if ((sline = br.readLine()) != null){
					  
					  try{sline = br.readLine();}catch(Exception e){
						  System.out.println("failed io");
					  }
				  //stringstream ssline( sline );
					  StringTokenizer ssline = new StringTokenizer(sline);
				  do { // Once.
				    String str;
				    str = ssline.nextToken();
				    //ssline >> str;
				    System.out.println(str); 
				    if ("(A1_token:" != str) { System.out.println("ERR: header " + str); break; }
				    str = ssline.nextToken();
				    System.out.println(str); 
				    //ssline >> str;
				    //DOUT DZ( str ) ENDLDOUT;
				    if ("m_enum=" != str) { System.out.println("ERR: enum key " + str); break; }
				    int ienum = 0;
				    //ssline >> ienum;
				    str = ssline.nextToken();
				    try{ienum = Integer.parseInt(str);}
				    catch(Exception e){
				    	System.out.println("not int");
				    }
				    if (ienum != ienum % TERMINALS_SIZE) { System.out.println( "ERR: enum val "+ str); break; }
				    
				    mEnum = terminal_enum_symbols.valueOf(g_akenums.getAsString(ienum));
				    System.out.println(mEnum);
				    //ssline >> str;
				    str = ssline.nextToken();
				    System.out.println(str); 
				    if ("m_line_num=" != str) { System.out.println( "ERR: line_num key "+ str ); break; }
				    mLineNumber = Integer.parseInt(ssline.nextToken()); 
				    System.out.println(mLineNumber);
				    str = ssline.nextToken();
				    System.out.println(str); 
				    if ("m_str=" != str) { System.out.println("line_num key " + str); break; }
				    // Get the token's string field.
				    //size_t qpos1 = sline.find( '"' );
				    int qpos1 = sline.indexOf('"');
				    if (qpos1==-1) { System.out.println("Parse ERR: double-quote"); break; }
				    int qpos2 = sline.indexOf( '"', qpos1 + 1 );
				    if (qpos2 == -1) { System.out.println("Parse ERR: 2nd double-quote" ); break; }
				    int len = qpos2 - qpos1 - 1;
				    System.out.println(len);
				    mString = sline.substring( 1 + qpos1, len );
				    System.out.println(mString);
				  } while (false); // Once.
				  // show( );
				  //}
			};

	public void show() {
		System.out.println("A2_token: " + mEnum + " Line_Number: "
				+ mLineNumber + " String: '" + mString + "'");

	}
	
	public String returnTokens(){
		return "A2_token: " + mEnum + " Line_Number: "
				+ mLineNumber + " String: '" + mString + "'";
	}

}

