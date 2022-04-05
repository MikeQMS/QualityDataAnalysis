package com.example.qdata.services;

import com.example.qdata.model.Data;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class DataImportService {

    @Autowired
    private DataService dataService;
    private ArrayList<Data> importedData;
    private InputStream newFile;
    private Workbook workbook;

    public Set<Data> createDataImportList(UploadedFile file){
        importedData = new ArrayList<>();
        try {
            newFile = file.getInputStream();
            workbook = new XSSFWorkbook(newFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = workbook.getSheetAt(0);
        int i = 0;
        for (Row row: sheet){
            if (row.getRowNum()==0)
                continue;
            if(row.getCell(0).getStringCellValue().equals(""))
                continue;
            Data data = new Data();
            for(Cell cell : row){
                switch(sheet.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue()) {
                    case "Meldung":
                        data.setMeldung(Integer.parseInt(cell.getStringCellValue()));
                        break;
                    case "StörBeginn":
                        data.setStoerBeginn(cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        break;
                    case "Art":
                        data.setArt(cell.getStringCellValue());
                        break;
                    case "Material":
                        data.setMat(cell.getStringCellValue());
                        break;
                    case "Materialkurztext":
                        data.setMatKurzText(cell.getStringCellValue());
                        break;
                    case "Positionstext":
                        data.setPositionText(cell.getStringCellValue());
                        break;
                    case "Beschreibung":
                        data.setBeschreibung(cell.getStringCellValue());
                        break;
                    case "Baumuster":
                        data.setBaumuster(Integer.parseInt(cell.getStringCellValue()));
                        break;
                    case "Verantwrtl":
                        if (!cell.getStringCellValue().equals(""))
                            data.setVerantwortlicher(Integer.parseInt(cell.getStringCellValue()));
                        break;
                    case "ArbPlatz":
                        if(!cell.getStringCellValue().equals("")) {
                            data.setArbeitsplatz(Integer.parseInt(cell.getStringCellValue()));
                        }
                        break;
                    case "MaCd":
                        data.setMassnahmenCode(Integer.parseInt(cell.getStringCellValue()));
                        break;
                    case "Maßnahmentext":
                        data.setMassnahmenText(cell.getStringCellValue());
                        break;
                    case "Referenznummer":
                        data.setRefNumber(cell.getStringCellValue());
                        break;
                    case "Angel.von":
                        data.setCreatedBy(cell.getStringCellValue());
                        break;
                    case "ErlDatum":
                        if(cell.getCellType()== CellType.NUMERIC || !cell.getStringCellValue().equals("")){
                            data.setTaskClosed(cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        }
                        break;
                    case "Erledigt von":
                        data.setTaskClosedBy(cell.getStringCellValue());
                        break;
                    case "Werk":
                        data.setWerksnummer(Integer.parseInt(cell.getStringCellValue()));
                        break;
                    case "Nr.":
                        data.setUrsache(Integer.parseInt(cell.getStringCellValue()));
                        break;
                    case "Ursa":
                        data.setUrsachenNummer(cell.getStringCellValue());
                        break;
                    case "Int. Ver. Arbeitsplatz":
                        data.setInternalWorkplace(cell.getStringCellValue());
                        break;
                    case "Prüfling":
                        data.setPruefling(cell.getStringCellValue());
                        break;
                    case "CgrProbl":
                        data.setCgrprobl(cell.getStringCellValue());
                        break;
                    case "SCd":
                        data.setScd(cell.getStringCellValue());
                        break;
                    case "Nr. Pos":
                        data.setPositionNumber(Integer.parseInt(cell.getStringCellValue()));
                        break;
                    case "Nr. Ort":
                        data.setOrtnumber(Integer.parseInt(cell.getStringCellValue()));
                        break;
                    case "Baugruppe":
                        data.setBaugruppe(cell.getStringCellValue());
                        break;
                    case "Planpreis 3":
                        data.setPrice(cell.getNumericCellValue());
                        break;
                }
            }
            importedData.add(data);
        }
        Set<Data> set = new HashSet<>(importedData);
        return set;
    }


}
