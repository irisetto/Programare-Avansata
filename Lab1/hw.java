import java.util.*;

class CommandLine {
 
    public static void main(String args[]) {
      
          System.out.println(args[0]);
          
          try 
		{ 
			Integer.parseInt(args[0]); 
			System.out.println(args[0] + " is a valid integer"); 
		}  
		catch (NumberFormatException e)  
		{ 
			throw new NumberFormatException(args[0]+" is not a valid integer"); 
            
		} 

        long startTime = System.nanoTime();
        Integer n=Integer.parseInt(args[0]);
        Integer[][] latinMatrix=new Integer[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                latinMatrix[i][j]=(i+j)%n+1;
  
            }

        }
        String row= "";
        String col= "";
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                 row=row+latinMatrix[i][j];
                 col=col+latinMatrix[j][i];
            }
            if(n<10){
            System.out.print("Row "+(i+1)+": "+row  + "\t");
            System.out.print("Column "+(i+1)+": "+col  + "\t");
            System.out.println();
            }
            col="";
            row="";

        }
        long estimatedTime = System.nanoTime() - startTime;
System.out.println(estimatedTime);
    }
 }