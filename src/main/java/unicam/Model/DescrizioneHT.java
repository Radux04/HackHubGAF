package unicam.Model;

public class DescrizioneHT {
    private String regolamento;
    private float premio;
    private int maxSize;

    public DescrizioneHT(String regolamento, float premio, int maxSize) {
        this.regolamento = regolamento;
        this.premio = premio;
        this.maxSize = maxSize;
    }

    public String getRegolamento() {
        return regolamento;
    }

    public float getPremio() {
        return premio;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setRegolamento(String regolamento) {
        this.regolamento = regolamento;
    }

    public void setPremio(float premio) {
        this.premio = premio;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
}
