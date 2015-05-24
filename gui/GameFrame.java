package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GameFrame {
	private static JPanel board = new BoardPanel();
	
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
	
	private static void createAndShowGUI() {
        JFrame f = new JFrame("Risk");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel menu = new JPanel();
        menu.setFocusable(false);

		f.setLayout(new BorderLayout());
		f.add(board, BorderLayout.NORTH);
		f.getContentPane().setSize(750,540);
        f.pack();
        f.setVisible(true);
    }
}