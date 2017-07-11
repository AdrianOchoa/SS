/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vw.util;

import com.vw.model.RequiredData;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Adrián Ochoa Martínez
 */
public class ExcelFileHandler {

    private final XSSFWorkbook xssfWorkbook;
    private List rowsForSheet;

    /**
     *
     * @param xssfWorkbook
     */
    public ExcelFileHandler(XSSFWorkbook xssfWorkbook) {
        this.xssfWorkbook = xssfWorkbook;
    }

    /**
     *
     * @return true if the easy file was successfully procesed, false otherwise
     */
    public boolean processEasyFile() {
        processData(xssfWorkbook, 1, "", "");
        return true;
    }

    /**
     *
     * @return true if the data warehouse file was successfully procesed, false
     * otherwise
     */
    public boolean processDwhFile() {
        processData(xssfWorkbook, 2, "", "");
        return true;
    }

    /**
     *
     * @return true if the roc file was successfully procesed, false otherwise
     */
    public boolean processRocFile() {
        processData(xssfWorkbook, 3, "", "");
        return true;
    }

    /**
     *
     * @return true if the claim file was successfully procesed, false otherwise
     */
    public boolean processClaimFile() {
        processData(xssfWorkbook, 4, "", "");
        return true;
    }

    /**
     *
     * @return true if the monthly roc file was successfully procesed, false
     * otherwise
     */
    public boolean processMonthlyRocFile(String month, String year) {
        processData(xssfWorkbook, 5, month, year);
        return true;
    }

    /**
     * This method takes a file, upload it to the server, reads the data from
     * it, and builds the query. It calls an instance of the DataBaseHelper
     * class to send the query to the server
     *
     * @param file
     * @param table
     * @return true if the whole proccess was executed. false otherwise
     */
    private boolean processData(XSSFWorkbook file, int table, String month, String year) {
        //First we get the file and set the policy
        XSSFWorkbook workbook = file;
        workbook.setMissingCellPolicy(Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

        //Get the number of sheets for a book
        int maxSheets = workbook.getNumberOfSheets();
        //In this list, we're gonna save all the data from the sheet
        rowsForSheet = new ArrayList();
        /*this var will tell us if we're reading the data warehouse file.
         It's a special case because we're gonna read all the sheets from that file*/
        boolean flag = false;
        DataFormatter defaultFormat = new DataFormatter();
        FormulaEvaluator formulaEvaluator = new XSSFFormulaEvaluator(workbook);

        //Here we go trough every sheet
        for (int i = 0; i < maxSheets; i++) {
            XSSFSheet sheet = workbook.getSheetAt(i);//get the sheet
            String sheetName = sheet.getSheetName().toLowerCase().trim();//get the sheet name
            switch (table) {
                case 1://easy file
                    if (!sheetName.equals("open claims list")) { //we're just interested int this sheet
                        continue;
                    }
                    break;
                case 2://data warehouse file
                    flag = true; //Here, we're gonna read all the sheets
                    break;
                case 3://roc file
                    if (!sheetName.equals("summary claim list")) { //we're just interested in this sheet
                        continue;
                    }
                    break;
                case 4://Claim file
                    if (!sheetName.equals("export worksheet")) { //we're just interested in this sheet
                        continue;
                    }
                    break;
                case 5://monthly roc file
                    if (!sheetName.equals("kriterien - criteria")) { //we're just interested in this file
                        continue;
                    }
                    break;
            }
            int lastCell;
            try {
                //we get the last active row from a sheet
                int lastRow = getLastRow(workbook, sheet);
                //we get the last active cell from a row
                lastCell = (flag) ? ((XSSFRow) sheet.getRow(2)).getLastCellNum()
                        : ((XSSFRow) sheet.getRow(1)).getLastCellNum();
                /*In this for we have two if's.
                 if flag is true, that means we're reading the data warehouse file.
                 this means, we're gonna read 5 diferent sheets, and every sheet's first row
                 has the name of the columns. So, we're gonna read that just from the firse sheet.
                 So, if the flag is true and we're reading the first sheet, we start reading
                 from the second row (flag == true && i == 0). If flag is true and we're reading
                 any other sheet, we start reading from the third row (flag == true && i != 0).
                 If flag is false we always start reading from the first row.*/
                for (int j = (flag) ? (i == 0) ? 2 : 3 : 0; j < lastRow; j++) {
                    //In this list we're gonna save the content of the cells
                    List<String> cells = new ArrayList();
                    XSSFRow row = sheet.getRow(j);
                    for (int k = 0; k < lastCell; k++) {
                        XSSFCell cell = (XSSFCell) row.getCell(k,
                                Row.CREATE_NULL_AS_BLANK);
                        formulaEvaluator.evaluate(cell);
                        //Here we add the content from a cell to out list
                        String cellContent = defaultFormat.formatCellValue(cell, formulaEvaluator);
                        cells.add(cellContent);
                    }
                    //Here, we add the content of the row to our list
                    rowsForSheet.add(cells);
                }
            } finally {
                if (xssfWorkbook != null) {
                    try {
                        xssfWorkbook.close();
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            }
        }
        //In this point we have all the info in our list.

        //Ths first row of our list are the names of the columns read from the file
        List rowNames = (List) rowsForSheet.get(0);

        DataBaseHelper dataBaseHandler = new DataBaseHelper();
        dataBaseHandler.getConnection();
        for (int i = 1; i < rowsForSheet.size(); i++) {
            //we get the row
            List row = (List) rowsForSheet.get(i);
            dataBaseHandler.executeQuery(generateRowQuery(row, rowNames, table, month, year), false);
        }
        dataBaseHandler.closeConnection();
        return true;
    }

    /**
     * This method gets three maps that represents: column name database vs file
     * column name database vs data type column name file vs index in row names
     *
     * @param data
     * @param rowNames
     * @param tableNumber
     * @return a string with the query
     */
    public String generateRowQuery(List data, List rowNames, int tableNumber, String month, String year) {
        LinkedHashMap<String, String> table = getTable(tableNumber);
        LinkedHashMap<String, String> dataTypes = getTableTypes(tableNumber);
        LinkedHashMap<String, Integer> dataIndexes = getDataIndexes(rowNames, table);
        return buildQuery(data, rowNames, table, dataTypes, dataIndexes, tableNumber, month, year);
    }

    private String buildQuery(List data, List rowNames,
            LinkedHashMap<String, String> table,
            LinkedHashMap<String, String> dataTypes,
            LinkedHashMap<String, Integer> dataIndexes, int tableNumber,
            String month, String year) {
        StringBuilder query = new StringBuilder();
        LinkedHashMap<String, String> valuesForInsert = new LinkedHashMap();
        query.append("INSERT INTO [criterios_logicos].[dbo].");
        switch (tableNumber) {
            case 1:
                query.append("[easy] \n(");
                break;
            case 2:
                query.append("[dwh] \n(");
                break;
            case 3:
                query.append("[roc] \n(");
                break;
            case 4:
                query.append("[claim] \n(");
                break;
            case 5:
                query.append("[roc_mensual] \n(");
                break;
        }
        query.append(buildInsert(table, data, valuesForInsert, dataIndexes, tableNumber, rowNames, month, year));
        query.append(buildValues(valuesForInsert, dataTypes));
        return query.toString();
    }

    private String buildInsert(LinkedHashMap<String, String> table,
            List data, LinkedHashMap<String, String> valuesForInsert,
            LinkedHashMap<String, Integer> dataIndexes,
            int tableNumber, List rowNames,
            String month, String year) {
        StringBuilder query = new StringBuilder();

        for (Map.Entry<String, String> entry : table.entrySet()) {
            try {
                //column name in database
                String key = entry.getKey();
                //column name in file
                String value = entry.getValue();

                /*The primary key (id) is going to be build with this columns 
                 id = NUMERO_RECLAMO + SERIAL_RECLAMO + DEALER*/


                /*This switch will detect special cases for each file
                 An special case is, for example, the primary key (id) of each file,
                 because is not included in the file, we have to build it 
                 from differente files.*/
                switch (tableNumber) {
                    case 1://easy
                        if (processEasySpecialCases(data, key, value,
                                query, rowNames, valuesForInsert, dataIndexes)) {
                            continue;
                        }
                        break;
                    case 2://dwh
                        if (processDataSpecialCases(data, key, value,
                                query, valuesForInsert, dataIndexes)) {
                            continue;
                        }
                        break;
                    case 3://roc
                        if (processRocSpecialCases(data, key, value,
                                query, rowNames, valuesForInsert, dataIndexes)) {
                            continue;
                        }
                        break;
                    case 4://claim
                        if (processClaimSpecialCases(data, key, value,
                                query, valuesForInsert, dataIndexes)) {
                            continue;
                        }
                        break;
                    case 5://rocM
                        if (processMonthlySpecialCases(data, key,
                                query, valuesForInsert, dataIndexes, month, year)) {
                            continue;
                        }
                        break;
                }

                String valueInData = "";
                int indexOfData = 0;
                try {
                    indexOfData = dataIndexes.get(value);
                    valueInData = (String) data.get(indexOfData);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                query.append("[").append(key).append("], ");
                valuesForInsert.put(key, valueInData);
            } catch (Exception ex) {
                System.out.println("Error en el build insert" + ex);
            }
        }
        query = new StringBuilder(query.substring(0, query.length() - 2));
        query.append(") VALUES (");
        return query.toString();
    }

    private String buildValues(LinkedHashMap<String, String> valuesForInsert,
            LinkedHashMap<String, String> dataTypes) {
        StringBuilder query = new StringBuilder();
        for (Map.Entry<String, String> entry : valuesForInsert.entrySet()) {
            String value = entry.getValue();//This is the value that's gonna be inserted in the query
            String key = entry.getKey();//This is the name of the field in db inserted in the query
            String dataTye = (String) dataTypes.get(key);
            if (dataTye.equals("string")) {
                query.append("'").append(removeSpecialChars(
                        value.replaceAll("\n", ",").replaceAll("'", "")).trim()).append("', ");
            } else if (dataTye.equals("double")) {
                double valD = 0.0;
                System.out.println("Valor sin replace " + value);
                try {
                    valD = Double.parseDouble(value);
                } catch (NumberFormatException ex) {
                    value = value.replace(",", ".");
                    System.out.println("Valor con replace " + value);
                    try {
                        valD = Double.parseDouble(value);
                    } catch (NumberFormatException e) {
                        valD = 0.0;
                    }
                } catch (NullPointerException ex) {
                    System.out.println(ex);
                }
                query.append(valD).append(", ");
            } else if (dataTye.equals("date")) {
                String[] date = value.split("[/]+");
                String day = date[0];
                if (day.length() == 1) {
                    day = "0" + day;
                }
                String month = date[1];
                if (month.length() == 1) {
                    month = "0" + month;
                }
                String year = date[2];
                String sqlDate = year + "-" + month + "-" + day + " 00:00:00";
                query.append("'").append(sqlDate).append("', ");
            } else if (dataTye.equals("int")) {
                int val = 0;
                try {
                    val = Integer.parseInt(value);
                } catch (Exception ex) {
                    val = 0;
                }
                query.append(val).append(", ");
            }
        }
        query.delete(query.length() - 2, query.length());
        query.append(");");
        return query.toString();
    }

    /**
     *
     * @param data the data from the row
     * @param key column name in database
     * @param value column name in file
     * @param query
     * @param valuesForInsert key = column name in file, value = index
     * @param dataIndexes
     * @return
     */
    private boolean processEasySpecialCases(List data,
            String key, String value, StringBuilder query, List rowNames,
            LinkedHashMap<String, String> valuesForInsert,
            LinkedHashMap<String, Integer> dataIndexes) {
        if (key.equals("easy_ID")) {
            String easyID = buildClaimId(data, dataIndexes,
                    "númerodereclamación", "claimserialno", "taller");
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, easyID);
            return true;
        }
        if (key.equals("easy_claim_type_imp")) {
            String field = dataFromSplitableColumn(value, data, rowNames, false);
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, field);
            return true;
        }
        if (key.equals("easy_claim_type_mft")) {
            String field = dataFromSplitableColumn(value, data, rowNames, false);
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, field);
            return true;
        }
        if (key.equals("easy_claim_date")) {
            String dateOriginal = (String) data.get(dataIndexes.get(value));
            String[] vals = dateOriginal.split("[/]+");
            String day = vals[1];
            String month = vals[0];
            String year = vals[2];
            String date = year + "/" + month + "/" + day;
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, date);
            return true;
        }
        return false;
    }

    private boolean processDataSpecialCases(List data,
            String key, String value, StringBuilder query,
            LinkedHashMap<String, String> valuesForInsert,
            LinkedHashMap<String, Integer> dataIndexes) {
        if (key.equals("dwh_ID")) {
            String easyID = buildClaimId(data, dataIndexes,
                    "repairorder", "claimserial", "dealer");
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, easyID);
            return true;
        }
        if (key.equals("dwh_fecha_reclamacion")) {
            String date =
                    getDateFromDataWarehouse((String) data.get(dataIndexes.get(value)));
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, date);
            return true;
        }
        if (key.equals("dwh_fecha_pago")) {
            String date =
                    getDateFromDataWarehouse((String) data.get(dataIndexes.get(value)));
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, date);
            return true;
        }
        if (key.equals("dwh_mo_int")) {
            double doubleValue =
                    getDoubleFromDataWarehouse((String) data.get(dataIndexes.get(value)));
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, Double.toString(doubleValue));
            return true;
        }
        if (key.equals("dwh_mat_int_sin_profit")) {
            double doubleValue =
                    getDoubleFromDataWarehouse((String) data.get(dataIndexes.get(value)));
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, Double.toString(doubleValue));
            return true;
        }
        if (key.equals("dwh_mo_ext")) {
            double doubleValue =
                    getDoubleFromDataWarehouse((String) data.get(dataIndexes.get(value)));
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, Double.toString(doubleValue));
            return true;
        }
        if (key.equals("dwh_mat_ext_sin_profit")) {
            double doubleValue =
                    getDoubleFromDataWarehouse((String) data.get(dataIndexes.get(value)));
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, Double.toString(doubleValue));
            return true;
        }
        if (key.equals("dwh_total_sin_profit")) {
            double doubleValue =
                    getDoubleFromDataWarehouse((String) data.get(dataIndexes.get(value)));
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, Double.toString(doubleValue));
            return true;
        }
        if (key.equals("dwh_total_pagado")) {
            double doubleValue =
                    getDoubleFromDataWarehouse((String) data.get(dataIndexes.get(value)));
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, Double.toString(doubleValue));
            return true;
        }
        if (key.equals("dwh_productora")) {
            String marca = ((String) data.get(dataIndexes.get("marca"))).toLowerCase();
            marca = (marca.equals("audi")) ? "A" : (marca.equals("seat")) ? "S" : (marca.equals("nfz")) ? "N" : "V";
            String rest = (String) data.get(dataIndexes.get(value));
            rest = (rest.length() == 3) ? "0" + rest : rest;
            marca += rest;
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, marca);
            return true;
        }
        return false;
    }

    private boolean processRocSpecialCases(List data,
            String key, String value, StringBuilder query, List rowNames,
            LinkedHashMap<String, String> valuesForInsert,
            LinkedHashMap<String, Integer> dataIndexes) {
        if (key.equals("roc_id")) {
            String rocID = buildClaimId(data, dataIndexes,
                    "numerodereclamacion", "claimserialno", "taller");
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, rocID);
            return true;
        }
        if (key.equals("roc_claim_type_imp")) {
            String field = dataFromSplitableColumn(value, data, rowNames, false);
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, field);
            return true;
        }
        if (key.equals("roc_claim_type_mft")) {
            String field = dataFromSplitableColumn(value, data, rowNames, false);
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, field);
            return true;
        }
        if (key.equals("roc_criterios")) {
            String field = dataFromSplitableColumn(value, data, rowNames, true);
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, field);
            return true;
        }
        if (key.equals("roc_criterios_version")) {
            String field = dataFromSplitableColumn(value, data, rowNames, true);
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, field);
            return true;
        }
        if (key.equals("roc_claim_date")) { //17-31-01 00:00:00
            String dateOriginal = (String) data.get(dataIndexes.get(value));
            String[] div = dateOriginal.split("[ ]+");
            String[] vals = div[0].split("[/]+");
            String day = (vals[2].length() == 1) ? "0" + vals[2] : vals[2];
            String month = (vals[0].length() == 1) ? "0" + vals[0] : vals[0];
            String year = vals[1];
            String date = day + "/" + month + "/" + year;
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, date);
            return true;
        }
        return false;
    }

    private boolean processClaimSpecialCases(List data,
            String key, String value, StringBuilder query,
            LinkedHashMap<String, String> valuesForInsert,
            LinkedHashMap<String, Integer> dataIndexes) {
        if (key.equals("claim_ID")) {
            String claimID = buildClaimId(data, dataIndexes,
                    "claim_no", "serial_no", "dealer");
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, claimID);
            return true;
        }
        if (key.equals("claim_produc")) {
            String manufacturer = (String) data.get(dataIndexes.get("manufacturer"));
            String rest = (String) data.get(dataIndexes.get(value));
            rest = (rest.length() == 3) ? "0" + rest : rest;
            String produc = manufacturer + rest;
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, produc);
            return true;
        }
        if (key.equals("claim_claim_date")) {
            String date = getClaimDate(value, data, dataIndexes);
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, date);
            return true;
        }
        if (key.equals("claim_fecha_reparacion")) {
            String date = getClaimDate(value, data, dataIndexes);
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, date);
            return true;
        }
        return false;
    }

    private boolean processMonthlySpecialCases(List data,
            String key, StringBuilder query,
            LinkedHashMap<String, String> valuesForInsert,
            LinkedHashMap<String, Integer> dataIndexes,
            String month, String year) {
        if (key.equals("roc_mensual_ID")) {
            String rocMID;
            rocMID = (String) data.get(dataIndexes.get("identificador")) + "*";
            rocMID += month;
            rocMID += year;
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, rocMID);
            return true;
        }
        if (key.equals("roc_mensual_fecha")) {
            String date = year + month + "01";
            query.append("[").append(key).append("], ");
            valuesForInsert.put(key, date);
            return true;
        }
        return false;
    }

    private String buildClaimId(List data, LinkedHashMap<String, Integer> dataIndexes, String numero,
            String serial, String dealer) {
        String claimID = "";
        String numeroReclamacion;
        String serialReclamacion;
        String dealerReclamacion;
        try {
            numeroReclamacion = (String) data.get(dataIndexes.get(numero));
        } catch (ArrayIndexOutOfBoundsException ex) {
            numeroReclamacion = "NF";
        }
        try {
            serialReclamacion = (String) data.get(dataIndexes.get(serial));
            if (serialReclamacion.length() < 2) {
                serialReclamacion = "0" + serialReclamacion;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            serialReclamacion = "NF";
        }
        try {
            dealerReclamacion = (String) data.get(dataIndexes.get(dealer));
            if (dealerReclamacion.length() > 4) {
                dealerReclamacion = dealerReclamacion.substring(0, 4);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            dealerReclamacion = "NF";
        }
        claimID = fillIfNull(numeroReclamacion).trim()
                + fillIfNull(serialReclamacion).trim()
                + fillIfNull(dealerReclamacion).trim();
        return claimID;
    }

    private int findIndex(String value, List rowNames) {
        for (int i = 0; i < rowNames.size(); i++) {
            String val = removeSpecialChars(((String) rowNames.get(i))
                    .toLowerCase().trim().replace(" ", "")).replace(".", "");
            value = removeSpecialChars(value).replace(".", "");
            if (val.equals(value)) {
                return i;
            }
        }
        return -1;
    }

    private String dataFromSplitableColumn(String column,
            List data, List rowNames, boolean flag) {
        String value = "";
        String[] values = column.split("[*]+");
        String[] vals = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            try {
                vals[i] = (String) data.get(findIndex(values[i], rowNames));
            } catch (Exception ex) {
                vals[i] = "";
            }
        }
        if (flag) {
            for (String val : vals) {
                value += val + "*";
            }
        } else {
            for (String val : vals) {
                value += val;
            }
        }
        while (true) {
            if (value.endsWith("*")) {
                value = value.substring(0, value.length() - 1);
            } else {
                break;
            }
        }
        return value;
    }

    private LinkedHashMap<String, Integer> getDataIndexes(List rowNames,
            LinkedHashMap<String, String> table) {
        LinkedHashMap<String, Integer> indexes = new LinkedHashMap();
        for (Map.Entry<String, String> entry : table.entrySet()) {
            String columnNameFile = entry.getValue();
            int index = findIndex(columnNameFile, rowNames);
            indexes.put(columnNameFile, index);
        }
        return indexes;
    }

    private LinkedHashMap<String, String> getTable(int table) {
        RequiredData requiredData = new RequiredData();
        switch (table) {
            case 1:
                return requiredData.getEasy();
            case 2:
                return requiredData.getData();
            case 3:
                return requiredData.getRoc();
            case 4:
                return requiredData.getClaim();
            case 5:
                return requiredData.getMonthlyRoc();
        }
        return null;
    }

    private LinkedHashMap<String, String> getTableTypes(int table) {
        RequiredData requiredData = new RequiredData();
        switch (table) {
            case 1:
                return requiredData.getEasyTypes();
            case 2:
                return requiredData.getDataTypes();
            case 3:
                return requiredData.getRocTypes();
            case 4:
                return requiredData.getClaimTypes();
            case 5:
                return requiredData.getMonthlyTypes();
        }
        return null;
    }

    private String removeSpecialChars(String string) {
        String normalizaString = Normalizer.normalize(string, Normalizer.Form.NFD);
        String clearString = normalizaString.replaceAll("[^\\p{ASCII}]", "");
        return clearString;
    }

    private String fillIfNull(String s) {
        if (s == null || s.isEmpty() || s.equals("")) {
            return "NF";
        } else {
            return s;
        }
    }

    private String getClaimDate(String value, List data,
            LinkedHashMap<String, Integer> dataIndexes) {
        HashMap<String, String> months = getMonths();
        String dateOriginal = (String) data.get(dataIndexes.get(value));
        String[] div = dateOriginal.split("[ ]+");
        boolean flag = div[0].contains("-");
        String day;
        String month;
        String year;
        if (flag) {
            String[] vals = div[0].split("[-]+");
            day = (vals[0].length() == 1) ? "0" + vals[0] : vals[0];
            month = months.get(vals[1].toUpperCase());
            year = (vals[2].length() == 1) ? "20" + vals[2] : vals[2];
        } else {
            String[] vals = div[0].split("[/]+");
            day = (vals[0].length() == 1) ? "0" + vals[0] : vals[0];
            month = (vals[1].length() == 1) ? "0" + vals[1] : vals[1];
            year = (vals[2].length() == 2) ? "20" + vals[2] : vals[2];
        }
        String date = day + "/" + month + "/" + year;
        return date;
    }

    private HashMap<String, String> getMonths() {
        HashMap<String, String> months = new HashMap<String, String>();
        months.put("JAN", "01");
        months.put("FEB", "02");
        months.put("MAR", "03");
        months.put("APR", "04");
        months.put("MAY", "05");
        months.put("JUN", "06");
        months.put("JUL", "07");
        months.put("AUG", "08");
        months.put("SEP", "09");
        months.put("OCT", "10");
        months.put("NOV", "11");
        months.put("DEC", "12");
        return months;
    }

    private double getDoubleFromDataWarehouse(String value) {
        value = value.replaceAll(",", "");
        if (value.contains("$")) {
            value = value.replace("$", "");
        }
        return Double.parseDouble(value.trim());
    }

    private String getDateFromDataWarehouse(String value) {
        String[] vals = value.split("[/]+");
        String day = (vals[0].length() == 1) ? "0" + vals[0] : vals[0];
        String month = (vals[1].length() == 1) ? "0" + vals[1] : vals[1];
        String year = (vals[2].length() == 2) ? "20" + vals[2] : vals[2];
        String date = month + "/" + day + "/" + year;
        return date;
    }

    private int getLastRow(XSSFWorkbook workbook, XSSFSheet sheet) {
        if (workbook instanceof XSSFWorkbook) {
            XSSFFormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            DataFormatter formatter = new DataFormatter(true);
            int lastRowIndex = -1;
            if (sheet.getPhysicalNumberOfRows() > 0) {
                lastRowIndex = sheet.getLastRowNum();
                for (; lastRowIndex >= 0; lastRowIndex--) {
                    Row row = sheet.getRow(lastRowIndex);
                    if (!isRowEmpty(row, formatter, evaluator)) {
                        break;
                    }
                }
            }
            return lastRowIndex;
        }
        return 0;
    }

    private boolean isRowEmpty(Row row,
            DataFormatter formatter, XSSFFormulaEvaluator evaluator) {
        if (row == null) {
            return true;
        }
        int cellCount = row.getLastCellNum() + 1;
        for (int i = 0; i < cellCount; i++) {
            String cellValue = getCellValue(row, i, formatter, evaluator);
            if (cellValue != null && cellValue.length() > 0) {
                return false;
            }
        }
        return true;
    }

    private String getCellValue(Row row, int columnIndex,
            DataFormatter formatter, XSSFFormulaEvaluator evaluator) {
        String cellValue;
        Cell cell = row.getCell(columnIndex);
        if (cell == null) {
            cellValue = null;
        } else {
            if (cell.getCellType() != Cell.CELL_TYPE_FORMULA) {
                cellValue = formatter.formatCellValue(cell);
            } else {
                cellValue = formatter.formatCellValue(cell, evaluator);
            }
        }
        return cellValue;
    }
}
