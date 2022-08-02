package lotto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class LottoMain {

	public static void main(String[] args) throws IOException {
		
		LottoNumber lottoNum = new LottoNumber();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();

		StringTokenizer st = new StringTokenizer(str, " ");
		lottoNum.LottoNumber();
//		int a = Integer.parseInt(st.nextToken())

		
	}

}
