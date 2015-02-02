package ec.com.dlc.bunsys.entity.administracion;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import ec.com.dlc.bunsys.entity.administracion.pk.TadmconversionunidadPK;
import ec.com.dlc.bunsys.entity.base.BaseEntity;


/**
 * The persistent class for the tadmconversionunidad database table.
 * 
 */
@Entity
public class Tadmconversionunidad extends BaseEntity<TadmconversionunidadPK> {
	private static final long serialVersionUID = 1L;

	@Column
	private Double boxes;

	@Column
	private Double cantidadbunch;

	@Column
	private String descripcion;

	@Column
	private String estado;

	@Column
	private Integer estadocodigo;

	@Column
	private Double totalbunch;

	@Column
	private String unidadventa;

	@Column
	private Integer unidadventacodigo;

	public Tadmconversionunidad() {
	}


	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getEstadocodigo() {
		return this.estadocodigo;
	}

	public void setEstadocodigo(Integer estadocodigo) {
		this.estadocodigo = estadocodigo;
	}

	public String getUnidadventa() {
		return this.unidadventa;
	}

	public void setUnidadventa(String unidadventa) {
		this.unidadventa = unidadventa;
	}

	public Integer getUnidadventacodigo() {
		return this.unidadventacodigo;
	}

	public void setUnidadventacodigo(Integer unidadventacodigo) {
		this.unidadventacodigo = unidadventacodigo;
	}


	public Double getBoxes() {
		return boxes;
	}


	public void setBoxes(Double boxes) {
		this.boxes = boxes;
	}


	public Double getCantidadbunch() {
		return cantidadbunch;
	}


	public void setCantidadbunch(Double cantidadbunch) {
		this.cantidadbunch = cantidadbunch;
	}


	public Double getTotalbunch() {
		return totalbunch;
	}


	public void setTotalbunch(Double totalbunch) {
		this.totalbunch = totalbunch;
	}

}