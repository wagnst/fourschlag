package fourschlag.services.data.requests;

import fourschlag.entities.accessors.ActualSalesAccessor;
import fourschlag.entities.accessors.ForecastSalesAccessor;
import fourschlag.entities.tables.Entity;
import fourschlag.entities.tables.SalesEntity;
import fourschlag.entities.types.*;
import fourschlag.services.db.CassandraConnection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static fourschlag.entities.types.KeyPerformanceIndicators.*;

/**
 * Extends Request. Offers Functionality to request Sales KPIs for a specific
 * region, period and product main group
 */
public class SalesRequest extends KpiRequest {

    private final String productMainGroup;
    private final SalesType salesType;
    private final ActualSalesAccessor actualAccessor;
    private final ForecastSalesAccessor forecastAccessor;
    private static final String FC_TYPE = "sales";

    /**
     * Constructor for SalesRequest
     *
     * @param connection       Cassandra connection that is supposed to be used
     * @param productMainGroup Product Main Group to filter for
     * @param planYear         Indicates the time span for which the KPIs are
     *                         supposed to be queried
     * @param currentPeriod    The point of view in time from which the data is
     *                         supposed to be looked at
     * @param region           Region to filter for
     * @param salesType        Sales Type to filter for
     * @param exchangeRates    Desired output currency
     */
    public SalesRequest(CassandraConnection connection, String productMainGroup, int planYear, Period currentPeriod,
                        String region, SalesType salesType, ExchangeRateRequest exchangeRates,
                        OrgStructureAndRegionRequest orgAndRegionRequest) {
        super(connection, orgAndRegionRequest.getSbu(productMainGroup), region, planYear , currentPeriod, exchangeRates, FC_TYPE);
        this.productMainGroup = productMainGroup;
        this.salesType = salesType;

        /* Create needed accessors to be able to do queries */
        actualAccessor = getManager().createAccessor(ActualSalesAccessor.class);
        forecastAccessor = getManager().createAccessor(ForecastSalesAccessor.class);
    }

    /**
     * Queries KPIs from the actual sales table
     *
     * @return SalesEntity Object with query result
     */
    @Override
    protected SalesEntity getActualData(Period tempPlanPeriod) {
        /* Set this flag to true, so the entry type can be set correctly later */
        actualFlag = true;
        /* Send query to the database with data source BW B */
        SalesEntity queryResult = actualAccessor.getSalesKPIs(productMainGroup, tempPlanPeriod.getPeriod(), region,
                salesType.getType(), DataSource.BW_B.toString());

        /* IF result is empty THEN query again with data source BW A */
        if (queryResult == null) {
            queryResult = actualAccessor.getSalesKPIs(productMainGroup, tempPlanPeriod.getPeriod(), region,
                    salesType.getType(), DataSource.BW_A.toString());
            /* IF result is NOT empty THEN get cm1 value from forecast data and put it in the query result */
            if (queryResult != null) {
                queryResult.setCm1(getForecastCm1(tempPlanPeriod));
            }
        }
        return queryResult;
    }

    /**
     * Queries KPIs from the forecast sales table
     *
     * @return SalesEntity Object with query result
     */
    @Override
    protected SalesEntity getForecastData(Period tempPlanPeriod, EntryType entryType) {
        /* Set this flag to true, so the entry type can be set correctly later */
        forecastFlag = true;
        return forecastAccessor.getSalesKpis(productMainGroup, currentPeriod.getPeriod(),
                tempPlanPeriod.getPeriod(), region, salesType.toString(), entryType.toString());
    }

    /**
     * Gets the cm1 value from the forecast sales table
     *
     * @return double value of cm1
     */
    private double getForecastCm1(Period tempPlanPeriod) {
        /* Set this flag to true, so the entry type can be set correctly later */
        forecastFlag = true;
        /* TODO: Currency conversion */
        SalesEntity cm1 = forecastAccessor.getCm1(productMainGroup, currentPeriod.getPeriod(),
                tempPlanPeriod.getPeriod(), region, salesType.toString());

        if (cm1 == null) {
            return 0;
        }
        return cm1.getCm1();
    }

    @Override
    protected SalesEntity getBudgetData(Period tempPlanPeriod) {
        return forecastAccessor.getSalesKpis(productMainGroup, tempPlanPeriod.getPeriod(), tempPlanPeriod.getPeriod(),
                region, salesType.toString(), EntryType.BUDGET.toString());
    }

    /**
     * Private method that calculates the BJ values for all KPIs but one specific period (--> zero month period)
     *
     * @param zeroMonthPeriod ZeroMonthPeriod of the desired budget year
     */
    @Override
    protected Map<KeyPerformanceIndicators, Double> calculateBj(Period zeroMonthPeriod) {
        SalesEntity queryResult = forecastAccessor.getSalesKpis(productMainGroup, currentPeriod.getPeriod(),
                zeroMonthPeriod.getPeriod(), region, salesType.toString(), EntryType.BUDGET.getType());

        return validateQueryResult(queryResult, new Period(zeroMonthPeriod));
    }

    /**
     * Private method to validate a query result.
     *
     * @param result    The query result that will be validated
     * @param tempPlanPeriod planPeriod of that query result
     * @return Map with all the values for the sales KPIs
     */
    @Override
    protected Map<KeyPerformanceIndicators, Double> validateQueryResult(Entity result, Period tempPlanPeriod) {
        SalesEntity queryResult = (SalesEntity)result;
        /* Prepare the kpi variables */
        Map<KeyPerformanceIndicators, Double> resultMap = new HashMap<KeyPerformanceIndicators, Double>(){{
            Arrays.stream(kpiArray)
                    .forEach(kpi -> put(kpi, 0.0));
        }};

        /* IF the result of the query is empty THEN set these KPIs to 0
         * ELSE get the values from the query result
         */
        if (queryResult == null) {
        } else {
            resultMap.put(SALES_VOLUME, queryResult.getSalesVolumes());
            resultMap.put(NET_SALES, queryResult.getNetSales());
            resultMap.put(CM1, queryResult.getCm1());

            /* IF the currency of the KPIs is not the desired one THEN get the exchange rate and convert them */
            if (queryResult.getCurrency().equals(exchangeRates.getToCurrency()) == false) {
                double exchangeRate = exchangeRates.getExchangeRate(tempPlanPeriod, queryResult.getCurrency());

                for (KeyPerformanceIndicators kpi : resultMap.keySet()) {
                    resultMap.put(kpi, resultMap.get(kpi) * exchangeRate);
                }
            }
        }

         /* IF sales volume is not 0 THEN calculate these other KPIs */
        if (resultMap.get(SALES_VOLUME) != 0) {
            resultMap.put(PRICE, resultMap.get(NET_SALES) / resultMap.get(SALES_VOLUME) * 1000);
            resultMap.put(VAR_COSTS, (resultMap.get(NET_SALES) - resultMap.get(CM1)) * 1000 / resultMap.get(SALES_VOLUME));
            resultMap.put(CM1_SPECIFIC, resultMap.get(CM1) / resultMap.get(SALES_VOLUME) * 1000);
        }

        /* IF net sales is not 0 THEN calculate these other KPIs  */
        if (resultMap.get(NET_SALES) != 0) {
            resultMap.put(CM1_PERCENT, resultMap.get(CM1) / resultMap.get(NET_SALES));
        }

        return resultMap;
    }

    /**
     * Creates a OutputDataType Object with all given attributes
     *
     * @param kpi           KPI that is supposed to be set in the
     *                      OutputDataType
     * @param monthlyValues The monthly values for the KPI
     * @return OutputDataType object
     */
    @Override
    protected OutputDataType createOutputDataType(KeyPerformanceIndicators kpi, EntryType entryType,
                                                LinkedList<Double> monthlyValues, LinkedList<Double> bjValues) {
        return new OutputDataType(kpi, sbu, productMainGroup,
                region, region, salesType.toString(), entryType.toString(), exchangeRates.getToCurrency(), monthlyValues,
                bjValues);
    }

}
