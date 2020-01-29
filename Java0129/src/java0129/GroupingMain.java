package java0129;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupingMain {

	public static void main(String[] args) {
		// 집계함수를 사용
		// 샘플 데이터 작성
		Student student1 = new Student(1, "유재석", "남", 30, 90);
		Student student2 = new Student(2, "김종국", "남", 27, 85);
		Student student3 = new Student(3, "아이린", "여", 25, 95);
		Student student4 = new Student(4, "이광수", "남", 26, 60);
		Student student5 = new Student(5, "송지효", "여", 18, 40);

		ArrayList<Student> list = new ArrayList<Student>();
		list.add(student1);
		list.add(student2);
		list.add(student3);
		list.add(student4);
		list.add(student5);

		// List를 String으로 변환
		Stream<Student> stream = list.stream();

		// stream으로 그룹화
		Map<String, List<Student>> map = stream.collect(Collectors.groupingBy(Student::getGender));

		Set<String> keys = map.keySet();
		for (String key : keys) {
			System.out.println(key + ":" + map.get(key));

		}

		System.out.println();
		// 스트림은 한 번 성공 하면 소멸된다.
		// 새로운 작업을 수행 할 때 마다 새로운 스트림을 다시 생성 해야 한다.
		stream = list.stream();
		// gender 별로 그룹화 한 후 score의 평균 구하기

		Map<String, Double> result = stream
				.collect(Collectors.groupingBy(Student::getGender, Collectors.averagingDouble(Student::getScore)));
		keys = result.keySet();
		for (String key : keys) {
			System.out.println(key + ":" + result.get(key));

			// 그룹화는 존재하는 메소드를 이용해도 되지만 람다로 직접 만들어도 된다.
			// 하나의 매개변수(스트림의 제너릭)를 받아서 데이터를 리턴하는 람다 식이면 된다.
			stream = list.stream();

			Map<Object, Double> result1 = stream.collect(Collectors.groupingBy((student) -> {
				return student.getName();
			}, Collectors.summingDouble(Student::getScore)));
		}
		keys = result.keySet();
		for (String key : keys) {
			System.out.println(key + ":" + result.get(key));

		}
		// 성별 최대 인 데이터를 추출
		stream = list.stream();
		// gender 별로 그룹화 한 후 score의 평균 구하기
		Map<String, Optional<Student>> result2 = stream.collect(Collectors.groupingBy(Student::getGender,
				Collectors.maxBy(Comparator.comparingInt(Student::getScore))));

	}
}
