package com.wander.wave.plot;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * 绘图面板鼠标监听器
 * 
 * @author RobinTang
 * 
 */
public class PlotPanleMouseListener implements MouseListener, MouseMotionListener {

	private PlotPanle panle = null;
	private int downX;
	private int downY;

	public PlotPanleMouseListener(PlotPanle panle) {
		super();
		this.panle = panle;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int ix = panle.getDrawableObjectByXY(e.getX(), e.getY());
		if (ix >= 0) {
			panle.toggleShow(ix);
		} else if (e.getClickCount() >= 2) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				// change by wander
				// panle.zoomIn(e.getX(), e.getY());
				panle.zoom(1.0f + 0.1, 1.0f);
			} else if (e.getButton() == MouseEvent.BUTTON3) {
				// change by wander
				// panle.zoomOut(e.getX(), e.getY());
				panle.zoom(1.0f - 0.1, 1.0f);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		downX = e.getX();
		downY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.panle.updateUI();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int w = panle.getWidth();
		int h = panle.getHeight();
		double sx = ((double) (downX - x)) / ((double) w);
		double sy = ((double) (y - downY)) / ((double) h);
		this.panle.moveAxis(sx, sy);
		downX = e.getX();
		downY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.panle.setCursorPoint(e.getX(), e.getY());
	}

}
