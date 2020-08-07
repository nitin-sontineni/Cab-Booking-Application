package cabops;

import java.lang.Math;
import java.io.*;
import database.DriverDatabase;

public class CabAssign
{
	public int arrivalTime,travelTime;
	public double cost;
	int outCoor=0;
	int a;
	public double outDist=100000,travelDist=-1;
	public int cabAssigned;
	public String cabDriver;

	public void assign(int map[][],int cabCoor[][],String cabDrivers[],int x_user,int y_user,int x_destiny,int y_destiny)
	{
		double tempcoor=0,tempDist=-1;
		for(int i=0;i<32;i++)
		{
			for(int j=0;j<32;j++)
			{
				if(map[i][j]>0)
				{
					double dist=((x_user-i)*(x_user-i))+((y_user-j)*(y_user-j));
					tempDist=Math.sqrt(dist);
					if(outDist >= tempDist)// rating to be added.
					{
						if(outDist>tempDist)
						{
							outDist=tempDist;
							outCoor=(i*100)+j;
							for(int k=0;k<30;k++)
							{
								if(cabCoor[k][1]==outCoor)
								{
									cabAssigned=cabCoor[k][0];
									cabDriver=cabDrivers[k];
									a=i;
									
									break;
								}
							}
						}
						else
						{
							for(int k=0;k<30;k++)
							{
								if(cabCoor[k][1]==(i*100)+j)
								{
									String strRating1 = DriverDatabase.getRatingByCarID(cabCoor[k][0]+"");
									double rating1 = Double.parseDouble(strRating1);
									
									String strRating2 = DriverDatabase.getRatingByCarID(cabAssigned+"");
									double rating2 = Double.parseDouble(strRating2);
									
									if(rating1>rating2)
									{
										cabAssigned=cabCoor[k][0];
										cabDriver=cabDrivers[k];
										a=i;
										
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		cabCoor[cabAssigned-1001][1]=-1000;
		map[outCoor/100][outCoor%100]--;
		double tdist=((x_user-x_destiny)*(x_user-x_destiny))+((y_user-y_destiny)*(y_user-y_destiny));
		travelDist=Math.sqrt(tdist)+outDist;
		travelTime=(int)(travelDist*1.32);
		
		arrivalTime=(int)(outDist*1.32);
		cost=((travelDist-outDist)*(11.3));
		System.out.println("outDist is " + outDist);
	}
	public void notAssign(int map[][], int cabCoor[][])
	{
		cabCoor[cabAssigned-1001][1]=outCoor;
		map[outCoor/100][outCoor%100]++;
	}
	
	public void completedTrip(int map[][],int cabCoor[][],int x_destiny,int y_destiny)
	{
		cabCoor[cabAssigned-1001][1]=(x_destiny*100)+y_destiny;
		map[x_destiny][y_destiny]++;
	}
}	






////////////////
