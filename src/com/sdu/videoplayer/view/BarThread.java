package com.sdu.videoplayer.view;

import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import com.sdu.videoplayer.heartsupport.VideoPlayHeart;

public class BarThread extends Thread {
	
	
	/**
	 * 在这个类中实现了进度条的变动线程
	 * 通过线程的方式来刷新进度条
	 * */
	private static int DELAY = 100;
	JProgressBar progressBar;
	VideoPlayHeart videoPlayHeart;
	JLabel nowTime;

	public BarThread(JProgressBar bar, VideoPlayHeart videoPlayHeart, JLabel nowTime) {
		progressBar = bar;
		this.videoPlayHeart = videoPlayHeart;
		this.nowTime = nowTime;
	}

	public void run() {
		long nowTime = videoPlayHeart.getNowTime();
		long totalTime = videoPlayHeart.getTotalTime();
		while (!(nowTime >= 0 && totalTime > 0)) {
			nowTime = videoPlayHeart.getNowTime();
			totalTime = videoPlayHeart.getTotalTime();
		}
		while (progressBar.getValue() < 100) {
			nowTime = videoPlayHeart.getNowTime();
			double result = (double) nowTime / totalTime;
			int value = (int) (result * 100);
			progressBar.setValue(value);
			long tTime = videoPlayHeart.getNowTime() / 1000;
			int min = (int) (tTime / 60);
			int hour = min / 60;
			min = min % 60;
			int sec = (int) (tTime % 60);
			String stTime;
			DecimalFormat df = new DecimalFormat("00");
			if (hour > 0) {
				stTime = df.format(hour) + ":" + df.format(min) + ":" + df.format(sec);
			} else if (min > 0) {
				stTime = df.format(min) + ":" + df.format(sec);
			} else {
				stTime = "00:" + df.format(sec);
			}
			this.nowTime.setText(stTime);
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
