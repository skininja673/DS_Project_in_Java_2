/**Assignment #: 02
Course: EECS2011 E
Professor: Jia Xu
Name : Akalpit Sharma
*/
package cpu;

/**
 * The Class SequenceTime that contains
 * all common properties for cputime and
 * iotime.
 */
public abstract class SequenceTime {
	
	/** The time unit. */
	final int TIME_UNIT;
	
	/** The service time. */
	int serviceTime;
	
	/** The arrival time. */
	int arrivalTime;
	
	/**
	 * Stores currently spent timeUnit.
	 */
	int executeTime; 
	
	/** The next seq time. */
	SequenceTime next;

	/**
	 * Instantiates a new sequence time.
	 *
	 * @param TIME_UNIT the time unit
	 * @param next the next
	 */
	public SequenceTime(int TIME_UNIT, SequenceTime next) {
		this.TIME_UNIT = TIME_UNIT;
		this.next = next;
		executeTime = TIME_UNIT; // To make a copy of total timeUnit unit.
		// This value will be used to know how many timeUnit units
		// are left.
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[TIME_UNIT=" + TIME_UNIT + "]";
	}

	/**
	 * Gets the time unit.
	 *
	 * @return the time unit
	 */
	public int getTIME_UNIT() {
		return TIME_UNIT;
	}

	/**
	 * Gets the next.
	 *
	 * @return the next
	 */
	public SequenceTime getNext() {
		return next;
	}

	/**
	 * Sets the next.
	 *
	 * @param next the new next
	 */
	public void setNext(SequenceTime next) {
		this.next = next;
	}

	/**
	 * Gets the execute time.
	 *
	 * @return the execute time
	 */
	public int getExecuteTime() {
		return executeTime;
	}

	/**
	 * Sets the execute time.
	 *
	 * @param executeTime the new execute time
	 */
	public void setExecuteTime(int executeTime) {
		this.executeTime = executeTime;
	}


} // end of class
