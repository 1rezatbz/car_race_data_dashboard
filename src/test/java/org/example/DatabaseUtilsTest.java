package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DatabaseUtilsTest {
    @Test
    public void testGetDataSubset() {
        try (Connection conn = DatabaseUtils.getDBConnection()) {
            List<Double> subset = DatabaseUtils.getDataSubset(conn, "CzF", 1000, 1006);
            // Add assertions to validate the results
            Assertions.assertEquals(7, subset.size());
            // Add more assertions as needed
        } catch (SQLException e) {
            Assertions.fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testCalculateAggregateStats() {
        try (Connection conn = DatabaseUtils.getDBConnection()) {
            double aggregateStats = DatabaseUtils.calculateAggregateStats(conn, "AVG","CornerPhase", "Straight-line", "Speed");
            // Add assertions to validate the result
            Assertions.assertEquals(0.7669113155900718, aggregateStats);
            // Add more assertions as needed
        } catch (SQLException e) {
            Assertions.fail("Exception occurred: " + e.getMessage());
        }
    }
}
