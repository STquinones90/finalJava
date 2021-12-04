package ar.edu.unlam.pb1.finalAgosto;

import java.util.ArrayList;
import java.util.List;

public class Empresa {

  private String nombre;
  private List<Integer> listaCodigoPostal;
  private List<Contacto> listaContacto;

  public Empresa(String nombre) {
    this.nombre = nombre;
    this.listaCodigoPostal = new ArrayList<>();
    this.listaContacto = new ArrayList<>();
  }

  /**
   * Devuelve el nombre de la empresa.
   *
   * @return El nombre de la empresa.
   */
  public String getNombreEmpresa() {
    return nombre;
  }

  public List<Contacto> getListaContacto() {
    return listaContacto;
  }

  /**
   * Incorpora un nuevo contacto a la empresa.
   *
   * @param nuevo Contacto nuevo a agregar.
   * @return Si se pudo agregar o no el contacto.
   */
  public boolean agregarNuevoContacto(Contacto nuevo) {
    return listaContacto.add(nuevo);
  }

  /**
   * Incorpora una nueva zona de cobertura (Las zonas de cobertura se identifican por el codigo
   * postal).
   *
   * @param codigoPostal El codigo postal en entero.
   * @return Si se pudo o no agregar el codigo postal a la empresa.
   */
  public boolean agregarNuevaZonaDeCobertura(int codigoPostal) {

    if (!elCodigoPostalEstaDentroDeLaZonaDeCobertura(codigoPostal)) {
      return listaCodigoPostal.add(codigoPostal);
    }

    return false;
  }

  /**
   * Determina si un c�digo postal est� dentro de la zona de cobertura.
   *
   * @param codigoPostal El codigo postal en entero.
   * @return Si existe agregado el codigo postal o no.
   */
  private boolean elCodigoPostalEstaDentroDeLaZonaDeCobertura(int codigoPostal) {
    return listaCodigoPostal.stream().anyMatch(codigo -> codigo.compareTo(codigoPostal) == 0);
  }

  /**
   * Para determinar qu� contacto el sistema debe mostrar, se debe realizar la siguiente b�squeda:
   * 1.	El contacto NO debe ser cliente a�n. 2.	El contacto desea ser llamado o al menos no inform�
   * lo contrario. 3.	El c�digo postal del contacto debe existir en las zonas de cobertura
   * existente. 4.	Del conjunto de contactos resultante se debe seleccionar aleatoriamente el
   * pr�ximo llamado.
   *
   * @return El Contacto candidato en caso contrario NULL.
   */
  public Contacto buscarCandidato() {
    return listaContacto.stream()
        .filter(contacto -> !contacto.esCliente())
        .filter(contacto -> contacto.puedeLlamarse())
        .filter(contacto -> listaCodigoPostal.contains(contacto.getCodigoPostal()))
        .findAny().orElse(null);
  }
}
