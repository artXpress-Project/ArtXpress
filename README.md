# artXpress

artXpress is an online platform for art enthusiasts to explore and purchase artworks from various artists.

## Features

- Browse artworks by category, artist, or style.
- View detailed information about each artwork, including artist information and pricing.
- Add artworks to a shopping cart and proceed to checkout.
- Manage user accounts, including registration, login, and profile management.
- Admin panel for managing artworks, artists, and user orders.

## Technologies Used

- **Backend**: Spring Framework
- **Frontend**: TypeScript
- **Database**: [MySql]
- **Deployment**: [-]

## Getting Started

### Prerequisites

- [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Node.js](https://nodejs.org/)
- [npm](https://www.npmjs.com/) (typically installed with Node.js)
- [MySql]

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/artXpress.git
   ```

2. Backend Setup:
   - Navigate to the `backend` directory:
     ```bash
     cd backend
     ```
   Sure, here are some instructions for setting up the Spring backend for artXpress:
      
      I. **Clone the Repository**:
       - If you haven't already cloned the artXpress repository, you can do so by running:
         ```bash
         git clone https://github.com/yourusername/artXpress.git
         ```
      
      II. **Navigate to the Backend Directory**:
       - Once cloned, navigate to the backend directory:
         ```bash
         cd artXpress/backend
         ```
      
      III. **Set up the Database**:
       - Configure your database settings in the `application.properties` file located in the `src/main/resources` directory.
       - Ensure that you have the necessary database software installed and running.
       - Create the required tables and schema according to the application's database design.
      
      IV. **Build the Project**:
       - Use Maven to build the Spring Boot application:
         ```bash
         mvn clean install
         ```
      
      V. **Run the Application**:
       - After building the project, you can run the Spring Boot application using Maven:
         ```bash
         mvn spring-boot:run
         ```
       - Alternatively, you can run the JAR file generated in the `target` directory:
         ```bash
         java -jar target/artXpress-backend.jar
         ```
      
      VI. **Verify Backend Setup**:
       - Once the application is running, you can verify that the backend is set up correctly by accessing the defined endpoints through a tool like Postman or by navigating to `http://localhost:8080` in your web browser (if there's a landing page or API documentation available).
      
      VII. **Additional Configuration**:
       - Depending on the specific requirements of your application, you may need to configure additional settings such as security, logging, or external services integration.
      
      ### Note:
      Make sure to replace `yourusername` in the clone command with your actual GitHub username. Additionally, adjust the database settings and configurations according to your specific database setup and requirements.

3. Frontend Setup:
   - Navigate to the `frontend` directory:
     ```bash
     cd frontend
     ```
   - Install dependencies:
     ```bash
     npm install
     ```

4. Database Setup:
       Here are instructions for setting up the MySQL database for artXpress:

      
      I. **Install MySQL Server**:
       - If MySQL Server is not already installed on your system, download and install it from the official MySQL website: [MySQL Downloads](https://dev.mysql.com/downloads/)
      II. **Start MySQL Server**:
       - Start the MySQL server on your local machine. The method to start the server may vary depending on your operating system.
      III. **Create a Database**:
       - Once the MySQL server is running, you can create a new database for artXpress. You can do this via the MySQL command-line interface (CLI) or a graphical user interface (GUI) tool like MySQL Workbench.
       - Example command in the MySQL CLI:
         ```sql
         CREATE DATABASE artxpress_db;
         ```
      IV. **Create Database User**:
       - Create a new user and grant necessary privileges to the newly created database. Replace `username` and `password` with your desired values.
       - Example command in the MySQL CLI:
         ```sql
         CREATE USER 'username'@'localhost' IDENTIFIED BY 'password';
         GRANT ALL PRIVILEGES ON artxpress_db.* TO 'username'@'localhost';
         FLUSH PRIVILEGES;
         ```
      V. **Database Configuration**:
       - Open the `application.properties` file located in the `src/main/resources` directory of the backend project.
       - Update the database connection settings to match the database name, username, and password you just created:
         ```properties
         spring.datasource.url=jdbc:mysql://localhost:3306/artxpress_db
         spring.datasource.username=username
         spring.datasource.password=password
         spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
         ```
      VI. **Run the Spring Boot Application**:
       - Build and run the Spring Boot application as described in the previous instructions.
      
      VII. **Verify Database Setup**:
       - After starting the Spring Boot application, verify that it successfully connects to the MySQL database by checking the console logs for any connection errors.
       - You can also verify the database setup by checking if the necessary tables are created in the `artxpress_db` database.
      
      ### Note:
      Make sure to replace `username` and `password` in the commands and configurations with your actual MySQL username and password. Additionally, adjust the database name (`artxpress_db`) according to your preference, if needed.

### Usage

1. Start the backend server:

   ```bash
   # Navigate to the backend directory
   cd backend
   
   # Run the Spring application
   [Command to start the Spring application]
   ```

2. Start the frontend development server:

   ```bash
   # Navigate to the frontend directory
   cd frontend
   
   # Run the frontend application
   npm start
   ```

3. Open your web browser and visit `http://localhost:3000` to access the artXpress web application.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.

## License



## Acknowledgements
