# Leetcode #489. Robot Room Cleaner

## 原题

489 Robot Room Cleaner

Hard

Given a robot cleaner in a room modeled as a grid.

Each cell in the grid can be empty or blocked.

The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.

When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.

Design an algorithm to clean the entire room using only the 4 given APIs shown below.

```java
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
```

**Example:**

```java

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
```

**Explanation:**
All grids in the room are marked by either 0 or 1.
0 means the cell is blocked, while 1 means the cell is accessible.
The robot initially starts at the position of row=1, col=3.
From the top left corner, its position is one row below and three columns right.

**Notes:**

* The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
* The robot's initial position will always be in an accessible cell.
* The initial direction of the robot will be facing up.
* All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
* Assume all four edges of the grid are all surrounded by wall.

## 解法

dfs + backtracking + visited
## 复杂度

O(M * N)

## 代码

```Java
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

```
