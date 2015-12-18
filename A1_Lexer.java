//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ A1_CHARIST CLASS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ (done, maybe put into its own class)
	public class A1_Lexer {

		public A1_Lexer(){
			usage();
			m_cr = new A1_charist();
			m_tk = new Token();
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
				System.out.println("  is_lu1");
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
			System.out.println("qDone? " + qdone + "mState" + m_state
					+ "mtbeg: " + m_tbeg);
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
			      m_tk.mString = get_str( ); // from tbeg to here.
			      // if ("input" == m_tk.m_str) m_tk.m_enum = "kwdinput";
			      if ("input" == m_tk.mString) m_tk.mEnum = m_tk.mEnum.KWDINPUT;
			      // else if ("print" == m_tk.m_str) m_tk.m_enum = "kwdprint";
			      else if ("print" == m_tk.mString) m_tk.mEnum = m_tk.mEnum.KWDPRINT;
			      // else if ("prog" == m_tk.m_str) m_tk.m_enum = "kwdprog";
			      else if ("prog" == m_tk.mString) m_tk.mEnum = m_tk.mEnum.KWDPROG;
			      // else m_tk.m_enum = "id";
			      else m_tk.mEnum = m_tk.mEnum.ID;
			      m_cr.pushBackChar( ); // Char that stopped us collecting.
			      qdone = 1;
			      // m_tk.show( );
			    }
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
			return null;

		}

		public String get_str2() {
			return null;

		}

		public void next_line() {

		}

		public Character peekChar() {
			return m_cr.peekChar();
		}

		public Token next_token() {
			System.out.println("L next_token.");
			  int qdone = 0;
			  Token tk = new Token();
			  
			  m_tk = tk; // Start with reset token.
			  m_state = m_state.ST_START;
			  do {
			    Character cx = m_cr.nextChar(false); //Maybe problem TRI
			    System.out.println(m_state + " "+ cx);
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