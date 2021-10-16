package com.empresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresa.entity.FiltroModalidad;
import com.empresa.entity.Modalidad;

public interface ModalidadRepository extends JpaRepository<Modalidad, Integer> {

	public abstract List<Modalidad> findByNombre(String nombre);
	
	public abstract List<Modalidad> findBySede(String sede);
	
	@Query("select m from Modalidad m where "
								+ "( :p_nombre is '' or m.nombre = :p_nombre ) and "
								+ "( :p_sede is '' or m.sede like :p_sede ) ")
	public abstract List<Modalidad> listaModalidadNombreSede(
													@Param("p_nombre") String nombre,
													@Param("p_sede") String sede);
	
	@Query("select m from Modalidad m where "
			+ "( :p_nombre is '' or m.nombre = :p_nombre ) and "
			+ "( :p_sede is '' or m.sede like :p_sede ) and "
			+ "( :p_deporte is 0 or m.deporte.idDeporte = :p_deporte ) ")
	public abstract List<Modalidad> listaModalidadNombreSedeDeporte(
												@Param("p_nombre") String nombre,
												@Param("p_sede") String sede,
												@Param("p_deporte") int idDeporte);
	
	
	@Query("select m from Modalidad m where "
			+ "( :#{#fil.nombre} is '' or m.nombre = :#{#fil.nombre} ) and " 
			+ "( :#{#fil.sede} is '' or m.sede like :#{#fil.sede} ) and "
			+ "( :#{#fil.idDeporte} is 0 or m.deporte.idDeporte = :#{#fil.idDeporte} ) ")
	public abstract List<Modalidad>listaModalidadFiltro(@Param("fil")FiltroModalidad filtro);
}
