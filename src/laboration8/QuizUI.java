package laboration8;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class QuizUI extends JPanel {
	private QuizController controller;
	private JTextField tfIp = new JTextField("195.178.227.53");
	private JTextField tfPort = new JTextField("7721");
	private JTextField tfQuestionIndex = new JTextField();
	private JTextField tfAnswer = new JTextField();
	private JTextArea textarea = new JTextArea();
	private JButton btnConnection = new JButton("Anslut");

	public QuizUI(QuizController controller) { 
		this.controller = controller;
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(textarea);
		scroll.setPreferredSize(new Dimension(400,150));
		setLayout(new BorderLayout());
		add(mainPanel(),BorderLayout.NORTH);
		add(scroll,BorderLayout.CENTER);
	}

	private JPanel connectionPanel() {
		JPanel pnlConnection = new JPanel(new BorderLayout());
		JPanel pnlInfo = new JPanel(new BorderLayout());
		JPanel pnlTitles = new JPanel(new GridLayout(2,1));
		JPanel pnlInput = new JPanel(new GridLayout(2,1));

		btnConnection.addActionListener(new ConnectListener());
		pnlTitles.add(new JLabel("Ip-adress:"));
		pnlTitles.add(new JLabel("Portnummer:"));
		pnlInput.add(tfIp);
		pnlInput.add(tfPort);
		pnlInfo.add(pnlTitles,BorderLayout.WEST);
		pnlInfo.add(pnlInput,BorderLayout.CENTER);

		pnlConnection.add(pnlInfo,BorderLayout.CENTER);
		pnlConnection.add(btnConnection,BorderLayout.SOUTH);
		return pnlConnection;
	}

	private JPanel questionPanel() {
		JPanel pnlQuestion = new JPanel(new GridLayout(1,4));

		tfQuestionIndex.setEnabled(false);
		tfQuestionIndex.addActionListener(new Question());
		tfAnswer.setEnabled(false);
		tfAnswer.addActionListener(new Answer());

		pnlQuestion.add(new JLabel("Fråga nr (1-20):",JLabel.RIGHT));
		pnlQuestion.add(tfQuestionIndex);
		pnlQuestion.add(new JLabel("Svar:",JLabel.RIGHT));
		pnlQuestion.add(tfAnswer);
		return pnlQuestion;
	}

	private JPanel mainPanel() {
		JPanel pnlMain = new JPanel(new BorderLayout());
		JLabel lblTitle = new JLabel("FRÅGESPORT",JLabel.CENTER);

		lblTitle.setForeground(Color.black);
		lblTitle.setFont(new Font("SansSerif",Font.BOLD,18));

		pnlMain.add(lblTitle,BorderLayout.NORTH);
		pnlMain.add(connectionPanel(),BorderLayout.CENTER);
		pnlMain.add(questionPanel(),BorderLayout.SOUTH);
		return pnlMain;
	}
	
	public void setText(final String txt) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				textarea.setText(txt);	
			}
		});
	}
	
	public void setTextTest(final String txt){
		setText(txt);
	}
	
	public void appendText(final String txt) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
		        textarea.append(txt);
			}
		});
	}
	
	public void waitingForConnection(final String txt) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setText(txt);
				btnConnection.setEnabled(false);
			}
		});
	}
	
	public void connected(final String txt) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
		        btnConnection.setText("Koppla ner");
		        btnConnection.setEnabled(true);
		        tfQuestionIndex.setEnabled(true);
		        tfQuestionIndex.setText("");
		        tfAnswer.setEnabled(false);
		        tfAnswer.setText("");
		        tfIp.setEnabled(false);
		        tfPort.setEnabled(false);
		        setText(txt);
			}
		});
	}
	
	public void disconnected() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				btnConnection.setText("Anslut");
				tfQuestionIndex.setEnabled(false);
				tfQuestionIndex.setText("");
				tfAnswer.setEnabled(false);
				tfAnswer.setText("");
				tfIp.setEnabled(true);
				tfPort.setEnabled(true);
			}
		});
	}
	
	public void waitingForResponse() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				tfQuestionIndex.setEnabled(false);
				tfAnswer.setEnabled(false);
			}
		});
	}

	public void enableQuestion() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				tfQuestionIndex.setEnabled(true);
				tfQuestionIndex.setText("");
				tfQuestionIndex.requestFocus();
				tfAnswer.setText("");
			}
		});
	}

	public void enableAnswer() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				tfAnswer.setEnabled(true);
				tfAnswer.requestFocus();
			}
		});
	}
	
	private class ConnectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(btnConnection.getText().equals("Anslut")) {
				try {
					controller.connect();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				controller.disconnect();
			}
		}
	}
    
    private class Question implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
				controller.question();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }
    
    private class Answer implements ActionListener {
        public void actionPerformed(ActionEvent e) {
			controller.answer();
        }
    }
    
    public String getIp(){
    	return tfIp.getText();
    }
    
    public int getPort(){
    	return Integer.parseInt(tfPort.getText());
    }
    
    public int getQuestionIndex(){
    	return Integer.parseInt(tfQuestionIndex.getText());
    }
    
}
