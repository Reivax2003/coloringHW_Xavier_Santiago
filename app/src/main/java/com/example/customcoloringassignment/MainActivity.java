package com.example.customcoloringassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

/**
 * @author Xavier Santiago
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawing);

        DrawingView drawingView = findViewById(R.id.drawingView); //our DrawingView is created automatically
        ElementManager elementManager = drawingView.getElementManager(); //element manager is created by drawingView so we grab it here
        elementManager.setDisplayTV(findViewById(R.id.displayTV)); //we can only use findViewById here which is why we grabbed elementManager

        //sorry about the bad naming, I was pretty tired while doing this
        CustomRect background = new CustomRect("Background", 0xFFFFFFFF, -1,
                0, 0, 5000, 5000, true);
        CustomRect rect1 = new CustomRect("Top Cake Layer", 0xFFF2EC72, 0,
                200, 600, 1000, 800, true);
        CustomRect rect2 = new CustomRect("Bottom Cake Layer", 0xFFF2EC72, 0,
                200, 800, 1000, 1000, true);
        CustomRect rect3 = new CustomRect("Top Frosting", 0xFFFFAAAA, 1,
                200, 600, 1000, 650, true);
        CustomRect rect4 = new CustomRect("Middle Frosting", 0xFFFFAAAA, 1,
                200, 800, 1000, 850, true);
        CustomRect rect5 = new CustomRect("Left Candle", 0xFF1CA621, 0,
                400, 400, 450, 600, true);
        CustomRect rect6 = new CustomRect("Right Candle", 0xFF1CA621, 0,
                750, 400, 800, 600, true);
        CustomCircle circle1 = new CustomCircle("Left Flame", 0xFFFFA600, 1,
                425, 375, 25, true);
        CustomCircle circle2 = new CustomCircle("Right Flame", 0xFFFFA600, 1,
                775, 375, 25, true);
        elementManager.registerElement(background); //add our elements to elementManager's list
        elementManager.registerElement(rect1);
        elementManager.registerElement(rect2);
        elementManager.registerElement(rect3);
        elementManager.registerElement(rect4);
        elementManager.registerElement(rect5);
        elementManager.registerElement(rect6);
        elementManager.registerElement(circle1);
        elementManager.registerElement(circle2);

        //get these before creating interactionManager since it needs them in it's constructor
        SeekBar redSB = findViewById(R.id.redSB);
        SeekBar greenSB = findViewById(R.id.greenSB);
        SeekBar blueSB = findViewById(R.id.blueSB);

        InteractionManager interactionManager = new InteractionManager(drawingView, elementManager, redSB, greenSB, blueSB);

        //however we still assign the listener after since we need interactionManager to exist
        blueSB.setOnSeekBarChangeListener(interactionManager);
        greenSB.setOnSeekBarChangeListener(interactionManager);
        redSB.setOnSeekBarChangeListener(interactionManager);

        drawingView.setOnTouchListener(interactionManager);
    }
}