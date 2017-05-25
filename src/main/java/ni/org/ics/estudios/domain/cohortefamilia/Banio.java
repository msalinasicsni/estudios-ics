package ni.org.ics.estudios.domain.cohortefamilia;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Created by Miguel Salinas on 5/3/2017.
 * V1.0
 */
@Entity
@DiscriminatorValue("banio")
public class Banio extends  AreaAmbiente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AreaAmbiente areaAmbiente;
    private char conVentana;

    @ManyToOne(optional=true)
    @JoinColumn(name = "CODIGO_AREA")
    @ForeignKey(name = "FK_AREA_AREA")
    public AreaAmbiente getAreaAmbiente() {
        return areaAmbiente;
    }

    public void setAreaAmbiente(AreaAmbiente areaAmbiente) {
        this.areaAmbiente = areaAmbiente;
    }

    @Column(name = "CON_VENTANA", length = 1)
    public char getConVentana() {
        return conVentana;
    }

    public void setConVentana(char conVentana) {
        this.conVentana = conVentana;
    }
}
