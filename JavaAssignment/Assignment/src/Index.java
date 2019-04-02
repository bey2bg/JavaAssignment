import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Index {
	public static ArrayList<WrapperStudent> wrapper=new ArrayList<WrapperStudent>();
	
	public static void mainscreen() {
		int choice = -1;		
		do {
			Scanner input = new Scanner(System.in);
			System.out.println("1- Create a new student");
			System.out.println("2- List all of students");
			System.out.println("3- Show student info only one student");
			System.out.println("4- List all of students info");
			System.out.println("5- Show average only one student");
			System.out.println("6- Show average all of students");
			System.out.println("7- Edit student");
			System.out.println("0- Exit");
			System.out.print("Choose option : ");
			choice = input.nextInt();
			switch(choice)
			{
			case 1:
				createStudent();
				break;
			case 2:
				listStudent();
				break;
			case 3: 
				showStudentInfoOneStudent();
				break;
			case 4:
				listStudentInfo();
				break;
			case 5:
				showAverageOneStudent();
				break;
			case 6:
				showAverageAllof();
				break;
			case 7:
				editStudentInfo();
				break;
			case 0:
				System.out.println("Program closed.");
				break;
			default:
				System.out.println("Wrong option !");
				break;						
			}
		}while(choice != 0);
		
	}
	
	public static void wrapStudent() {
		
		wrapper.clear();
        String line = null;

		
		FileReader fileReader;
		try {
			fileReader = new FileReader("Student.txt");

         
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
              
            	String[] l = line.split("-");
            	WrapperStudent ws=new WrapperStudent();
            	ws.ID=Integer.parseInt(l[0]);
            	ws.Name=l[1];
            	ws.Surname=l[2];
            	ws.Les1=Integer.parseInt(l[3]);
            	ws.Les2=Integer.parseInt(l[4]);
            	ws.Les3=Integer.parseInt(l[5]);
            	ws.Les4=Integer.parseInt(l[6]);
            	ws.Les5=Integer.parseInt(l[7]);
            	ws.avg=(ws.Les1+ws.Les2+ws.Les3+ws.Les4+ws.Les5)/5;
            	wrapper.add(ws);
            }   

           
            bufferedReader.close();   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
	}
	
	public static void createStudent()
	{
		Scanner input = new Scanner(System.in);
		Student s= new Student();
		
		System.out.print("Student ID : ");
		s.ID=input.nextInt();
		
		System.out.print("Student Name : ");
		s.Name=input.next();
		
		System.out.print("Student Surname : ");
		s.SurName=input.next();
		
		
		System.out.println("Added 5 lessons for this student; ");
				
		for(int i=0; i<5;i++)
		{
			Lesson l = new Lesson();
			
			System.out.print(i+1+"- Lesson Name : ");
			l.setName(input.next());
			
			System.out.print(i+1+"- Lesson Grade : ");
			l.setGrade(input.nextInt());
			
			s.les.add(l);
		}
		
		 FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("Student.txt",true);
			 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			 
			 String grade="";
			 for(int i=0;i<5;i++)
			 {
				grade+="-"+s.les.get(i).getGrade();
			 }
			 
			 bufferedWriter.write(s.ID+"-"+s.Name+"-"+s.SurName+grade);
			 bufferedWriter.newLine();
			 bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		wrapStudent();
		
	}

	public static void listStudent()
	{
		for(int i=0;i<wrapper.size();i++)
		{
			System.out.println("ID:"+wrapper.get(i).ID+" Name:"+wrapper.get(i).Name+" Surname:"+wrapper.get(i).Surname+" Lesson1:"
							+wrapper.get(i).Les1+" Lesson2:"+wrapper.get(i).Les2+" Lesson3:"+wrapper.get(i).Les3+" Lesson4:"+wrapper.get(i).Les4+" Lesson5:"+wrapper.get(i).Les5
							+" Avg:"+wrapper.get(i).avg);
			
		}
	}
	
	public static void listStudentInfo() {
		for(int i=0;i<wrapper.size();i++)
		{
			System.out.println("ID:"+wrapper.get(i).ID+" Name:"+wrapper.get(i).Name+" Surname:"+wrapper.get(i).Surname);			
		}
	}

	public static void showAverageAllof() {
		int avg=0;
		for(int i=0;i<wrapper.size();i++)
		{
			avg+=wrapper.get(i).avg;			
		}
		avg=avg/wrapper.size();
		System.out.println(wrapper.size()+". students average = "+avg);
	}
	
	public static void showAverageOneStudent() {
		boolean check=false;
		int id=-1;
		Scanner input = new Scanner(System.in);
		System.out.print("Enter student id : ");
		id=input.nextInt();
		
		for(int i=0;i<wrapper.size();i++) {
			if(wrapper.get(i).ID==id)
			{
				System.out.println(wrapper.get(i).ID+" "+wrapper.get(i).Name+" "+wrapper.get(i).Surname+" AVG: "+wrapper.get(i).avg);
				check=true;
				break;
			}
		}
		
		if(!check)
		{
			System.out.println("There is not student that ID.");
		}
		
	}
	
	public static void showStudentInfoOneStudent() {
		boolean check=false;
		int id=-1;
		Scanner input = new Scanner(System.in);
		System.out.print("Enter student id : ");
		id=input.nextInt();
		
		for(int i=0;i<wrapper.size();i++) {
			if(wrapper.get(i).ID==id)
			{
				System.out.println(wrapper.get(i).ID+" "+wrapper.get(i).Name+" "+wrapper.get(i).Surname);
				check=true;
				break;
			}
		}
		
		if(!check)
		{
			System.out.println("There is not student that ID.");
		}
		
	}
	
	public static void editStudentInfo() {
		boolean check=false;
		int id=-1, location=-1; 
		Scanner input = new Scanner(System.in);
		System.out.print("Enter student id : ");
		id=input.nextInt();
		
		for(int i=0;i<wrapper.size();i++) {
			if(wrapper.get(i).ID==id)
			{
				location=i;
				check=true;
				break;
			}
		}		
		if(!check)
		{
			System.out.println("There is not student that ID.");
		}
		else
		{
			boolean q=true;
			do{
				int chose=-1;
				System.out.println("1- Edit ID");
				System.out.println("2- Edit Name");
				System.out.println("3- Edit Surname");
				System.out.println("4- Edit Lesson 1 grade");
				System.out.println("5- Edit Lesson 2 grade");
				System.out.println("6- Edit Lesson 3 grade");
				System.out.println("7- Edit Lesson 4 grade");
				System.out.println("8- Edit Lesson 5 grade");
				System.out.println("9- Delete student");
				System.out.println("0- Back to menu");
				System.out.print("Choose option : ");
				chose=input.nextInt();
				
				switch(chose)
				{
				case 1:
					System.out.print("Enter new ID : ");
					wrapper.get(location).ID=input.nextInt();
					reWriteTXT();
					wrapStudent();
					break;
				case 2:
					System.out.print("Enter new name : ");
					wrapper.get(location).Name=input.next();
					reWriteTXT();
					wrapStudent();
					break;
				case 3:
					System.out.print("Enter new surname : ");
					wrapper.get(location).Surname=input.next();
					reWriteTXT();
					wrapStudent();
					break;
				case 4:
					System.out.print("Enter new Lesson 1 grade : ");
					wrapper.get(location).Les1=input.nextInt();
					reWriteTXT();
					wrapStudent();
					break;
				case 5:
					System.out.print("Enter new Lesson 2 grade : ");
					wrapper.get(location).Les2=input.nextInt();
					reWriteTXT();
					wrapStudent();
					break;
				case 6:
					System.out.print("Enter new Lesson 3 grade : ");
					wrapper.get(location).Les3=input.nextInt();
					reWriteTXT();
					wrapStudent();
					break;
				case 7:
					System.out.print("Enter new Lesson 4 grade : ");
					wrapper.get(location).Les4=input.nextInt();
					reWriteTXT();
					wrapStudent();
					break;
				case 8:
					System.out.print("Enter new Lesson 5 grade : ");
					wrapper.get(location).Les5=input.nextInt();
					reWriteTXT();
					wrapStudent();
					break;
				case 9:
					System.out.print("Do you want to delete this student [y/n] : ");
					if(input.next().equals("y") || input.next().equals("Y"))
					{
						wrapper.remove(location);
						reWriteTXT();
						wrapStudent();
						q=false;
					}
					break;
				case 0:
					q=false;
					break;
				default:
					System.out.println("Wrong option !");
					break;	
				}
				
			}while(q);
		}
		
	}

	public static void reWriteTXT(){
		 FileWriter fileWriter;
			try {
				fileWriter = new FileWriter("Student.txt",false);
				 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				 
				 for(int i=0;i<wrapper.size();i++)
				 {
					 bufferedWriter.write(wrapper.get(i).ID+"-"+wrapper.get(i).Name+"-"+wrapper.get(i).Surname+"-"+
							 			  wrapper.get(i).Les1+"-"+wrapper.get(i).Les2+"-"+wrapper.get(i).Les3+"-"+
							 			 wrapper.get(i).Les4+"-"+wrapper.get(i).Les5);
					 bufferedWriter.newLine();
				 }				 
				 
				 bufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) {
	 
		wrapStudent();
		mainscreen();
	
	}	
	

}
