package com.sdu.videoplayer.heartsupport;

import java.awt.image.BufferedImage;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

public class VideoPlayHeart {

	private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
	private String VideoURL;
	
	private boolean canPlay;
	private boolean canPause;

	public VideoPlayHeart() {
		new NativeDiscovery().discover();
		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		
		canPlay = false;
		canPause=false;
	}

	public EmbeddedMediaPlayerComponent getEmbeddedMediaPlayerComponent() {
		return mediaPlayerComponent;
	}
	
	/** ������Ƶ��ַ*/
	public void setURL(String VideoURL) {
		this.VideoURL = VideoURL;
		mediaPlayerComponent.getMediaPlayer().prepareMedia(VideoURL);
		canPlay = true;
	}
	
	/** ������Ƶ*/
	public void PlayVideo() {
		if (canPlay) {
			mediaPlayerComponent.getMediaPlayer().play();
			canPause=true;
		}
	}
	
	/** ��ͣ��Ƶ*/
	public void PauseVideo()
	{
		if (canPause)
		{
			mediaPlayerComponent.getMediaPlayer().pause();
		}
	}
	
	/**�����Ƶ�ı���*/
	public String getTitle()
	{
		return mediaPlayerComponent.getMediaPlayer().getMediaMeta().getTitle();
	}
	
	/**�����Ƶ�ĳ���*/
	public long getTotalTime()
	{
		return mediaPlayerComponent.getMediaPlayer().getLength();
	}
	
	/**�����Ƶ���ŵ���λ��*/
	public long getNowTime()
	{
		return mediaPlayerComponent.getMediaPlayer().getTime();
	}
	
	/**��ת��ָ��λ��*/
	public void GoTo(long time)
	{
		mediaPlayerComponent.getMediaPlayer().setTime(time);
	}
	
	/**���/����*/
	public void SkipTo(long time)
	{
		mediaPlayerComponent.getMediaPlayer().skip(time);
	}
	
	/**����ļ��Ƿ��Ѿ�׼������*/
	public boolean CanPlay()
	{
		return canPlay;
	}
	
	/**��ͼ
	 * @return */
	public BufferedImage ScreenShot()
	{
		return mediaPlayerComponent.getMediaPlayer().getSnapshot();
	}
	
	/**��������*/
	public void setVolum(int date)
	{
		mediaPlayerComponent.getMediaPlayer().setVolume(date);
	}
	
	/**ֹͣ����*/
	public void StopPlay()
	{
		mediaPlayerComponent.getMediaPlayer().setTime(0);
		mediaPlayerComponent.getMediaPlayer().stop();
	}
}
