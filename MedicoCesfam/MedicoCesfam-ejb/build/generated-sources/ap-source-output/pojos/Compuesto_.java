package pojos;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pojos.MedicamentoCompuesto;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-28T05:09:39")
@StaticMetamodel(Compuesto.class)
public class Compuesto_ { 

    public static volatile SingularAttribute<Compuesto, BigDecimal> id;
    public static volatile ListAttribute<Compuesto, MedicamentoCompuesto> medicamentoCompuestoList;
    public static volatile SingularAttribute<Compuesto, String> descripcion;

}