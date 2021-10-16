package com.empresa.service;

import java.util.List;

import com.empresa.entity.FiltroModalidad;
import com.empresa.entity.Modalidad;

public interface ModalidadService {

	
	public Modalidad insertaActualizaModalidad(Modalidad obj);
	
	public abstract List<Modalidad> listaModalidad();
	
	//LISTA DE MODADALIDAD POR NOMBRE
	public abstract List<Modalidad> listaModalidadPorNombre(String nombre);
	
	//LISTA DE MODALIDAD POR SEDE
	public abstract List<Modalidad> listaModalidadPorSede(String sede);
	
	//LISTA DE MODALIDAD POR NOMBRE Y SEDE
	public abstract List<Modalidad> listaModalidadNombreSede(String nombre, String sede);

	//LISTA DE MODALIDAD POR NOMBRE, SEDE Y DEPORTE
	public abstract List<Modalidad> listaModalidadNombreSedeDeporte(String nombre, String sede, int idDeporte);

	
	public abstract List<Modalidad> listaModalidadFiltro(FiltroModalidad filtro);
}
