package base;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RequestMessage {
    private Integer correlationId;
    private Integer int1;
    private Integer int2;
}
