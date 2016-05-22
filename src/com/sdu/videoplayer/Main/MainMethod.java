package com.sdu.videoplayer.Main;

import java.io.IOException;

import javax.swing.SwingUtilities;

import com.sdu.videoplayer.view.WelcomeView;

import uk.co.caprica.vlcj.discovery.NativeDiscovery;

public class MainMethod {
	public static void main(final String[] args) {
		new NativeDiscovery().discover();
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new WelcomeView();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}
}


