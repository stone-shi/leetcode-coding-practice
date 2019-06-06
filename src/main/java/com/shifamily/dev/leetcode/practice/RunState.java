package com.shifamily.dev.leetcode.practice;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RunState {

    private String name;
    private Long runTimeInNs;
    private Long runMemoryInBytes;
    private String result;



    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(": [").append(result).append("]");
        sb.append(String.format(" [%,d] ns", runTimeInNs));
        sb.append(String.format(" [%,d] bytes", runMemoryInBytes));

        return sb.toString();
    }

}
