package binary;

import java.util.Stack;

public class DecimalToBinary {
	

	public DecimalToBinary() {
		
	}

	private int convertDecimalNum(int decimalNum) {
		Stack<Integer> stack = new Stack<Integer>();
		int hold = decimalNum ;
		do{
			if(hold % 2 == 0){
				stack.push(0);
			}else{
				stack.push(1);
			}
			hold =(hold/2); //remainder will dispaear since its an int
		}while (hold != 0);
		StringBuilder build = new StringBuilder();
		while (!stack.isEmpty()) {
			build.append(stack.pop());
		}
		
		int binaryNum = Integer.parseInt(build.toString());
		if(decimalNum <0){ //if the number started negative
			binaryNum *= -1; //return a negative binary number
		}
		return binaryNum;
	}

	public int getBinaryForm(int decimalNum) {
		//if (this.binaryNum.equals(null)) {
			int binaryNum=convertDecimalNum(decimalNum);
		//}
		return binaryNum;
	}

}
