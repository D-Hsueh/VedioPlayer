package com.sdu.videoplayer.view.Listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JProgressBar;

import com.sdu.videoplayer.heartsupport.VideoPlayHeart;

public class ProgressBarMouseMotionListener  implements MouseMotionListener{


	JProgressBar progressbar;
	VideoPlayHeart videoPlayHeart;
	public ProgressBarMouseMotionListener(JProgressBar progressbar,VideoPlayHeart videoPlayHeart)
	{
		this.progressbar=progressbar;
		this.videoPlayHeart=videoPlayHeart;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(20);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int x = e.getX();
		float result = (float) x / progressbar.getWidth();
		progressbar.setValue((int) (result * 100));
		long time = (long) (result * videoPlayHeart.getTotalTime());
		videoPlayHeart.GoTo(time);
	}

}
