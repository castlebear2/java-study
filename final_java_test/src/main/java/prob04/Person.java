package prob04;

public class Person {
	private static int numberOfPerson; // 전체 인구수
	private int age;
	private String name;
	
	/* 코드 작성 */
	public Person() {
		numberOfPerson++;
		this.age = 12;
		this.name ="";
	}
	public Person(int age, String name) {
		numberOfPerson++;
		this.age = age;
		this.name = name;
	}
	public Person(String name) {
		numberOfPerson++;
		this.age = 12;
		this.name = name;
	}
	
	public void selfIntroduce() {
		System.out.println("내 이름은 " + name + "이며, 나이는 " + age+"살입니다.");
		
	}
	public int getPopulation() {
		return numberOfPerson;
		
	}
}
