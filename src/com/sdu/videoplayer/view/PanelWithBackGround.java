package com.sdu.videoplayer.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelWithBackGround extends JPanel {
	/**
	 * 一个可以动态加载一个图片做背景的Jpanel
	 */
		Image im;
		// 构造函数制定Jpanel的大小
		public PanelWithBackGround(File file) throws IOException {
			im = ImageIO.read(file);
			// 希望该Panel的大小事自适应的
			//int width = Toolkit.getDefaultToolkit().getScreenSize().width;
			//int height = Toolkit.getDefaultToolkit().getScreenSize().height;
			//this.setSize(width, height);
		}

		// 画出背景
		@Override
		protected void paintComponent(Graphics g) {
			// 清屏
			super.paintComponent(g);
			g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);

		}
	}


