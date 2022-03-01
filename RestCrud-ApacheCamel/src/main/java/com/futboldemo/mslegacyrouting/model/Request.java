package com.futboldemo.mslegacyrouting.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@JsonAutoDetect
@Data
public class Request {

    @JsonProperty (value ="Nombre")
	@NotBlank (message = "valor no valido para el campo Nombre")
	@Pattern (regexp = "^[ a-zA-Zá-üÁ-Ü].*$" , message = "Nombre tiene un patron inválido" )
	private String nombre;

    @JsonProperty (value ="Ciudad")
	@NotBlank (message = "valor no valido para el campo Ciudad")
	@Pattern (regexp = "^[a-zA-Z0-9].*$" , message = "Ciudad tiene un patron inválido" )
	private String ciudad;

    @JsonProperty (value ="Propietario")
	@NotBlank (message = "valor no valido para el campo Propietario")
	@Pattern (regexp = "^[a-zA-Z0-9].*$" , message = "Propietario tiene un patron inválido" )
	private String propietario;

    @JsonProperty (value ="Capacidad")
	@NotNull (message = "valor no valido para el campo Capacidad")
	@Range(min=0, max=99999999, message = "Capacidad tiene un patron inválido" )
	private Long capacidad;

    @JsonProperty (value ="Division")
	@NotNull (message = "valor no valido para el campo Division")
	@Range(min=0, max=3, message = "Division tiene un patron inválido" )
	private int division;

    @JsonProperty (value ="Competicion")
	@NotBlank (message = "valor no valido para el campo Competicion")
	@Pattern (regexp = "^[a-zA-Z0-9].*$" , message = "Competicion tiene un patron inválido" )
	private String competicion;

    @JsonProperty (value ="Numero_Jugadores")
	@NotNull (message = "valor no valido para el campo Numero_Jugadores")
	@Range(min=0, max=999 , message = "Numero_Jugadores tiene un patron inválido" )
	private int numeroJugadores;

    @JsonProperty (value ="Fecha_Fundacion")
	@NotNull (message = "valor no valido para el campo Fecha_Fundacion")
	@Past( message = "Fecha_Fundacion tiene un patron inválido" )
	private Date fechaFundacion;
    
}
