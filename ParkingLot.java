import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Toolkit;
import java.awt.Color;

public class ParkingLot extends JFrame implements ActionListener
{
    private String name, dateString;
    private int capacity, numOfOccupied, dateInt;
    private Car[] places;
    private String[] date, numbers;
    private JButton btEnter, btLeave, btPay, btDisplay, btExit, btClear;
    private JTextArea display;
    private JLabel lbName, lbCapacity, lbDate, lbTime;
    private static long timerStart, elapsedTime;
    javax.swing.Timer myTimer;

    public ParkingLot(String name, int capacity) {
        super(name + " | Mario Simak");
        this.setLayout(null);
        this.name = name;
        this.capacity = capacity;
        numOfOccupied = 0;
        places = new Car[capacity];
        setIconImage(Toolkit.getDefaultToolkit().getImage("img/parkingLotIcon.png"));
        myTimer = new javax.swing.Timer(500,this);
        myTimer.stop();

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
        numbers = new String[capacity];
        date[2] = Integer.toString(cal.get(Calendar.YEAR));
        date[1] = Integer.toString(cal.get(Calendar.MONTH)+1);
        date[0] = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
        dateString = date[0] + date[1] + date[2];
        dateInt = Integer.parseInt(dateString);

        //BUTTONS
        btEnter = new JButton("Enter");
        btEnter.setBackground(Color.WHITE);
        btEnter.setBounds(20, 50, 100, 30);
        add(btEnter);
        btLeave = new JButton("Leave");
        btLeave.setBackground(Color.WHITE);
        btLeave.setBounds(20, 90, 100, 30);
        add(btLeave);
        btPay = new JButton("Pay");
        btPay.setBackground(Color.WHITE);
        btPay.setBounds(20, 130, 100, 30);
        add(btPay);
        btDisplay = new JButton("Display");
        btDisplay.setBackground(Color.WHITE);
        btDisplay.setBounds(20, 170, 100, 30);
        add(btDisplay);
        btExit = new JButton("Exit");
        btExit.setBackground(Color.WHITE);
        btExit.setBounds(20, 210, 100, 30);
        add(btExit);
        btClear = new JButton("Clear");
        btClear.setBackground(Color.WHITE);
        btClear.setBounds(370, 250, 100, 30);
        add(btClear);

        //ACTIONLISTENER
        btEnter.addActionListener(this);
        btLeave.addActionListener(this);
        btPay.addActionListener(this);
        btDisplay.addActionListener(this);
        btExit.addActionListener(this);
        btClear.addActionListener(this);

        //LABELS
        lbName = new JLabel(name);
        lbName.setBounds(20, 20, 100, 30);
        add(lbName);
        lbCapacity = new JLabel("Capacity: " + numOfOccupied + "/" + capacity);
        lbCapacity.setBounds(130, 20, 100, 30);
        add(lbCapacity);
        lbDate = new JLabel("Date: " + date[0] + "/" + date[1] + "/" + date[2]);
        lbDate.setBounds(310, 20, 100, 30); 
        add(lbDate);
        lbTime = new JLabel("Time: " + getTime());
        lbTime.setBounds(400, 20, 100, 30);
        add(lbTime);
    }

    public int getRandom() {
        int random;
        do {
            random = new Random().nextInt(places.length);
        } while (places[random] != null);
        return random;
    }

    public void enter () {

        if(numOfOccupied < capacity) {
        }else{
            display.append("The ParkingLot is full." + "\n");
            btEnter.setEnabled(false);
        }

        if(numOfOccupied < capacity) {
            int number = getRandom();
            numbers[number] = Integer.toString(number);
            places[number] = new Car(dateInt, this.getTime());
            display.append("Your number is: " + number + "\n");
            numOfOccupied++;
            lbCapacity.setText("Capacity: " + numOfOccupied + "/" + capacity);
        }
    }

    public void pay () {

        try{
            int numOfCar = displayList();
            //elapsedTime = System.currentTimeMillis()-places[numOfCar].getEndTime();
            places[numOfCar].payment(dateInt, this.getTime());
            places[numOfCar].setPrice(0);
            display.append("Paid: " + places[numOfCar].getPrice() +  " You can now leave." + "\n");
        }
        catch(NullPointerException npe){
            JOptionPane.showMessageDialog(null, npe.getMessage(), "No such car in the ParkingLot.", JOptionPane.ERROR_MESSAGE);
        }
        catch(ArrayIndexOutOfBoundsException aioobe){
            JOptionPane.showMessageDialog(null, aioobe.getMessage(), "No such car in the ParkingLot.", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void leave () {

        try{   
            int numOfCar2 = displayList();
            if(places[numOfCar2] != null && places[numOfCar2].leave(dateInt, this.getTime()) ) {
                places[numOfCar2] = null;
                numOfOccupied--;
                display.append("You left the ParkingLot." + "\n");
                lbCapacity.setText("Capacity: " + numOfOccupied + "/" + capacity);
                numbers[numOfCar2] = null;
            }else{
                display.append("You have to pay at first." + "\n");
            }
        }
        catch(NullPointerException npe){
            JOptionPane.showMessageDialog(null, npe.getMessage(), "No such car in the ParkingLot.", JOptionPane.ERROR_MESSAGE);
        }
        catch(ArrayIndexOutOfBoundsException aioobe){
            JOptionPane.showMessageDialog(null, aioobe.getMessage(), "No such car in the ParkingLot.", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btEnter) {
            enter();
            if(numOfOccupied == capacity) {
                btEnter.setEnabled(false);
            }
        }else if(ae.getSource() == btLeave) {
            if(numOfOccupied != 0) {
                leave();
                btEnter.setEnabled(true);
            }
        }else if(ae.getSource() == btPay) {
            pay();
        }else if(ae.getSource() == btDisplay) {

            for(int i = 0; i < capacity; i++) {
                if(places[i] != null)  display.append((i + " : " + places[i].toString())+ "\n");
            }

        }else if(ae.getSource() == btExit) {
            int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to quit?","Confirm Quit", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.YES_OPTION) System.exit(0);
        }else if(ae.getSource() == btClear) {
            display.setText("");
        }

        if(ae.getSource() == myTimer){
        }
    }

    public static void main(String args[]) {
        ParkingLot frame = new ParkingLot(MyInput.JOptionPaneString("Name of ParkingLot: "), MyInput.JOptionPaneInt("Capacity: "));
        frame.setSize(500, 400);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    public int displayList() {
        String input = (String) JOptionPane.showInputDialog(null, "What is the number of your Car?", "Selection", JOptionPane.QUESTION_MESSAGE, null, numbers, numbers[0]);
        return Integer.parseInt(input);
    }

    public static double getTime() {
        timerStart = System.currentTimeMillis();
        int minutes = (int) ((timerStart / (1000*60)) % 60);
        int hours = (int) ((timerStart / (1000*60*60)) % 24);
        return Double.parseDouble(hours + "." + minutes);
    }
}
