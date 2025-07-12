package com.hospitalmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctors {

	public Doctors() {
		try {
			Connection db = Database.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void viewDoctors() {
		
		String query = "Select * from doctors";
		
		try {
			Connection db = Database.getConnection();
			PreparedStatement ps = db.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			System.out.println("Doctor");
			System.out.println("_____***_____");
			System.out.println("|doctor id |Name  |dept");
			while(rs.next()) {
				int id =rs.getInt("id");
				String name = rs.getString("name");
				String dept = rs.getString("dept");
				System.out.printf("| %-10s | %-7s | %-14s|\n",id,name,dept);
			}
		}catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	public boolean getDoctorid(int id) {
		String query = "select * from doctors where id=?";
		try {
			Connection db = Database.getConnection();
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				return true;
			else
				return false;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
