package com.sdu.videoplayer.view.Listener;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JProgressBar;

import com.sdu.videoplayer.heartsupport.VideoPlayHeart;

public class ProgressBarMouseCleckListener implements MouseListener {

	
	JProgressBar progressbar;
	VideoPlayHeart videoPlayHeart;
	public ProgressBarMouseCleckListener(JProgressBar progressbar,VideoPlayHeart videoPlayHeart)
	{
		this.progressbar=progressbar;
		this.videoPlayHeart=videoPlayHeart;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		float result = (float) x / progressbar.getWidth();
		progressbar.setValue((int) (result * 100));
		long time = (long) (result * videoPlayHeart.getTotalTime());
		videoPlayHeart.GoTo(time);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



}
