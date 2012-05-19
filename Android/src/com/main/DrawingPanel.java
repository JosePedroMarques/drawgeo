package com.main;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class DrawingPanel extends View implements OnTouchListener {

	private Canvas mCanvas;
	private Path mPath;
	private ArrayList<Paint> mPaints;
	private Paint mPaint;
	private ArrayList<Path> paths = new ArrayList<Path>();
	private Boolean clean = false;
	private int currentColor= Color.BLACK;
	private int strokeWidth = 6; 
	private ArrayList<Integer> pathsByPaint;

	public DrawingPanel(Context context) {
		super(context);
		setFocusable(true);
		setFocusableInTouchMode(true);
		this.setOnTouchListener(this);
		
		pathsByPaint = new ArrayList<Integer>();
		mPaints = new ArrayList<Paint>();
		mCanvas = new Canvas();
		mCanvas.drawColor(Color.WHITE);
		
		createPaint();
	}
	
	//cria uma nova instancia paint, que guardar� os desenhos
	private void createPaint() {
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(currentColor);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(strokeWidth);
		mPaints.add(mPaint);
		mPath = new Path();
		paths.add(mPath);
		pathsByPaint.add(1);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		// limpa o ecr� se a flag estiver ativa
		if(clean) {
			paths.clear();
			pathsByPaint.clear();
			mPaints.clear();
			createPaint();
			clean = false;
		}
		else {
			//desenha cada paint com a sua cor
			int acum = 0;
			for(int j = 0; j < mPaints.size(); j++) {
				for(int n = pathsByPaint.get(j); n > 0; n--) {
					canvas.drawPath(paths.get(acum++), mPaints.get(j));
				}
			}
		}
	}

	private float mX, mY;
	private static final float TOUCH_TOLERANCE = 4;

	private void touch_start(float x, float y) {
		mPath.reset();
		mPath.moveTo(x, y);
		mX = x;
		mY = y;
	}

	private void touch_move(float x, float y) {
		float dx = Math.abs(x - mX);
		float dy = Math.abs(y - mY);
		if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
			mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
			mX = x;
			mY = y;
		}
	}

	private void touch_up() {
		mPath.lineTo(mX, mY);
		// envia o path para o canvas de background 
		mCanvas.drawPath(mPath, mPaint);
		// elimina o caminho atual, para n�o desenhar duas vezes o mesmo
		mPath = new Path();
		paths.add(mPath);
		pathsByPaint.set(pathsByPaint.size()-1, pathsByPaint.get(pathsByPaint.size()-1)+1);
	}

	public boolean onTouch(View arg0, MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		
		// switch para os eventos relacionados com o toque no ecr�
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touch_start(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			touch_move(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			touch_up();
			invalidate();
			break;
		}
		return true;
	}
	
	/*@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
	    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	        // regista inten��o de limpar o canvas na pr�xima atualiza��o
	    	clean = true;
	    	this.invalidate();
	    }
	    return true;
	}*/

	public void cleanCanvas() {
		clean = true;
		this.invalidate();
	}
	
	public void eraseMode() {
		currentColor = Color.WHITE;
		strokeWidth = 12;
		
		// � criado um novo paint com a cor a branco, que serve para apagar
		createPaint();
	}
	
	public void changeColor(int color) {
		currentColor = color;
		strokeWidth = 6;
		
		// � criado um novo paint com a nova cor seleccionada
		createPaint();
	}
}
