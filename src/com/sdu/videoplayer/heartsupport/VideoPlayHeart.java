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
	
	/** 设置视频地址*/
	public void setURL(String VideoURL) {
		this.VideoURL = VideoURL;
		mediaPlayerComponent.getMediaPlayer().prepareMedia(VideoURL);
		canPlay = true;
	}
	
	/** 播放视频*/
	public void PlayVideo() {
		if (canPlay) {
			mediaPlayerComponent.getMediaPlayer().play();
			canPause=true;
		}
	}
	
	/** 暂停视频*/
	public void PauseVideo()
	{
		if (canPause)
		{
			mediaPlayerComponent.getMediaPlayer().pause();
		}
	}
	
	/**获得视频的标题*/
	public String getTitle()
	{
		return mediaPlayerComponent.getMediaPlayer().getMediaMeta().getTitle();
	}
	
	/**获得视频的长度*/
	public long getTotalTime()
	{
		return mediaPlayerComponent.getMediaPlayer().getLength();
	}
	
	/**获得视频播放到的位置*/
	public long getNowTime()
	{
		return mediaPlayerComponent.getMediaPlayer().getTime();
	}
	
	/**跳转到指定位置*/
	public void GoTo(long time)
	{
		mediaPlayerComponent.getMediaPlayer().setTime(time);
	}
	
	/**快进/快退*/
	public void SkipTo(long time)
	{
		mediaPlayerComponent.getMediaPlayer().skip(time);
	}
	
	/**检查文件是否已经准备就绪*/
	public boolean CanPlay()
	{
		return canPlay;
	}
	
	/**截图
	 * @return */
	public BufferedImage ScreenShot()
	{
		return mediaPlayerComponent.getMediaPlayer().getSnapshot();
	}
	
	/**设置声音*/
	public void setVolum(int date)
	{
		mediaPlayerComponent.getMediaPlayer().setVolume(date);
	}
	
	/**停止播放*/
	public void StopPlay()
	{
		mediaPlayerComponent.getMediaPlayer().setTime(0);
		mediaPlayerComponent.getMediaPlayer().stop();
	}
}
