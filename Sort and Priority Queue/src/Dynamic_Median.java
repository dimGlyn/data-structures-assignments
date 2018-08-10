import java.io.*;

public class Dynamic_Median {

    private static File f = null;
    private static BufferedReader reader;
    private static String path;
    private static String line;
    private static PQ<Movie> lesser = new PQ<>(4);
    private static PQ<Movie> bigger = new PQ<>(4);

    public static void main(String[] args){

        path = args[0];
        readFile();

    }

    private static void readFile() {

        try {
            f = new File(path);
        } catch (NullPointerException e) {
            System.out.println("There is no such file :(");
        }
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        } catch (FileNotFoundException e) {
            System.out.println("Error!");
        }
        try {

            line = reader.readLine();
            int median = 0;
            int id;
            String title;
            int likes;

            int k=1;

            while(line!=null){

                int i = 1;
                while(true){
                    if((line.substring(0,i).endsWith(" "))) {
                        id = Integer.parseInt(line.substring(0,i).trim());
                        i--;
                        break;
                    }else
                        i++;
                }

                int j = line.length();
                while (true){
                    if((line.substring(j).startsWith(" "))){
                        likes = Integer.parseInt(line.substring(j).trim());
                        break;
                    }else
                        j--;
                }


                title = line.substring(i,j).trim();

                if(k==1) {
                    bigger.insert(new Movie(id, -likes, title));
                }else if (k==2) {
                    lesser.insert(new Movie(id, likes, title));
                }else{
                    if (likes< lesser.Max().getLikes()) {
                        lesser.insert(new Movie(id, likes, title));
                    }else if (likes>Math.abs(bigger.Max().getLikes())){
                        bigger.insert(new Movie(id, -likes, title));
                    }else
                        bigger.insert(new Movie(id, -likes, title));
                }
                //Checks if the two max of bigger is less tha the max of
                // lesser and if so it swaps them.
                if (k > 2) {
                    if (Math.abs(lesser.Max().getLikes()) > Math.abs(bigger.Max().getLikes())) {
                        Movie x = new Movie(lesser.Max().getId(), -lesser.Max().getLikes(), lesser.getMax().getTitle());
                        lesser.insert(new Movie(bigger.Max().getId(), bigger.Max().getLikes(), bigger.getMax().getTitle()));
                        bigger.insert(x);
                    }
                }


                median = bigger.Max().getLikes();


                if ((k+1)%5 == 0) {
                    System.out.println("Current median: "+median+" and is achived by "+bigger.Max().getTitle());
                }

                k++;
                line = reader.readLine();
            }
            
            System.out.println("Final median of the list is: " +median );

        } catch (Exception e){
            System.out.println("Error!!");
            System.out.println(e.toString());
        }
    }

}
