package exeTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.SwingConstants;

public class exeTest {
	
	public int garo = 5;
	public int sero = 5;
	public LinkedHashMap<Integer, Integer[]> grid = new LinkedHashMap<>();
	public LinkedHashMap<Integer, String[]> piece = new LinkedHashMap<>();
	
	public static void main(String[] args) {
		exeTest et = new exeTest();

		// ArrayList<ArrayList<Integer>> grid = new
		// ArrayList<ArrayList<Integer>>();
		String[] temp1 = {"1","3"};
		String[] temp2 = {"a","d"};
		et.piece.put(1, temp1);
		et.piece.put(2, temp2);
		
		int count = 0;
		int i = 1;
		int j = 1;
		for (i = 1; i <= et.sero; i++) {
			for (j = 1; j <= et.garo; j++) {
				count++;
				// ArrayList<Integer> temp_arr = new ArrayList<Integer>();
				Integer[] yx = { i, j };
				et.grid.put(count, yx);
			}
		}

		et.grid.forEach((k, v) -> System.out.println("key: " + k + " value:" + v[0] + "/" + v[1]));
		//System.out.println(et.matching("1"));
		
	}
	// 담았고
	//
	// 같은 형태의 조각을 이미 읽은적이 있는가
	// 다음칸에 만족하는 조건은
	// 조건에 만족하는 조각들 추리기
	// 포인터 depth 관리
	
	// 조각 매칭
	public String matching(String in) {
		switch (in) {
		case "1": return "a"; 
		case "2": return "b"; 
		case "3": return "c"; 
		case "4": return "d"; 
		case "5": return "e"; 
		case "6": return "f"; 
		case "a": return "1"; 
		case "b": return "2"; 
		case "c": return "3"; 
		case "d": return "4"; 
		case "e": return "5"; 
		case "f": return "6"; 
		default: System.out.println( "error - matching" );
			break;
		}
		System.out.println( "error - matching" );
		return null;
	}
	
	// 다음 좌표 반환
	public Integer[] getNextPosi(Integer[] posi_arr){
		int y = posi_arr[0];
		int x = posi_arr[1];
		x++;
		if (x>this.garo){
			x = 1;
			y++;
			if (y>this.sero) {
				this.theEnd();	
			}
		}
		Integer[] r = {y,x};
		return r;
	}
	
	//해당하는 좌표에서 갖추어야 하는 조건 찾기
	public String[] findQualifi(Integer[] posi_arr){
		int y = posi_arr[0];
		int x = posi_arr[1];
		
		if (x>1){
			//left
			
		}
		if (y>1) {
			//above
			
			
		}
		
		return null;
	}
	
	
	//포함되었는가
	
	//없는가
	
	// 종료 함수
	public void theEnd(){
		System.out.println("the end");
		System.exit(0);
		return;
	}
}
