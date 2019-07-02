package exercise;

import java.io.*;

public class Telecom {
  private double basic = 25.0;
  private int time;
  private final double feePer = 0.15;
  private double last;
  private double lastRate = 0.05;
  private int count;
  private double discount;
  private double result;

  public double getFeePer() {
    return feePer;
  }

  public double getLast() {
    return last;
  }

  private void setLast(double last) {
    this.last = last;
    if (this.last < 0) {
      this.valid = false;
    }
  }

  public double getLastRate() {
    return lastRate;
  }

  public void setLastRate(double lastRate) {
    this.lastRate = lastRate;
  }

  public boolean isValid() {
    return valid;
  }

  public void setValid(boolean valid) {
    this.valid = valid;
  }

  public double getBasic() {
    return basic;
  }

  public void setBasic(double basic) {
    this.basic = basic;
  }

  public double getTime() {
    return time;
  }

  private void setTime(int time) {
    this.time = time;
    if (time < 0) {
      this.valid = false;
    }
  }

  public int getCount() {
    return count;
  }

  private void setCount(int count) {
    this.count = count;
    if (count < 0 || count > 11) {
      this.valid = false;
    } else {
      if (this.time > 300 && this.count <= 6) {
        this.discount = 0.03;
      } else if (this.time > 180 && this.count <= 3) {
        this.discount = 0.025;
      } else if (this.time > 120 && this.count <= 3) {
        this.discount = 0.02;
      } else if (this.time > 60 && this.count <= 2) {
        this.discount = 0.015;
      } else if (this.count <= 1) {
        this.discount = 0.01;
      } else {
        this.discount = 0;
      }
    }

  }

  public double getDiscount() {
    return discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }

  private double getResult() {
    return result;
  }

  public void setResult(double result) {
    this.result = result;
  }


  private boolean valid = true;

  private void calculate() {
    if (!this.valid) {
      this.result = -1;
    } else {
      this.result = this.last * this.lastRate + this.basic + (1 - this.discount) * this.time * this.feePer;
    }
  }

  private boolean equal(double res) {
    return Math.abs(this.result - res) < 0.01;
  }

  public static void exec() {
    try {
      System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t开始测试");
      Telecom telecom = new Telecom();
      BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/6.csv")), "GBK"));
      BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("src/6_res.csv")),
              "GBK"));
      String lineTxt;
      output.write("输入未交费,输入时间,输入次数,期望费用,输出费用");
      output.newLine();
      int sum = 0, correct = 0;
      while ((lineTxt = input.readLine()) != null) {
        ++sum;
        String[] names = lineTxt.split(",");
        telecom.setLast(Double.parseDouble(names[0]));
        telecom.setTime(Integer.parseInt(names[1]));
        telecom.setCount(Integer.parseInt(names[2]));
        telecom.calculate();
        output.write(lineTxt + "," + telecom.getResult());
        output.newLine();
        if (telecom.equal(Double.parseDouble(names[3]))) {
          ++correct;
        }
      }
      output.write("准确率为" + ((float) correct) / sum);
      output.newLine();
      output.flush();
      input.close();
      output.close();
      System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t测试结果准确率为" + ((float) correct) * 100 / sum + "%");
      System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t测试结果已经写入6_res.csv文件");
    } catch (Exception e) {
      System.out.println("测试失败");
    }
  }
}
