package java0129;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ParallelMain {

	// 문자열을 매개변수로 받아서 2초 후에 출력하는 메소드
	public static void work(String str) {
		try {
			Thread.sleep(2000);
			System.out.println(str);
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) {
		String[] ar = { "일", "이", "삼", "사", "오" };
		List<String> list = Arrays.asList(ar);

		// 일반 스트림을 생성해서 ar에 work를 수행 - 10초 동안 소요
		long start = System.currentTimeMillis();
		Stream<String> arStream = list.stream();
		arStream.forEach((data) -> {
			work(data);
		});
		long end = System.currentTimeMillis();
		System.out.println("순차처리:" + (end - start));

		// 병렬 스트림을 생성해서 work를 수행 - cpu 코어 개수에 따라 걸리는 시간이 다름
		// 코어가 5개 이상이면 한 변에 수행하므로 2초 정도 소요
		// 데이터의 모임을 가지고 작업을 수행하는 경우 다른 데이터의 영향을 전혀 받지않고 수행되는 경우에는 병렬처리를 하는 것이 시간으로는 이득
		// 앞의 데이터의 연산 결과를 가지고 다음 작업을 수행하는 작업은 병렬처리 하는것이 곤란
		// 필터링 같은 경우는 병렬처리를 수행하기 좋은 작업 - 이러한 처리 방식을 Mamp-Reduce 라고 한다.
		// 각각의 데이터에서 작업을 처리한 후 그 결과를 모아서 리턴
		Stream<String> parellelStream = list.parallelStream();
		start = System.currentTimeMillis();
		parellelStream.forEach((data) -> {
			work(data);
		});
		end = System.currentTimeMillis();
		System.out.println("병렬 처리: " + (end - start));

	}

}
