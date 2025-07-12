package com.hospitalmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;





public class Patient {
//	private Connection db;
	private Scanner scn;
	
	public Patient (Scanner scn) throws SQLException
	{
		Connection db = Database.getConnection();
		
		this.scn = scn;
		
	}
	public void addPatient() {
		System.out.println("Enter the Patient name : ");
		String Name = scn.next();
		System.out.println("Enter the Patient Age : ");
		int Age = scn.nextInt();
		System.out.println("Enter the Patient Gender : ");
		String Gender = scn.next();
		
		try {
			String query ="insert into patients (name,age,gender) values (?,?,?)";
			Connection db = Database.getConnection();
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, Name);
			ps.setInt(2, Age);
			ps.setString(3, Gender);
			int affectedrows = ps.executeUpdate();
			if (affectedrows > 0) {
				System.out.println("Patient Added");
			}else {
				System.out.println("Failed");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		
		
	}
	public void viewpatient() {
		String query ="Select * from patients";
		try {
			Connection db = Database.getConnection();
			PreparedStatement ps = db.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			System.out.println("Patients");
			System.out.println("------------------------------------");
			System.out.println("| patient id | Name  | Age | Gender |");
			while(rs.next())
			{
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age=rs.getInt("age");
				String gender = rs.getString("gender");
				System.out.printf("|%-12s|%-10s|%-4s|%-10s|\n",id,name,age,gender);//format method used to print 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public boolean getPatientid(int id) {
		
		String query = "select * from patients where id = ?";
		try {
			Connection db = Database.getConnection();
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				return true;
			else
				return false; 
				
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
		
	}
}
