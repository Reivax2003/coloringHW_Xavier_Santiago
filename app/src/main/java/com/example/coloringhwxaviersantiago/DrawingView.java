package com.example.coloringhwxaviersantiago;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * @author Xavier Santiago
 */
public class DrawingView extends SurfaceView {
    private ElementManager elementManager;

    public DrawingView(Context context, AttributeSet attrs) { //initialize and make an associated element manager
        super(context, attrs);
        elementManager = new ElementManager();
        setWillNotDraw(false);
    }

    public ElementManager getElementManager() { //so everything can use a single element manager
        return elementManager;
    }

    @Override
    public void onDraw(Canvas canvas) { //just loop through the registered elements adn draw them
        for (CustomElement each : elementManager.getElements()) {
            each.drawMe(canvas);
        }
    }
}
