/**Assignment #: 02
Course: EECS2011 E
Professor: Jia Xu
Name : Akalpit Sharma
*/
package cpu;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class Test that contains
 * a main method to test the program.
 */
public class Test {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		try {

			Device dev1 = new Device(0);
			Device dev2 = new Device(1);

			List<Process> processes = new ArrayList<>();
			processes.add(new Process("P1", new CPUTime(3, new IOTime(9, new CPUTime(14, new IOTime(3, new CPUTime(7, null), dev1)), dev1))));
			processes.add(new Process("P2", new CPUTime(2, new IOTime(1, new CPUTime(1, null), dev1))));
			processes.add(new Process("P3", new CPUTime(2, new IOTime(1, new CPUTime(1, null), dev2))));

			RR rr = new RR(processes, 4);
			rr.schedule();

			System.out.println("\nResults for RR:");
			System.out.println("Avg waiting time: " + rr.avgWaitingTime());
			System.out.println("Avg turnaround time: " + rr.avgTurnaroundTime());

			processes.clear();
			processes.add(new Process("P1", new CPUTime(3, new IOTime(9, new CPUTime(14, new IOTime(3, new CPUTime(7, null), dev1)), dev1))));
			processes.add(new Process("P2", new CPUTime(2, new IOTime(1, new CPUTime(1, null), dev1))));
			processes.add(new Process("P3", new CPUTime(2, new IOTime(1, new CPUTime(1, null), dev2))));

			FCFS fcfs = new FCFS(processes);
			fcfs.schedule();

			System.out.println("\nResults for FCFS:");
			System.out.println("Avg waiting time: " + fcfs.avgWaitingTime());
			System.out.println("Avg turnaround time: " + fcfs.avgTurnaroundTime());

			processes.clear();
			processes.add(new Process("P1", new CPUTime(3, new IOTime(2, new CPUTime(5, null), dev1))));
			processes.add(new Process("P2", new CPUTime(2, new IOTime(3, new CPUTime(2, null), dev1)), 1));
			processes.add(new Process("P3", new CPUTime(5, new IOTime(5, new CPUTime(4, null), dev2)), 2));
			processes.add(new Process("P4", new CPUTime(2, new IOTime(2, new CPUTime(3, null), dev2)), 3));

			SJF sjfPreem = new SJF(processes, true);
			sjfPreem.schedule();

			System.out.println("\nResults for SJF preemptive:");
			System.out.println("Avg waiting time: " + sjfPreem.avgWaitingTime());
			System.out.println("Avg turnaround time: " + sjfPreem.avgTurnaroundTime());

			processes.clear();
			processes.add(new Process("P1", new CPUTime(3, new IOTime(2, new CPUTime(5, null), dev1))));
			processes.add(new Process("P2", new CPUTime(2, new IOTime(3, new CPUTime(2, null), dev1)), 1));
			processes.add(new Process("P3", new CPUTime(5, new IOTime(5, new CPUTime(4, null), dev2)), 2));
			processes.add(new Process("P4", new CPUTime(2, new IOTime(2, new CPUTime(3, null), dev2)), 3));

			SJF sjfNonPreem = new SJF(processes, false);
			sjfNonPreem.schedule();

			System.out.println("\nResults for SJF non-preemptive:");
			System.out.println("Avg waiting time: " + sjfNonPreem.avgWaitingTime());
			System.out.println("Avg turnaround time: " + sjfNonPreem.avgTurnaroundTime());

			processes.clear();
			processes.add(new Process("P1", new CPUTime(3, new IOTime(2, new CPUTime(7, null), dev1)), 0, 3));
			processes.add(new Process("P2", new CPUTime(1, null), 0, 1));
			processes.add(new Process("P3", new CPUTime(2, null), 0, 4));
			processes.add(new Process("P4", new CPUTime(1, null), 0, 5));
			processes.add(new Process("P5", new CPUTime(2, new IOTime(8, new CPUTime(3, null), dev1)), 0, 2));

			Priority priorPreem = new Priority(processes, true);
			priorPreem.schedule();

			System.out.println("\nResults for Priority preemptive:");
			System.out.println("Avg waiting time: " + priorPreem.avgWaitingTime());
			System.out.println("Avg turnaround time: " + priorPreem.avgTurnaroundTime());

			processes.clear();
			processes.add(new Process("P1", new CPUTime(3, new IOTime(2, new CPUTime(7, null), dev1)), 0, 3));
			processes.add(new Process("P2", new CPUTime(1, null), 0, 1));
			processes.add(new Process("P3", new CPUTime(2, null), 0, 4));
			processes.add(new Process("P4", new CPUTime(1, null), 0, 5));
			processes.add(new Process("P5", new CPUTime(2, new IOTime(8, new CPUTime(3, null), dev1)), 0, 2));

			Priority priorNonPreem = new Priority(processes, false);
			priorNonPreem.schedule();

			System.out.println("\nResults for Priority non-preemptive:");
			System.out.println("Avg waiting time: " + priorNonPreem.avgWaitingTime());
			System.out.println("Avg turnaround time: " + priorNonPreem.avgTurnaroundTime());

			processes.clear();
			processes.add(new Process("P1", new CPUTime(3, new IOTime(2, new CPUTime(7, null), dev1)), 0, 3));
			processes.add(new Process("P2", new CPUTime(11, null), 0, 18));
			processes.add(new Process("P3", new CPUTime(23, null), 10, 14));
			processes.add(new Process("P4", new CPUTime(11, null), 0, 15));
			processes.add(new Process("P5", new CPUTime(5, new IOTime(8, new CPUTime(3, null), dev1)), 0, 2));

			MultilevelQueue mulQue = new MultilevelQueue(processes, 3, 2);
			mulQue.schedule();

			processes.clear();
			processes.add(new Process("P1", new CPUTime(3, new IOTime(2, new CPUTime(7, null), dev1)), 0, 3));
			processes.add(new Process("P2", new CPUTime(11, null), 0, 18));
			processes.add(new Process("P3", new CPUTime(23, null), 10, 14));
			processes.add(new Process("P4", new CPUTime(11, null), 0, 15));
			processes.add(new Process("P5", new CPUTime(5, new IOTime(8, new CPUTime(3, null), dev1)), 0, 2));

			MultilevelFeedbackQueue mulFeedQue = new MultilevelFeedbackQueue(processes);
			mulFeedQue.schedule();

		} catch (IllegalArgumentException exc) {
			System.err.println("Program closed: " + exc.getMessage());
		}
	}

} // end of class
