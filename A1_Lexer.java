import java.io.BufferedReader;

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ A1_CHARIST CLASS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ (done, maybe put into its own class)
	public class A1_Lexer {

		public A1_Lexer(BufferedReader br){
			usage();
			mBr = br;
			m_cr = new A1_charist(mBr);
			m_tk = new Token(mBr);
			
		}
		
		public void usage(){
			System.out.println("Usage for A1 Lexer");

		}
		
		public boolean is_lu1(Character rcx){
			System.out.println("C is_lul1?");
			return((('A' <= rcx) && (rcx <= 'Z'))
			           || (('a' <= rcx) && (rcx <= 'z'))
			           || ('_' == rcx));
		}

		public A1_charist m_cr;
		// public Token t = new Token();
		public int m_tbeg; // A1_token start index, in line.
		public Token.start_emum_symbols m_state;
		public Token m_tk;
		public BufferedReader mBr;
		public int mComment = 0;

		// public : // For Rel.
		public int doStart(Character rcx) {
			System.out.println("L do_start." + rcx);
			int qdone = 0;
			Character c2 = peekChar();
			if (('/' == rcx) && ('/' == c2)) {
				System.out.println(" '/");
				m_cr.nextLine();
			} else if (('0' <= rcx) && (rcx <= '9')) {
				System.out.println("  0..9");
				m_state = m_state.ST_DIGITS;
				m_tbeg = get_cx_index();
			} else if (is_lu1(rcx)) {
				System.out.println("yes,  is_lu1");
				m_state = m_state.ST_ID;
				m_tbeg = get_cx_index();
			} else if ('"' == rcx) {
				System.out.println("  '\"'");
				m_state = m_state.ST_STRING;
				m_tbeg = get_cx_index();
			} else if ((('-' == rcx) || ('+' == rcx))
					&& (('0' <= c2) && (c2 <= '9'))) {
				System.out.println("  +-0..9");
				m_state = m_state.ST_DIGITS; // Signed num.
				m_tbeg = get_cx_index();
			} else if ('{' == rcx) {
				m_tk.mLineNumber = get_cx_line_num();
				// m_tk.m_enum = "brace1";
				m_tk.mEnum = m_tk.mEnum.BRACE1;
				qdone = 1;
			} else if ('}' == rcx) {
				m_tk.mLineNumber = get_cx_line_num();
				// m_tk.m_enum = "brace2";
				m_tk.mEnum = m_tk.mEnum.BRACE2;
				qdone = 1;
			} else if ('(' == rcx) {
				m_tk.mLineNumber = get_cx_line_num();
				// m_tk.m_enum = "parens1";
				m_tk.mEnum = m_tk.mEnum.PARENS1;
				qdone = 1;
			} else if (')' == rcx) {
				m_tk.mLineNumber = get_cx_line_num();
				// m_tk.m_enum = "parens2";
				m_tk.mEnum = m_tk.mEnum.PARENS2;
				qdone = 1;
			} else if (',' == rcx) {
				m_tk.mLineNumber = get_cx_line_num();
				// m_tk.m_enum = "comma";
				m_tk.mEnum = m_tk.mEnum.COMMA;
				qdone = 1;
			} else if (';' == rcx) {
				m_tk.mLineNumber = get_cx_line_num();
				// m_tk.m_enum = "semi";
				m_tk.mEnum = m_tk.mEnum.SEMI;
				qdone = 1;
			} else if ('=' == rcx) {
				m_tk.mLineNumber = get_cx_line_num();
				// m_tk.m_enum = "equal";
				m_tk.mEnum = m_tk.mEnum.EQUAL;
				qdone = 1;
			} else if ('*' == rcx) {
				m_tk.mLineNumber = get_cx_line_num();
				// m_tk.m_enum = "aster";
				m_tk.mEnum = m_tk.mEnum.ASTER;
				qdone = 1;
			} else if ('/' == rcx) {
				m_tk.mLineNumber = get_cx_line_num();
				// m_tk.m_enum = "slash";
				m_tk.mEnum = m_tk.mEnum.SLASH;
				if(mComment++==1){mComment=0;
				m_cr.nextLine();
				}
				qdone = 1;
			} else if ('^' == rcx) {
				m_tk.mLineNumber = get_cx_line_num();
				// m_tk.m_enum = "caret";
				m_tk.mEnum = m_tk.mEnum.CARET;
				qdone = 1;
			} else if ('+' == rcx) {
				m_tk.mLineNumber = get_cx_line_num();
				// m_tk.m_enum = "plus";
				m_tk.mEnum = m_tk.mEnum.PLUS;
				qdone = 1;
			} else if ('-' == rcx) {
				m_tk.mLineNumber = get_cx_line_num();
				// m_tk.m_enum = "minus";
				m_tk.mEnum = m_tk.mEnum.MINUS;
				qdone = 1;
			}
			System.out.println("qDone? " + qdone + " mState" + m_state
					+ " mtbeg: " + m_tbeg);
			return qdone;

		};

		// ====================================STOPPED
		// HERE!!!!!!!!!!!!!!!!!========================================================
		public int do_id(char rcx) {
			
			
			System.out.println("L do_id.");
			  int qdone = 0;
			  if (('0' <= rcx) && (rcx <= '9')) ; // Continue.
			  else if (is_lu1( rcx )) ; // Continue.
			  else // ID done.
			    {
			      m_tk.mLineNumber = get_cx_line_num( );
			      System.out.println(m_tbeg);
			      m_tk.mString = get_str( ); 
			      System.out.println("HEREEE!!!!!!!!!!!!!!!!!!!!!!!!!");// from tbeg to here.
			      // if ("input" == m_tk.m_str) m_tk.m_enum = "kwdinput";
			      if ("else".equals(m_tk.mString)) m_tk.mEnum = m_tk.mEnum.KWDELSE;
			      // else if ("print" == m_tk.m_str) m_tk.m_enum = "kwdprint";
			      else if ("elseif".equals(m_tk.mString)) m_tk.mEnum = m_tk.mEnum.KWDELSEIF;
			      // else if ("prog" == m_tk.m_str) m_tk.m_enum = "kwdprog";
			      else if ("fcn".equals(m_tk.mString)) m_tk.mEnum = m_tk.mEnum.KWDFCN;
			      else if ("if".equals(m_tk.mString)) m_tk.mEnum = m_tk.mEnum.KWDIF;
			      else if ("main".equals(m_tk.mString)) m_tk.mEnum = m_tk.mEnum.KWDMAIN;
			      else if ("prog".equals(m_tk.mString)) m_tk.mEnum = m_tk.mEnum.KWDPROG;
			      else if ("return".equals(m_tk.mString)) m_tk.mEnum = m_tk.mEnum.KWDRETURN;
			      else if ("vars".equals(m_tk.mString)) m_tk.mEnum = m_tk.mEnum.KWDVARS;
			      else if ("while".equals(m_tk.mString)) m_tk.mEnum = m_tk.mEnum.KWDWHILE;
			      // else m_tk.m_enum = "id";
			      else m_tk.mEnum = m_tk.mEnum.ID;
			      m_cr.pushBackChar( ); // Char that stopped us collecting.
			      qdone = 1;
			      // m_tk.show( );
			    }
			  System.out.println("DEBUG!!! m_tk.mString= " + m_tk.mString);
			  System.out.println("DEBUG!!! m_tk.enum= " + m_tk.mEnum);
			  return qdone;
			
			
		}

		public int do_string(char rcx) {
			System.out.println("L do_string.");
			  int qdone = 0;
			  if ('"' == rcx) // End delimiter?
			    {
			      m_tk.mLineNumber = get_cx_line_num( );
			      // m_tk.m_enum = "string";
			      m_tk.mEnum = m_tk.mEnum.STRING;
			      m_tk.mString = get_str2( ); // from tbeg+1 to here-1.
			      qdone = 1;
			    }
			  return qdone;
		}

		public int do_digits(char rcx) {
			System.out.println("L do_digits.");
			  int qdone = 0;
			  if (('0' <= rcx) && (rcx <= '9')) ; // Continue.
			  else  if ('.' == rcx)
			    {
			      m_state = m_state.ST_NUMP; // Unsigned num.
			    }
			  else if (is_lu1( rcx )) // Letter?
			    {
			      String str = get_str( );
			      System.out.println("Error Lexer: letter after num, " + str + ".");
			      m_tk.mLineNumber = get_cx_line_num( );
			      // m_tk.m_enum = "error";
			      m_tk.mEnum = m_tk.mEnum.ERROR;
			      qdone = 1;
			    }
			  else // Num done.
			    {
			      m_tk.mLineNumber = get_cx_line_num( );
			      // m_tk.m_enum = "int";
			      m_tk.mEnum = m_tk.mEnum.INT;
			      m_tk.mString = get_str( ); // from tbeg to here.
			      m_cr.pushBackChar( ); // Char that stopped us collecting.
			      qdone = 1;
			    }
			  return qdone;
		}

		public int do_nump(char rcx) {
			System.out.println("L do_nump.");
			  int qdone = 0;
			  if (('0' <= rcx) && (rcx <= '9'))
			    {
			      m_state = m_state.ST_FLOAT;
			    }
			  else
			    {
			      String str = get_str( );
			      System.out.println("Error Lexer: non-digit after dot, " + str + ".");
			      m_tk.mLineNumber = get_cx_line_num( );
			      // m_tk.m_enum = "error";
			      m_tk.mEnum = m_tk.mEnum.ERROR;
			      qdone = 1;
			    }
			  return qdone;
		}

		public int do_float(char rcx) {
			System.out.println("L do_float.");
			  int qdone = 0;
			  if (('0' <= rcx) && (rcx <= '9')) ; // Continue.
			  else if (is_lu1( rcx )) // Letter?
			    {
			      String str = get_str( );
			      System.out.println("Error Lexer: letter after num, " + str + ".");
			      // m_tk.m_enum = "error";
			      m_tk.mEnum = m_tk.mEnum.ERROR;
			      qdone = 1;
			    }
			  else // Num done.
			    {
			      m_tk.mLineNumber = get_cx_line_num( );
			      // m_tk.m_enum = "float";
			      m_tk.mEnum = m_tk.mEnum.FLOAT;
			      m_tk.mString = get_str( ); // from tbeg to here.
			      m_cr.pushBackChar( ); // Char that stopped us collecting.
			      qdone = 1;
			    }
			  return qdone;   
		}

		public int get_cx_index() {
			return m_cr.mCxIndex;
		}

		public int get_cx_line_num() {
			return m_cr.mCxLineNum;
		}

		public String get_str() {
			System.out.println("C get_str. mLINE " + m_cr.mLine);
			  int beg = m_tbeg;
			  int end = m_cr.mCxIndex ;
			  int len = 1 + end - beg;
			  System.out.println(" beg end len "+ beg +" "+ end +" "+ len);
			  
			  return m_cr.mLine.substring(beg, end); 

		}

		public String get_str2() {
			System.out.println("C get_str.");
			  int beg = 1 + m_tbeg;
			  int end = m_cr.mCxIndex;
			  int len = 1 + end - beg;
			  return m_cr.mLine.substring( beg, end ); 

		}

		public void nextLine() {
			System.out.println("L next_line.");
			  m_cr.nextChar(true);
		}

		public Character peekChar() {
			return m_cr.peekChar();
		}

		public Token next_token() {
			System.out.println("L next_token.");
			  int qdone = 0;
			  Token tk = new Token(mBr);
			  
			  m_tk = tk; // Start with reset token.
			  m_state = m_state.ST_START;
			  do {
				  Character cx;
				  if (m_cr.mLineLength -1 == m_cr.mIndex){
					  cx = m_cr.nextChar(true);
					  if(cx==null)continue;
				  }else
			    cx = m_cr.nextChar(false); //Maybe problem TRI
			    System.out.println("MSTATE BITCH "+m_state + " CX BITCH "+ cx);
			    switch (m_state)
			      {
			        case ST_START : qdone = doStart( cx ); break;
			        case ST_DIGITS : qdone = do_digits( cx ); break;
			        case ST_ID : qdone = do_id( cx ); break;
			        case ST_STRING : qdone = do_string( cx ); break;
			        case ST_NUMP : qdone = do_nump( cx ); break;
			        case ST_FLOAT : qdone = do_float( cx ); break;
			      }
			  } while (qdone!=1 && m_cr.mEOF!=1);
			  m_tk.mLineNumber = m_cr.mLineNum;
			  // if (m_cr.m_eof) m_tk.m_enum = "EOF";
			  if (m_cr.mEOF == 1) m_tk.mEnum = m_tk.mEnum.KEOF;
			  return m_tk;
		}

	}