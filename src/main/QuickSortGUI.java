package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class QuickSortGUI extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	static private final String newline = "\n";
	JButton _jbtnSort, _jbtnClearIn, _jbtnClearOut;
	JTextArea _jtaIn;
	JTextArea _jtaOut;
	JFileChooser fc;

	public QuickSortGUI() {
		super(new BorderLayout());

		// Input area;
		TitledBorder borderInArea = BorderFactory.createTitledBorder("Eingabe");
		_jtaIn = new JTextArea(5, 100);
		_jtaIn.setEditable(true);
		_jtaIn.setLineWrap(true);
		
		JScrollPane jspIn = new JScrollPane(_jtaIn);
		JPanel jpInArea = new JPanel();
		
		_jbtnClearIn = new JButton("Feld löschen");
		_jbtnClearIn.addActionListener(this);
	
		jpInArea.setBorder(borderInArea);
		jpInArea.add(jspIn, BorderLayout.PAGE_START);
		jpInArea.add(_jbtnClearIn, BorderLayout.CENTER);

		// Output area
		TitledBorder borderOutArea = BorderFactory.createTitledBorder("Ausgabe");
		_jtaOut = new JTextArea(5, 100);
		_jtaOut.setEditable(true);
		_jtaOut.setLineWrap(true);
		
		JScrollPane jspOut = new JScrollPane(_jtaOut);
		JPanel jpOutArea = new JPanel();
		
		_jbtnClearOut = new JButton("Feld löschen");
		_jbtnClearOut.addActionListener(this);
	
		jpOutArea.setBorder(borderOutArea);
		jpOutArea.add(jspOut, BorderLayout.PAGE_START);
		jpOutArea.add(_jbtnClearOut, BorderLayout.CENTER);

		// Create a file chooser
		fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		_jbtnSort = new JButton("Sortieren");
		_jbtnSort.addActionListener(this);

		JPanel buttonPanel = new JPanel(); // use FlowLayout
		buttonPanel.add(_jbtnSort);

		// add to pane
		add(jpInArea, BorderLayout.PAGE_START);
		add(jpOutArea, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.PAGE_END);
	}

	public void actionPerformed(ActionEvent ae) {

		// Handle open button action.
		if (ae.getSource() == _jbtnSort) {

			String line = _jtaIn.getText();
			String[] parts = line.split(",");

			List<String> list = new ArrayList<String>();
			for (int i = 0; i < parts.length; i++) {
				list.add(String.valueOf(parts[i]).intern());
			}
			Collections.sort(list, String.CASE_INSENSITIVE_ORDER);

			for (int i = 0; i < list.size(); i++) {
				_jtaOut.append(list.get(i));
				if (i < list.size() - 1)
					_jtaOut.append(",");
			}
		} else if (ae.getSource() == _jbtnClearIn) {
			_jtaIn.setText("");	
		} else if (ae.getSource() == _jbtnClearOut) {
			_jtaOut.setText("");	
		}
	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("QuickSorter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new QuickSortGUI());
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}
}
