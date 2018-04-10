import java.util.*;
import java.math.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.*;


public class partialPivot{


public void pivot(Double mat[][]){


		int currentCol = 0;
		int currentRow = 0;
		//{"106.8", "177.2", "279.2"};
		int length = mat.length;
		BigDecimal[][] newMat = new BigDecimal[length][length];
		BigDecimal[] vec = new BigDecimal[length];

		vec[0] = BigDecimal.valueOf(106.8);
		vec[1] = BigDecimal.valueOf(177.2);
		vec[2] = BigDecimal.valueOf(279.2);


		for(int i=0; i<length; i++){
			for(int j=0; j<mat.length; j++){
				newMat[i][j] = BigDecimal.valueOf(mat[i][j]); //change double matrix into BigDecimal matrix
			}
		}


	

		for(int i=0; i<length-1; i++){//total number of iterations 

			int maxRow=0;
			BigDecimal max= new BigDecimal("0");
			BigDecimal temp = new BigDecimal("0");
			BigDecimal vecTemp = new BigDecimal("0");
			BigDecimal multiplier= new BigDecimal("0");
			BigDecimal currentVal = new BigDecimal("0");
			BigDecimal vecCurrentVal = new BigDecimal("0");
			BigDecimal newMatVal = new BigDecimal("0");
			BigDecimal newVecVal = new BigDecimal("0");

			for(int j=currentRow; j<length; j++){//get max row
	
				if(newMat[j][currentCol].compareTo(max) == 1){
					max = newMat[j][currentCol];
					maxRow=j;
				}
			
			

					for(int k=currentCol; k<length; k++){ //swap max row to make it the pivot, do this for matrix and corresponding vector
														
						temp = newMat[maxRow][k];
						newMat[maxRow][k] = newMat[currentRow][k];
						newMat[currentRow][k] = temp;


						vecTemp = vec[maxRow];
						vec[maxRow] = vec[currentRow];
						vec[currentRow] = vecTemp;
						
					}
				}

					



						for(int x=currentRow+1; x<length; x++){ //get multiplier

							multiplier = newMat[x][currentCol].divide(max,20,RoundingMode.CEILING);
							
							for(int y=0; y<length; y++){

								currentVal = newMat[currentRow][y];//assign values in pivot row to currentVal
								currentVal = currentVal.multiply(multiplier); //apply multiplier
								newMatVal = newMat[x][y].subtract(currentVal); //take these values from target row (to make zero)
								newMat[x][y] = newMatVal; //assign new values to target row

				
							}
								/**Do the same with corresponding vector**/
								vecCurrentVal = vec[currentRow];
								vecCurrentVal = vecCurrentVal.multiply(multiplier);
								newVecVal = vec[x].subtract(vecCurrentVal);
								vec[x] = newVecVal;


							
						}
						/**Increment position**/
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
								newMat[row][col] = BigDecimal.ZERO;
								col++;
							}
							else if(col==num){
								row++;
								num++;
							}
						

					}

				}

			
					BigDecimal[] result = new BigDecimal[length];
					int arrIndex = 0;
					int thisRow = length-2;
					int thisCol = length-1;
					int vecIndex=length-2;
					int count = 0;
					int z, i ;
					BigDecimal first = new BigDecimal("0");
					BigDecimal preSum = new BigDecimal("0");
					BigDecimal sum = new BigDecimal("0");
					BigDecimal thisOne = new BigDecimal("0");

					//we can always add the first 'result' straight into out results vector, as its the same position no matter matrix size
					first = vec[length-1].divide(newMat[length-1][length-1], 20, RoundingMode.CEILING );
					result[0] = first;



						for(int j=length-2; j>=0; j--){//loop rows
							count = 0;
							arrIndex++;
							z = length-1;
							i = 1;
							sum = BigDecimal.ZERO;
							preSum = BigDecimal.ZERO;
							

							for(int x = length-1; x>=0; x--){//loop columns

										if(newMat[j][x].compareTo(BigDecimal.ZERO) !=0){//find number of non zero vals in row
											count++;
										}
									}
									int index = length - count; //index is where we want to stop iterating through the columns i.e before the position of
																//the leftmost non-zero value

									for(int y = length -1; y >0; y--){//loop to get matrix vals, multiply by previous result in result array, and store
																		//value in a 'preSum' variable which will be subtracted from a vector value
										if(y > index){

												preSum = preSum.add(newMat[j][y].multiply(result[i - 1])); 

												i++;
												//minusSum = minusSum.subtract(newMat[j][y]);
										}
											thisOne = newMat[j][index]; //leftmost non zero value in this row

											sum  = vec[vecIndex].subtract(preSum) ; //subtract 'preSum' val from vector value
											result[arrIndex] = sum.divide(thisOne,20,RoundingMode.CEILING); //divide by leftmost value to get new X value

									}


									vecIndex--; 
									//thisRow--;
								}
					/**Gauss show = new Gauss();
						show.display(newMat);**/
	
					for(int b = 0; b<length; b++){
						System.out.println("X " + (b+1) + "= " + result[b]);
					}
					for(int b = 0; b<length; b++){
						System.out.println(vec[b]);
					}
					
			}
			
			

public static void main(String[] args)throws Exception{
				
				Scanner inFile = new Scanner(new FileReader("C:/Users/Sean Rooney/Documents/College/MSc/Num Comp/input.txt"));
				Scanner myScanner = new Scanner(System.in);
				
				int[][] myVector;
				int size=0;
				
				Double[][] myMat;
				String nums =  inFile.nextLine();
				String [] sep = nums.split(";");
				size= (int)Math.sqrt(sep.length);
				myMat = new Double[size][size];
				
				Double[] numArray = new Double[sep.length];
				Double newVal;
				
				for(int i=0; i<sep.length; i++){
					newVal = Double.parseDouble(sep[i]);
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
					
						partialPivot four = new partialPivot();
						four.pivot(myMat);
					}
			
		
			}
		
	
		
