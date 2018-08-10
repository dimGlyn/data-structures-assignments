import java.io.*;
import java.util.Scanner;

public class Top_k{

    private static File f = null;
    private static BufferedReader reader;
    private static BufferedReader lineCounterReader;
    private static String path;
    private static String line;
    private static Movie movies [];

    public static void main(String [] args){

        path = args[0];

        readFile();

        Scanner input =new Scanner(System.in);

        System.out.print("Type the number of the movies tha you want to appear on the screen : ");

        int number=input.nextInt();

        quickSort(0, movies.length - 1);

        try {

            System.out.println("The top "+number+" movies are : ");

            for (int i = 1; i <= number; i++) {
                System.out.println(i + ": " +movies[movies.length - i].getTitle()+ " | Likes: "+movies[movies.length-i].getLikes());
            }

        }catch(Exception e){
            System.out.println("The list had only "+movies.length+" movies.");
        }
    }

    public static void readFile(){

        try {
            f = new File(path);
        } catch (NullPointerException e) {
            System.out.println("There is no such file :(");
        }

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            lineCounterReader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

        } catch (FileNotFoundException e) {
            System.out.println("Error!");
        }
        try {

            int lines = 0;
            while (lineCounterReader.readLine() != null)
                lines++;
            lineCounterReader.close();

            movies = new Movie[lines];

            line = reader.readLine();

            int id;
            String title;
            int likes;

            int x = 0;

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

                movies[x] = new Movie(id,likes,title);

                line = reader.readLine();
                x++;
            }

        } catch (Exception e){
            System.out.println("Error!!");
        }

    }

    private static void swap(int left, int right) {

        if(movies[left].getLikes()==movies[right].getLikes()){

            int comp=movies[left].getTitle().compareTo(movies[right].getTitle());

            if(comp>0){
                return;
            }
        }
        Movie temp = movies[right];
        movies[right] = movies[left];
        movies[left] = temp;
    }

    private static void quickSort(int left, int right){

        int l=left;
        int r=right;
        int pivot=movies[(int)left+(right-left)/2].getLikes();

        while(l<=r){

            while(movies[l].getLikes()<pivot){
                l++;
            }

            while(movies[r].getLikes()>pivot){
                r--;
            }

            if(l<=r){
                swap(l,r);
                l++;
                r--;
            }

            if(left<r)
                quickSort(left, r);
            if(l<right)
                quickSort(l, right);


        }
    }


}
