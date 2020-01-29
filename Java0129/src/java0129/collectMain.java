package java0129;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class collectMain {

	public static void main(String[] args) {
		// 집계함수를 사용
		// 샘플 데이터 작성
		Student student1 = new Student(1, "유재석", "남", 30, 97);
		Student student2 = new Student(2, "이광수", "남", 27, 86);
		Student student3 = new Student(3, "하하", "남", 45, 78);
		Student student4 = new Student(4, "전소민", "여", 26, 58);
		Student student5 = new Student(5, "송지효", "여", 18, 90);

		ArrayList<Student> list = new ArrayList<Student>();
		list.add(student1);
		list.add(student2);
		list.add(student3);
		list.add(student4);
		list.add(student5);

		Stream<Student> stream = list.stream();
		
		// gender 의 값이 남 인 데이터만 가지고 List를 생성
		List<Student> manList = stream.filter((student) -> {
			return student.getGender().equals("남");
		}).collect(Collectors.toList());

		for (Student temp : manList) {
			System.out.println(temp);
		}
		
		// gender의 값이 여인 데이터를 가지고 name을 key로 하고 전체를 value로 하는 Map을 생성
		stream = list.stream();
		Map<String, Object> womanMap = stream.filter((student) -> {
			return student.getGender().equals("여");})
				.collect(Collectors.toMap(Student::getName, (student) -> {return student;}));

		
		// map의 모든 데이터 출력
		Set<String> keys = womanMap.keySet();
		for (String key : keys) {
			System.out.println(key + ":" + womanMap.get(key));

		}
		
		System.out.println();
		stream = list.stream();
		long count = stream.filter((student) -> {return student.getGender().equals("여");})
				.collect(Collectors.counting());
		System.out.println("여자 인원수 : " + count);
		

	}

}
