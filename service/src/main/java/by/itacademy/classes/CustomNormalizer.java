package by.itacademy.classes;

import lombok.Getter;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.util.data.norm.Normalizer;

@Getter
public class CustomNormalizer implements Normalizer {

    private double[] maxIn;
    private double[] maxOut;

    @Override
    public void normalize(DataSet dataSet) {

        findMaxVectors(dataSet);

        for (DataSetRow row : dataSet.getRows()) {
            double[] normalizedInput = normalizeMax(row.getInput(), maxIn);
            row.setInput(normalizedInput);

            if (dataSet.isSupervised()) {
                double[] normalizedOutput = normalizeMax(row.getDesiredOutput(), maxOut);
                row.setDesiredOutput(normalizedOutput);
            }
        }
    }

    private void findMaxVectors(DataSet dataSet) {
        int inputSize = dataSet.getInputSize();
        int outputSize = dataSet.getOutputSize();

        maxIn = new double[inputSize];
        for (int i = 0; i < inputSize; i++) {
            maxIn[i] = Double.MIN_VALUE;
        }

        maxOut = new double[outputSize];
        for (int i = 0; i < outputSize; i++) {
            maxOut[i] = Double.MIN_VALUE;
        }

        for (DataSetRow dataSetRow : dataSet.getRows()) {
            double[] input = dataSetRow.getInput();
            for (int i = 0; i < inputSize; i++) {
                if (input[i] > maxIn[i]) {
                    maxIn[i] = input[i];
                }
            }

            double[] output = dataSetRow.getDesiredOutput();
            for (int i = 0; i < outputSize; i++) {
                if (output[i] > maxOut[i]) {
                    maxOut[i] = output[i];
                }
            }

        }
    }

    public double[] normalizeMax(double[] vector, double[] max) {
        double[] normalizedVector = new double[vector.length];

        for (int i = 0; i < vector.length; i++) {
            normalizedVector[i] = vector[i] / max[i];
        }

        return normalizedVector;
    }
}
