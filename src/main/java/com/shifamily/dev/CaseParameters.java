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
public class CaseParameters {
    @Builder.Default
    private String description = "Case";
    private Object[] parameters;
    private Object answer;
    private Comparator answersComparator;
    @Builder.Default
    private Boolean answersOrderMatter = false;
    @Builder.Default
    private int answerInPlaceIndex = -1;

}
