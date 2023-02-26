import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.endsWith;

public class DeleteBookTest {
    BookClient bookClient;
    int bookId;
    boolean isOk;

    @Before
    public void setUp() {
        bookClient = new BookClient();
        bookId = bookClient.addBook(Book.randomBookWithAllFields()).extract().path("book.id");
    }

    @After
    public void tearDown() {
        if (!isOk) bookClient.delete(bookId);
    }

    @Test
    public void deleteBook() {
        isOk = bookClient.delete(bookId)
                .statusCode(200)
                .extract().path("result");
    }

    @Test
    public void deleteBookWithoutId() {
        bookClient.deleteWithoutId()
                .statusCode(404);
    }

    @Test
    public void deleteBookWithWrongId() {
        int wrongId = bookId + new Random().nextInt(100);
        bookClient.delete(wrongId)
                .statusCode(404)
                .assertThat()
                .body("error", endsWith("not found"));
    }
}
