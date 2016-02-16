

import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

import javax.swing.*;


public class NM extends JFrame implements ActionListener

{ 
	
	
	int cyphertext, desencriptado;
	
	
	JPanel panel1, panel2, panel11, panel12;
	
	JTextField tKey, tText;



	JTextArea pantalla;
	JLabel eKey, eText, tNull, ttNull;
	JButton bEncrypt, bDecrypt, bBruteForce, bBorrar;
	
	public NM ()
	{
		super("DES");
		Container contenedor = getContentPane();
		contenedor.setLayout(new GridLayout(1,2));
		
		bEncrypt= new JButton("Encrypt");
		bDecrypt= new JButton("Decrypt");
		bBruteForce= new JButton("Brute Force");
		bBorrar = new JButton("Borrar");
		bEncrypt.addActionListener(this);
		bDecrypt.addActionListener(this);
		bBruteForce.addActionListener(this);
		bBorrar.addActionListener(this);
		
		eKey= new JLabel ("Key", SwingConstants.CENTER);
		eText= new JLabel ("Text", SwingConstants.CENTER);
		tNull= new JLabel(" ");
		ttNull = new JLabel(" ");
		
		
		tKey= new JTextField();
		tText= new JTextField();
		
		pantalla= new JTextArea();
		
		panel1 = new JPanel(new GridLayout(2,1));
		panel11 = new JPanel(new GridLayout(3,2));
		panel11.add(eKey);
		panel11.add(eText);
		panel11.add(tNull);
		panel11.add(ttNull);
		panel11.add(tKey);
		panel11.add(tText);
		panel1.add(panel11);
		panel12 = new JPanel(new GridLayout(2,2));
		panel12.add(bEncrypt);
		panel12.add(bDecrypt);
		panel12.add(bBruteForce);
		panel12.add(bBorrar);
		panel1.add(panel12);
		
		panel2= new JPanel(new GridLayout(1,1));
		panel2.add(pantalla);
		
		contenedor.add(panel1);
		contenedor.add(panel2);
		
		
		
		 setSize(550,250);  
		 setResizable(true);
	     setVisible(true); 
		 setLocationRelativeTo(null);
		
	}
	
	public void appendtoarea (String text)// para escribir en la pantalla desde otra clase
	{
		pantalla.append(text);
	}
	
	
	  public static void main (String Arg[])//el metodo main
	 {
		NM run =new NM ();//creando un objeto de la clase Calculadora
		run.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 }


	@Override
	public void actionPerformed(ActionEvent e) 
	{

	
		if (e.getSource()==bEncrypt)// Boton de encriptar	
		{	
			pantalla.setText(" ");
			
			int k = Integer.parseInt(tKey.getText(), 2) ;// con ese dos convertimos el numero a decimal
			Metodos metodo= new Metodos(k);
			int t = Integer.parseInt(tText.getText(), 2);
			pantalla.append("\nLa primer llave es: "+  Integer.toBinaryString(metodo.K1));
			pantalla.append("\nLa segunda llave es: "+  Integer.toBinaryString(metodo.K2));
			
			t=metodo.encrypt(t);// t nos da un decimal
			pantalla.append("\nMensaje encriptado: "+  metodo.dtob(t));
			
			
			cyphertext=Integer.parseInt( metodo.dtob(t),2);//guardamos el cyphertext como decimal
		
		}
		
		
		if (e.getSource()==bDecrypt)
		{
			pantalla.setText(" ");
			
			int k = Integer.parseInt(tKey.getText(), 2) ;// con ese dos convertimos el numero a decimal
			Metodos metodo= new Metodos(k);
			
			cyphertext= metodo.decrypt(cyphertext);// nos da un decimal
			pantalla.append("\nMensaje Desencriptado: "+  metodo.dtob(cyphertext) );

		}
		
		if (e.getSource()==bBorrar)
		{
			pantalla.setText(" ");
			tKey.setText(" ");
			tText.setText(" ");
		}
		
		if (e.getSource()==bBruteForce)
		{	
			Metodos metodo = new Metodos (0);
                        
			
			try 
			{
			String[] array0;// arreglo del plaintext y cyphertext
			String []plainText = new String [(metodo.getLine())];//arreglo plaintext
			String [] cypherText = new String[(metodo.getLine())]	;// arreglo cyphertext
			
			array0 =metodo.getArray();//Arreglo de plain text y cyphertext
			
			int arr1=0;
			int arr2=0;	
			for (int k =0; k<array0.length;k +=2)//Hace el arreglo del plaintext
			{
				
				String mioli =array0[k];
				plainText[arr1]=mioli;
				arr1++;
			}

			for (int k =1; k<array0.length;k +=2)//Hace el arreglo del cyphertext
			{
				
				String mioli =array0[k];
				cypherText[arr2]=mioli;
				arr2++;
			}
			
		
			//System.out.println(metodo.getLine());
			
			ArrayList <Integer> list = new ArrayList<Integer>();
			for (int k=0; k<1024;k++ )
			{
				
				
				for (int pt=0; pt<plainText.length; pt++)
				{
					Metodos brute = new Metodos(k);
					int plt = Integer.parseInt(plainText[pt], 2);//Lo convierte a decimal
					plt = brute.bruteForce(plt);
					
					if (cypherText[pt].equals(brute.dtob(plt)))
					{
						list.add(k);
						//System.out.println(k);
					}
	
				}
				 
				
				
			}
				Integer [] common = list.toArray(new Integer[list.size()]);
				int popular =metodo.getPopularElement(common);//El que mas se repita es la llave!
				System.out.println("La llave es: " + metodo.dtob2(popular));
				
			
			} catch (IOException e1) {}

		}
		
	}
		
}
