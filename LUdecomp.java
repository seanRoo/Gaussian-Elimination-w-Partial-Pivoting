import java.util.*;
import java.math.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.*;

public class LUdecomp{

	public void decomp(BigDecimal mat[][] ){
		int length = mat.length;
		int currentCol = 0;
		int currentRow = 0;
		int arrIndex = 0;
		BigDecimal multiplier= new BigDecimal("0");
		BigDecimal[][] multArr = new BigDecimal[length][1];
		BigDecimal[][] lArray = new BigDecimal[length][length];
		BigDecimal currentVal = new BigDecimal("0");
		BigDecimal newMatVal = new BigDecimal("0");



		for(int i=0; i<length-1; i++){

			for(int x=currentRow+1; x<length; x++){ //get multiplier

				multiplier = mat[x][currentCol].divide(mat[currentRow][currentCol],20,RoundingMode.CEILING);
				multArr[arrIndex][0] = multiplier;
				arrIndex++;
				
				for(int y=0; y<length; y++){

					currentVal = mat[currentRow][y];//assign values in pivot row to currentVal
					currentVal = currentVal.multiply(multiplier); //apply multiplier
					newMatVal = mat[x][y].subtract(currentVal); //take these values from target row to make zero
					mat[x][y] = newMatVal; //assign new values to target row

	
				}

								
			}
	
			currentRow++;
			currentCol++;
		}

					/**Make lower traingle values zero**/
					int col = 0;
					int row = 1;
					int num = 1;
					for(int i = 0; i<length-1; i++){
						col=0;
						for(int j=0; j<length; j++){

							if(col<num){
								mat[row][col] = BigDecimal.ZERO;
								col++;
							}
							else if(col==num){
								row++;
								num++;
							}
						

					}

				}
				LUdecomp ones = new LUdecomp();
				ones.onesZeros(lArray);
				ones.lMat(lArray, multArr);
				System.out.print("L Matrix = ");
				System.out.println("");
				ones.display(lArray);
				System.out.print("U Matrix = ");
				System.out.println("");
				ones.display(mat);
				ones.display(multArr);
		}

	public void display(BigDecimal mat[][]){
		
		int length = mat.length;
		int colLength=0;
		
		if(mat[0].length>1){
			
			colLength=mat.length;
		}
		else if(mat[0].length==1){
			colLength=1;
		}
		for(int i=0; i<length; i++){
			int count=0;
			
			for(int j=0; j<colLength; j++){
				System.out.print(mat[i][j] + "  ");
				count++;
				
				if(count==length){
				
					System.out.println("");
				}
				
			}
		}
}
	
	public BigDecimal[][] lMat(BigDecimal mat[][], BigDecimal multis[][]){

		int length = mat.length;
		int thisRow = 1;
		int thisCol = 0;
		int num = 1;
		int arrIndex=0;

		for(int i = 0; i<length-1; i++){

			thisCol=0;

			for(int j=0; j<length-1; j++){

				if(thisCol<num){

					mat[thisRow][thisCol] = multis[arrIndex][0];
					thisCol++;
					arrIndex++;

				}

				else if(thisCol==num){
					thisRow++;
					num++;
				}
			

			}

		}
		return mat;

	}

	public BigDecimal[][] onesZeros(BigDecimal mat[][]){
		int length = mat.length;

		int oneRow = 0;
		int oneCol = 0;

		int twoRow = length -2;
		int twoCol;
		int num = 1;

		for(int i = 0; i<length; i++){
			mat[oneRow][oneCol] = BigDecimal.valueOf(1);
			oneRow++;
			oneCol++;
		}

		for(int i = 0; i<length-1; i++){
			twoCol = length-1;
			for(int j=0; j<length; j++){

				if(twoCol>num){
					mat[twoRow][twoCol] = BigDecimal.ZERO;
					twoCol--;
				}
				else if(twoCol==num){
					twoRow--;
					num--;
				}
			

		}

	}
		return mat;
}

	public static void main(String[] args)throws Exception{
				
				Scanner inFile = new Scanner(new FileReader("C:/Users/Sean Rooney/Documents/College/MSc/Num Comp/input.txt"));
				Scanner myScanner = new Scanner(System.in);

				BigDecimal[][] myMat;
				String nums =  inFile.nextLine();
				String [] sep = nums.split(";");
				int size= (int)Math.sqrt(sep.length);
				myMat = new BigDecimal[size][size];
				
				BigDecimal[] numArray = new BigDecimal[sep.length];
				BigDecimal newVal = new BigDecimal("0");
				
				for(int i=0; i<sep.length; i++){
					newVal = BigDecimal.valueOf(Double.parseDouble(sep[i]));
					numArray[i] = newVal;
				}
				
				int row=0;
				int col=0;
					
					for(int i=0; i<numArray.length; i++){
						
						myMat[row][col]= numArray[i];
						col++;
						
						if(col==size){
							
							row++;
							col=0;
								
							}
						}
					
						LUdecomp four = new LUdecomp();
						four.decomp(myMat);
						
					}
			
		
}
		