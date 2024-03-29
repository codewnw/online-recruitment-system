package com.ors.presentation.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.ors.dao.impl.LoginDaoImpl;
import com.ors.dao.impl.SchemaDaoImpl;
import com.ors.dao.service.SchemaDao;
import com.ors.model.Login;

@WebListener
public class ContextListener implements ServletContextListener {

	public ContextListener() {
	}

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext contxt = arg0.getServletContext();

		String createSchema = contxt.getInitParameter("CreateSchema");
		String dropExistingTables = contxt.getInitParameter("DropExistingTables");

		String dbPropPath = contxt.getRealPath("WEB-INF//properties//db.properties");
		LoadProperty.load(dbPropPath);

		String getPath = contxt.getRealPath("WEB-INF//tables//h2.sql");

		SchemaDao sch = new SchemaDaoImpl();

		if (dropExistingTables != null && dropExistingTables.equalsIgnoreCase("yes")) {
			sch.dropTables(getPath);
		}

		if (createSchema != null && createSchema.equalsIgnoreCase("yes")) {
			sch.createTables(getPath);
			new LoginDaoImpl().insertLogin(new Login("admin", "admin", "admin"));
		}

	}

}
