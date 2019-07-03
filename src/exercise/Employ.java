package exercise;

import java.io.*;

public class Employ {
  private int x;
  private int y;
  private int z;
  private boolean valid = true;
  private double total;
  private double bonus;

  private void setX(int x) {
    this.x = x;
    if (this.x < 1 || this.x > 70) {
      setValid(false);
    }
  }

  private void setY(int y) {
    this.y = y;
    if (this.y < 1 || this.y > 80) {
      setValid(false);
    }
  }

  private void setZ(int z) {
    this.z = z;
    if (this.z < 1 || this.z > 90) {
      setValid(false);
    }
  }

  private int getX() {
    return x;
  }

  private int getY() {
    return y;
  }

  private int getZ() {
    return z;
  }

  public boolean isValid() {
    return valid;
  }


  public double getTotal() {
    return total;
  }

  public double getBonus() {
    return bonus;
  }

  private void setValid(boolean valid) {
    this.valid = valid;
  }

  private void caculate() {
    if (!this.valid) {
      this.total = -1;
      this.bonus = -1;
      this.x=-1;
      this.y=-1;
      this.z=-1;
    } else {
      this.total = this.x * 25 + this.y * 30 + this.z * 45;
      if (this.total <= 1000) {
        this.bonus = this.total * 0.1;
      } else if (this.total <= 1800) {
        this.bonus = this.total * 0.15;
      } else {
        this.bonus = this.total * 0.2;
      }
    }
    setValid(true);
  }

  public static void exec() {
    try {
      System.out.println("开始测试");
      Employ employ = new Employ();
      BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/3.csv")), "GBK"));
      BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("src/3_res.csv")),
              "GBK"));
      String lineTxt = null;
      output.write("输入主机,输入显示器,输入外设,预期输出销售额,预期输出佣金,预期输出主机,预期输出显示器,预期输出外设,实际输出销售额,实际输出佣金,实际输出主机,实际输出显示器,实际输出外设");
      output.newLine();
      while ((lineTxt = input.readLine()) != null) {
        String[] names = lineTxt.split(",");
        if (Integer.parseInt(names[0]) == -1) {
          output.write("-1,,,800,80,,,,800,80,,,");
        } else {
          employ.setX(Integer.parseInt(names[0]));
          employ.setY(Integer.parseInt(names[1]));
          employ.setZ(Integer.parseInt(names[2]));
          employ.caculate();
          output.write(lineTxt + "," + employ.total + "," + employ.bonus + "," + employ.getX() + "," + employ.getY() + "," + employ.getZ());
        }
        output.newLine();
      }
      output.flush();
      input.close();
      output.close();
      System.out.println("测试结果已经写入3_res.csv文件");
    } catch (Exception e) {
      System.out.println("测试失败");
    }
  }
}
