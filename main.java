import java.util.*;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// get program

		// run next token til end of program

		// save tokenSeq
		
		A1_Lexer lx = new A1_Lexer();
		
		lx.m_cr.mFileName = "filename";
		
		lx.m_cr.openFile();
		String error = "ERROR";
		
		if(true){ //file opens without errors CHECK LATER
			Token tk = new Token();
			do{ 
				tk = lx.next_token();
				tk.show();
				
			}while(!error.equals(tk.mEnum.ERROR) || lx.m_cr.mEOF!= 1);
		}
		
		

	}
}
