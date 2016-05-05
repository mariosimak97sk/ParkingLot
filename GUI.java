import javax.swing.JFrame;
import javax.swing.*; // vsetky classy
import java.awt.event.*;
import java.util.*;

public class GUI extends JFrame implements ActionListener
{

    private JButton btSize, bt2;
    private JLabel lSize, l2;
    private int count;
    Random generate;
    private final int STEP = 50;

    public GUI(String heading) {
        super(heading); // super must be declared at first
        this.setSize(500, 300);
        this.setLayout(null); // absolute layout, musime to nastavit
        generate = new Random();
        int x = generate.nextInt(2*STEP+1) - STEP;

        /*btSize = new JButton("Display the size of the frame.");
        btSize.setBounds(20, 40, 300, 100);
        this.add(btSize);

        btSize.addActionListener(this);

        lSize = new JLabel("Mario is the king.");
        lSize.setBounds(20, 100, 300, 100);
        this.add(lSize);
         */

        bt2 = new JButton("Click me!");
        bt2.setBounds(20, 20, 200, 40);
        this.add(bt2);

        bt2.addActionListener(this);

        count = 0;

        l2 = new JLabel("clicks: " + count);
        l2.setBounds(20, 100, 300, 100);
        this.add(l2);

    }

    public void actionPerformed(ActionEvent ae) { // ae name of the object ActionEvent, objects of type event
        if(ae.getSource() == btSize) {
            lSize.setText(this.getWidth() + ":" + this.getHeight());
        }

        if(ae.getSource() == bt2) {
            /*count++;
            l2.setText("clicks: " + count);
             */

            /*bt2.getX() + ;
            bt2.getY() + ;
            bt2.setLocation();
             */
        }
    }

    public static void main() {
        GUI frame1 = new GUI("First Frame");// nevideli by sme ho na obrazovke
        frame1.setVisible(true);
        frame1.setLocation(10, 10);

        /*GUI frame2 = new GUI("Second Frame");// nevideli by sme ho na obrazovke
        frame2.setVisible(true);
        frame2.setLocation(450, 10);
         */
    }
}
