package storage.dqMeas.read;

public class LastResortDoubleParser {
	
	public static void main(String[] args) {
		String[] tests = {"1234,567", "1234.567", "1,234.567", "1234,567"};
		for (String s: tests) {
			System.out.println(Double.toString(parse(s)));
		}
	}

	private LastResortDoubleParser() {
		// TODO Auto-generated constructor stub
	}
	
	public static double parse (String content) {
		
		//System.out.println("Reading "+content);
		
		String doubleStrIn = new String(content);
	    doubleStrIn = content.replaceAll("[^\\d,\\.]++", "");
	    if (doubleStrIn.matches(".+\\.\\d+,\\d+$"))
	        return Double.parseDouble(doubleStrIn.replaceAll("\\.", "").replaceAll(",", "."));
	    if (doubleStrIn.matches(".+,\\d+\\.\\d+$"))
	        return Double.parseDouble(doubleStrIn.replaceAll(",", ""));
	    return Double.parseDouble(doubleStrIn.replaceAll(",", "."));
	}
	
	

}
