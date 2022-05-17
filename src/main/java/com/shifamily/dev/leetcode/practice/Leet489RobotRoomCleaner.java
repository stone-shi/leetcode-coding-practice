package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.HashSet;
import java.util.Set;

/*
489. Robot Room Cleaner
Hard

520

29

Favorite

Share
Given a robot cleaner in a room modeled as a grid.

Each cell in the grid can be empty or blocked.

The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.

When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.

Design an algorithm to clean the entire room using only the 4 given APIs shown below.

interface Robot {
  // returns true if next cell is open and robot moves into the cell.
  // returns false if next cell is obstacle and robot stays on the current cell.
  boolean move();

  // Robot will stay on the same cell after calling turnLeft/turnRight.
  // Each turn will be 90 degrees.
  void turnLeft();
  void turnRight();

  // Clean the current cell.
  void clean();
}
Example:

Input:
room = [
  [1,1,1,1,1,0,1,1],
  [1,1,1,1,1,0,1,1],
  [1,0,1,1,1,1,1,1],
  [0,0,0,1,0,0,0,0],
  [1,1,1,1,1,1,1,1]
],
row = 1,
col = 3

Explanation:
All grids in the room are marked by either 0 or 1.
0 means the cell is blocked, while 1 means the cell is accessible.
The robot initially starts at the position of row=1, col=3.
From the top left corner, its position is one row below and three columns right.
Notes:

The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
The robot's initial position will always be in an accessible cell.
The initial direction of the robot will be facing up.
All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
Assume all four edges of the grid are all surrounded by wall.

 */
public class Leet489RobotRoomCleaner extends BasicStudy {

    //test code
    static class Robot {

        int[][] room;
        int col;
        int row;
        int currentDirection = 0;
        int colLen = 0;
        int rowLen;

        int[][] dirToMove = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        int[][] getRoom() {
            return room;
        }

        public Robot(int[][] room, int row, int col) {
            this.room = room;
            this.col = col;
            this.row = row;

            rowLen = room.length;
            if (rowLen > 0)
                colLen = room[0].length;

        }

        // Returns true if the cell in front is open and robot moves into the cell.
        // Returns false if the cell in front is blocked and robot stays in the current cell.
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
            System.out.println("Cleaned " + row + "-" + col);

        }
    }


    public Leet489RobotRoomCleaner() {
        int[][][] casesP1 = {
                {
                        {1, 1, 1, 1, 1, 0, 1, 1},
                        {1, 1, 1, 1, 1, 0, 1, 1},
                        {1, 0, 1, 1, 1, 1, 1, 1},
                        {0, 0, 0, 1, 0, 0, 0, 0},
                        {1, 1, 1, 1, 1, 1, 1, 1}
                }
        };

        int[] casesP2 = {1};
        int[] casesP3 = {3};


        int[][][] answers = {
                {
                        {2, 2, 2, 2, 2, 0, 2, 2},
                        {2, 2, 2, 2, 2, 0, 2, 2},
                        {2, 0, 2, 2, 2, 2, 2, 2},
                        {0, 0, 0, 2, 0, 0, 0, 0},
                        {2, 2, 2, 2, 2, 2, 2, 2}
                }
        };

        for (int i = 0; i < casesP1.length; i++) {
            Object[] p = new Object[3];
            p[0] = casesP1[i];
            p[1] = casesP2[i];
            p[2] = casesP3[i];
            addParameterAndAnswer(p, answers[i], true);
        }


    }

    @CaseRunner
    public int[][] runnerWrapper(int[][] room, int row, int col) {

        int[][] roomClone = new int[room.length][];
        for (int i = 0; i < room.length; i++)
            roomClone[i] = room[i].clone();

        Robot robot = new Robot(roomClone, row, col);
        cleanRoom(robot);
        return roomClone;
    }

    public void helper(Robot robot, Set<String> visited,  int relativeRow, int relativeCol, int dir) {

        int[][] dirToMove = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        String cord = relativeRow + "," + relativeCol;
        visited.add(cord);
        robot.clean();
        //System.out.println("Clean " + cord);

        for (int i = 0; i < 4; i++) {
            int dirTo = (dir + i) % 4;
            int rowTo = relativeRow + dirToMove[dirTo][0] ;
            int colTo = relativeCol + dirToMove[dirTo][1];

            String cordTo = rowTo + "," + colTo;
            if (visited.contains(cordTo))
                continue;

            visited.add(cordTo);
            //System.out.println("Visiting... " + cordTo);
            if (robot.move()) {
                helper(robot, visited, rowTo, colTo, dirTo);
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnLeft();
                robot.turnLeft();
            }
            robot.turnRight();
        }
    }

    public void cleanRoom(Robot robot) {
        HashSet<String> visited = new HashSet<>();
        helper(robot, visited, 0, 0, 0);
    }
}
