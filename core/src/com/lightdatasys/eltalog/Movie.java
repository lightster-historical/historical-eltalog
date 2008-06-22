package com.lightdatasys.eltalog;

import java.util.ArrayList;
import java.util.Date;

import com.lightdatasys.eltalog.field.DateField;
import com.lightdatasys.eltalog.field.TextField;

public class Movie extends Item
{
	private ArrayList<Role> characters; 
	
	
	public Movie()
	{
		super();

		fields.put("title", new TextField("Title"));
		fields.put("releaseDate", new DateField("Date"));
	}
	
	public Movie(String title)
	{
		this();
		
		setTitle(title);
	}
	

	public void setTitle(String title)
	{
		((TextField)fields.get("title")).setValue(title);
	}
	
	public void setReleaseDate(Date date) 
	{
		((DateField)fields.get("releaseDate")).setDate(date);
	}
	
	
	public String getTitle() 
	{
		return ((TextField)fields.get("title")).getValue();
	}
	
	public Date getReleaseDate() 
	{
		return ((DateField)fields.get("releaseDate")).getDate();
	}
	
	public Role[] getCharacters() 
	{
		return characters.toArray(new Role[characters.size()]);
	}

	
	public ItemType<Movie> getItemType()
	{
		return MovieType.getInstance();
	}
	
	
	public Object getColumn(int column)
		throws ArrayIndexOutOfBoundsException
	{
		return getItemType().getColumn(this, column);
	}
}
