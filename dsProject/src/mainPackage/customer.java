package mainPackage;

public class customer {

	int id;
	String name;
	long tot_sale;
	
	customer()
	{
		id=0;
		name="";
		//tot_sale=0;
	}
	
	customer(int id,String name)
	{
		this.id=id;
		this.name=name;
	//	this.tot_sale = 0;
	}
	
	public int get_id()
	{
		return this.id;
	}
	
	public String get_name()
	{
		return this.name;
	}
	public long get_tot_sale()
	{
		return this.tot_sale;
	}
	
	public void set_id(int id)
	{
		this.id = id;
	}
	public void set_name(String name)
	{
		this.name=name;
	}
	public void set_tot_sale(long sale)
	{
		this.tot_sale = sale;
	}
}
