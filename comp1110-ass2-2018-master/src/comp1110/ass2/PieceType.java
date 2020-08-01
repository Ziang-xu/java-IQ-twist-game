package comp1110.ass2;

public enum PieceType {

   //meaning of number:
   //0: nothing, 1: solid, -1: loop
   //" " means nothing
   // - by Yang Kong(u6275465)
   a0(new int[][]{{-1,1,-1},{0,0,1}}, new String[][]{{"red", "red", "red"}, {" ", " ", "red"}}),
   a1(new int[][]{{0,-1},{0,1},{1,-1}}, new String[][]{{" ", "red"}, {" ", "red"}, {"red", "red"}}),
   a2(new int[][]{{1,0,0},{-1,1,-1}}, new String[][]{{"red", " ", " "}, {"red", "red", "red"}}),
   a3(new int[][]{{-1,1},{1,0},{-1,0}}, new String[][]{{"red", "red"}, {"red", " "}, {"red", " "}}),
   a4(new int[][]{{0,0,1},{-1,1,-1}}, new String[][]{{" ", " ", "red"}, {"red", "red", "red"}}),
   a5(new int[][]{{-1,0},{1,0},{-1,1}}, new String[][]{{"red", " "}, {"red", " "}, {"red", "red"}}),
   a6(new int[][]{{-1,1,-1},{1,0,0}}, new String[][]{{"red", "red", "red"}, {"red", " ", " "}}),
   a7(new int[][]{{1,-1},{0,1},{0,-1}}, new String[][]{{"red", "red"}, {" ", "red"}, {" ", "red"}}),

   b0(new int[][]{{1,1,0},{0,-1,1}}, new String[][]{{"red", "red", " "}, {" ", "red", "red"}}),
   b1(new int[][]{{0,1},{-1,1},{1,0}}, new String[][]{{" ", "red"}, {"red", "red"}, {"red", " "}}),
   b2(new int[][]{{1,-1,0},{0,1,1}}, new String[][]{{"red", "red", " "}, {" ", "red", "red"}}),
   b3(new int[][]{{0,1},{1,-1},{1,0}}, new String[][]{{" ", "red"}, {"red", "red"}, {"red", " "}}),
   b4(new int[][]{{0,-1,1},{1,1,0}}, new String[][]{{" ", "red", "red"}, {"red", "red", " "}}),
   b5(new int[][]{{1,0},{1,-1},{0,1}}, new String[][]{{"red", " "}, {"red", "red"}, {" ", "red"}}),
   b6(new int[][]{{0,1,1},{1,-1,0}}, new String[][]{{" ", "red", "red"}, {"red", "red", " "}}),
   b7(new int[][]{{1,0},{-1,1},{0,1}}, new String[][]{{"red", " "}, {"red", "red"}, {" ", "red"}}),

   c0(new int[][]{{1,-1,1,1}}, new String[][]{{"blue", "blue", "blue", "blue"}}),
   c1(new int[][]{{1},{-1},{1},{1}}, new String[][]{{"blue"}, {"blue"}, {"blue"}, {"blue"}}),
   c2(new int[][]{{1,1,-1,1}}, new String[][]{{"blue", "blue", "blue", "blue"}}),
   c3(new int[][]{{1},{1},{-1},{1}}, new String[][]{{"blue"}, {"blue"}, {"blue"}, {"blue"}}),
   c4(new int[][]{{1,-1,1,1}}, new String[][]{{"blue", "blue", "blue", "blue"}}),
   c5(new int[][]{{1},{-1},{1},{1}}, new String[][]{{"blue"}, {"blue"}, {"blue"}, {"blue"}}),
   c6(new int[][]{{1,1,-1,1}}, new String[][]{{"blue", "blue", "blue", "blue"}}),
   c7(new int[][]{{1},{1},{-1},{1}}, new String[][]{{"blue"}, {"blue"}, {"blue"}, {"blue"}}),

   d0(new int[][]{{1,1,1},{0,-1,-1}}, new String[][]{{"blue", "blue", "blue"}, {" ", "blue", "blue"}}),
   d1(new int[][]{{0,1},{-1,1},{-1,1}}, new String[][]{{" ", "blue"}, {"blue", "blue"}, {"blue", "blue"}}),
   d2(new int[][]{{-1,-1,0},{1,1,1}}, new String[][]{{"blue", "blue", " "}, {"blue", "blue", "blue"}}),
   d3(new int[][]{{1,-1},{1,-1},{1,0}}, new String[][]{{"blue", "blue"}, {"blue", "blue"}, {"blue", " "}}),
   d4(new int[][]{{0,-1,-1},{1,1,1}}, new String[][]{{" ", "blue", "blue"}, {"blue", "blue", "blue"}}),
   d5(new int[][]{{1,0},{1,-1},{1,-1}}, new String[][]{{"blue", " "}, {"blue", "blue"}, {"blue", "blue"}}),
   d6(new int[][]{{1,1,1},{-1,-1,0}}, new String[][]{{"blue", "blue", "blue"}, {"blue", "blue", " "}}),
   d7(new int[][]{{-1,1},{-1,1},{0,1}}, new String[][]{{"blue", "blue"}, {"blue", "blue"}, {" ", "blue"}}),

   e0(new int[][]{{1,-1},{0,-1}}, new String[][]{{"green", "green"}, {" ", "green"}}),
   e1(new int[][]{{0,1},{-1,-1}}, new String[][]{{" ", "green"}, {"green", "green"}}),
   e2(new int[][]{{-1,0},{-1,1}}, new String[][]{{"green", " "}, {"green", "green"}}),
   e3(new int[][]{{-1,-1},{1,0}}, new String[][]{{"green", "green"}, {"green", " "}}),
   e4(new int[][]{{0,-1},{1,-1}}, new String[][]{{" ", "green"}, {"green", "green"}}),
   e5(new int[][]{{1,0},{-1,-1}}, new String[][]{{"green", " "}, {"green", "green"}}),
   e6(new int[][]{{-1,1},{-1,0}}, new String[][]{{"green", "green"}, {"green", " "}}),
   e7(new int[][]{{-1,-1},{0,1}}, new String[][]{{"green", "green"}, {" ", "green"}}),

   f0(new int[][]{{1,1,-1},{0,-1,0}}, new String[][]{{"green", "green", "green"}, {" ", "green", " "}}),
   f1(new int[][]{{0,1},{-1,1},{0,-1}}, new String[][]{{" ", "green"}, {"green", "green"}, {" ", "green"}}),
   f2(new int[][]{{0,-1,0},{-1,1,1}}, new String[][]{{" ", "green", " "}, {"green", "green", "green"}}),
   f3(new int[][]{{-1,0},{1,-1},{1,0}}, new String[][]{{"green", " "}, {"green", "green"}, {"green", " "}}),
   f4(new int[][]{{0,-1,0},{1,1,-1}}, new String[][]{{null, "green", null}, {"green", "green", "green"}}),
   f5(new int[][]{{1,0},{1,-1},{-1,0}}, new String[][]{{"green", " "}, {"green", "green"}, {"green", " "}}),
   f6(new int[][]{{-1,1,1},{0,-1,0}}, new String[][]{{"green", "green", "green"}, {" ", "green", " "}}),
   f7(new int[][]{{0,-1},{-1,1},{0,1}}, new String[][]{{" ", "green"}, {"green", "green"}, {" ", "green"}}),

   g0(new int[][]{{-1,0,0},{-1,1,1},{0,-1,0}}, new String[][]{{"yellow", " ", " "}, {"yellow", "yellow", "yellow"}, {" ", "yellow", " "}}),
   g1(new int[][]{{0,-1,-1},{-1,1,0},{0,1,0}}, new String[][]{{" ", "yellow", "yellow"}, {"yellow", "yellow", " "}, {" ", "yellow", " "}}),
   g2(new int[][]{{0,-1,0},{1,1,-1},{0,0,-1}}, new String[][]{{" ", "yellow", " "}, {"yellow", "yellow", "yellow"}, {" ", " ", "yellow"}}),
   g3(new int[][]{{0,1,0},{0,1,-1},{-1,-1,0}}, new String[][]{{" ", "yellow", " "}, {" ", "yellow", "yellow"}, {"yellow", "yellow", " "}}),
   g4(new int[][]{{0,-1,0},{-1,1,1},{-1,0,0}}, new String[][]{{" ", "yellow", " "}, {"yellow", "yellow", "yellow"}, {"yellow", " ", " "}}),
   g5(new int[][]{{-1,-1,0},{0,1,-1},{0,1,0}}, new String[][]{{"yellow", "yellow", " "}, {" ", "yellow", "yellow"}, {" ", "yellow", " "}}),
   g6(new int[][]{{0,0,-1},{1,1,-1},{0,-1,0}}, new String[][]{{" ", " ", "yellow"}, {"yellow", "yellow", "yellow"}, {" ", "yellow", " "}}),
   g7(new int[][]{{0,1,0},{-1,1,0},{0,-1,-1}}, new String[][]{{" ", "yellow", " "}, {"yellow", "yellow", " "}, {" ", "yellow", "yellow"}}),

   h0(new int[][]{{-1,1,1}}, new String[][]{{"yellow", "yellow", "yellow"}}),
   h1(new int[][]{{-1},{1},{1}}, new String[][]{{"yellow"}, {"yellow"}, {"yellow"}}),
   h2(new int[][]{{1,1,-1}}, new String[][]{{"yellow", "yellow", "yellow"}}),
   h3(new int[][]{{1},{1},{-1}}, new String[][]{{"yellow"}, {"yellow"}, {"yellow"}}),
   h4(new int[][]{{-1,1,1}}, new String[][]{{"yellow", "yellow", "yellow"}}),
   h5(new int[][]{{-1},{1},{1}}, new String[][]{{"yellow"}, {"yellow"}, {"yellow"}}),
   h6(new int[][]{{1,1,-1}}, new String[][]{{"yellow", "yellow", "yellow"}}),
   h7(new int[][]{{1},{1},{-1}}, new String[][]{{"yellow"}, {"yellow"}, {"yellow"}});

   private int[][] eachLoopStatus;
   private String[][] color;

   PieceType(int[][] eachLoopStatus, String[][] color){
      this.eachLoopStatus = eachLoopStatus;
      this.color = color;
   }

   public String[][] getColor() {
      return this.color;
   }

   public int[][] getEachLoopStatus() {
      return this.eachLoopStatus;
   }
}
