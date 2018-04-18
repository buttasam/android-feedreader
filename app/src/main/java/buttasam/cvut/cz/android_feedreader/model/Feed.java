package buttasam.cvut.cz.android_feedreader.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Samuel Butta
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feed {

    private Integer id;
    private String title;
    private String url;

}
