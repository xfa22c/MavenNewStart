package dgf.xfa22c.maven.book;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Book {

    private int id;
    private String name;
    private String author;
    private int year;
    private boolean isRead;

}
