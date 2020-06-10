import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import java.io.File;
import java.io.FileReader;

public class JaxBHelper {

    public XmlKurs unmarshall(){
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlKurs.class);

            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            saxParserFactory.setFeature("http://xml.org/sax/features/validation", false);
            XMLReader xmlReader = saxParserFactory.newSAXParser().getXMLReader();
            InputSource inputSource = new InputSource(new FileReader("src/main/resources/rozklad.xml"));
            SAXSource saxSource = new SAXSource(xmlReader,inputSource);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            XmlKurs xmlKurs = (XmlKurs) unmarshaller.unmarshal(saxSource);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.marshal(xmlKurs,System.out);
            return xmlKurs;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void marshall(XmlKurs xmlKurs){
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlKurs.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.marshal(xmlKurs,System.out);
            marshaller.marshal(xmlKurs,new File("src/main/resources/rozkladNew.xml"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
