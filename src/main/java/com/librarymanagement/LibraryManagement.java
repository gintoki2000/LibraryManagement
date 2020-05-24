package com.librarymanagement;

import com.librarymanagement.utils.ComponenContainer;
import com.librarymanagement.utils.InjectBy;
import com.librarymanagement.utils.Inject;

import javax.swing.*;


public class LibraryManagement extends JFrame {
	private JPanel mainView;
	public LibraryManagement() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ComponenContainer container = new ComponenContainer(Context.class);
	}

	@Inject(injectBy = InjectBy.NAME, component = "mainView")
	public void setMainView(JPanel mainView) {
		this.mainView = mainView;
		setContentPane(mainView);
		pack();
	}
}
