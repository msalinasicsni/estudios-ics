package ni.org.ics.estudios.domain.cohortefamilia;

import ni.org.ics.estudios.domain.cohortefamilia.AreaAmbiente;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Created by Miguel Salinas on 5/3/2017.
 * V1.0
 */
@Entity
@DiscriminatorValue("ventana")
public class Ventana extends AreaAmbiente {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private AreaAmbiente areaAmbiente;
    private char abierta;

    @Column(name = "ABIERTA", length = 1)
    public char getAbierta() {
        return abierta;
    }

    public void setAbierta(char abierta) {
        this.abierta = abierta;
    }

    @ManyToOne
    @JoinColumn(name = "CODIGO_AREA")
    @ForeignKey(name = "FK_AREA_AREA")
    public AreaAmbiente getAreaAmbiente() {
        return areaAmbiente;
    }

    public void setAreaAmbiente(AreaAmbiente areaAmbiente) {
        this.areaAmbiente = areaAmbiente;
    }
}
