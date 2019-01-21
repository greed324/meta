package algorithm;

public class InsertionSort {
	
	public static void main(String[] args) {
		int[] num = {12,7,5,11,1};
		int temp;
		int data;
		
		System.out.println("정렬 전 : " + num[0]+" "+num[1]+" "+num[2]+" "+num[3]+" "+num[4]);
		
		for(int i=1; i<num.length; i++) {
			temp = num[i];	//임시 변수에 대상 배열값 저장
			data = i - 1;	//비교할 대상
			
			while((data >= 0 && temp < num[data])) {	//삽입 대상이 정렬된 대상보다 작을경우
				num[data + 1] = num[data];	//비교대상이 클 경우 오른쪽으로 이동
				data--;
			}
			num[data + 1] = temp;
			System.out.println("변화과정 : " + num[0]+" "+num[1]+" "+num[2]+" "+num[3]+" "+num[4]);
		}
		System.out.println("결과 : " + num[0]+" "+num[1]+" "+num[2]+" "+num[3]+" "+num[4]);
	}

}
