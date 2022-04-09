import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class BankTransferConfig {
    private String CONFIG1 = "en";
    private int CONFIG2 =  25000000;
    private int CONFIG3 = 6500;
    private int CONFIG4 = 15000;
    private String[] CONFIG5 = {"RTO (real-time)","SKN","RTGS","BI FAST"};
    private String CONFIG6 = "yes";
    private String CONFIG7 = "ya";
    public BankTransferConfig(String path){
        try {
            JSONParser jp = new JSONParser();
            Object obj = jp.parse(new FileReader(path));
            JSONObject jso = (JSONObject) obj;
            JSONObject transfer = (JSONObject) jso.get("transfer");
            JSONObject confirmation = (JSONObject) jso.get("confirmation");
            JSONArray methods = (JSONArray) jso.get("methods");
            CONFIG1 = (String)jso.get("lang");
            CONFIG2 = (int) transfer.get("treshold");
            CONFIG3 = (int) transfer.get("low_fee");
            CONFIG4 = (int) transfer.get("high_fee");
            CONFIG5 = (String[]) methods.toArray();
            CONFIG6 = (String) confirmation.get("en");
            CONFIG7 = (String) confirmation.get("id");
        } catch (Exception e) {
            WriteJson(path);
        }
    }
    public void WriteJson(String path){
        JSONObject jso = new JSONObject();
        JSONObject transfer = new JSONObject();
        JSONObject confirmation = new JSONObject();
        JSONArray methods = new JSONArray();
        for (String object : CONFIG5) {
            methods.add(object);
        }

        jso.put("lang", this.CONFIG1);
        transfer.put("treshold", this.CONFIG2);
        transfer.put("low_fee", this.CONFIG3);
        transfer.put("high_fee", this.CONFIG4);
        jso.put("transfer", transfer);
        jso.put("methods", methods);
        confirmation.put("en", CONFIG6);
        confirmation.put("id", CONFIG7);
        jso.put("confirmation", confirmation);
        try {
            FileWriter file = new FileWriter(path);
            file.write(jso.toString());
            file.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void setLang(String lang){
        this.CONFIG1 = lang;
    }
    public void setTreshold(int tresh){
        this.CONFIG2 = tresh;
    }
    public void setLowFee(int fee){
        this.CONFIG3 = fee;
    }
    public void setHighFee(int fee){
        this.CONFIG4 = fee;
    }
    public void setMethod(String[] methods){
        this.CONFIG5 = methods;
    }
    public void setEn(String msg){
        this.CONFIG6 = msg;
    }
    public void setId(String msg){
        this.CONFIG7 = msg;
    }

    public String getLang(){
        return this.CONFIG1;
    }
    public int getTreshold(){
        return this.CONFIG2;
    }
    public int getLowFee(){
        return this.CONFIG3;
    }
    public int getHighFee(){
        return this.CONFIG4;
    }
    public String[] getMethod(){
        return this.CONFIG5;
    }
    public String getEn(){
        return this.CONFIG6;
    }
    public String getId(){
        return this.CONFIG7;
    }



}