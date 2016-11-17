package fourschlag.entities.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import fourschlag.entities.tables.ExchangeRateEntity;

@Accessor
public interface ExchangeRateAccessor {
    @Query("SELECT period, from_currency, to_currency, rate FROM exchange_rate WHERE period = :period AND to_currency = :to_currency ALLOW FILTERING")
    Result<ExchangeRateEntity> getExchangeRate(@Param("period") int period, @Param("to_currency") String toCurrency);

    @Query("SELECT rate FROM exchange_rate WHERE period = :period AND from_currency = :from_currency AND to_currency = :to_currency ALLOW FILTERING")
    ExchangeRateEntity getSpecificExchangeRate(@Param("period") int period,
                                               @Param("from_currency") String fromCurrency,
                                               @Param("to_currency") String toCurrency);
}