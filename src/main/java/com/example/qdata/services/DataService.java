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

package com.example.qdata.services;

import com.example.qdata.dao.DataDAO;
import com.example.qdata.model.Data;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class DataService implements Serializable {

    @Autowired
    private DataDAO dataDAO;

    public List<Data> listData() {
        return dataDAO.findAll(Sort.by(Sort.Direction.DESC, "stoerBeginn"));
    }

    public boolean compareDataWithSQL(Data data) {
        return !listData().contains(data);
    }

    public Data saveData(Data data){return dataDAO.save(data);}

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public void saveDataSet(List<Data> data) {
        List<Data> database = listData();
            for (Data entity: data) {
                if(database.stream().filter(p->p.equals(entity)).count()<=0){
                    dataDAO.save(entity);
                    }
                }
    }

    public Data findById(long id){
        Optional<Data> opt = dataDAO.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }
        return null;
    }

    public Long size(){
        return dataDAO.count();
    }

    public boolean removeById(long id){
        if(findById(id)!=null){
            dataDAO.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Data> findDataByRange(LocalDate startDate, LocalDate endDate) {
        List<Data> dataList = new ArrayList<>();
        List<Date> dateList = new ArrayList<>();
        List<LocalDate> localDateList = startDate.datesUntil(endDate.plusDays(1)).collect(Collectors.toList());
        for (LocalDate date: localDateList) {
            dateList.add(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        dataList = dataDAO.findAll().stream().filter(e-> dateList.stream()
                        .anyMatch(d -> DateUtils.isSameDay(d, Date.from(e.getStoerBeginn().atStartOfDay(ZoneId.systemDefault()).toInstant()))))
                .collect(Collectors.toList());
        return dataList;
    }

    public LocalDate findMinStoerBeginnDate(List<Data> datalist){
        Data mindate = datalist.stream().min(Comparator.comparing(Data::getStoerBeginn)).get();

        return mindate.getStoerBeginn();
    }

    public LocalDate findMaxStoerBeginnDate(List<Data> datalist){
        Data maxdate = datalist.stream().max(Comparator.comparing(Data::getStoerBeginn)).get();

        return maxdate.getStoerBeginn();
    }

    public LocalDate findMinDate(List<LocalDate> datesList) {
        return datesList.stream().min(Comparator.comparing(LocalDate::toEpochDay)).get();
    }

    public LocalDate findMaxDate(List<LocalDate> datesList) {
        return datesList.stream().max(Comparator.comparing(LocalDate::toEpochDay)).get();
    }

}
