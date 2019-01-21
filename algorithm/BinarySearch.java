
package algorithm;

public class BinarySearch {

	public static void main(String[] args) {
		int[] num = {1,5,7,8,9,12,15,17,19,20,21};
		int searchNum = 17;
		int mid;
		int left = 0;
		int right = num.length-1;
		int count=0; // 반복횟수
		
		System.out.println("숫자 : " + num[0]+" "+num[1]+" "+num[2]+" "+num[3]+" "+num[4]+" "+num[5]+" "+num[6]+" "+num[7]+" "+num[8]+" "+num[9]);
		System.out.println("찾을 숫자 : " + searchNum);
		while(right >= left) {
			mid = (left + right) / 2;
			
			if(searchNum == num[mid]) {
				count++;
				System.out.println(count + "번째만에 찾았습니다.");
				System.out.println("찾은 숫자는 " + (mid+1) + "번째에 있습니다.");
				break;
				
			}
			else if(searchNum < num[mid]){
				right = mid -1;
				System.out.println("찾지 못했습니다.");
				count++;
			}
			else {
				left = mid +1;
				System.out.println("찾지 못했습니다.");
				count++;
			}
		}
		
	}

}