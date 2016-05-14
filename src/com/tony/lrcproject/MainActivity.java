package com.tony.lrcproject;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends Activity {

	private LyricView lyricView;   
    private MediaPlayer mediaPlayer;   
    private Button button;   
    private SeekBar seekBar;   
    private String mp3Path;   
    private int INTERVAL=45;//梧簡耽佩議寂侯    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lyricView = (LyricView) findViewById(R.id.lv_lrc);
		init();
	}
	
	public void doClick(View v)
	{
		lyricView.next();
	}
	
	private void init()
	{
		List<String> lrcs=new ArrayList<String>();
		lrcs.add("及1佩。。。。。。。。。");
		lrcs.add("及2佩。。。。。。。。。");
		lrcs.add("及3佩。。。。。。。。。");
		lrcs.add("及4佩。。。。。。。。。");
		lrcs.add("及5佩。。。。。。。。。");
		lrcs.add("及6佩。。。。。。。。。");
		lrcs.add("及7佩。。。。。。。。。");
		lrcs.add("及8佩。。。。。。。。。");
		lrcs.add("及9佩。。。。。。。。。");
		lrcs.add("及10佩。。。。。。。。。");
		
		lyricView.setLrcs(lrcs);
	}



}
