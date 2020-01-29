package java0129;

public class DesingPatternMain {

	public static void main(String[] args) {
		Singleton s1 = Singleton.sharedInstance();
		Singleton s2 = Singleton.sharedInstance();
		// 해시코드 출력 - 싱글톤 패턴으로 디자인되서 해시코드가 동일
		System.out.println(System.identityHashCode(s1));
		System.out.println(System.identityHashCode(s2));

	}

}
