package by.issoft.careers.tests;

import by.issoft.careers.model.CVData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertTrue;

public class CareersTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validCvDataXml() throws IOException {

   try (BufferedReader reader = new BufferedReader( new FileReader(new File("src/test/resources/CvData.xml")))){
     String xml = "";
     String line =  reader.readLine();
     while (line != null) {
       xml += line;
       line = reader.readLine();
     }
     XStream xstream = new XStream();
     xstream.processAnnotations(CVData.class);
     List<CVData> cvDataList = (List<CVData>) xstream.fromXML(xml);
     return cvDataList.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
   }
  }

  @DataProvider
  public Iterator<Object[]> validCvDataJson() throws IOException {
   try (BufferedReader reader = new BufferedReader( new FileReader(new File("src/test/resources/CvData.json")))) {
     String json = "";
     String line =  reader.readLine();
     while (line != null) {
       json += line;
       line = reader.readLine();
     }
     Gson gson = new Gson();
     List<CVData> cvDataList = gson.fromJson(json, new TypeToken<List<CVData>>(){}.getType()); //List<CvData>.class
     return cvDataList.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
   }
  }

  @DataProvider
  public Iterator<Object[]> validCvDataCsv() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    try (BufferedReader reader = new BufferedReader( new FileReader(new File("src/test/resources/CvData.xml")))){
      String line =  reader.readLine();
      while (line != null) {
        String[] split = line.split(";");
        list.add(new Object[] {new CVData().withName(split[0])
                                           .withEmail(split[1])
                                           .withPhone(split[2])
                                           .withSubject(split[3])
                                           .withCv(new File(split[4]))
                                           .withMessage(split[5])});
        line = reader.readLine();
      }
     return list.iterator();
    }
  }

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().vacancies();
    app.goTo().qaEngineer();
    app.goTo().sendResume();
  }

  @Test(dataProvider =  "validCvDataJson")
  public void testSendCV(CVData cvData) throws Exception {
    app.careers().fillSendCVForm(cvData);
    assertTrue(app.careers().isElementOnPage(By.xpath("//div[@class='wpcf7-response-output wpcf7-display-none wpcf7-mail-sent-ok' and contains(text(), 'Ваше сообщение было отправлено успешно. Спасибо.')]")));
  }
}
