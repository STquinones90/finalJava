package ar.edu.unlam.pb1.finalAgosto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InterfazCallCenter {

  public static void main(String args[]) {

    // SE ASUME QUE SOLAMENTE EXISTE UNA EMPRESA POR CORRIDA DEL PROGRAMA.
    String nombreEmpresa = "PRUEBA";
    Empresa empresa = new Empresa(nombreEmpresa);

    System.out.println("Empresa: " + empresa.getNombreEmpresa());
    System.out.println("Bienvenido al callcenter");

    int opcion = 0;
    do {
      Scanner sc = new Scanner(System.in);
      mostrarMenu();
      if (sc.hasNextInt()) {
        opcion = sc.nextInt();
        switch (opcion) {
          case 1:
            incorporarZonaDeCobertura(empresa);
            break;
          case 2:
            darDeAltaNuevoContacto(empresa);
            break;
          case 3:
            realizarNuevaLlamada(empresa);
            break;
          case 4:
            verInformacionDelContacto(empresa);
            break;
        }
      }
    } while (opcion != 9);
  }

  /**
   * Se registra un nuevo c�digo postal dentro de la zona de cobertura de la empresa.
   *
   * @param empresa Empresa
   */
  private static void incorporarZonaDeCobertura(Empresa empresa) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Ingrese el codigo postal: ");
    int codigo;
    if (sc.hasNextInt()) {
      codigo = sc.nextInt();
      if (empresa.agregarNuevaZonaDeCobertura(codigo)) {
        crearMensajeExito("El codigo ingresado se agrego con exito.");
      } else {
        System.out.println("El codigo ingresado no se pudo agregar");
      }
    } else {
      System.out.println("El codigo ingresado no es valido");
    }
  }

  /**
   * Registra un nuevo contacto en la empresa.
   *
   * @param empresa Empresa
   */
  private static void darDeAltaNuevoContacto(Empresa empresa) {
    System.out.println("A continuacion se le pedira los datos del contacto a dar de alta");

    String nombre = obtenerDato("Ingrese el nombre: ",
        "El valor ingresado no es valido - No se aceptan n�meros.", true);
    String apellido = obtenerDato("Ingrese el apellido: ",
        "El valor ingresado no es valido - No se aceptan n�meros.", true);
    String celular = obtenerDato("Ingrese el celular: ",
        "El valor ingresado no es valido.");
    String direccion = obtenerDato("Ingrese el direccion: ",
        "El valor ingresado no es valido.");
    Integer codigoPostal = obtenerDatoInteger("Ingrese el codigoPostal: ",
        "El valor ingresado no es valido - Valor num�rico.");
    String localidad = obtenerDato("Ingrese la localidad: ",
        "El valor ingresado no es valido.");
    Provincia provincia = obtenerDatoProvincia("Ingrese el numero de la provincia: ",
        "El valor ingresado no es valido - Enumerador que contenga las 23 provincias argentinas.");

    Contacto contacto = new Contacto(nombre, apellido, celular, direccion, codigoPostal, localidad,
        provincia);

    do {
      String email = obtenerDato("Ingrese el email: ",
          "El valor ingresado no es valido - Debe contener al menos el s�mbolo �@� y el caracter �.�.");
      contacto.esEmailValido(email);
    } while (contacto.getEmail() == null);

    if (empresa.agregarNuevoContacto(contacto)) {
      crearMensajeExito("El contacto fue dado de alta exitosamente.");
    } else {
      System.out.println("El contacto no fue dado de alta");
    }
  }

  /**
   * Busca un candidato,  muestra los datos del mismo, y permite almacenar el resultado de la
   * llamada.
   * <p>
   * a.	Si la llamada fue exitosa (en ese caso el contacto pasa a ser cliente, y no se lo debe
   * volver a llamar). b.	Si el contacto no desea ser llamado nuevamente (la llamada pudo no haber
   * sido exitosa, pero se haga un nuevo intento en el futuro). c.	Observaciones: Texto libre donde
   * el operador deja registro de lo conversado.
   *
   * @param empresa Empresa
   */
  private static void realizarNuevaLlamada(Empresa empresa) {
    Contacto contacto = empresa.buscarCandidato();
    if (contacto != null) {
      System.out.println("Se encontro un candidato - A continuacion se mostran sus datos");
      System.out.println(contacto);

      crearMensajeExito("Se procede a llamar al contacto.");

      boolean llamadaExitosa = obtenerDatoBoolean(
          "¿La llamada fue exitosa? (1 para un Si - 0 para un No): ", "Opcion Invalida.");
      boolean puedeLlamarse = true;
      if (!llamadaExitosa) {
        puedeLlamarse = obtenerDatoBoolean(
            "¿Puede llamarse devuelta al contacto? (1 para un Si - 0 para un No): ",
            "Opcion Invalida.");
      }
      String observacion = obtenerDato("Ingrese la observacion de la llamada: ",
          "Opcion invalida.");
      Llamada llamada = new Llamada(llamadaExitosa, observacion);
      if (contacto.registrarNuevaLlamada(llamada, puedeLlamarse)) {
        crearMensajeExito("Se registro la llamada con exito.");
      }
    } else {
      System.out.println("No existe un candidato valido.");
    }

  }

  /**
   * Se visualiza la informaci�n del contacto, incluso el listado de las llamadas que se le hicieron.
   *
   * @param empresa Empresa
   */
  private static void verInformacionDelContacto(Empresa empresa) {
    String celular = obtenerDato("Ingrese el celular del contacto a buscar: ",
        "El valor ingresado no es valido.");
    Contacto contactoBusqueda = empresa.getListaContacto().stream()
        .filter(contacto -> contacto.getCelular().compareTo(celular) == 0).findFirst().orElse(null);

    if (contactoBusqueda != null) {
      crearMensajeExito("Contacto encontrado: " + contactoBusqueda);
    } else {
      System.out.println("No existe el contacto buscado.");
    }
  }

  private static void crearMensajeExito(String mensaje) {
    System.out.println();
    System.out.println("************************");
    System.out.println(mensaje);
    System.out.println("************************");
    System.out.println();
  }

  private static void mostrarMenu() {
    System.out.println("************************");
    System.out.println("Menu de opciones\n");
    System.out.println("1 - Incorporar zona de cobertura");
    System.out.println("2 - Dar de alta un nuevo contacto");
    System.out.println("3 - Realizar nueva llamada");
    System.out.println("4 - Ver informaci�n del contacto");
    System.out.println("9 - Salir");
    System.out.println("************************");
  }

  private static Provincia obtenerDatoProvincia(String mensajePregunta,
      String mensajeError) {
    boolean datoRequerido = true;
    Provincia dato = null;
    do {
      Scanner sc = new Scanner(System.in);
      System.out.print(mensajePregunta);

      List<Provincia> listaProvincias = new ArrayList<>();
      Arrays.stream(Provincia.values()).forEach(p -> {
        listaProvincias.add(p);
        System.out.println(listaProvincias.indexOf(p) + " - " + p.name());
      });

      if (sc.hasNextInt()) {
        Integer opcion = sc.nextInt();
        dato = listaProvincias.get(opcion);
        datoRequerido = dato == null;
      }

      if (datoRequerido) {
        System.out.println(mensajeError);
      }
    } while (datoRequerido);
    return dato;
  }

  private static Boolean obtenerDatoBoolean(String mensajePregunta, String mensajeError) {
    boolean datoRequerido = true;
    Boolean dato = null;
    do {
      Scanner sc = new Scanner(System.in);
      System.out.print(mensajePregunta);
      if (sc.hasNextInt()) {
        switch (sc.nextInt()) {
          case 0:
            dato = false;
            datoRequerido = false;
            break;
          case 1:
            dato = true;
            datoRequerido = false;
            break;
          default:
            break;
        }
      }

      if (datoRequerido) {
        System.out.println(mensajeError);
      }
    } while (datoRequerido);
    return dato;
  }

  private static Integer obtenerDatoInteger(String mensajePregunta, String mensajeError) {
    boolean datoRequerido = true;
    Integer dato = null;
    do {
      Scanner sc = new Scanner(System.in);
      System.out.print(mensajePregunta);
      if (sc.hasNextInt()) {
        dato = sc.nextInt();
        datoRequerido = false;
      }

      if (datoRequerido) {
        System.out.println(mensajeError);
      }
    } while (datoRequerido);
    return dato;
  }

  private static String obtenerDato(String mensajePregunta, String mensajeError) {
    return obtenerDato(mensajePregunta, mensajeError, false);
  }

  private static String obtenerDato(String mensajePregunta, String mensajeError,
      boolean evaluarNumeros) {
    boolean datoRequerido = true;
    String dato;
    do {
      Scanner sc = new Scanner(System.in);
      System.out.print(mensajePregunta);
      dato = sc.nextLine();
      if (dato != null && !dato.trim().isBlank() &&
          (!evaluarNumeros || dato.chars().noneMatch(Character::isDigit))
      ) {
        datoRequerido = false;
      }

      if (datoRequerido) {
        System.out.println(mensajeError);
      }
    } while (datoRequerido);
    return dato;
  }
}
