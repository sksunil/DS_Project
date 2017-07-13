package mainPackage;

public class product {

	String name;
	long quantity;
	long price;
	
	product()
	{
		name="";
		quantity=0;
		price=0;
	}
	
	product(String name, long quantity, long price)
	{
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	
	public String toStrng()
	{
		String content;
		content = "->";
		content += this.name;
		content +="->";
		content += Integer.toString((int)this.quantity);
		content +="->";
		content += Integer.toString((int)this.price);
		return content;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setQuantity(long quantity)
	{
		this.quantity = quantity;
	}
	
	public void setPrice(long price)
	{
		this.price = price;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public long getPrice()
	{
		return this.price;
	}
	
	public long getQuantity()
	{
		return this.quantity;
	}
}
