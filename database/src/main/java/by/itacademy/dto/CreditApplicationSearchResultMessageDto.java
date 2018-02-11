package by.itacademy.dto;

import by.itacademy.entity.CreditApplication;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreditApplicationSearchResultMessageDto implements Serializable {

    private static final long serialVersionUID = 1791120330034012035L;

    private String outputMessage;

    public CreditApplicationSearchResultMessageDto(List<CreditApplication> creditApplications) {
        StringBuilder stringBuilder = new StringBuilder();
        for (CreditApplication creditApplication : creditApplications) {
            stringBuilder.append(" Application date: " + creditApplication.getApplicationDate())
                            .append(" Birthday: " + creditApplication.getClient().getBirthday())
                            .append(" Children: " + creditApplication.getClient().getChildren().size())
                            .append(" Gender: " + creditApplication.getClient().getGender())
                            .append(" Marital status: " + creditApplication.getClient().getMaritalStatus())
                            .append(" Rating: " + creditApplication.getClient().getRating())
                            .append(" Credit: " + creditApplication.getCredit().getTitle())
                            .append(" Income: " + creditApplication.getIncome())
                            .append(" Pledge: " + creditApplication.getPledge())
                            .append(" Sum: " + creditApplication.getSum())
                            .append(" Loan period: " + creditApplication.getLoanPeriod())
                            .append(" Application quality: " + creditApplication.getApplicationQuality())
                            .append(" Scoring system resolution: " + creditApplication.getScoringSystemResolution())
                            .append("\n");
        }
        this.outputMessage = stringBuilder.toString();
    }
}
