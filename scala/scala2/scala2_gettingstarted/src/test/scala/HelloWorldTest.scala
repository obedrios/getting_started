
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.Test

class HelloWorldTest {

  var helloObj: HelloWorld = null;

  @BeforeEach
  def setUp(): Unit = {
    helloObj = new HelloWorld();
  }

  @AfterEach
  def tearDown(): Unit = {
  }

  @Test
  def testSayHelloWorld(): Unit = {
    assertEquals("Hello World!!",helloObj.sayHello())
  }

}