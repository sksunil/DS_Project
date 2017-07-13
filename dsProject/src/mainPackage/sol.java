package mainPackage;
import java.io.*;
import java.util.Scanner;

public class sol {

	public static void main(String[] args) {
		
		int choice;
		Main object = new Main("test.csv");
		Scanner scan = new Scanner(System.in);
		
		do
		{
			
			System.out.println("1. Generate Sale...");
			System.out.println("2. Insert Quantity...");
			System.out.println("3. Insert Product...");
			System.out.println("4. Display Product Details...");
			System.out.println("5. Display All Products...");
			System.out.println("6. Add Customer");
			System.out.println("7. Exit");
				choice = scan.nextInt();
				
			switch(choice)
			{
			case 1:
				object.sale();
					break;
			case 2:
				object.insertQty();
					break;
			case 3:
				object.insertP();
					break;
			case 4:
				object.display();
					break;
			case 5:
				object.displayAll();
					break;
			case 6:
				object.addCustomer();
					break;
			case 7:
				object.updateFile();
					//obj.updateLog();
				object.updateCustomerFile();
					break;
			default:
					System.out.println("*** Incorrect Input ***");
			}
		}while(choice != 7);
		
		scan.close();
		
	}

}
