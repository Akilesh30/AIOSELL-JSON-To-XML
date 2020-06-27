package internshala1;

import java.io.*;

import org.json.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class JSONtoXMLTest {
   public static void main(String[] args) throws JSONException, TransformerException {
      String json = "{\r\n" + 
      		"  \"startDate\": \"2020-06-27\",\r\n" + 
      		"  \"endDate\": \"2020-06-27\",\r\n" + 
      		"   \"taxInclusive\": true,\r\n" + 
      		"  \"rooms\": [\r\n" + 
      		"    {\r\n" + 
      		"      \"rateplans\": [\r\n" + 
      		"        {\r\n" + 
      		"          \"rate\": {\r\n" + 
      		"            \"rackRate\": 2208\r\n" + 
      		"          },\r\n" + 
      		"          \"rateplanId\": \"executive-s-cp\"\r\n" + 
      		"        },\r\n" + 
      		"        {\r\n" + 
      		"          \"rate\": {\r\n" + 
      		"            \"rackRate\": 2508\r\n" + 
      		"          },\r\n" + 
      		"          \"rateplanId\": \"executive-d-cp\"\r\n" + 
      		"        }\r\n" + 
      		"      ],\r\n" + 
      		"      \"roomId\": \"executive\"\r\n" + 
      		"    },\r\n" + 
      		"    {\r\n" + 
      		"      \"rateplans\": [\r\n" + 
      		"        {\r\n" + 
      		"          \"rate\": {\r\n" + 
      		"            \"rackRate\": 1008\r\n" + 
      		"          },\r\n" + 
      		"          \"rateplanId\": \"super-deluxe-s-ep\"\r\n" + 
      		"        },\r\n" + 
      		"        {\r\n" + 
      		"          \"rate\": {\r\n" + 
      		"            \"rackRate\": 2008\r\n" + 
      		"          },\r\n" + 
      		"          \"rateplanId\": \"super-deluxe-d-ep\"\r\n" + 
      		"        }\r\n" + 
      		"      ],\r\n" + 
      		"      \"roomId\": \"super-deluxe\"\r\n" + 
      		"    }\r\n" + 
      		"  ]\r\n" + 
      		"}\r\n" + 
      		"";
      //Convert JSON to XML
      String xml = convert(json); 
      System.out.println(xml);
      
   }
   private static Document convertToDocument(String xmlStr) {
       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
       DocumentBuilder builder;  
       try  
       {  
           builder = factory.newDocumentBuilder();  
           Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
           return doc;
       } catch (Exception e) {  
           e.printStackTrace();  
       } 
       return null;
   }
   public static String convert(String json) throws JSONException, TransformerException {
      JSONObject jsonObject = new JSONObject(json);
      String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-15\"?>\n<"+"root"+">" + XML.toString(jsonObject)+"\n" + "</"+"root"+">";
      Document doc = convertToDocument(xml);
      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
      StreamResult result = new StreamResult(new StringWriter());
      DOMSource source = new DOMSource(doc);
      transformer.transform(source, result);
      String xmlString = result.getWriter().toString();
      
      return xmlString;
   }
   
}