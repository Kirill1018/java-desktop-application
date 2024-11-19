package pv211.javaDesktopApplication;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Dimension;
import java.io.IOException;
import java.lang.NullPointerException;
public class ActList implements ActionListener {
	JButton jbutton = new JButton();//button object
	JFrame frame_ = new JFrame();//frame object
	ActList(JButton button, JFrame shot_) {
		this.jbutton = button;//button assignment
		this.frame_ = shot_;//frame assignment
	}
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();//providing support for standard platform file dialogs
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.showOpenDialog(chooser);
		try {
			ActionListener hearer = new Client(chooser.getSelectedFile().getPath(), chooser.getName(chooser
					.getSelectedFile()), this.frame_);//object of action listener
			this.jbutton.addActionListener(hearer);
			JFrame frame = new JFrame();//frame object
			frame.setTitle(chooser.getName(chooser.getSelectedFile()));
			ImageIcon icon = new ImageIcon("java.png");//painting icons from images
			Image image = ImageIO.read(chooser.getSelectedFile());//representing graphical images
			frame.setIconImage(icon.getImage());
			JLabel jLabel = new JLabel(new ImageIcon(image));//display area for short text string or image
			JPanel jPan = new JPanel();//panel object
			addComp(frame, jPan, jLabel);
			frame.setPreferredSize(new Dimension(300, 300));
			JFrame.setDefaultLookAndFeelDecorated(true);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
		catch(IOException iOException) { }
		catch(NullPointerException nullPointerException) { }
	}
	static void addComp(JFrame shot, JPanel jPanel, JLabel label) {
		jPanel.add(label);
		shot.add(jPanel);
	}
}