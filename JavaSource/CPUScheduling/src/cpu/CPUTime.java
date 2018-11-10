/**Assignment #: 02
Course: EECS2011 E
Professor: Jia Xu
Name : Akalpit Sharma
*/
package cpu;

/**
 * The Class CPUTime that encapsulates
 * cpu computation time.
 */
public class CPUTime extends SequenceTime {

	/**
	 * This constructor calls constructor
	 * of superclass SequenceTime and 
	 * instantiates CPUTime object.
	 * 
	 * @param timeUnit the time unit
	 * @param next the next SequenceTime
	 *        in the linked list data
	 *        structure
	 */
	public CPUTime(int timeUnit, SequenceTime next) {
		super(timeUnit, next);
	}

	/* (non-Javadoc)
	 * @see cpu.SequenceTime#toString()
	 */
	@Override
	public String toString() {
		return "CPUTime " + super.toString();
	}

} // end of class
