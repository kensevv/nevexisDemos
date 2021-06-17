package fes.logon;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

import com.frantishex.persistent.base.ConnectionManager;

public class ConnectionFilter implements Filter {

	private static Logger logger = Logger.getLogger(ConnectionFilter.class);

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		logger.debug("Connection Filter Initialized");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		try {
			ConnectionManager.setCnn();
			filterChain.doFilter(request, response);
		} catch (UnknownHostException | SQLException e) {
			e.printStackTrace();
			throw new Error("Fatal - Connection Filter: ", e);
		} finally {
			ConnectionManager.releaseConnection();
		}
	}

	@Override
	public void destroy() {
		ConnectionManager.destroyPool();
	}


}