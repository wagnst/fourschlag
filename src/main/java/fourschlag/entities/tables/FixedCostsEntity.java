package fourschlag.entities.tables;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;

import java.util.UUID;

public class FixedCostsEntity extends Entity {
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

    public String getSbu() {
        return sbu;
    }

    public String getSubregion() {
        return subregion;
    }

    public double getFixPreManCost() {
        return fixPreManCost;
    }

    public double getShipCost() {
        return shipCost;
    }

    public double getSellCost() {
        return sellCost;
    }

    public double getDiffActPreManCost() {
        return diffActPreManCost;
    }

    public double getIdleEquipCost() {
        return idleEquipCost;
    }

    public double getRdCost() {
        return rdCost;
    }

    public double getAdminCostBu() {
        return adminCostBu;
    }

    public double getAdminCostOd() {
        return adminCostOd;
    }

    public double getAdminCostCompany() {
        return adminCostCompany;
    }

    public double getOtherOpCostBu() {
        return otherOpCostBu;
    }

    public double getOtherOpCostOd() {
        return otherOpCostOd;
    }

    public double getOtherOpCostCompany() {
        return otherOpCostCompany;
    }

    public double getSpecItems() {
        return specItems;
    }

    public double getProvisions() {
        return provisions;
    }

    public double getCurrencyGains() {
        return currencyGains;
    }

    public double getValAdjustInventories() {
        return valAdjustInventories;
    }

    public double getOtherFixCost() {
        return otherFixCost;
    }

    public double getDepreciation() {
        return depreciation;
    }

    public double getCapCost() {
        return capCost;
    }

    public double getEquityIncome() {
        return equityIncome;
    }
}
