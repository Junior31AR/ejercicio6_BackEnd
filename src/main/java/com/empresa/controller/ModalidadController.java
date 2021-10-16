	package com.empresa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.FiltroModalidad;
import com.empresa.entity.Modalidad;
import com.empresa.service.ModalidadService;
import com.empresa.util.Constantes;

@RestController
@RequestMapping("/rest/modalidad")
@CrossOrigin(origins = "http://localhost:4200")
public class ModalidadController {

	@Autowired
	private ModalidadService modalidadService;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Modalidad>> listaAlumno() {
		List<Modalidad> lista = modalidadService.listaModalidad();
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaModalidad(@RequestBody Modalidad obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Modalidad objSalida = modalidadService.insertaActualizaModalidad(obj);
			if (objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
			} else {
				salida.put("mensaje", Constantes.MENSAJE_REG_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/porNombre/{paramNombre}")
	public ResponseEntity<List<Modalidad>>listaModalidadPorNombre(@PathVariable("paramNombre")String nombre){
		List<Modalidad>lista = modalidadService.listaModalidadPorNombre(nombre);
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("porSede/{paramSede}")
	public ResponseEntity<List<Modalidad>>listaModalidadPorSede(@PathVariable("paramSede")String sede){
		List<Modalidad>lista = modalidadService.listaModalidadPorSede(sede);
		return ResponseEntity.ok(lista);
	}
	
	
    @GetMapping("/porNombreSede/{paramNombre}/{paramSede}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>>listaModalidadNombreSede(
    									@PathVariable("paramNombre")String nombre,
    									@PathVariable("paramSede")String sede){
    	Map<String, Object>salida = new HashMap<String, Object>();
    	try {
			List<Modalidad>lista = modalidadService.listaModalidadNombreSede(nombre,"%"+sede+"%");
			if(CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existe elementos para la consulta");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Se tiene " + lista.size() + " elementos");
			}
		} catch (Exception e) {
			salida.put("mensaje", "Error : " + e.getMessage());
		}
    	return ResponseEntity.ok(salida);
    }
    
    @GetMapping("/porNombreSedeDeporteConParametros")
    @ResponseBody
    public ResponseEntity<Map<String, Object>>listaNombreSedeDeporteConParametros(
    		@RequestParam(value = "nombre", required = false, defaultValue = "") String nombre,
    		@RequestParam(value = "sede", required = false, defaultValue = "") String dni,
    		@RequestParam(value = "idDeporte", required = false, defaultValue = "0") int idDeporte) {
    	Map<String, Object> salida = new HashMap<String, Object>();
    	try {
			List<Modalidad>lista = modalidadService.listaModalidadNombreSedeDeporte(nombre, dni, idDeporte);
			if(CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existe elementos para la consulta");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Se tiene " + lista.size() + " elementos");
			}
		} catch (Exception e) {
			salida.put("mensaje", "Error : " + e.getMessage());
		}
    	return ResponseEntity.ok(salida);
    }
    
    @GetMapping("/porNombreSedeDeporteConParametrosConJSON")
    @ResponseBody
    public ResponseEntity<Map<String, Object>>listaNombreSedeDeporteConParametros(
    								@RequestBody FiltroModalidad filtro){
    	Map<String, Object> salida = new HashMap<String, Object>();
    	try {
			filtro.setSede("%"+filtro.getSede()+"%");
			List<Modalidad>lista = modalidadService.listaModalidadFiltro(filtro);
			if(CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existe elementos para la consulta");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Se tiene " + lista.size() + " elementos");
			}
		} catch (Exception e) {
			salida.put("mensaje", "Error : " + e.getMessage());
		}
    	return ResponseEntity.ok(salida);
    }
    
}
