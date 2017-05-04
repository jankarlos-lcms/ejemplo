package org.usco.lcms.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.usco.lcms.modelo.JSONRespuesta;
import org.usco.lcms.modelo.Resolucion;
import org.usco.lcms.utilidad.Respuesta;

@RestController
public class ResolucionController {

	@Autowired
	DataSource dataSourceAcademiaInvitado;

	@RequestMapping(value = "/resoluciones", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> adicionar(@RequestBody Resolucion resolucion) {

		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceAcademiaInvitado);

			String sql = "INSERT INTO resolucion (res_codigo, res_fecha, res_dependencia, res_descripcion, res_numero)"
					+ " VALUES (?, ?, ?, ?, ?)";

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date fechaRes = sdf.parse(resolucion.getFecha());

			int resultado = jdbcTemplate.update(sql, resolucion.getCodigo(), new java.sql.Date(fechaRes.getTime()),
					resolucion.getDependencia(), resolucion.getDescripcion(), resolucion.getNumero());

			if (resultado > 0) {
				Respuesta respuesta = new Respuesta(Respuesta.EJECUCION_OK);
				return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}

		Respuesta respuesta = new Respuesta(Respuesta.EJECUCION_ERROR);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/resoluciones/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> modificar(@PathVariable int id, @RequestBody Resolucion resolucion)
			throws ParseException {

		if (id > 0) {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceAcademiaInvitado);
			// update
			String sql = "UPDATE resolucion SET res_fecha=?, res_dependencia=?, res_descripcion=?, res_numero=?"
					+ " WHERE res_codigo=?";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date fechaRes = sdf.parse(resolucion.getFecha());

			int resultado = jdbcTemplate.update(sql, new java.sql.Date(fechaRes.getTime()), resolucion.getDependencia(),
					resolucion.getDescripcion(), resolucion.getNumero(), id);

			if (resultado > 0) {
				Respuesta respuesta = new Respuesta(Respuesta.EJECUCION_OK);
				return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
			}
		}

		Respuesta respuesta = new Respuesta(Respuesta.EJECUCION_ERROR);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/resoluciones/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<Respuesta> eliminar(@PathVariable int id) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceAcademiaInvitado);
		String sql = "DELETE FROM resolucion WHERE res_codigo=?";
		int resultado = jdbcTemplate.update(sql, id);

		if (resultado > 0) {
			Respuesta respuesta = new Respuesta(Respuesta.EJECUCION_OK);
			return new ResponseEntity<Respuesta>(respuesta, HttpStatus.OK);
		}

		Respuesta respuesta = new Respuesta(Respuesta.EJECUCION_ERROR);
		return new ResponseEntity<Respuesta>(respuesta, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/resoluciones", method = RequestMethod.GET, headers = "Accept=application/json")
	public JSONRespuesta getPaises(@RequestParam(value = "search[value]", defaultValue = "") String search,
			@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "length", defaultValue = "10") int length,
			@RequestParam(value = "draw", defaultValue = "1") int draw) {

		JSONRespuesta respuesta = new JSONRespuesta();

		if (start == 0) {
			start = 1;
		}

		int fin = start + length - 1;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceAcademiaInvitado);

		String sql = "select count(*) from resolucion";
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		int filtrados = count;

		if (search.length() > 0) {
			sql = sql + " where res_descripcion like '%" + search + "%'";
			filtrados = jdbcTemplate.queryForObject(sql, Integer.class);
		}

		sql = "select res_codigo, res_fecha, res_dependencia, res_descripcion, res_numero";
		sql = sql + " from (select row_number() over(order by res_descripcion ASC) AS RowNumber,";
		sql = sql + " res_codigo, res_fecha, res_dependencia, res_descripcion, res_numero";
		sql = sql + " from resolucion";

		if (search.length() > 0) {
			sql = sql + " where res_descripcion like '%" + search + "%'";
		}

		sql = sql + ") as tabla";
		sql = sql + " where tabla.RowNumber between " + start + " and " + fin;

		List<Resolucion> listaResolucion = jdbcTemplate.query(sql, new RowMapper<Resolucion>() {

			public Resolucion mapRow(ResultSet rs, int rowNum) throws SQLException {
				Resolucion resolucion = new Resolucion();

				resolucion.setCodigo(rs.getInt("res_codigo"));
				resolucion.setFecha(rs.getString("res_fecha"));
				resolucion.setDependencia(rs.getInt("res_dependencia"));
				resolucion.setDescripcion(rs.getString("res_descripcion"));
				resolucion.setNumero(rs.getString("res_numero"));
				return resolucion;
			}

		});

		respuesta.setDraw(draw);
		respuesta.setRecordsFiltered(filtrados);
		respuesta.setRecordsTotal(count);
		respuesta.setData(listaResolucion);

		return respuesta;
	}

}