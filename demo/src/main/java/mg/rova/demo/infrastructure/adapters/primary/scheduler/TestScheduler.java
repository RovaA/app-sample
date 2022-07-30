package mg.rova.demo.infrastructure.adapters.primary.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;
import java.util.stream.Stream;

//@Component
public class TestScheduler {

    private Logger logger = Logger.getLogger(TestScheduler.class.getName());

    private final String basePath = "/home/rova/Documents/data";

    @Scheduled(fixedRate = 5000)
    public void copyFiles() {
        logger.info("Begin to copy files ....");
        try (Stream<Path> paths = Files.list(Paths.get(basePath + "/pnmdata"))) {
            paths.filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .forEach(file -> {
                        try {
                            Path source = file.toPath();
                            Path destination = Paths.get(basePath + "/rsp/" + file.getName());
                            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (Exception e) {

        }
    }
}
