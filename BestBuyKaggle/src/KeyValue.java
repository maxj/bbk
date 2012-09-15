
public class KeyValue {

	private String string;
	private int count;
	
	
	
	public KeyValue (String s){
		string = s;
		count = 0;
	}
	
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void increase(){
		this.count++;
	}
	
}
