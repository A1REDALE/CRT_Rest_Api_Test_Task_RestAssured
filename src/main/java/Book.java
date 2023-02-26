import org.apache.maven.surefire.shared.lang3.RandomStringUtils;

import java.util.Random;

public class Book {
    private String name;
    private Object author;
    private Object year;
    private Object isElectronicBook;

    private Book(BookBuilder bookBuilder) {
        name = bookBuilder.name;
        author = bookBuilder.author;
        year = bookBuilder.year;
        isElectronicBook = bookBuilder.isElectronicBook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getAuthor() {
        return author;
    }

    public void setAuthor(Object author) {
        this.author = author;
    }

    public Object getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Object isElectronicBook() {
        return isElectronicBook;
    }

    public void setElectronicBook(Object electronicBook) {
        isElectronicBook = electronicBook;
    }

    public static class BookBuilder {
        private String name;
        private Object author;
        private Object year;
        private Object isElectronicBook;

        public BookBuilder(String name) {
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
                .setAuthor(1234)
                .build();
    }

    public static Book bookWithoutRequiredField() {
        return new Book.BookBuilder().build();
    }
}
