package Lab1;

import java.awt.FlowLayout;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

public class Client {
	public static void main(String[] args) {
		Gui g = new Gui();
	}
}


class Gui extends JFrame implements ActionListener{
	Socket soc;
	DataOutputStream dout;
	JFrame frame, updateJFrame;
	JTextField user, for_commands, command, directoryName;
	JLabel Username,output,file_generated,makeFile;
	JButton Ok,commandEnter;
	JMenuBar menubar;
	JMenuItem exit;
	JMenu File;
	File file;
	String User;
	
	Gui() {
		frame = new JFrame();
		menubar = new JMenuBar();
		File = new JMenu("File");
		exit = new JMenuItem("Exit");
		Username = new JLabel("Username: ");
		output = new JLabel();
		file_generated = new JLabel();
		user = new JTextField(10);
		Ok = new JButton("Enter");
		
		
		for_commands = new JTextField(20);
		
		frame.setLayout(new FlowLayout(100, 30, 15)	);
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menubar);
		menubar.add(File);
		
		File.add(exit);
		frame.add(Username);
		frame.add(user);
		frame.add(Ok);
		
		frame.add(output);
		frame.add(file_generated);
		
		Ok.addActionListener(this);
		exit.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()== exit) {
			System.exit(0);
		}
		if(ae.getSource()== Ok) {
			try {
                makeHomeDirectory();
                processInformation();
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
			
			
			//makeHomeDirectory();
		}
		
		if(ae.getSource() == commandEnter) {
			try {
				directoryInHomeDirectory();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * public void makedirectory() { // TODO Auto-generated method stub
	 * add(for_commands); }
	 */
	
	public void processInformation() throws UnknownHostException, IOException {
		soc = new Socket("localhost",35000);
		String name = user.getText();
	    dout = new DataOutputStream(soc.getOutputStream());
	    dout.writeUTF(name);
	    dout.flush();
	    dout.close();
	    updateGui();
	    
	    // Here we read the details from server
		/*
		 * BufferedReader response = new BufferedReader(new InputStreamReader(
		 * soc.getInputStream())); txtS.setText("The server respond: " +
		 * response.readLine());
		 */
	  
	}
	
	public void updateGui() {
		updateJFrame = new JFrame();
		menubar = new JMenuBar();
		File = new JMenu("File");
		exit = new JMenuItem("Exit");
		JLabel forCommand = new JLabel("Command: ");
		command = new JTextField(10);
		command.setBounds(200, 50, 150, 40);
		JLabel dirName = new JLabel("Name of Directory:");
		directoryName = new JTextField(10);
		commandEnter = new JButton("Enter");
		
		updateJFrame.setLayout(new FlowLayout(300, 100, 15)	);
		updateJFrame.setVisible(true);
		updateJFrame.setSize(500, 500);
		updateJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		updateJFrame.setJMenuBar(menubar);
		menubar.add(File);
		File.add(exit);
		updateJFrame.add(forCommand);
		updateJFrame.add(command);
		updateJFrame.add(dirName);
		updateJFrame.add(directoryName);
		updateJFrame.add(commandEnter);
		
		// Calling action listener
		commandEnter.addActionListener(this);
		exit.addActionListener(this);
		
		
	}
	
	// TO make a home directory
	public void makeHomeDirectory() {
		try {
			//System.out.println("Client Started");
			User = user.getText();
			output.setText("Welcome"+User);
			file =new File("D:\\Study\\Semester-3(Fall 2020)\\Distributed Systems\\CSE-5306\\Lab\\Lab-1\\"+User).getAbsoluteFile();
			if(file.exists()) {
				file_generated.setText("Your Directory is already present at this path---D:\\Study\\Semester-3(Fall 2020)\\Distributed Systems\\CSE-5306\\Lab\\Lab-1");
			}
			else {
				file.mkdir();
				file_generated.setText("Your Directory has been generated at this path---D:\\Study\\Semester-3(Fall 2020)\\Distributed Systems\\CSE-5306\\Lab\\Lab-1");
			}
		
		FileSystemView	fsv = new HomeDirectory(file);
		JFileChooser chooser = new JFileChooser(fsv);
		
		chooser.setFileSystemView(fsv);
		
		chooser.showOpenDialog(this);
		chooser.setCurrentDirectory(file);
		
		
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void directoryInHomeDirectory() throws IOException, StringIndexOutOfBoundsException {
		String filepath;
		String textCommand = command.getText().toLowerCase();
		String Name = directoryName.getText();
		
		
		if(textCommand.matches("make")) {
			filepath = file.getAbsolutePath()+"\\"+Name;
			file = new File(filepath);
			file.mkdir();
			makeFile = new JLabel("Your new Directory generated");
			updateJFrame.add(makeFile);
			updateGui();
		}
		try {
		if(textCommand.matches("delete")) {
			if(file.getName().matches(User)) {
				System.out.println("No Directory to delete");
			}
			else if(file.getAbsolutePath().substring(1).matches(Name)) {
				System.out.println("Then its cool");
			}
			System.out.println(file.getAbsolutePath().substring(0, -1).matches(Name));
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		dout.close();
	}
}



