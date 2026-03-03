## Urban Waste Collection and Citizen Complaint Tracking System (Hibernate Version)

## Project Objective
   The objective of this project is to develop a robust Urban Waste Management System using Java, Hibernate (ORM), and MySQL/Oracle. This system transitions from manual JDBC to an automated ORM approach to manage:
      1. Citizen Management: Household details with automated ORM mapping.
      2. Waste Collection Logs: Tracking vehicles and schedules.
      3. Complaint Tracking: Raising and updating sanitation complaints.
      4. Automated DB Schema: Using Hibernate's hbm2ddl to manage tables automatically.

## How to Run in Eclipse IDE
Step 1: Create a Maven Project
Since we are using Hibernate, Maven is the best way to manage dependencies.
Go to: File → New → Maven Project.
Select Create a simple project 
Enter Group Id: in.kce, Artifact Id: HibernateApp.

Step 2: Update pom.xml
Add the following dependencies to your pom.xml:
1. hibernate-core (Version 6.x)
2. ojdbc8 (Oracle Driver)
3. hibernate-hikaricp (For connection pooling)

Step 3: Project Structure & Packages
Right-click on src/main/java and ensure these packages exist:

1. com.kce.bean (Entity Classes with @Entity annotations)
2. com.kce.dao (Hibernate Session logic)
3. com.kce.service (Business Logic)
4. com.kce.util (HibernateUtil for SessionFactory)
5. com.kce.app (Main class)

Step 4: Configuration 
Create hibernate.cfg2.xml inside src/main/resources:
Configure Dialect, URL, Username, and Password.
Set <property name="hibernate.hbm2ddl.auto">update</property> to auto-create tables.
Add <mapping class="..."/> for all Bean classes.

Step 5: Run the Application
Go to com.kce.app.WasteMain.
Right-click → Run As → Java Application.

## Key Technical Components
Entities -->	Citizen, WasteServiceRow (Annotated with JPA/Hibernate)
Utility -->	HibernateUtil (Manages SessionFactory)
DAO Layer -->	Uses session.persist(), session.get(), and HQL for CRUD operations.
Service Layer -->	Handles business logic and transaction management.

## Database Schema (Auto-managed)
Hibernate will automatically generate/update these tables based on Bean classes:
1. CITIZEN_TBL: Stores household data.
2. WASTE_SERVICE_TBL: Unified table for visits and complaints.

## OUTPUT:

<img width="788" height="534" alt="Screenshot 2026-02-19 220414" src="https://github.com/user-attachments/assets/aaf2a5e3-42aa-40f2-812b-f6392c8b5e47" />
<img width="913" height="582" alt="Screenshot 2026-02-19 220444" src="https://github.com/user-attachments/assets/93733608-fd5c-4739-a65f-88c8ea9d3b02" />

## STUDENT DETAILS:
NAME: PRIYADHARSHINI M
ROLL NO: 717823P141


