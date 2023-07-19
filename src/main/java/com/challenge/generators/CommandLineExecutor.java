package com.challenge.generators;

import com.challenge.generators.model.ReaderStrategy;
import com.challenge.generators.services.FileService;
import com.challenge.generators.services.JsonService;
import com.challenge.generators.services.OperationRunner;
import com.challenge.generators.services.Processor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Profile("!test")
@Component
@Slf4j
/**
 * Command line executor to execute spring project with commandLine.
 */
public class CommandLineExecutor  implements CommandLineRunner {

    private final Processor processor;
    private final ReaderStrategy readerStrategy;

    private final JsonService jsonService;

    private final OperationRunner operationRunner;

    /**
     *
     * @param processor the processor itself is going to manage the logic of the program.
     * @param fileService in this case we instantiate a file service to search for the file inside the resources folder.
     * @param operationRunner this operation runner is the core to process operations like sum,average,max,min or other operations.
     * @param jsonService this is the jsonService we use to process the json from the data.json.
     */
    public CommandLineExecutor(Processor processor, FileService fileService,
                               OperationRunner operationRunner, JsonService jsonService){
        this.processor = processor;
        this.readerStrategy = fileService;
        this.jsonService = jsonService;
        this.operationRunner = operationRunner;
    }

    @Override
    public void run(String... args) {
        while(true){
            Scanner scanner = new Scanner(System.in);
            log.info("Press r rerun everytime you need: ");
            String input = scanner.nextLine();
            processor.setFileReader(readerStrategy);
            processor.setJsonService(jsonService);
            processor.setOperationRunner(operationRunner);

            if(input.equalsIgnoreCase("r")){
                processor.run();
            } else {
                break;
            }

        }
    }
}
