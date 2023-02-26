import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.notNullValue;


public class GetBookInfoTest {

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
    public void getBookInfoWithId() {
        bookId = bookClient.addBook(Book.randomBookWithAllFields()).extract().body().path("book.id");
        bookClient.getBook(bookId)
                .body("book.name", notNullValue())
                .and()
                .statusCode(200);
    }

    @Test
    public void getListOfBooks() {
        bookId = bookClient.addBook(Book.randomBookWithAllFields()).extract().body().path("book.id");
        bookClient.getListOfBooks()
                .body("books", notNullValue())
                .and()
                .statusCode(200);
    }

    @Test
    public void getBookInfoWithoutId() {
        bookId = bookClient.addBook(Book.randomBookWithAllFields()).extract().body().path("book.id");
        int emptyId = 0;
        bookClient.getBookWithoutId(emptyId)
                .statusCode(404)
                .assertThat()
                .body("error", endsWith("not found"));
    }

    @Test
    public void getBookInfoWithWrongId() {
        bookId = bookClient.addBook(Book.randomBookWithAllFields()).extract().path("book.id");
        int wrongId = bookId + new Random().nextInt(100);
        bookClient.getBook(wrongId)
                .statusCode(404)
                .assertThat()
                .body("error", endsWith("not found"));
    }
}


