package ar.edu.unlam.pb1.finalAgosto;

import java.util.ArrayList;
import java.util.List;

public class Contacto {

  /*
   * Se deben incorporar los atributos necesarios.
   *
   * �	Nombre y Apellido: No se aceptan n�meros.
   * �	Celular: Compuesto del c�digo de pa�s + c�digo de �rea + n�mero de celular.
   * �	E-Mail: Debe contener al menos el s�mbolo �@� y el caracter �.�.
   * �	Direcci�n: Valor alfanum�rico.
   * �	C�digo Postal: Valor num�rico.
   * �	Localidad: Valor alfanum�rico.
   * �	Provincia. Enumerador que contenga las 23 provincias argentinas.
   * �	Es cliente (Si o No): Inicialmente se carga en �No�.
   * �	Desea ser llamado nuevamente (Si o No): Inicialmente se carga en �Si�.
   */

  private String nombre;
  private String apellido;
  private String celular;
  private String email;
  private String direccion;
  private Integer codigoPostal;
  private String localidad;
  private Provincia provincia;
  private boolean esCliente;
  private boolean puedeLlamarse;
  private List<Llamada> llamadas;

  public Contacto(String nombre, String apellido, String celular, String direccion,
      Integer codigoPostal, String localidad,
      Provincia provincia) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.celular = celular;
    this.direccion = direccion;
    this.codigoPostal = codigoPostal;
    this.localidad = localidad;
    this.provincia = provincia;
    this.esCliente = false;
    this.puedeLlamarse = true;
    this.llamadas = new ArrayList<>();
  }

  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public String getCelular() {
    return celular;
  }

  public String getEmail() {
    return email;
  }

  public String getDireccion() {
    return direccion;
  }

  public Integer getCodigoPostal() {
    return codigoPostal;
  }

  public String getLocalidad() {
    return localidad;
  }

  public Provincia getProvincia() {
    return provincia;
  }

  public boolean esCliente() {
    return esCliente;
  }

  public boolean puedeLlamarse() {
    return puedeLlamarse;
  }

  public List<Llamada> getLlamadas() {
    return llamadas;
  }

  /**
   * Eval�a si un String determinado puede ser almacenado como E-MAIL. Se asume que si es valido lo
   * guarda en la variable email.
   *
   * @param eMail
   */
  public void esEmailValido(String eMail) {
    if (eMail != null && !eMail.trim().isBlank() && eMail.contains("@") && eMail.contains(".")) {
      this.email = eMail;
    } else {
      System.out.println(
          "El valor ingresado no es valido - Debe contener al menos el s�mbolo �@� y el caracter �.�.");
    }
  }

  /**
   * Registra una nueva llamada asociada al contacto actual.
   *
   * @param nueva La llamada nueva a guardarse.
   * @param puedeLlamarse En caso que no fuera una llamada exitosa, se consulta si se puede llamar de vuelta o no.
   * @return Si se pudo registrar o no la llamada.
   */
  public boolean registrarNuevaLlamada(Llamada nueva, boolean puedeLlamarse) {
    if (nueva != null) {
      if (!this.esCliente && nueva.isFueExitosa()) {
        this.esCliente = true;
      }
      if (this.puedeLlamarse) {
        this.puedeLlamarse = puedeLlamarse;
      }

      return this.llamadas.add(nueva);
    }
    return false;
  }

  /**
   * Muestra los datos de un contacto, entre los que se debe incluir el registro de las llamadas realizadas.
   *
   * @return Un String con los datos del usuario
   */
  @Override
  public String toString() {
    return "Contacto{" +
        "nombre='" + nombre + '\'' +
        ", apellido='" + apellido + '\'' +
        ", celular='" + celular + '\'' +
        ", email='" + email + '\'' +
        ", direccion='" + direccion + '\'' +
        ", codigoPostal=" + codigoPostal +
        ", localidad='" + localidad + '\'' +
        ", provincia=" + provincia +
        ", esCliente=" + esCliente +
        ", puedeLlamarse=" + puedeLlamarse +
        ", llamadas=" + llamadas +
        '}';
  }
}
