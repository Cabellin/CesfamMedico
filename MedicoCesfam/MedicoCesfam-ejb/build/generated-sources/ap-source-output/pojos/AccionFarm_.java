package pojos;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pojos.Medicamento;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-15T18:42:45")
@StaticMetamodel(AccionFarm.class)
public class AccionFarm_ { 

    public static volatile SingularAttribute<AccionFarm, String> descripcion;
    public static volatile ListAttribute<AccionFarm, Medicamento> medicamentoList;
    public static volatile SingularAttribute<AccionFarm, BigDecimal> id;

}