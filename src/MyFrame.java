import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyFrame extends JFrame implements ActionListener {

	JButton doIt;
	JPanel upperPanel;
	JTextField line1;
	JTextField line2;

	MyFrame(){
		doIt = new JButton();
		doIt.setBounds(170, 75, 50, 25);
		ImageIcon icon = new ImageIcon("C:\\Users\\Георги\\Desktop\\tu2.png");
		JFrame frame = new JFrame();
		
		line1 = new JTextField();
		line1.setBounds(50, 50, 100, 25);
		line2 = new JTextField();
		line2.setBounds(50, 100, 100, 25);
//		line1.setPreferredSize(new Dimension(10,25));

		
		frame.setTitle("Fun File operations");
		frame.setVisible(true);
		frame.setSize(720,520);
//		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(123,50,250));
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		line1.setPreferredSize(new Dimension(10,25));
		frame.add(line1);
		frame.add(doIt);
		frame.add(line2);
		
//		frame.add(line2);
//		frame.pack();


		
//		upperPanel = new JPanel();
//		upperPanel.setBackground(new Color(123,50,200));		
//		upperPanel.setPreferredSize(new Dimension(720,100));
//		
//		frame.add(upperPanel,BorderLayout.NORTH);
//		upperPanel.add(open);
		
//		open.setText("Click to load file");
//        open.addActionListener(e -> {
//            selectFile();
//        });

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==doIt) {
			
		}
	}

//	 public void selectFile() {
//	        JFileChooser chooser = new JFileChooser();
//	        // optionally set chooser options ...
//	        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
//	            File f = chooser.getSelectedFile();
//	            // read  and/or display the file somehow. ....
//	        } else {
//	            // user changed their mind
//	        }
//	    }
	
}
