package com.challenge.generators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Profile("!test")
@Component
@Slf4j
public class CommandLineExecutor  implements CommandLineRunner {

    private Processor processor;

    public CommandLineExecutor(Processor processor){
        this.processor = processor;
    }

    @Override
    public void run(String... args) {
        while(true){
            Scanner scanner = new Scanner(System.in);
            log.info("Press r rerun and enter: ");
            String input = scanner.nextLine();


            if(input.equalsIgnoreCase("r")){
                processor.run();
                log.info("Press r rerun and enter: ");
            } else {
                break;
            }

        }
    }
}
