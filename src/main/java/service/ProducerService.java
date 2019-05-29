package service;

import lombok.RequiredArgsConstructor;
import repositories.CountryRepository;
import repositories.ProducerRepository;

import java.util.logging.Logger;

@RequiredArgsConstructor
public class ProducerService {

    private final ProducerRepository producerRepository;
    private final CountryRepository countryRepository;

    private static Logger LOGGER = Logger.getLogger("ProducerService");

    public void addProducer() {

    }

}
