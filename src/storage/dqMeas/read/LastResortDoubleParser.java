package storage.dqMeas.read;

public class LastResortDoubleParser {

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
