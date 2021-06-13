/**
 * 
 */
package com.kensev.connection;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

/**
 * @author kensev
 *
 */
public class ConnectionTest {
	@Test
	public void Test_If_Connection_To_Database_Is_Created() throws SQLException {
		Connection connection = ConnectionPool.getConnection();
		assertTrue("Connection wasnt created", connection != null);
		ConnectionPool.releaseConnection(connection);
	}
	
	@Test
	public void Test_If_ConnectionPool_Loads_Properly() throws SQLException {
		int numberConnectionsInPool = ConnectionPool.getAvailableConnections().size();
		
		// Assert that the connection pool loads with the proper number of connections in the stack and with empty used connections
		assertTrue(numberConnectionsInPool > 0);
		assertTrue(numberConnectionsInPool == ConnectionPool.getMinimumConnections());
		
		// get 1 connection from the stack -> should be pushed to the list of used connections
		Connection connection = ConnectionPool.getConnection();
		
		assertTrue(ConnectionPool.getAvailableConnections().size() == numberConnectionsInPool - 1);
		
		// release the connections -> should be returned to the stack and popped from the list
		ConnectionPool.releaseConnection(connection);
		
		assertTrue(ConnectionPool.getAvailableConnections().size() == numberConnectionsInPool);
	}
	
	@Test 
	public void Test_If_ProxyHandler_Overrides_Connection_Close_Function_With_Connection_Release() throws SQLException {
		// when we call connection.close, the proxy should call the .release function of the connection to retun it to the stack, not to close it.
		
		try(Connection connection = ConnectionPool.getConnection()){

			assertTrue(connection != null);
			// 1 connection popped from the stack and pushed to the list of used.
			assertTrue(ConnectionPool.getAvailableConnections().size() == ConnectionPool.getMinimumConnections() - 1);
		} // TRY WITH clause - after the end should call connection.close();
		
		// asserting that the connection is returned to the pool, not closed.
		assertTrue(ConnectionPool.getAvailableConnections().size() == ConnectionPool.getMinimumConnections());
	}
}
