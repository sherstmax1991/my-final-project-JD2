package by.itacademy.dto;

import by.itacademy.entity.CreditApplication;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreditApplicationSearchResultPageDto {

    private List<CreditApplicationSearchResultDto> resultPage;
    private Long lastPage;

    public CreditApplicationSearchResultPageDto(PageableSearchResult<CreditApplication> searchResult) {
        this.resultPage = searchResult.getResultPage().stream()
                            .map(CreditApplicationSearchResultDto::new)
                            .collect(Collectors.toList());
        this.lastPage = searchResult.getLastPage();
    }
}