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
 * ����Ч����ʾ��ʣ�����next������ʾĳ��
 * @author 
 *
 */
public class LyricView extends View {

	/**
	 * ���ÿһ�и�ʵ�String����
	 */
	private List<String> lrcs;
	private int width;
	private int height;
	
	/**
	 * ��ǰ��ʾ���е�����
	 */
	private int index;
	
	private Paint normalPaint;
	
	private Paint specialPaint;
	
	private Scroller mScroller;
	
	/**
	 * �����С
	 */
	private int textSize;
	
	/**
	 * �������룬�������������С�ľ���
	 */
	private int gap;
	private Context context;

	/**
	 * ��¼��ǰ��������Y����
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
	 * ��ĳ�����������һ��
	 * @param startX	
	 * @param startY
	 * @param endX
	 * @param endY
	 */
	private void scrollToPosition(int startX, int startY, int endX, int endY) {
		scroll(endX - startX, endY - startY);
	}

	/**
	 * �ӵ�ǰ�����һ�ξ���
	 * @param dx	X���ϵ�λ��
	 * @param dy	Y���ϵ�λ��
	 */
	private void scroll(int dx, int dy) {
		//��ȡ��ǰ��λ��
		int cx = mScroller.getCurrX();
		int cy = mScroller.getCurrY();
		//����
		mScroller.startScroll(cx, cy, dx, dy, 500);
		invalidate();
	}

	//����ʵ�ֵķ�����Scroller���ṩ������ÿ��ʱ���Ӧ���ڵ�λ�ã�����ÿ�ε�λ�ã��������δ��ɣ���������µ�λ��
	@Override
	public void computeScroll() {
		super.computeScroll();
		if (mScroller.computeScrollOffset()) {
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			postInvalidate();
		}
	}

	/**
	 * ��������һ�и��
	 */
	public void next() {
		index++;
		next(index);
	}

	/**
	 * ������ָ����
	 * @param index ָ���е�����
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
	 * ��ʾ��ǰ��ʣ��Ȼ�����ǰҪ�������У�Ȼ�󻭳�������
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
