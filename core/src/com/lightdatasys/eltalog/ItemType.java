package com.lightdatasys.eltalog;


public interface ItemType<T extends Item>
{
	public String getTypeName();
	
	public int getColumnCount();
	public String getColumnName(int column)
		throws ArrayIndexOutOfBoundsException;
	public Object getColumn(T item, int column)
		throws ArrayIndexOutOfBoundsException;
}
