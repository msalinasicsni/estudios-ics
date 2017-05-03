package ni.org.ics.estudios.domain.catalogs;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.audit.Auditable;

import javax.persistence.*;

/**
 * Simple objeto de dominio que representa un operadora telefónica
 * 
 * @author Miguel Salinas
 **/

@Entity
@Table(name = "operadoras", catalog = "estudios_ics")

public class Operadora extends BaseMetaData implements Auditable {
	
	private Integer codigo;
	private String nombre;

    @Id
    @Column(name = "CODIGO", nullable = false, length = 4)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Column(name = "NOMBRE", nullable = false, length = 150)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return false;
    }

    @Override
    public String toString() {
        return "'" + codigo + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operadora)) return false;

        Operadora operadora = (Operadora) o;

        return (!codigo.equals(operadora.codigo));
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}