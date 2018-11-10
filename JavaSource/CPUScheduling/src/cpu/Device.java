/**Assignment #: 02
Course: EECS2011 E
Professor: Jia Xu
Name : Akalpit Sharma
*/
package cpu;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The Class Device that encapsulates a
 * simple device for i/o.
 */
public class Device {

	/** The id of device. */
	private final int ID;

	/** The queue of waiting 
	 * processes to use device. */
	private Queue<Process> queue;

	/**
	 * Instantiates a new device.
	 *
	 * @param ID the id
	 */
	public Device(int ID) {
		this.ID = ID;
		queue = new LinkedList<>();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return ID;
	}

	/**
	 * Acquires the device.
	 *
	 * @param process the process
	 *        which tries to acquire device
	 * @return true, if queue is empty or
	 *         process at the head of the
	 *         queue is the same process as
	 *         the given one (for example, in 
	 *         RR when the quantum expires but
	 *         not a required time unit)
	 */
	public boolean acquire(Process process) {
		boolean success = false;
		if (queue.isEmpty()) {
			queue.add(process);
			success = true;
		} else {
			if (process.equals(queue.peek())) {
				success = true;
			} 
		}

		return success;
	}

	/**
	 * Releases a device only when
	 * the process at the head of
	 * the queue is the same process
	 * as a given one.
	 *
	 * @param process the given process
	 * @return true, if successful
	 */
	public boolean release(Process process) {
		if (process.equals(queue.peek())) {
			queue.poll();
			return true;
		}
		return false;
	}
} // end of class
