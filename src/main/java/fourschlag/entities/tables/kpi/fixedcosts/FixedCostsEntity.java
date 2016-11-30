package fourschlag.entities.tables.kpi.fixedcosts;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import fourschlag.entities.tables.kpi.KpiEntity;

import java.util.UUID;

/**
 * Super class FixedCostsEntity. Extends KpiEntity. Provides the data from the FixedCosts table
 */

public class FixedCostsEntity extends KpiEntity {
    @Column(name = "sbu")
    private String sbu;

    @PartitionKey
    @Column(name = "subregion")
    private String subregion;

    @Column(name = "fix_pre_man_cost")
    private double fixPreManCost;

    @Column(name = "ship_cost")
    private double shipCost;

    @Column(name = "sell_cost")
    private double sellCost;

    @Column(name = "diff_act_pre_man_cost")
    private double diffActPreManCost;

    @Column(name = "idle_equip_cost")
    private double idleEquipCost;

    @Column (name = "rd_cost")
    private double rdCost;

    @Column (name = "admin_cost_bu")
    private double adminCostBu;

    @Column (name = "admin_cost_od")
    private double adminCostOd;

    @Column (name = "admin_cost_company")
    private double adminCostCompany;

    @Column (name = "other_op_cost_bu")
    private double otherOpCostBu;

    @Column (name = "other_op_cost_od")
    private double otherOpCostOd;

    @Column (name = "other_op_cost_company")
    private double otherOpCostCompany;

    @Column (name = "spec_items")
    private double specItems;

    @Column (name = "provisions")
    private double provisions;

    @Column (name = "currency_gains")
    private double currencyGains;

    @Column (name = "val_adjust_inventories")
    private double valAdjustInventories;

    @Column (name = "other_fix_cost")
    private double otherFixCost;

    @Column (name = "depreciation")
    private double depreciation;

    @Column (name = "cap_cost")
    private double capCost;

    @Column (name = "equity_income")
    private double equityIncome;


    public FixedCostsEntity() {
    }


    public FixedCostsEntity(UUID uuid, int period, String region, int periodYear, int periodMonth, String currency, String userId, String entryTs, String sbu, String subregion, double fixPreManCost, double shipCost, double sellCost, double diffActPreManCost, double idleEquipCost, double rdCost, double adminCostBu, double adminCostOd, double adminCostCompany, double otherOpCostBu, double otherOpCostOd, double otherOpCostCompany, double specItems, double provisions, double currencyGains, double valAdjustInventories, double otherFixCost, double depreciation, double capCost, double equityIncome) {
        super(uuid, period, region, periodYear, periodMonth, currency, userId, entryTs);
        this.sbu = sbu;
        this.subregion = subregion;
        this.fixPreManCost = fixPreManCost;
        this.shipCost = shipCost;
        this.sellCost = sellCost;
        this.diffActPreManCost = diffActPreManCost;
        this.idleEquipCost = idleEquipCost;
        this.rdCost = rdCost;
        this.adminCostBu = adminCostBu;
        this.adminCostOd = adminCostOd;
        this.adminCostCompany = adminCostCompany;
        this.otherOpCostBu = otherOpCostBu;
        this.otherOpCostOd = otherOpCostOd;
        this.otherOpCostCompany = otherOpCostCompany;
        this.specItems = specItems;
        this.provisions = provisions;
        this.currencyGains = currencyGains;
        this.valAdjustInventories = valAdjustInventories;
        this.otherFixCost = otherFixCost;
        this.depreciation = depreciation;
        this.capCost = capCost;
        this.equityIncome = equityIncome;
    }

    /**
     * Getter for the SBU
     *
     * @return SBU that is currently used
     */
    public String getSbu() {
        return sbu;
    }

    /**
     * Getter for the Subregion
     *
     * @return Subregion that is currently used
     */
    public String getSubregion() {
        return subregion;
    }

    /**
     * Getter for the FixPreManCost
     *
     * @return FixPreManCost that is currently used
     */
    public double getFixPreManCost() {
        return fixPreManCost;
    }

    /**
     * Getter for the ShipCost
     *
     * @return ShipCost that is currently used
     */
    public double getShipCost() {
        return shipCost;
    }

    /**
     * Getter for the SellCost
     *
     * @return SellCost that are currently used
     */
    public double getSellCost() {
        return sellCost;
    }

    /**
     * Getter for the DiffActPreManCost
     *
     * @return DiffActPreManCost that are currently used
     */
    public double getDiffActPreManCost() {
        return diffActPreManCost;
    }

    /**
     * Getter for the IdleEquipCost
     *
     * @return IdleEquipCost that are currently used
     */
    public double getIdleEquipCost() {
        return idleEquipCost;
    }

    /**
     * Getter for RdCost
     *
     * @return RdCost that are currently used
     */
    public double getRdCost() {
        return rdCost;
    }

    /**
     * Getter for AdminCostBu
     *
     * @return AdminCostBu that is currently used
     */
    public double getAdminCostBu() {
        return adminCostBu;
    }

    /**
     * Getter for AdminCostOd
     *
     * @return AdminCostOd that is currently used
     */
    public double getAdminCostOd() {
        return adminCostOd;
    }

    /**
     * Getter for AdminCostCompany
     *
     * @return AdminCostCompany that are currently used
     */
    public double getAdminCostCompany() {
        return adminCostCompany;
    }

    /**
     * Getter for OtherOpCostBu
     *
     * @return OtherOpCostBu that are currently used
     */
    public double getOtherOpCostBu() {
        return otherOpCostBu;
    }

    /**
     * Getter for OtherOpCostOd
     *
     * @return OtherOpCostOd that are currently used
     */
    public double getOtherOpCostOd() {
        return otherOpCostOd;
    }

    /**
     * Getter for OtherOpCostCompany
     *
     * @return OtherOpCostCompany that are currently used
     */
    public double getOtherOpCostCompany() {
        return otherOpCostCompany;
    }

    /**
     * Getter for SpecItems
     *
     * @return SpecItems that are currently used
     */
    public double getSpecItems() {
        return specItems;
    }

    /**
     * Getter for Provisions
     *
     * @return Provisions that are currently used
     */
    public double getProvisions() {
        return provisions;
    }

    /**
     * Getter for CurrencyGains
     *
     * @return CurrencyGains that are currently used
     */
    public double getCurrencyGains() {
        return currencyGains;
    }

    /**
     * Getter for ValAdjustInventories
     *
     * @return ValAdjustInventories that are currently used
     */
    public double getValAdjustInventories() {
        return valAdjustInventories;
    }

    /**
     * Getter for OtherFixCost
     *
     * @return OtherFixCost that are currently used
     */
    public double getOtherFixCost() {
        return otherFixCost;
    }

    /**
     * Getter for Depreciation
     *
     * @return Depreciation hat is currently used
     */
    public double getDepreciation() {
        return depreciation;
    }

    /**
     * Getter for CapCost
     *
     * @return CapCost that are currently used
     */
    public double getCapCost() {
        return capCost;
    }

    /**
     * Getter for EquityIncome
     *
     * @return EquityIncome that are currently used
     */
    public double getEquityIncome() {
        return equityIncome;
    }
}
