package com.lightdatasys.eltalog;

public class MovieLibrary extends Library<Movie>
{
	private static final long serialVersionUID = 200806181106L;
	

	public MovieLibrary() 
		throws NullPointerException
	{
		super(MovieType.getInstance());
	}

}
