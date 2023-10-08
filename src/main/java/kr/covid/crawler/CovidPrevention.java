package kr.covid.crawler;

public class CovidPrevention {
    private String region;
    private int firstweeklynew;
    private int firstaccrue;
    private String firstpercent;
    private int secondweeklynew;
    private int secondaccrue;
    private String secondpercent;

    public CovidPrevention() {
    }

    public CovidPrevention(String region, int firstweeklynew, int firstaccrue, String firstpercent, int secondweeklynew, int secondaccrue, String secondpercent) {
        this.region = region;
        this.firstweeklynew = firstweeklynew;
        this.firstaccrue = firstaccrue;
        this.firstpercent = firstpercent;
        this.secondweeklynew = secondweeklynew;
        this.secondaccrue = secondaccrue;
        this.secondpercent = secondpercent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getFirstweeklynew() {
        return firstweeklynew;
    }

    public void setFirstweeklynew(int firstweeklynew) {
        this.firstweeklynew = firstweeklynew;
    }

    public int getFirstaccrue() {
        return firstaccrue;
    }

    public void setFirstaccrue(int firstaccrue) {
        this.firstaccrue = firstaccrue;
    }

    public String getFirstpercent() {
        return firstpercent;
    }

    public void setFirstpercent(String firstpercent) {
        this.firstpercent = firstpercent;
    }

    public int getSecondweeklynew() {
        return secondweeklynew;
    }

    public void setSecondweeklynew(int secondweeklynew) {
        this.secondweeklynew = secondweeklynew;
    }

    public int getSecondaccrue() {
        return secondaccrue;
    }

    public void setSecondaccrue(int secondaccrue) {
        this.secondaccrue = secondaccrue;
    }

    public String getSecondpercent() {
        return secondpercent;
    }

    public void setSecondpercent(String secondpercent) {
        this.secondpercent = secondpercent;
    }

    @Override
    public String toString() {
        return "CovidPrevention{" +
                "region='" + region + '\'' +
                ", firstweeklynew=" + firstweeklynew +
                ", firstaccrue=" + firstaccrue +
                ", firstpercent='" + firstpercent + '\'' +
                ", secondweeklynew=" + secondweeklynew +
                ", secondaccrue=" + secondaccrue +
                ", secondpercent='" + secondpercent + '\'' +
                '}';
    }
}
