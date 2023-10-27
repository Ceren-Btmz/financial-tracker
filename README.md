# Financial Tracker 💵📝

The Financial Tracker is a Java-based application for managing your financial transactions. With this application, you can keep track of your deposits and payments, view your financial ledger, and generate various reports to analyze your financial activities.


## Features:

* Add Deposits: Record your income by specifying the date, time, description, vendor, and deposit amount.
* Add Payments (Debits): Record your expenses by specifying the date, time, description, vendor, and payment amount.
* Ledger: View all your financial transactions, including both deposits and payments.
* Reports: Generate various financial reports based on different criteria, such as month-to-date, previous month, year-to-date, previous year, and vendor-based searches.

## How to Use:

1. Clone or download this repository to your local machine.
2. Compile and run the 'FinancialTracker.java' file
3. Follow the on-screen instructions to navigate through the application:

   ![1.png](imgs%2F1.png)

    For adding deposits and payments, you will be prompted to enter date, time, description, vendor, and the corresponding amount.

4. In the ledger, you can choose to view all transactions, deposits, or payments. The transactions will be displayed on the screen.

5. In the reports menu, you can select from the following options:

    ![2.png](imgs%2F2.png)

6. **Data Storage:** The application stores your financial transactions in a file named transactions.csv. Ensure that this file is in the project directory and is not modified manually to avoid data corruption.

7. To exit the application, choose the "X" option from the main menu.   

## Dependencies:

* The Financial Tracker project has no external dependencies other than the standard Java libraries included with the JDK. Make sure you have a working Java environment (JDK) set up on your system.
* This project is built using Java and relies on the following libraries and classes:

  * 'java.io.*' for file input and output.
  * 'java.time.*' for handling date and time.
  * 'java.util.Scanner' for user input.
  * 'ArrayList<Transaction>' for managing a list of financial transactions.
  * 'DateTimeFormatter' for parsing and formatting date and time.
  
* Once you have followed these steps, you should be able to run the Financial Tracker and start managing your financial transactions.

## Special Feature:

This part of the code was very fun and entertaining to me because it was an area we had not fully covered. Creating a line of code that properly expressed the correct parameters felt like solving a math equation. This part of the code enhances the project because it reduces the need to have separate methods for each limitation of data. 

   ![3.png](imgs%2F3.png)

## Future Work:


* User Authentication: Implement user accounts and authentication to ensure the privacy and security of financial data.
* Data Export: Allow users to export their transaction data in various formats (e.g., CSV, Excel) for reporting and analysis in other tools.
* Budgeting and Categories: Introduce the ability to categorize transactions and set up budgets to track spending in specific categories.
* Limited Error Handling: The project may have limited error handling in its current state. Enhancing error handling can provide more user-friendly feedback and prevent application crashes due to unexpected inputs.
* Date and Time Formats: The project assumes specific date and time formats. It could be enhanced to support more flexible date and time input formats.

## Gratitude and Thanks!

I would like to express my sincere gratitude to the following individuals and resources that have been instrumental in the development of the Financial Tracker project:

A special thanks to **Raymond** for his invaluable guidance, support, and expertise throughout the development process. His insights and advice have been crucial in shaping this project.

I am deeply grateful to the open-source community for providing a wealth of knowledge and resources that have been crucial in building this application. Open-source libraries, frameworks, and forums have been invaluable in overcoming challenges and learning.

The Java development community has been an endless source of knowledge and inspiration. Countless online tutorials, forums, and documentation have been indispensable in understanding Java and its libraries.

A big thank you to all of my peers who helped and guided me on this project, whether through code contributions, testing, or feedback. A special thanks to **Hamza** for the support he's given me in aiding me in right direction. Your efforts have improved the quality and functionality of the Financial Tracker.