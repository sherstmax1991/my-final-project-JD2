package by.itacademy.service.neuronNetworkService;

import by.itacademy.classes.CustomNormalizer;
import by.itacademy.dto.CreditApplicationNeuronNetworkDto;
import by.itacademy.entity.enums.ApplicationQuality;
import org.apache.log4j.Logger;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

@Service
@Transactional
public class CreditApplicationAnalyzerImpl implements CreditApplicationAnalyzer {

    private static final Logger LOGGER = Logger.getLogger(CreditApplicationAnalyzerImpl.class);
    private static CustomNormalizer normalizer = new CustomNormalizer();

    public ApplicationQuality analyzeCreditApplication(CreditApplicationNeuronNetworkDto
                                                               creditApplicationNeuronNetworkDto) {
        LOGGER.info("Analyzing " + creditApplicationNeuronNetworkDto);
        DataSetRow row = new DataSetRow(creditApplicationNeuronNetworkDto.getClientAge(),
                            creditApplicationNeuronNetworkDto.getGender(),
                            creditApplicationNeuronNetworkDto.getChildren(),
                            creditApplicationNeuronNetworkDto.getClientRating(),
                            creditApplicationNeuronNetworkDto.getMaritalStatus(),
                            creditApplicationNeuronNetworkDto.getLoanPayments(),
                            creditApplicationNeuronNetworkDto.getLoanPeriod(),
                            creditApplicationNeuronNetworkDto.getGuarantors(),
                            creditApplicationNeuronNetworkDto.getCoverageByPledge());
        double[] maxInputs = NeuralNetworkCreationServiceImpl.getMaxInputs();
        LOGGER.info("NeuralNetworkCreationServiceImpl.maxInputs " + maxInputs);
        double[] rowForAnalyze = normalizer.normalizeMax(row.getInput(), maxInputs);
        LOGGER.info("Row for analyze is " + Arrays.toString(rowForAnalyze));
        NeuralNetwork multiLayerPerceptron = null;
        try {
            multiLayerPerceptron = MultiLayerPerceptron.load(new FileInputStream("//home//maxim//IdeaProjects//"
                                        + "my-final-project-JD2//service//src//main//resources//neuronNetwork.nnet"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        multiLayerPerceptron.setInput(rowForAnalyze);
        multiLayerPerceptron.calculate();
        double[] networkOutput = multiLayerPerceptron.getOutput();
        LOGGER.info("networkOutput " + Arrays.toString(networkOutput));
        ApplicationQuality result = NeuralNetworkCreationServiceImpl.maxOutput(networkOutput) == 0
                                                ? ApplicationQuality.GOOD
                                                : ApplicationQuality.BAD;
        LOGGER.info("Result of analyze " + creditApplicationNeuronNetworkDto + ": " + result);
        return result;
    }
}
