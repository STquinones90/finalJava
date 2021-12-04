package ar.edu.unlam.pb1.finalAgosto;

public class Llamada {

  private boolean fueExitosa;
  private String observaciones;

  public Llamada(boolean fueExitosa, String observaciones) {
    this.setFueExitosa(fueExitosa);
    this.setObservaciones(observaciones);
  }

  public boolean isFueExitosa() {
    return fueExitosa;
  }

  public void setFueExitosa(boolean fueExitosa) {
    this.fueExitosa = fueExitosa;
  }

  public String getObservaciones() {
    return observaciones;
  }

  public void setObservaciones(String observaciones) {
    this.observaciones = observaciones;
  }

  @Override
  public String toString() {
    return "Llamada{" +
        "fueExitosa=" + fueExitosa +
        ", observaciones='" + observaciones + '\'' +
        '}';
  }
}
