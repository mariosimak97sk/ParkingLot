public class Car
{
    private double entryTime, endTime, price, currentTime, elapsedTime;
    private int entryDate, endDate, currentDate;

    public Car(int entryDate, double entryTime) {
        this.entryDate = entryDate;
        this.entryTime = entryTime;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    //GETTERS
    public int getEntryDate() { return entryDate; }

    public double getEntryTime() { return entryTime; }

    public double getPrice() { return price; } 

    public int getEndDate() { return endDate; }

    public double getEndTime() { return endTime; };

    //setters - modifiers
    public void setEndDate(int endDate) {
        //validation required
        if(endDate >= entryDate) {
            this.endDate = endDate;
        }
    }

    public void setEndTime(double endTime) {
        //validation required
        if(endTime >= entryTime || endDate > entryDate) {
            this.endTime = endTime;
        }
    }

    //nezalezi na objektoch, preto moze byt pouzita na hocicom
    public static int timeInMinutes(double Time) {
        //local variables
        //static fucntion, if not using object variables
        int h = (int)Time;
        int m = (int)((Time-h)*100);
        return  (h*60) + m;
    }

    private double calcPrice() {
        //local variables
        //change because of repayment
        int entryTime = timeInMinutes(this.entryTime); 
        int endTime = timeInMinutes(this.endTime);
        int timeDifference = endTime - entryTime;
        return ((timeDifference /30) * 0.5);
    }

    public double payment(int endDate, double endTime) {
        setEndDate(endDate);
        setEndTime(endTime);
        price = calcPrice();
        return price;

    }

    public boolean leave(int currentDate, double currentTime) {
        int entryMin = timeInMinutes(entryTime);
        int currentMin = timeInMinutes(currentTime);
        int endMin = timeInMinutes(endTime);
        //when paying endTime i set to a value, it is not 0
        if(currentMin - endMin < 10 && endTime != 0) return true;
        if(endTime == 0 && currentMin - entryMin < 10) return true;
        return false;   
    }

    public String toString() {
        return "entryTime: " + entryTime + "; entryDate: " + entryDate + "; endTime: " + endTime + "; endDate: " + endDate + "; price: " + price;
    }
}

