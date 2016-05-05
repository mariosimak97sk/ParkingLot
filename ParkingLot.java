import java.util.Scanner;
import java.util.Random;

public class ParkingLot
{

    private String name;
    private int capacity;
    private Car[] places;
    private int numOfOccupied;

    public ParkingLot(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        numOfOccupied = 0;
        places = new Car[capacity];
    }

    public int getRandom() {
        int random;
        do {
            // od 1 az po places.length -1
            random = new Random().nextInt(places.length);
        } while (places[random] != null);
        return random;
    }

    public void enter () {

        if(numOfOccupied < capacity) {
            System.out.println("You can enter.");
        }else{
            System.out.println("The ParkingLot is full.");
        }

        if(numOfOccupied < capacity) {
            int number = getRandom();
            places[number] = new Car(MyInput.JOptionPaneInt("Enter date: "), MyInput.JOptionPaneDouble("Enter time: "));
            System.out.println("Your number is: " + number);
            numOfOccupied++;
        }
    }

    public void pay (int numOfCar) {

        try{
            places[numOfCar].payment(MyInput.JOptionPaneInt("Enter endDate: "), MyInput.JOptionPaneDouble("Enter endTime: "));
            places[numOfCar].setPrice(0);
            System.out.println("Paid. You can now leave.");
            //return true;
        }
        catch(NullPointerException npe){
            System.out.println("No such car in the ParkingLot.");
            //return false;
        }
        catch(ArrayIndexOutOfBoundsException aioobe){
            System.out.println("No such car in the ParkingLot.");
            //return false;
        }
    }

    public void leave (int numOfCar) {
        /* The machine checks whether there is a car at the place which is
         * selected by the driver. If there is a car, then the machine checks if the car is allowed to leave. A car is
         * allowed to leave if it leaves maximum 10 minutes after the payment was made. */
        try{
            if(places[numOfCar] != null && places[numOfCar].leave(MyInput.JOptionPaneInt("Enter endDate: "), MyInput.JOptionPaneDouble("Enter endTime: ")) ) {
                places[numOfCar] = null;
                numOfOccupied--;
                System.out.println("You left the ParkingLot.");
            }else{
                System.out.println("You have to pay at first.");
            }
        }
        catch(NullPointerException npe){
            System.out.println("No such car in the ParkingLot.");
        }
        catch(ArrayIndexOutOfBoundsException aioobe){
            System.out.println("No such car in the ParkingLot.");
        }
    }

    public void menu () {
        int input;

        do {
            System.out.println("");
            System.out.println(" 1. Enter the Parking Lot \n 2. Leave the Parking Lot \n 3. Make a payment  \n 4. Display the list of cars \n 5. Quit the menu \n");
            input = MyInput.inputInt("Select an option from the menu: ");

            switch (input) {
                case 1:  
                enter();
                break;

                case 2:  
                if(numOfOccupied != 0) {
                    leave(MyInput.inputInt("Fill in a number of your car: "));
                }
                break;

                case 3:  
                pay(MyInput.inputInt("Fill in a number of your car: "));
                break;

                case 4:  
                for(int i = 0; i < capacity; i++) {
                    if(places[i] != null) System.out.println(i + " : " + places[i].toString());
                }
                break;

                default: 
                System.out.println("Exit.");
                break;
            }
        } while (input != 5);
    }
}
