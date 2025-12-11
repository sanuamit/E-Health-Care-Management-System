# 🏥 E-Health Care Management System (Java – CLI)

A simple **Command-Line Interface (CLI)** based hospital management system built using **Core Java**, **Object-Oriented Programming (OOP)** concepts like **Classes, Inheritance, Abstraction**, and organized under a clean folder structure.

This project runs directly in **VS Code PowerShell** using the Java compiler (`javac`) and Java runtime (`java`).  
Perfect for **resume projects**, **Java beginners**, and **academic assignments**.

---

## 🚀 Features

### 👨‍⚕️ Manage Doctors
- Add new doctors  
- View all doctors  

### 🧑‍💼 Manage Patients
- Add patients  
- View all patients  

### 📅 Appointments
- Book an appointment  
- View all booked appointments  

### 💾 Data Storage
- Uses in-memory lists  
- 100% Java, no external database  

---

## 🛠️ Tech Stack

- **Java (Core Java)**
- **OOP (Inheritance, Abstraction, Classes)**
- **VS Code**
- **PowerShell Terminal**
- **JDK 17+ recommended**

---

## 📂 Folder Structure

```
EHealthProject/
│
├── src/
│   ├── Main.java
│   ├── entities/
│   │   ├── Person.java
│   │   ├── Doctor.java
│   │   ├── Patient.java
│   ├── services/
│   │   ├── DoctorService.java
│   │   ├── PatientService.java
│   │   ├── AppointmentService.java
│
├── out/     (Auto-generated after compile)
│
├── commands.txt
└── README.md
```

---

## 🧑‍💻 Setup & Run (VS Code + PowerShell)

### ✔ Step 1 — Install JDK  
Install **JDK 17 or higher**. Verify installation:

```powershell
java -version
javac -version
```

---

### ✔ Step 2 — Open Folder in VS Code  
Go to:

```
File → Open Folder → EHealthProject
```

---

### ✔ Step 3 — Create the required folders

```powershell
mkdir src
mkdir src\entities
mkdir src\services
mkdir out
```

---

### ✔ Step 4 — Add all Java files into `src/`  
(Copy all `.java` files from the project)

---

### ✔ Step 5 — Compile the Project

```powershell
javac -d out src\Main.java src\entities\*.java src\services\*.java
```

---

### ✔ Step 6 — Run the Application

```powershell
java -cp out Main
```

---

## 📸 Sample Output

```
Welcome to E-Health Care Management System

Main Menu:
1. Manage Patients
2. Manage Doctors
3. Book Appointment
4. View Appointments
5. Exit
Choose:
```

---

## 📜 Commands Reference (commands.txt)

A separate file named **commands.txt** is included:

```
mkdir src
mkdir src\entities
mkdir src\services
mkdir out

javac -d out src\Main.java src\entities\*.java src\services\*.java
java -cp out Main
```

---

## 📘 Concepts Used

### ✔ Abstraction  
`Person` is an abstract parent class.

### ✔ Inheritance  
`Doctor` and `Patient` inherit from `Person`.

### ✔ Encapsulation  
Used with private fields + getters/setters.

### ✔ Polymorphism  
Dynamic behavior in service methods.

### ✔ Modular Architecture  
Services keep business logic separate from Main.

---

## 📄 Future Enhancements

- File-based storage (JSON / CSV)
- Admin login module  
- Prescription management  
- Billing module  

---

## 👨‍💻 Author

**Amit Kumar Pati**  
E-Health Care CLI System (Java)  
📧 Email: sanuamitpati@gmail.com  
🌐 Portfolio: sanuamit.github.io/PERSONAL-PROTFOLIO/  
💼 GitHub: https://github.com/sanuamit  

---

### ⭐ If you use this project, don’t forget to give it a **Star** on GitHub!

