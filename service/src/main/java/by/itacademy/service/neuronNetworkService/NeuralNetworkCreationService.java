package by.itacademy.service.neuronNetworkService;

import by.itacademy.dto.NeuronNetworkSettingsDto;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;

public interface NeuralNetworkCreationService {

    String run(NeuronNetworkSettingsDto dto);
    String testNeuralNetwork(NeuralNetwork neuralNet, DataSet testSet);
    void keepScore(int prediction, int ideal);
    String formatDecimalNumber(double number);
}
