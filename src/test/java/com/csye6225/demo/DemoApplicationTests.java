package com.csye6225.demo;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

<<<<<<< HEAD
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

  @Ignore
  @Test
  public void contextLoads() {
=======
import static org.junit.Assert.assertEquals;


public class DemoApplicationTests {


  @Test
  public void contextLoads() {
    assertEquals(11,11);

>>>>>>> assignment7
  }

}
