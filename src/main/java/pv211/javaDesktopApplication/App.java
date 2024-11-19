package pv211.javaDesktopApplication;
import javafx.application.Application;
import javafx.stage.Stage;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.Dimension;
/**
 * JavaFX App
 */
public class App extends Application {
    @Override
    public void start(Stage stage) {
    	JPanel panel = new JPanel();//generic lightweight container
    	panel.setLayout(new FlowLayout(FlowLayout.LEADING));
    	JButton choice = new JButton("select"), sending = new JButton("send");//keys
    	JFrame jFrame = new JFrame();//addendum support for swing component architecture
    	ActionListener actionListener = new ActList(sending, jFrame);//receiving action events
    	choice.addActionListener(actionListener);
    	JButton[] jButt = { choice, sending };//button array
    	for (int i = 0; i < jButt.length; i++) panel.add(jButt[i]);
    	ImageIcon imageIcon = new ImageIcon("java.png");//painting icons from images
    	jFrame.setIconImage(imageIcon.getImage());
    	jFrame.add(panel);
    	jFrame.setPreferredSize(new Dimension(300, 300));
    	JFrame.setDefaultLookAndFeelDecorated(true);
    	jFrame.pack();
    	jFrame.setLocationRelativeTo(null);
    	jFrame.setVisible(true);
    }
    public static void main(String[] args) {
        launch();
    }

}