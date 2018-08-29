package com.foo.stream;

import com.foo.pojo.McDonald;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String... args) throws Exception {

        Stream<String> lines = Files.lines(Paths.get("files", "mcdonalds.csv")) ;
        List<McDonald> mcDonaldList = lines.map(s -> {
            // -149.95038,61.13712,"McDonalds-Anchorage,AK","3828 W Dimond Blvd, Anchorage,AK, (907) 248-0597"
            // -72.84817,41.27988,"McDonalds-Branford,CT","424 W Main St, Branford,CT, (203) 488-9353"
            String [] strings = s.split(",") ;
            McDonald mdo = new McDonald() ;
            mdo.setLatitude(Double.parseDouble(strings[0])) ;
            mdo.setLongitude(Double.parseDouble(strings[1])) ;
            mdo.setName(strings[2].substring(1) + strings[3].substring(0, strings[3].length() - 1)) ;
            mdo.setAddress(strings[4].substring(1)) ;
            mdo.setCity(strings[5].trim()) ;
            mdo.setState(strings[6].trim()) ;
            if (mdo.state().endsWith("\"")) {
                mdo.setState(mdo.state().substring(0, mdo.state().length() - 1)) ;
            }
            if (mdo.state().contains(" ")) {
                mdo.setState(mdo.state().substring(0, mdo.state().indexOf(" "))) ;
            }
            if (mdo.state().length() > 2) {
                mdo.setState(strings[7].trim()) ;
            }
            return mdo ;
        }).collect(Collectors.toList()) ;

        System.out.println("# of McDos = " + mcDonaldList.size()) ;

        // The number of cities that have a McDonald
        long nTowns =
                mcDonaldList.stream()
                        .map(McDonald::city)
                        .collect(Collectors.toSet())
                        .size() ;
        System.out.println("The number of cities that have a McDonald : " + nTowns) ;

        // The city has the most MacDonald
        Set set = mcDonaldList.stream().collect(Collectors.groupingBy(McDonald::city, Collectors.counting())).entrySet();
        //there are TWO approaches to get the city name of the most MacDonald, line51 and line52
        String mostMacDonald = ((Map.Entry<String, Long>)Collections.max(set, Map.Entry.comparingByValue())).getKey();
        Map.Entry<String, Long> entry = (Map.Entry<String, Long>)set.stream().max(Map.Entry.comparingByValue()).get();

        System.out.println("The city has the most MacDonald : " + entry.getKey()) ;

    }
}