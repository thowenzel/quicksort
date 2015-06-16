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
	static private final String newline = "\n"; //$NON-NLS-1$
	JButton _jbtnSort, _jbtnClearIn, _jbtnClearOut;
	JTextArea _jtaIn;
	JTextArea _jtaOut;
	JFileChooser fc;

	public QuickSortGUI() {
		super(new BorderLayout());

		// Choose input
		
		
		// Input area;
		TitledBorder borderInArea = BorderFactory.createTitledBorder(Messages.getString("ui.titleInputArea")); //$NON-NLS-1$
		_jtaIn = new JTextArea(5, 100);
		_jtaIn.setEditable(true);
		_jtaIn.setLineWrap(true);
		
		JScrollPane jspIn = new JScrollPane(_jtaIn);
		JPanel jpInArea = new JPanel();
		
		_jbtnClearIn = new JButton(Messages.getString("ui.nameButtonDeleteField")); //$NON-NLS-1$
		_jbtnClearIn.addActionListener(this);
	
		jpInArea.setBorder(borderInArea);
		jpInArea.add(jspIn, BorderLayout.PAGE_START);
		jpInArea.add(_jbtnClearIn, BorderLayout.CENTER);

		// Output area
		TitledBorder borderOutArea = BorderFactory.createTitledBorder(Messages.getString("ui.titleOutputArea")); //$NON-NLS-1$
		_jtaOut = new JTextArea(5, 100);
		_jtaOut.setEditable(true);
		_jtaOut.setLineWrap(true);
		
		JScrollPane jspOut = new JScrollPane(_jtaOut);
		JPanel jpOutArea = new JPanel();
		
		_jbtnClearOut = new JButton(Messages.getString("ui.nameButtonDeleteField")); //$NON-NLS-1$
		_jbtnClearOut.addActionListener(this);
	
		jpOutArea.setBorder(borderOutArea);
		jpOutArea.add(jspOut, BorderLayout.PAGE_START);
		jpOutArea.add(_jbtnClearOut, BorderLayout.CENTER);

		// Create a file chooser
		fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		_jbtnSort = new JButton(Messages.getString("ui.nameButtonSort")); //$NON-NLS-1$
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
			String[] parts = line.split(Messages.getString("ui.comma")); //$NON-NLS-1$

			List<String> list = new ArrayList<String>();
			for (int i = 0; i < parts.length; i++) {
				list.add(String.valueOf(parts[i]).intern());
			}
			Collections.sort(list, String.CASE_INSENSITIVE_ORDER);

			for (int i = 0; i < list.size(); i++) {
				_jtaOut.append(list.get(i));
				if (i < list.size() - 1)
					_jtaOut.append(Messages.getString("ui.comma")); //$NON-NLS-1$
			}
		} else if (ae.getSource() == _jbtnClearIn) {
			_jtaIn.setText("");	 //$NON-NLS-1$
		} else if (ae.getSource() == _jbtnClearOut) {
			_jtaOut.setText("");	 //$NON-NLS-1$
		}
	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame(Messages.getString("ui.programTitle")); //$NON-NLS-1$
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new QuickSortGUI());
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE); //$NON-NLS-1$
				createAndShowGUI();
			}
		});
	}
}
