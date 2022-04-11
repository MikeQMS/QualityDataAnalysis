/*
    <Q-Data Analytics tool with xlsx import and MySQL DB>
    Copyright (C) 2022-  MikeQMS

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.example.qdata.beans;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.annotation.RequestScope;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Named
@RequestScope
public class FileDownloadBean {

    public void download(String filename) throws IOException {

        FacesContext facesContext = FacesContext.getCurrentInstance();

        HttpServletResponse response =
                (HttpServletResponse) facesContext.getExternalContext().getResponse();

        response.reset();
        response.setHeader("Content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);

        OutputStream responseOutputStream = response.getOutputStream();

        InputStream fileInputStream = loadExampleXlsx(filename);

        byte[] bytesBuffer = new byte[2048];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(bytesBuffer)) > 0) {
            responseOutputStream.write(bytesBuffer, 0, bytesRead);
        }

        responseOutputStream.flush();

        fileInputStream.close();
        responseOutputStream.close();

        facesContext.responseComplete();

    }

    public InputStream loadExampleXlsx(String filename) {
        try {
            return new ClassPathResource("file/" + filename).getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
