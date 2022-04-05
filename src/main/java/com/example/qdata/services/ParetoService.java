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

//    public <K, V extends Comparable<V>> Map<K, V> maxToMin(Map<String, List<Data>> map) {
//
//        List<Map.Entry<K,V>> sortedEntries = new ArrayList<Map.Entry<K,V>>();
//
//        Collections.sort(sortedEntries,
//                new Comparator<Map.Entry<K,V>>() {
//                    @Override
//                    public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
//                        return e2.getValue().compareTo(e1.getValue());
//                    }
//                }
//        );
//        return sortedEntries;

//        Map<K,V> sortedEntry = map.entrySet()
//                .stream()
//                .sorted()
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(oldValue, newValue) -> oldValue, LinkedHashMap::new));
//        return sortedEntry;
//    }

    public <K, V extends Comparable<V>> List<V> biggestValues(Map<K, V> map) {
        List<V> valuesSorted = new ArrayList<V>(map.values());
        Collections.sort(valuesSorted, Collections.reverseOrder());

        return valuesSorted;
    }



}
