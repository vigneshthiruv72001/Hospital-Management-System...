# 🏥 Hospital Management System

A **Java-based console application** that helps manage hospital records, including patients, doctors, and appointments. It uses **JDBC** for database operations and **MySQL** as the backend database.

---

## 🔧 Technologies Used

- Java (Core)
- JDBC (Java Database Connectivity)
- MySQL
- Eclipse IDE

---

## 📁 Features

- ➕ Add new patients with age and gender
- 📄 View all patients
- 🔍 Verify patient ID for booking
- 👨‍⚕️ View all doctors and their departments
- 📅 Book appointments (optional extension)
- 🔒 Uses a singleton DB connection for performance

---

## 🗃️ Database Schema

Make sure you create the following tables in your MySQL database:

### 📌 `patients` Table
### 🗃️ Database Schema (MySQL)

sql
USE hospital;

CREATE TABLE patients (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  age INT NOT NULL,
  gender VARCHAR(100) NOT NULL
);

CREATE TABLE doctors (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  dept VARCHAR(50) NOT NULL
);

CREATE TABLE appointments (
  id INT AUTO_INCREMENT PRIMARY KEY,
  patient_id INT NOT NULL,
  doctor_id INT NOT NULL,
  appointment_date DATE NOT NULL,
  FOREIGN KEY (patient_id) REFERENCES patients(id),
  FOREIGN KEY (doctor_id) REFERENCES doctors(id)
);

-- Optional: For quick lookup
ALTER TABLE appointments ADD COLUMN patient_name VARCHAR(100);
Hospital-Management-System/
├── com.hospitalmanagement/
│   ├── Hospitalmanagement.java     # Main menu and control
│   ├── Patient.java                # Patient operations (add, view, check ID)
│   ├── Doctors.java                # Doctor view & ID check
│   ├── Database.java               # Singleton DB connection class


