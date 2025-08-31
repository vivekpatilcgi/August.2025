package pac1;
import org.testng.annotations.Test; 
public class TC010_group {
  @Test(groups= {"smoke"})
  public void test1() {
	  System.out.println("This is smoketest1");
  }
  @Test(groups= {"regression"})
  public void test2() {
	  System.out.println("This is regressiontest1");
  }
  @Test(groups= {"regression"})
  public void test3() {
	  System.out.println("This is  regressiontest2");
  }
  @Test(groups= {"smoke"})
  public void test4() {
	  System.out.println("This is smoketest2");
  }
}

 