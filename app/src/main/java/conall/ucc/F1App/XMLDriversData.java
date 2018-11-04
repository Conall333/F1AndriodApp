package conall.ucc.F1App;

import android.content.*;

import java.io.*;
import java.lang.reflect.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.lang.String;
import org.w3c.dom.*;


public class XMLDriversData {

    private Context context;
    private Driver[] data;

    public XMLDriversData(Context context) {

        this.context = context;

        // get to read string to xml and parse it

        InputStream stream = this.context.getResources().openRawResource(R.raw.drivers);

        DocumentBuilder builder = null;
        Document document = null;
        try {

            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = builder.parse(stream);


        }

        catch(Exception e){ }


        // extract the nodeLists for name, address, etc

        NodeList nameList = document.getElementsByTagName("name");
        NodeList teamList = document.getElementsByTagName("team");
        NodeList racesList = document.getElementsByTagName("races");
        NodeList imageList = document.getElementsByTagName("image");
        NodeList image2List = document.getElementsByTagName("image2");
        NodeList countryList = document.getElementsByTagName("country");
        NodeList urlList = document.getElementsByTagName("url");


        data = new Driver[nameList.getLength()];


        // traverse these node lists to populate data

        for(int i=0;i < nameList.getLength();i++) {
            //extract name address etc

            String name = nameList.item(i).getFirstChild().getNodeValue();
            String team = teamList.item(i).getFirstChild().getNodeValue();
            String races = racesList.item(i).getFirstChild().getNodeValue();
            String image = imageList.item(i).getFirstChild().getNodeValue();
            String image2 = image2List.item(i).getFirstChild().getNodeValue();
            String country = countryList.item(i).getFirstChild().getNodeValue();
            String url = urlList.item(i).getFirstChild().getNodeValue();


            // make a person object

            Driver p = new Driver(name,team,races,image,image2,country,url);

            // add it ot data

            data[i] = p;

        }





    }



    public Driver getPersonData (int i)
    {
        return data[i];

    }

    public String [] getTeams(){

        String [] teams = new String[data.length];

        for(int i=0;i<data.length; i++)
        {
            teams[i]=data[i].getTeam();

        }

        return teams;

    }


    public String [] getNames(){

        String [] names = new String[data.length];

        for(int i=0;i<data.length; i++)
        {
            names[i]=data[i].getName();

        }

        return names;

    }


    public String [] getImages(){

        String [] images = new String[data.length];

        for(int i=0;i<data.length; i++)
        {
            images[i]=data[i].getImage2();

        }

        return images;



    }


    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    public Integer [] getImageIds(String[] strings){

        Integer [] images = new Integer[data.length];

        for(int i=0;i<data.length; i++)
        {
            String name = strings[i];
            images[i]= getResId( name, R.drawable.class);

        }

        return images;



    }






    public int getLength(){

        return data.length;
    }




}
