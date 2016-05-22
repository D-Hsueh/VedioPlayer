package com.sdu.videoplayer.view.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sdu.videoplayer.heartsupport.VideoPlayHeart;

public class PlayButtonListener implements ActionListener {

	/**
	 * 此类的作用为把播放按钮的监听器转移方便阅读
	 */
	JFrame frame;
	VideoPlayHeart videoPlayHeart;
	JLabel totalTime;
	JButton playButton;

	public PlayButtonListener(JFrame frame, VideoPlayHeart videoPlayHeart, JLabel totalTime, JButton playButton) {
		this.frame = frame;
		this.videoPlayHeart = videoPlayHeart;
		this.totalTime = totalTime;
		this.playButton = playButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (playButton.getText().equals("播放")) {
			videoPlayHeart.PlayVideo();
			frame.setTitle(String.format("Optimus Video Player 正在播放- %s", videoPlayHeart.getTitle()));

			// 更新视频总时长
			if (totalTime.getText().equals("00:00")) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				long tTime = videoPlayHeart.getTotalTime() / 1000;
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
				totalTime.setText(stTime);
			}
			playButton.setText("暂停");
		} else {
			videoPlayHeart.PauseVideo();
			frame.setTitle(String.format("Optimus Video Player 暂停播放- %s", videoPlayHeart.getTitle()));
			playButton.setText("播放");
		}

	}
}
