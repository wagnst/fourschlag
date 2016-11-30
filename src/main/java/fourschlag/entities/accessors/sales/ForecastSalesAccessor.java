package fourschlag.entities.accessors.sales;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import fourschlag.entities.tables.kpi.sales.ForecastSalesEntity;

/**
 * Provides functionality to query the database for the ForecastSales
 */

@Accessor
public interface ForecastSalesAccessor {
    /*CQL-Query to get the ForecastSales data */
    @Query("SELECT sales_volumes, net_sales, cm1, topdown_adjust_sales_volumes, topdown_adjust_net_sales, topdown_adjust_cm1, currency FROM forecast_sales WHERE product_main_group = :product_main_group AND period = :period AND plan_period = :plan_period AND region = :region AND sales_type = :sales_type AND entry_type = :entry_type ALLOW FILTERING;")
    ForecastSalesEntity getSalesKpis(
            @Param("product_main_group") String productMainGroup,
            @Param("period") int period,
            @Param("plan_period") int planPeriod,
            @Param("region") String region,
            @Param("sales_type") String salesType,
            @Param("entry_type") String entryType);
    /*CQL-Query to get the ForecastSales data */
    @Query("SELECT cm1, topdown_adjust_cm1, currency FROM forecast_sales WHERE product_main_group = :product_main_group AND period = :period AND plan_period = :plan_period AND region = :region AND sales_type = :sales_type AND entry_type = 'forecast' ALLOW FILTERING;")
    ForecastSalesEntity getCm1(
            @Param("product_main_group") String productMainGroup,
            @Param("period") int period,
            @Param("plan_period") int planPeriod,
            @Param("region") String region,
            @Param("sales_type") String salesType);
    /*CQL-Query to get the ForecastSales Product Main Group and Region */
    @Query("SELECT DISTINCT product_main_group, region FROM forecast_sales;")
    Result<ForecastSalesEntity> getDistinctPmgAndRegions();
}
