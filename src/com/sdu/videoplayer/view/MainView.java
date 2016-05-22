package com.sdu.videoplayer.view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sdu.videoplayer.heartsupport.VideoPlayHeart;
import com.sdu.videoplayer.view.Listener.PlayButtonListener;
import com.sdu.videoplayer.view.Listener.ProgressBarMouseCleckListener;
import com.sdu.videoplayer.view.Listener.ProgressBarMouseMotionListener;

import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

public class MainView {

	protected final JFrame frame;

	private VideoPlayHeart videoPlayHeart;

	// protected final JButton pauseButton;

	protected final JButton playButton;

	protected final JButton reviewButton;

	protected final JButton stopButton;

	protected final JButton skipButton;

	protected final JButton volumControlButton;

	protected final JProgressBar progressbar;

	protected final JMenuBar menuBar;

	protected final JMenu fileMenu;

	protected final JMenuItem openFile;

	protected final JLabel nowTime;

	protected final JLabel totalTime;

	protected final JSlider volumControlSlider;

	public MainView() throws IOException {

		frame = new JFrame("Optimus Video Player");
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// mediaPlayerComponent.release();
				System.exit(0);
			}
		});

		// �˵���
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		// �ļ���˵�
		fileMenu = new JMenu("�ļ�");
		menuBar.add(fileMenu);
		// ���ļ�
		openFile = new JMenuItem("���ļ�");
		fileMenu.add(openFile);
		openFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jfc.showDialog(new JLabel(), "ѡ��");
				// jfc.showDialog(new JLabel(), "ѡ��");
				File file = jfc.getSelectedFile();
				if (file.isFile()) {
					videoPlayHeart.setURL(file.getAbsolutePath());
					playButton.setText("����");
					progressbar.setValue(0);
					frame.setTitle(String.format("Optimus Video Player ׼������- %s", videoPlayHeart.getTitle()));

				}
			}

		});

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());

		// ������
		JPanel vedioPlayPanel = new JPanel();
		vedioPlayPanel.setLayout(new BorderLayout());
		videoPlayHeart = new VideoPlayHeart();
		vedioPlayPanel.add(videoPlayHeart.getEmbeddedMediaPlayerComponent(), BorderLayout.CENTER);
		contentPane.add(vedioPlayPanel, BorderLayout.CENTER);

		// ������
		JPanel controlsPane = new PanelWithBackGround(new File("./img/background.jpg"));
		controlsPane.setLayout(new BorderLayout());
		// ��ť��
		JPanel buttoncontrolPanel = new JPanel();
		buttoncontrolPanel.setOpaque(false);
		buttoncontrolPanel.setLayout(new FlowLayout());
		// pauseButton = new JButton("��ͣ");
		// buttoncontrolPanel.add(pauseButton);
		stopButton = new JButton("ֹͣ");
		buttoncontrolPanel.add(stopButton);
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				videoPlayHeart.StopPlay();
				playButton.setText("����");
				progressbar.setValue(0);
			}
		});

		reviewButton = new JButton("����");
		buttoncontrolPanel.add(reviewButton);
		reviewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				videoPlayHeart.SkipTo(-10000);
			}
		});
		playButton = new JButton("����");
		buttoncontrolPanel.add(playButton);
		skipButton = new JButton("���");
		buttoncontrolPanel.add(skipButton);
		skipButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				videoPlayHeart.SkipTo(10000);
			}
		});
		volumControlButton = new JButton("����");
		buttoncontrolPanel.add(volumControlButton);
		volumControlButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (volumControlButton.getText().equals("����")) {
					volumControlButton.setText("����");
					volumControlSlider.setValue(0);
				} else {
					volumControlButton.setText("����");
					volumControlSlider.setValue(60);
				}
			}
		});

		volumControlSlider = new JSlider();
		volumControlSlider.setOpaque(false);
		volumControlSlider.setPaintLabels(true);
		volumControlSlider.setSnapToTicks(true);
		volumControlSlider.setPaintTicks(true);
		volumControlSlider.setValue(100);
		volumControlSlider.setMaximum(120);
		volumControlSlider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int date = (int) (e.getX() * ((float) volumControlSlider.getMaximum() / volumControlSlider.getWidth()));
				volumControlSlider.setValue(date);
			}
		});
		volumControlSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				videoPlayHeart.setVolum(volumControlSlider.getValue());
			}
		});
		buttoncontrolPanel.add(volumControlSlider);

		controlsPane.add(buttoncontrolPanel, BorderLayout.SOUTH);

		// ���������������ʱ������
		nowTime = new JLabel("00:00");
		nowTime.setForeground(Color.WHITE);
		controlsPane.add(nowTime, BorderLayout.WEST);

		totalTime = new JLabel("00:00");
		totalTime.setForeground(Color.WHITE);
		controlsPane.add(totalTime, BorderLayout.EAST);

		// ������
		progressbar = new JProgressBar();
		progressbar.setOrientation(JProgressBar.HORIZONTAL);
		progressbar.setMinimum(0);
		progressbar.setMaximum(100);
		progressbar.setValue(0);
		progressbar.setStringPainted(true);
		// progressbar.addChangeListener(this);
		progressbar.setPreferredSize(new Dimension(300, 20));
		progressbar.setBorderPainted(true);
		progressbar.setBackground(Color.WHITE);
		// �����������������߳�,ʵ�ֽ�������ˢ��
		Thread stepper = new BarThread(progressbar, videoPlayHeart, nowTime);
		stepper.start();
		// ������������������¼�
		progressbar.addMouseListener(new ProgressBarMouseCleckListener(progressbar, videoPlayHeart));
		// ��������϶��¼�
		progressbar.addMouseMotionListener(new ProgressBarMouseMotionListener(progressbar, videoPlayHeart));
		controlsPane.add(progressbar, BorderLayout.CENTER);

		contentPane.add(controlsPane, BorderLayout.SOUTH);

		playButton.addActionListener(new PlayButtonListener(frame, videoPlayHeart, totalTime, playButton));
		videoPlayHeart.getEmbeddedMediaPlayerComponent().getMediaPlayer()
				.addMediaPlayerEventListener(new MediaPlayerEventAdapter() {

					@Override
					public void finished(MediaPlayer mediaPlayer) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								videoPlayHeart.StopPlay();
								playButton.setText("����");
								progressbar.setValue(0);
							}
						});
					}
				});
		frame.setContentPane(contentPane);
		frame.setVisible(true);

	}
}