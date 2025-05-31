
Employee-Management-System

An intuitive web application designed to efficiently manage employee data within an organization. This system provides a robust backend API for data operations and a dynamic, responsive frontend for user interaction.

🚀 Features
Employee Management: Add, view, update, and delete employee records.
Search & Filter: Easily find employees based on various criteria (e.g., name, department).
RESTful API: A well-documented API for seamless interaction with the frontend and potential third-party integrations.
Responsive UI: User-friendly interface that adapts to various screen sizes.
💻 Technologies Used
Backend
Spring Boot: Framework for building robust, standalone, production-grade Spring applications.
Java: The core programming language.
Spring Data JPA: Simplifies data access and persistence with Hibernate.
Hibernate: ORM (Object-Relational Mapping) framework.
Maven: Dependency management and build automation tool.
MySQL Connector/J: JDBC driver for MySQL database connectivity.
Frontend
React: A JavaScript library for building user interfaces.
JavaScript (ES6+): The programming language for frontend logic.
npm / Yarn: Package manager for JavaScript.
Database
MySQL: A widely used open-source relational database management system.
Deployment
AWS Elastic Beanstalk: Platform as a service (PaaS) for deploying and scaling web applications and services.
AWS RDS (Relational Database Service): Managed database service for MySQL.
📋 Prerequisites
Before you begin, ensure you have the following installed on your local machine:

Java Development Kit (JDK) 17 or higher:
Download JDK
Maven 3.6.0 or higher:
Download Maven
Node.js 18.x or higher & npm (comes with Node.js) or Yarn:
Download Node.js
MySQL Server (for local development):
[suspicious link removed]
Git:
Download Git
An IDE (e.g., IntelliJ IDEA, VS Code): Highly recommended for development.
🚀 Getting Started (Local Development)
Follow these steps to set up and run the project on your local machine.

1. Clone the Repository
Bash

git clone <repository_url>
cd Employee-Management-System
2. Backend Setup (Spring Boot)
Navigate to the backend directory:
Bash

cd backend
Configure Database Connection:
Open the src/main/resources/application.properties (or application.yml) file.
Update the database connection details to point to your local MySQL instance. Replace placeholders with your actual credentials:
Properties

# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/assignmentdb?useSSL=true&requireSSL=true
spring.datasource.username=<your_local_mysql_username>
spring.datasource.password=<your_local_mysql_password>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update # or create, none depending on your preference
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
Important: Ensure a database named assignmentdb (or whatever you configure) exists on your local MySQL server. You might need to create it manually using a MySQL client:
SQL

CREATE DATABASE assignmentdb;
Build and Run the Backend:
Bash

mvn clean install
mvn spring-boot:run
The backend application will start and typically run on http://localhost:8080.
3. Frontend Setup (React)
Navigate to the frontend directory:
Bash

cd ../frontend
Install Dependencies:
Bash

npm install  # or yarn install
3.  Start the Frontend Application:
bash npm start # or yarn start
The React application will start and typically open in your browser at http://localhost:3000. It will proxy API requests to the backend at http://localhost:8080.

☁️ Deployment on AWS
This project is designed for deployment on AWS using Elastic Beanstalk for the Spring Boot backend and RDS for the MySQL database.

1. AWS RDS (MySQL) Setup
Create a MySQL RDS Instance:
In the AWS Management Console, navigate to RDS.
Click "Create database" and choose MySQL.
Configure it as a "Free tier" or suitable instance type for your needs.
Set the DB instance identifier (e.g., assignmentdb-instance).
Set a Master username and Master password.
Under Connectivity, choose the VPC where your Elastic Beanstalk environment will run.
Create a new VPC security group (or select an existing one) for your RDS instance. Let's call this rds-db-sg.
Crucially, ensure the MySQL database named assignmentdb exists within this RDS instance. You might need to connect to it using a MySQL client (like MySQL Workbench) and run CREATE DATABASE assignmentdb; after the instance is available. Pay attention to the exact casing (assignmentdb vs AssignmentDb vs ASSIGNMENTDB) and use that exact casing in your application's SPRING_DATASOURCE_URL.
2. AWS Elastic Beanstalk Setup
Create a New Elastic Beanstalk Environment:

In the AWS Management Console, navigate to Elastic Beanstalk.
Click "Create a new environment" and choose "Web server environment".
For Platform, select Java and the latest suitable Java version (e.g., Corretto 17).
Upload your Spring Boot .jar file (generated from mvn package in the backend directory).
Configure your environment name (e.g., emp-management-system-env).
Configure Environment Variables (Software Settings):

After creating the environment, go to its Configuration section.
Under the "Software" (or "Software settings") category, click "Edit".
Add the following environment properties:
SPRING_DATASOURCE_URL: jdbc:mysql://<your_rds_endpoint>:3306/assignmentdb?useSSL=true&requireSSL=true
Replace <your_rds_endpoint> with the Endpoint found in your RDS database details.
Ensure assignmentdb here exactly matches the casing of your database name on RDS.
SPRING_DATASOURCE_USERNAME: <your_rds_master_username>
SPRING_DATASOURCE_PASSWORD: <your_rds_master_password>
SERVER_PORT: 5000 (Elastic Beanstalk's default proxy often expects applications on this port).
SPRING_JPA_HIBERNATE_DDL_AUTO: update (or none for production).
Save these changes.
Configure Security Group for Database Connectivity (MOST CRITICAL STEP!):

Find your Elastic Beanstalk EC2 Instance's Security Group:
Go to the EC2 Dashboard.
Click on "Instances".
Find your running EC2 instance associated with your Elastic Beanstalk environment (its name usually starts with your EB environment name, e.g., emp-management-system-env-xxxx).
Select the instance, then click the "Security" tab in the details pane.
Note the ID of the security group that starts with awseb- (e.g., sg-0deaa320bc27f62df). This is your EB Instance Security Group ID.
Edit Inbound Rules of your RDS Security Group:
Go to EC2 Dashboard > Security Groups.
Find and select your RDS database's security group (rds-db-sg or sg-0dfbf870912ed91ef if you used default).
Click "Actions" > "Edit inbound rules".
Add a new rule:
Type: MySQL/Aurora (or Custom TCP with Port range: 3306).
Source: Select Custom and paste the EB Instance Security Group ID you copied (e.g., sg-0deaa320bc27f62df).
Click "Save rules".
Deploy Frontend (Optional - if served separately):

For the React frontend, you can build it (npm run build or yarn build) and deploy the static build folder to an AWS S3 bucket configured for static website hosting, fronted by AWS CloudFront for better performance.
🚀 Usage
Once deployed, access your Employee Management System via your Elastic Beanstalk environment's URL (e.g., http://emp-management-system-env.eba-qysthikm.us-east-1.elasticbeanstalk.com/).

🤝 Contributing
Feel free to fork the repository, create a new branch, and submit pull requests.

📄 License
This project is licensed under the MIT License - see the LICENSE file for details.
