package exeTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import javax.swing.SwingConstants;

public class exeTest {
	
	public int garo = 5;
	public int sero = 5;
	public int pointer = 1;
	public int pointer2 = 0;
	
	// 조각 좌표
	public LinkedHashMap<Integer, Integer[]> grid = new LinkedHashMap<>();
	
	// 조각 번호와 조각 구성
	public LinkedHashMap<Integer, String[]> piece = new LinkedHashMap<>();
	
	// 배열 번호와 조각 후보들
	public LinkedHashMap<Integer, ArrayList<Integer>> piece_candidate = new LinkedHashMap<>();
	
	// 실제적으로 조각들의 번호들이 들어갈 배열
	public LinkedHashMap<Integer, Integer> sol = new LinkedHashMap<>();
	
	public static void main(String[] args) {
		System.out.println("local exeTest Run...");
		exeTest et = new exeTest();

		// ArrayList<ArrayList<Integer>> grid = new
		// ArrayList<ArrayList<Integer>>();
		String[] temp1 = {"6","c"};
		String[] temp2 = {"6","c"};
		String[] temp3 = {"6","c"};
		String[] temp4 = {"f","a"};
		String[] temp5 = {"d","c"};
		String[] temp6 = {"6","c"};
		String[] temp7 = {"f","a","3","6"};
		String[] temp8 = {"f","a"};
		String[] temp9 = {"1","4"};
		String[] temp10 = {"f","a"};
		String[] temp11 = {"6","c"};
		String[] temp12 = {"1","6"};
		String[] temp13 = {"f","c","3","4"};
		String[] temp14 = {"d","a","3","4"};
		String[] temp15 = {"d","1"};
		String[] temp16 = {};
		String[] temp17 = {"d","6"};
		String[] temp18 = {"3","f"};
		String[] temp19 = {"1","c"};
		String[] temp20 = {"f","a","3","4"};
		String[] temp21 = {"1","d"};
		String[] temp22 = {"3","4"};
		String[] temp23 = {"3","4"};
		String[] temp24 = {"d","1"};
		String[] temp25 = {"3","f"};
		
		et.piece.put(1, temp1);
		et.piece.put(2, temp2);
		et.piece.put(3, temp3);
		et.piece.put(4, temp4);
		et.piece.put(5, temp5);
		et.piece.put(6, temp6);
		et.piece.put(7, temp7);
		et.piece.put(8, temp8);
		et.piece.put(9, temp9);
		et.piece.put(10, temp10);
		et.piece.put(11, temp11);
		et.piece.put(12, temp12);
		et.piece.put(13, temp13);
		et.piece.put(14, temp14);
		et.piece.put(15, temp15);
		et.piece.put(16, temp16);
		et.piece.put(17, temp17);
		et.piece.put(18, temp18);
		et.piece.put(19, temp19);
		et.piece.put(20, temp20);
		et.piece.put(21, temp21);
		et.piece.put(22, temp22);
		et.piece.put(23, temp23);
		et.piece.put(24, temp24);
		et.piece.put(25, temp25);
		/*
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
*/
		//et.grid.forEach((k, v) -> System.out.println("key: " + k + " value:" + v[0] + "/" + v[1]));
		//System.out.println(et.matching("1"));
		
		//manage
		int pointerLimit = et.garo * et.sero;
		for (; et.pointer <= pointerLimit; et.pointer++) {
						
			et.piece_candidate.put(et.pointer,et.findPieces( et.findNeedQualifi(et.getYX(et.pointer)),  et.findNeedlessQualifi(et.getYX(et.pointer)) ));
			et.piece_candidate.forEach((k, v) -> System.out.println(k+":num " + v.toString()));
			//반환된 조건만족 피스의 갯수가 0이면 포인터를 -1
			//System.out.println("pointer  "+et.pointer);
			//if(et.pointer>1) 
				//System.out.println(et.piece_candidate.get(et.pointer).toString());
			
			if (et.piece_candidate.get(et.pointer).size() < 1 ) {
				//System.out.println("1번");
				et.pointer--;
				et.pointer2++;
				continue;
			}
			
			//피스후보의 갯수를 포인터2가 넘어서면 포인터2 초기화0 , 포인터는 -1
			if (et.pointer2>et.piece_candidate.get(et.pointer).size()) {
				System.out.println("2번");
				et.pointer--;
				et.pointer2 = 0;
				continue;
			}
			et.sol.put(et.pointer, et.piece_candidate.get(et.pointer).get(et.pointer2));
			System.out.println(et.pointer);
			System.out.println(et.piece_candidate.get(et.pointer).get(et.pointer2));
			//if(et.pointer>1) return;
		}
		
	// 담았고
	//
	// 같은 형태의 조각을 이미 읽은적이 있는가
	// 다음칸에 만족하는 조건은
	// 조건에 만족하는 조각들 추리기
	// 포인터 depth 관리
	}	
	
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
	public ArrayList<String> findNeedQualifi(Integer[] posi_arr){
		int y = posi_arr[0];
		int x = posi_arr[1];
		ArrayList<String> ret = new ArrayList<>();
		/* yx
		 * 00 01 02 03 04    1 2 3 4 5
		 * 10 11 12 13 14    
		 * 20 21 22 23 24 
		 * 30 31 32 33 34
		 * 40 41 42 43 44
		 * 
		 */
		if (x>0){
			//left
			int target1 = getArrNum(y,x-1);
			int piece_num1 = this.sol.get(target1);
			String[] piece_cont1 = this.piece.get(piece_num1);
			String[] range1 = {"4","5","6"};
			
			for (int i = 0; i < piece_cont1.length; i++) {
				if ( Arrays.asList(range1).contains(piece_cont1[i]) )
				ret.add( matching(piece_cont1[i]) );
			}
			
		}
		if (y>0) {
			//above
			int target2 = getArrNum(y-1,x);
			int piece_num2 = this.sol.get(target2);
			String[] piece_cont2 = this.piece.get(piece_num2);
			String[] range2 = {"a","b","c"};
			for (int j = 0; j < piece_cont2.length; j++) {
				if ( Arrays.asList(range2).contains(piece_cont2[j]) )
				ret.add( matching(piece_cont2[j]) );
			}
			
			
		}
		
		return ret;
	}
	public ArrayList<String> findNeedlessQualifi(Integer[] posi_arr){
		int y = posi_arr[0];
		int x = posi_arr[1];
		ArrayList<String> ret = new ArrayList<>();
		if (y==0) {
			ret.add("1");
			ret.add("2");
			ret.add("3");
		}
		if (x==0) {
			ret.add("d");
			ret.add("e");
			ret.add("f");
		}
					
		if (y>=this.sero) {
			ret.add("a");
			ret.add("b");
			ret.add("c");
		}
		if (x>=this.garo) {
			ret.add("4");
			ret.add("5");
			ret.add("6");
		}
		return ret;
	}
	
	
	
	// 좌표 받아서 배열 번호 돌려주는 함수
	public Integer getArrNum(Integer y , Integer x){
		return (y*5) + x + 1;
	}
	// 배열번호 받아서 좌표 반환
	public Integer[] getYX(Integer num){
		num--;
		int a = (int)(num/this.garo);
		int b = (num%this.garo);
		Integer[] ret = {a,b};
		return ret;
	}
	
	//해당 좌표에 만족하는 조각들 반환
	public ArrayList<Integer> findPieces(ArrayList<String> need,ArrayList<String> needless){
		ArrayList<Integer> ret = new ArrayList<>();
		boolean needfg = true;
		boolean needlessfg = true;
		
		
		for (int i = 1; i <= this.piece.size() ; i++) {						//전체 조각의 길이 만큼
			
			//for (int j = 0; j < this.piece.get(i).length ; j++) {			//해당 조각의 조건 갯수 길이 만큼
			//need
				needfg = true;
				for (int j = 0; j < need.size() ; j++) {					//넘어온 변수의 길이만큼, 필요 조건
					/*if (need.get(j2).equals(this.piece.get(i)[j]) ){
						continue;
					}*/
					//boolean matchfg = IntStream.of(this.piece.get(i)).anyMatch(v -> v == need.get(j2));
					//if ( !IntStream.of(this.piece.get(i)).anyMatch(v -> v == need.get(j)) ) needfg = false;
					if ( !Arrays.asList(this.piece.get(i)).contains(need.get(j)) ) needfg = false;
				}
			//needless
				needlessfg = true;
				for (int j2 = 0; j2 < needless.size() ; j2++) {
					//if ( !IntStream.of(this.piece.get(i)).anyMatch(v -> v == needless.get(j2)) ) needlessfg = false;
					if ( Arrays.asList(this.piece.get(i)).contains(needless.get(j2)) ) needlessfg = false;
					
				}			
				
				// 해당 피스 번호 넣는 위치
				if(needfg && needlessfg)ret.add(i);
			
			//}
			
		}
		
		
		
		
		return ret;
	}
	
	
	//포함되었는가
	
	//없는가
	
	// 종료 함수
	public void theEnd(){
		System.out.println(sol.toString());
		System.out.println("the end");
		System.exit(0);
		return;
	}
}
