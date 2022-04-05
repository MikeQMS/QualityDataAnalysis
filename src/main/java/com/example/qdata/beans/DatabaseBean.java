package com.example.qdata.beans;


import com.example.qdata.model.Data;
import com.example.qdata.model.SchadenCode;
import com.example.qdata.services.DataService;
import org.apache.commons.lang3.SerializationUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class DatabaseBean {

    @Autowired
    private DataService dataService;
    private List<Data> database;
    private List<Data> filteredData;
    private Data selectedData = new Data();
    private Data saveData = new Data();
//    private SchadenCode schadenCodes;


    @PostConstruct
    public void init() {
        database = dataService.listData();
        PrimeFaces.current().ajax().update("form");
    }

    public void refresh() {database = dataService.listData();}


    public List<Data> getDatabase() {
        return database;
    }

    public void setDatabase(List<Data> database) {
        this.database = database;
    }

    public List<Data> getFilteredData() {
        return filteredData;
    }

    public void setFilteredData(List<Data> filteredData) {
        this.filteredData = filteredData;
    }

    public Data getSelectedData() {
        return selectedData;
    }

    public void setSelectedData(Data selectedData) {
        this.selectedData = selectedData;
    }

    public void openNew(){
        this.selectedData = new Data();
    }

    public Data getSaveData() {
        return saveData;
    }

    public void setSaveData(Data saveData) {
        this.saveData = saveData;
    }

    public void save(){
        Data newTodo = SerializationUtils.clone(selectedData); //erzeugt ein neues Objekt
        dataService.saveData(newTodo);
        database = dataService.listData();
        FacesMessage message = new FacesMessage("Meldung: "+ newTodo.getMeldung() +" gespeichert");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void deleteData(){
        if(dataService.removeById(selectedData.getId())) {
            database = dataService.listData();
            FacesMessage message = new FacesMessage("Meldung: "+ selectedData.getMeldung() +" gelöscht", "Mit ID "+ selectedData.getId());
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage("Something went wrong during delete of ", String.valueOf(selectedData.getId()));
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        }

    public String scdEnum (String datascd){
        return SchadenCode.valueOf(datascd).label;
    }


}


