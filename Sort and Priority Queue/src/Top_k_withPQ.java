import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;



public class Top_k_withPQ{

    private static PQ<Movie> heap;
    private static File f = null;
    private static BufferedReader reader;
    private static String path;
    private static String line;
    private static int number;
    public static void main(String[] args) throws Exception {

        path = args[0];

        userInput();

        readFile();

        int size = heap.size();

        try {

            System.out.println("The top "+number+" movies are (in reverse order): ");

            for (int i = 0; i < number; i++) {
                Movie item = heap.getMax();
                System.out.println(number-i+" : " + item.getTitle() +" | Likes : "+(-item.getLikes()) );
            }

        }catch(Exception e){
            System.out.println("The list had only "+size+" movies.");
        }



    }

    private static void readFile(){
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

            int id;
            String title;
            int likes;



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


                Movie m=new  Movie(id,-likes,title);
                if(heap.size()<number) {
                    heap.insert(m);
                }else{
                    if(m.compareTo(heap.Max()) > 0) {
                        heap.getMax();
                        heap.insert(m);
                    }
                }

                line = reader.readLine();

            }

        } catch (Exception e){
            System.out.println("Error!!");
        }




    }


    private static void userInput(){

        Scanner input = new Scanner(System.in);

        System.out.print("Type the number of the movies tha you want to appear on the screen :");

        number=input.nextInt();

        heap =new PQ<Movie>(number);


    }
}
