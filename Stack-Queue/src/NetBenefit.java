import java.io.*;

public class NetBenefit {

    private  static IntQueueImpl queue = new IntQueueImpl();

    private static String path;
    private static File f = null;
    private static BufferedReader reader;

    public static void main(String[] args ){

        path = args[0];

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


        findBenefit();

    }

    public static void findBenefit(){

        int benefit = 0;

        int stocks;
        int price;
        try{


            String line = reader.readLine();
            while(line != null) {

                if (line.startsWith("buy")){
                    int i = 1;
                    while(true){

                        if((line.substring(3,3+i).trim().endsWith("p"))) {
                            i--;
                            break;
                        }else
                            i++;
                    }

                    stocks = Integer.parseInt(line.substring(3,3+i).trim());
                    price = Integer.parseInt(line.substring(8+i).trim());

                    for (int j = 0; j <stocks; j++) {

                        queue.put(price);

                    }

                }else if(line.startsWith("sell")){
                    int i = 1;
                    while (true){

                        if((line.substring(4,4+i).trim().endsWith("p"))){
                            i--;
                            break;
                        }else
                            i++;
                    }

                    stocks = Integer.parseInt(line.substring(4,4+i).trim());
                    price = Integer.parseInt(line.substring(9+i).trim());

                    for (int j = 0; j < stocks; j++) {
                        benefit += price - queue.get();
                    }

                }

                line = reader.readLine();
            }

            if(benefit<0)
                System.out.println("The damage is: " + benefit + ".");
            else if(benefit==0)
                System.out.println("The transactions had no profit.");
            else
                System.out.println("The profit is: " + benefit + ".");

        }catch(Exception e){

            System.out.println("An error has occured.");

        }

    }


}
