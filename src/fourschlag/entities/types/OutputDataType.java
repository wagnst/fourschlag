package fourschlag.entities.types;

import java.util.LinkedList;

public class OutputDataType {
    private int orderNumber;
    private String kpi;
    private double m01;
    private double m02;
    private double m03;
    private double m04;
    private double m05;
    private double m06;
    private double m07;
    private double m08;
    private double m09;
    private double m10;
    private double m11;
    private double m12;
    private double m13;
    private double m14;
    private double m15;
    private double m16;
    private double m17;
    private double m18;
    private double bj2;
    private double bj3;
    private double bj4;
    private String region;
    private String subregion;
    private String sbu;
    private String entryType;
    private String productMainGroup;
    private String salesType;
    private String currency;
    private String unit;
    private String fcType;

    public OutputDataType(KeyPerformanceIndicators kpi) {
        this.kpi = kpi.getFullName();
        this.orderNumber = kpi.getOrderNumber();
        this.fcType = kpi.getFcType();
    }

    public OutputDataType(KeyPerformanceIndicators kpi, String sbu, String productMainGroup, String region, String subregion,
                          String salesType, String entryType, String currency, LinkedList<Double> months) {
        this.kpi = kpi.getFullName();
        this.orderNumber = kpi.getOrderNumber();
        this.fcType = kpi.getFcType();
        this.sbu = sbu;
        this.productMainGroup = productMainGroup;
        this.region = region;
        this.subregion = subregion;
        this.salesType = salesType;
        this.entryType = entryType;
        this.currency = currency;
        this.unit = convertUnitCurrency(kpi.getUnit(), currency);
        this.setMonths(months);
    }

    private void setMonths(LinkedList<Double> months) {
        this.m01 = months.poll();
        this.m02 = months.poll();
        this.m03 = months.poll();
        this.m04 = months.poll();
        this.m05 = months.poll();
        this.m06 = months.poll();
        this.m07 = months.poll();
        this.m08 = months.poll();
        this.m09 = months.poll();
        this.m10 = months.poll();
        this.m11 = months.poll();
        this.m12 = months.poll();
        this.m13 = months.poll();
        this.m14 = months.poll();
        this.m15 = months.poll();
        this.m16 = months.poll();
        this.m17 = months.poll();
        this.m18 = months.poll();
    }

    private String convertUnitCurrency(String unit, String currency) {
        if (currency.equals("eur")) {
            return unit;
        } else if (unit.indexOf('€') == -1) {
            return unit;
        } else if (currency.equals("usd")) {
            return unit.replace("€", "$");
        } else {
            return unit.replace("€", currency);
        }
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getKpi() {
        return kpi;
    }

    public double getM01() {
        return m01;
    }

    public void setM01(double m01) {
        this.m01 = m01;
    }

    public double getM02() {
        return m02;
    }

    public void setM02(double m02) {
        this.m02 = m02;
    }

    public double getM03() {
        return m03;
    }

    public void setM03(double m03) {
        this.m03 = m03;
    }

    public double getM04() {
        return m04;
    }

    public void setM04(double m04) {
        this.m04 = m04;
    }

    public double getM05() {
        return m05;
    }

    public void setM05(double m05) {
        this.m05 = m05;
    }

    public double getM06() {
        return m06;
    }

    public void setM06(double m06) {
        this.m06 = m06;
    }

    public double getM07() {
        return m07;
    }

    public void setM07(double m07) {
        this.m07 = m07;
    }

    public double getM08() {
        return m08;
    }

    public void setM08(double m08) {
        this.m08 = m08;
    }

    public double getM09() {
        return m09;
    }

    public void setM09(double m09) {
        this.m09 = m09;
    }

    public double getM10() {
        return m10;
    }

    public void setM10(double m10) {
        this.m10 = m10;
    }

    public double getM11() {
        return m11;
    }

    public void setM11(double m11) {
        this.m11 = m11;
    }

    public double getM12() {
        return m12;
    }

    public void setM12(double m12) {
        this.m12 = m12;
    }

    public double getM13() {
        return m13;
    }

    public void setM13(double m13) {
        this.m13 = m13;
    }

    public double getM14() {
        return m14;
    }

    public void setM14(double m14) {
        this.m14 = m14;
    }

    public double getM15() {
        return m15;
    }

    public void setM15(double m15) {
        this.m15 = m15;
    }

    public double getM16() {
        return m16;
    }

    public void setM16(double m16) {
        this.m16 = m16;
    }

    public double getM17() {
        return m17;
    }

    public void setM17(double m17) {
        this.m17 = m17;
    }

    public double getM18() {
        return m18;
    }

    public void setM18(double m18) {
        this.m18 = m18;
    }

    public double getBj2() {
        return bj2;
    }

    public void setBj2(double bj2) {
        this.bj2 = bj2;
    }

    public double getBj3() {
        return bj3;
    }

    public void setBj3(double bj3) {
        this.bj3 = bj3;
    }

    public double getBj4() {
        return bj4;
    }

    public void setBj4(double bj4) {
        this.bj4 = bj4;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
        this.subregion = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public String getSbu() {
        return sbu;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public String getProductMainGroup() {
        return productMainGroup;
    }

    public String getSalesType() {
        return salesType;
    }

    public void setSalesType(String salesType) {
        this.salesType = salesType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFcType() {
        return fcType;
    }

    public void setRegion(String region, String subregion) {
        this.region = region;
        this.subregion = subregion;
    }

    //Setter for Product Main Group and SBU if Product Main Group is existent
    public void setProductMainAndSBU(String sbu, String product_main_group) {
        this.sbu = sbu;
        this.productMainGroup = product_main_group;
    }

    //Setter for Product Main Group and SBU if Product Main Group is NOT existent
    public void setProductMainAndSBU(String sbu) {
        this.sbu = sbu;
        this.productMainGroup = sbu;
    }

    @Override
    public String toString() {
        return "OutputDataType{" +
                "orderNumber=" + orderNumber +
                ", kpi='" + kpi + '\'' +
                ", m01=" + m01 +
                ", m02=" + m02 +
                ", m03=" + m03 +
                ", m04=" + m04 +
                ", m05=" + m05 +
                ", m06=" + m06 +
                ", m07=" + m07 +
                ", m08=" + m08 +
                ", m09=" + m09 +
                ", m10=" + m10 +
                ", m11=" + m11 +
                ", m12=" + m12 +
                ", m13=" + m13 +
                ", m14=" + m14 +
                ", m15=" + m15 +
                ", m16=" + m16 +
                ", m17=" + m17 +
                ", m18=" + m18 +
                ", bj2=" + bj2 +
                ", bj3=" + bj3 +
                ", bj4=" + bj4 +
                ", region='" + region + '\'' +
                ", subregion='" + subregion + '\'' +
                ", sbu='" + sbu + '\'' +
                ", entryType=" + entryType +
                ", productMainGroup='" + productMainGroup + '\'' +
                ", salesType=" + salesType +
                ", currency='" + currency + '\'' +
                ", unit='" + unit + '\'' +
                ", fcType='" + fcType + '\'' +
                '}';
    }
}