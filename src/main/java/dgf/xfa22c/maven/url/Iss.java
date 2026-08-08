package dgf.xfa22c.maven.url;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Iss {

    private String message;
    private long timestamp;
    private IssPosition iss_position;

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    static class IssPosition{
        private String longitude;
        private String latitude;
    }

}
