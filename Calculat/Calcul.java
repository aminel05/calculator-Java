package Calculat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calcul implements ActionListener{
	
	JFrame frame;
	JPanel board,panel,sidpanel;
	JButton[][] buttons;
	JTextField text;
	int numbers,k;
	double total=0,num;
	String[] operation;
	boolean onedote=true,firstcal=true,after_calc=true,turn=true;
	String[][] name = {
            {"C", "-/+","%","/"},
            {"7", "8", "9", "*"},
            {"4", "5", "6", "-"},
            {"1", "2", "3", "+"},
            {"0", "00","." ,"="}
        };
	ImageIcon img;
	Calcul(){
		operation = new String[2];
		img = new ImageIcon(getClass().getResource("cal.png"));
		operation[0] = new String();
		operation[1] = new String();
		
		frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Calculator (by amine)");
        frame.setIconImage(img.getImage());
				
        board = new JPanel(new GridLayout());
        board.setPreferredSize(new Dimension(400, 120));
        board.setLocation(0, 0);
        frame.add(board,BorderLayout.NORTH);
        
        text = new JTextField();
        text.setBackground(Color.BLACK);
        text.setFont(new Font(".", Font.PLAIN, 50));
        text.setForeground(Color.WHITE);
        text.setHorizontalAlignment(JTextField.CENTER);
        text.setCaretColor(Color.BLACK);
        text.setText("0");
        
        board.add(text);
		
        panel = new JPanel(new GridLayout(5,3)); 
        panel.setPreferredSize(new Dimension(300, 500));
        panel.setLocation(0, 100);
        frame.add(panel,BorderLayout.CENTER);
        
        
        
        
		
		buttons = new JButton[5][4];
		for (int i = 0; i < 5; i++) {
		    for (int j = 0; j < 4; j++) {
		        String label = name[i][j];
		            buttons[i][j] = new JButton(label);
		            buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 50));
		            buttons[i][j].setBackground(Color.GRAY);
		            buttons[i][j].setFocusable(false);
		            buttons[i][j].addActionListener(this);
		            panel.add(buttons[i][j]);
		        
		    }
		}
		
		
		frame.pack();
        frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void oper() {
		turn=true;
		if(operation[1].equals("equals")) {
			turn=false;
		}
					if(operation[0].equals("dev")||(operation[0].equals("dev")&& operation[1].equals("equals"))) {
						total=total/num;
						operation[0]="";
					}
					if(operation[0].equals("mult")||(operation[0].equals("mult")&& operation[1].equals("equals"))) {
						total=total*num;
						operation[0]="";
					}
					if(operation[0].equals("min")||(operation[0].equals("min")&& operation[1].equals("equals"))) {
						total=total-num;
						operation[0]="";
					}
					if(operation[0].equals("plus")||(operation[0].equals("plus")&& operation[1].equals("equals"))) {
						total=total+num;
						operation[0]="";
					}
		
////////////////////////////////////////////////////////////
		
					if(operation[1].equals("dev")) {
						operation[0]="dev";
						operation[1]="";
					}
					if(operation[1].equals("mult")) {
						operation[0]="mult";
						operation[1]="";
			
					}
					if(operation[1].equals("min")) {
						operation[0]="min";
						operation[1]="";
			
					}
					if(operation[1].equals("plus")) {
						operation[0]="plus";
						operation[1]="";
			
					}
					
					
					if(after_calc) {
						
					text.setText(String.valueOf(total));
					}
					
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		for(int i = 0 ; i <5 ; i++) {
			for(int j = 0 ; j <4 ; j++) {
				if(after_calc) {
					text.setText("");
					after_calc=false;
					
				}
				String temptext = text.getText();
				if(e.getSource()==buttons[i][j] && j!=3 && i!=0 &&name[i][j]!="="&&name[i][j]!=".") {
					text.setText(temptext+name[i][j]);
					num=Double.parseDouble(text.getText());
					turn=false;
					
				}
			}
			
		}
		if(e.getSource()==buttons[0][0]) {//reset
			text.setText("");
			total=0;
			num=0;
			operation[0]="";operation[1]="";
			firstcal=true;onedote=true;after_calc=true;
			
		}
		if(e.getSource()==buttons[0][1]) {//-/+
			if(!text.getText().equals("")) {
			text.setText(String.valueOf(Double.parseDouble(text.getText())*-1));
			num=Double.parseDouble(text.getText())*-1;}
			else {
				text.setText(String.valueOf(total*-1));
			}
			
		}
		if(e.getSource()==buttons[0][2]) {// %
			if(!text.getText().equals("")) {
			text.setText(String.valueOf(Double.parseDouble(text.getText())/100));
			num=Double.parseDouble(text.getText());}
			else {
				text.setText(String.valueOf(total/100));
			}
		}
		if(turn==false) {
////////////////////////////////////////////////////////////////////////////////////
		if(e.getSource()==buttons[0][3]) {//devision/
			onedote=true;
			if(firstcal==false &&Double.parseDouble(text.getText())!=0 ) {
				after_calc=true;
				operation[1]="dev";
			}
			else {
				firstcal=false;
				total=Double.parseDouble(text.getText());
				operation[1]="dev";
				after_calc=true;			}
		}
///////////////////////////////////////////////////////////////////
		if(e.getSource()==buttons[1][3]) {//produit*
			onedote=true;
			if(firstcal==false) {
				after_calc=true;
				operation[1]="mult";
				
			}
			else {
				firstcal=false;
				total=Double.parseDouble(text.getText());
				operation[1]="mult";
				after_calc=true;
			}
		}
/////////////////////////////////////////////////////////////////////////////
		if(e.getSource()==buttons[2][3]) {//minus-
			onedote=true;
			if(firstcal==false) {
				after_calc=true;
				operation[1]="min";				
			}
			else {
				firstcal=false;
				total=Double.parseDouble(text.getText());
				operation[1]="min";
				after_calc=true;
			}
		}
/////////////////////////////////////////////////////////////////////////
		if(e.getSource()==buttons[3][3]) {//plus+
			onedote=true;
			if(firstcal==false) {
				after_calc=true;
				operation[1]="plus";
				
			}
			else {
				operation[1]="plus";
				firstcal=false;
				total=Double.parseDouble(text.getText());
				after_calc=true;
			}}
////////////////////////////////////////////////////////////////////////////////
		if(e.getSource()==buttons[4][3]) {//equals=
			after_calc=true;
			//firstcal=true;
			onedote=true;
			operation[1]="equals";
			num=Double.parseDouble(text.getText());
			}	
		}
//////////////////////////////////////////////////////////////////////////////
		
		if(e.getSource()==buttons[4][2]) {//dote.
			
			if(onedote) {
				text.setText(text.getText()+".");
				onedote=false;
				if(text.getText().equals("")) {
					text.setText("0.");
					onedote=false;
				}
			}
			
		}

////////////////////////////////////////////////////////////////////////////////
		if(turn==false) {
			for(int i = 0 ; i <5 ; i++) {
				for(int j = 0 ; j <4 ; j++) {
					if((e.getSource()==buttons[i][j] && (name[i][j].equals("=") || name[i][j].equals("+") || name[i][j].equals("/") || name[i][j].equals("*") || name[i][j].equals("-"))) ) {
						oper();
						break;
					}
				}
			}
		
		
		}
		System.out.println(text.getText());

		text.repaint();
	}
}
