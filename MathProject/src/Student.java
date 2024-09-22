public class Student {

    private Long mathScore;
    private String name;
    private String school;
    private String address;


    public Student(){

    }
    public Student(String name, String school, String address, Long mathScore){
        this.name = name;
        this.school = school;
        this.address = address;
        this.mathScore = mathScore;
    }
    // 성적 변경
    public void setMathScore(Long score){
        if(score < 0 || score > 100){
            System.out.println("성적값이 올바르지 않습니다. 다시 확인해주세요.");
        }else{
            mathScore = score;
        }
    }

    public String getName(){
        return this.name;
    }
    // 학생정보조회
    public void studentInfo(){
        System.out.println("이름: " + name +  " 수학성적: " + mathScore +  " 학교: " + school +  " 주소: " + address);
    }
}
