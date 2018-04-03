package by.itacademy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NeuronNetworkSettingsDto {

    public static final int INPUTS_AMOUNT = 9;
    public static final int OUTPUTS_AMOUNT = 2;

    private int trainSetPercent;
    private int testSetPercent;
    private Integer[] layers;
    private double learningRate;
    private double maxError;
    private int maxIterations;
    private String trainSetFilePath;
    private String neuronNetworkFilePath;
}
