package org.example;

import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Route;
import static spark.Spark.*;

// Import DatabaseUtils class
import org.example.DatabaseUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class API {
    public static void main(String[] args) {
        port(8080);

        // Add headers to each response
        before((Filter) (Request request, Response response) -> {
            response.header("Content-Type", "application/json");
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, x-test-header");
        });

        // Home route that displays available routes
        get("/", (Route) (Request request, Response response) -> {
            String routes = "{\"subset\": \"/subset/{column_name}/{start_row}/{end_row}\", " +
                    "\"aggregate\": \"/aggregate/{condition_column}/{condition_value}/{stats_column}\"}";
            return routes;
        });

        // Route for retrieving a data subset
        get("/subset/:column_name/:start_row/:end_row", (Route) (Request request, Response response) -> {
            String columnName = request.params(":column_name");
            int startRow = Integer.parseInt(request.params(":start_row"));
            int endRow = Integer.parseInt(request.params(":end_row"));

            try (Connection conn = DatabaseUtils.getDBConnection()) {
                List<Double> subset = DatabaseUtils.getDataSubset(conn, columnName, startRow, endRow);
                return subset;
            } catch (SQLException e) {
                response.status(500);
                return "{\"error\": \"" + e.getMessage() + "\"}";
            }
        });

        // Route for calculating aggregate statistics
        get("/aggregate/:aggregate_function/:condition_column/:condition_value/:stats_column", (Route) (Request request, Response response) -> {
            String aggregateFunction = request.params(":aggregate_function");
            String conditionColumn = request.params(":condition_column");
            String conditionValue = request.params(":condition_value");
            String statsColumn = request.params(":stats_column");

            try (Connection conn = DatabaseUtils.getDBConnection()) {
                double aggregateStats = DatabaseUtils.calculateAggregateStats(conn, aggregateFunction, conditionColumn, conditionValue, statsColumn);
                return aggregateStats;
            } catch (SQLException e) {
                response.status(500);
                return "{\"error\": \"" + e.getMessage() + "\"}";
            }
        });
    }
}
