package stepDefinitions;

import com.jayway.jsonpath.DocumentContext;
import com.sun.net.httpserver.Headers;

public class BaseStep {
    public Headers headers;
    private String endpointPath;
    private Response response;
    public DocumentContext requestBodyJson;

    public BaseSteps() {  }

    public void setHeaders(Headers value) { restHeaders();
        headers = value; }

    private void restHeaders() {  headers = null;}

    public void setHeadersWithContentType() {
        headers = new Headers(
                new Header("Content-Type", "application/json"));
        setHeaders(headers);  }

    protected URL getURL() {
        try {
            return new URL(endpointPath);
        } catch (MalformedURLException e) {
            throw new RuntimeException();  }  }
    public Response getCall() {
        response = RestAssured.given().log().all()
                .relaxedHTTPSValidation()
                .headers(headers)
                .when().get(getURL())
                .then().log().all().extract().response();
        return response; }

    public Response getPostCall() {
        response = RestAssured.given().log().all()          .relaxedHTTPSValidation().headers(headers)
                .body(requestBodyJson.jsonString())
                .when().post(getURL())
                .then().log().all().extract().response();
        return response; }

    public void setEndpointPath(String endpointPath) {
        this.endpointPath = endpointPath;  }

    public DocumentContext loadJsonTemplate(String path) {
        requestBodyJson = JsonPath.parse(this.getClass().getResourceAsStream(path));
        return requestBodyJson; }
}


