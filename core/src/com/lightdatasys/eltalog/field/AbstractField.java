package com.lightdatasys.eltalog.field;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JPanel;

public abstract class AbstractField implements Serializable
{
	private String label;
	

	protected AbstractField()
	{
		this.label = null;
	}
	
	protected AbstractField(String label)
	{
		this.label = label;
	}
	
	
	public String getLabel() {return label;}
	
	
	public abstract JPanel getPanel();
	public abstract void keepValues();
	public abstract void disposePanel();
	
	
	public abstract void writeObject(ObjectOutputStream out)
		throws IOException;
	public abstract void readObject(ObjectInputStream in)
		throws IOException, ClassNotFoundException;
}
