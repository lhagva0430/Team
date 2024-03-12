package sample;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import sample.GamePanel.MyKeyAdapter;

public class helpPanel extends JPanel implements ActionListener{
	static final int SCREEN_WIDTH = 500;
    static final int SCREEN_HEIGHT = 350;
    JButton b1;
    public helpPanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
