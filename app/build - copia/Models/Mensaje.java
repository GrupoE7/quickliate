package Models;

public class Mensaje {
    private String Hum;

    public Mensaje(){

    }
    public Mensaje(String hum){
        this.Hum =hum;

    }

    public String getHum() {
        return Hum;
    }

    public void setHum(String hum) {
        Hum = hum;
    }
}
