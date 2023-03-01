import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.*;

public class UpdateBookInfoTest {

    BookClient bookClient;
    int bookId;

    @Before
    public void setUp() {
        bookClient = new BookClient();
        bookId = bookClient.addBook(Book.createBookWithAllFields()).extract().path("book.id");
    }

    @After
    public void tearDown() {
        if (bookId != 0) {
            bookClient.delete(bookId);
        }
    }

    @Test
    public void updateBookInfo() {
        bookClient.getRefreshBookInfo(bookId, Book.createBookWithAllFields())
                .body("book.id", equalTo(bookId))
                .statusCode(200);
    }

    @Test
    public void updateBookInfoWithoutId() {
        bookClient.getRefreshBookInfoWithoutId(Book.createBookWithAllFields())
                .statusCode(404);
    }

    @Test
    public void updateBookInfoWithWrongId() {
        int wrongId = bookId + new Random().nextInt(100);
        bookClient.getRefreshBookInfo(wrongId, Book.createBookWithAllFields())
                .statusCode(404)
                .body("error", containsString("not found"));
    }

    @Test
    public void updateBookInfoWithoutIsElectronicBookField() {
        bookClient.getRefreshBookInfo(bookId, Book.createBookWithoutIsElectronicField())
                .statusCode(400)
                .body("error", equalTo("IsElectronicBook is required"));
    }

    @Test
    public void updateBookInfoWithoutYearField() {
        bookClient.getRefreshBookInfo(bookId, Book.createBookWithoutYearField())
                .statusCode(400)
                .body("error", equalTo("Year is required"));
    }

    @Test
    public void updateBookInfoWithoutAuthorField() {
        bookClient.getRefreshBookInfo(bookId, Book.createBookWithoutAuthorField())
                .statusCode(400)
                .body("error", equalTo("Author is required"));
    }

    @Test
    public void updateBookInfoWithoutNameField() {
        bookClient.getRefreshBookInfo(bookId, Book.createBookWithoutRequiredField())
                .statusCode(400);
    }

    @Test
    public void updateBookInfoWithoutRequestBody() {
        bookClient.getRefreshBookInfoWithoutRequestBody(bookId)
                .statusCode(400);
    }

    @Test
    public void updateBookInfoWithEmptyRequestBody() {
        bookClient.getRefreshBookInfo(bookId, Book.createBookWithoutRequiredField())
                .statusCode(400)
                .body("error", equalTo("Not found request Json body"));
    }
}
