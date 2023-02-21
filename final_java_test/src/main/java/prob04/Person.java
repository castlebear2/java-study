package prob04;

public class Person {
	private static int numberOfPerson; // 전체 인구수
	private int age;
	private String name;
	
	/* 코드 작성 */
	public Person(int age, String name) {
		numberOfPerson++;
		this.age = age;
		this.name = name;
	}
	public Person(String name) {
		numberOfPerson++;
		this.name = name;
		this.age = 12;
	}
	
	public void selfIntroduce() {
		System.out.println("내이름은 "+ name +"이며, 나이는 "+ age + "살 입니다.");
	}
	
	public static String getPopulation() {
		return Integer.toString(numberOfPerson);
	}
}
