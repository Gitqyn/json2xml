package jsonxml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
/** 
* json xml ��ת������
* @author��yqgao 
* @date��2015-4-16 ����02:01:38 
*/
public class XmlJSONUtil {
private static XMLSerializer xmlserializer = new XMLSerializer();
private static final String STR_JSON_OBJECT = "{"+
"\"name\": \"С��\","+
"\"age\": \"20\","+
"\"add\": \"����\","+
"\"gender\": \"��\""+
"}";
private static final String STR_JSON_ARRAY = "[{"+
"\"name\": \"С��\","+
"\"age\": \"20\","+
"\"add\": \"����\","+
"\"gender\": \"��\""+
"},"+
"{"+
"\"name\": \"С��\","+
"\"age\": \"21\","+
"\"add\": \"�Ϻ�\","+
"\"gender\": \"Ů\""+
"}]";
/**
* xml��ʽ�ַ���ת����jsonObject����jsonArray
* @param xml
* @return
*/
public static String xml2json(String xml){
String rs = "";
try {
rs = xmlserializer.read(xml).toString();
} catch (Exception e) {
//e.printStackTrace();
System.err.println("xmlת��Ϊjson�쳣...");
}
return rs;
}
/**
* jsonArray����jsonObject�ַ���ת��Ϊxml
* @param json
* @return
*/
public static String json2xml(String json){
String rs = "";
try {
if(json.contains("[")&&json.contains("]")){
//jsonArray
JSONArray jobj = JSONArray.fromObject(json);
rs = xmlserializer.write(jobj);
}else{
//jsonObject
JSONObject jobj = JSONObject.fromObject(json);
rs = xmlserializer.write(jobj);
}
} catch (Exception e) {
//e.printStackTrace();
System.err.println("jsonArrayת��Ϊxml�쳣...");
}
return rs;
}
/***
* json��xml�ļ���ת
* @param sourcePath json�ļ���·��
* @param outPath xml�ļ���·��
* @param enterFlag ת����ʶ 1��ʾjsonת��Ϊxml 
* 2��ʾxmlת��Ϊjson
* @return
*/
public static String jfxfTranspose(String sourcePath,String outPath,int enterFlag){
FileInputStream in =null;
BufferedReader br = null; 
FileWriter fw = null;
String rs = null;
try{
File jsonFile = new File(sourcePath);
in = new FileInputStream(jsonFile); 
StringBuffer sbuf = new StringBuffer();
br = new BufferedReader(new InputStreamReader(in));
String temp =null;
while((temp=br.readLine())!=null){
sbuf.append(temp);
}
if(1==enterFlag){
rs = json2xml(sbuf.toString());
}else{
rs = xml2json(sbuf.toString());
}
File test = new File(outPath);
if(!test.exists()){
test.createNewFile();
}
fw = new FileWriter(test);
fw.write(rs);
}catch (Exception e) {
System.err.println("json��xmlת���ļ��쳣...");
}finally{
try {
fw.close();
br.close();
in.close();
} catch (Exception e) {
System.err.println("���롢������ر��쳣");
e.printStackTrace();
}
}
return rs;
}
public static void main(String[] args) throws IOException {
// String xml1 = json2xml(STR_JSON_OBJECT);
String xml1 = json2xml(STR_JSON_ARRAY);
System.out.println("xml ==>"+xml1);
String json1 = xml2json(xml1);
/**
 * jsonת��Ϊxml 
 */
System.out.println("json==>"+json1);
String spath2 = "F:/GIT_Space/json2xml/json&xmldemo/jsondemo.json";
String opath2 = "F:/GIT_Space/json2xml/json&xmldemo/jsondemo.xml";
String rs2 = jfxfTranspose(spath2,opath2,1);
System.out.print(rs2);
/**
 * xmlת��Ϊjson
 */
/*String spath1 = "F:/WorkSpace/JavaProject/src/com/test/util/testGao.xml";
String opath1 = "F:/WorkSpace/JavaProject/src/com/test/util/test.json";
String rs1 = jfxfTranspose(spath1,opath1,2);
System.out.print(rs1);*/
}
}

