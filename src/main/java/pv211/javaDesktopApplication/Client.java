package pv211.javaDesktopApplication;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.io.*;
import java.awt.event.ActionEvent;
import java.net.Socket;
public class Client implements ActionListener {
	String address = new String(), behalf = new String();//path and name
	JFrame image_ = new JFrame();//frame object
	static DataOutputStream dataOutputStream = null;//application writing primitive java data types to output stream in portable way
	static DataInputStream dataInputStream = null;//application using data output stream to write data that can later be read by data input stream
	Client(String path, String sake, JFrame frame) {
		this.address = path;//setting value to address
		this.behalf = sake;//setting value to name
		this.image_ = frame;//setting value to frame
	}
	public void actionPerformed(ActionEvent e) {
		this.image_.dispose();
		String dirAddr = "name.txt";//file name
		try (Socket socket = new Socket("192.168.1.146", 900)) {//implementation sockets
			FileOutputStream fileOutputStream = new FileOutputStream(dirAddr);//output stream for writing data to file or file descriptor
			fileOutputStream.write(this.behalf.getBytes());
			fileOutputStream.flush();
			fileOutputStream.close();
			dataInputStream = new DataInputStream(socket.getInputStream());//stream object of data input
			dataOutputStream = new DataOutputStream(socket.getOutputStream());//stream object of data output
			String[] files = { dirAddr, this.address };//file array
			for (int i = 0; i < files.length; i++) sendFile(files[i]);
			dataInputStream.close();
		}
		catch(Exception exception) { }
	}
	static void sendFile(String url) throws Exception {
		int bytes = 0;//bytes for file stream
		File file = new File(url);//abstract representation of file and directory pathnames
		FileInputStream fileInputStream = new FileInputStream(file);//obtaining input bytes from file in file system
		dataOutputStream.writeLong(file.length());
		byte[] buffer = new byte[4 * 1024];//byte array for file stream
		while ((bytes = fileInputStream.read(buffer)) != -1) {
			dataOutputStream.write(buffer, 0, bytes);
			dataOutputStream.flush();
		}
		fileInputStream.close();
	}
}