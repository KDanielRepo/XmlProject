import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class XmlTrasa {
    @XmlElement(name = "godzina_odjazdu")
    private String godzinaOdj;
    @XmlElement(name = "godzina_przyjazdu")
    private String godzinaPrzy;
    @XmlElement(name = "nr_autokaru")
    private String autokar;
    @XmlAttribute(name = "z")
    private String from;
    @XmlAttribute(name = "do")
    private String to;
}
