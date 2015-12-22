
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// get program

		// run next token til end of program

		// save tokenSeq
		
		BufferedReader br = null;
		ArrayList<String> tokens = null;
		try {
			br = new BufferedReader(new FileReader("C:\\temp\\testing.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		A1_Lexer lx = new A1_Lexer(br);
		lx.m_cr.mFileName = "C:\\temp\\testing.txt";
		
		//br = lx.m_cr.openFile();
		String error = "ERROR";
		tokens = new ArrayList<String>();
		
		if(true){ //file opens without errors CHECK LATER
			Token tk = new Token(br);
			do{ 
				System.out.println("EOF? 1 or 0: " +lx.m_cr.mEOF);
				tk = lx.next_token();
				tk.show();
				tokens.add(tk.returnTokens());
			}while(!error.equals(tk.mEnum.ERROR) && lx.m_cr.mEOF != 1);
		}
		
		System.out.println("OUTPUT FILE");
		for(int i = 0; i < tokens.size(); i++){
			System.out.println(tokens.get(i));
			
		}

		/*try (BufferedReader br = new BufferedReader(new FileReader("C:\\temp\\testing.txt")))
		{

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(sCurrentLine);
			     while (st.hasMoreTokens()) {
			         System.out.println("TOKEN: " + st.nextToken());
			     }
				System.out.println(sCurrentLine);
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}*/ 
	}
}
