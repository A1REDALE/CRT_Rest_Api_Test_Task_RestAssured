import org.apache.maven.surefire.shared.lang3.RandomStringUtils;

import java.util.Random;

public class Book {
    private final Object name;
    private final Object author;
    private final Object year;
    private final Object isElectronicBook;

    private Book(BookBuilder bookBuilder) {
        name = bookBuilder.name;
        author = bookBuilder.author;
        year = bookBuilder.year;
        isElectronicBook = bookBuilder.isElectronicBook;
    }

    public static class BookBuilder {
        private Object name;
        private Object author;
        private Object year;
        private Object isElectronicBook;

        public BookBuilder(Object name) {
            this.name = name;
        }

        public BookBuilder() {
        }

        public BookBuilder setAuthor(Object author) {
            this.author = author;
            return this;
        }

        public BookBuilder setYear(Object year) {
            this.year = year;
            return this;
        }

        public BookBuilder setElectronicBook(Object electronicBook) {
            isElectronicBook = electronicBook;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }

    public static Book randomBookWithAllFields() {
        return new Book.BookBuilder(RandomStringUtils.randomAlphabetic(10))
                .setAuthor(RandomStringUtils.randomAlphabetic(10))
                .setYear(new Random().nextInt(3000))
                .setElectronicBook(new Random().nextBoolean())
                .build();
    }

    public static Book randomBookWithRequiredField() {
        return new Book.BookBuilder(RandomStringUtils.randomAlphabetic(10))
                .build();
    }

    public static Book randomBookWithoutIsElectronicBookField() {
        return new Book.BookBuilder(RandomStringUtils.randomAlphabetic(10))
                .setAuthor(RandomStringUtils.randomAlphabetic(10))
                .setYear(new Random().nextInt(3000))
                .build();
    }

    public static Book randomBookWithoutYearField() {
        return new Book.BookBuilder(RandomStringUtils.randomAlphabetic(10))
                .setAuthor(RandomStringUtils.randomAlphabetic(10))
                .setElectronicBook(new Random().nextBoolean())
                .build();
    }

    public static Book randomBookWithoutAuthorField() {
        return new Book.BookBuilder(RandomStringUtils.randomAlphabetic(10))
                .setYear(new Random().nextInt(3000))
                .setElectronicBook(new Random().nextBoolean())
                .build();
    }

    public static Book randomBookWithNameFieldWithIncorrectValue() {
        return new Book.BookBuilder(new Random().nextInt(10))
                .build();
    }

    public static Book randomBookWithNameFieldAndIsElectronicBookWithIncorrectValue() {
        return new Book.BookBuilder(RandomStringUtils.randomAlphabetic(10))
                .setElectronicBook(RandomStringUtils.randomAlphabetic(6))
                .build();
    }

    public static Book randomBookWithNameFieldAndYearBookWithIncorrectValue() {
        return new Book.BookBuilder(RandomStringUtils.randomAlphabetic(10))
                .setYear(RandomStringUtils.randomAlphabetic(6))
                .build();
    }

    public static Book randomBookWithNameFieldAndAuthorWithIncorrectValue(){
        return new Book.BookBuilder(RandomStringUtils.randomAlphabetic(10))
                .setAuthor(new Random().nextInt(10))
                .build();
    }

    public static Book bookWithoutRequiredField() {
        return new Book.BookBuilder().build();
    }
}
