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
package de.ramsden.tomcat.admin.model;

import java.io.File;
import java.io.Serializable;

public class ServerConfiguration implements Serializable {
	private static final long serialVersionUID = 6162414946343273884L;
	
	private String configurationName;
	private File baseFolder;
	
	public String getConfigurationName() {
		return configurationName;
	}
	public void setConfigurationName(String configurationName) {
		this.configurationName = configurationName;
	}
	public File getBaseFolder() {
		return baseFolder;
	}
	public void setBaseFolder(File baseFolder) {
		this.baseFolder = baseFolder;
	}
}
