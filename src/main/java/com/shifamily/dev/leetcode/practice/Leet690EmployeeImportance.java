package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import java.util.*;

import com.shifamily.dev.CaseData;
import com.shifamily.dev.CaseParameters;
import com.shifamily.dev.CaseRunner;


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

    @CaseRunner
    public int getImportance2(List<Employee> employees, int id) {
        Map<Integer, Integer> mapImportance = new HashMap<>();
        Map<Integer, List<Integer>> mapTree = new HashMap<>();
        for (Employee e : employees){
            mapImportance.put(e.id, e.importance);
            mapTree.put(e.id, e.subordinates);
        }
        if (!mapImportance.containsKey(id))
            return -1;
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(id);
        int res = 0;
        while (!q.isEmpty()){
            int currId = q.poll();
            res += mapImportance.getOrDefault(currId, 0);
            q.addAll(mapTree.getOrDefault(currId, new ArrayList<>()));
        }
        return res;        
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
