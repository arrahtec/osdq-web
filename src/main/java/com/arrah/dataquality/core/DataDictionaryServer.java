package com.arrah.dataquality.core;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.sql.SQLException;

import com.arrah.framework.dataquality.Rdbms_conn;
import com.itextpdf.text.DocumentException;

public class DataDictionaryServer {

	/**
	 * Creates a data-dictionary that includes the following information Index
	 * information,Procedure Information, PK-FK Information, Data Information,
	 * Metadata Information and Parameter Information. This consumes an instance
	 * of outputstream and renders an output that can be saved as PDF
	 * 
	 * @param output
	 *            an instance of OutputStream
	 * @throws SQLException
	 * @throws DocumentException
	 */

	public static void createDataDictionary(OutputStream output) {

		try {
			Rdbms_conn
					.populateTable(null, null, null, new String[] { "TABLE" });

			DataDictionaryPDF dataPDF = new DataDictionaryPDF();

			try {
				dataPDF.createDDPDF(output);

			} catch (FileNotFoundException se) {
				se.getLocalizedMessage();
			}

		} catch (Exception e) {
			e.getLocalizedMessage();
		} finally {
			try {
				Rdbms_conn.closeConn();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
