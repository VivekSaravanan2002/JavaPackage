import java.io.*;
import java.util.Scanner;
import java.lang.String;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
public class JavaPackage
{
	public static void main(String[] args)
	{
		System.out.println("Connecting DataBase And Java");
		Connection con=null;
		Statement stmt=null;
		String sql;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/VotingDB","root","");
			stmt=(Statement) con.createStatement();
			sql="select * from PartiesList";
			ResultSet result=stmt.executeQuery(sql);
			while(result.next())
			{
				System.out.println("\nSerial Number:\t"+result.getString(1));
				System.out.println("Parties Name:\t"+result.getString(2));
				System.out.println("Parties Symbol:\t"+result.getString(3));
				System.out.println("Parties Year:\t"+result.getString(4));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("\nEnter The Number Of People Alloted In The Particular Both.");
		Scanner input=new Scanner(System.in);
		int Total=input.nextInt();
		int Admk=0,Dmk=0,TorchLight=0,Bjp=0,Congress=0,Nota=0;
		int arr;
		arr=Total+1;
		String[] VotersIdNumber=new String[arr];
		for(int j=1;j<=Total;j++)
		{
			System.out.println("\nEnter The Details For Verification Candidate:" +j);
			System.out.println("\nEnter Your Name Same As On Voters ID:\t");
			Scanner Value=new Scanner(System.in);
			String VotersName=Value.nextLine();
			System.out.println("\nEnter your Voters ID Number:\t");
			VotersIdNumber[j]=Value.nextLine();
			int flag=0;
			try
			{
				if(VotersIdNumber[j].length()!=10)
				{
					flag++;
					throw new VotersIdNumberValidationException("Invalid Number Or The Length Is Not Equal To 10");
				}
				for(int i=0;i<3;i++)
				{
					Boolean ch=Character.isDigit(VotersIdNumber[j].charAt(i));
					{
						if(ch)
						{
							flag++;
						}
					}
				}
				if(flag!=0)
				{
					throw new VotersIdNumberValidationException("Invalid Number Or The Length Is Not Equal To 10");
				}
			}
			catch(VotersIdNumberValidationException vid)
			{
				System.out.println("\nThe Voters Details:");
				System.out.println("\nThe Voters Name is:" +VotersName.toUpperCase());
				System.out.println("The Voters Id Number Is:" +VotersIdNumber[j].toUpperCase());
				//System.out.println(vid);
			}
			int test=0;
			for(int n=j-1;n!=0;n--)
			{
				if(VotersIdNumber[j].equals(VotersIdNumber[n]))
				{
					System.out.println("\nThe Candidate Has Already voted.");
					test=1;
					break;
				}
			}
			if(test==1)
			{
				j=j-1;
				continue;
			}
			if(flag==0)
			{
				System.out.println("\nThe Voters Details:");
				System.out.println("\nThe Voters Name is:" +VotersName.toUpperCase());
				System.out.println("The Voters Id Number Is:" +VotersIdNumber[j].toUpperCase());
				System.out.println("Verification Process Done.");
				System.out.println("You Can Proceed To Vote.");
				System.out.println("\nSelect Your Party Which You Are Intrested To Vote.");
				System.out.println("\n\t1.Admk\n\t2.Dmk\n\t3.Makkal Needhi Maiyam\n\t4.Bjp\n\t5.Congress\n\t6.Nota");
				System.out.println("\nEnter Your Choice:\t");
				int Choice=0;
				Choice=Value.nextInt();
				switch(Choice)
				{
					case 1:
					{
						System.out.println("\nYou Have Voted To The Party Admk.");
						Admk++;
						break;
					}
					case 2:
					{
						System.out.println("\nYou Have Voted To The Party Dmk.");
						Dmk++;
						break;
					}
					case 3:
					{
						System.out.println("\nYou Have Voted To The Party Makkal Needhi Maiyam.");
						TorchLight++;
						break;
					}
					case 4:
					{
						System.out.println("\nYou Have Voted To The Party Bjp.");
						Bjp++;
						break;
					}
					case 5:
					{
						System.out.println("\nYou Have Voted To The Party Congress.");
						Congress++;
						break;
					}
					case 6:
					{
						System.out.println("\nYou Have Voted At Nota.");
						Nota++;
						break;
					}
					default:
					{
						System.out.println("Invalid Choice");
					}
				}
			}
		}
		System.out.println("\nResults Of The Election");
		if(Admk>Dmk&&Admk>TorchLight&&Admk>Bjp&&Admk>Congress&&Admk>Nota)
		{
			System.out.println("\nThe Winner Of The Election Is Admk.");
			System.out.println("The Number Of Votes Voted For This Party Is:" +Admk);
		}
		else if(Dmk>Admk&&Dmk>TorchLight&&Dmk>Bjp&&Dmk>Congress&&Dmk>Nota)
		{
			System.out.println("\nThe Winner Of The Election Is Dmk.");
			System.out.println("The Number Of Votes Voted For This Party Is:" +Dmk);
		}
		else if(TorchLight>Admk&&TorchLight>Dmk&&TorchLight>Bjp&&TorchLight>Congress&&TorchLight>Nota)
		{
			System.out.println("\nThe Winner Of The Election Is Makkal Needhi Maiyam.");
			System.out.println("The Number Of Votes Voted For This Party Is:" +TorchLight);
		}
		else if(Bjp>Admk&&Bjp>Dmk&&Bjp>TorchLight&&Bjp>Congress&&Bjp>Nota)
		{
			System.out.println("\nThe Winner Of The Election Is Bjp.");
			System.out.println("The Number Of Votes Voted For This Party Is:" +Bjp);
		}
		else if(Congress>Admk&&Congress>Dmk&&Congress>TorchLight&&Congress>Bjp&&Congress>Nota)
		{
			System.out.println("\nThe Winner Of The Election Is Congress.");
			System.out.println("The Number Of Votes Voted For This Party Is:" +Congress);
		}
		else if(Nota>Admk&&Nota>Dmk&&Nota>TorchLight&&Nota>Bjp&&Nota>Congress)
		{
			System.out.println("\nThe Winner Of The Election Is Nota.");
			System.out.println("The Number Of Votes Voted For This Party Is:" +Nota);
		}
		if(Admk>0&&Dmk>0)
		{
			if(Admk==Dmk)
			{
				System.out.println("\nThe Election Has Been Draw Due To Equal Amount Of Vote.");
				System.out.println("The Parties Which Have The Same Number Of Votes Are Admk And Dmk.");
				System.out.println("The Vote Count Of The Both Parties Is:" +Dmk);
			}
		}
		if(Admk>0&&TorchLight>0)
		{
			if(Admk==TorchLight)
			{
				System.out.println("\nThe Election Has Been Draw Due To Equal Amount Of Vote.");
				System.out.println("The Parties Which Have The Same Number Of Votes Are Admk And Makkal Needhi Maiyam.");
				System.out.println("The Vote Count Of The Both Parties Is:" +TorchLight);
			}
		}
		if(Admk>0&&Bjp>0)
		{
			if(Admk==Bjp)
			{
				System.out.println("\nThe Election Has Been Draw Due To Equal Amount Of Vote.");
				System.out.println("The Parties Which Have The Same Number Of Votes Are Admk And Bjp.");
				System.out.println("The Vote Count Of The Both Parties Is:" +Bjp);
			}
		}
		if(Admk>0&&Congress>0)
		{
			if(Admk==Congress)
			{
				System.out.println("\nThe Election Has Been Draw Due To Equal Amount Of Vote.");
				System.out.println("The Parties Which Have The Same Number Of Votes Are Admk And Congress.");
				System.out.println("The Vote Count Of The Both Parties Is:" +Congress);
			}
		}
		if(Dmk>0&&TorchLight>0)
		{
			if(Dmk==TorchLight)
			{
				System.out.println("\nThe Election Has Been Draw Due To Equal Amount Of Vote.");
				System.out.println("The Parties Which Have The Same Number Of Votes Are Dmk And Makkal Needhi Maiyam.");
				System.out.println("The Vote Count Of The Both Parties Is:" +TorchLight);
			}
		}
		if(Dmk>0&&Bjp>0)
		{
			if(Dmk==Bjp)
			{
				System.out.println("\nThe Election Has Been Draw Due To Equal Amount Of Vote.");
				System.out.println("The Parties Which Have The Same Number Of Votes Are Dmk And Bjp.");
				System.out.println("The Vote Count Of The Both Parties Is:" +Bjp);
			}
		}
		if(Dmk>0&&Congress>0)
		{
			if(Dmk==Congress)
			{
				System.out.println("\nThe Election Has Been Draw Due To Equal Amount Of Vote.");
				System.out.println("The Parties Which Have The Same Number Of Votes Are Dmk And Congress.");
				System.out.println("The Vote Count Of The Both Parties Is:" +Congress);
			}
		}
		if(TorchLight>0&&Bjp>0)
		{
			if(TorchLight==Bjp)
			{
				System.out.println("\nThe Election Has Been Draw Due To Equal Amount Of Vote.");
				System.out.println("The Parties Which Have The Same Number Of Votes Are Makkal Needhi Maiyam And Bjp.");
				System.out.println("The Vote Count Of The Both Parties Is:" +Bjp);
			}
		}
		if(TorchLight>0&&Congress>0)
		{
			if(TorchLight==Congress)
			{
				System.out.println("\nThe Election Has Been Draw Due To Equal Amount Of Vote.");
				System.out.println("The Parties Which Have The Same Number Of Votes Are Makkal Needhi Maiyam And Congress.");
				System.out.println("The Vote Count Of The Both Parties Is:" +Congress);
			}
		}
		if(Bjp>0&&Congress>0)
		{
			if(Bjp==Congress)
			{
				System.out.println("\nThe Election Has Been Draw Due To Equal Amount Of Vote.");
				System.out.println("The Parties Which Have The Same Number Of Votes Are Bjp And Congress.");
				System.out.println("The Vote Count Of The Both Parties Is:" +Congress);
			}
		}
		if(Admk>0&&Nota>0)
		{
			if(Admk==Nota)
			{
				System.out.println("\nThe Winner Of The Election Is Admk.");
				System.out.println("The Number Of Votes Voted For This Party Is:" +Admk);
			}
		}
		if(Dmk>0&&Nota>0)
		{
			if(Dmk==Nota)
			{
				System.out.println("\nThe Winner Of The Election Is Dmk.");
				System.out.println("The Number Of Votes Voted For This Party Is:" +Dmk);
			}
		}
		if(TorchLight>0&&Nota>0)
		{
			if(TorchLight==Nota)
			{
				System.out.println("\nThe Winner Of The Election Is Makkal Needhi Maiyam.");
				System.out.println("The Number Of Votes Voted For This Party Is:" +TorchLight);
			}
		}
		if(Bjp>0&&Nota>0)
		{
			if(Bjp==Nota)
			{
				System.out.println("\nThe Winner Of The Election Is Bjp.");
				System.out.println("The Number Of Votes Voted For This Party Is:" +Bjp);
			}
		}
		if(Congress>0&&Nota>0)
		{
			if(Congress==Nota)
			{
				System.out.println("\nThe Winner Of The Election Is Congress.");
				System.out.println("The Number Of Votes Voted For This Party Is:" +Congress);
			}
		}
	}
}
class VotersIdNumberValidationException extends Exception
{
	VotersIdNumberValidationException(String e)
	{
		super(e);
	}
}