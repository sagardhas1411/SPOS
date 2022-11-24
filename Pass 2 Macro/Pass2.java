import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
class Pass2{
	public static void main(String[] args) throws Exception 
	{
		MacroNameTable MNT[] = new MacroNameTable[5];
		int iMNT = 0;
		MacroDefinitionTable MDT[] = new MacroDefinitionTable[20];
		int iMDT = 0 , iMDTP , arg[] = new int[5];
		String d,d1[]=null,put;
		{
			FileReader f = new FileReader("MNT.txt");
			BufferedReader bw = new BufferedReader(f);
			String data = bw.readLine();
			String data1[] = null;
			do
			{
				data1 = data.split(" ");
				MNT[iMNT] = new MacroNameTable();
				MNT[iMNT].name = data1[0];
				MNT[iMNT].address = data1[1];
				iMNT++;
				data = bw.readLine();
			}while(data!=null);
			bw.close();
		}
		{
			FileReader f = new FileReader("MDT.txt");
			BufferedReader bw = new BufferedReader(f);
			String data = bw.readLine();
			do
			{
				MDT[iMDT] = new MacroDefinitionTable();
				MDT[iMDT].def = data;
				iMDT++;
				data = bw.readLine();
			}while(data!=null);
			bw.close();
		}
		File fi = new File("output_pass2.txt");
		FileReader f = new FileReader("output_pass1.txt");
		BufferedReader br = new BufferedReader(f);
		BufferedWriter bw = new BufferedWriter(new FileWriter(fi));
		d = br.readLine();
		do
		{
			d1 = d.split(" ");
			int i = searchMNT(d1[0],iMNT,MNT);
			if(i != -1)
			{
				iMDTP = Integer.parseInt(MNT[i].address);
				String d2[]=null,str[]=null;
				d2 = MDT[iMDTP].def.split(",");
				for(int k=0;k<d2.length;k++)
				{
					if(d2[k].contains("="))
					{
						str=d2[k].split("=");
						MNT[i].arg[k]=str[1];
					}
				}
				iMDTP = iMDTP + 1;
				d1 = d1[1].split(",");
				int j;
				for(j=0 ; j<d1.length ; j++)
				{
					MNT[i].arg[j] = d1[j];
				}
				while(!MDT[iMDTP].def.equalsIgnoreCase("mend"))
				{
					d1 = MDT[iMDTP].def.split(" ");
					put = d1[0]+" ";
					d1 = d1[1].split(",");
					for(int k = 0 ; k < 2 ; k++)
					{
						if(d1[k].matches("[0-9]+"))
						{
							put = put + MNT[i].arg[Integer.parseInt(d1[k])];
						}
						else
							put = put + d1[k];
						if(k < 1)
							put = put + ",";
					}
					bw.write(put+"\n");
					iMDTP++;
				}
			}
			else
			{
				bw.write(d+"\n");
			}
			d = br.readLine();
		}while(d != null);
		br.close();
		bw.close();
	}
	private static int searchMNT(String string , int iMDT , MacroNameTable[] MNT) {
		for(int i=0 ; i < iMDT ; i++ )
		{
			if(MNT[i].name.equalsIgnoreCase(string))
			{
				return i;
			}
		}
		return -1;
	}
}
