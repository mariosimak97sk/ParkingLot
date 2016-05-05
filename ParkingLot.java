import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class ParkingLot extends JFrame implements ActionListener
{
    private String name, dateString;
    private int capacity, numOfOccupied, dateInt;
    private Car[] places;
    private String[] date;
    private JButton btEnter, btLeave, btPay, btDisplay, btExit;
    private JTextArea display;
    private JLabel lbName, lbCapacity;
    //javax.swing.Timer myTimer;
    long duration;

    public ParkingLot(String name, int capacity) {
        super(name + " | Mario Simak");
        this.setLayout(null);
        this.name = name;
        this.capacity = capacity;
        numOfOccupied = 0;
        places = new Car[capacity];
        //myTimer = new javax.swing.Timer(500,this);
        //myTimer.stop();

        //TEXTAREA
        display = new JTextArea();
        JScrollPane sp = new JScrollPane(display);
        sp.setBounds(130, 50, 340, 190);
        add(sp);

        //CALENDAR
        Calendar cal = Calendar.getInstance();
        String[] aparts = cal.getTime().toString().split(" ");
        String[] atime = aparts[3].split(":");
        date = new String[3];
        date[2] = Integer.toString(cal.get(Calendar.YEAR));
        date[1] = Integer.toString(cal.get(Calendar.MONTH)+1);
        date[0] = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
        dateString = date[0] + date[1] + date[2];
        dateInt = Integer.parseInt(dateString);

        //BUTTONS
        btEnter = new JButton("Enter");
        btEnter.setBounds(20, 50, 100, 30);
        add(btEnter);
        btLeave = new JButton("Leave");
        btLeave.setBounds(20, 90, 100, 30);
        add(btLeave);
        btPay = new JButton("Pay");
        btPay.setBounds(20, 130, 100, 30);
        add(btPay);
        btDisplay = new JButton("Display");
        btDisplay.setBounds(20, 170, 100, 30);
        add(btDisplay);
        btExit = new JButton("Exit");
        btExit.setBounds(20, 210, 100, 30);
        add(btExit);

        //ACTIONLISTENER
        btEnter.addActionListener(this);
        btLeave.addActionListener(this);
        btPay.addActionListener(this);
        btDisplay.addActionListener(this);
        btExit.addActionListener(this);

        //LABELS
        lbName = new JLabel(name);
        lbName.setBounds(20, 20, 100, 30);
        add(lbName);
        lbCapacity = new JLabel("Capacity: " + numOfOccupied + "/" + capacity);
        lbCapacity.setBounds(130, 20, 100, 30);
        add(lbCapacity);

        /*LOGO
        ImageIcon logo = new ImageIcon("C:/Users/Mario Simak/Downloads/bhi_parking_banner.jpg");
        JLabel lbLogo = new JLabel("Hit boxes", logo, JLabel.CENTER);
        lbLogo.setBounds(0, 0, 480, 150);
        this.add(lbLogo);
         */
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
            display.append("You can enter." + "\n");

        }else{
            display.append("The ParkingLot is full." + "\n");
            btEnter.setEnabled(false);
        }

        if(numOfOccupied < capacity) {
            int number = getRandom();
            numOfOccupied++;
            lbCapacity.setText("Capacity: " + numOfOccupied + "/" + capacity);
            places[number] = new Car(dateInt, MyInput.JOptionPaneDouble("Enter time: "));
            display.append("Your number is: " + number + "\n");
        }
    }

    public void pay () {

        try{
            //MyInput.JOptionPaneDouble("Enter endTime: ")
            int numOfCar = MyInput.JOptionPaneInt("Enter numOfCar: ");
            places[numOfCar].payment(dateInt, MyInput.JOptionPaneDouble("Enter endTime: "));
            places[numOfCar].setPrice(0);
            display.append("Paid: " + places[numOfCar].getPrice() +  " You can now leave." + "\n");
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

    public void leave () {

        try{    
            int numOfCar2 = MyInput.JOptionPaneInt("Enter numOfCar: ");
            if(places[numOfCar2] != null && places[numOfCar2].leave(dateInt, MyInput.JOptionPaneDouble("Enter endTime: ")) ) {
                places[numOfCar2] = null;
                numOfOccupied--;
                display.append("You left the ParkingLot." + "\n");
            }else{
                display.append("You have to pay at first." + "\n");
            }
        }
        catch(NullPointerException npe){
            System.out.println("No such car in the ParkingLot.");
        }
        catch(ArrayIndexOutOfBoundsException aioobe){
            System.out.println("No such car in the ParkingLot.");
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btEnter) {
            enter();
        }else if(ae.getSource() == btLeave) {
            if(numOfOccupied != 0) {
                leave();
            }
        }else if(ae.getSource() == btPay) {
            pay();
        }else if(ae.getSource() == btDisplay) {

            for(int i = 0; i < capacity; i++) {
                if(places[i] != null)  display.append((i + " : " + places[i].toString())+ "\n");
            }

        }else if(ae.getSource() == btExit) {
            int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to quit?","Confirm Quit", JOptionPane.YES_NO_CANCEL_OPTION);
            if (result == JOptionPane.YES_OPTION) System.exit(0);
        }

        /* if(ae.getSource() == myTimer){
        myTimer.stop();
        System.out.println(myTimer);
        myTimer.start();
        }
         */
    }

    public static void main() {
        ParkingLot frame = new ParkingLot(MyInput.JOptionPaneString("Name of ParkingLot: "), MyInput.JOptionPaneInt("Capacity: "));
        frame.setSize(500, 400);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}
