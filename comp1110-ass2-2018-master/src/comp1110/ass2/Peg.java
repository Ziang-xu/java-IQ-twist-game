package comp1110.ass2;

 // different types of pegs - by Yang Kong(u6275465)

public enum Peg {
    i("red"),
    j("blue"),
    k("green"),
    l("yellow");

    private String color;

    Peg(String color){
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }
}
