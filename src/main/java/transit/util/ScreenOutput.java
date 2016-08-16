package transit.util;

import transit.AppOutput;

public class ScreenOutput implements AppOutput{
  @Override
  public void print(String output) {
      System.out.println(output);
  }
}
