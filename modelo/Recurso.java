package modelo;

import java.util.Objects;

public class Recurso {
 private int idRecurso;
 private String nombre;
 private String descripcion;
 private String ubicacion;
 private int capacidad;

 public Recurso() {
  this.idRecurso = 0;
  this.nombre = "";
  this.descripcion = "";
  this.ubicacion = "";
  this.capacidad = 0;
 }

 public Recurso(int idRecurso, String nombre, String descripcion, String ubicacion, int capacidad) {
  this.idRecurso = idRecurso;
  this.nombre = nombre;
  this.descripcion = descripcion;
  this.ubicacion = ubicacion;
  this.capacidad = capacidad;
 }

 public Recurso(String nombre, String descripcion, String ubicacion, int capacidad) {
  this.nombre = nombre;
  this.descripcion = descripcion;
  this.ubicacion = ubicacion;
  this.capacidad = capacidad;
 }

 public int getIdRecurso() {
  return idRecurso;
 }
 public void setIdRecurso(int idRecurso) {
  this.idRecurso = idRecurso; }
 public String getNombre() {
  return nombre; }
 public void setNombre(String nombre) {
  this.nombre = nombre; }
 public String getDescripcion() {
  return descripcion; }
 public void setDescripcion(String descripcion) {
  this.descripcion = descripcion; }
 public String getUbicacion() {
  return ubicacion; }
 public void setUbicacion(String ubicacion) {
  this.ubicacion = ubicacion; }
 public int getCapacidad() {
  return capacidad; }
 public void setCapacidad(int capacidad) {
  this.capacidad = capacidad; }

 @Override
 public int hashCode() {
  final int primo = 31;
  int result = 1;
  result = result * primo + ((nombre == null) ? 0 : nombre.hashCode());
  result = result * primo + capacidad;
  return result;
 }

 @Override
 public boolean equals(Object obj) {
  if (this == obj) return true;
  if (obj == null || getClass() != obj.getClass()) return false;
  Recurso other = (Recurso) obj;
  return capacidad == other.capacidad && Objects.equals(nombre, other.nombre);
 }

 @Override
 public String toString() {
  return "Recurso{" + "id=" + idRecurso + ", nombre='" + nombre + '\'' +
          ", ubicacion='" + ubicacion + '\'' + ", capacidad=" + capacidad + '}';
 }
}

