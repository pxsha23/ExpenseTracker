#ExpenseTracker
A desktop application built in Java using Swing to help you easily track, categorize, and manage daily expenses with a real-time clock and SQLite database storage.

🚀 Features
Add expenses with category, amount, and date

View all expenses in a table view

Delete the most recent expense entry

Live clock display in the UI

Persistent storage using an embedded SQLite database

🗂️ Project Structure
Place your .java files in a folder like:

css
Copy code
ExpenseTracker/
├── Main.java
├── ExpenseTrackerGUI.java
├── DBHelper.java
├── ExpenseManager.java
└── Expense.java
File responsibilities:

Main.java – Launches the GUI

ExpenseTrackerGUI.java – Swing UI and interactions

DBHelper.java – Sets up/opens SQLite database and tables

ExpenseManager.java – CRUD operations (add, read, delete)

Expense.java – Model representing an expense record
