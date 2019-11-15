/**
 * Tomcat admin tools
 * - for deploying and maintaining Tomcat servers
 * 
 * Copyright (c) 2019 Paul Ramsden
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package de.ramsden.tomcat.admin.action;

import java.io.File;
import java.io.PrintWriter;
import java.net.Socket;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.ramsden.tomcat.admin.model.ServerConfiguration;
import de.ramsden.tomcat.admin.util.XmlHelper;

public class PortShutdownAction {
	private static Logger log = Logger.getLogger(PortShutdownAction.class.getName());
	
	private ServerConfiguration config;

	public PortShutdownAction(ServerConfiguration config) {
		this.config = config;
	}
	
	public void shutdown() {
		try {
			File server = new File(config.getBaseFolder(), "conf/server.xml");
			Document doc = XmlHelper.loadXml(server);
			
			XPath xPath = XPathFactory.newInstance().newXPath();
			Element el = (Element) xPath.compile("/Server").evaluate(doc, XPathConstants.NODE);
			int port = 8005;
			
			String p = el.getAttribute("port");
			if(p.length() > 0)
				port = Integer.parseInt(p);
			String shutdownKey = el.getAttribute("shutdown");
			
			shutdown(port, shutdownKey);
		} catch (Exception e) {
			log.error("Shutdown failed", e);
		}
	}

	/**
	 * @param port
	 * @param shutdownKey
	 */
	public void shutdown(int port, String shutdownKey) {
		if(log.isInfoEnabled())
			log.info(String.format("Issuing shutdown '%s' in port %d", shutdownKey, port));
		
		try { 
		    Socket socket = new Socket("localhost", port); 
		    if (socket.isConnected()) { 
		        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true); 
		        pw.println(shutdownKey);//send shut down command 
		        pw.close(); 
		        socket.close(); 
		    } 
		} catch (Exception e) { 
		    e.printStackTrace(); 
		}
	}
}
