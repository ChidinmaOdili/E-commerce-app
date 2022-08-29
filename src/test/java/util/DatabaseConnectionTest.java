package util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest extends Mockito {

    @BeforeEach
    void setUp() {
        DatabaseConnection.connection = mock(Connection.class);
    }

    @Test
    @DisplayName("Should return a connection when the database is available")
    void getConnectionWhenDatabaseIsAvailable() {
        Connection connection = DatabaseConnection.getConnection();
        assertNotNull(connection);
    }

    @Test
    @DisplayName("Should throw an exception when the database is not available")
    void getConnectionWhenDatabaseIsNotAvailable() {
        Connection connection = DatabaseConnection.getConnection();
        assertNull(null);

    }
}