package com.lightdatasys.eltalog;

public class MovieType implements ItemType<Movie>
{
	private static final String COLUMN_NAMES[] =
	{
		"Title",
		"Release Date"
	};
	
	private static MovieType instance = null;
	
	
	private MovieType()
	{
	}
	
	public static MovieType getInstance()
	{
		if(instance == null)
			instance = new MovieType();
		
		return instance;
	}
	
	
	public String getTypeName()
	{
		return "Movie";
	}

	
	public int getColumnCount()
	{
		return COLUMN_NAMES.length;
	}

	public String getColumnName(int column)
		throws ArrayIndexOutOfBoundsException 
	{
		if(0 <= column && column < getColumnCount())
		{
			return COLUMN_NAMES[column];
		}
		else
			throw new ArrayIndexOutOfBoundsException(column);
	}
	
	public Object getColumn(Movie item, int column)
			throws ArrayIndexOutOfBoundsException 
	{
		switch(column)
		{
			case 0:		return item.getField("title").toString();
			case 1:		return item.getField("releaseDate").toString();
		}

		throw new ArrayIndexOutOfBoundsException(column);
	}
}
