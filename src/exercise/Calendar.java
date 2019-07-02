package exercise;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.*;

public class Calendar {
  private int year;
  private int month;
  private int day;
  private boolean valid = true;

  public boolean isValid() {
    return valid;
  }

  public void setValid(boolean valid) {
    this.valid = valid;
  }

  private int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

  public int getYear() {
    return year;
  }

  private void setYear(int year) {
    if (year < 1900 || year > 2300) {
      this.valid = false;
    }
    this.year = year;
    if (this.year % 400 == 0 || this.year % 100 != 0 && this.year % 4 == 0) {
      this.days[1] = 29;
    } else {
      this.days[1] = 28;
    }
  }

  public int getMonth() {
    return month;
  }

  private void setMonth(int month) {
    if (!this.valid || month < 1 || month > 12) {
      this.valid = false;
    }
    this.month = month;
  }

  public int getDay() {
    return day;
  }

  private void setDay(int day) {
    if (!this.valid || day <= 0 || day > this.days[this.month - 1]) {
      this.valid = false;
    }
    this.day = day;
  }

  private void next() {
    if (this.valid) {
      ++this.day;
      if (this.day > this.days[this.month - 1]) {
        this.day = 1;
        ++this.month;
      }
      if (this.month > 12) {
        this.month = 1;
        ++this.year;
      }
    } else {
      this.day = 0;
      this.month = 0;
      this.year = 0;
    }
    this.valid = true;
  }

  public boolean equal(int year, int month, int day) {
    return this.year == year && this.month == month && this.day == day;
  }

  public static void exec(String id) {
    try {
      System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t开始测试");
      Calendar calendar = new Calendar();
      BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/" + id + ".csv")), "GBK"));
      BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("src/" + id + "_res.csv")),
              "GBK"));
      String lineTxt = null;
      output.write("输入年份,输入月份,输入天数,期望年份,期望月份,期望天数,输出年份,输出天数,输出日期");
      output.newLine();
      int sum = 0, correct = 0;
      while ((lineTxt = input.readLine()) != null) {
        ++sum;
        String[] names = lineTxt.split(",");
        calendar.setYear(Integer.parseInt(names[0]));
        calendar.setMonth(Integer.parseInt(names[1]));
        calendar.setDay(Integer.parseInt(names[2]));
        calendar.next();
        output.write(lineTxt + "," + calendar.getYear() + "," + calendar.getMonth() + "," + calendar.getDay());
        output.newLine();
        if (calendar.equal(Integer.parseInt(names[3]), Integer.parseInt(names[4]), Integer.parseInt(names[5]))) {
          ++correct;
        }
      }
      output.write("准确率为" + ((float) correct) / sum);
      output.newLine();
      output.flush();
      input.close();
      output.close();
      System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t测试结果准确率为" + ((float) correct) * 100 / sum + "%");
      System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t测试结果已经写入" + id + "_res.csv文件");
    } catch (Exception e) {
      System.out.println("测试失败");
    }
  }
}
