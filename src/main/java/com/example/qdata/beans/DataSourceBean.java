/*
    <Q-Data Analytics tool with xlsx import and MySQL DB>
    Copyright (C) 2022-  MikeQMS

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.example.qdata.beans;

import com.example.qdata.model.Data;
import com.example.qdata.services.DataImportService;
import com.example.qdata.services.DataService;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class DataSourceBean implements Serializable {

        @Autowired
        private DataImportService DataImportService;
        @Autowired
        private DataService dataService;
        private List<Data> importedData = new ArrayList<>();
        private UploadedFile file;
        private UploadedFiles files;
        private String dropZoneText = "Drag & Drop the DataFile here";
        private List<Data> filteredData;
        private Data selectedData = new Data();


        // Datei upload über DataImportService
        public void handleFileUpload(FileUploadEvent event) {
            FacesMessage message = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
            file = event.getFile();
            importedData = new ArrayList<>();
            importedData.addAll(DataImportService.createDataImportList(file));
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        // hochgeladene Informationen in DB speichern
        public void importDataSet(){
            FacesMessage message = new FacesMessage("Successful uploaded DataTable.");
            dataService.saveDataSet(importedData);
            importedData.clear();
            FacesContext.getCurrentInstance().addMessage(null, message);
        }


        public UploadedFile getFile() {
            return file;
        }

        public void setFile(UploadedFile file) {
            this.file = file;
        }

        public UploadedFiles getFiles() {
            return files;
        }

        public void setFiles(UploadedFiles files) {
            this.files = files;
        }

        public String getDropZoneText() {
            return dropZoneText;
        }

        public void setDropZoneText(String dropZoneText) {
            this.dropZoneText = dropZoneText;
        }
        public List<Data> getImportedData() {
            return importedData;
        }

    public void setImportedData(List<Data> importedData) {
        this.importedData = importedData;
    }

    public List<Data> getFilteredData() {
        return filteredData;
    }

    public void setFilteredData(List<Data> filteredData) {
        this.filteredData = filteredData;
    }

    public void saveData() {
        System.out.println("Updated: Meldung: "+selectedData.getMeldung());
        importedData.add(selectedData);
        FacesMessage message = new FacesMessage("Meldung bearbeitet", String.valueOf(selectedData.getMeldung()));
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Data getSelectedData() {
        return selectedData;
    }

    public void setSelectedData(Data selectedData) {
        this.selectedData = selectedData;
    }

    public void deleteDataTable(){
        importedData.clear();
        FacesMessage message = new FacesMessage("Data Deleted");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void deleteSelecteData(){
            importedData.remove(selectedData);
        FacesMessage message = new FacesMessage("Meldung "+selectedData.getMeldung()+" gelöscht.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}

