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
	 * һ�����Զ�̬����һ��ͼƬ��������Jpanel
	 */
		Image im;
		// ���캯���ƶ�Jpanel�Ĵ�С
		public PanelWithBackGround(File file) throws IOException {
			im = ImageIO.read(file);
			// ϣ����Panel�Ĵ�С������Ӧ��
			//int width = Toolkit.getDefaultToolkit().getScreenSize().width;
			//int height = Toolkit.getDefaultToolkit().getScreenSize().height;
			//this.setSize(width, height);
		}

		// ��������
		@Override
		protected void paintComponent(Graphics g) {
			// ����
			super.paintComponent(g);
			g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);

		}
	}


