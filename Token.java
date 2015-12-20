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
		FLOAT, ID, INT, KWDINPUT, KWDPRINT, KWDPROG, STRING, KEOF,
		// Below, these are NOT parser terminal symbols.
		// They are handy Lexer internal codes.
		ERROR
	}

	static int TERM_ENUM_SYMB_SIZE = 25; // Needs to change depending on size
	static public int TERMINALS_SIZE = TERM_ENUM_SYMB_SIZE - 1; // WTF is
																// this???

	public enum g_akenums { // !! Must be same order as ktenum_t.

		// DUPLICATED from terminal_enum_symbols without error

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
		FLOAT, ID, INT, KWDINPUT, KWDPRINT, KWDPROG, STRING, KEOF
	};

	public String[] g_aknames = {

			// Paired Delimeters
			"brace1", "brace2", "parens1", "parens2", "angle1", "angle2",
			"bracket1", "bracket2",

			// Unpaired Delimeters
			"comma", "semi",

			// Single-char operators
			"equal", "aster", "slash", "caret", "plus", "minus",

			// From ref compiler, unsure if used
			"float", "id", "int", "kwdinput", "kwdprint", "kwdprog", "string",
			"keof", };

	String error = "ERROR";
	public terminal_enum_symbols mEnum;
	public String mString;
	public int mLineNumber;
	public BufferedReader br;

	public Token(BufferedReader file) {
		mEnum = terminal_enum_symbols.ERROR;
		;
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
				  String sline;
				  //getline( g_tk_fin, sline );
				  //sline = br.readLine();
				  if ((sline = br.readLine()) != null){
				  //stringstream ssline( sline );
					  StringTokenizer ssline = new StringTokenizer(sline);
				  do { // Once.
				    String str;
				    str = ssline.nextToken();
				    //ssline >> str;
				    System.out.println(str); 
				    if ("(A1_token:" != str) { perr( "header", str ); break; }
				    ssline >> str;
				    DOUT DZ( str ) ENDLDOUT;
				    if ("m_enum=" != str) { perr( "enum key", str ); break; }
				    int ienum;
				    ssline >> ienum;
				    if (ienum != ienum % KTERMINALS_SIZE) { perr( "enum val", str ); break; }
				    m_enum = g_akenums[ ienum ];
				    DOUT DZ( m_enum ) ENDLDOUT;
				    ssline >> str;
				    DOUT DZ( str ) ENDLDOUT;
				    if ("m_line_num=" != str) { perr( "line_num key", str ); break; }
				    ssline >> m_line_num;
				    DOUT DZ( m_line_num ) ENDLDOUT;
				    ssline >> str;
				    DOUT DZ( str ) ENDLDOUT;
				    if ("m_str=" != str) { perr( "line_num key", str ); break; }
				    // Get the token's string field.
				    size_t qpos1 = sline.find( '"' );
				    if (string::npos == qpos1) { perr( "double-quote" ); break; }
				    size_t qpos2 = sline.find( '"', 1 + qpos1 );
				    if (string::npos == qpos2) { perr( "2nd double-quote" ); break; }
				    int len = qpos2 - qpos1 - 1;
				    DOUT DZ( len ) ENDLDOUT;
				    m_str = sline.substr( 1 + qpos1, len );
				    DOUT DZ( m_str ) ENDLDOUT;
				  } while (0); // Once.
				  // show( );
				  }
			};

	public void show() {
		System.out.println("A1_token: " + mEnum + " Line Number: "
				+ mLineNumber + " String: " + mString);

	}

}
