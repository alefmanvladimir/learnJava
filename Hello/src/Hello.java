
public class Hello {
	public static void main(String[] args) {
		long l = 0L;
		for(int i=0; i<7; i++) {
			byte r = (byte)(1+Math.random()*8);
//			System.out.println(r);
			boolean flag = true;
			for(int j=i; j>=0; j--) {	
				if(r==(byte)(l>>(8*j))) {
					flag = false;
				}
			}
			if(flag){
				System.out.println(r);
				l += r<<(8*i);
			} else {
				i--;
			}
			
		}
		System.out.println(l);		
	}
}