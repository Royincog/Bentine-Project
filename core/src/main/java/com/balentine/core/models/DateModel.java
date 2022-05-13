package com.balentine.core.models;

import lombok.extern.slf4j.Slf4j;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.joda.time.LocalDate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class DateModel {
    private int days = Days.daysBetween(LocalDate.parse("2022-02-02"),LocalDate.parse("3022-02-02")).getDays();

    private List<LocalDate> getListOfDates() {
        List<LocalDate> dates = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            LocalDate d = LocalDate.parse("2022-02-02").withFieldAdded(DurationFieldType.days(), i);
            dates.add(d);
        }
        return dates;
    }

    @PostConstruct
    private void afterConstruct(){
        log.info("The palindrome dates " );
    }

    private boolean isPalindrome(String num){
        boolean isPalindromeNum = false;
        String cleanNum = num.replace("-","");
        if(new StringBuilder(cleanNum).reverse().toString().equals(cleanNum)){
            isPalindromeNum = true;
        }
        return isPalindromeNum;
    }
    public List<LocalDate> getPalindromeDates(){
        List<LocalDate> dates = getListOfDates();
        List<LocalDate> palindromeDates = dates.stream().filter(date -> isPalindrome(date.toString())).collect(Collectors.toList());
        return palindromeDates;
    }

    }

