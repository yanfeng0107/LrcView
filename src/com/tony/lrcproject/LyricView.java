package com.tony.lrcproject;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;

/**
 * 滚动效果显示歌词，调用next方法显示某行
 * @author 
 *
 */
public class LyricView extends View {

	/**
	 * 存放每一行歌词的String集合
	 */
	private List<String> lrcs;
	private int width;
	private int height;
	
	/**
	 * 当前显示的行的索引
	 */
	private int index;
	
	private Paint normalPaint;
	
	private Paint specialPaint;
	
	private Scroller mScroller;
	
	/**
	 * 字体大小
	 */
	private int textSize;
	
	/**
	 * 滚动距离，设置两个字体大小的距离
	 */
	private int gap;
	private Context context;

	/**
	 * 记录当前滚动到的Y坐标
	 */
	private int currentY;

	public LyricView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	private void init() {
		lrcs = new ArrayList<String>();
		index = 0;
		textSize = 24;
		gap = textSize * 2;
		mScroller = new Scroller(context);
		currentY = 0;

		normalPaint = new Paint();
		normalPaint.setColor(0xaabbbbbb);
		normalPaint.setTextSize(textSize);
		normalPaint.setTextAlign(Align.CENTER);

		specialPaint = new Paint();
		specialPaint.setColor(0xc00076a8);
		specialPaint.setTextSize(textSize);
		specialPaint.setTextAlign(Align.CENTER);
	}

	/**
	 * 从某个点滚动到另一个
	 * @param startX	
	 * @param startY
	 * @param endX
	 * @param endY
	 */
	private void scrollToPosition(int startX, int startY, int endX, int endY) {
		scroll(endX - startX, endY - startY);
	}

	/**
	 * 从当前点滚动一段距离
	 * @param dx	X轴上的位移
	 * @param dy	Y轴上的位移
	 */
	private void scroll(int dx, int dy) {
		//获取当前点位置
		int cx = mScroller.getCurrX();
		int cy = mScroller.getCurrY();
		//滚动
		mScroller.startScroll(cx, cy, dx, dy, 500);
		invalidate();
	}

	//滚动实现的方法，Scroller类提供滚动中每个时间点应处于的位置，计算每次的位置，如果滚动未完成，则滚动到新的位置
	@Override
	public void computeScroll() {
		super.computeScroll();
		if (mScroller.computeScrollOffset()) {
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			postInvalidate();
		}
	}

	/**
	 * 滚动到下一行歌词
	 */
	public void next() {
		index++;
		next(index);
	}

	/**
	 * 滚动到指定行
	 * @param index 指定行的索引
	 */
	public void next(int index) {
		if (lrcs != null && lrcs.size() > 0) {
			int startY = currentY;
			this.index = index;
			this.index = this.index % lrcs.size();
			int endY = this.index * gap;
			currentY = endY;
			scrollToPosition(0, startY, 0, endY);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {

		super.onSizeChanged(w, h, oldw, oldh);
		this.width = w;
		this.height = h;
	}

	/*
	 * 显示当前歌词，先画出当前要高亮的行，然后画出其他行
	 * 
	 */
	@Override
	protected void onDraw(Canvas canvas) {

		try {

			if (canvas != null) {
				if (lrcs != null && lrcs.size() > 0) {
					int centerX = width / 2;
					int centerY = height / 2;
					int num = lrcs.size();
					canvas.drawText(lrcs.get(index), centerX, centerY + gap * index, specialPaint);

					for (int i = num-1; i >=0; i--) {
						if (i == index) {
							continue;
						}
						canvas.drawText(lrcs.get(i), centerX, centerY + gap * (i), normalPaint);
					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}

	}

	public int getTextSize() {
		return textSize;
	}

	public void setTextSize(int textSize) {
		this.textSize = textSize;
		this.gap = textSize * 2;
	}

	public List<String> getLrcs() {
		return lrcs;
	}

	public Paint getNormalPaint() {
		return normalPaint;
	}

	public void setNormalPaint(Paint normalPaint) {
		this.normalPaint = normalPaint;
	}

	public Paint getSpecialPaint() {
		return specialPaint;
	}

	public void setSpecialPaint(Paint specialPaint) {
		this.specialPaint = specialPaint;
	}

	public void setLrcs(List<String> lrcs) {
		this.lrcs = lrcs;
	}

}
