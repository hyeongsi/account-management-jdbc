import java.util.Scanner;

import com.view.accountView;

public class accountMain {	

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		
		while(loop) {
			accountView.renderMenu();		// 메뉴 출력
			loop = accountView.run(scan);	// 메뉴 실행
		} 
		
		scan.close();
	} // end main	
	
}
