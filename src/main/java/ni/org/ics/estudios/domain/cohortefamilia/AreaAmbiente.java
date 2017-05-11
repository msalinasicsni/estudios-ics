package ni.org.ics.estudios.domain.cohortefamilia;

import ni.org.ics.estudios.domain.BaseMetaData;
import ni.org.ics.estudios.domain.Casa;
import ni.org.ics.estudios.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Created by Miguel Salinas on 5/3/2017.
 * V1.0
 */
@Entity
@Table(name = "chf_areas_ambiente", catalog = "estudios_ics")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE", length = 50, discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue(value="area_ambiente")
public class AreaAmbiente extends BaseMetaData implements Auditable {

    private String codigo;
    private Double largo;
    private Double ancho;
    private Double totalM2;
    private Integer numVentanas;
    private CasaCohorteFamilia casa;

    @Id
    @Column(name = "CODIGO", length = 50)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "LARGO")
    public Double getLargo() {
        return largo;
    }

    public void setLargo(Double largo) {
        this.largo = largo;
    }

    @Column(name = "ANCHO")
    public Double getAncho() {
        return ancho;
    }

    public void setAncho(Double ancho) {
        this.ancho = ancho;
    }

    @Column(name = "TOTAL_M2")
    public Double getTotalM2() {
        return totalM2;
    }

    public void setTotalM2(Double totalM2) {
        this.totalM2 = totalM2;
    }

    @Column(name = "NUM_VENTANAS")
    public Integer getNumVentanas() {
        return numVentanas;
    }

    public void setNumVentanas(Integer numVentanas) {
        this.numVentanas = numVentanas;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name = "CODIGO_CASACHF", nullable = true)
    @ForeignKey(name = "FK_AREAAMBIENTE_CASA")
    public CasaCohorteFamilia getCasa() {
        return casa;
    }

    public void setCasa(CasaCohorteFamilia casa) {
        this.casa = casa;
    }

    @Override
    public boolean isFieldAuditable(String fieldname) {
        return true;
    }

    @Override
    public String toString() {
        return "AreaAmbiente{" + codigo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AreaAmbiente)) return false;

        AreaAmbiente that = (AreaAmbiente) o;

        return (!codigo.equals(that.codigo));
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}