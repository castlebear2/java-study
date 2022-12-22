package prob02;

public class Book {
	private int bookNo;
	private String title;
	private String author;
	private int stateCode;

	public Book(int bookNo, String title, String author) {
		this.bookNo = bookNo;
		this.title = title;
		this.author = author;
		this.stateCode = 1;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	public void displayBookInfo() {

	}

	public void rent(int bookNo) {
		this.stateCode = 0;
		System.out.println(title + "가 대여되었습니다!");

	}
	public void print() {
		if(stateCode==1) {
			System.out.println("책번호: "+bookNo+" 제목:"+title+ " 작가: "+author+ " 상태: 대여 가능");
		}else {
			System.out.println("책번호: "+bookNo+" 제목:"+title+ " 작가: "+author+ " 상태: 대여중");
		}
		
	}

}
