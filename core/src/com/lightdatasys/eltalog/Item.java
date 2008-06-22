package com.lightdatasys.eltalog;

import java.util.HashMap;
import java.util.Map;

import com.lightdatasys.eltalog.field.AbstractField;


public abstract class Item 
{
	protected Map<String,AbstractField> fields;
	
	
	protected Item()
	{
		fields = new HashMap<String,AbstractField>();
	}
	
	
	public abstract ItemType<? extends Item> getItemType();
	
	public abstract Object getColumn(int column)
		throws ArrayIndexOutOfBoundsException;
	
	
	public AbstractField getField(String key)
	{
		return fields.get(key);
	}
	
}
