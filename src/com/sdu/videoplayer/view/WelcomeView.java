package com.sdu.videoplayer.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.discovery.NativeDiscovery;

public class WelcomeView extends JFrame {

	public WelcomeView() throws IOException {
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JPanel welcomePanel = new WelcomePanel();
		welcomePanel.setLayout(new BorderLayout());
		JTextArea welcomeText = new JTextArea("\n\n            »¶Ó­Ê¹ÓÃ\n  Optimus Video Player");
		welcomeText.setOpaque(false);
		welcomeText.setForeground(Color.white); 
		welcomeText.setFont(new java.awt.Font("Dialog", 1, 35));
		welcomeText.setEditable(false);
		welcomePanel.add(welcomeText);
		this.add(welcomePanel);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// mediaPlayerComponent.release();
				System.exit(0);
			}
		});
		Timer timer = new Timer();
		timer.schedule(new MyTask(this), 0, 1000);

		// setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

class MyTask extends TimerTask {
	int count = 0;
	JFrame jframe;

	public MyTask(JFrame jframe) {
		this.jframe = jframe;
	}

	@Override
	public void run() {
		count++;
		if (count > 3) {
			new NativeDiscovery().discover();
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					try {
						new MainView();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			jframe.dispose();
			this.cancel();
		}

	}

}
