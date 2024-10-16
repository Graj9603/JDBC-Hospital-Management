import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalApp {
	public static void main(String[] args) {
		while(true) {
			System.out.println("Choose the Options");
			System.out.println("1 Register");
			System.out.println("2 Update Disease By Id");
			System.out.println("3 Update Age By Pho no");
			System.out.println("4 View All");
			System.out.println("5 View By Phono");
			System.out.println("6 Search By Disease");
			System.out.println("7 search By Name");
			System.out.println("8 Delete By Name And Phono");
			System.out.println("9 Exit");
			int choise=sc.nextInt();
			switch(choise) {
			case 1:
				register();
				break;
			case 2:
				UpdateDiseaseById();
				break;
			case 3:
				UpdateAgeByPhno();
				break;
			case 4:
				ViewAllDetails();
				break;
			case 5:
				ViewDetailsByPhno();
				break;
			case 6:
				SearchByDisease();
				break;
			case 7:
				SearchByName();
				break;
			case 8:
				DeleteByPhnoName();
				break;
			case 9:
				System.out.println("Thank you");
				System.exit(0);
			default:
				System.out.println("Invalid Option");
			}
		}
	}
	static Scanner sc =new Scanner(System.in);
	static void register() {
		System.out.println("Enter Patient Id");
		int id=sc.nextInt();
		System.out.println("Enter Patient name");
		String name=sc.next();
		System.out.println("Enter Patient Age");
		int age=sc.nextInt();
		System.out.println("Enter Patient Phone Number");
		long phno=sc.nextLong();
		System.out.println("Enter Patient Disease");
		String disease=sc.next();
		System.out.println("Enter Patient Admited Date");
		String adimitedDate=sc.next();
		Connection con=null;
		PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","Graj@123");
			ps=con.prepareStatement("insert into patient values(?,?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setInt(3, age);
			ps.setLong(4, phno);
			ps.setString(5, disease);
			ps.setDate(6, Date.valueOf(adimitedDate));
			int row=ps.executeUpdate();
			System.out.println(row+" :Row Affected...");
			System.out.println("--------------------");
		
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	static void UpdateDiseaseById() {
		System.out.println("Enter Patient Id");
		int id=sc.nextInt();
		System.out.println("Enter Patient Disease");
		String disease=sc.next();
		Connection con=null;
		PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","Graj@123");
			ps=con.prepareStatement("update patient set disease=? where id=?");
			ps.setString(1, disease);
			ps.setInt(2, id);
			int row=ps.executeUpdate();
			System.out.println(row+" :Row Affected...");
			System.out.println("--------------------");
		
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	static void UpdateAgeByPhno() {
		System.out.println("Enter Patient Age");
		int age=sc.nextInt();
		System.out.println("Enter Patient Phone number");
		long phno=sc.nextLong();
		Connection con=null;
		PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","Graj@123");
			ps=con.prepareStatement("update patient set age=? where pnono=?");
			ps.setInt(1, age);
			ps.setLong(2, phno);
			int row=ps.executeUpdate();
			System.out.println(row+" :Row Affected...");
			System.out.println("--------------------");
		
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	static void ViewAllDetails() {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","Graj@123");
			ps=con.prepareStatement("select * from patient");
			ResultSet res=ps.executeQuery();
			while(res.next()) {
				System.out.print(res.getInt(1)+"\t");
				System.out.print(res.getString(2)+"\t");
				System.out.print(res.getInt(3)+"\t");
				System.out.print(res.getLong(4)+"\t");
				System.out.print(res.getString(5)+"\t");
				System.out.println(res.getDate(6));
			}
			System.out.println("--------------------");
		
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	static void ViewDetailsByPhno() {
		System.out.println("Enter Patient Phone number");
		long phno=sc.nextLong();
		Connection con=null;
		PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","Graj@123");
			ps=con.prepareStatement("select * from patient where pnono=?");
			ps.setLong(1, phno);
			ResultSet res=ps.executeQuery();
			while(res.next()) {
				System.out.print(res.getInt(1)+"\t");
				System.out.print(res.getString(2)+"\t");
				System.out.print(res.getInt(3)+"\t");
				System.out.print(res.getLong(4)+"\t");
				System.out.print(res.getString(5)+"\t");
				System.out.println(res.getDate(6));
			}
			System.out.println("--------------------");
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	static void SearchByDisease() {
		System.out.println("Enter Patient Disease");
		String disease=sc.next();
		Connection con=null;
		PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","Graj@123");
			ps=con.prepareStatement("select * from patient where disease=?");
			ps.setString(1, disease);
			ResultSet res=ps.executeQuery();
			while(res.next()) {
				System.out.print(res.getInt(1)+"\t");
				System.out.print(res.getString(2)+"\t");
				System.out.print(res.getInt(3)+"\t");
				System.out.print(res.getLong(4)+"\t");
				System.out.print(res.getString(5)+"\t");
				System.out.println(res.getDate(6));
			}
			System.out.println("--------------------");
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	static void SearchByName() {
		System.out.println("Enter Patient Name");
		String name=sc.next();
		Connection con=null;
		PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","Graj@123");
			ps=con.prepareStatement("select * from patient where name=?");
			ps.setString(1, name);
			ResultSet res=ps.executeQuery();
			while(res.next()) {
				System.out.print(res.getInt(1)+"\t");
				System.out.print(res.getString(2)+"\t");
				System.out.print(res.getInt(3)+"\t");
				System.out.print(res.getLong(4)+"\t");
				System.out.print(res.getString(5)+"\t");
				System.out.println(res.getDate(6));
			}
			System.out.println("--------------------");
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	static void DeleteByPhnoName() {
		System.out.println("Enter Patient phno");
		long phno=sc.nextLong();
		System.out.println("Enter Patient name");
		String name=sc.next();
		Connection con=null;
		PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","Graj@123");
			ps=con.prepareStatement("delete from patient where pnono=? and name=?");
			ps.setLong(1, phno);
			ps.setString(2, name);
			int row=ps.executeUpdate();
			System.out.println("--------------------");
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
