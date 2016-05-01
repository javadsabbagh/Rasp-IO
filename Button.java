import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.*;

/**
 * This class represents a button connected to GPIO pin 04 of the Raspberry Pi.
 * Connect your button to 3.3V power and to GPIO 04.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class Button implements GpioPinListenerDigital
{
    private GpioPinDigitalInput buttonPin;
    private ButtonListener listener;
    
    /**
     * Create an object representing a button at pin GPIO 4.
     */
    public Button()
    {
        this(4);
    }
    
    /**
     * Create an object representing a button connected to a GPIO pin.
     * 
     * @param gpioNumber  The logical GPIO number [0..15] (see Raspberry Pi model B+ documentation)
     */
    public Button(int gpioNumber)
    {
        Pin[] pins = { RaspiPin.GPIO_00, RaspiPin.GPIO_01, RaspiPin.GPIO_02, RaspiPin.GPIO_03,
                       RaspiPin.GPIO_04, RaspiPin.GPIO_05, RaspiPin.GPIO_06, RaspiPin.GPIO_07,
                       RaspiPin.GPIO_08, RaspiPin.GPIO_09, RaspiPin.GPIO_10, RaspiPin.GPIO_11,
                       RaspiPin.GPIO_12, RaspiPin.GPIO_13, RaspiPin.GPIO_14, RaspiPin.GPIO_15 };
                       
        GpioController gpio = GpioFactory.getInstance();
        buttonPin = gpio.provisionDigitalInputPin(pins[gpioNumber], PinPullResistance.PULL_DOWN);
        buttonPin.addListener(this);
    }

    /**
     * True if the button is currently pressed.
     */
    public boolean isDown()
    {
        return buttonPin.isHigh();
    }
    
    /**
     * True if the button is currently not pressed.
     */
    public boolean isUp()
    {
        return buttonPin.isLow();
    }
    
    public void addListener(ButtonListener listener)
    {
        this.listener = listener;
    }
    
    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent evt)
    {
        if (listener != null) {
            listener.buttonEvent(this);
        }
    }
        
}