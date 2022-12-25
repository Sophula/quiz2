import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class quiz2 {
        String baseURI = RestAssured.baseURI = "https://bookstore.toolsqa.com/Account/v1/User";

        @Test
        public void userLogin() {
                int statusCode = given().contentType(ContentType.JSON).body("{\n" +
                                "  \"userName\": \"Sopio333\",\n" +
                                "  \"password\": \"Quizquiz132.@\"\n" +
                                "}")
                                .when().post(baseURI).getStatusCode();
                Assert.assertEquals(statusCode, 201);
        }

        @Test
        public void emptyUser() {
                int statusCode = given().contentType(ContentType.JSON).body("{\n" +
                                "  \"userName\": \"\",\n" +
                                "  \"password\": \"\"\n" +
                                "}")
                                .when().post(baseURI).getStatusCode();
                Assert.assertEquals(statusCode, 400);
        }

        @Test
        public void wrongPassword() {
                int statusCode = given()
                                .contentType(ContentType.JSON)
                                .body("{\n" +
                                                "  \"userName\": \"Sopio2\",\n" +
                                                "  \"password\": \"Quizquiz132.\"\n" +
                                                "}")
                                .when().post(baseURI).getStatusCode();
                Assert.assertEquals(statusCode, 400);
        }
}
