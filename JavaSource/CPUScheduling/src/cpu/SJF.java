/**Assignment #: 02
Course: EECS2011 E
Professor: Jia Xu
Name : Akalpit Sharma
*/
package cpu;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The Class SJF that encapsulates SJF
 * scheduling algorithm.
 */
public class SJF extends Scheduler {

	/** The preemptive indicator. */
	private boolean preemptive;

	/**
	 * Instantiates a new SJF.
	 *
	 * @param processes the processes
	 * @param preemptive the preemptive
	 */
	public SJF(List<Process> processes, boolean preemptive) {
		super(processes);
		this.preemptive = preemptive;
	}

	/* (non-Javadoc)
	 * @see cpu.Scheduler#schedule(java.util.Queue, java.util.List)
	 */
	@Override
	void schedule(Queue<Process> readyQueue, List<Process> processes) {

		while (!readyQueue.isEmpty()) {
			Collections.sort((LinkedList<Process>)readyQueue, (p1, p2) -> Integer.compare(p1.getBurstTime(), p2.getBurstTime()));
			Process next = readyQueue.poll();

			next.state = State.RUNNING;						

			next.serviceTime = clock;
			System.out.println(next);

			do {
				clock ++;
				next.executeTime ++; // ????
				//				System.out.println("clock: " + clock);

				SequenceTime st;

				// First, we need to check whether the next time unit is for i/o
				// to indicate waiting.
				if ((st = next.getCurrentSeqTime()) instanceof IOTime) {
					IOTime io = (IOTime)st;
					if (io.getDevice().acquire(next)) { // If success.
						System.out.println(next.id + " acquired device " + io.getDevice().getId());

					} else {
						System.out.println(next.id + " waiting for device " + io.getDevice().getId());

					}
					next.state = State.WAITING; // P is either currently performing I/O on 
					// an I/O device or is currently waiting in an I/O queue.

					System.out.println(next);				
				} else { // If CPU.
					Device dev = next.releaseDevice();
					if (dev != null) {
						System.out.println(next.id + " released device " + dev.getId());
					}
				}

				List<Process> arriving = getArrivingProcesses(processes);
				readyQueue.addAll(arriving);
				if (preemptive) {
					for (Process p: readyQueue) {
						if (p.getBurstTime() < next.getBurstTime()) {
							next.state = State.READY;
							next.arrivalTime = clock;
							System.out.println(next);
							break;
						}
					}
				}

				if (next.executeTime == next.getBurstTime()) {
					next.state = State.TERMINATED;
					System.out.println(next);
				}
				if (next.state == State.READY) {
					readyQueue.add(next);
				}
			} while (next.state == State.RUNNING || next.state == State.WAITING);
		} // while

	}

} // end of class
