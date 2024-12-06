package dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrencyDTO {
    private Long id;
    private String code;
    private String name;
    private String sign;
}
