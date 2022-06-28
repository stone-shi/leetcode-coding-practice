package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet489RobotRoomCleaner extends BasicStudy {

    // test driver code, not part of solution
    static class Robot {

        int[][] room;
        int col;
        int row;
        int currentDirection = 0; // up
        int colLen = 0;
        int rowLen;

        int[][] dirToMove = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

        public Robot(int[][] room, int row, int col) {
            this.room = room;
            this.col = col;
            this.row = row;

            rowLen = room.length;
            if (rowLen > 0)
                colLen = room[0].length;

        }

        // Returns true if the cell in front is open and robot moves into the cell.
        // Returns false if the cell in front is blocked and robot stays in the current
        // cell.
        public boolean move() {
            int colTo = col, rowTo = row;

            rowTo += dirToMove[currentDirection][0];
            colTo += dirToMove[currentDirection][1];

            if (colTo >= colLen || colTo < 0 || rowTo >= rowLen || rowTo < 0 || room[rowTo][colTo] == 0)
                return false;

            this.col = colTo;
            this.row = rowTo;
            return true;
        }

        // Robot will stay in the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        public void turnLeft() {
            currentDirection--;
            if (currentDirection < 0)
                currentDirection = 3;
        }

        public void turnRight() {
            currentDirection++;
            currentDirection = currentDirection % 4;
        }

        // Clean the current cell.
        public void clean() {
            room[row][col] = 2;
        }
    }

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder().parameters(new Object[] { new int[][] {
                { 1, 1, 1, 1, 1, 0, 1, 1 },
                { 1, 1, 1, 1, 1, 0, 1, 1 },
                { 1, 0, 1, 1, 1, 1, 1, 1 },
                { 0, 0, 0, 1, 0, 0, 0, 0 },
                { 1, 1, 1, 1, 1, 1, 1, 1 } }, 1, 3 }).answer(
                        new int[][] {
                                { 2, 2, 2, 2, 2, 0, 2, 2 },
                                { 2, 2, 2, 2, 2, 0, 2, 2 },
                                { 2, 0, 2, 2, 2, 2, 2, 2 },
                                { 0, 0, 0, 2, 0, 0, 0, 0 },
                                { 2, 2, 2, 2, 2, 2, 2, 2 } })
                .description("case a").build());
        return cases;
    }

    // second try - 2022/06/27
    @CaseRunner
    public int[][] runnerWrapper2(int[][] room, int row, int col) {
        Robot robot = new Robot(room, row, col);
        cleanRoom2(robot);
        return room;
    }

    private void cleanRoom2(Robot r) {
        helper(r, 0, 0, 0, new HashSet<>());
    }

    int[][] dir = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    private void helper(Robot r, int i, int j, int d, Set<String> visited) {
        // d: 0 - up, 1 - right, 2 - down, 3 - left
        r.clean();
        visited.add(i + "," + j);

        for (int k = 0; k < 4; k++) {
            int nextI = i + dir[d][0];
            int nextJ = j + dir[d][1];
            if (!visited.contains(nextI + "," + nextJ) && r.move()) {
                helper(r, nextI, nextJ, d, visited);
                r.turnRight();
                r.turnRight();
                r.move();
                r.turnRight();
                r.turnRight();
            }
            d++;
            d %= 4;
            r.turnRight();
        }

    }

    @CaseRunner
    public int[][] runnerWrapper(int[][] room, int row, int col) {
        Robot robot = new Robot(room, row, col);
        cleanRoom(robot);
        return room;
    }

    private void cleanRoom(Robot r) {
        dfs(r, 0, 0, 0, new HashSet<>());
    }

    private void dfs(Robot robot, int i, int j, int d, Set<String> visited) {
        robot.clean();
        visited.add(i + "," + j);
        int[][] move = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

        for (int k = 0; k < 4; k++) {
            int next_i = i + move[d][0];
            int next_j = j + move[d][1];
            if (!visited.contains(next_i + "," + next_j) && robot.move()) {
                dfs(robot, next_i, next_j, d, visited);
                // back track, move robot back to location
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }
            d++;
            d %= 4;
            robot.turnRight();
        }
    }

}
