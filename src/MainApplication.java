import exercise.Calendar;
import exercise.Employ;
import exercise.Telecom;
import exercise.Triangle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainApplication {
  public static void main(String[] args) throws IOException, InterruptedException {
    System.out.println("-------------------请输入序号完成测试------------------");
    System.out.println("---------------------1.第二道题目---------------------");
    System.out.println("---------------------2.第三道题目---------------------");
    System.out.println("---------------------3.第六道题目---------------------");
    System.out.println("---------------------4.第七道题目---------------------");
    System.out.println("-------------------输入其他则会退出-------------------");
    boolean flag = true;
    char select;
    String input;
    while (flag) {
      System.out.print("请输入你的选择：");
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      input = br.readLine();
      if (input.length() == 0) {
        continue;
      }
      select = input.charAt(0);
      switch (select) {
        case '1': {
          Triangle.exec();
          Calendar.exec("2.2");
          System.out.println("测试结束");
          break;
        }
        case '2': {
          Employ.exec();
          System.out.println("测试结束");
          break;
        }
        case '3': {
          Telecom.exec();
          System.out.println("测试结束");
          break;
        }
        case '4': {
          Calendar.exec("7");
          System.out.println("测试结束");
          break;
        }
        default: {
          System.out.println("即将退出");
          Thread.sleep(2000);
          flag = false;
        }
      }
    }
  }
}
