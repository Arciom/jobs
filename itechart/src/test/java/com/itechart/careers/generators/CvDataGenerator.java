package com.itechart.careers.generators;

import com.itechart.careers.model.CVData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class CvDataGenerator {

    @Parameter(names = "-c", description = "CvDataList count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        CvDataGenerator generator = new CvDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException e) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<CVData> cvDataList = generateCVData(count);
        if(format.equals("json")) {
            saveAsJson(cvDataList, new File(file));
        }
        else if(format.equals("xml")) {
            saveAsXml(cvDataList, new File(file));
        }else if(format.equals("csv")) {
            saveAsCsv(cvDataList, new File(file));
        }else {
            System.out.println("Unrecognized format: " + format);
        }
    }

    private void saveAsJson(List<CVData> cvDataList, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(cvDataList);
        try ( Writer writer = new FileWriter(file);) {
            writer.write(json);
            writer.close();
        }
    }

    private void saveAsXml(List<CVData> cvDataList, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(CVData.class);
        String xml = xstream.toXML(cvDataList);
        try(Writer writer = new FileWriter(file);) {
            writer.write(xml);
            writer.close();
        }
    }

    private void saveAsCsv(List<CVData> cvDataList, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        try( Writer writer = new FileWriter(file);) {
            for (CVData cvData : cvDataList) {
                writer.write(String.format("%s;%s;%s;%s;%s\n", cvData.getName(), cvData.getEmail()
                                                             , cvData.getPhone(), cvData.getCv()
                                                             , cvData.getMessage()));
            }
            writer.close();
        }
    }

    private List<CVData> generateCVData(int count) {
        List<CVData> cvDataList = new ArrayList<CVData>();
        for (int i = 0; i < count; i++) {
            cvDataList.add(new CVData().withName(String.format("name %s", i))
                                       .withEmail(String.format("%s", "sample@gmail.com"))
                                       .withPhone(String.format("+375%s%s %s%s%s-%s%s-%s%s", i,i,i,i,i,i,i,i,i))
                                       .withCv(new File(String.format("%s", "\"src/test/resources/Resume.PDF\"")))
                                       .withMessage(String.format("%s", i)));
        }
        return cvDataList;
    }
}
