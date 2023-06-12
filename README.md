# Data Race Analysis Tool


### Description

The Data Race Analysis Tool is a web application that allows users to query and analyze data from a SQLite database containing race data. It provides two main functionalities:

1. **Query Data Subset Info**: Users can specify a column name, start time, and end time to retrieve a subset of data from the database. The tool will display the selected column values within the specified time range.

2. **Aggregate Statistics**: Users can calculate aggregate statistics based on a condition. They can choose an aggregate function (e.g., AVG, COUNT, SUM, MAX, MIN), a condition column, a condition value, and a statistics column. The tool will compute the aggregate value for the specified condition and display the result.

### Technologies Used

- Java
- SQLite
- HTML
- CSS
- JavaScript
- jQuery

## File Structure

- src/
    - main/
        - java/
            - org.example/
                - DatabaseUtils.java
                - DatabaseUtilsTest.java
    - template/
        - img/
        - index.html
        - script.js
        - style.css
    - test/
        - java/
            - org.example/
                - DatabaseUtilsTest.java
    - data.sql

    
## Creating a SQLite Database

To create the SQLite database, we have imported our data from a CSV file. SQLite provides a straightforward process for importing CSV data into a database table. By defining the table schema and using appropriate data types, we ensure the integrity and consistency of the data. SQLite's efficient storage format and indexing techniques optimize data retrieval, enabling faster query execution and improved performance.


With our data stored in a SQLite database, we can now leverage the power of SQL for querying and manipulating the data. SQL offers a rich set of features for data analysis, filtering, sorting, aggregation, and joining multiple tables. We can easily write complex queries to extract valuable insights and perform advanced data operations that are not possible or efficient with raw CSV files.


### How to Use

1. Clone the repository:


2. Set up the database:

    - Make sure you have SQLite installed.
    - Import the provided `data.db` file into your local SQLite database.

3. Open the project in your preferred Java IDE.

4. Modify the `DatabaseUtils.java` file:

    - Update the `DATABASE` variable with the path to your `data.db` file.

5. Build and run the project.

6. Open the application in your web browser by accessing `http://localhost:8080` or `http://127.0.0.1:8080`.

7. Query Data Subset Info:

    - Select a column name from the dropdown menu.
    - Enter the start and end times.
    - Click the "Get Subset" button.
    - The results will be displayed below.

8. Aggregate Statistics:

    - Select an aggregate function from the dropdown menu.
    - Select a condition column from the dropdown menu.
    - Enter a condition value.
    - Select a statistics column from the dropdown menu.
    - Click the "Get Statistics" button.
    - The result will be displayed below.

### Test
The test code in the DatabaseUtilsTest class validates the functionality of 
the getDataSubset and calculateAggregateStats methods in the DatabaseUtils 
class by asserting expected results against the actual results obtained from the SQLite database connection.