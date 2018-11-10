/**Assignment #: 02
Course: EECS2011 E
Professor: Jia Xu
Name : Akalpit Sharma
*/
package cpu;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * The Class MultilevelFeedbackQueue that
 * encapsulates algorithm which consists
 * of the 3 types of queue (RR, RR and FCFS).
 * Once the previous queue is empty then 
 * the next queue executes processes
 * remaining from the previous queues.
 */
public class MultilevelFeedbackQueue extends Scheduler {

	/**
	 * Instantiates a new multilevel feedback queue.
	 *
	 * @param processes the processes
	 */
	public MultilevelFeedbackQueue(List<Process> processes) {
		super(processes);
	}

	/**
	 * Schedules the processes.
	 * This method just calls the 
	 * next overloaded schedule
	 * method and overrides this 
	 * method from the superclass
	 * because we don't need implementation
	 * of this method from the superclass.
	 */
	/*
	 * (non-Javadoc)
	 * @see cpu.Scheduler#schedule()
	 */
	void schedule() {
		schedule(null, null);
	}

	@Override
	void schedule(Queue<Process> readyQueue, List<Process> processes) {
		// First, create empty last queue (according to the figure 6.7, p. 276).
		FCFS q2 = new FCFS(new ArrayList<>());

		// Then, create empty second queue with quantum 16.
		RR q1 = new RR(new ArrayList<>(), 16, q2);

		// Next, create first queue with all processes with quantum 8.
		RR q0 = new RR(this.processes, 8, q1);

		// Next, schedule first queue.
		q0.schedule();

		// If first queue is empty, schedule second queue.
		q1.schedule();

		// If the second queue is empty, schedule last queue.
		q2.schedule();

		System.out.println("\nResults for " + getClass().getSimpleName() + ":");
		System.out.println("Avg waiting time: " + (q0.avgWaitingTime() + q1.avgWaitingTime() + q2.avgWaitingTime()) / 3);
		System.out.println("Avg turnaround time: " + (q0.avgTurnaroundTime() + q1.avgTurnaroundTime() + q2.avgTurnaroundTime()) / 3);
	}

} // end of class
