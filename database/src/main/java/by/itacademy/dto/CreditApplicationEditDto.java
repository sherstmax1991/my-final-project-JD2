package by.itacademy.dto;

import by.itacademy.entity.enums.ApplicationQuality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditApplicationEditDto {

    private Long id;
    private ApplicationQuality applicationQuality;
}
