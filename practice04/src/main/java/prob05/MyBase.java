package prob05;

public class MyBase extends Base {
	public void service(String state){
		if( state.equals( "낮" ) ) {
			day();
		} else if (state.equals("오후")){
			System.out.println("오후에도 열심히 일해야 합니다.");
		} else {
			night();
		}
	}
	
	public void day(){
		System.out.println("낮에는 열심히 일해야 합니다.");
	}
}
