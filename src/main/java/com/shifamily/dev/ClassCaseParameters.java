package com.shifamily.dev;

import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter   
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClassCaseParameters {
    private String[] operations;
    private Object[][] operationParameters;
    private Object[] answer;
    @Builder.Default
    private String description = "Case"; 
    private Comparator answersComparator;
    @Builder.Default
    private Boolean answersOrderMatter = true;
}
