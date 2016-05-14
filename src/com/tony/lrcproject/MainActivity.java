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
    private int INTERVAL=45;//���ÿ�еļ��    
	
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
		lrcs.add("��1�С�����������������");
		lrcs.add("��2�С�����������������");
		lrcs.add("��3�С�����������������");
		lrcs.add("��4�С�����������������");
		lrcs.add("��5�С�����������������");
		lrcs.add("��6�С�����������������");
		lrcs.add("��7�С�����������������");
		lrcs.add("��8�С�����������������");
		lrcs.add("��9�С�����������������");
		lrcs.add("��10�С�����������������");
		
		lyricView.setLrcs(lrcs);
	}



}
