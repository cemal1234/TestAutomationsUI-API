import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredTest {
    @Test
    public void ApiControl()
    {
        // Testi çalıştırmadan önce queryParams değeri girilmeli ve istek tipi get/post olarak seçilmelidir.

        RestAssured.baseURI = "http://localhost:4567";
        given()
                .contentType("application/json")
                .header("user","info")
                .header("pass","123")
                .queryParams("params","7,8")
                .when()
                .post("/add")
                .then()
                .statusCode(200)
                .body("status", equalTo(true))
                .log()
                .all();
    }
}