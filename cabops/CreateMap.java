package cabops;

public class CreateMap
{
	public int[][] map = new int[32][32];
	public String[] cabDrivers = new String[30];
	public int[][] cabCoor = new int[30][2]; 
	public void mapCreation()
	{
		
		for(int i=13;i<19;i++)
		{
			for(int j=13;j<18;j++)
			{
				map[i][j]=1;
			}
		}
	}
	public void cabCoorCreation()
	{
		for(int i=1;i<31;i++)
		{
			cabCoor[i-1][0]=1000+i;
		}
		int k=0;
		for(int i=13;i<19;i++)
		{
			for(int j=13;j<18;j++)
			{
				cabCoor[k][1]=(i*100)+j;
				k++;
			}
		}	
		
	cabDrivers[0]= "Advik";
    cabDrivers[1]= "Muhammad";
    cabDrivers[2]= "Reyansh";
    cabDrivers[3]= "Advait";
    cabDrivers[4]= "Ayaan";
    cabDrivers[5]= "Dhruv";
    cabDrivers[6]= "Shivansh";
    cabDrivers[7]= "Atharv";
    cabDrivers[8]= "Arjun";
    cabDrivers[9]= "Kabir";
    cabDrivers[10]= "Aarav";
    cabDrivers[11]= "Vivaan";
    cabDrivers[12] = "Ivaan";
    cabDrivers[13]= "Ishaan";
    cabDrivers[14]= "Aditya";
    cabDrivers[15]= "Vihaan";
    cabDrivers[16]= "Ethan";
    cabDrivers[17]= "Shaurya";
    cabDrivers[18]= "Rayyan";
    cabDrivers[19]= "Ahaan";
    cabDrivers[20]="Yuvaan";
    cabDrivers[21]="Rudra";
    cabDrivers[22]="Joshua";
    cabDrivers[23]="Aayansh";
    cabDrivers[24]="Aryan";
    cabDrivers[25]="Hridaan";
    cabDrivers[26]="Abeer";
    cabDrivers[27]="Joel";
    cabDrivers[28]="Rishaan";
    cabDrivers[29]= "Krishna";
	}
}
