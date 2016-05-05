import java.util.Scanner;
import javax.swing.JOptionPane;

public class MyInput
{

    private static Scanner javain = new Scanner(System.in);

    public static int inputInt (String message) {
        System.out.print(message);
        return Integer.parseInt(javain.nextLine().trim());
    }

    public static double inputDouble (String message) {
        System.out.print(message);
        return Double.parseDouble(javain.nextLine().trim());
    }

    public static String inputString (String message) {
        System.out.print(message);
        return javain.nextLine().trim();
    }
    
    public static int JOptionPaneInt (String message) {
        String valueString = JOptionPane.showInputDialog(null, message , "Input", JOptionPane.PLAIN_MESSAGE);
        return Integer.parseInt(valueString.trim());
    }
    
    public static double JOptionPaneDouble (String message) {
        String valueString = JOptionPane.showInputDialog(null, message , "Input", JOptionPane.PLAIN_MESSAGE);
        return Double.parseDouble(valueString.trim());
    }
}
