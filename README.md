# What it is:
A login Graphical User Interface using Java as the frontend and a locally run MySQL database server as the backend. 

![An example image of Standard User Options](https://github.com/s412924/ReadMe/blob/ae85feea212635d3b3c7feaf0694d12cb46b8914/StandardUserExample.png)

The GUI includes various options for standard users, such as the ability to change your username, change your password, personalized budget for each account, a song player (though the songs have to be on the local computer) an investment calculator, a code cracker and a number game.

![An example image of Admin Options](https://github.com/s412924/ReadMe/blob/74c02b539fb310c283024b9543bfbe6dc525f880/AdminExample.png)

For admin users, you have all the same abilities as standard users, but you also have the ability to create new users, update user's group, update account status, delete users, display all users, display the log, and display admin messages.

For SuperAdmin users (the equivalent of Super User or sudo in Linux), you have all the same abilities as an admin account, but you also have the ability to change the password complexibility requirements (just the length for right now), change the expiration time for the passwords (the amount of days you can go before you are required to change your password), and restart the database server, though that is still in it's alpha stage, and hasn't been perfected yet.

![An example image of SuperAdmin Options](https://github.com/s412924/ReadMe/blob/1cb265f017b4121478041f89f341041a51d9ba90/SuperAdminExample.png)

# How To Use:
When you first get on, you have the option to choose whether you've logged in before. If you choose no, you will be given the default username and password. If it is your first time setting up the GUI, then the Default user will automatically be created. Once you login, you have to set up a new user. Input your Name, Username, Password, Expiration time, and group type. Again, if this is your first time setting up the GUI, then you will be given the option to create a new SuperAdmin. **WARNING! ONCE YOU SET A NEW SUPERADMIN YOU CANNOT CREATE ANOTHER ONE!**
After that, it's a simple matter of logging out and then logging back in useing your new credentials.
You will have to setup a new MySQL Server in order to use the backend. Instructions to do that are below




# How to setup MySQL server
- [ ] https://dev.mysql.com/downloads/mysql/ Download and setup the MySQL server service.
- [ ] Start the MySQL service. If you just setup the MySQL server service, then it should have already started, if not, you may have to go in and start it manually. Click the Windows Key then type in services. Go to the M's and find the MySQL. Right click on it and select Start.
- [ ] Edit the password.txt file to contain the MySQL Server password that you set up for the root user.
- [ ] Using the command MySQL 8.4 command line interface, login to the sql server. Then create the userDatabase and budget database. The commands are as follows:
  1. **create database userDatabase;**
  2. **create database budget;**
- make sure you include the semicolon. Hit Ctrl + enter to execute each command.
- [ ] Now you need to setup the tables within each database.
- [ ] Type the command **use userDatabse;**
- There are four tables within the userDatabase. The commands to create them are as follows
  - 1. **CREATE TABLE userdatabase.users (
    id INT(11),
    name VARCHAR(50) CHARACTER SET utf8mb4,
    username VARCHAR(50) CHARACTER SET utf8mb4,
    password VARCHAR(125) CHARACTER SET utf8mb4,
    `group` VARCHAR(255) CHARACTER SET utf8mb4,
    status ENUM('Enabled', 'Disabled') CHARACTER SET utf8mb4,
    last_login DATE,
    salt VARCHAR(32) CHARACTER SET utf8mb4,
    expiration_time DATE
);**
  - 2. **CREATE TABLE userdatabase.bannedUsers (
    idBannedUsers INT,
    username VARCHAR(255),
    name VARCHAR(255)
);**
  - 3. **CREATE TABLE userdatabase.passwordexpiration (
    user VARCHAR(255),
    lastChangedDate DATE
);**
  - 4. **CREATE TABLE userdatabase.passwordhistory (
    user VARCHAR(255),
    password VARCHAR(255)
);**

- [ ] Type the command **use budget;**
- There are two tables you need for the budget database. The commands to create them are as follows:
  - 1. **CREATE TABLE budget.master_budget (
    category VARCHAR(255),
    MaxAllowedMoney DECIMAL(10, 2),
    user VARCHAR(255)
);**
  - 2. **CREATE TABLE budget.master_subcategories (
    Category_name VARCHAR(255) CHARACTER SET utf8mb4,
    Subcategory_name VARCHAR(255) CHARACTER SET utf8mb4,
    AmountSpent DECIMAL(12, 2),
    user VARCHAR(255) CHARACTER SET utf8mb4
);**
- [ ] Make sure you set the [mysql java connector](https://github.com/s412924/MyGUI/blob/5d4a6aa97f56904a6305dab0ea0e08e238a14954/mysql-connector-java-8.0.25.jar) as a dependancy for your IDE 
- [ ] Now all you need to do is run the LoginGUI.java file. Set the file path of the passwords file and then answer the question. You're all setup.
