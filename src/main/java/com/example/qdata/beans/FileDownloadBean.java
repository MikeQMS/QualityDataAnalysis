package com.example.qdata.beans;

import org.springframework.web.context.annotation.RequestScope;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Named
@RequestScope
public class FileDownloadBean {

    public void download() throws IOException
    {

        File file = new File("src/main/resources/file/Data_example_File.xlsx");

        FacesContext facesContext = FacesContext.getCurrentInstance();

        HttpServletResponse response =
                (HttpServletResponse) facesContext.getExternalContext().getResponse();

        response.reset();
        response.setHeader("Content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=example_data.xlsx");

        OutputStream responseOutputStream = response.getOutputStream();

        InputStream fileInputStream = new FileInputStream(file);

        byte[] bytesBuffer = new byte[2048];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(bytesBuffer)) > 0)
        {
            responseOutputStream.write(bytesBuffer, 0, bytesRead);
        }

        responseOutputStream.flush();

        fileInputStream.close();
        responseOutputStream.close();

        facesContext.responseComplete();

    }
}
