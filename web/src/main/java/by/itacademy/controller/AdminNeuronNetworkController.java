package by.itacademy.controller;

import by.itacademy.dto.CreditApplicationNeuronNetworkDto;
import by.itacademy.dto.NeuronNetworkSettingsDto;
import by.itacademy.entity.CreditApplication;
import by.itacademy.entity.enums.ApplicationQuality;
import by.itacademy.service.CreditApplicationService;
import by.itacademy.service.neuronNetworkService.CreditApplicationAnalyzer;
import by.itacademy.service.neuronNetworkService.NeuralNetworkCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminNeuronNetworkController {

    private static final String TRAINING_SET_FILE_NAME =
                "//home//maxim//IdeaProjects//my-final-project-JD2//service//src//main//resources//dataset.txt";
    private static final String NEURON_NETWORK_PATH =
                "//home//maxim//IdeaProjects//my-final-project-JD2//service//src//main//resources//neuronNetwork.nnet";
    private static final int DEFAULT_TRAIN_SET_PERCENT = 70;
    private static final int DEFAULT_TEST_SET_PERCENT = 30;
    private static final double DEFAULT_LEARNING_RATE = 0.01;
    private static final double DEFAULT_MAX_ERROR = 0.001;
    private static final int DEFAULT_MAX_ITERATIONS = 1000;
    private static final int DEFAULT_SECOND_LAYER = 12;
    private static final int DEFAULT_THIRD_LAYER = 6;
    private CreditApplicationService creditApplicationService;
    private CreditApplicationAnalyzer creditApplicationAnalyzer;
    private NeuralNetworkCreationService neuralNetworkCreationService;

    @Autowired
    public AdminNeuronNetworkController(CreditApplicationService creditApplicationService,
                                        CreditApplicationAnalyzer creditApplicationAnalyzer,
                                        NeuralNetworkCreationService neuralNetworkCreationService) {
        this.creditApplicationService = creditApplicationService;
        this.creditApplicationAnalyzer = creditApplicationAnalyzer;
        this.neuralNetworkCreationService = neuralNetworkCreationService;
    }

    @ModelAttribute("neuronNetworkSettingsDto")
    public NeuronNetworkSettingsDto neuronNetworkSettingsDto() {
        NeuronNetworkSettingsDto dto = new NeuronNetworkSettingsDto();
        dto.setTrainSetPercent(DEFAULT_TRAIN_SET_PERCENT);
        dto.setTestSetPercent(DEFAULT_TEST_SET_PERCENT);
        dto.setLearningRate(DEFAULT_LEARNING_RATE);
        dto.setMaxError(DEFAULT_MAX_ERROR);
        dto.setMaxIterations(DEFAULT_MAX_ITERATIONS);
        Integer[] layers = {DEFAULT_SECOND_LAYER, DEFAULT_THIRD_LAYER, 0, 0, 0, 0};
        dto.setLayers(layers);
        return dto;
    }

    @GetMapping("/admin/neuronNetwork")
    public String getNeuronNetwork() {
        return "admin/neuronNetwork";
    }

    @PostMapping("/admin/neuronNetwork/teach")
    @ResponseBody
    public String trainNeuronNetwork(@RequestBody NeuronNetworkSettingsDto dto) {
        List<CreditApplicationNeuronNetworkDto> dtoList = creditApplicationService.findAll().stream()
                                                                            .map(CreditApplicationNeuronNetworkDto::new)
                                                                            .collect(Collectors.toList());
        createDataset(dtoList);
        dto.setTrainSetFilePath(TRAINING_SET_FILE_NAME);
        dto.setNeuronNetworkFilePath(NEURON_NETWORK_PATH);
        return neuralNetworkCreationService.run(dto);
    }

    @RequestMapping("/admin/neuronNetwork/refreshResolutions")
    public String refreshScoringResolution() {
        List<CreditApplication> allCreditApplications = creditApplicationService.findAll();
        for (CreditApplication creditApplication : allCreditApplications) {
            CreditApplicationNeuronNetworkDto creditApplicationNeuronNetworkDto =
                                                            new CreditApplicationNeuronNetworkDto(creditApplication);
            ApplicationQuality applicationQuality =
                                creditApplicationAnalyzer.analyzeCreditApplication(creditApplicationNeuronNetworkDto);
            creditApplication.setScoringSystemResolution(applicationQuality);
        }
        creditApplicationService.save(allCreditApplications);
        return "admin/neuronNetwork";
    }

    private void createDataset(List<CreditApplicationNeuronNetworkDto> dtoList) {
        File datasetFile = new File(TRAINING_SET_FILE_NAME);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(datasetFile))) {
            datasetFile.createNewFile();
            StringBuilder stringBuilder = new StringBuilder();
            for (CreditApplicationNeuronNetworkDto creditApplicationNeuronNetworkDto:dtoList) {
                stringBuilder.append(creditApplicationNeuronNetworkDto);
                stringBuilder.append("\n");
            }
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
