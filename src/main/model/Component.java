package model;

public class Component {
    private String name;
    private Double marksYouGet;
    private Double marksOutOf;
    private Double marksOutOfInPercentage;


    public Component(String name, Double marksOutOfInPercentage) {
        this.name = name;
        this.marksOutOfInPercentage = marksOutOfInPercentage;
    }

    public void setMarksYouGet(Double marksYouGet) {
        this.marksYouGet = marksYouGet;
    }

    public void setMarksOutOf(Double marksOutOf) {
        this.marksOutOf = marksOutOf;
    }

    public String getName() {
        return name;
    }

    public Double getMarksYouGet() {
        return marksYouGet;
    }

    public Double getMarksOutOf() {
        return marksOutOf;
    }

    public Double getMarksOutOfInPercentage() {
        return marksOutOfInPercentage;
    }


}
