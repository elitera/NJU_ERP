// -*- Java -*-
/*
 * <copyright>
 * 
 *  Copyright (c) 2002
 *  Institute for Information Processing and Computer Supported New Media (IICM),
 *  Graz University of Technology, Austria.
 * 
 * </copyright>
 * 
 * <file>
 * 
 *  Name:    KWIC.java
 * 
 *  Purpose: Demo system for practice in Software Architecture
 * 
 *  Created: 11 Sep 2002 
 * 
 *  $Id$
 * 
 *  Description:
 *    The basic KWIC system is defined as follows. The KWIC system accepts an ordered 
 *  set of lines, each line is an ordered set of words, and each word is an ordered set
 *  of characters. Any line may be "circularly shifted" by repeadetly removing the first
 *  word and appending it at the end of the line. The KWIC index system outputs a
 *  listing of all circular shifts of all lines in alphabetical order.
 * </file>
*/

//package kwic.ms;

/*
 * $Log$
*/

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.ArrayList;

/**
 *  This class is an implementation of the main/subroutine architectural solution
 *  for the KWIC system. This solution is based on functional
 *  decomposition of the system. Thus, the system is decomposed into a number of
 *  modules, each module being a function. In this solution all functions share access
 *  to data, which is stored in the "core storage". The system is decomposed into the
 *  following modules (functions):
 *  <ul>
 *  <li>Master Control (main). This function controls the sequencing among the
 *  other four functions.
 *  <li>Input. This function reads the data lines from the input medium (file) and
 *  stores them in the core for processing by the remaining modules. The characters are
 *  stored in a character array (char[]). The blank space is used to separate words in
 *  a particular line. Another integer array (int[]) keeps the starting indices of
 *  each line in the character array.
 *  <li>Circular Shift. This function is called after the input function has
 *  completed its work. It prepares a two-dimensional integer array (int[][]) to keep
 *  track of all circular shifts. The array is organized as follows: for each circular
 *  shift, both index of its original line, together with the index of the starting word of
 *  that circular shift are stored as one column of the array.
 *  <li>Alphabetizing. This function takes as input the arrays produced by the input
 *  and circular shift functions. It produces an array in the same format (int[][])
 *  as that produced by circular shift function. In this case, however, the circular
 *  shifts are listed in another order (they are sorted alphabetically).
 *  <li>Output. This function uses the arrays produced by input and alphabetizing
 *  function. It produces a nicely formated output listing of all circular shifts.
 *  </ul>
 *  @author  dhelic
 *  @version $Id$
*/

//此类是 KWIC 系统的主/子程序架构解决方案的实现。该解决方案基于系统的功能分解。
// 因此，系统被分解为多个模块，每个模块都是一个功能。在此解决方案中，所有功能共享对存储在“核心存储”中的数据的访问。系统分解为以下模块（功能）：
//
//主控（主）。此功能控制其他四个功能之间的顺序。
//
//输入。该函数从输入介质（文件）中读取数据线，并将它们存储在核心中，以供其余模块处理。
//字符存储在字符数组 (char[]) 中。空格用于分隔特定行中的单词。另一个整数数组 (int[]) 保存字符数组中每一行的起始索引。
//
//循环移位。在输入函数完成其工作后调用此函数。它准备一个二维整数数组 (int[][]) 来跟踪所有循环移位。
//该数组的组织如下：对于每个循环移位，其原始行的索引以及该循环移位的起始字的索引都存储为数组的一列。
//
//按字母顺序排列。此函数将输入和循环移位函数生成的数组作为输入。它产生一个与循环移位函数产生的格式相同的数组（int[][]）。
//但是，在这种情况下，循环移位以另一种顺序列出（它们按字母顺序排序）。
//
//输出。此函数使用输入和字母排序函数生成的数组。它会生成格式良好的所有循环移位的输出列表。

public class KWIC{

//----------------------------------------------------------------------
/**
 * Fields
 *
 */
//----------------------------------------------------------------------

/*
 * Core storage for shared data
 *
 */

/**
 * Input characters
 *
 */

  private char[] chars_;

/**
 * Array that keeps line indices (line index is the index of the first character of a line)
 *
 */

  private int[] line_index_;

/**
 * 2D array that keeps circular shift indices (each circular shift index is a column
 * in this 2D array, the first index is the index of the original line from line_index_ 
 * array, the second index is the index of the starting word from chars_ array of 
 * that circular shift)
 *
 */

  private int[][] circular_shifts_;

/**
 * 2D array that keeps circular shift indices, sorted alphabetically
 *
 */

  private int[][] alphabetized_;

  private LinkedList<String> input = new LinkedList<>();
  private LinkedList<String> result = new LinkedList<>();


//----------------------------------------------------------------------
/**
 * Constructors
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * Methods
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * Input function reads the raw data from the specified file and stores it in the core storage.
 * If some system I/O error occurs the program exits with an error message.
 * The format of raw data is as follows. Lines are separated by the line separator
 * character(s) (on Unix '\n', on Windows '\r\n'). Each line consists of a number of
 * words. Words are delimited by any number and combination of the space chracter (' ')
 * and the horizontal tabulation chracter ('\t'). The entered data is parsed in the 
 * following way. All line separators are removed from the data, all horizontal tabulation
 * word delimiters are replaced by a single space character, and all multiple word
 * delimiters are replaced by a single space character. Then the parsed data is represented 
 * in the core as two arrays: chars_ array and line_index_ array.
 * @param file Name of input file
 *
 * 输入函数从指定文件中读取原始数据并将其存储在核心存储中。
 * 如果发生某些系统 I/O 错误，程序将退出并显示错误消息。
 * 原始数据的格式如下。行由行分隔符分隔（在 Unix 上为“\n”，在 Windows 上为“\r\n”）。
 * 每行由多个单词组成。 单词由空格字符 (' ') 和水平制表字符 ('\t') 的任意数量和组合分隔。输入的数据按以下方式解析。
 * 从数据中删除所有行分隔符，将所有水平制表单词分隔符替换为单个空格字符，并且将所有多个单词分隔符替换为单个空格字符。
 * 然后解析后的数据在核心中表示为两个数组：chars_array 和 line_index_array。
 */


  public void input(String file){
    try {
      FileReader fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr);
      String line;
      while ((line = br.readLine()) != null){
        input.add(line);
      }
      br.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

//----------------------------------------------------------------------
/**
 * This function processes arrays prepared by the input
 * function and produces circular shifts of the stored lines. A circular
 * shift is a line where the first word is removed from the begin of a line
 * and appended at the end of the line. To obtain all circular shifts of a line
 * we repeat this process until we can't obtain any new lines. Circular shifts 
 * are represented as a 2D array that keeps circular shift indices (each circular 
 * shift index is a column in this 2D array, the first index is the index of 
 * the original line from line_index_ array, the second index is the index of 
 * the starting word from chars_ array of that circular shift)
 *
 * 该函数处理由输入函数准备的数组并产生存储行的循环移位。循环移位是从行首删除第一个单词并附加到行尾的行。
 * 为了获得一条线的所有循环移位，我们重复这个过程，直到我们不能获得任何新的线。
 * 循环移位表示为保持循环移位索引的二维数组（每个循环移位索引是此二维数组中的一列，
 * 第一个索引是 line_index_ 数组中原始行的索引，第二个索引是起始词的索引 来自该循环移位的 chars_ 数组）
 */

  public void circularShift(){
    for (String line: input){
      String[] words = line.split("[\\s]+");
      String[] tag = words.clone();
      int count = 0;
      while (count < words.length){
        String s = tag[0];
        System.arraycopy(tag, 1, tag, 0, words.length - 1);
        tag[words.length - 1] = s;
        count++;
        result.add(String.join(" ", tag));
      }
    }
  }

//----------------------------------------------------------------------
/**
 * This function sorts circular shifts lines alphabetically. The sorted shifts
 * are represented in the same way as the unsorted shifts with the only difference
 * that now they are ordered alphabetically. This function implements binary search
 * to sort the shifts.
 *
 * 此函数按字母顺序对循环移位线进行排序。 已排序的班次以与未排序的班次相同的方式表示，
 * 唯一的区别是现在它们按字母顺序排列。 此函数实现二进制搜索以对班次进行排序。
 */

  public void alphabetizing(){
    Collections.sort(result);
  }

//----------------------------------------------------------------------
/**
 * This function prints the sorted shifts at the standard output.
 */

  public void output(){
    for (String res:
         result) {
      System.out.println(res);
    }
  }

//----------------------------------------------------------------------
/**
 * This function controls all other functions in the system. It implements
 * the sequence of calls to other functions to obtain the desired functionality
 * of the system. Before any other function is called, main function checks the 
 * command line arguments. The program expects exactly one command line argument
 * specifying the name of the file that contains the data. If the program have
 * not been started with proper command line arguments, main function exits
 * with an error message. Otherwise, the input function is called first to read the 
 * data from the file. After that the circularShift and alphabetizing 
 * functions are called to produce and sort the shifts respectivelly. Finally, the output
 * function prints the sorted shifts at the standard output.
 * @param args command line argumnets
 */

  public static void main(String[] args){
    KWIC kwic = new KWIC();
    kwic.input("Test_Case.txt");
    kwic.circularShift();
    kwic.alphabetizing();
    kwic.output();
  }

//----------------------------------------------------------------------
/**
 * Inner classes
 *
 */
//----------------------------------------------------------------------

}
