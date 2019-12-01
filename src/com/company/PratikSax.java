package com.company;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;


class UserHnadler extends DefaultHandler{

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private int id = 1;
    boolean bplat_attri;
    boolean bdesc;
    boolean bcategoria;
    boolean bpreu;
    /*
    <restaurant>
    <plat codi="P7">
    <descripcio>Spaghetti al pesto</descripcio>
    <categoria>Pasta</categoria>
    <preu >8</preu>
    </plat>
    </restaurant>
    */
    @Override
    public void startElement(String uri, String localname, String qname, Attributes attributes) throws SAXException {
        switch (qname.toLowerCase()){
            case "plat":
                String codi = attributes.getValue("codi");
                System.out.println("Num plat: " + getId());
                System.out.println("Codi : " + codi);
                id++;
                break;

            case "descripcio":
                bdesc = true;
                break;

            case "categoria":
                bcategoria = true;
                break;

            case "preu":
                bpreu = true;
                break;
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (bdesc){
            System.out.println("Descripcio : " + new String(ch, start, length));
            bdesc = false;
        }
        else if (bcategoria){
            System.out.println("Categoria : " + new String(ch, start, length));
            bcategoria = false;
        }
        else if (bpreu){
            System.out.println("Preu : " + new String(ch, start, length) + "\n");
            bpreu = false;
        }
    }

}
public class PratikSax {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        File file = new File("Data/cartaplats.xml");
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        UserHnadler userHandler = new UserHnadler();
        saxParser.parse(file, userHandler);
    }
}



