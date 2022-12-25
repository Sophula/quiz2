import org.testng.annotations.Test;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

public class Quiz_2 {
  String URL = "https://bookstore.toolsqa.com/Account/v1/User";
  JsonObject userData = new JsonObject();

  @Test(description = "Create user without providing any data")
  public void createWithoutData() {
    given().contentType(ContentType.JSON).post(URL).then().assertThat().statusCode(400);
  }

  @Test(description = "Create user that already exist")
  public void createExistingUser() {
    userData.addProperty("userName", "testuser");
    userData.addProperty("password", "StrongPassword#1");
    given().contentType(ContentType.JSON).body(userData).post(URL).then().assertThat().statusCode(406);
  }

  @Test(description = "Create user (with randomly generated username)")
  public void createUser() {
    userData.addProperty("userName", "testuser-" + java.time.LocalDateTime.now());
    userData.addProperty("password", "StrongPassword#1");
    given().contentType(ContentType.JSON).body(userData).post(URL).then().assertThat().statusCode(201);
  }
}
