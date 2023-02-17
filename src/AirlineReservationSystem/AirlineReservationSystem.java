/*Program that enables an airline assign seats for a 10 passenger plane. 
 * Passengers choose between First Class (1) and Economy (2) and a seat is assigned accordingly.
 * When first class is full, passenger should be offered a seat in economy and vice versa.
 * When plane is full passengers are directed to the next flight
 */

package AirlineReservationSystem;

import java.util.Scanner;

public class AirlineReservationSystem {

	private static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		
		boolean[] seats = new boolean[10];
		
		//Check the availability of seats in each section, and ask to switch if not available
		while(planeHasSeats(seats)) {
		
			int choice = getSectionChoice();  //user choice for either Firstclass(1) or Economy (2) or 2 
			
			if (!seatsAvailable(choice, seats)) {
				if (willingToSwitch(choice)) {
					choice = otherOne(choice);
				} else {
					byebye();
					continue;
				}
			}
			
			int seat = getNextSeat(choice, seats);	
			printBoardPass(choice, seat, seats);
		}
		
		byebye();
		
		input.close();
	}
	//method that tells the checks if there are still available seats in the section chosen
	private static boolean planeHasSeats(boolean[] seats) {
		return seatsAvailable(1, seats) || seatsAvailable(2, seats);
	}

	//method that tells the passenger the section is full by redirecting them to the next flight 
	private static void byebye() {
		System.out.println("Next flight leaves in 3 hours.");
	}
	
	//method uses a ternary operator to change choice 
	private static int otherOne(int choice) {
		return choice == 1 ? 2 : 1;
	}
	
	//method that asks the passenger to switch and actually switches plane section
	private static boolean willingToSwitch(int choice) {
		System.out.printf("%s is full, would you like to switch to %s (1 = yes, 2 = no)?: ",
				sectionName(choice),
				sectionName(otherOne(choice))
				);
		
		int decision = input.nextInt();
		return decision == 1;
	}

	//method that assigns the section Name (First Class or Economy) to users input of 1 or 2
	private static String sectionName(int choice) {
		return choice == 1 ? "First Class" : "Economy";
	}

	//method that prints the boarding pass with the seat selection based on the section of choice
	private static void printBoardPass(int choice, int seat, boolean[] seats) {
		System.out.println();
		System.out.println("***********************");
		System.out.println("**** Boarding Pass ****");
		System.out.println("***********************");
		System.out.printf("* %-11s Seat %02d *\n", sectionName(choice), seat + 1);
		System.out.println("***********************");
		System.out.println();
	}
	
	// uses for loop to assign seat based on availability in section seat 1-5 for First class  and seats 6 to 10 for Economy
	private static int getNextSeat(int choice, boolean[] seats) {
		int start = choice == 1 ? 0 : 5;
		int end = choice == 1 ? 4 : 9;
		
		for (int i = start; i <= end; i++) {
			if (seats[i] == false) {
				seats[i] = true;
				return i;
			}
		}
		
		return -1;
	}
	//checks if there are any available seats 
	private static boolean seatsAvailable(int choice, boolean[] seats) {
		int start = choice == 1 ? 0 : 5;
		int end = choice == 1 ? 4 : 9;
		
		for (int i = start; i <= end; i++) {
			if (seats[i] == false) {
				return true;
			}
		}
		
		return false;
	}
  
	//gets users plane section of choice
	private static int getSectionChoice() {
		System.out.print("Please Enter your section of choice (1 = First Class, 2 = Economy): ");
		return input.nextInt();
	}

}