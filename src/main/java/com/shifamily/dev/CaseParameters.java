package com.shifamily.dev;

import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter   
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaseParameters {
    private String description;
    private Object[] parameters;
    private Object answer;
    private Comparator answersComparator;
    @Builder.Default
    private Boolean answersOrderMatter = false;
    @Builder.Default
    private int answerInPlaceIndex = -1;
}
