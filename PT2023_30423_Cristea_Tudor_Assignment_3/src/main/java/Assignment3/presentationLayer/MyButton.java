package Assignment3.presentationLayer;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents a custom class based on JButton, which has the added functionality of having different colors
 * based on the action of the mouse with respect to the button: none, hover and press
 * @author Tudor Cristea
 */
public class MyButton extends JButton
{
    /**
     * This field is the color the button will have when the mouse hovers over it
     */
    private Color hoverBackgroundColor;
    /**
     * This field is the color the button will have when the mouse presses it
     */
    private Color pressedBackgroundColor;

    /**
     * This is the only constructor of this class. It initializes its text field and sets the contentAreaFilled to
     * false in order for the button to not be transparent, and thus have a color (or more)
     * @param text the string of text the button will have
     */
    public MyButton(String text)
    {
        super(text);
        super.setContentAreaFilled(false);
    }

    /**
     * This is an overridden version of the setEnabled method. It calls the same method from the parent class
     * @param b true to enable the button, otherwise false
     */
    @Override
    public void setEnabled(boolean b)
    {
        super.setEnabled(b);
    }

    /**
     * This is an overridden version of the paintComponent method. It sets different colors for the button based on
     * the different actions of the mouse (none, hover and press)
     * @param g contains the Color component which is needed to color the button
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        if (getModel().isPressed())
        {
            g.setColor(pressedBackgroundColor);
        }
        else if (getModel().isRollover())
        {
            g.setColor(hoverBackgroundColor);
        }
        else
        {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    /**
     * This method is the mutator of the hoverBackgroundColor field
     * @param hoverBackgroundColor the new hoverBackgroundColor of a MyButton object
     */
    public void setHoverBackgroundColor(Color hoverBackgroundColor)
    {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    /**
     * This method is the mutator of the pressedBackgroundColor field
     * @param pressedBackgroundColor the new pressedBackgroundColor of a MyButton object
     */
    public void setPressedBackgroundColor(Color pressedBackgroundColor)
    {
        this.pressedBackgroundColor = pressedBackgroundColor;
    }
}