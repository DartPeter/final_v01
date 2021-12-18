package base;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErrorMessage {
    private Integer correlationId;
    private Integer int1;
    private Integer int2;
    private Integer sum;
}
