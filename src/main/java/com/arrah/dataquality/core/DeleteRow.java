package com.arrah.dataquality.core;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@XmlRootElement
public class DeleteRow {
	private String dbstr;
	private String message;
	private String table, column, resp;
	private Object value;

	static int rowCount;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DeleteRow.class);

	public DeleteRow(String _dbstr, String _table, String _column,
			Object _value, String _resp)  {
		dbstr = _dbstr;
		table = _table;
		column = _column;
		value = _value;
		resp = _resp;
		deleteRec(dbstr, table, column, value, resp);
	}

	public DeleteRow() {
	}

	@XmlElement
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void deleteRec(String _dbstr, String _table, String _column,
			Object value, String resp) {

		try {
			if (resp.equalsIgnoreCase("yes") || resp.equalsIgnoreCase("y")) {
				DeleteRowServer.deleteRecord(_dbstr, _table, _column, value);
				setMessage("query executed successfully!");
			} else if (resp.equalsIgnoreCase("no")
					|| resp.equalsIgnoreCase("n")) {
				setMessage("Records were not deleted");
			} else {
				setMessage("Please enter a valid input");
			}
		} catch (Exception se) {
			setMessage("ERROR!!" + se.getLocalizedMessage());
			LOGGER.error("Error in delete row", se);
		}

	}

}
