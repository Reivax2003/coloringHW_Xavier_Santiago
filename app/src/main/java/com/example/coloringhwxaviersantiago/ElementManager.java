package com.example.coloringhwxaviersantiago;

import android.graphics.Color;
import android.os.Build;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

/**
* @author Xavier Santiago
 */

public class ElementManager {
    private ArrayList<CustomElement> elements;
    private CustomElement currentElement;
    private TextView displayTV;

    public ElementManager() {
        elements = new ArrayList<CustomElement>();
    }
    public void setDisplayTV(TextView displayTV) {
        this.displayTV = displayTV;
    }

    //make sure we have a list of all elements we want to be able to change
    public void registerElement(CustomElement element) {
        elements.add(element);
    }


    //look through the list of elements and return the topmost one which contains given point
    public CustomElement getTopElement(int x, int y) {
        CustomElement element = null;

        for (CustomElement each : elements) {
            if (each.containsPoint(x, y) && (element == null || each.height > element.height) && each.editable) {
                element = each;
            }
        }

        return element;
    }

    //keep track of which element we've selected and change the text
    public void setCurrentElement(CustomElement element) {
        currentElement = element;
        displayTV.setText(element.myName);
    }

    //so we can draw them
    public ArrayList<CustomElement> getElements(){
        return elements;
    }

    //change the color of something
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setElementRed(int red) {
        Color color = Color.valueOf(currentElement.getColor());
        int intColor = Color.rgb(red/255f, color.green(), color.blue());
        currentElement.setColor(intColor);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setElementGreen(int green) {
        Color color = Color.valueOf(currentElement.getColor());
        int intColor = Color.rgb(color.red(), green/255f, color.blue());
        currentElement.setColor(intColor);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setElementBlue(int blue) {
        Color color = Color.valueOf(currentElement.getColor());
        int intColor = Color.rgb(color.red(), color.green(), blue/255f);
        currentElement.setColor(intColor);
    }

    //so we can check if it's null, aka nothing has been tapped yet
    public CustomElement getCurrentElement() {
        return currentElement;
    }
}
