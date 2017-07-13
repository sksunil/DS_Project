package mainPackage;

import java.util.*;

import javax.print.PrintException;
import javax.swing.*;
import java.awt.*;
import java.awt.print.*;
import java.applet.*;
import java.io.*;

public class Main {

	HashMap<Integer,product> mapIdProduct;
	HashMap<String,Integer> bought = new HashMap<String,Integer>(); // temp hash map to store products currently bought by a customer
	HashMap<Integer,customer> cust = new HashMap<Integer,customer>();  // only on custemer
	TreeMap<Integer,product> treeIdProduct = new TreeMap<Integer,product>(); //for studey
	LinkedList<String> listIdProduct = new LinkedList<String>(); 
	
	Scanner scan;
	long totalBill = 0;
	product p;
	String filename;
	int repeat,cust_id;
	customer c = new customer();
	boolean chk_cust;
	Main( String fileName) // constructor
	{
		mapIdProduct = new HashMap<Integer, product>();
		String line;
		repeat=0;
		this.filename = fileName;
		// **************** || reading the products file and storing it in hash map || **************** // 
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) //line by line reader
		{
			while((line = br.readLine()) != null)
			{
				String[] element = line.split(",");
				p = new product(element[1],(long)Integer.parseInt(element[2].toString()),(long)Integer.parseInt(element[3]));
				mapIdProduct.put(Integer.parseInt(element[0]), p);
			}
			br.close();
		}catch(IOException e)
		{
			System.out.println(e.toString());
		}
		
		// *************** || reading customer file and storing it in hash map || *************************** //
		
		try(BufferedReader br = new BufferedReader(new FileReader("customers.csv")))
		{
			while((line = br.readLine()) != null)
			{
				String[] element = line.split(",");
				c = new customer(Integer.parseInt(element[0]),element[1]);
				cust.put(Integer.parseInt(element[0]), c);
				c.set_tot_sale(Integer.parseInt(element[2]));
			}
			br.close();
		}catch(IOException e)
		{
			System.out.println(e.toString()); 
		}
		
		// ********************* || Loading all Products In Linked List || ********************* //
		loadLinkedList();
		
	}
	
	public void loadLinkedList()
	{
		String line,content;
		try(BufferedReader br = new BufferedReader(new FileReader("test.csv")))    //read from file
		{
			while((line = br.readLine()) != null)
			{
				String[] element = line.split(",");
				p = new product(element[1],(long)Integer.parseInt(element[2].toString()),(long)Integer.parseInt(element[3]));
				content = element[0].toString();
				//content += "->";
				content += p.toStrng();
				listIdProduct.addLast(content);
			}
	}catch(IOException e)
		{
		System.out.println("IOException called in Loading Linked List ... \n "+e.toString());
	}
		//System.out.println(listIdProduct);
	}
	
	public void loadTreeMap()
	{
		String line;
		try(BufferedReader br = new BufferedReader(new FileReader("customers.csv")))
		{
			while((line = br.readLine()) != null)
			{
				String[] element = line.split(",");
				p = new product(element[1],(long)Integer.parseInt(element[2].toString()),(long)Integer.parseInt(element[3]));
				treeIdProduct.put(Integer.parseInt(element[0]), p);	
			}
			br.close();
		}catch(IOException e)
		{
			System.out.println(e.toString());
		}
		
	}
	
	public void insertP() // inserting a whole new product
	{
		scan = new Scanner(System.in);
		String name,content;
		long price;
		long quantity;
		int id;
		
		System.out.println("Enter Product Id...");
			id = scan.nextInt();
			if(mapIdProduct.containsKey(id))
			{
				System.out.println("*** Product ID Already Exists ***");
				return;
			}
		System.out.println("Enter Product Name...");
			name = scan.next();
		System.out.println("Enter Product Quantity...");
			quantity = scan.nextLong();
		System.out.println("Enter Product Price...");
			price = scan.nextLong();
			
		p = new product(name,quantity,price);
		mapIdProduct.put(id, p); // adding products into Hash Map
		
		// ************ adding new product to Linked List ************** //
		content = Integer.toString(id); 
		content += p.toStrng();
		listIdProduct.addLast(content);
	}
	
	public void sale()
	{
		int id,qty,bought_qty;
		scan = new Scanner(System.in);
		if(repeat == 0)
		{
			System.out.println("Enter Customer Id....");
			cust_id = scan.nextInt();
			//repeat++;
			chk_cust = cust.containsKey(cust_id);
		}
		if(chk_cust == true)
		{
		System.out.println("Enter Product ID...");
		id = scan.nextInt();
		System.out.println("Enter Product Quantity...");
		qty = scan.nextInt();
		
/*		if(mapIdProduct.containsKey(id)){
			mapIdProduct.get(id);
		}*/
		if(mapIdProduct.containsKey(id))
		{
			//Map.Entry mIdProduct = (Map.Entry);
				product temp = mapIdProduct.get(id);
				
				if(temp.getQuantity() < qty)
					System.out.println("Quantity is greater that available stock");
				else
				{
					long currQty = temp.getQuantity();
					temp.setQuantity(currQty - qty);
					mapIdProduct.put(id, temp);
					totalBill = totalBill + (temp.getPrice()*qty);
					//System.out.println( "Amount = " + (temp.getPrice()*qty));
					//bought.add(temp.getName() + );
					//bought_qty = bought.get(temp.getName()) + qty;
					if(repeat == 0)
						{
						bought.put(temp.getName(),qty);
						//repeat++;
						}
					else if(bought.containsKey(temp.getName()))
						{
							bought_qty = bought.get(temp.getName()) + qty;
							bought.put(temp.getName(),bought_qty);
						}
					else
					{
						bought.put(temp.getName(), qty);
					}
					if(repeat == 0)
						repeat++;
			}
		
		}
		
		System.out.println("Press 1 to Enter More Products to this bill...");
			qty = scan.nextInt(); // as qty was of no use now so i used it again over here
			if(qty == 1)
				sale();
			else
			{
				System.out.println("Total Bill of this Sale= " + totalBill);
				printBill();
				repeat=0;
				updateFile();
				c = cust.get(cust_id);
				long curr_totSale = c.get_tot_sale();
				c.set_tot_sale(totalBill+curr_totSale);
				cust.put(cust_id, c);
				updateLog();
				updateCustomerFile();
				totalBill = 0;
				bought.clear();
			}
		}
		else
		{
			System.out.print("Invalid Customer ID....");	
		}
	}
	
	public void insertQty()
	{
		int id;
		long qty;
		scan = new Scanner(System.in);
		String content;
		System.out.println("Enter Product ID...");
			id = scan.nextInt();
		System.out.println("Enter Product Qty...");
			qty = scan.nextLong();
		
		if(mapIdProduct.containsKey(id))
		{
				p = (product)mapIdProduct.get(id);
				long currQty = p.getQuantity();
				currQty  += qty;
				p.setQuantity(currQty);
				mapIdProduct.put(id, p); // adding products to hash Map
				treeIdProduct.put(id, p); // adding products to tree map
				System.out.println("*** Product Quantity Successfully Updated ***");
		
		// **** adding new quantity to linked list product **** //
		ListIterator itr = listIdProduct.listIterator();
		while(itr.hasNext())
		{
			content = itr.next().toString();
			String[] element = content.split("->");
			if(Integer.parseInt(element[0]) == id)
			{
				int currQty1 = Integer.parseInt(element[2]);
				currQty1 += qty;
				element[2] = Integer.toString(currQty1);
				String write1 = element[0] + "->" + element[1] + "->" + element[2] + "->" +element[3];
				itr.set(write1);
			//	System.out.println("*** Successfully Updated in Linked List ***");
				break;
			}
		}
		
		}
		//System.out.println(listIdProduct);
	}
	
	
	private String generateContentForProductDisplay()
	{
		StringBuilder builder = new StringBuilder();
		
		for(HashMap.Entry<Integer,product> write : mapIdProduct.entrySet())
		{
			p=(product)write.getValue();
			builder.append(write.getKey());
			builder.append(",");
			builder.append(p.getName());
			builder.append(",");
			builder.append(p.getQuantity());
			builder.append(",");
			builder.append(p.getPrice());
			builder.append("\r\n");
		}
		String content = builder.toString().trim(); //remove space
		return content;
	}
	private String generateContentForBill()
	{
		Date date = new Date();
		StringBuilder builder = new StringBuilder();
		builder.append(cust_id);
		for(HashMap.Entry<String,Integer> write : bought.entrySet())
		{
			//p=()write.getValue();
			builder.append(",");
			builder.append(write.getKey());
			builder.append("->");
			builder.append(write.getValue());
		}
		builder.append(",");
		builder.append("Total Bill = " + totalBill);
		builder.append(" on " + date.toString());
		builder.append("\r\n");
		String content = builder.toString().trim();
		return content;
	}
	private String generateContentForCustomer()
	{
		StringBuilder builder = new StringBuilder();
		for(HashMap.Entry<Integer,customer> write : cust.entrySet())
		{
			//p=()write.getValue();
			c=write.getValue();
			builder.append(c.get_id());
			builder.append(",");
			builder.append(c.get_name());
			builder.append(",");
			builder.append(c.get_tot_sale());
			builder.append("\r\n");
		}
		String content = builder.toString().trim();
		return content;
	}
	public void displayAll()
	{
		System.out.println(generateContentForProductDisplay());
	}
	
	public void display()
	{
		int id,flag=0;
		scan = new Scanner(System.in);
		
		System.out.println("Enter Product Id...");
			id = scan.nextInt();
	
			if(mapIdProduct.containsKey(id))
			{	
				long startTime = System.nanoTime();
				p = mapIdProduct.get(id);
				long endTime = System.nanoTime();
				System.out.println("Name : " + p.getName());
				System.out.println("Available Quantity : " + p.getQuantity());
				System.out.println("Price per Piece : " + p.getPrice());
				System.out.println("Hash Map Took ...." + (endTime-startTime) + " ns");
				startTime = System.nanoTime();
				ListIterator itr = listIdProduct.listIterator();
				while(itr.hasNext())
				{
					String content = itr.next().toString();
					String[] element = content.split("->");
					int ll_id = Integer.parseInt(element[0]);
					if(ll_id == id)
					{
						endTime = System.nanoTime();
						System.out.println("Linked List Took "+ (endTime-startTime) + " ns");
						flag=1;
						break;
					}
					
				}
				if(flag==0)
				{
					System.out.println("Element no found in Linked List");
				}
				startTime = System.nanoTime();
				p=treeIdProduct.get(id);
				endTime = System.nanoTime();
				System.out.println("Tree Map took " + (endTime-startTime) + " ns");
				 
			}
			else
			{
				System.out.println("*** Product Does Not Exist ***");
			}
			
	}
	
	public void updateFile()
	{
		String content = generateContentForProductDisplay();
		
		try(PrintWriter out = new PrintWriter("test.csv"))
		{
			out.println(content);
			out.close();
		}catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
	}
	public void updateLog()
	{
		String content = generateContentForBill();
		try(FileWriter fw = new FileWriter("cust_log.csv", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
				out.println();
			    out.println(content);
			    //more code
			    //out.println("more text");
			    //more code
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
				System.out.println("Exception in UpdateLog() .... \n"  + e.toString());
			}
	}
	
	public void updateCustomerFile()
	{
		String content = generateContentForCustomer();
		
		try(PrintWriter out = new PrintWriter("customers.csv"))
		{
			out.println(content);
			out.close();
		}catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
	}
	
	public void addCustomer()
	{
		//customer c = new customer();
		Scanner scan = new Scanner(System.in);
		int id=0;
		String name="";
		System.out.println("Enter customer id...");
			id = scan.nextInt();
			if(cust.containsKey(id))
			{
			System.out.println("Customer Id Already Existed...");
			return;
			}
			else{
		System.out.println("Enter customer name...");
			name = scan.next();
		customer c = new customer(id,name);
		c.set_tot_sale(0);
		cust.put(id, c);
			}
		//scan.close();
	}
	
	public void printBill()
	{
		Date date = new Date();
		StringBuilder builder = new StringBuilder();
		builder.append(" on " + date.toString() + "\n\n");
		builder.append(cust_id);
		for(HashMap.Entry<String,Integer> write : bought.entrySet())
		{
			builder.append(write.getKey());
			builder.append("->");
			builder.append(write.getValue());
			builder.append("\n");
		}
		builder.append("\n\n");
		builder.append("Total Bill = " + totalBill);
		builder.append("\r\n");
		String content = builder.toString().trim();
		JTextArea myArea = new JTextArea();
		myArea.setLineWrap(true);
		myArea.append(content);
		try{
			myArea.print();
		}
		catch(PrinterException pe)
		{
			System.out.println("Exception in Printing.... \n" + pe.toString());
		}
	
	}
}
