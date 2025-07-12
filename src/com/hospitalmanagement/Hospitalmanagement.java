package com.hospitalmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;


public class Hospitalmanagement {
	public static void main(String [] args) {
		
	Scanner sc = new Scanner(System.in);
	try {
		Connection db = Database.getConnection();
		System.out.println("Db Connected....");
		Patient patient = new Patient(sc);
		Doctors doctor = new Doctors();
		while(true)
		{
			System.out.println("Welcome to ABC Hospital Management");
			System.out.println(" 1,Add Patient \n 2,View Patient \n 3,ViewDoctors \n 4,Book Appointment \n 5,Exit");
			System.out.println("Enter your option : ");
			int choice = sc.nextInt();
			switch(choice) {
			case 1 : patient.addPatient();
			break;
			
			case 2 : patient.viewpatient();
			break;
			
			case 3 : doctor.viewDoctors();
			break;
			
			case 4 : bookAppointment(patient,doctor,sc);
			
			System.out.println();
			break;
			
			case 5: return;
			
			default : System.out.println("Invalid request ");
				
			}
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
	public static void bookAppointment(Patient patient,Doctors doctor,Scanner sc) {
		try {
			System.out.println("Enter the patient id: ");
			int p_id = sc.nextInt(); 
			
			System.out.println("Enter the patient Name: ");
			String p_name = sc.next();
			
			System.out.println("Enter the Doctor id: ");
			int d_id = sc.nextInt();
			
			System.out.println("Enter the Appointment Date(YYYY-MM-DD) : ");
			String a_date = sc.next();
			
			if(patient.getPatientid(p_id) && doctor.getDoctorid(d_id)) {
				if(checkDoctorAvailability(d_id,a_date)) {
					String appointmentQuery = "insert into appointments(patient_id,patient_name,doctor_id,appointment_date)values(?,?,?,?)";
					try {
						Connection db = Database.getConnection();
						PreparedStatement ps = db.prepareStatement(appointmentQuery);
						ps.setInt(1, p_id);
						ps.setString(2, p_name);
						ps.setInt(3, d_id);
						ps.setString(4, a_date);
						
						int raff=ps.executeUpdate();
						if(raff >0) {
							System.out.println("Appointment Booked");
						}
						else {
							System.out.println("Failed to book appointment");
						}
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			Connection db = Database.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static boolean checkDoctorAvailability(int d_id,String a_date) {
		String query = "select count(*) from appointments where doctor_id = ? and appointment_date = ?";
		try {
			Connection db = Database.getConnection();
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1,d_id);
			ps.setString(2, a_date);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int count = rs.getInt(1);
				if (count == 0) 
					return true;
				else 
					return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
}

	
}
