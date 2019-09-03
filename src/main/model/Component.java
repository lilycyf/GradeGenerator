package model;

public class Component {
    private String name;
    private Double marksYouGet;
    private Double marksOutOf;
    private Double marksOutOfInPercentage;

    public void setMarksYouGet(Double marksYouGet) {
        this.marksYouGet = marksYouGet;
    }

    public void setMarksOutOf(Double marksOutOf) {
        this.marksOutOf = marksOutOf;
    }

    public Double getMarksYouGet() {
        return marksYouGet;
    }

    public Double getMarksOutOf() {
        return marksOutOf;
    }

    public Component(String name, Double marksOutOfInPercentage) {
        this.name = name;
        this.marksOutOfInPercentage = marksOutOfInPercentage;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMarksOutOfInPercentage() {
        return marksOutOfInPercentage;
    }

    public void setMarksOutOfInPercentage(Double marksOutOfInPercentage) {
        this.marksOutOfInPercentage = marksOutOfInPercentage;
    }

}
