import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class Metodos 
{
        
	
    public int K1, K2;
    public static final int P10[] = { 3, 5, 2, 7, 4, 10, 1, 9, 8, 6};
    public static final int P10max = 10;
    public static final int P8[] = { 6, 3, 7, 4, 8, 5, 10, 9};
    public static final int P8max = 10;
    public static final int P4[] = { 2, 4, 3, 1};
    public static final int P4max = 4;
    public static final int IP[] = { 2, 6, 3, 1, 4, 8, 5, 7};
    public static final int IPmax = 8;
    public static final int IPI[] = { 4, 1, 3, 5, 7, 2, 8, 6};
    public static final int IPImax = 8;
    public static final int EP[] = { 4, 1, 2, 3, 2, 3, 4, 1};
    public static final int EPmax = 4;
    public static final int S0[][] = {{ 1, 0, 3, 2},{ 3, 2, 1, 0},{ 0, 2, 1,
                                                          3},{ 3, 1, 3, 2}};
    public static final int S1[][] = {{ 0, 1, 2, 3},{ 2, 0, 1, 3},{ 3, 0, 1,
                                                          2},{ 2, 1, 0, 3}};

	public Metodos(int k) 
	{
        k = permute( k, P10, P10max);
        int t1 = (k >> 5) & 0x1F;
        int t2 = k & 0x1F;
        t1 = ((t1 & 0xF) << 1) | ((t1 & 0x10) >> 4);
        t2 = ((t2 & 0xF) << 1) | ((t2 & 0x10) >> 4);
        K1 = permute( (t1 << 5)| t2, P8, P8max);
        t1 = ((t1 & 0x7) << 2) | ((t1 & 0x18) >> 3);
        t2 = ((t2 & 0x7) << 2) | ((t2 & 0x18) >> 3);
        K2 = permute( (t1 << 5)| t2, P8, P8max);
		
	}
   
	public static int permute( int x, int p[], int pmax)
    {
      int y = 0;
      for( int i = 0; i < p.length; ++i)
       {
         y <<= 1;
         y |= (x >> (pmax - p[i])) & 1;
     }
      return y;
    }

	  public static int fK( int m, int K)
      {
          int L = (m >> 4) & 0xF;
          int R = m & 0xF;
          return ((L ^ F(R,K)) << 4) | R;
      }
	  
      public static int F( int R, int K)
      {
         int t = permute( R, EP, EPmax) ^ K;
         int t0 = (t >> 4) & 0xF;
         int t1 = t & 0xF;
         t0 = S0[ ((t0 & 0x8) >> 2) | (t0 & 1) ][ (t0 >> 1) & 0x3 ];
         t1 = S1[ ((t1 & 0x8) >> 2) | (t1 & 1) ][ (t1 >> 1) & 0x3 ];
          t = permute( (t0 << 2) | t1, P4, P4max);
        return t;
      
 }

     
public static int SW( int x)
{
 return ((x & 0xF) << 4) | ((x >> 4) & 0xF);
}
	
    public byte encrypt( int m)
    
    
    {
	    	System.out.println("\n*****Procedimiento de Encriptacion***** \n"); 
	    	m = permute( m, IP, IPmax);
		     System.out.print("\nAplicando IP: " + dtob(m));
		      m = fK( m, K1);
		      System.out.print("\nAntes del Swap: " + dtob(m));
		      m = SW( m);
		      System.out.print("\nDespues del Swap: " + dtob(m));
		      m = fK( m, K2);
		      System.out.print("\nDespues de Fk " + dtob(m) + "\n\n");
		      m = permute( m, IPI, IPImax);
		      return (byte) m;
	   
    }

    public byte decrypt( int m)
    
    {
      System.out.println("\n*****Procedimiento de Desencriptacion*****\n");
      m = permute( m, IP, IPmax);
      System.out.print("\nDespues de la permutuacion: " + dtob(m));
      m = fK( m, K2);
      System.out.print("\nAntes del Swap: " + dtob(m));
      m = SW( m);
      System.out.print("\nDespues del Swap : " + dtob(m));
      m = fK( m, K1);
      m = permute( m, IPI, IPImax);
      System.out.print("\nDespues de la permutuacion de extraccion: " + dtob(m));

      return (byte) m;
   
    }
    
    public  String dtob (int x )// regresa string de binario de 8 bits
    {
    	String s = ("00000000" +(Integer.toBinaryString(x)));
    	s =s.substring(s.length()-8) ;
    	return s;
    	
    }
    
    public  String dtob2 (int x )// regresa string de binario de 10 bits
    {
    	String s = ("0000000000" +(Integer.toBinaryString(x)));
    	s =s.substring(s.length()-10) ;
    	return s;
    	
    }
    
    public int getLine () throws IOException
    {
    	
    
		LineNumberReader  lnr = new LineNumberReader(new BufferedReader(new FileReader("SamplePairs.txt")));
		lnr.skip(Long.MAX_VALUE);
		lnr.close();
		return lnr.getLineNumber();

		
    }
    
    public String[] getArray () throws IOException
    {
		int k =0;
		String [] arry0 = new String[getLine() * 2];

		try {
			BufferedReader in = new BufferedReader(new FileReader("SamplePairs.txt"));
			String str;
			
			
			 ArrayList<String> list = new ArrayList<String>();
		        while((str = in.readLine()) != null){
		        	str.split(",");
		        	list.add(str);
		        }

		        String[] stringArr = list.toArray(new String[0]);
		      
		        for (String s:stringArr)
		        {
		        	String [] s2 = s.split(",");
		        	
		        	for (String results : s2)
		        	{
		        		arry0[k]= results;
		        		
		        		k++;
		        	}
		        }
			 
		} catch (FileNotFoundException e1) {	} catch (IOException e1) {}
		
		return arry0;
		
    }
    
    public int getPopularElement(Integer[] a)
    {
      int count = 1, tempCount;
      int popular = a[0];
      int temp = 0;
      for (int i = 0; i < (a.length - 1); i++)
      {
        temp = a[i];
        tempCount = 0;
        for (int j = 1; j < a.length; j++)
        {
          if (temp == a[j])
            tempCount++;
        }
        if (tempCount > count)
        {
          popular = temp;
          count = tempCount;
        }
      }
      return popular;
    }
    
    public byte bruteForce( int m)
    
    
    {
	    	
		  m = permute( m, IP, IPmax);	     
		  m = fK( m, K1);		    
		  m = SW( m);
		  m = fK( m, K2);
		  m = permute( m, IPI, IPImax);
		  return (byte) m;
	   
    }
}


