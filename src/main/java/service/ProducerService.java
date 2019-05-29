package service;

import convert.Mapper;
import dto.CountryDto;
import dto.ErrorsEnumDto;
import dto.ProducerDto;
import dto.TradeDto;
import exceptions.MyException;
import lombok.RequiredArgsConstructor;
import model.Country;
import model.Errors;
import model.Producer;
import model.Trade;
import org.apache.commons.lang3.StringUtils;
import repositories.CountryRepository;
import repositories.ProducerRepository;
import repositories.TradeRepository;
import repositories.impl.CountryRepositoryImpl;
import repositories.impl.ProducerRepositoryImpl;
import repositories.impl.TradeRepositoryImpl;
import utils.UtilsMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class ProducerService {

    private final ProducerRepositoryImpl producerRepository;
    private final CountryRepositoryImpl countryRepository;
    private final TradeRepositoryImpl tradeRepository;

    private static Logger LOGGER = Logger.getLogger("ProducerService");

    public void addProducer() {
        LOGGER.info("Start operation addProducer()");

        Country country = null;
        Trade trade = null;

        ProducerDto producerDto = new ProducerDto();
        CountryDto countryDto = new CountryDto();
        TradeDto tradeDto = new TradeDto();

        enterProducerData(producerDto);
        validateProducer(producerDto);

        country = countryRepository.findByNameCountry(producerDto.getCountry().getName());
        trade = tradeRepository.findByNameTrade(producerDto.getTrade().getName());

        if (country == null) {
            country = new Country(producerDto.getCountry().getName());
//            countryRepository.saveOrUpdate(country);


        }

        if (trade == null) {
            trade = new Trade(producerDto.getTrade().getName());
//            tradeRepository.saveOrUpdate(trade);
        }

        producerDto.setCountry(Mapper.fromCountryToCountryDto(country));
        producerDto.setTrade(Mapper.fromTradeToTradeDto(trade));

        Producer producer = Mapper.fromProducerDtoToProducer(producerDto);

//        TODO before will uou save data check name producer and country and trade isn't exists

        producerRepository.saveOrUpdate(producer);
        System.out.println(producerDto);
        LOGGER.info("End operation addProducer()");
    }

    private void enterProducerData(ProducerDto producerDto) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter producer name: ");
        producerDto.setName(sc.nextLine());
        System.out.println("Enter country name: ");
        String countryName = sc.nextLine();
        producerDto.setCountry(Mapper.fromCountryToCountryDto(new Country(countryName)));
        System.out.println("Enter producer's industry type: ");
        String tradeName = sc.nextLine();
        producerDto.setTrade(Mapper.fromTradeToTradeDto(new Trade(tradeName)));
    }

    private void validateProducer(ProducerDto producerDto) {

        List<Errors> lists = new ArrayList<>();

        Errors producerError = new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCER.name() + " - add producer");
        if (producerDto == null) {
            Errors producerEmpty = new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCER.name() + " - producer objectis empty");
            lists.add(producerEmpty);
            ErrorService.saveError(producerError);
            throw new MyException("Producer data are incorrect", lists);
        }

        if (StringUtils.isEmpty(producerDto.getName()) || !ValidateService.nameIsCorrect(producerDto.getName())) {
            lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCER.name() + " - producer name is incorrect"));
        }

        if (StringUtils.isEmpty(producerDto.getCountry().getName()) || !ValidateService.nameIsCorrect(producerDto.getCountry().getName())) {
            lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCER.name() + " - country is incorrect"));
        }

        if (StringUtils.isEmpty(producerDto.getTrade().getName()) || !ValidateService.nameIsCorrect(producerDto.getTrade().getName())) {
            lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCER.name() + " - trade is incorrect"));
        }

        if (!lists.isEmpty()) {
            ErrorService.saveError(producerError);

            lists.stream().forEach(item -> LOGGER.warning(item.getDate() + " " + item.getMessage()));
            throw new MyException("Add producer data are incorrect", lists);
        }
    }
}
