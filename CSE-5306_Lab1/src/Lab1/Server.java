package Lab1;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

public class Server {
	public static void main(String[] args) {
		ServerGui server = new ServerGui();
		
		/*try { 
			System.out.println("Waiting for clients");
			ServerSocket soc = new ServerSocket(35000);
			Socket sc = soc.accept();
			System.out.println("Connection established");
		} 
		catch (Exception e) { 
			// TODO: handle exception 
			e.printStackTrace(); 
		}*/
	}		
}



class ServerGui extends JFrame  implements ActionListener{
	JLabel serverStart;
	JFrame serverFrame;
	JMenuBar serverMenubar;
	JMenu serverMenu;
	JMenuItem exit;
	ServerGui() {
		// TODO Auto-generated constructor stub
		serverFrame= new JFrame();
		serverMenubar = new JMenuBar();
		serverMenu = new JMenu("File");
		exit = new JMenuItem("Exit");
		
		
		serverFrame.setLayout(new FlowLayout(100, 30, 15)	);
		serverFrame.setVisible(true);
		serverFrame.setSize(500, 500);
		serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		serverFrame.setJMenuBar(serverMenubar);
		serverMenubar.add(serverMenu);
		serverMenu.add(exit);
		
		exit.addActionListener(this);
		
		
		
		try { 
			serverStart = new JLabel(); 
			serverStart.setText("Waiting for the clients");
			serverFrame.add(serverStart);
			ServerSocket soc = new ServerSocket(35000);
			
			
			
				Socket sc = soc.accept();
				DataInputStream din = new DataInputStream(sc.getInputStream());
				
				String userin = din.readUTF();
			//	clientConnected = new JLabel();
				serverStart = new JLabel(userin+" is connected");
				serverFrame.add(serverStart);
				
			//	serverFrame.add(clientConnected);
				soc.close();
			}
				
		
		catch (Exception e) { 
			// TODO: handle exception 
			e.printStackTrace(); 
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()== exit) {
			System.exit(0);
		}
	}
	
}

