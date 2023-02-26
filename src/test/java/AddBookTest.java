import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class AddBookTest {
    BookClient bookClient;
    int bookId;

    @Before
    public void setUp() {
        bookClient = new BookClient();
    }

    @After
    public void tearDown() {
        if (bookId != 0) {
            bookClient.delete(bookId);
        }
    }

    @Test
    public void checkAddBookWithAllFilledFields() {
        bookId = bookClient.addBook(Book.randomBookWithAllFields())
                .body("book.id", notNullValue())
                .and()
                .statusCode(201)
                .extract().path("book.id");
    }

    @Test
    public void checkAddBookWithRequiredField() {
        bookId = bookClient.addBook(Book.randomBookWithRequiredField())
                .body("book.id", notNullValue())
                .and()
                .statusCode(201)
                .extract().path("book.id");
    }

    @Test
    public void checkAddBookWithRequiredFieldAndAuthorWithIncorrectValue() {
        bookId = bookClient.addBook(Book.randomBookWithNameFieldAndAuthorWithIncorrectValue())
                .body("book.id", notNullValue())
                .and()
                .statusCode(400)
                .extract().path("book.id");
    }

    @Test
    public void checkAddBookWithRequiredFieldAndIsElectronicWithIncorrectValue() {
        bookId = bookClient.addBook(Book.randomBookWithNameFieldAndIsElectronicBookWithIncorrectValue())
                .body("book.id", notNullValue())
                .and()
                .statusCode(400)
                .extract().path("book.id");
    }

    @Test
    public void checkAddBookWithRequiredFieldAndYearWithIncorrectValue() {
        bookId = bookClient.addBook(Book.randomBookWithNameFieldAndYearBookWithIncorrectValue())
                .body("book.id", notNullValue())
                .and()
                .statusCode(400)
                .extract().path("book.id");
    }

    @Test
    public void checkAddBookWithoutRequiredField() {
        bookClient.addBook(Book.bookWithoutRequiredField())
                .statusCode(400)
                .assertThat()
                .body("error", equalTo("Name is required"));
    }

    @Test
    public void checkAddBookWithoutRequestBody() {
        bookClient.addBookWithoutRequestBody(Book.randomBookWithRequiredField())
                .statusCode(400);
    }

    @Test
    public void checkAddBookWithEmptyRequestBody() {
        bookClient.addBook(Book.bookWithoutRequiredField())
                .statusCode(400);
    }
}
