package fourschlag.services.data.requests;

import com.datastax.driver.mapping.Result;
import fourschlag.entities.accessors.fixedcosts.ActualFixedCostsAccessor;
import fourschlag.entities.accessors.fixedcosts.ForecastFixedCostsAccessor;
import fourschlag.entities.tables.kpi.fixedcosts.ActualFixedCostsEntity;
import fourschlag.entities.tables.kpi.fixedcosts.FixedCostsEntity;
import fourschlag.entities.tables.kpi.fixedcosts.ForecastFixedCostsEntity;
import fourschlag.entities.types.EntryType;
import fourschlag.entities.types.OutputDataType;
import fourschlag.entities.types.Period;
import fourschlag.services.db.CassandraConnection;

import java.util.*;

public class FixedCostsRequest extends Request {

    private final ActualFixedCostsAccessor actualAccessor;
    private final ForecastFixedCostsAccessor forecastAccessor;
    private Map<String, Set<String>> sbuMap;

    public FixedCostsRequest(CassandraConnection connection) {
        super(connection);
        actualAccessor = getManager().createAccessor(ActualFixedCostsAccessor.class);
        forecastAccessor = getManager().createAccessor(ForecastFixedCostsAccessor.class);
    }

    public boolean setForecastFixedCosts(String sbu, String subregion, double fixPreManCost, double shipCost, double sellCost, double diffActPreManCost,
                                         double idleEquipCost, double rdCost, double adminCostBu, double adminCostOd, double adminCostCompany, double otherOpCostBu, double otherOpCostOd,
                                         double otherOpCostCompany, double specItems, double provisions, double currencyGains, double valAdjustInventories, double otherFixCost,
                                         double deprecation, double capCost, double equitiyIncome, double topdownAdjustFixCosts, int planPeriod, int planYear, int planHalfYear, int planQuarter,
                                         int planMonth, String status, String usercomment, String entryType, int period, String region, int periodYear, int periodMonth, String currency,
                                         String userId, String entryTs) {
        try {
            if (forecastAccessor.getForecastFixedCost(sbu, subregion, period, planPeriod, entryType) != null) {
                // update an existing record
                forecastAccessor.updateForecastFixedCost(sbu, subregion, fixPreManCost, shipCost, sellCost, diffActPreManCost, idleEquipCost, rdCost, adminCostBu, adminCostOd, adminCostCompany, otherOpCostBu,
                        otherOpCostOd, otherOpCostCompany, specItems, provisions, currencyGains, valAdjustInventories, otherFixCost, deprecation, capCost, equitiyIncome, topdownAdjustFixCosts, planPeriod,
                        planYear, planHalfYear, planQuarter, planMonth, status, usercomment, entryType, period, region, periodYear, periodMonth, currency, userId, entryTs);
            } else {
                forecastAccessor.setForecastFixedCost(sbu, subregion, fixPreManCost, shipCost, sellCost, diffActPreManCost, idleEquipCost, rdCost, adminCostBu, adminCostOd, adminCostCompany, otherOpCostBu,
                        otherOpCostOd, otherOpCostCompany, specItems, provisions, currencyGains, valAdjustInventories, otherFixCost, deprecation, capCost, equitiyIncome, topdownAdjustFixCosts, planPeriod,
                        planYear, planHalfYear, planQuarter, planMonth, status, usercomment, entryType, period, region, periodYear, periodMonth, currency, userId, entryTs);
            }
        } catch (Exception e) {
            //TODO: implement better exception to be catched
            return false;
        }

        return true;
    }

    /**
     * Gets all ForecastFixedCostsEntities with no filter applied
     *
     * @return all entities which are present inside forecast_fixed_costs
     */
    public List<ForecastFixedCostsEntity> getForecastFixedCosts() {
        return forecastAccessor.getAllForecastFixedCosts().all();
    }

    /**
     * Gets a specific ForecastFixedCostsEntity filtered by joined primary keys
     *
     * @return single entity of ForeCastFixedCostsEntity
     */
    public ForecastFixedCostsEntity getForecastFixedCosts(String sbu, String subregion, Period period, EntryType entryType, Period planPeriod) {
        return forecastAccessor.getForecastFixedCost(sbu, subregion, period.getPeriod(), planPeriod.getPeriod(), entryType.getType()).one();
    }

    /**
     * Gets specific ForecastFixedCostsEntites filtered by senseful drill down
     * parameters
     *
     * @return a list of entities which are present inside forecast_fixed_costs
     */
    public List<ForecastFixedCostsEntity> getForecastFixedCosts(String subregion, String sbu, Period period, EntryType entryType, Period planPeriodFrom, Period planPeriodTo) {
        List<ForecastFixedCostsEntity> resultList = new ArrayList<>();

        if (entryType == EntryType.BUDGET) {
            /* in case we have budget as entry type we need to query all months seperately and append to list */
            for (int i = 0; i < OutputDataType.getNumberOfMonths(); i++) {
                //simply use planPeriodFrom as planPeriod instead of writing a new method
                resultList.addAll(forecastAccessor.getForecastFixedCost(sbu, subregion, planPeriodFrom.getPeriod(), entryType.getType()).all());
                //increment period to fetch all months
                planPeriodFrom.increment();
            }
        } else {
            /* all other entry types */
            resultList.addAll(forecastAccessor.getForecastFixedCost(subregion, sbu, period.getPeriod(),
                    entryType.getType(), planPeriodFrom.getPeriod(), planPeriodTo.getPeriod()).all());
        }

        return resultList;
    }

    //TODO: implement method for non-forecast related tables

    public Map<String, Set<String>> getSubregionsAndSbu() {
        if (sbuMap == null) {
            querySubregionsAndSbu();
        }
        return sbuMap;
    }

    private void querySubregionsAndSbu() {
        Result<ActualFixedCostsEntity> entitiesFromActual = actualAccessor.getDistinctSbuAndSubregions();
        Result<ForecastFixedCostsEntity> entitiesFromForecast = forecastAccessor.getDistinctSbuAndSubregions();
        sbuMap = new HashMap<>();

        for (ActualFixedCostsEntity entity : entitiesFromActual) {
            addToSbuMap(entity);
        }

        for (ForecastFixedCostsEntity entity : entitiesFromForecast) {
            addToSbuMap(entity);
        }
    }

    private void addToSbuMap(FixedCostsEntity entity) {
        if (sbuMap.containsKey(entity.getSbu())) {
            sbuMap.get(entity.getSbu()).add(entity.getSubregion());
        } else {
            sbuMap.put(entity.getSbu(), new HashSet<String>() {{
                add(entity.getSubregion());
            }});
        }
    }
}