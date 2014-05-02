package com.dzhao.common.codegen.plugins.freemarker;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FreeMarkerWriter{

    private final String templateUrl;
    private final Object input;
    private final String targetUrl;

    public FreeMarkerWriter(String templateUrl, String targetUrl, Object input){
        this.templateUrl = templateUrl;
        this.targetUrl = targetUrl;
        this.input = input;
    }

    public void write(){
        Configuration cfg = new Configuration();
        Template template = null;
        try {
            System.out.println("templateUrl: " + templateUrl);
            template = cfg.getTemplate(templateUrl);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        FileWriter writer = null;
        File file = new File(targetUrl);
        System.out.println("targetUrl: " + targetUrl);
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
            writer = new FileWriter(file);
            if (template != null) {
                try {
                    System.out.println("input: " + input.toString());
                    template.process(input, writer);
                } catch (TemplateException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
