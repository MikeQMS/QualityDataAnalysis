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
import com.example.qdata.model.SchadenCode;
import com.example.qdata.services.DataService;
import com.example.qdata.services.ParetoService;
import org.primefaces.PrimeFaces;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.AxesGridLines;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class ParetoBean implements Serializable {

    @Autowired
    private DataService dataService;
    @Autowired
    private ParetoService paretoService;

    private BarChartModel mixedModel;
    private List<LocalDate> multi;
    private List<Data> selectedDataList;
    private List<Data> selectedData;
    private List<Data> filteredData;
    private Map<String, Integer> fehlerCodeAmount;
    private Map<String, List<Data>> dataModel;
    private SchadenCode schadenCode;
    private List<SortMeta> sortBy;


    @PostConstruct
    public void init() {
        if (dataService.listData().isEmpty()){
            dataService.saveData(new Data(0, LocalDate.of(1990,1,1), "0", "0", "0", "0", "DummyEntry", 0, 0, 0, 0, "0", "0", "0", LocalDate.now() , "0", 0, 0, "0", "0", "0", "0", "ZZZZ", 0, 0, "0", 0));
        }
        multi = new ArrayList<>();
        multi.add(findDateMin());
        multi.add(findDateMax());
        createMixedModel();
        selectedDataList = dateList();
        createDataModel();
        sortBy = new ArrayList<>();
        sortBy.add(SortMeta.builder()
                .field("name")
                .order(SortOrder.ASCENDING)
                .build());

        sortBy.add(SortMeta.builder()
                .field("category")
                .order(SortOrder.ASCENDING)
                .priority(1)
                .build());
    }

    public void refresh(){
        createMixedModel();
        createDataModel();
        PrimeFaces.current().ajax().update(":form:barchart");
    }


    public synchronized void createDataModel() {
        dataModel = new HashMap<>();
        selectedDataList = dateList();
        fehlerCodeAmount = paretoService.maxUsingStreamAndLambda(paretoService.evaluate(selectedDataList));
        //System.out.println(fehlerCodeAmount);
        Map<String, List<Data>> dataMod = paretoService.getDataToErrorCode(selectedDataList);
        //System.out.println(dataMod);
        int i = 0;
        fehlerCodeAmount.forEach((K,V) -> {
            if(dataModel.size()<=10)
                dataModel.put(K, dataMod.get(K));
        });
//        System.out.println(dataModel);

    }

    public List<SortMeta> getSortBy() {
        return sortBy;
    }

    public void createMixedModel() {
        mixedModel = new BarChartModel();
        ChartData data = new ChartData();
        selectedDataList = dateList();

        Map<String, Integer> test = paretoService.maxUsingStreamAndLambda(paretoService.evaluate(selectedDataList));



        BarChartDataSet dataSet = new BarChartDataSet();
        List<Number> values = new ArrayList<>();
        LineChartDataSet dataSet2 = new LineChartDataSet();
        List<Object> values2 = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        List<String> bgColor = new ArrayList<>();
        List<String> borderColor = new ArrayList<>();

        int a = 0;
        int calcTotal = 0;
        for (Map.Entry<String, Integer> entry : test.entrySet()) {
            if (a <= 10){
                calcTotal += entry.getValue();
            }
            a++;
        }
        int calc = 0;
        int i = 0;
        for (Map.Entry<String, Integer> entry : test.entrySet()) {
            if (i <= 10){
                values.add(entry.getValue());
                labels.add((SchadenCode.valueOf(entry.getKey()).label));
                double result1 = entry.getValue() + calc;
                double result2 = result1 / calcTotal;
                int finalresult =(int) (result2 * 100);
                values2.add(finalresult);
                if (finalresult <= 80){
                    bgColor.add("rgba(250, 130, 130, 0.8)");
                    borderColor.add("rgba(255, 99, 132, 0.4)");
                }
                else {
                    bgColor.add("rgba(57, 57, 57, 0.3)");
                    borderColor.add("rgba(0, 0, 0, 0.46)");
                }
            }

            calc += entry.getValue();
            i++;
        }


        dataSet.setData(values);
        dataSet.setLabel("Anzahl Fehler");
        dataSet.setBorderColor(borderColor);
        dataSet.setBackgroundColor(bgColor);

        dataSet2.setData(values2);
        dataSet2.setLineTension(0.01);
        dataSet2.setLabel("Verteilung");
        dataSet2.setFill(false);
        dataSet2.setBorderColor("rgb(54, 162, 235)");
        dataSet2.setYaxisID("right-y-axis");


        data.addChartDataSet(dataSet2);
        data.addChartDataSet(dataSet);
        data.setLabels(labels);

        mixedModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        CartesianLinearAxes linearAxes2 = new CartesianLinearAxes();
        AxesGridLines gridlines = new AxesGridLines();
        linearAxes.setOffset(true);
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        gridlines.setDisplay(false);

        linearAxes2.setId("right-y-axis");
        linearAxes2.setPosition("right");
        linearAxes2.setGridLines(gridlines);


        cScales.addYAxesData(linearAxes);
        cScales.addYAxesData(linearAxes2);
        options.setScales(cScales);
        mixedModel.setOptions(options);
    }

    public LocalDate findDateMax(){
        return dataService.findMaxStoerBeginnDate(dataService.listData());
    }

    public LocalDate findDateMin(){
        return dataService.findMinStoerBeginnDate(dataService.listData());
    }

    public List<LocalDate> getMulti() {
        return multi;
    }

    public void setMulti(List<LocalDate> multi) {
        this.multi = multi;
    }

    public List<Data> dateList(){
        return dataService.findDataByRange(dataService.findMinDate(multi), dataService.findMaxDate(multi));

    }

    public List<Data> getSelectedDataList() {
        return selectedDataList;
    }

    public void setSelectedDataList(List<Data> selectedDataList) {
        this.selectedDataList = selectedDataList;
    }

    public BarChartModel getMixedModel() {
        return mixedModel;
    }

    public void setMixedModel(BarChartModel mixedModel) {
        this.mixedModel = mixedModel;
    }


    public List<Data> getSelectedData() {
        return selectedData;
    }

    public void setSelectedData(List<Data> selectedData) {
        this.selectedData = selectedData;
    }

    public List<Data> getFilteredData() {
        return filteredData;
    }

    public void setFilteredData(List<Data> filteredData) {
        this.filteredData = filteredData;
    }

    public Map<String, Integer> getFehlerCodeAmount() {
        return fehlerCodeAmount;
    }

    public void setFehlerCodeAmount(Map<String, Integer> fehlerCodeAmount) {
        this.fehlerCodeAmount = fehlerCodeAmount;
    }


    public Map<String, List<Data>> getDataModel() {
        return dataModel;
    }

    public void setDataModel(Map<String, List<Data>> dataModel) {
        this.dataModel = dataModel;
    }

//    public SchadenCode[] getSchadcode() {
//        return SchadenCode.values();
//    }

    public SchadenCode getSchadenCode() {
        return schadenCode;
    }

    public String umbenennen(String enumKey){
        return SchadenCode.valueOf(enumKey).label;
    }

    public int countTotalCasesPerCode(List<Data> dataList){
        return dataList.size();
    }

//    public int sortByModel(Object data1, Object data2) {
//        System.out.println(data1 +""+data2);
//        if (data1.size() < data2.size()){
//            return -1;
//        } else if (data1.size() == data2.size()){
//            return 0;
//        } else {
//            return 1;
//       }
//    }
}
