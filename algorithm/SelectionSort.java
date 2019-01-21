package algorithm;

public class SelectionSort {	
	
	public static void main(String[] args) {
		int[] num = {7,12,5,19,1};
        int min;	//최소 값
        int data;	//최소 값 위치를 저장 할 변수
        int temp;	// 변경할 위치의 데이터를 임시로 저장하기 위한 변수
        
        System.out.println("정렬 전 : " + num[0]+" "+num[1]+" "+num[2]+" "+num[3]+" "+num[4]);
        
        for (int i = 0; i < num.length; i++) {
            min = num[i];	//최소 값 초기 설정
            data = i;	//최소 값
            for (int j = i+1; j < num.length; j++) {
                if(min > num[j]) {	//최소값과 현재 위치의 값을 비교
                    //현재 위치의 값이 작으면
                    min = num[j];	//현재 값을 최소 값으로
                    data = j;		//그 인덱스를 최소위치로
                }
            }
            System.out.println("변화과정 : " + num[0]+" "+num[1]+" "+num[2]+" "+num[3]+" "+num[4]);  
            //최소 값이랑 0번 인덱스의 값을 바꾼다
            temp = num[data];	//최소 값을 임시변수 temp에 저장한다
            num[data] = num[i];
            num[i] = min;
        }
        System.out.println("결과 : " + num[0]+" "+num[1]+" "+num[2]+" "+num[3]+" "+num[4]);        
    }
}
