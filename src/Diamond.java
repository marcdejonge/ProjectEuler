public class Diamond {
	public static void main(String[] args) {
		char c = args[0].charAt(0);
		System.out.println(c>='a'?d('a',c-97,-1):d('A',c-65,-1));
	}
	static String d(int c,int x,int y) {
		String line = r(x)+(char)c+(y>0?r(y)+(char)c:"")+"\n";
		return x>0?line+d(c+1,x-1,y+2)+line:line;
	}
	static String r(int n) {
		return n<1?"":" "+r(n-1);
	}
}
