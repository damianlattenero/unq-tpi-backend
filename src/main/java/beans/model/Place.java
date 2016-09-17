package beans.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Place")
public class Place extends BaseEntity{
    private static final long serialVersionUID = 1L;
}
