package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.utils.TreeNode;
import com.shifamily.dev.utils.TreeUtils;

import java.util.*;
import com.shifamily.dev.*;

public class Leet2096StepByStepDirectionsFromBinaryTreeNodeAnother extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { TreeUtils.createTreeFromString("[5,1,2,3,null,6,4]"), 3, 6 }).answer("UURL")
                .description("Example 1").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { TreeUtils.createTreeFromString("[2,1]"), 2, 1 }).answer("L")
                .description("Example 2").build());
        return cases;
    }

    @CaseRunner
    public String getDirections(TreeNode root, int startValue, int destValue) {
        return null;

    }
}
