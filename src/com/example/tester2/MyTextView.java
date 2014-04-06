package com.example.tester2;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class MyTextView extends TextView {
	
	
	/**x
	 * Custom TextView-
	 * - 2 text fields with 2 different drawing settings (Color/font)
	 * - 2 custom attributes (first_name and last_name)
	 * @author prasanta
	 *
	 */
	
		Context context;
		String firstText = "Phil";
		String lastText = "Whitwell";
		int pad = 5;
		String TAG = getClass().getName();
		Paint mPaint = new Paint();
		Paint mPaintB = new Paint();
		
		public MyTextView(Context context, AttributeSet attrs) {
			super(context, attrs);
			this.context = context;
		Log.i(TAG, "MyTextView(ctxt, attrs)");
			Log.i(TAG, "Attributes...");
			/*for(int i=0; i<attrs.getAttributeCount(); i++){
				Log.i(TAG, attrs.getAttributeName(i));
				
				 //Read value of custom attributes
				 
				this.firstText = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.my", "first_name");
				this.lastText = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.my", "last_name");
				Log.i(TAG, "firstText "+ firstText);
				Log.i(TAG, "lastText "+ lastText);
			}*/

			init();
		}
		
		public MyTextView(Context context){
			super(context);
			this.context = context;
			Log.i(TAG, "MyTextView(ctxt)");
			init();
		}

		private void init(){
			setPadding(0, 0, 0, 8);
			// set Size
			mPaint.setTextSize(16);
			// set Color- blue
		    mPaint.setColor(0xFF0000FF);
		    mPaintB.setTextSize(16);
		    // set Color- green
		    mPaintB.setColor(0xFF00FF00);
		    // set font to Bold
			mPaintB.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
		}
		
		public void setText(String firstText, String lastText){
			System.out.println("firstText "+ firstText);
			System.out.println("lastText "+ lastText);
			this.firstText = firstText;
			this.lastText = lastText;
			// request for re-draw- calls draw()
			invalidate();
		}
		
		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			int reqWidth;
			int reqHeight;
			Log.i(TAG, "onMeasure");
			// width & height measure specification
			Log.i(TAG, "widthMeasureSpec="+ widthMeasureSpec +" heightMeasureSpec="+ heightMeasureSpec);
			
			// width & height mode
			int widthMode = MeasureSpec.getMode(widthMeasureSpec);
			int heightMode = MeasureSpec.getMode(heightMeasureSpec);
			// specified width & height
			int widthSize = MeasureSpec.getSize(widthMeasureSpec);
			int heightSize = MeasureSpec.getSize(heightMeasureSpec);
			
			// find out Width based on widthMode
			if(widthMode == MeasureSpec.EXACTLY)
				// set user specified Width
				reqWidth = widthSize;
			else
				// find out the total pixel size required for first and last text
				reqWidth = (int)(mPaint.measureText(firstText) + mPaintB.measureText(lastText) + 3*pad);
			
			// find out Height based on heightMode
			if(heightMode == MeasureSpec.EXACTLY)
				// set user specified Height
				reqHeight = heightSize;
			else
				// get the default height of the Font
				reqHeight = (int) mPaintB.getTextSize();
			
			Log.i(TAG, "reqWidth="+ reqWidth +" reqHeight="+ reqHeight);
			// set the calculated width and height of your drawing area
			setMeasuredDimension(reqWidth, reqHeight);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			Log.i(TAG, "--> onDraw");
			Log.i(TAG, "W="+ canvas.getWidth() +" H="+ canvas.getHeight());
			int x = getLeft();
			int y = getTop();
			Log.i(TAG, "x="+ x +" y="+ y);
			
			// first text
			Log.i(TAG, "draw first text");
			
			canvas.drawText(firstText, x, y, mPaint);
			
			// shift to next word position = (width of the first text) + padding
			x += mPaint.measureText(firstText) + pad;
			
			Log.i(TAG, "X="+ x +" y="+ y);
			// last text
			Log.i(TAG, "draw 2nd text");
			
			canvas.drawText(lastText, x, y, mPaintB);
		}
	}


