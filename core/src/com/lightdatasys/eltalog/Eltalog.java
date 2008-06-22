package com.lightdatasys.eltalog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.lightdatasys.eltalog.gui.FilterPanel;
import com.lightdatasys.eltalog.gui.LibraryTablePanel;
import com.lightdatasys.gui.AppWindow;
import com.lightdatasys.gui.QuitHandler;
import com.lightdatasys.gui.WindowUtil;

public class Eltalog extends AppWindow implements QuitHandler
{
	private static final long serialVersionUID = 200806181106L;


	private JTabbedPane libraryTabs;
	private JPanel buttonPane;

	private JButton editButton;
	private JButton deleteButton;
	
	
	public static void main(String[] args) 
	{
		Eltalog catalog = new Eltalog();
		if(catalog == null)
			return;
	}
	
	
	
	
	
	public Eltalog()
	{
		super("Eltalog");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JSplitPane splitLibrariesMain;
		splitLibrariesMain = new JSplitPane();
		splitLibrariesMain.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitLibrariesMain.setContinuousLayout(true);
		
		JTree libTree = new JTree();
		libTree.setBackground(Color.BLACK);
		libTree.removeAll();
		splitLibrariesMain.setLeftComponent(libTree);

		JPanel libraryButtonPane = new JPanel(new BorderLayout());
		
		JSplitPane splitFilterTable;
		{
			splitFilterTable = new JSplitPane();
			splitFilterTable.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitFilterTable.setContinuousLayout(true);
			
			splitFilterTable.setTopComponent(new FilterPanel());
		
			MovieLibrary library = new MovieLibrary();
			Movie m;
			for(int i = 0; i < 1000; i++)
			{
				m = new Movie("Hello");
				m.setReleaseDate((new GregorianCalendar(2008, 0, 35)).getTime());
				library.add(m);
				m = new Movie("Test 123");
				m.setReleaseDate((new GregorianCalendar()).getTime());
				library.add(m);
			}
			
			//JComponent libraryTable = new LibraryTablePanel<Movie>(library);
			libraryTabs = new JTabbedPane();
			LibraryTablePanel<Movie> movieLibrary = new LibraryTablePanel<Movie>(library);
			libraryTabs.add("Movies", movieLibrary);
			movieLibrary.addListSelectionListener
			(
					new ListSelectionListener()
					{
						public void valueChanged(ListSelectionEvent e)
						{		
							buttonPane.validate();
						}
					}
			);
			libraryTabs.add("Books", new LibraryTablePanel<Movie>(library));
			libraryTabs.add("Music", new LibraryTablePanel<Movie>(library));
			libraryTabs.add("Games", new LibraryTablePanel<Movie>(library));
			libraryTabs.addChangeListener
			(
					new ChangeListener()
					{
						public void stateChanged(ChangeEvent e)
						{
							if(e.getSource().equals(libraryTabs))
							{
								buttonPane.validate();
							}
						}
					}
			);
			splitFilterTable.setBottomComponent(libraryTabs);
		}
		splitLibrariesMain.setRightComponent(splitFilterTable);
		libraryButtonPane.add(splitFilterTable, BorderLayout.CENTER);
		
		{
			editButton = new JButton("Edit");
			deleteButton = new JButton("Delete");
			
			buttonPane = new JPanel(new FlowLayout())
			{
				public void validate()
				{
					super.validate();

					LibraryTablePanel<?> library = (LibraryTablePanel<?>)libraryTabs.getSelectedComponent();
					
					ArrayList<?> selected = library.getSelectedItems();

					editButton.setEnabled(selected.size() == 1);
					deleteButton.setEnabled(selected.size() >= 1);
				}
			};
			buttonPane.add(new JButton("New"));
			buttonPane.add(editButton);
			buttonPane.add(deleteButton);
		}
		libraryButtonPane.add(buttonPane, BorderLayout.PAGE_END);
		
		add(libraryButtonPane);
		
		WindowUtil.centerWindow(this);

		splitLibrariesMain.setDividerSize(4);		
		splitFilterTable.setDividerLocation(150);

		splitLibrariesMain.setDividerSize(3);
		splitLibrariesMain.setDividerLocation(150);

		this.setVisible(true);

		splitFilterTable.setDividerLocation(.3);
		
		splitFilterTable.revalidate();
		splitFilterTable.repaint();
		
		/*
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }

		
		try {
		    Connection conn = 
		        DriverManager.getConnection("jdbc:mysql://localhost/litesign_alpha?" + 
		                                    "user=litesign_mlight&password=***REMOVED***");

		     // Do something with the Connection
		    
		    conn.close();

		 } catch (SQLException ex) {
		     // handle any errors
		     System.out.println("SQLException: " + ex.getMessage());
		     System.out.println("SQLState: " + ex.getSQLState());
		     System.out.println("VendorError: " + ex.getErrorCode());
		 }
		 //*/
	}
	
	
	public void handleClose()
	{
        System.out.println("Close Handled");
        
        handleQuit();
	}
    
    public void handleQuit()
    {
        System.out.println("Quit Handled");
        
        dispose();
    } 
}
