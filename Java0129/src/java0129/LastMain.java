package java0129;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Stream;

public class LastMain {

	public static void main(String[] args) {
		//문자열 배열을 이용해서 스트림을 생성
		String [] ar = {"Python", "Java", "Scala", "Kotlin", "C#", "C&C++", "JavaScript"};
		Stream <String> arStream = Arrays.stream(ar);
		//배열의 모든 데이터가 3글자 이상인지 확인
		//anMatch는 true - any는 하나라도 만족하면 true
		//NonMatch는 false - 하나도 만족하는게 없으면 true
		boolean r =arStream.allMatch((language) ->{return language.length() > 3;});
		System.out.println(r);
		System.out.println();
		
		//집계함수를 사용
		//샘플 데이터 작성
		Student student1 = new Student(1, "유재석", "남", 30, 97);
		Student student2 = new Student(2, "이광수", "남", 27, 86);
		Student student3 = new Student(3, "하하", "남", 45, 78);
		Student student4 = new Student(4, "전소민", "여", 26, 58);
		Student student5 = new Student(5, "송지효", "여", 18, 90);
		
		ArrayList<Student>list = new ArrayList<Student>();
		list.add(student1);
		list.add(student2);
		list.add(student3);
		list.add(student4);
		list.add(student5);
		
		Stream<Student> stream = list.stream();
		//점수의 합계 구하기
		int tot = stream.mapToInt(Student::getScore).sum();
		System.out.println("score 합계: " + tot);
		stream = list.stream();
		System.out.println();
		
		//남자 나이 합계
		tot = stream.filter((student)->{return student.getGender().equals("남");}).mapToInt(Student::getAge).sum();
		System.out.println("남자 나이 합계: " + tot);
		System.out.println();
		
		//데이터 개수
		stream = list.stream();
		long cnt = stream.filter((student)->{return student.getGender().equals("여");}).count();
		System.out.println("데이터 개수: " + cnt);
		System.out.println();
		
		//Gender가 여 인 데이터의 score 평군
		stream = list.stream();
		//Optional은 기존 자료형의 데이터를 wrapping 한 자료형
		OptionalDouble avg= stream.filter((student)->{return student.getGender().equals("여");}).mapToInt(Student::getScore).average();
		//getAsDouble 로 가져오면 결과가 null 일 때 예외가 발생
		//orElse에서 기본값을 설정하면 결과가 null일 때 기본값을 리턴
		System.out.println("여자score 평균: " + avg.orElse(0));
		System.out.println();
		
		//max 나 min은 Comaparator.comparing자료형(비교형 데이터의 메소드) 를 대입하면 Optional<제너릭> 으로 결과를 리턴
		//score의 최대값
		stream = list.stream();
		Optional<Student> result = stream.filter((student)->{return student.getGender().equals("남");}).max(Comparator.comparingInt(Student::getScore));
		System.out.println("남자 최저 점수: " + result.get());
		
				
	}

}
