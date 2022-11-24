import java.io.*;
class Pass1
{
	public static void main(String args[])throws Exception
	{
		FileReader FP=new FileReader(args[0]);
		BufferedReader bufferedReader = new BufferedReader(FP);		
		
		String line=null;
		int line_count=0,LC=0,symTabLine=0,opTabLine=0,litTabLine=0,poolTabLine=0;
		  
		 //Data Structures
		final int MAX=100;
		String SymbolTab[][]=new String[MAX][3];
		String OpTab[][]=new String[MAX][3];
		String LitTab[][]=new String[MAX][2];
		int PoolTab[]=new int[MAX];
		int litTabAddress=0;
/*---------------------------------------------------------------------------------------------------*/
		 
		System.out.println("___________________________________________________");
		while((line = bufferedReader.readLine()) != null) {
		 	String[] tokens = line.split("\t");
		 	if(line_count==0)
		 	{
		 		LC=Integer.parseInt(tokens[2]);					
				//set LC to operand of START
		 		for(int i=0;i<tokens.length;i++)		//for printing the input program
		 	 		System.out.print(tokens[i]+"\t");
		 	 		System.out.println("");
		 	}
		 	else
		 	{
		     	 for(int i=0;i<tokens.length;i++) //for printing the input program
		     	 	System.out.print(tokens[i]+"\t");
		     	 System.out.println("");
		     	if(!tokens[0].equals(""))
		     	{
		 
		     		//Inserting into Symbol Table
		     		SymbolTab[symTabLine][0]=tokens[0];
		     		SymbolTab[symTabLine][1]=Integer.toString(LC);
		     		SymbolTab[symTabLine][2]=Integer.toString(1);
		     		symTabLine++;
		     	}
				else if(tokens[1].equalsIgnoreCase("DS")||tokens[1].equalsIgnoreCase("DC"))
				{
					//Entry into symbol table for declarative statements
					SymbolTab[symTabLine][0]=tokens[0];
		    	 		SymbolTab[symTabLine][1]=Integer.toString(LC);
		    	 		SymbolTab[symTabLine][2]=Integer.toString(1);
		    	 		symTabLine++;
				}

				if(tokens.length==3 && tokens[2].charAt(0)=='=')
				{
					//Entry of literals into literal table
					LitTab[litTabLine][0]=tokens[2];
		    	 		LitTab[litTabLine][1]=Integer.toString(LC);
		    	 		litTabLine++;
				}
				else if(tokens[1]!=null)
				{
					//Entry of Mnemonic in opcode table
					OpTab[opTabLine][0]=tokens[1];
					if(tokens[1].equalsIgnoreCase("START"))
					{
		    	 		OpTab[opTabLine][1]="AD";
						OpTab[opTabLine][2]="01";					
					}	
					else if (tokens[1].equalsIgnoreCase("END")){
						OpTab[opTabLine][1]="AD";
						OpTab[opTabLine][2]="02";
					}
					else if (tokens[1].equalsIgnoreCase("ORIGIN")){
						OpTab[opTabLine][1]="AD";
						OpTab[opTabLine][2]="03";
					}
					else if (tokens[1].equalsIgnoreCase("EQU")){
						OpTab[opTabLine][1]="AD";
						OpTab[opTabLine][2]="04";
					}
					else if (tokens[1].equalsIgnoreCase("LTORG")){
						OpTab[opTabLine][1]="AD";
						OpTab[opTabLine][2]="05";
					}
					else if(tokens[1].equalsIgnoreCase("DS"))
					{
						OpTab[opTabLine][1]="DL";
						OpTab[opTabLine][2]="02";					
					}
					else if(tokens[1].equalsIgnoreCase("DC")){
						OpTab[opTabLine][1]="DL";
						OpTab[opTabLine][2]="01";
					}
					else if(tokens[1].equalsIgnoreCase("STOP"))
					{
						OpTab[opTabLine][1]="IS";
						OpTab[opTabLine][2]="00";
					}
					else if(tokens[1].equalsIgnoreCase("ADD"))
					{
						OpTab[opTabLine][1]="IS";
						OpTab[opTabLine][2]="01";
					}
					else if(tokens[1].equalsIgnoreCase("SUB"))
					{
						OpTab[opTabLine][1]="IS";
						OpTab[opTabLine][2]="02";
					}
					else if(tokens[1].equalsIgnoreCase("MULT"))
					{
						OpTab[opTabLine][1]="IS";
						OpTab[opTabLine][2]="03";
					}
					else if(tokens[1].equalsIgnoreCase("MOVER"))
					{
						OpTab[opTabLine][1]="IS";
						OpTab[opTabLine][2]="04";
					}
					else if(tokens[1].equalsIgnoreCase("MOVEM"))
					{
						OpTab[opTabLine][1]="IS";
						OpTab[opTabLine][2]="05";
					}
					else if(tokens[1].equalsIgnoreCase("DIV"))
					{
						OpTab[opTabLine][1]="IS";
						OpTab[opTabLine][2]="06";
					}
					else if(tokens[1].equalsIgnoreCase("BC"))
					{
						OpTab[opTabLine][1]="IS";
						OpTab[opTabLine][2]="07";
					}
					else if(tokens[1].equalsIgnoreCase("COMP"))
					{
						OpTab[opTabLine][1]="IS";
						OpTab[opTabLine][2]="08";
					}
					else if(tokens[1].equalsIgnoreCase("READ"))
					{
						OpTab[opTabLine][1]="IS";
						OpTab[opTabLine][2]="09";
					}
					else if(tokens[1].equalsIgnoreCase("PRINT"))
					{
						OpTab[opTabLine][1]="IS";
						OpTab[opTabLine][2]="10";
					}
		    	 	opTabLine++;
				}
		    }
		    line_count++;
		    LC++;
		}   

		System.out.println("___________________________________________________");  

		//print symbol table
		System.out.println("\n\n	SYMBOL TABLE		");
		System.out.println("--------------------------");			
		System.out.println("SYMBOL\tADDRESS\tLENGTH");
		System.out.println("--------------------------");			
		for(int i=0;i<symTabLine;i++)
			System.out.println(SymbolTab[i][0]+"\t"+SymbolTab[i][1]+"\t"+SymbolTab[i][2]);
		System.out.println("--------------------------");
		//print opcode table
		System.out.println("\n\n	OPCODE TABLE		");
		System.out.println("----------------------------");			
		System.out.println("MNEMONIC\tCLASS\tINFO");
		System.out.println("----------------------------");			
		for(int i=0;i<opTabLine;i++)
			System.out.println(OpTab[i][0]+"\t\t"+OpTab[i][1]+"\t"+OpTab[i][2]);
		System.out.println("----------------------------");
		//print literal table
		System.out.println("\n\n   LITERAL TABLE		");
		System.out.println("-----------------");			
		System.out.println("LITERAL\tADDRESS");
		System.out.println("-----------------");			
		for(int i=0;i<litTabLine;i++)
			System.out.println(LitTab[i][0]+"\t"+LitTab[i][1]);
		System.out.println("------------------");
		
		//intialization of POOLTAB
		for(int i=0;i<litTabLine;i++)
		{
			if(LitTab[i][0]!=null && LitTab[i+1][0]!=null ) //if literals are present
			{
				if(i==0)
				{
					PoolTab[poolTabLine]=i+1;
					poolTabLine++;
				}
				else if(Integer.parseInt(LitTab[i][1])<(Integer.parseInt(LitTab[i+1][1]))-1)
				{	
					PoolTab[poolTabLine]=i+2;
					poolTabLine++;
				}
			}
		}
		//print pool table
		System.out.println("\n\n   POOL TABLE		");
		System.out.println("-----------------");			
		System.out.println("LITERAL NUMBER");
		System.out.println("-----------------");			
		for(int i=0;i<poolTabLine;i++)
			System.out.println(PoolTab[i]);
		System.out.println("------------------");
		
	
	    // Always close files.
	    bufferedReader.close();
	}
}
