# KWIC-MS #

KWIC主程序与子程序风格

1. 成员变量说明
```java
/**
 * Input characters
 * 输入字符
 */

  private char[] chars_;

/**
 * Array that keeps line indices (line index is the index of the first character of a line)
 * 保存行索引的数组（行索引是行的第一个字符的索引）
 */

  private int[] line_index_;

/**
 * 2D array that keeps circular shift indices (each circular shift index is a column
 * in this 2D array, the first index is the index of the original line from line_index_ 
 * array, the second index is the index of the starting word from chars_ array of 
 * that circular shift)
 * 保持循环移位索引的二维数组
 * （每个循环移位索引是此二维数组中的一列，
 * 第一个索引是 line_index_ 数组中原始行的索引，
 * 第二个索引是该循环的 chars_ 数组中起始单词的索引 转移）
 */

  private int[][] circular_shifts_;

/**
 * 2D array that keeps circular shift indices, sorted alphabetically
 * 保持循环移位索引的二维数组，按字母顺序排序
 */

  private int[][] alphabetized_;
```
2. 作业要求
   + 实现4个方法。    
        - kwic.input("Test_Case.txt");
        - kwic.circularShift();
        - kwic.alphabetizing();
        - kwic.output();
   + 思考如果涉及潜在的变更，需要设计修改哪些的方法
        - 存储的形式发生更改（比如不采用index方式，而是记录完整字符串）
        - 循环位移算法发生更改
   
   