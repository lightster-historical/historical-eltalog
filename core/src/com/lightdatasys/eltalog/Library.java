package com.lightdatasys.eltalog;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class Library<I extends Item> extends AbstractTableModel 
{
	private static final long serialVersionUID = 200806181106L;
	
	
	private ItemType<I> itemType;
	public ArrayList<I> items;
	
	
	public Library(ItemType<I> itemType)
		throws NullPointerException
	{
		if(itemType != null)
		{
			this.itemType = itemType;
			this.items = new ArrayList<I>();
		}
		else
			throw new NullPointerException("Input itemType must be non-null");
	}
	
	
	public ItemType<I> getItemType()
	{
		return itemType;
	}
	
	
	public int getColumnCount() 
	{
		return itemType.getColumnCount();
	}

	public int getRowCount() 
	{
		return items.size();
	}

	public Object getValueAt(int row, int col) 
		throws ArrayIndexOutOfBoundsException
	{
		if(!(0 <= row && row < items.size()))
			throw new ArrayIndexOutOfBoundsException();
		if(!(0 <= col && col < getColumnCount()))
			throw new ArrayIndexOutOfBoundsException();
		else
			return items.get(row).getColumn(col);
	}
	
	public I getRowAt(int row)
		throws ArrayIndexOutOfBoundsException
	{
		if(!(0 <= row && row < items.size()))
			throw new ArrayIndexOutOfBoundsException();
		else
			return (I)items.get(row);
	}

	public String getColumnName(int col)
		throws ArrayIndexOutOfBoundsException 
	{
		return itemType.getColumnName(col);
	}
	
	
	public void add(I item)
	{
		items.add(item);
	}
}
