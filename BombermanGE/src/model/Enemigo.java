package model;

public class Enemigo {
    private int i, j;
    private boolean vivo;
    
    public Enemigo(int i, int j) {
        this.i = i;
        this.j = j;
        this.vivo = true;
    }

    public void morir() {
        this.vivo = false;
    }

    public boolean isVivo() {
        return vivo;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
