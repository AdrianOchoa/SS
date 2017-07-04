/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vw.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This class will convert an xsl excel to an xlsx excel for standar usage
 *
 * @author Adrián Ochoa Martínez
 */
public class ExcelConverter implements Callable <XSSFWorkbook> {
    
    private HSSFWorkbook source;
    
    public ExcelConverter (HSSFWorkbook source) {
        this.source = source;
    }
    
    @Override
    public XSSFWorkbook call() throws Exception {
        XSSFWorkbook retVal = new XSSFWorkbook();
        for (int i = 0; i < source.getNumberOfSheets(); i++) {
            HSSFSheet hSSFSheet = source.getSheetAt(i);
            XSSFSheet xSSFSheet = retVal.createSheet(hSSFSheet.getSheetName());
            copySheets(hSSFSheet, xSSFSheet);
        }
        return retVal;
    }

//    public static XSSFWorkbook convertHSSFWorkbookToXSSFWorkbook(HSSFWorkbook source) {
//        XSSFWorkbook retVal = new XSSFWorkbook();
//        for (int i = 0; i < source.getNumberOfSheets(); i++) {
//            HSSFSheet hSSFSheet = source.getSheetAt(i);
//            XSSFSheet xSSFSheet = retVal.createSheet(hSSFSheet.getSheetName());
//            copySheets(hSSFSheet, xSSFSheet);
//        }
//        return retVal;
//    }

    public static void copySheets(HSSFSheet source, XSSFSheet destination) {
        copySheets(source, destination, true);
    }

    public static void copySheets(HSSFSheet source, XSSFSheet destination, boolean copyStyle) {
        int maxColumnNum = 0;
        Map<Integer, HSSFCellStyle> styleMap = (copyStyle) ? new HashMap<Integer, HSSFCellStyle>() : null;
        for (int i = source.getFirstRowNum(); i < source.getLastRowNum(); i++) {
            HSSFRow srcRow = source.getRow(i);
            XSSFRow destRow = destination.createRow(i);
            if (srcRow != null) {
                copyRow(source, destination, srcRow, destRow, styleMap);
                if (srcRow.getLastCellNum() > maxColumnNum) {
                    maxColumnNum = srcRow.getLastCellNum();
                }
            }
        }
        for (int i = 0; i <= maxColumnNum; i++) {
            destination.setColumnWidth(i, source.getColumnWidth(i));
        }
    }

    public static void copyRow(HSSFSheet srcSheet, XSSFSheet destSheet,
            HSSFRow srcRow, XSSFRow destRow, Map<Integer, HSSFCellStyle> styleMap) {
        //manage a list of merged zone in order to not insert two times a merged zone
        Set<CellRangeAddressWrapper> mergedRegions = new TreeSet<CellRangeAddressWrapper>();
        destRow.setHeight(srcRow.getHeight());
        //pour chaque row
        for (int i = srcRow.getFirstCellNum(); i <= srcRow.getLastCellNum(); i++) {
            HSSFCell oldCell = srcRow.getCell(i);
            XSSFCell newCell = destRow.getCell(i);
            if(oldCell != null) {
                if(newCell == null) {
                    newCell = destRow.createCell(i);
                }
                //copy chaque cell
                copyCell (oldCell, newCell, styleMap);
                CellRangeAddress mergedRegion = getMergedRegion(srcSheet, 
                        srcRow.getRowNum(), (short) oldCell.getColumnIndex());
                if(mergedRegion != null) {
                    CellRangeAddress newMergedRegion = new CellRangeAddress(
                            mergedRegion.getFirstRow(),mergedRegion.getLastRow(),
                            mergedRegion.getFirstColumn(), mergedRegion.getLastColumn());
                    CellRangeAddressWrapper wrapper = new CellRangeAddressWrapper(newMergedRegion);
                    if(isNewMergedRegion(wrapper, mergedRegions)) {
                        mergedRegions.add(wrapper);
                        destSheet.addMergedRegion(wrapper.range);
                    }
                }
            }
        }
        System.gc();
    }
    
    public static void copyCell (HSSFCell oldCell, XSSFCell newCell, Map<Integer, HSSFCellStyle> styleMap) {
        if(styleMap != null) {
            int stHashCode = oldCell.getCellStyle().hashCode();
            HSSFCellStyle sourceCellStyle = styleMap.get(stHashCode);
            XSSFCellStyle destnCellStyle = newCell.getCellStyle();
            if(sourceCellStyle == null) {
                sourceCellStyle = oldCell.getSheet().getWorkbook().createCellStyle();
            }
//            destnCellStyle.cloneStyleFrom(oldCell.getCellStyle());
            styleMap.put(stHashCode, sourceCellStyle);
            newCell.setCellStyle(destnCellStyle);
        }
        switch(oldCell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                newCell.setCellValue(oldCell.getStringCellValue());
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                newCell.setCellValue(oldCell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                newCell.setCellType(HSSFCell.CELL_TYPE_BLANK);
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                newCell.setCellValue(oldCell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                newCell.setCellValue(oldCell.getErrorCellValue());
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                newCell.setCellValue(oldCell.getCellFormula());
                break;
            default :
                break;
        }
        System.gc();
    }
    
    public static CellRangeAddress getMergedRegion (HSSFSheet sheet, int rowNum, short cellNum) {
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            CellRangeAddress merged = sheet.getMergedRegion(i);
            if(merged.isInRange(rowNum, cellNum)) {
                return merged;
            }
        }
        return null;
    }
    
    private static boolean isNewMergedRegion (CellRangeAddressWrapper newMergedRegion,
            Set<CellRangeAddressWrapper> mergedRegions) {
        return !mergedRegions.contains(newMergedRegion);
    }

    private static class CellRangeAddressWrapper implements Comparable<CellRangeAddressWrapper> {

        public CellRangeAddress range;

        public CellRangeAddressWrapper(CellRangeAddress range) {
            this.range = range;
        }

        @Override
        public int compareTo(CellRangeAddressWrapper o) {
            if (range.getFirstColumn() < o.range.getFirstColumn()
                    && range.getFirstRow() < o.range.getFirstRow()) {
                return -1;
            } else if (range.getFirstColumn() == o.range.getFirstColumn()
                    && range.getFirstRow() == o.range.getFirstRow()) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
