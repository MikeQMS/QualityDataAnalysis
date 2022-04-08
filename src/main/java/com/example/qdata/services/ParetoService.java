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

import com.example.qdata.model.Data;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParetoService {


    public HashMap<String, Integer> evaluate(List<Data> selectedData){
        HashMap< String, Integer> countMap = new HashMap<>();

        for (Data data : selectedData){
            if (countMap.containsKey(data.getScd())){
                countMap.put(data.getScd(), countMap.get(data.getScd()) + 1);
            }
            else {
                countMap.put(data.getScd(), 1);
            }
        }
        return countMap;
    }

    public HashMap<String, List<Data>> getDataToErrorCode(List<Data> selectedData){
        HashMap< String, List<Data>> countMap = new HashMap<>();
        for (Data data : selectedData){
            if (countMap.containsKey(data.getScd())){
                countMap.computeIfAbsent(data.getScd(), k -> new ArrayList<>()).add(data);
            }
            else {
                countMap.computeIfAbsent(data.getScd(), k -> new ArrayList<>()).add(data);
            }
        }

        return countMap;
    }

    public <K, V extends Comparable<V>> Map<K, V> maxUsingStreamAndLambda(Map<K, V> map) {
        Map<K,V> sortedEntry = map.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return sortedEntry;
    }

    public <K, V extends Comparable<V>> List<V> biggestValues(Map<K, V> map) {
        List<V> valuesSorted = new ArrayList<V>(map.values());
        Collections.sort(valuesSorted, Collections.reverseOrder());

        return valuesSorted;
    }



}
