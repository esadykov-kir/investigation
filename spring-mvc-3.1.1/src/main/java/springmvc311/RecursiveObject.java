package springmvc311;

public class RecursiveObject {

    private RecursiveObject ro;
    private String something = "ro";

    public RecursiveObject getRo() {
        return ro;
    }

    public void setRo(RecursiveObject ro) {
        this.ro = ro;
    }

    public String getSomething() {
        return something;
    }

    public void setSomething(String something) {
        this.something = something;
    }
}
