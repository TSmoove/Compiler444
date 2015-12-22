
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class A1_charist {
	public A1_charist(BufferedReader br){
		mLineLength = 0;
		mIndex = 0;
		mLineNum = 0;
		mCxLineNum = 0;
		mCxIndex = 0;
		mEOF = 0;
		mLine = "";
		mFileIn = br;
	}
	
	//========================================================= NEXT CHARACTER ========== (done, might be problem converting int to boolean)
	
	public Character nextChar(boolean rSkipLine){
		//rSkipLine = 0 is false
		System.out.println("A2_CharManipulate nextChar()~ skipline? " + rSkipLine);
		System.out.println("mIndex =" + mIndex + " mLineLength = "+ mLineLength);
		Character retval = null;
		if (rSkipLine  || mIndex>= mLineLength){
			mLineLength = 0;//force new Line
			
		}
		while(mLineLength== 0){ //MAYBE PROBLEM
			if(nextLine()==0){
				break;
			}
			mIndex = -1;
		} //Update line if needed
		
		System.out.println("mIndex= "+ mIndex + " mLineLength= " + mLineLength);
		if(mLineLength !=0 ){
			retval = mLine.charAt(++mIndex);
		}
		System.out.println("DOES IT HAVE SPACES" + mLine);
		System.out.println(retval);
		mCxIndex = mIndex;
		System.out.println("mCxIndex= " + mCxIndex + " mIndex = "+ mIndex);
		
		return retval; 
		
	};
	
	
	//======================================================  OPEN FILE ================== (done, might have conversion problems)
	
	public BufferedReader openFile(){
		try {
			mFileIn = new BufferedReader(new FileReader(mFileName));
		} catch (FileNotFoundException e) {
			System.out.println("Open File");
			e.printStackTrace();
		}
		return mFileIn;
	};
	
	
	//=========================================== PEEK CHARACTER ================================ (done, maybe don't use println and print on same line)
	
	public Character peekChar(){
		Character retval = 0;
		System.out.println("CX INDEX= " +mCxIndex);
		if(mIndex > mCxIndex){
			retval = mLine.charAt(mIndex);
		}
		System.out.println("C peekChar. " + "retval = " + retval + "BLAH");
		//System.out.println(retval);
		return retval;
	};

	//=========================================== PUSH BACK CHARACTER ================================ (done)
	
	public void pushBackChar(){
		--mIndex;
	};
	
	//====================================================== NEXT LINE ============= (relatively done)
	
	public int nextLine() {
		System.out.println("C nextLine. ");
		mLineLength = null;
		while(mLineLength == null){
			
			try {
				if((mLine = mFileIn.readLine()) == null){
						//END OF FILE
						System.out.println("Lexer: EOF");
						mEOF = 1;
						
					
				}
			} catch (IOException e) { // I/O Error
				System.out.println("Error Lexer: Input problem");
				e.printStackTrace();//Block later
				System.exit(1);
			}
			if (mLine!=null)
			mLineLength = mLine.length();
			else mLineLength =0;
			++mLineNum;
			mCxIndex=-1; mIndex =-1;
			System.out.println("mLineNum= "+ mLineNum + " mLineLength= "+ mLineLength +" mLine= " + mLine);
		}
		return mLineLength;//TODO
		
	};
	
	//====================================================== SHOW =========== (done)
	
	public void show(){
		System.out.println("A1_charist: ");
		System.out.println("mLineLength "+ mLineLength);
		System.out.println("mIndex "+ mIndex);
		System.out.println("mLineNum "+ mLineNum);
		System.out.println("mCxLineNum "+ mCxLineNum);
		System.out.println("mIndex "+ mIndex);
		System.out.println("mLine "+ mLine);
		
	};
	
	
	//======================= MEMBER VARIABLES FOR A1_CHARIST CLASS ==========================
	
	public Integer mLineLength, mIndex,mLineNum, mCxLineNum, /*index of current getChar*/ 
	mCxIndex, mEOF;
	public String mLine, mFileName;
	//ifstream mFile
	public BufferedReader mFileIn;
	
	
} // END OF A1_CHARIST CLASS




