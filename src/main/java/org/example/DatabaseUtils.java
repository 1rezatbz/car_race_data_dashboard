package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {
    private static final String DATABASE = "/Users/reza/Desktop/data_race/src/data.db";

    // Establishes a connection to the SQLite database
    public static Connection getDBConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DATABASE);
        conn.setAutoCommit(false);
        return conn;
    }

    // Retrieves a subset of data based on the provided parameters
    public static List<Double> getDataSubset(Connection conn, String columnName, int startRow, int endRow) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            String query = "SELECT rd." + columnName + " FROM race_data AS rd WHERE rd.Timekey BETWEEN " + startRow + " AND " + endRow;
            ResultSet resultSet = stmt.executeQuery(query);

            List<Double> subset = new ArrayList<>();
            while (resultSet.next()) {
                subset.add(resultSet.getDouble(1));
            }
            return subset;
        }
    }

    // Calculates aggregate statistics based on the provided condition
    public static double calculateAggregateStats(Connection conn, String aggregateFunction, String conditionColumn, String conditionValue, String statsColumn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT " + aggregateFunction + "(" + statsColumn + ") FROM race_data WHERE " + conditionColumn + " = ?")) {
            stmt.setString(1, conditionValue);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            } else {
                throw new SQLException("No aggregate stats found");
            }
        }
    }
}
