package test.problem;

public class Test {

	public static void main(String[] args) {
		System.out.println("chay hang tong: " + Tong());

	}
	
	public static int Tong() {
		try {
			System.out.println("Dang trong try");
			return 123;
		} catch (Exception e) {
			return 0;
		}finally {
			System.out.println("Dang trong finally");
		}
	}

}
