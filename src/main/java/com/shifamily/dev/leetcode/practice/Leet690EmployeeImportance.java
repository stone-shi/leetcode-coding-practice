package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import java.util.*;

import com.shifamily.dev.CaseData;
import com.shifamily.dev.CaseParameters;
import com.shifamily.dev.CaseRunner;

/*
690 Employee Importance
Medium

1607

1236

Add to List

Share
You have a data structure of employee information, including the employee's unique ID, importance value, and direct subordinates' IDs.

You are given an array of employees employees where:

employees[i].id is the ID of the ith employee.
employees[i].importance is the importance value of the ith employee.
employees[i].subordinates is a list of the IDs of the direct subordinates of the ith employee.
Given an integer id that represents an employee's ID, return the total importance value of this employee and all their direct and indirect subordinates.

 

Example 1:
![Example 1](https://assets.leetcode.com/uploads/2021/05/31/emp1-tree.jpg)

Input: employees = [[1,5,[2,3]],[2,3,[]],[3,3,[]]], id = 1
Output: 11
Explanation: Employee 1 has an importance value of 5 and has two direct subordinates: employee 2 and employee 3.
They both have an importance value of 3.
Thus, the total importance value of employee 1 is 5 + 3 + 3 = 11.
Example 2:
![Example 2](https://assets.leetcode.com/uploads/2021/05/31/emp2-tree.jpg)

Input: employees = [[1,2,[5]],[5,-3,[]]], id = 5
Output: -3
Explanation: Employee 5 has an importance value of -3 and has no direct subordinates.
Thus, the total importance value of employee 5 is -3.
 

Constraints:

1 <= employees.length <= 2000
1 <= employees[i].id <= 2000
All employees[i].id are unique.
-100 <= employees[i].importance <= 100
One employee has at most one direct leader and may have several subordinates.
The IDs in employees[i].subordinates are valid IDs.
Accepted
174,341
Submissions
271,741
*/

public class Leet690EmployeeImportance extends BasicStudy {
    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();

        cases.add(CaseParameters.builder()
                .parameters(new Object[] { createEmployee("[[1,5,[2,3]],[2,3,[]],[3,3,[]]]"), 1 }).answer(11)
                .description("case a").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { createEmployee("[[1,2,[5]],[5,-3,[]]]"), 5 }).answer(-3)
                .description("case b").build());

        return cases;
    }

    private List<Employee> createEmployee(String s) {
        s = s.replace(" ", "");
        s = s.substring(1, s.length() - 1);
        List<Employee> res = new LinkedList<>();
        String[] es = s.split("]]");
        for (String e : es) {
            if (e.charAt(0) == ',')
                e = e.substring(1);
            String[] a = e.split(",");
            int id = Integer.parseInt(a[0].replace("[", ""));
            int importance = Integer.parseInt(a[1]);
            List<Integer> subordinates = new LinkedList<>();
            for (int i = 2; i < a.length; i++) {
                String n = a[i].replace("[", "");
                if (n.isEmpty())
                    continue;
                subordinates.add(Integer.parseInt(n));
            }
            res.add(new Employee(id, importance, subordinates));
        }
        return res;
    }

    static class Employee {
        Employee(int id, int importance, List<Integer> subordinates) {
            this.id = id;
            this.importance = importance;
            this.subordinates = subordinates;
        }

        public int id;
        public int importance;
        public List<Integer> subordinates;
    };

    @CaseRunner
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, List<Integer>> mapEmployees = new HashMap<>();
        Map<Integer, Integer> mapImportance = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        for (Employee employee : employees) {
            List<Integer> sub = mapEmployees.getOrDefault(employee.id, new LinkedList<>());
            sub.addAll(employee.subordinates);
            mapEmployees.put(employee.id, sub);
            mapImportance.put(employee.id, employee.importance);
        }
        Deque<Integer> q = new LinkedList<>();
        q.add(id);
        int res = 0;
        while (!q.isEmpty()){
            int c = q.poll();
            if (visited.contains(c))
                continue;
            res += mapImportance.get(c);
            visited.add(c);
            q.addAll(mapEmployees.get(c));
        }

        return res;
    }

}
