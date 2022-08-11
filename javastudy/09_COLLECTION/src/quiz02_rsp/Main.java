package quiz02_rsp;

import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {

		String[] rsp = {"가위", "바위" , "보"};
		
		// rsp배열에서 임의의 값을 선택하여 HashSet 에 저장한다.
		//res[1] = "가위"
		//res[2] = "바위"
		//res[3] = "보"
		
		// 몇 번만에 HashSet에 모두 넣을 수 있는지 확인한다.
		Set<String> set = new HashSet<String>();
		
		

		int cnt = 0;
		
		while(set.size() < 3) {
			int i = (int) (Math.random() *3) ;
			set.add(rsp[i]);
			cnt++;
		}
		
		System.out.println(set);
		System.out.println(cnt);
	}

}
