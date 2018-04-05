package buttasam.cvut.cz.android_feedreader.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Samuel Butta
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private Integer id;
    private String title;
    private String author;
    private Date date;
    private String content;
    private String url;
}
