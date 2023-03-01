import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.maven.surefire.shared.lang3.RandomStringUtils;

import java.util.Random;

@Data
@Builder
@AllArgsConstructor

public class Book {
    private final Object name;
    private final Object author;
    private final Object year;
    private final Object isElectronicBook;

    public static Book createBookWithAllFields(){
        return Book.builder().name(RandomStringUtils.randomAlphabetic(10))
                .author(RandomStringUtils.randomAlphabetic(10))
                .year(new Random().nextInt(3000))
                .isElectronicBook(new Random().nextBoolean())
                .build();
    }
    public static Book createBookWithNameField(){
        return Book.builder().name(RandomStringUtils.randomAlphabetic(10))
                .build();
    }

    public static Book createBookWithoutIsElectronicField(){
        return Book.builder().name(RandomStringUtils.randomAlphabetic(10))
                .author(RandomStringUtils.randomAlphabetic(10))
                .year(new Random().nextInt(3000))
                .build();
    }
    public static Book createBookWithoutYearField(){
        return Book.builder().name(RandomStringUtils.randomAlphabetic(10))
                .author(RandomStringUtils.randomAlphabetic(10))
                .isElectronicBook(new Random().nextBoolean())
                .build();
    }
    public static Book createBookWithoutAuthorField(){
        return Book.builder().name(RandomStringUtils.randomAlphabetic(10))
                .year(new Random().nextInt(3000))
                .isElectronicBook(new Random().nextBoolean())
                .build();
    }
    public static Book createBookWithNameFieldWithIncorrectValue() {
        return Book.builder().name(new Random().nextInt(10))
                .build();
    }
    public static Book createBookWithNameFieldAndIsElectronicBookWithIncorrectValue(){
        return Book.builder().name(RandomStringUtils.randomAlphabetic(10))
                .isElectronicBook(RandomStringUtils.randomAlphabetic(6))
                .build();
    }
    public static Book createBookWithNameFieldAndYearBookWithIncorrectValue(){
        return Book.builder().name(RandomStringUtils.randomAlphabetic(10))
                .year(RandomStringUtils.randomAlphabetic(6))
                .build();
    }
    public static Book createBookWithNameFieldAndAuthorWithIncorrectValue(){
        return Book.builder().name(RandomStringUtils.randomAlphabetic(10))
                .author(RandomStringUtils.randomAlphabetic(10))
                .year(new Random().nextInt(3000))
                .isElectronicBook(new Random().nextBoolean())
                .build();
    }
    public static Book createBookWithoutRequiredField(){
        return Book.builder()
                .build();
    }
}



