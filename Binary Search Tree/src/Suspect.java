public class Suspect {

    private long AFM;
    private String first_name;
    private String last_name;
    private double savings;
    private double taxed_income;

    public Suspect(int AFM, String first_name, String last_name, double savings, double taxed_income) {
        this.AFM = AFM;
        this.first_name = first_name;
        this.last_name = last_name;
        this.savings = savings;
        this.taxed_income = taxed_income;
    }


    public long key() {
        return AFM;
    }

    private String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public double getSavings() {
        return savings;
    }

    public double getTaxed_income() {
        return taxed_income;
    }

    /*
    Used to find the top suspects.
     */
    public double getDifference(){
        return savings-taxed_income;
    }

    @Override
    public String toString() {
        return "Suspect{" +
                "AFM=" + AFM +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", savings=" + savings +
                ", taxed_income=" + taxed_income +
                '}';
    }
}