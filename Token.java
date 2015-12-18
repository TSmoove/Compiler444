import java.util.*;

public class Token {
	
	
	
	public enum start_emum_symbols { //might replace symbols with token REF:ksenum_t
		ST_START, ST_ID, ST_STRING,
        ST_DIGITS, ST_NUMP, ST_FLOAT
	}
	
	
	public enum terminal_enum_symbols { //might replace symbols with token REF:ktenum_t
		//Paired Delimeters
		BRACE1, BRACE2, PARENS1, PARENS2, 
		ANGLE1, ANGLE2, BRACKET1, BRACKET2, //not in ref compiler
		
		//Unpaired Delimeters
		COMMA, SEMI,
		
		//Single-char operators
		EQUAL, ASTER, SLASH, CARET, PLUS, MINUS,
		
		
			//From ref compiler, unsure if used   
			  FLOAT, ID, INT, KWDINPUT, KWDPRINT,
			  KWDPROG,  
			   STRING, KEOF,
			  // Below, these are NOT parser terminal symbols.
			  // They are handy Lexer internal codes.
			  ERROR
	}
	
	static int TERM_ENUM_SYMB_SIZE = 25; //Needs to change depending on size
	static public int TERMINALS_SIZE = TERM_ENUM_SYMB_SIZE - 1; //WTF is this???
	
	public enum g_akenums{  // !! Must be same order as ktenum_t.
		
		//DUPLICATED from terminal_enum_symbols without error
		  
		//Paired Delimeters
				BRACE1, BRACE2, PARENS1, PARENS2, 
				ANGLE1, ANGLE2, BRACKET1, BRACKET2, //not in ref compiler
				
				//Unpaired Delimeters
				COMMA, SEMI,
				
				//Single-char operators
				EQUAL, ASTER, SLASH, CARET, PLUS, MINUS,
				
				
					//From ref compiler, unsure if used   
					  FLOAT, ID, INT, KWDINPUT, KWDPRINT,
					  KWDPROG,  
					   STRING, KEOF
		  };
		  
	
		public String[] g_aknames = {
			   
			  //Paired Delimeters
			    "brace1", "brace2", "parens1", "parens2",
			    "angle1", "angle2", "bracket1", "bracket2",
			    
			  //Unpaired Delimeters 
			    "comma", "semi",
			    
			  //Single-char operators
			    "equal", "aster", "slash", "caret", "plus", "minus",
			    
			  //From ref compiler, unsure if used   
			    "float", "id", "int", "kwdinput", "kwdprint", "kwdprog", 
			      "string", "keof",
		};
		
		
		
		
			String error = "ERROR";
			public terminal_enum_symbols mEnum; 
			public String mString;
			public int mLineNumber;
			
			public Token(){
			mEnum = terminal_enum_symbols.ERROR;;
			mLineNumber = -1;
			mString = "";
			}
			
			public terminal_enum_symbols findEnumByName(String inputName){
				//
				terminal_enum_symbols returnValue = terminal_enum_symbols.ERROR;
				for(int i=0; i<TERMINALS_SIZE;i++){
					if (inputName == g_aknames[i])
			        {
			          returnValue = terminal_enum_symbols.values()[i];
			          break;
			        }
			    }	
				return returnValue;
			};
			
			
			public void read(){
				//refer to buffer reader for file
				
				//br.readline()
				
				//TO DO
			};
			
			public void show(){
				System.out.println("A1_token: "+ mEnum +" Line Number: " 
			+mLineNumber + " String: " + mString);
				
			}
			
		}

	

