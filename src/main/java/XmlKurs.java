import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "rozklad_jazdy")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class XmlKurs {
    @XmlElementWrapper(name = "trasy")
    @XmlElement(name = "trasa")
    private List<XmlTrasa> trasas;






}
