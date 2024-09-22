import javax.script.SimpleScriptContext;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class School {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();

//        Student student = new Student("정성웅", "새우깡", "광안리", 100L);


        while(true){
            System.out.println("0.학생정보 등록 / 1. 학생정보 조회 / 2. 학생 점수 변경 / 3. 프로그램 종료");
            Scanner sc = new Scanner(System.in);
            int menuNum = sc.nextInt();
            String buffer = sc.nextLine();
            if(menuNum == 3){
                break;
            }
            switch(menuNum) {
                case 0:
                    System.out.print("이름 : ");
                    String name = sc.nextLine();

                    System.out.print("주소 : ");
                    String address = sc.nextLine();

                    System.out.print("학교 :");
                    String school = sc.nextLine();

                    System.out.print("수학점수 : ");
                    Long score = sc.nextLong();

                    Student s = new Student(name, school, address, score);
                    studentList.add(s);

                // 학생정보조회
                case 1:
                    for(int i = 0; i < studentList.size(); i++){
                        studentList.get(i).studentInfo();
                    }

                    break;
                // 학생점수변경
                case 2:
                    System.out.print("*수정할 학생의 이름을 입력해주세요 : ");
                    String editName = sc.nextLine();
                    Student editStudent = null;
                    boolean found = false;
                    for(int i = 0; i< studentList.size(); i++){
                        if(studentList.get(i).getName().equals(editName)){
                            System.out.println("수학점수를 입력해주세요 : ");
                            Scanner sc2 = new Scanner(System.in);
                            Long editScore = sc2.nextLong();

                            studentList.get(i).setMathScore(editScore);
                            System.out.println("학생점수를 " + editScore + "점으로 수정하였습니다.");

                            found = true;
                            break;
                        }
                    }
                    if(found){
                        System.out.println("입력하신 학생의 정보를 찾을 수 없습니다.");
                    }
                    break;
                default:
                    System.out.print("메뉴 번호를 확인해주세요");
            }
        }
    }
}