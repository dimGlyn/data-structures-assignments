import java.io.*;

public  class TagMatching {

	private static File f = null;
	private static StringStackImpl tagsStack = new StringStackImpl();
	private static BufferedReader reader;
	private static String path;

	public static void main(String[] args){

		path = args[0];

		try {
			f = new File(path);
		} catch (NullPointerException e) {
			System.out.println("There is no such file :(");
		}

		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

			if(Check())
				System.out.println("The tags are matched! ");
			else
				System.out.println("The tags are not matched! ");

		} catch (FileNotFoundException e) {
			System.out.println("Error!");
		}



	}


	private static boolean Check(){

		try{

			String tagname="";
			int r;

			while ((r = reader.read()) != -1) {
				if(((char) r)=='<'){
					tagname += (char)r;
					while(((char) r)!='>'){
						r = reader.read();
						tagname+= (char)r;
					}

					if (!tagname.contains("/")){
						tagsStack.push(tagname);
					}else{
						if(tagsStack.peek().equals(tagname.replace("/", ""))){
							tagsStack.pop();
						}
					}


				}
				tagname="";
			}


		}catch(Exception e){
			System.out.println("The file does not exists or it's empty");

		}

		return tagsStack.isEmpty();
	}


}