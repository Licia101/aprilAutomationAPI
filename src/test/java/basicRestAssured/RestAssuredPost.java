package basicRestAssured;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class RestAssuredGet {
    @Test
    public void GetSpecificPost(){
        given().log().all().contentType(ContentType.JSON).
                when().get("https://jsonplaceholder.typicode.com/posts/{id}", 5).
               then().log().all().statusCode(200).body("title", equalTo("nesciunt quas odio")).
                body("body",equalTo("repudiandae veniam quaerat sunt sed\\nalias aut fugiat sit autem sed est\\nvoluptatem omnis possimus esse voluptatibus quis\\nest aut tenetur dolor neque"));
    }
}
