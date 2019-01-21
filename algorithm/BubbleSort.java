package algorithm;

public class BubbleSort {
	
	public static void main(String[] args) {
		int[] num = {7,12,5,19,1};
		int temp;
		
		System.out.println("정렬 전 : " + num[0]+" "+num[1]+" "+num[2]+" "+num[3]+" "+num[4]);
		
		for(int i = 0; i<num.length; i++) {
			System.out.println(i+1 +"단계 ");
			for(int j = 1; j<num.length-i; j++) {
				if(num[j - 1] > num[j]) {
					temp = num[j-1];
					num[j-1] = num[j];
					num[j] = temp;
				}
				System.out.println("변화과정 : " + num[0]+" "+num[1]+" "+num[2]+" "+num[3]+" "+num[4]);
			}
		}
		System.out.println("결과 : " + num[0]+" "+num[1]+" "+num[2]+" "+num[3]+" "+num[4]);
	}

}
