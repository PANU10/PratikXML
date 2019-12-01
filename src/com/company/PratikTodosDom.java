package com.company;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;


public class PratikTodosDom {

    public void addElementToXml() {
        // Para crear un nodo nuevo
        /*
        <restaurant>
        <plat codi="P7">
        <descripcio>Spaghetti al pesto</descripcio>
        <categoria>Pasta</categoria>
        <preu >8</preu>
        </plat>
        </restaurant>
        */
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File("Data/cartaplats.xml"));
            document.getDocumentElement().normalize();

            Element rootElement = document.getDocumentElement();

            Element palt = document.createElement("plat");
            Attr codi = document.createAttribute("codi");
            codi.setValue("P7");
            palt.setAttributeNode(codi);
            rootElement.appendChild(palt);

            Element descElement = document.createElement("descripcio");
            descElement.setTextContent("Spaghetti al pesto");
            palt.appendChild(descElement);

            Element categoriaElement = document.createElement("categoria");
            categoriaElement.setTextContent("Pasta");
            palt.appendChild(categoriaElement);

            Element preuElement = document.createElement("preu");
            preuElement.setTextContent("8");
            palt.appendChild(preuElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult consoleResult = new StreamResult("Data/cartaplats.xml");
            transformer.transform(source,consoleResult);


        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void modifyDom() {

    }
}
