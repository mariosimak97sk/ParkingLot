
public class CarLP extends Car
{
 
    private String licencePlate;
    public static final String DEFAULT_LP = "no name";
    
    public CarLP(int entryDate, double entryTime, String lp)
    {
        super(entryDate, entryTime);
        setLicencePlate(lp);
    }
    
    private void setLicencePlate(String licencePlate) {
        if(licencePlate == null || licencePlate == "") {
            this.licencePlate = DEFAULT_LP;
        }else{
            this.licencePlate = licencePlate;
        }
    }
    
    public String getLicencePlate() {
        return licencePlate;
    }
    
    public String toString() {
        String s = super.toString();
        return s + "; " + getLicencePlate();
    }
}
