import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

import javax.swing.*;

public class TextAnalysisFrame extends JFrame{
	JPanel panel1;
	JPanel panel2;
	JPanel panel3;

	public TextAnalysisFrame(String title){
		super(title);
		this.setSize(600, 600);
		this.setLocation(300, 100);
		this.setLayout(new BorderLayout());
		
		TextAnalysis txtAnal = new TextAnalysis();
		
		//create panels
		panel1 = new JPanel();
		panel1.setBackground(Color.DARK_GRAY);
		panel2 = new JPanel();
		panel2.setBackground(Color.DARK_GRAY);
		panel3 = new JPanel();
		panel3.setBackground(Color.DARK_GRAY);
		

		
		//-- Panel 2 --
		//words display textArea
		JTextArea uniqWordBox = new JTextArea("Unique words displayed here",30,20);
		JScrollPane scrollPane2 = new JScrollPane(uniqWordBox);
		setPreferredSize(new Dimension(10, 20));
		
		JTextArea originalTxtBox = new JTextArea(".txt file displayed here", 30,20);
		JScrollPane scrollPane3 = new JScrollPane(originalTxtBox);
		setPreferredSize(new Dimension(10, 20));
		
		panel2.add(scrollPane2);
		panel2.add(scrollPane3);
		
		//-- Panel 3 --		
		//word freq display
		JTextArea wordFreqDisplay = new JTextArea("word freq display", 5, 20);
		
		//search bar
		JTextField searchBar = new JTextField("search word",15);
		searchBar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int count = txtAnal.wordSearch(searchBar.getText());
				wordFreqDisplay.setText("" + count);
			}			
		});
		
		//sort words button
		JButton button2 = new JButton("sort words");
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtAnal.sortByFrequency();
				uniqWordBox.setText(txtAnal.getUniqueWordCount() + "\n" + txtAnal.getWords());
			}			
		});
		
		
		
		panel3.add(searchBar);
		panel3.add(wordFreqDisplay);
		panel3.add(button2);
		
		//-- Panel 1 --
		//file textArea
		JTextArea textArea = new JTextArea("input .txt file",5, 30);
		JScrollPane scrollPane = new JScrollPane(textArea);
		setPreferredSize(new Dimension(450, 90));
		
		//analysis file button
		JButton button = new JButton("Get & Analysis File");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtAnal.setFile(textArea.getText());
				try {
					txtAnal.analysisFile();
					originalTxtBox.setText(txtAnal.getOriginalTxt());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				uniqWordBox.setText(txtAnal.getUniqueWordCount() + "\n" + txtAnal.getWords());
			}			
		});

		
		panel1.add(scrollPane, BorderLayout.EAST);
		panel1.add(button);
		
		//adding panels to frame
		this.add(panel1,"North");
		this.add(panel2, "Center");
		this.add(panel3, "South");
	}
}
