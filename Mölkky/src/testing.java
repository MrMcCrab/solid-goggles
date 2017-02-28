import java.util.Random;

public class testing {
	public static void main(String[] args) {
		Random rand = new Random();
		
		for(int i = 1; i < 10; i++){
			int luku = rand.nextInt(6);
			System.out.println(luku+1);
		}
	
}
}
