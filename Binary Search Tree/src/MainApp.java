import java.util.Scanner;


public class MainApp {

    private static ST st = new ST();
    private static Scanner input = new Scanner(System.in);
    private static String path;
    public static void main(String[] args) {



        while (true) {

            System.out.print("\n" +
                    "Press number to the equivalent action.\n" +
                    "0:EXIT.\n" +
                    "1:Load file with suspects.\n" +
                    "2:Delete a suspect\n" +
                    "3:Search by AFM.\n" +
                    "4:Search by last name.\n" +
                    "5:Get average savings.\n" +
                    "6:Print top suspects.\n" +
                    "7:Print tree sorted by AFM.\n" +
                    "8:Add a new suspect." +
                    "\n\nACTION: ");

            String choice;
            choice = input.nextLine();

            if (choice.equals("0"))
                break;

            switch (choice){
                case "1":
                    System.out.println("Give the filepath:");
                    path = input.nextLine();
                    st.load(path);
                    break;
                case "2":
                    while(true) {
                        System.out.println("Enter the AFM of the Suspect: ");
                        try {
                            path = input.nextLine();
                            if (st.searchByAFM(Integer.parseInt((path)))!=null){
                                st.remove(st.searchByAFM(Integer.parseInt(path)));
                            }else {
                                System.out.println("Suspect not found.");
                            }
                            break;
                        }catch (Exception e){
                            System.out.println("Wrong AFM format.");
                        }
                    }
                    break;
                case "3":
                    while(true) {
                        System.out.println("Enter the AFM of the Suspect: ");
                        try {
                            path = input.nextLine();
                            if (st.searchByAFM(Integer.parseInt(path))!=null){
                                System.out.println(st.searchByAFM(Integer.parseInt(path)).toString());
                            }else {
                                System.out.println("Suspect not found");
                            }
                            break;
                        }catch (Exception e){
                            System.out.println("Wrong AFM format.");
                        }
                    }
                    break;

                case "4":
                    System.out.println("Enter the last name of the Suspect:");
                    path = input.nextLine();
                    List list=st.searchByLastname(path);
                    if (list.isEmpty()) {
                        System.out.println("Suspect not found.");
                    }else {
                        System.out.println(list.toString());
                    }

                    break;

                case "5":
                    System.out.println("The numer of average savings is: " + st.getMeanSavings());
                    break;
                case "6":
                    while (true) {
                        System.out.println("Enter the number of the Suspects that you want to see:");
                        path = input.nextLine();
                        try {
                            st.printTopSuspects(System.out, Integer.parseInt(path));
                        }catch (NumberFormatException nfe){
                            System.out.println("This is not a number.");
                        }
                        break;
                    }
                    break;
                case "7":
                    st.printTreeByAFM(System.out);
                    break;
                case "8":
                    int AFM;
                    String first;
                    String last;
                    double savings;
                    double taxed_income;
                    while (true){
                        System.out.print("Enter the suspects AFM: ");
                        try {
                            String afm = input.nextLine();
                            AFM = Integer.parseInt(afm);
                            if(st.searchByAFM(AFM)==null) {
                                break;
                            }
                            System.out.println("Suspect with given AFM already exists.");
                        }catch(Exception e){
                            System.out.println("Error! Type a number.");
                        }
                    }
                    while (true){
                        System.out.print("Enter the suspects first name: ");
                        try {
                            first = input.nextLine();
                            break;
                        }catch(Exception e){
                            System.out.println("Error!");
                        }
                    }
                    while (true){
                        System.out.print("Enter the suspects last name: ");
                        try {
                            last = input.nextLine();
                            break;
                        }catch(Exception e){
                            System.out.println("Error!");
                        }
                    }
                    while (true){
                        System.out.print("Enter the suspects savings: ");
                        try {
                            savings = Double.parseDouble(input.nextLine());
                            break;
                        }catch(Exception e){
                            System.out.println("Error! Try again.");
                        }
                    }
                    while (true){
                        System.out.print("Enter the suspects taxed income: ");
                        try {
                            taxed_income = Double.parseDouble(input.nextLine());
                            break;
                        }catch(Exception e){
                            System.out.println("Error! Type a number.");
                        }
                    }
                    Suspect s = new Suspect(AFM, first, last, savings, taxed_income);
                    int method;
                    while (true){
                        System.out.print("In what method do you want to add the suspect in the tree?\n" +
                                "(1 for simple insert, 2 for insert at root): ");
                        try {
                            String usin = input.nextLine();
                            method = Integer.parseInt(usin);
                            if (method == 1) {
                                st.insert(s);
                            }else if (method == 2) {
                                st.insert_at_root(s);
                            }else {
                                System.out.println("Wrong input. Must be 1 or 2");
                                continue;
                            }
                            break;
                        }catch(Exception e){
                            System.out.println("Error! Type 1 or 2.");
                        }
                    }
                    break;
                default:
                    System.out.println("Sorry wrong input... Try again.");
            }


        }

    }
}
