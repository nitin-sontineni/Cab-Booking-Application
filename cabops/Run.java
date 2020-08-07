package cabops;

public class Run
{
	private static int tot;
	private static int[][] iniMod=new int [2][2];
	private static int[][] finMod=new int [2][2];
	
	private static int readcabs(int map[][],int startx,int starty,int dim)
	{
		int specificblock_runningcabs=0;
		for(int i=startx; i<(startx+dim); i++)
		{
			for(int j=starty; j<(starty+dim); j++)
			{
					specificblock_runningcabs=specificblock_runningcabs+map[i][j];
			}
		}
	return specificblock_runningcabs;
	}
	private static void model(int map[][],int x_start,int y_start,int dim)
	{
		for(int i=0;i<2;i++)
		{
			for(int j=0;j<2;j++)
			{
				iniMod[i][j]=readcabs(map,((x_start)+(dim*i)),((y_start)+(dim*j)),dim);
			}
		}
		tot=readcabs(map,x_start,y_start,2*dim);
		int p=tot%4;
		int x=p;
		int y=tot/4;
		for(int i=0;i<2;i++)
		{
			for(int j=0;j<2;j++)
			{
				finMod[i][j]=y;
			}
		}
		for(int i=0;i<2;i++)
		{
			for(int j=0;j<2;j++)
			{
				if(x!=0)
				{
					if(iniMod[i][j]>=(y+1))
					{
						finMod[i][j]=y+1;
						x--;
					}
				}
			}
		}
		if(x!=0)
		{
			for(int i=0;i<2;i++)
			{
				for(int j=0;j<2;j++)
				{
					if(x!=0)
					{
						if(finMod[i][j]!=(y+1))
						{
							finMod[i][j]=(y+1);
							x--;
						}
					}
				}
			}
		}
		if(p==2)
		{
			if(finMod[0][0]==y)
			{
				finMod[1][1]=y;
				finMod[1][0]=finMod[0][1]=y+1;
			}
			else
			{
				finMod[1][1]=y+1;
				finMod[1][0]=finMod[0][1]=y;
			}
		}
	}
	private static int findCab(int cabCoor[][],int loc)
	{
		int k=0;
		for(int i=0;i<30;i++)
		{
			if(cabCoor[i][1]==loc)
			{
				k=cabCoor[i][0];
				break;
			}
		}
		return k;
	}
	private static int[] moveMod()
	{
		int[] mov = new int[2];
		int from=0,to=0;
		int x=1;
		first:for(int i=0;i<2;i++)
		{
			for(int j=0;j<2;j++)
			{
				if(finMod[i][j]<iniMod[i][j])
				{
					from=x;
					break first;
				}
				x++;
			}
		}
		x=1;
		first:for(int i=0;i<2;i++)
		{
			for(int j=0;j<2;j++)
			{
				if(finMod[i][j]>iniMod[i][j])
				{
					to=x;
					break first;
				}
				x++;
			}
		}
			mov[0]=from;
			mov[1]=to;
		return mov;
	}
	private static void searchAndMove(int map[][],int cabCoor[][],int startx,int starty,int mov[],int dim)
	{
		int from=mov[0];
		int to=mov[1];
		int tempdist=1000,leastdist=100000,fincar=0,finpos=0;
		int cari=0,carj=0,posi=0,posj=0;
		if(from==1)
		{
			cari=startx;
			carj=starty;
		 	iniMod[0][0]--;
		}
		if(from==2)
		{
			cari=startx;
		 	carj=starty+(dim);
		 	iniMod[0][1]--;
		}
		if(from==3)
		{
		 	cari=startx+(dim);
		 	carj=starty;
		 	iniMod[1][0]--;
		}
		if(from==4)
		{
		 	cari=startx+(dim);
		 	carj=starty+(dim);
		 	iniMod[1][1]--;
		}
		if(to==1)
		{
		 	posi=startx;
		 	posj=starty;
		 	iniMod[0][0]++;
		}
		if(to==2)
		{
		 	posi=startx;
		 	posj=starty+(dim);
		 	iniMod[0][1]++;
		}
		if(to==3)
		{
		 	posi=startx+(dim);
		 	posj=starty;
		 	iniMod[1][0]++;
		}
		if(to==4)
		{
		 	posi=startx+(dim);
		 	posj=starty+(dim);
		 	iniMod[1][1]++;
		}
		for(int i=cari;i<(cari+(dim));i++)
		{
			for(int j=carj;j<(carj+(dim));j++)
			{
				if(map[i][j]>0)
				{
					for(int k=posi;k<(posi+(dim));k++)
					{
						for(int l=posj;l<(posj+(dim));l++)
						{
							if(map[k][l]==0)
							{
								tempdist=((k-i)*(k-i))+((j-l)*(j-l));
								if(leastdist>tempdist)
								{
									leastdist=tempdist;
									fincar=(i*100)+j;
									finpos=(k*100)+l;
								}
							}
						}
					}
				}
			}
		}
		cabCoor[findCab(cabCoor,fincar)-1001][1]=finpos;
		map[fincar/100][fincar%100]--;
		map[finpos/100][finpos%100]++;
	}
	public static void reassign(int dim,int startx,int starty,int map[][],int cabCoor[][]) //Run.reassign(16, 0, 0, map, cabCoor) 
	{
		model(map,startx,starty,dim);
		for(int i=0;i<30;i++)
		{
			searchAndMove(map,cabCoor,startx,starty,moveMod(),dim);
		}
		if(dim>2)
		{
			reassign(dim/2,startx,starty,map,cabCoor);
			reassign(dim/2,startx+(dim),starty,map,cabCoor);
			reassign(dim/2,startx,starty+(dim),map,cabCoor);
			reassign(dim/2,startx+(dim),starty+(dim),map,cabCoor);
		}
	}
}
