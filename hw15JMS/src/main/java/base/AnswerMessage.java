package base;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AnswerMessage {
    private Integer correlationId;
    private Integer sum;
}
