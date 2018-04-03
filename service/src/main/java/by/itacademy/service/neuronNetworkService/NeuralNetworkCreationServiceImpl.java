package by.itacademy.service.neuronNetworkService;

import by.itacademy.classes.CustomNormalizer;
import by.itacademy.dto.NeuronNetworkSettingsDto;
import org.apache.log4j.Logger;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class NeuralNetworkCreationServiceImpl implements LearningEventListener, NeuralNetworkCreationService {

    private static final Logger LOGGER = Logger.getLogger(NeuralNetworkCreationServiceImpl.class);
//    private static final int TRAIN_SET_PERCENT = 70;
//    private static final int TEST_SET_PERCENT = 30;
//    private static final int SECOND_LAYER_NEURONS_NUMBER = 12;
//    private static final int THIRD_LAYER_NEURONS_NUMBER = 6;
//    private static final double LEARNING_RATE = 0.01;
//    private static final double MAX_ERROR = 0.001;
//    private static final int MAX_ITERATIONS = 1000;
//    private static final int INPUTS_AMOUNT = 9;
//    private static final int OUTPUTS_AMOUNT = 2;
    private static final String TRAINING_SET_FILE_NAME = "//home//maxim//IdeaProjects//my-final-project-JD2//service//src//main//resources//dataset.txt";
//    private static final String NEURON_NETWORK_PATH = "//home//maxim//IdeaProjects//my-final-project-JD2//service//src//main//resources//neuronNetwork.nnet";

    private int[] count;
    private int[] correct;
    private int unpredicted;

    private static double[] maxInputs;

    static {
        initMaxInputs(TRAINING_SET_FILE_NAME);
    }

    private static DataSet initMaxInputs(String pathToDataset) {
        DataSet dataSet = DataSet.createFromFile(pathToDataset, NeuronNetworkSettingsDto.INPUTS_AMOUNT,
                NeuronNetworkSettingsDto.OUTPUTS_AMOUNT, " ");
        dataSet.shuffle();
        CustomNormalizer normalizer = new CustomNormalizer();
        normalizer.normalize(dataSet);
        maxInputs = normalizer.getMaxIn();
        return dataSet;
    }

    public static double[] getMaxInputs() {
        return maxInputs;
    }

    public String run(NeuronNetworkSettingsDto dto) {

        count = new int[3];
        correct = new int[3];
        unpredicted = 0;
        DataSet dataSet = initMaxInputs(dto.getTrainSetFilePath());
//        DataSet dataSet = DataSet.createFromFile(dto.getTrainSetFilePath(), NeuronNetworkSettingsDto.INPUTS_AMOUNT,
//                                                NeuronNetworkSettingsDto.OUTPUTS_AMOUNT, " ");
//        dataSet.shuffle();
//        CustomNormalizer normalizer = new CustomNormalizer();
//        normalizer.normalize(dataSet);
//        maxInputs = normalizer.getMaxIn();


        DataSet[] trainingAndTestSet = dataSet.createTrainingAndTestSubsets(dto.getTrainSetPercent(),
                                                                            dto.getTestSetPercent());
        DataSet trainingSet = trainingAndTestSet[0];
        DataSet testSet = trainingAndTestSet[1];

        List<Integer> networkStructure = getNetworkStructure(NeuronNetworkSettingsDto.INPUTS_AMOUNT, dto.getLayers(),
                                                            NeuronNetworkSettingsDto.OUTPUTS_AMOUNT);

        MultiLayerPerceptron neuralNet = new MultiLayerPerceptron(networkStructure);

        MomentumBackpropagation learningRule = (MomentumBackpropagation) neuralNet.getLearningRule();
        learningRule.addListener(this);

        learningRule.setLearningRate(dto.getLearningRate());
        learningRule.setMaxError(dto.getMaxError());
        learningRule.setMaxIterations(dto.getMaxIterations());

        neuralNet.learn(trainingSet);

        String report = testNeuralNetwork(neuralNet, testSet);
        neuralNet.save(dto.getNeuronNetworkFilePath());
        return report;
    }

    public String testNeuralNetwork(NeuralNetwork neuralNet, DataSet testSet) {

        StringBuilder stringBuilder = new StringBuilder();
        for (DataSetRow testSetRow : testSet.getRows()) {
            neuralNet.setInput(testSetRow.getInput());
            neuralNet.calculate();

            double[] networkOutput = neuralNet.getOutput();
            int predicted = maxOutput(networkOutput);

            double[] networkDesiredOutput = testSetRow.getDesiredOutput();
            int ideal = maxOutput(networkDesiredOutput);

            keepScore(predicted, ideal);
        }

        double correctlyPredictedTotalPercent = (double) this.correct[2] * 100 / (double) this.count[2];
        double correctlyPredictedGoodPercent = (double) this.correct[0] * 100.0 / (double) this.count[0];
        double incorrectlyPredictedBadPercent = (double) this.correct[1] * 100.0 / (double) this.count[1];

        stringBuilder.append("Total cases: " + this.count[2] + ". \n")
                .append("Correctly predicted cases: " + this.correct[2] + ". \n")
                .append("Incorrectly predicted cases: " + (this.count[2] - this.correct[2] - unpredicted) + ". \n")
                .append("Unrecognized cases: " + unpredicted + ". \n")
                .append("Predicted correctly: " + formatDecimalNumber(correctlyPredictedTotalPercent) + "%. \n")
                .append("Prediction for 'Good credit risk' => (Correct/total): " + this.correct[0] + "/" + count[0]
                        + "(" + formatDecimalNumber(correctlyPredictedGoodPercent) + "%). \n")
                .append("Prediction for 'Bad credit risk' => (Correct/total): " + this.correct[1] + "/" + count[1]
                        +
                        "(" + formatDecimalNumber(incorrectlyPredictedBadPercent) + "%). \n");

        return stringBuilder.toString();
    }

    @Override
    public void handleLearningEvent(LearningEvent event) {
        BackPropagation bp = (BackPropagation) event.getSource();
        if (event.getEventType().equals(LearningEvent.Type.LEARNING_STOPPED)) {
            double error = bp.getTotalNetworkError();
            LOGGER.info("Training completed in " + bp.getCurrentIteration() + " iterations, ");
            LOGGER.info("With total error: " + formatDecimalNumber(error));
        } else {
            LOGGER.info("Iteration: " + bp.getCurrentIteration() + " | Network error: " + bp.getTotalNetworkError());
        }
    }

    public static int maxOutput(double[] array) {
        double max = array[0];
        int index = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                index = i;
                max = array[i];
            }
        }
        if (max < 0.5) {
            return -1;
        }
        return index;
    }

    public void keepScore(int prediction, int ideal) {
        count[ideal]++;
        count[2]++;

        if (prediction == ideal) {
            correct[ideal]++;
            correct[2]++;
        }
        if (prediction == -1) {
            unpredicted++;
        }
    }

    public String formatDecimalNumber(double number) {
        return new BigDecimal(number).setScale(4, RoundingMode.HALF_UP).toString();
    }

    private List<Integer> getNetworkStructure(Integer inputs, Integer[] layers, Integer outputs) {
        List<Integer> result = new ArrayList<>();
        result.add(inputs);
        for (Integer layer: layers) {
            if (layer > 0) {
                result.add(layer);
            }
        }
        result.add(outputs);
        return result;
    }
}