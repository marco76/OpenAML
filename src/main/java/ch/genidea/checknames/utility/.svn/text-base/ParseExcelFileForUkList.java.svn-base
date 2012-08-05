/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package ch.genidea.checknames.utility;

//~--- non-JDK imports --------------------------------------------------------

import ch.genidea.checknames.model.ListEntry;

import ch.genidea.checknames.model.SourceList;
import ch.genidea.checknames.service.SourceListService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;

//~--- JDK imports ------------------------------------------------------------

import java.io.FileInputStream;
import java.io.IOException;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcomolteni
 */
public class ParseExcelFileForUkList {
    private boolean filterOnFirstID = false;
    private String  fileSource;
    public void setFileSource(String fileSource) {
		this.fileSource = fileSource;
	}

	private SourceList sl;

    public List<ListEntry> parseFile(SourceList sl) {
    	/*
    	 * Two string are used to compare if a value is repeated
    	 * the string is build on the basis of the vaules showed and used as 'primary key' for the comparison
    	 */
    	
    	String lastEntry = "";
    	StringBuilder newEntry = null;

        
        List<ListEntry> listEntries = new ArrayList<ListEntry>();
        HSSFWorkbook    wb;
        FileInputStream fis;
        String          cellValue = null;

        try {
            fis = new FileInputStream(fileSource);
            wb  = new HSSFWorkbook(fis);

            String    operatingID = "";
            HSSFSheet sheet       = wb.getSheetAt(0);
            
            newEntry = new StringBuilder();
            
            for (Iterator<HSSFRow> rit = (Iterator<HSSFRow>) sheet.rowIterator(); rit.hasNext(); ) {
                HSSFRow row = rit.next();
                newEntry = new StringBuilder();
                if (row.getRowNum() < 2) {
                    continue;
                }

                ListEntry le = new ListEntry();

                le.setSourceList(sl);

                CellReference cellRef = new CellReference(row.getRowNum(), 1);
                HSSFCell      cell    = row.getCell(0);

                if (filterOnFirstID) {
                    cell = row.getCell(28);

                    String newID = null;

                    if (cell != null) {
                        if (cell.getCellType() == cell.CELL_TYPE_STRING) {
                            newID = getCellValue(row, 28);
                        } else {
                            newID = String.valueOf(getCellNumericValue(row, 28));

                            if (newID.equals("-1")) {
                                newID = null;
                            }
                        }
                    }

                    if (newID == null) {
                        continue;
                    }

                    if (operatingID.equals(newID)) {
                        continue;
                    } else {
                        operatingID = newID;
                    }

                    if (operatingID != null) {
                        BigInteger temp = BigInteger.valueOf(Math.round(Float.parseFloat(operatingID)));

                        le.setUid(temp);
                        newEntry.append(temp.toString());
                    }
                }

                if (cell == null) {

                    // System.out.println ("Cell num " + cell.getRowIndex() + ":" + cell.getColumnIndex());
                    continue;
                }

                cell = row.getCell(0);

                String tempString = cell.getRichStringCellValue().getString();

                if (tempString != null) {
                    le.setFamilyName(tempString);
                    newEntry.append(tempString);
                }

                cell = row.getCell(1);

                if (cell != null) {
                    cellValue = cell.getRichStringCellValue().getString();

                    if (cellValue != null) {
                        le.setFirstName(cellValue);
                        newEntry.append(cellValue);
                    }

                    StringBuilder temp = new StringBuilder();

                    if (cellValue != null) {
                        temp.append(cellValue);

                        for (int cellNum = 2; cellNum < 6; cellNum++) {
                            cellValue = getCellValue(row, cellNum);

                            if (cellValue != null) {
                                temp.append(", " + cellValue);
                               
                            }
                        }
                    }

                    le.setAllNames(temp.toString());
                    newEntry.append(temp.toString());
                }

                cell = row.getCell(7);

                // le.setTitle(cell.getRichStringCellValue().getString());
                le.setTitle(getCellValue(row,6));
                newEntry.append(le.getTitle());
                le.setCountryOfResidence(getCellValue(row,21));
                newEntry.append(le.getCountryOfResidence());
                String uid;
                cell = row.getCell(28);
                if(cell != null)
                {
                if (cell.getCellType() == cell.CELL_TYPE_STRING) {
                    uid = getCellValue(row, 28);
                } else {
                    uid = String.valueOf(getCellNumericValue(row, 28));
                    }
                BigInteger temp = BigInteger.valueOf(Math.round(Float.parseFloat(uid)));

                le.setUid(temp);
                newEntry.append(le.getUid());
                }
                // le.setCountry(cell.getRichStringCellValue().getString());
                // if the two string are identical it's useless
                if (!lastEntry.equals(newEntry.toString()))	
                  listEntries.add(le);
                lastEntry = newEntry.toString();
            }

            fis.close();
        } catch (IOException ex) {
            Logger.getLogger(ParseExcelFileForUkList.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listEntries;
    }

    private String getCellValue(HSSFRow row, int cellNumber) {
        HSSFCell cell = row.getCell(cellNumber);

        if (cell == null) {
            return null;
        }

        return cell.getRichStringCellValue().getString();
    }

    private double getCellNumericValue(HSSFRow row, int cellNumber) {
        HSSFCell cell = row.getCell(cellNumber);

        if (cell == null) {
            return -1;
        }

        return cell.getNumericCellValue();
    }


}


//~ Formatted by Jindent --- http://www.jindent.com
