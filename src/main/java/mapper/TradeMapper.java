package mapper;

import dto.PaymentDto;
import dto.TradeDto;
import model.Payment;
import model.Trade;
import org.mapstruct.Mapper;

@Mapper
public interface TradeMapper {

    TradeDto tradeToTradeDto(Trade trade);

    Trade tradeDtoToTrade(TradeDto tradeDto);
}
