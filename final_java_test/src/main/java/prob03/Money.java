package prob03;

public class Money {
	private int amount;
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Money(int amount) {
		this.amount = amount;
	}
	public Money add(Money m) {
		
		return new Money(this.amount + m.amount);
	}
	public Money divide(Money m) {
		
		return new Money(this.amount / m.amount);
	}
	public Money multiply(Money m) {
		
		return new Money(this.amount * m.amount);
	}
	public Money minus(Money m) {
		
		return new Money(this.amount - m.amount);
	}
	@Override
	public boolean equals(Object m) {
		// TODO Auto-generated method stub
		Money mo = (Money) m;
		if(mo.amount == this.amount) return true;
		else return false;
	} 
	
	/* 코드 작성 */
}
