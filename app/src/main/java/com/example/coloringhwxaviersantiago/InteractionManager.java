package com.example.coloringhwxaviersantiago;

import android.graphics.Color;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

import androidx.annotation.RequiresApi;

/**
 * @author Xavier Santiago
 */

public class InteractionManager implements SeekBar.OnSeekBarChangeListener, View.OnTouchListener {
    private DrawingView drawingView;
    private ElementManager elementManager;
    private SeekBar redSB; //we need these so we can set their values
    private SeekBar greenSB;
    private SeekBar blueSB;

    //getting seek bars could be a separate function but this works well enough
    public InteractionManager(DrawingView drawingView, ElementManager elementManager, SeekBar redSB, SeekBar greenSB, SeekBar blueSB) {
        this.drawingView = drawingView;
        this.elementManager = elementManager;
        this.redSB = redSB;
        this.greenSB = greenSB;
        this.blueSB = blueSB;
    }

    @RequiresApi(api = Build.VERSION_CODES.O) //idk why this is needed but android studio was giving me an error
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) { //check which seek bar changed and tell elementManager
        if (elementManager.getCurrentElement() != null) { //in case we have nothing selected we don't want a crash
            if (seekBar.getId() == R.id.redSB) {
                elementManager.setElementRed(seekBar.getProgress()); //separate methods for each color, probably unnecessary but it looks nicer
            } else if (seekBar.getId() == R.id.greenSB) {
                elementManager.setElementGreen(seekBar.getProgress());
            } else if (seekBar.getId() == R.id.blueSB) {
                elementManager.setElementBlue(seekBar.getProgress());
            }
            drawingView.invalidate(); //tell view to redraw since colors changed
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    /**
     * External Citation
     * Mon Feb 7
     * Android Studio Documentation
     * Again, used documentation for Color functions
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) { //get the element we tapped and set the seek bars
        CustomElement element = elementManager.getTopElement((int) motionEvent.getX(), (int) motionEvent.getY());
        if (element != null) { //again, prevent a crash from having a null object
            elementManager.setCurrentElement(element);

            Color color = Color.valueOf(element.getColor()); //took me a while to find this, manually manipulating the hex number wasn't working
            redSB.setProgress((int)(color.red()*255)); //color returns as a float between 0 and 1 so convert to int
            greenSB.setProgress((int)(color.green()*255));
            blueSB.setProgress((int)(color.blue()*255));
        }
        return true;
    }
}
