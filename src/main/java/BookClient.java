import Config.Config;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BookClient {
    private final String BOOKS = "/api/books";
    private final String GET_BOOK = "/api/books/{bookId}";

    protected RequestSpecification getSpec() {
        return given().log().all()
                .header("Content-type", "application/json")
                .baseUri(Config.getUrl());
    }

    public ValidatableResponse addBook(Book book) {
        return getSpec()
                .body(book)
                .when()
                .post(BOOKS)
                .then().log().all()
                .assertThat();
    }

    public ValidatableResponse addBookWithoutRequestBody() {
        return getSpec()
                .when()
                .post(BOOKS)
                .then().log().all()
                .assertThat();
    }

    public ValidatableResponse getBook(int bookId) {
        return getSpec()
                .pathParam("bookId", bookId)
                .when()
                .get(GET_BOOK)
                .then().log().all()
                .assertThat();
    }

    public ValidatableResponse getBookWithoutId(int bookId) {
        return getSpec()
                .pathParam("bookId", bookId)
                .when()
                .get(GET_BOOK)
                .then().log().all()
                .assertThat();
    }

    public ValidatableResponse getListOfBooks() {
        return getSpec()
                .when()
                .get(BOOKS)
                .then().log().all()
                .assertThat();
    }

    public ValidatableResponse getRefreshBookInfo(int bookId, Book book) {
        return getSpec()
                .pathParam("bookId", bookId)
                .body(book)
                .when()
                .put(GET_BOOK)
                .then().log().all()
                .assertThat();
    }

    public ValidatableResponse getRefreshBookInfoWithoutId(Book book) {
        return getSpec()
                .body(book)
                .when()
                .put(BOOKS + "/")
                .then().log().all()
                .assertThat();
    }

    public ValidatableResponse getRefreshBookInfoWithoutRequestBody(int bookId) {
        return getSpec()
                .pathParam("bookId", bookId)
                .when()
                .put(GET_BOOK)
                .then().log().all()
                .assertThat();
    }

    public ValidatableResponse delete(int bookId) {
        return getSpec()
                .pathParam("bookId", bookId)
                .when()
                .delete(GET_BOOK)
                .then().log().all()
                .assertThat();
    }

    public ValidatableResponse deleteWithoutId() {
        return getSpec()
                .pathParam("bookId","")
                .when()
                .delete(GET_BOOK)
                .then().log().all()
                .assertThat();
    }
}

