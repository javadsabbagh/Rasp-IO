import com.pi4j.io.gpio.*;

/**
 * This class gives direct access to the GPIO pins used as general purpose output pins 
 * on the Raspberry Pi. Use this class if the pin is used for output. Instantiate the 
 * class with the GPIO pin number, and the resulting object then controls that pin. 
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class GPOutput 
{
    private GpioPinDigitalOutput gpioPin;

    /**
     * Create an object corresponding to a physical pin GPIO 1.
     */
    public GPOutput()
    {
        this(1);
    }
 
    /**
     * Create an object corresponding to a physical GPIO pin
     * 
     * @param gpioNumber  The logical GPIO number [0..15] (see Raspberry Pi model B+ documentation)
     */
    public GPOutput(int gpioNumber)
    {
        Pin[] pins = { RaspiPin.GPIO_00, RaspiPin.GPIO_01, RaspiPin.GPIO_02, RaspiPin.GPIO_03,
                       RaspiPin.GPIO_04, RaspiPin.GPIO_05, RaspiPin.GPIO_06, RaspiPin.GPIO_07,
                       RaspiPin.GPIO_08, RaspiPin.GPIO_09, RaspiPin.GPIO_10, RaspiPin.GPIO_11,
                       RaspiPin.GPIO_12, RaspiPin.GPIO_13, RaspiPin.GPIO_14, RaspiPin.GPIO_15 };
                       
        GpioController gpio = GpioFactory.getInstance();
        gpioPin = gpio.provisionDigitalOutputPin(pins[gpioNumber], PinState.LOW);
    }

    /**
     * Switch on (set pin to HIGH)
     */
    public void on() 
    {
        gpioPin.high();
    }
    
    /**
     * Switch off (set pin to LOW)
     */
    public void off() 
    {
        gpioPin.low();
    }

    /**
     * Blink n times. Time controls the speed of blinking.
     * 
     * @param n  The number of blinks
     * @param time  The time in milliseconds of a single on/off cycle
     */
    public void blink(int n, int time)
    {
        while (n>0) {
            on();
            wait(time/2);
            off();
            wait(time/2);
            n--;
        }
    }
    
    /**
     * Wait 'millis' milliseconds.
     */
    private void wait(int millis)
    {
       try {
           Thread.sleep(millis);
        }
       catch (InterruptedException exc) {}
    }
}