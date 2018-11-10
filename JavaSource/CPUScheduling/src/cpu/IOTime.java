/**Assignment #: 02
Course: EECS2011 E
Professor: Jia Xu
Name : Akalpit Sharma
*/
package cpu;

/**
 * The Class IOTime that encapsulates
 * i/o time.
 */
public class IOTime extends SequenceTime {
	
	/** The device object which is used. */
	private Device device;

	/**
	 * Instantiates a new IO time.
	 *
	 * @param timeUnit the time unit
	 * @param next the next SequenceTime object
	 * @param device the device
	 */
	public IOTime(int timeUnit, SequenceTime next, Device device) {
		super(timeUnit, next);
		this.device = device;
	}

	/* (non-Javadoc)
	 * @see cpu.SequenceTime#toString()
	 */
	@Override
	public String toString() {
		return "IOTime [device = " + device + "] " + super.toString();
	}

	/**
	 * Gets the device.
	 *
	 * @return the device
	 */
	public Device getDevice() {
		return device;
	}

	/**
	 * Sets the device.
	 *
	 * @param device the new device
	 */
	public void setDevice(Device device) {
		this.device = device;
	}
	

} // end of class
