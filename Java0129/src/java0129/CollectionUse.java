package java0129;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class CollectionUse {

	public static void main(String[] args) {
		// List의 데이터 사용

		// 배열을 이용한 List 만들기
		List<String> list = Arrays.asList("C", "Python", "Java");

		// 인덱스를 이용해서 하나 씩 접근 - 배열이나 리스트의 데이터 개수를 항상 알아야 됨.
		System.err.println("데이터를 인덱스를 이용해서 하나 씩 접근");
		int length = list.size();
		int i = 0;
		while (i < length) {
			String temp = list.get(i);
			System.out.println(temp);
			i = i + 1;
		}

		System.err.println("반복자(Iterator - Enumeration : __iter__ : python) 이용해서 하나 씩 접근");
		// 반복자는 데이터 개수를 알 필요 없다.
		Iterator<String> iterator = list.iterator();
		// 다음 데이터 존재 여부를 확인하고 다음 데이터에 접근 - 데이터 베이스에서는 Cursor 이라고 한다.
		// 데이터베이스 나 C++에서는 이런 방법으로 접근 하지만 Java 나 Python 같은 언어는 다른 방법을 제공
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		System.out.println();
		System.err.println("빠른 열거(for ~ each) 를 이용하는 방법");
		// 반벅자가 구현된 객체의 경우는 빠른 열거를 사용하는 것이 가능
		for (String temp : list) {
			System.out.println(temp);
		}

		System.out.println();
		System.err.println("Stream을 이용하는 방법");
		// 스트림 생성
		Stream<String> stream = list.stream();
		// 람다식을 이용해서 메소드의 내용을 매개변수로 대입 - 함수형 프로그래밍
		// 스트림을 만들 떄 사용한 Collection의 데이터를 arg에 순서대로 대입해서 {} 안의 내용을 수행
		// 반복문을 사용하지 않아도 forEach 가 알아서 순서대로 실행
		// Python에서의 numpy 의 ndarray 나 pandas 의 series, DataFrame 등의 동작 방식이 이 방법이랑 똑같다.
		stream.forEach((arg) -> {
			System.out.println(arg);
		});

		System.out.println();
		System.err.println("배열을 이용해서 스트림 생성"); // 실습할 떄는 자주 사용하지만 실제 프로그래밍에서는 대부분 List로 생성
		// 프로그래밍에서는 특별한 경우가 아니면 데이터를 직접 입력하지 않고 외부에서 불러오기 때문에 데이터 개수를 알지 못해서 List로 생성
		// 프로그래밍 언어 공부 할 때 데이터를 직접 만드는 것이 중요하지 않은것은 아니지만 실제로는 데이터를 직접 외부에서 읽어온다.

		// 현재 디렉토리에 있는 word.csv 파일의 내용을 읽어서 문자열 배열로 만들기
		try {
			// 파일을 읽을수 있는 스트림 생성
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./word.csv")));

			// 데이터 한 줄 읽기
			String line = br.readLine();
			// , 단위로 불할해서 문자열 배열로 만들기
			String[] ar = line.split(",");
			// 스트림 만들기
			Stream<String> streaml = Arrays.stream(ar);
			// 출력
			streaml.forEach(System.out::println);
			;
			br.close();

			// ar[3]에는 12345건이라는 문자열이 존재 - 12345라는 정수로 변환
			int su = 0;
			for (i = 0; i < ar[4].length(); i = i + 1) {
				// 한글자 씩 가져오기
				char ch = ar[4].charAt(i);
				// 숫자인지 확인
				if (ch >= '0' && ch <= '9') {
					su = su * 10 + (ch - '0');

				// 콤마가 있으면 else구문을 생략, 없으면 else구문 써도 됨
					/*
				} else {
					break;
					*/
				}

			}
			System.out.println(su);

		} catch (Exception e) {
			System.out.println("파일 내용읽기 실패:" + e.getMessage());
			e.printStackTrace();
		}
	}

}
