import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

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
        bookId = bookClient.addBook(Book.createBookWithAllFields())
                .body("book.id", notNullValue())
                .statusCode(201)
                .extract().path("book.id");
    }

    @Test
    public void checkAddBookWithRequiredField() {
        bookId = bookClient.addBook(Book.createBookWithNameField())
                .body("book.id", notNullValue())
                .statusCode(201)
                .extract().path("book.id");
    }

    @Test
    public void checkAddBookWithRequiredFieldWithIncorrectValue() {
        bookClient.addBook(Book.createBookWithNameFieldWithIncorrectValue())
                .statusCode(400)
                .body("error", equalTo("Name must be String type (Unicode)"));
    }

    @Test
    public void checkAddBookWithRequiredFieldAndAuthorWithIncorrectValue() {
        bookClient.addBook(Book.createBookWithNameFieldAndAuthorWithIncorrectValue())
                .statusCode(400)
                .body("error", containsString("Author must be String type"));
    }

    @Test
    public void checkAddBookWithRequiredFieldAndIsElectronicWithIncorrectValue() {
        bookClient.addBook(Book.createBookWithNameFieldAndIsElectronicBookWithIncorrectValue())
                .statusCode(400)
                .body("error", containsString("isElectronic must be Boolean type"));
    }

    @Test
    public void checkAddBookWithRequiredFieldAndYearWithIncorrectValue() {
        bookClient.addBook(Book.createBookWithNameFieldAndAuthorWithIncorrectValue())
                .statusCode(400)
                .body("error", containsString("Year must be int type"));
    }

    @Test
    public void checkAddBookWithoutRequiredField() {
        bookClient.addBook(Book.createBookWithoutRequiredField())
                .statusCode(400)
                .body("error", equalTo("Name is required"));
    }

    @Test
    public void checkAddBookWithoutRequestBody() {
        bookClient.addBookWithoutRequestBody()
                .statusCode(400);
    }

    @Test
    public void checkAddBookWithEmptyRequestBody() {
        bookClient.addBook(Book.createBookWithoutRequiredField())
                .statusCode(400)
                .body("error", equalTo("Name is required"));;
    }
}
