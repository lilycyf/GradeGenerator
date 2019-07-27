package model;

import java.util.ArrayList;
import java.util.List;

public class Component {
    private String name;
    private Double percentage;
    private List<UnitWork> unitWorks = new ArrayList<>();

    public Component(String name, Double percentage) {
        this.name = name;
        this.percentage = percentage;
    }

    public void addUnitWork(UnitWork uw){
        unitWorks.add(uw);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

}
