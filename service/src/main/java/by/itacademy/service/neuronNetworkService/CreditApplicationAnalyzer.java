package by.itacademy.service.neuronNetworkService;

import by.itacademy.dto.CreditApplicationNeuronNetworkDto;
import by.itacademy.entity.enums.ApplicationQuality;

public interface CreditApplicationAnalyzer {

    ApplicationQuality analyzeCreditApplication(CreditApplicationNeuronNetworkDto
                                                               creditApplicationNeuronNetworkDto);
}
